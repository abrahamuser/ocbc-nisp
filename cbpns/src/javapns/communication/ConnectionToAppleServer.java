package javapns.communication;

import java.io.*;
import java.net.*;
import java.security.*;

import javapns.communication.exceptions.*;

import javax.net.ssl.*;

//import org.apache.log4j.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.pex.module.func.ConfirmCreationPex;
import com.silverlake.mleb.pex.util.PropertiesManager;

/**
 * <h1>Class representing an abstract connection to an Apple server</h1>
 * 
 * Communication protocol differences between Notification and Feedback servers are
 * implemented in {@link javapns.notification.ConnectionToNotificationServer} and {@link javapns.feedback.ConnectionToFeedbackServer}.
 * 
 * @author Sylvain Pedneault
 */
public abstract class ConnectionToAppleServer {

	//protected static final Logger logger = Logger.getLogger(ConnectionToAppleServer.class);
	private static Logger logger = LogManager.getLogger(ConnectionToAppleServer.class);
	private PropertiesManager pmgr = new PropertiesManager();

	/* The algorithm used by KeyManagerFactory */
	private static final String ALGORITHM = "sunx509";
	//private static final String ALGORITHM = "IbmX509";//Added IbmX509 for IBM Websphere. Cannot use sunx509 with websphere.

	/* The protocol used to create the SSLSocket */
	private static final String PROTOCOL = "TLS";

	private static final String LOCAL_KEYSTORE_TYPE_PROPERTY = "javapns.communication.keystoreType";

	/* PKCS12 */
	public static final String KEYSTORE_TYPE_PKCS12 = "PKCS12";
	/* JKS */
	public static final String KEYSTORE_TYPE_JKS = "JKS";

	private KeyStore keyStore;
	private SSLSocketFactory socketFactory;
	private AppleServer server;


	/**
	 * Builds a connection to an Apple server.
	 * 
	 * @param server connection details
	 * @throws KeystoreException thrown if an error occurs when loading the keystore
	 */
	public ConnectionToAppleServer(AppleServer server) throws KeystoreException {
		this.server = server;
		this.keyStore = KeystoreManager.loadKeystore(server);
	}


	/**
	 * Builds a connection to an Apple server.
	 * 
	 * @param server connection details
	 * @param keystore
	 */
	public ConnectionToAppleServer(AppleServer server, KeyStore keystore) {
		this.server = server;
		this.keyStore = keystore;
	}


	public AppleServer getServer() {
		return server;
	}


	public KeyStore getKeystore() {
		return keyStore;
	}


	public void setKeystore(KeyStore ks) {
		this.keyStore = ks;
	}


	/**
	 * Generic SSLSocketFactory builder
	 * 
	 * @param trustManagers
	 * @return SSLSocketFactory
	 * @throws KeystoreException 
	 */
	protected SSLSocketFactory createSSLSocketFactoryWithTrustManagers(TrustManager[] trustManagers) throws KeystoreException {

		logger.debug("Creating SSLSocketFactory");
		// Get a KeyManager and initialize it 
		try {
			KeyStore keystore = getKeystore();
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(ALGORITHM);
			try {
				char[] password = KeystoreManager.getKeystorePasswordForSSL(server);
				kmf.init(keystore, password);
			} catch (Exception e) {
				e = KeystoreManager.wrapKeystoreException(e);
				throw e;
			}

			// Get the SSLContext to help create SSLSocketFactory			
			SSLContext sslc = SSLContext.getInstance(PROTOCOL);
			sslc.init(kmf.getKeyManagers(), trustManagers, null);

			return sslc.getSocketFactory();
		} catch (Exception e) {
			throw new KeystoreException("Keystore exception: " + e.getMessage(), e);
		}
	}


	public abstract String getServerHost();


	public abstract int getServerPort();


	/**
	 * Return a SSLSocketFactory for creating sockets to communicate with Apple.
	 * 
	 * @return SSLSocketFactory
	 * @throws KeystoreException
	 */
	public SSLSocketFactory createSSLSocketFactory() throws KeystoreException {
		return createSSLSocketFactoryWithTrustManagers(new TrustManager[] { new ServerTrustingTrustManager() });
	}


	public SSLSocketFactory getSSLSocketFactory() throws KeystoreException {
		if (socketFactory == null) socketFactory = createSSLSocketFactory();
		return socketFactory;
	}


	/**
	 * Create a SSLSocket which will be used to send data to Apple
	 * @return the SSLSocket
	 * @throws KeystoreException 
	 * @throws CommunicationException 
	 */
	public SSLSocket getSSLSocket() throws KeystoreException, CommunicationException
	{
		SSLSocketFactory socketFactory = getSSLSocketFactory();
		logger.debug("Creating SSLSocket to " + getServerHost() + ":" + getServerPort());

		try
		{
			String sendToProxy = pmgr.getProperty("send.to.proxy");
			
			
			//if (ProxyManager.isUsingProxy(server))
			if (sendToProxy != null && sendToProxy.equalsIgnoreCase("true")) // Get the settings from Property File for Proxy enable property	
			{
				return tunnelThroughProxy(socketFactory);
			}
			else
			{
				return (SSLSocket) socketFactory.createSocket(getServerHost(), getServerPort());
			}
		}
		catch (Exception e)
		{
			throw new CommunicationException("Communication exception: " + e, e);
		}
	}


	private SSLSocket tunnelThroughProxy(SSLSocketFactory socketFactory) throws UnknownHostException, IOException
	{
		SSLSocket socket;
		
		// If a proxy was set, tunnel through the proxy to create the connection
		/*String tunnelHost = ProxyManager.getProxyHost(server);
		Integer tunnelPort = ProxyManager.getProxyPort(server);*/
		
		//Get the Proxy IP & Port from properties file
		String tunnelHost = pmgr.getProperty("proxy.ip") != null ? pmgr.getProperty("proxy.ip") : "10.200.0.113";
		Integer tunnelPort = pmgr.getProperty("proxy.port") != null ? Integer.parseInt(pmgr.getProperty("proxy.port")) : 8080;
		
		ProxyManager.setProxy(tunnelHost, ""+tunnelPort);
		
		Socket tunnel = new Socket(tunnelHost, tunnelPort);
		doTunnelHandshake(tunnel, getServerHost(), getServerPort());

		/* overlay the tunnel socket with SSL */
		socket = (SSLSocket) socketFactory.createSocket(tunnel, getServerHost(), getServerPort(), true);

		/* register a callback for handshaking completion event */
		socket.addHandshakeCompletedListener(new HandshakeCompletedListener() {
			public void handshakeCompleted(HandshakeCompletedEvent event) {
				logger.debug("Handshake finished!");
				logger.debug("\t CipherSuite:" + event.getCipherSuite());
				logger.debug("\t SessionId " + event.getSession());
				logger.debug("\t PeerHost " + event.getSession().getPeerHost());
			}
		});

		return socket;
	}


	private void doTunnelHandshake(Socket tunnel, String host, int port) throws IOException {

		OutputStream out = tunnel.getOutputStream();

		StringBuilder header = new StringBuilder();
		//Use \r\n as delimiter to avoid proxy "400 bad request" response
		//HTTP1.0 spec stated to use CRLF as delimiter
		header.append("CONNECT " + host + ":" + port + " HTTP/1.0\r\n");
		header.append("User-Agent: BoardPad Server\r\n");
		String authorization = ProxyManager.getProxyAuthorization(server);
		if (authorization != null && authorization.length() > 0) {
			header.append("Proxy-Authorization: " + authorization + "\r\n");
		}
		header.append("\r\n");//Delimiter to indicate HTTP Body which apparently not available for tunnel handshake.

		String msg = header.toString();//"CONNECT " + host + ":" + port + " HTTP/1.0\n" + "User-Agent: BoardPad Server" + "\r\n\r\n";
		logger.debug("Handshake data: "+msg);
		byte b[] = null;
		try { //We really do want ASCII7 -- the http protocol doesn't change with locale.
			b = msg.getBytes("ASCII7");
		} catch (UnsupportedEncodingException ignored) { //If ASCII7 isn't there, something serious is wrong, but Paranoia Is Good (tm)
			b = msg.getBytes();
		}
		out.write(b);
		out.flush();

		// We need to store the reply so we can create a detailed error message to the user.
		byte reply[] = new byte[200];
		int replyLen = 0;
		int newlinesSeen = 0;
		boolean headerDone = false; //Done on first newline

		InputStream in = tunnel.getInputStream();

		while (newlinesSeen < 2) {
			int i = in.read();
			if (i < 0) {
				throw new IOException("Unexpected EOF from proxy");
			}
			if (i == '\n') {
				headerDone = true;
				++newlinesSeen;
			} else if (i != '\r') {
				newlinesSeen = 0;
				if (!headerDone && replyLen < reply.length) {
					reply[replyLen++] = (byte) i;
				}
			}
		}

		/*
		 * Converting the byte array to a string is slightly wasteful
		 * in the case where the connection was successful, but it's
		 * insignificant compared to the network overhead.
		 */
		String replyStr;
		try {
			replyStr = new String(reply, 0, replyLen, "ASCII7");
		} catch (UnsupportedEncodingException ignored) {
			replyStr = new String(reply, 0, replyLen);
		}

		/* We check for Connection Established because our proxy returns HTTP/1.1 instead of 1.0 */
		if (replyStr.toLowerCase().indexOf("200") == -1) {
			throw new IOException("Unable to tunnel through. Proxy returns \"" + replyStr + "\"");
		}

		/* tunneling Handshake was successful! */
	}
	
	public static String getKeystoreType() {
		String type = System.getProperty(LOCAL_KEYSTORE_TYPE_PROPERTY, KEYSTORE_TYPE_PKCS12);
		if (type == null || type.length() == 0)
			type = KEYSTORE_TYPE_PKCS12;
		return type;
	}

}

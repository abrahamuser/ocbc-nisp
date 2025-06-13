package com.google.android.gcm.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

//import org.apache.commons.httpclient.HttpHost;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class ProxySender extends Sender
{
	//private static Logger log = Logger.getLogger(ProxySender.class);
	private static Logger log = LogManager.getLogger(ProxySender.class);
	private String sendThroughProxy;

	public ProxySender(String key, String sendThroughProxy)
	{
		super(key);
		this.sendThroughProxy = sendThroughProxy;
	}
	
	@Override
	protected HttpsURLConnection getConnection(String gcmUrl, String proxyIp, String proxyPort) throws IOException
	{
		log.info("Getting Connection From ProxySender.. :: "+gcmUrl);
		Proxy proxy = null;
		boolean addProxy = false;
		if(sendThroughProxy != null && sendThroughProxy.equalsIgnoreCase("true") && proxyIp != null && !proxyIp.equals("") && proxyPort != null && !proxyPort.equals(""))
		{
			addProxy = true;
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, Integer.parseInt(proxyPort)));
			log.info("proxyIp :: "+proxyIp);
			log.info("proxyPort :: "+proxyPort);
		}
		
		
		
		
		URL url1 = new URL(gcmUrl);
	
		/*System.clearProperty("https.proxyHost");
		System.clearProperty("https.proxyPort");*/
		
		HttpsURLConnection conn = null;
		
		if(addProxy)
		{
			log.info("PROXY :: "+proxy);
			conn = (HttpsURLConnection) url1.openConnection(proxy);
			log.info("HTTPS PROXY CONNECTION :: "+conn);
		}
		else
		{
			conn = (HttpsURLConnection) url1.openConnection();
			log.info("HTTPS NORMAL CONNECTION :: "+conn);
		}
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "key="+this.getKey());
		
		return conn;
	}
	
}

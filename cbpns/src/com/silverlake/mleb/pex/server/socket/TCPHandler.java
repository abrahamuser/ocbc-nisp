package com.silverlake.mleb.pex.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public  class TCPHandler 
{
  
	private static Logger log = LogManager.getLogger(TCPHandler.class);
	
	public static final int maxMsgSize = 10000;
	public static final String CONNECTION_DISCONNECTED = "@DISCONNECTED@";
	public static final String MESSAGE_ENCODING = "CP037";
	public static final int SERVER_RECEIVE_SEND_TIMEOUT = 30000;


	public void closeConnection(BufferedInputStream in, BufferedOutputStream out, Socket connected)
	{
		try
		{
			if(null!=in)
				in.close();
			if(null!=out)
				out.close();
			if(null!=connected && !connected.isClosed())
				connected.close();
			log.info("Pex TCPServer-Client "+connected.getInetAddress() +":"+connected.getPort()+" IS DISCONNECTED ");
			
		}
		catch(Exception conx)
		{
		
		}
	}

	

	
	
	 public byte[] receiveByte(BufferedInputStream in)throws IOException {
			
			byte[] buffer = new byte[maxMsgSize];
				int msgResponseLength = 0;
				int bite = 0;
				int n = 0;
				int tryReceive = 0;
				while(bite>=0 )
				{
					
					try {
						bite = in.read();
						if(bite==-1)
						{		
							return "CONNECTION_DISCONNECTED".getBytes();
						}
					} 
					catch (SocketException ce) {
						ce.printStackTrace();
				
						return "CONNECTION_DISCONNECTED".getBytes();
					}
					catch (IOException e) {
						
						break;
					}
					
					buffer[n++] = (byte) bite;
					
					
					if(n==9)
					{
						byte[] msgL = {buffer[0],buffer[1],buffer[2],buffer[3],buffer[4],buffer[5],buffer[6],buffer[7],buffer[8]};
						msgResponseLength = Integer.parseInt(new String(msgL,MESSAGE_ENCODING));
						
						
					}
					else if(n>9)
					{
						msgResponseLength--;
						
						if(msgResponseLength == 0)
						{
							break;
						}
					}
					
					
				}

		    return buffer;
		} 
	   
	
	
   
   

   
	  public void sendByte(byte[] msg,BufferedOutputStream out) throws Exception{
		   
	       out.write(msg);
	       
	       out.flush();
		  
	       
	      /* DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
	   outToServer.writeBytes(reqMessage);*/
	   }
   
   
	
}

package com.silverlake.mleb.pex.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;





public class SocketClientUtil {
	
	
	private String ip;
	private int port;
	private int timeoutTcp = 30000;
	private String messageFormat = "";
	private int maxLengthMsg = 1024*7; 
	private Socket sock = null;
	private BufferedOutputStream out = null;
	private BufferedInputStream in = null;
	//private static org.apache.log4j.Logger log = LogManager.getLogger(TCPClientUtil.class);
   public SocketClientUtil(String ip, int port, int timeoutTcp, String messageFormat, int maxLengthMsg) throws Exception {
		this.ip = ip;
		this.port = port;
		this.timeoutTcp = timeoutTcp;
		this.messageFormat = messageFormat;
		this.maxLengthMsg = maxLengthMsg;
		sock=new Socket();
		SocketAddress sockAdd=new InetSocketAddress(ip,port);
		sock.connect(sockAdd, timeoutTcp); 
		sock.setKeepAlive(true);
		sock.setSoTimeout(timeoutTcp);
	}



   public void send(String reqMessage) throws Exception{
	   
       out = new BufferedOutputStream(sock.getOutputStream());
       //out.write(reqMessage.getBytes(messageFormat));
       out.write(reqMessage.getBytes(messageFormat));
       
       out.flush();
	  
       
      /* DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
       outToServer.writeBytes(reqMessage);*/
   }
	
	

	public String  receive()throws IOException {
		in = new BufferedInputStream(sock.getInputStream());
		byte[] buffer = new byte[maxLengthMsg];

		
			int msgResponseLength = 0;
			int bite = 0;
			int n = 0;
			int tryReceive = 0;
			while(bite>=0 )
			{
				
				try {
					bite = in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "TIMEOUT : TRANSACTION TIMEOUT, PLEASE CHECK HISTORY";
				}
				
				buffer[n++] = (byte) bite;
				
				
				if(n==9)
				{
					//msgResponseLength = (new String (buffer,"Cp1047")).length();
					
					String lengtMsg = new String (buffer,messageFormat);
					//log.info("["+lengtMsg.substring(0, 9)+"]");
					msgResponseLength = Integer.parseInt(lengtMsg.substring(0, 9));
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
		
		
		

	    String result = new String(buffer,messageFormat);
	    return result.trim();
	} 
	

	public void closeSocketx() throws IOException
	{
		if(out!=null)
			out.close();
		
		if(in!=null)
			in.close();
		
		//if(sock != null && sock.isConnected())
			sock.close();
	}

	
	public static void main(String args[]) throws Exception
	{
		//Cp1047
		SocketClientUtil tcputil = new SocketClientUtil("localhost",54321,30000,"Cp1047",1000);
		
		              
		tcputil.send("000000007nihaomx");
		
		
		String rData = tcputil.receive();
		
		System.out.println("Response : "+rData);
		

		
		
		BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
		
		String str;
		do {
	         str = br.readLine();
	        
	         if(str.equalsIgnoreCase(":stop"))
	        	 tcputil.closeSocketx();
	         tcputil.send(getFulllength_frontBlank(9, str.length()+"", "0")+str);
	         rData = tcputil.receive();
	 		
	 		System.out.println("Responsex : "+rData);
	         
	      } while(!str.equals("end"));
		
		
		tcputil.closeSocketx();
	}
	
	public static String getFulllength_frontBlank(int length,String data, String blankValue)
	{
		if(data!=null && length>=data.length())
		{
			int blankAdd = length-data.length();
			
			for(int i=0;i<blankAdd;i++)
			{
				data = blankValue+data;
			}
			
			return data;
		}
		else if(data==null)
		{
			return getFulllength_frontBlank(length,"",blankValue);
		}
		else
		{
			return data;
		}
	}
	
	
}
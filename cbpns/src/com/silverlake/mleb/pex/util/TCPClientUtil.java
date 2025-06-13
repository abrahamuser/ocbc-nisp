package com.silverlake.mleb.pex.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;





public class TCPClientUtil {
	

	
	
	
	private String ip;
	private int port;
	private int timeoutTcp = 30000;
	private String messageFormat = "";
	private int maxLengthMsg = 1024*7; 
	private Socket sock = null;
	private BufferedOutputStream out = null;
	private BufferedInputStream in = null;
	//private static org.apache.log4j.Logger log = LogManager.getLogger(TCPClientUtil.class);
 
	public TCPClientUtil()
	{
		
	}
	
	public TCPClientUtil(String ip, int port, int timeoutTcp, String messageFormat, int maxLengthMsg) throws Exception {
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
  

   
   public TCPClientUtil(String ip, int[] portx, int timeoutTcp, String messageFormat, int maxLengthMsg) throws Exception {
		this.ip = ip;
		if(portx.length==1)
		{
			this.port = portx[0];
		}
		else
		{
			this.port = TCPConnectionSplit.addConnection(portx,10,false,sock);
		}
		
		this.timeoutTcp = timeoutTcp;
		this.messageFormat = messageFormat;
		this.maxLengthMsg = maxLengthMsg;
		sock=new Socket();
		SocketAddress sockAdd=new InetSocketAddress(ip,port);
		sock.connect(sockAdd, timeoutTcp); 
		sock.setKeepAlive(true);
		sock.setSoTimeout(timeoutTcp);
		//TCPConnectionSplit.printRecords(portx);
	}
   

   public byte[]  getlengthData(String msg , String msgFormat)throws Exception
   {
	   byte[] fullByte = msg.getBytes();
	      
       byte[] msgLength = { fullByte[0],fullByte[1],fullByte[2],fullByte[3]};
       byte[] remainMsg = new byte[fullByte.length-4];
       
       byte[] convertedByte = msgFormat == null? msg.getBytes():msg.getBytes(msgFormat);
       
       System.out.println("TCP SEND DATA ["+msgFormat+"] : "+toHexString(convertedByte));
       
       
       for(int i=0;i<remainMsg.length;i++)
       {
    	   remainMsg[i] =  convertedByte[i+4];
       }
       
       
       byte[] combineMsg = new byte[fullByte.length];
       System.arraycopy(msgLength,0,combineMsg,0,msgLength.length);
       System.arraycopy(remainMsg,0,combineMsg,msgLength.length,remainMsg.length);
       
       return combineMsg;
   }
   
   
   public void send(String reqMessage, String bodyMessage,String hdx0,String hdx1,String ex0,String ex1) throws Exception{
	   
       out = new BufferedOutputStream(sock.getOutputStream());
       //out.write(reqMessage.getBytes(messageFormat));
       
     /*  byte[] fullByte = reqMessage.getBytes();
      
       byte[] msgLength = { fullByte[0],fullByte[1],fullByte[2],fullByte[3]};
       byte[] remainMsg = new byte[fullByte.length-4];
       
       byte[] convertedByte = reqMessage.getBytes(messageFormat);
       
       for(int i=0;i<remainMsg.length;i++)
       {
    	   remainMsg[i] =  convertedByte[i+4];
       }
       
       
       byte[] combineMsg = new byte[fullByte.length];
       System.arraycopy(msgLength,0,combineMsg,0,msgLength.length);
       System.arraycopy(remainMsg,0,combineMsg,msgLength.length,remainMsg.length);
       
       
       */
       
       
       byte[] header = getlengthData(reqMessage,messageFormat);
       byte[] body = getlengthData(bodyMessage,messageFormat);
       byte[] hd0 = hdx0.getBytes();
       byte[] hd1 = hdx1.getBytes(messageFormat);
       byte[] ext0 = ex0.getBytes();
       byte[] ext1 = ex1.getBytes(messageFormat);
       
       
     
       byte[] combineHOSTMsg = new byte[header.length+body.length+hd0.length+hd1.length+ext0.length+ext1.length];
       System.arraycopy(header,0,combineHOSTMsg,0,header.length);
       System.arraycopy(body,0,combineHOSTMsg,header.length,body.length);
       
       System.arraycopy(hd0,0,combineHOSTMsg,header.length+body.length,hd0.length);
       System.arraycopy(hd1,0,combineHOSTMsg,header.length+body.length+hd0.length,hd1.length);
       
       System.arraycopy(ext0,0,combineHOSTMsg,header.length+body.length+hd0.length+hd1.length,ext0.length);
       System.arraycopy(ext1,0,combineHOSTMsg,header.length+body.length+hd0.length+hd1.length+ext0.length,ext1.length);
       
    		   
     // byte[] fullBytex = { fullByte[0],fullByte[1], fullByte[2],  fullByte[3]};   

      // out.write(reqMessage.getBytes(messageFormat));
       
       out.write(combineHOSTMsg);
       
       out.flush();
	  
       
      /* DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
       outToServer.writeBytes(reqMessage);*/
   }
   
   
   
   public void send(byte[] msg) throws Exception{
	   
       out = new BufferedOutputStream(sock.getOutputStream());
      
       
       out.write(msg);
       
       out.flush();
	  
       
      /* DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
       outToServer.writeBytes(reqMessage);*/
   }
	
   
   
   public byte[] receiveByte()throws IOException {
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
					return null;
				}
				
				buffer[n++] = (byte) bite;
				
				
				if(n==4)
				{
					//msgResponseLength = (new String (buffer,"Cp1047")).length();
					
					//String lengtMsg = new String (buffer);
					byte[] msgL = {buffer[0],buffer[1],buffer[2],buffer[3]};
					//log.info("["+lengtMsg.substring(0, 9)+"]");
					
					msgResponseLength = ByteBuffer.wrap(msgL).getInt();
					
					System.out.println("RBS MSG LENGTH : "+msgResponseLength);
				}
				else if(n>4)
				{
					msgResponseLength--;
					
					if(msgResponseLength == 0)
					{
						break;
					}
				}
				
			
			}
		
		
			System.out.println("RBS MSG BODY : "+new String(buffer,"CP037"));

	    
	    return buffer;
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
				
				
				if(n==4)
				{
					//msgResponseLength = (new String (buffer,"Cp1047")).length();
					
					//String lengtMsg = new String (buffer);
					byte[] msgL = {buffer[0],buffer[1],buffer[2],buffer[3]};
					//log.info("["+lengtMsg.substring(0, 9)+"]");
					msgResponseLength = ByteBuffer.wrap(msgL).getInt();
					
					System.out.println("RECEIVE MSG LENGTH : [[[[[["+msgResponseLength+"]]]]]");
				}
				else if(n>4)
				{
					msgResponseLength--;
					
					if(msgResponseLength == 0)
					{
						break;
					}
				}
			}
		
		
		

	    String result = new String(buffer,messageFormat);
	    return result;
	} 
	
	
	
	public String  receivex() throws IOException{
		in = new BufferedInputStream(sock.getInputStream());
		byte[] buffer = new byte[maxLengthMsg];
		byte[] bufferx  = null;
			int msgResponseLength = 0;
			int bite = 0;
			int n = 0;
			int tryReceive = 0;
			String result = "";
			try
			{
			
				while(bite>=0 )
				{
	
					
					try {
						bite = in.read();
							if(bite==-1)
							{		
								
								return "@DISCONNECTED@";
							}
						
						} catch(SocketException ce)
						{
							//log.info("SocketException : "+ce.getMessage());
							
							return "@DISCONNECTED@";
							
						}			
						catch (IOException e) {
							result = new String(buffer,messageFormat);
							return result.trim();
						}
				
					buffer[n++] = (byte) bite;
					
					
					if(n==9)
					{
					
						String lengtMsg = new String (buffer,messageFormat);
						msgResponseLength = Integer.parseInt(lengtMsg.substring(0, 9));
						
						bufferx = new byte[9+msgResponseLength];
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
				
				System.arraycopy(buffer, 0, bufferx, 0, bufferx.length);
				result = new String(bufferx,messageFormat);
			}
			catch (Exception ex) {
				//log.info("PEx receive msg format exception", ex);
				return "@DISCONNECTED@";
			}

	
			
	    return result;
	}
	

	public void closeSocketx()
	{
		try
		{
		if(out!=null)
			out.close();
		
		if(in!=null)
			in.close();
		
		if(sock != null && sock.isConnected())
			sock.close();
		}catch (Exception e)
		{
			
		}
	}

	
	public static void main(String args[]) throws Exception
	{
		TCPClientUtil xa = new TCPClientUtil();
	       byte[] header = xa.getlengthData("123",null);
	       byte[] body = xa.getlengthData("456",null);
	       byte[] ext0 = xa.getlengthData("789",null);
	       byte[] ext1 = xa.getlengthData("012",null);
	       
	       
	     
	       byte[] combineHOSTMsg = new byte[header.length+body.length+ext0.length+ext1.length];
	       System.arraycopy(header,0,combineHOSTMsg,0,header.length);
	       System.arraycopy(body,0,combineHOSTMsg,header.length,body.length);
	       System.arraycopy(ext0,0,combineHOSTMsg,header.length+body.length,ext0.length);
	       System.arraycopy(ext1,0,combineHOSTMsg,header.length+body.length+ext0.length,ext1.length);
	       
	       
	       System.out.println(new String (combineHOSTMsg));
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
	
	
	public String toHexString(byte[] bytes) {
	    char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    char[] hexChars = new char[bytes.length * 2];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j*2] = hexArray[v/16];
	        hexChars[j*2 + 1] = hexArray[v%16];
	    }
	    return new String(hexChars);
	}
	
}
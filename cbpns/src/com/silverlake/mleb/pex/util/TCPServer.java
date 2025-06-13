package com.silverlake.mleb.pex.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer 
{
   public static void main(String argv[]) throws Exception
      {
         String fromclient;
         String toclient;
          
         ServerSocket Server = new ServerSocket (5000);
         
         System.out.println ("TCPServer Waiting for client on port 5000");

         while(true) 
         {
         	Socket connected = Server.accept();
            System.out.println( " THE CLIENT"+" "+
            connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");
            
            //BufferedReader inFromUser = 
            //new BufferedReader(new InputStreamReader(System.in));    
     
           // BufferedReader inFromClient =new BufferedReader(new InputStreamReader (connected.getInputStream()));
                  
            //PrintWriter outToClient =new PrintWriter(connected.getOutputStream(),true);
            
            System.out.println( "RECIEVING ......" );
           
            BufferedInputStream in = new BufferedInputStream(connected.getInputStream());
            BufferedOutputStream out = new BufferedOutputStream(connected.getOutputStream());
            
            	String rData = receive(in);

            	System.out.println( "RECIEVED:" + rData );
            	
            	
            	send("000000008"+"Good Bye",out);
            	
          }
      }
   
   
   public static String  receive(BufferedInputStream in) throws Exception{
		//in = new BufferedInputStream(sock.getInputStream());
		byte[] buffer = new byte[1000];

		try
		{
			int msgResponseLength = 0;
			int bite = 0;
			int n = 0;
			int tryReceive = 0;
			while(bite>=0 )
			{
				bite = in.read();
				buffer[n++] = (byte) bite;
				
				
				if(n==9)
				{
					//msgResponseLength = (new String (buffer,"Cp1047")).length();
					
					String lengtMsg = new String (buffer,"Cp1047");
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
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	    String result = new String(buffer,"Cp1047");
	    return result;
	}
   
   
   public static void  send(String reqMessage, BufferedOutputStream out) throws Exception{
	   
      
       //out.write(reqMessage.getBytes(messageFormat));
       out.write(reqMessage.getBytes("Cp1047"));
       
       out.flush();
	  
       
      /* DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
       outToServer.writeBytes(reqMessage);*/
   }
}

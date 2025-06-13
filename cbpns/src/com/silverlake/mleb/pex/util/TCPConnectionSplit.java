package com.silverlake.mleb.pex.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.sf.ehcache.Element;



public class TCPConnectionSplit 
{
	
	private static final int maxConnectionPerPort = 200;
	
	private static EhcacheSession ehCache = new EhcacheSession();

	public static void main (String [] args) throws Exception
	{
		
		Thread tp = new Thread(new PortPrint() );
		tp.start();
		
		Thread t1;
		for(int i=0 ; i<6;i++)
		{
			Random rnd = new Random();
				try{Thread.sleep(1*10000);}catch(Exception ex){}
			 t1 = new Thread(new PortCheck());
			 t1.start();
		}

		
	}
	
	
	public static int addConnection(int[] port, int retry, boolean forceAdd, Socket socket)
	{
		boolean isRetry = true;
	
		for(int prt : port)
		{
	
			String keyCount = "["+prt+"]";
			
			Object data = ehCache.getDspCallBalancer().putIfAbsent(new Element(prt, "1"));
			
			if(null == data)
			{
				
				Element rCount = ehCache.getDspCallBalancer().get(keyCount);
				if(null == rCount)
				{
					List<Socket> sockets = new ArrayList();
					sockets.add(socket);
					ehCache.getDspCallBalancer().put(new Element(keyCount, sockets));
					ehCache.getDspCallBalancer().put(new Element(keyCount+"x", 1));
					ehCache.getDspCallBalancer().remove(prt);
					return prt;
				}
				else
				{
			
					List<Socket> sockets =  (List<Socket>) rCount.getObjectValue();
				
					if(maxConnectionPerPort>sockets.size())
					{
						sockets.add(socket);
						
						ehCache.getDspCallBalancer().put(new Element(keyCount, sockets));
						int xcount = (Integer.parseInt(ehCache.getDspCallBalancer().get(keyCount+"x").getObjectValue().toString()))+1;
						ehCache.getDspCallBalancer().put(new Element(keyCount+"x", xcount));
						ehCache.getDspCallBalancer().remove(prt);
						return prt;
					}
					else if(forceAdd)
					{
						sockets.add(socket);
						ehCache.getDspCallBalancer().put(new Element(keyCount, sockets));
						int xcount = (Integer.parseInt(ehCache.getDspCallBalancer().get(keyCount+"x").getObjectValue().toString()))+1;
						ehCache.getDspCallBalancer().put(new Element(keyCount+"x", xcount));
						ehCache.getDspCallBalancer().remove(prt);
						return prt;
					}
					else
					{
						
						Iterator<Socket> itrSocket = sockets.iterator();
						boolean removeCount = false;
						while(itrSocket.hasNext())
						{
							
							if(null == itrSocket.next())
							{
								itrSocket.remove();
								removeCount = true;
								break;
							}
							else if(itrSocket.next().isClosed())
							{
								itrSocket.remove();
								removeCount = true;
								break;
								
							}
						}
						
						if(removeCount)
						{
							ehCache.getDspCallBalancer().remove(prt);
							break;
						}
						
					}
					
					
				}
				
				ehCache.getDspCallBalancer().remove(prt);
			}
			else if(!checkFullLoad(prt))
			{
				isRetry = false;
				break;
			}
			
			
			
			
			
		}
		
		
		if(retry <= 0)
		{
			forceAdd = false;
		}
		else if(isRetry)
		{
			retry = retry - 1;
			try{Thread.sleep(50);}catch(Exception ex){}
		}
		
		
		
		return addConnection(port,retry,forceAdd,socket);
	}
	
	
	
	
	public static boolean  checkFullLoad(int port)
	{
		String keyCount = "["+port+"]";
		
		Object rCount = ehCache.getDspCallBalancer().get(keyCount);
		if(null==rCount)
		{
			return false;
		}
		else if(null!=rCount)
		{
			List<Socket> sockets =  (List<Socket>) rCount;
			if(maxConnectionPerPort>sockets.size())
			{
				return false;
			}
			else
			{
				
				List<Socket> socketx = new ArrayList<Socket>(sockets);
				
				Iterator<Socket> itrSocket = socketx.iterator();
				
				
				while(itrSocket.hasNext())
				{
					Socket sc = itrSocket.next();
					if(null!= sc && sc.isClosed())
					{
						return false;
					}
				}
			}
					
		}
		
		
		
		return true;
	}

	/*public static void releaseConnection(int prt)
	{
		Object data = records.putIfAbsent(prt, "1");
		String keyCount = "["+prt+"]";
		if(null == data)
		{
			int rCount = (Integer) records.get(keyCount);
			rCount = rCount - 1 ;
			
			records.put(keyCount, rCount);
			
			
			records.remove(prt);
		}
		else
		{
			try{Thread.sleep(50);}catch(Exception ex){}
			releaseConnection(prt);
		}
		
		
	}*/
	
	public static void printRecords(int[] ports)
	{
		
    		
    		
    		for(int port :ports)
    		{
    			String keyCount = "["+port+"]";
    			
    			

    			Object  socketsx =  ehCache.getDspCallBalancer().get(keyCount);
    			if(null!=socketsx)
    			{
    				List<Socket> sockets =  (List<Socket>) socketsx;
    				List<Socket> sock = new ArrayList<Socket>(sockets);
 
    			
    				for(Socket soc:sock)
    				{
    					String sclose = "";
    					
    					if(null != soc)
    					{
    						sclose = soc.isClosed()+"";
    						
    						
    						
    					}
    					System.out.println(port+" ["+ehCache.getDspCallBalancer().get("["+port+"]x")+"] " + " -- " + sclose);
    				}
    			}
    			
    		
    			
    			
    		}
    		
    		
			System.out.println("\n\n\n\n");
			try{Thread.sleep(200);}catch(Exception ex){}
		
	}
	
	
	
}



class PortCheck implements Runnable {

    public void run() {
    	Random rnd = new Random();
    	int[] ports = {54321,54322};
    	Socket sock = new Socket();
    	int port  = TCPConnectionSplit.addConnection(ports,10,false,sock);
    	SocketAddress sockAdd=new InetSocketAddress("localhost",port);
		try {
			
			sock.connect(sockAdd, 30000);
		
       // System.out.println("port : "+port);
		
		if(port == 54321)
		{
			
			try{Thread.sleep(rnd.nextInt(3)*100);}catch(Exception ex){}
		}
		else
		{
			
			try{Thread.sleep(rnd.nextInt(3)*100);}catch(Exception ex){}
			
		
			
		}
		sock.close();
		sock = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				sock.close();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} 
		
		
    	
    	
    
    	//TCPConnectionSplit.releaseConnection(port);
    }
}



class PortPrint implements Runnable {

	
	public PortPrint()
	{
		
	}
	
    public void run() {
    	int[] ports = {54321,54322};
    	//while(true)
    	//TCPConnectionSplit.printRecords(ports);
    }
}
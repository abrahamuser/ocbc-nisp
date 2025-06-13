package com.silverlake.mleb.pex.util;






public class TCPClientUtil2  implements Runnable {
	
	public String data;
	
	TCPClientUtil2(String datax)
	{
		setData(datax);
	}
	
	
	public static void main(String args[]) throws Exception
	{
		/*for(int i=0;i<1000;i++)
		 {
		 Thread t1 = new Thread(new TCPClientUtil2("nihaoma xie xie ni"));
		 Thread t2 = new Thread(new TCPClientUtil2("xxxxxxxxxxxxxxxxxxxxxx"));
		 Thread t3 = new Thread(new TCPClientUtil2("salaoooooooooooooooo00"));
		 Thread t4 = new Thread(new TCPClientUtil2("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"));
		 Thread t5 = new Thread(new TCPClientUtil2("stopssssssssssssssssssssssssssssssssss"));
		 
		 
		 Thread t11 = new Thread(new TCPClientUtil2("the movie"));
		 Thread t22 = new Thread(new TCPClientUtil2("after earth"));
		 Thread t33 = new Thread(new TCPClientUtil2("man of steel"));
		 Thread t44 = new Thread(new TCPClientUtil2("war world Zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"));
		 Thread t55 = new Thread(new TCPClientUtil2("Iron Mannnnnnnnnnnnn"));
		 
		 
		 Thread t111 = new Thread(new TCPClientUtil2("Support System"));
		 Thread t222 = new Thread(new TCPClientUtil2("Identity & Access Management (IAM)"));
		 Thread t333 = new Thread(new TCPClientUtil2("Business Domain"));
		 Thread t444 = new Thread(new TCPClientUtil2("OWASP xx@xx what the thing inside the transaction payload"));
		 Thread t555 = new Thread(new TCPClientUtil2("MLEB 1.01 1234567890"));
		 
		 
			
			 t1.start();
			 t2.start();
			 t3.start();
			 t4.start();
			 t5.start();
			 
			 t11.start();
			 t22.start();
			 t33.start();
			 t44.start();
			 t55.start();
			 
			 t111.start();
			 t222.start();
			 t333.start();
			 t444.start();
			 t555.start();
			 Thread.sleep(2000);
			 
			 
			
		 }
		 */
		
		String data1 = "pex.0000123";
		
		
		System.out.println(data1.split("\\.")[1]);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		TCPClientUtil tcputil;
		try {
			tcputil = new TCPClientUtil("localhost",54321,30000,"Cp1047",100);
		
		
		//tcputil.send(tcputil.getFulllength_frontBlank(9, getData().length()+"", "0")+getData());
		
		String rData = tcputil.receive();
		
		if(getData().equalsIgnoreCase(rData.substring(9, rData.length())))
		{
			System.out.println("["+getData()+"]:: Response :: "+rData);
		}
		else
		{
			System.out.println("!!!!!!!!!!!!!!WARNING!!!!!!!!!!!!!!!["+getData()+"]:: Response :: "+rData);
		}
		
		
		
		tcputil.closeSocketx();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	

	
	
}
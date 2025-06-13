package com.silverlake.mleb.pex.util;

import com.silverlake.mleb.pex.entity.PexConf;




public class TestStaticSplit 
{
	

	public static void main (String [] args) throws Exception
	{
		
		try
		{
			PexConf ax = new PexConf();
		String a = null;
		a = a.replace("\\{0\\}", "");
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}

		
	}
	
	
	
	
	public static synchronized String getData(int x)
	{
		if(x == 0)
		{
			System.out.println("WAIT : "+x);
			try{Thread.sleep(1*10000);}catch(Exception ex){}
		}
		
		
		return "";
	}
	

}



class PortPrintx implements Runnable {

	int x = 0;
	public PortPrintx(int x)
	{
		this.x = x;
	}
	
    public void run() {
    	//TestStaticSplit xs = new TestStaticSplit();
    	System.out.println("START : "+x);
    	TestStaticSplit.getData(x);
    	
    	System.out.println("END : "+x);
    }
}
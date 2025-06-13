package com.silverlake.mleb.mcb.listerner;

import org.apache.commons.codec.digest.DigestUtils;




public class TestManager {
	
	
	public static void main(String args[]) throws Exception
	{
		ExtApiManager ext = new ExtApiManager();
		String rs = ext.calculateHMAC256("abc123", "abc");
		System.out.println("["+rs+"]");
		
		String rs2 = DigestUtils.sha256Hex("abc123");
		System.out.println("["+rs2+"]");
	}


}

package com.silverlake.mleb.mcb.module.func.registration;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.others.InquiryTransTimeBatchRequestData;
import com.silverlake.mleb.mcb.module.vc.others.TimeBatchList;

public class Test {

	 public static void main(String[] args) throws ParseException {
		   		  
		   /* String sDate1="2021-09-03";  
		    Date d2=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  
		    System.out.println(d2);  
	 
	
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date d1 = sdf.parse(sdf.format(new Date()));
		   
		 	    
		    System.out.println(d1); 
		   
		  int result = d1.compareTo(d2);
		    
		    System.out.println(result);
		    
		       if (result == 0) {
	            System.out.println("Date1 is equal to Date2");
	        } else if (result > 0) {
	            System.out.println("Date1 is after Date2");
	        } else if (result < 0) {
	            System.out.println("Date1 is before Date2");
	        } else {
	            System.out.println("How to get here?");
	        }
	*/ 		    
		    /*if(date.compareTo(d2) > 0) {
		         System.out.println("Date 1 occurs after Date 2");
		      } else if(date.compareTo(d2) < 0) {
		         System.out.println("Date 1 occurs before Date 2");
		      } else if(date.compareTo(d2) == 0) {
		         System.out.println("Both dates are equal");
		      }*/
		/* String revisionNo= "";
		 String sDate1="email"; 
		 String sDate2="email1234"; 
		 
		 System.out.println("-------revisionNo---"+revisionNo); 
		 if(revisionNo != null && !revisionNo.isEmpty()){
			 System.out.println("hello");  
		 }
		 if(sDate1.startsWith("email")){
			 System.out.println("Both dates are equal"); 
		 }
		*/
		 
		/* Calendar cal = Calendar.getInstance();
			long expiredMiliUnits = cal.getTimeInMillis() - (180 * 24 * 60 *  60 * 1000);
			cal.setTimeInMillis(expiredMiliUnits);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 System.out.println("Purging records before "+sdf.format(cal.getTime()));*/
		 
	//	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//	String currentDate = sdf.format(new Date()); 
		// System.out.println("---date---"+date);
			
			/* SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			
			cal = Calendar.getInstance();
			cal.add(Calendar.HOUR, -1);
			System.out.println(cal.getTime());*/
		
			/*Calendar cal = Calendar.getInstance();
			long expiredMiliUnits = cal.getTimeInMillis() - (180  * 24 * 60 *  60 * 1000);
			System.out.println("expiredMiliUnits "+cal.getTimeInMillis());
			cal.setTimeInMillis(expiredMiliUnits);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("Purging records before "+sdf.format(cal.getTime()));*/
		 
		
		/* InquiryTransTimeBatchRequestData batchRequestData = new InquiryTransTimeBatchRequestData();
		 String transfer = "ABC,BCD";
		 
		 String[] prod_cd_list = transfer==null?null:transfer.split(",");
			if(null!=prod_cd_list)
			{
				batchRequestData.setProd_cd_list(new ArrayList());
				for(String prd_cd:prod_cd_list)
				{
					batchRequestData.getProd_cd_list().add(prd_cd);
				}
				System.out.println(""+batchRequestData.getProd_cd_list());
			}
	*/
		 
		  BigDecimal b1 = new BigDecimal("10");
	        BigDecimal b2 = new BigDecimal("");
	  
	        // Multiply b1 with b2 and assign result to b3
	        BigDecimal b3 = b1.multiply(b2);
	  
	        // Print b3 value
	        System.out.println("Multiplication is " + b3);
		 
		 
	 }
}

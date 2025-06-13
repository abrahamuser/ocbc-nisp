package com.silverlake.mleb.mcb.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.MibTypeCode;



public class AdaptUtil
{
	
	//convert status = SUCCESS -> SUCCESSFUL 
	private static Logger log = LogManager.getLogger(AdaptUtil.class);
	private static PropertiesManager prop = new PropertiesManager();
	public static final String mapTransactionStatus(String transStatus, String locale){
		
		String result = ""; 
		
		if(transStatus != null){
			//Replacement Success
			if(locale.equalsIgnoreCase("en")){
				
				if(transStatus.equalsIgnoreCase("SUCCESS")){
					result = MiBConstant.STATUS_TRANSACTION_SUCCESS;			
				}
				else if(transStatus.equalsIgnoreCase("FAILED")){
					result = MiBConstant.STATUS_TRANSACTION_FAILED;			
				}
				else if(transStatus.equalsIgnoreCase("SUBMIT")){
					result = MiBConstant.STATUS_TRANSACTION_SUBMIT;			
				}
				else if(transStatus.equalsIgnoreCase("SCHED")){
					result = MiBConstant.STATUS_TRANSACTION_SCHED;
				}
				else if(transStatus.equalsIgnoreCase("CANCEL")){
					result = MiBConstant.STATUS_TRANSACTION_CANCEL;
				}
				else if(transStatus.equalsIgnoreCase("SKIP")){
					result = MiBConstant.STATUS_TRANSACTION_SKIP;
				}
				else if(transStatus.equalsIgnoreCase("COMPLETE")){
					result = MiBConstant.STATUS_TRANSACTION_COMPLETE;
				}
				else {
					result = transStatus; 
				}
				
			}
			
		}else{
			return "";
		}
		
		return result; 
	}
	
	
	
	//map Bank Name = HLB -> HONG LEONG BANK 
	public static final String mapBankName(String bankCode)
	{
		String result = ""; 
		log.info("bankCode :: "+bankCode);
		try
		{
			if(bankCode != null && !bankCode.equals(""))
			{
				result = prop.getProperty("BANK_CODE_"+bankCode);
			}
		}
		catch(Exception e)
		{
			log.info("DIDNT FIND BANK NAME FOR THE BANKCODE :: "+bankCode);
		}
		
		return result; 
	}
		
	//check whether exist available balance 
	public static final Boolean checkExistTransac(BigDecimal availableBalance, BigDecimal payAmount){
		if(availableBalance.compareTo(payAmount) == -1 ) {
			
			return false; 
		}
		
		return true;  
	}
	
	public static final String mapInterestPaymentMethod(String autoRenewCode){
		
		String result = "";
		if (autoRenewCode.equalsIgnoreCase("Y")){
			result = "Auto";
			}
			else{
				result = "";
			}
		return result; 		
	}
	
	public static final String mapStepUpMessage(Boolean stepUp, Boolean mach, String tenureCode){
		
		String stepUpMessage = "";
		if(stepUp == false){
			if(mach == true){
				stepUpMessage = "";
			}else{
				if(tenureCode.equalsIgnoreCase("D")){
					stepUpMessage = "PB Premium Savings";
				}else{
					stepUpMessage = "";
				}
			}

		} else if (stepUp == true && mach == false) {
			stepUpMessage = "# It is a Fixed Deposit Step-Up Rate Promotion. Interest rate stipulated is the tier interest rate for that month.";
		}else{
			stepUpMessage = "";
		}
		
		return stepUpMessage; 		
	}
	
	
	public static final String roundUpDecimal(String input){
		
		String result = "";
		String formatInput = input.replace(",", ""); 
		Double convertStr2Dob = 0.00; 
		
		String stringFormat = "";
		NumberFormat myFormat = NumberFormat.getInstance();
	    myFormat.setGroupingUsed(true);
		
		//specific cambodia format 
		
		if(input.contains(",") && input.contains(".")){

			try { 

				convertStr2Dob = Double.parseDouble(formatInput);
				long amountAfterFormat = Math.round(convertStr2Dob); 
				result = String.valueOf(myFormat.format(amountAfterFormat)); 
				
		    } catch(NumberFormatException e) {	    	
		       result = "0";  	       
		    }

		}else if(input.contains(",")){
			
			result = input;
			
		}else{
			
			try { 
				
				convertStr2Dob = Double.parseDouble(input);
				long amountAfterFormat = Math.round(convertStr2Dob); 
				result = String.valueOf(amountAfterFormat); 
				
		    } catch(NumberFormatException e) {	    	
		       result = "0";  	       
		    }
			
		}
		
				
		

		return result; 
	}
		
	
	public static final String mapLienStatus(String lienStatus, String locale){
		
		String result = "";
		if(lienStatus == null || "".equalsIgnoreCase(lienStatus ) ){
			if(locale.equalsIgnoreCase("en")){
				result = "No";
			}else if(locale.equalsIgnoreCase("km_KH")){
				result = MiBConstant.OPTION_NO_KH; 
			}
		}else{
			
			if(locale.equalsIgnoreCase("en")){
				result = lienStatus; 
			}else if(locale.equalsIgnoreCase("km_KH")){
				
				if(lienStatus.equalsIgnoreCase("No")){
					result = MiBConstant.OPTION_NO_KH;
				}else if(lienStatus.equalsIgnoreCase("Yes")){
					result = MiBConstant.OPTION_YES_KH;
				}
								
			}
			
			
		}
		
		return result; 
	}
	
	//purpose: reformat string's space
	public static final String reformatString(String input){
		
		String result = ""; 
		String finalResult = "";
		int lengthString = 0; 	                     
		String tokens[] = input.split(" ");
				
		for(String s : tokens){		
			lengthString = s.trim().length();   
			
			//array with value only
			if(lengthString>0){
				result += s.trim()+" ";				
			}					 
        }
		
		finalResult = result.trim(); //remove front and back space

		return finalResult; 
	}
	
	
	public static final String combineReferences(String remark1, String remark2){
		
		
		String result = remark1;
		
		if(remark2 != null && !remark2.equals(""))
		{
			result = result+"\n"+remark2;
		}
		
	
		return result;   
	}
	
	
//	public static void main(String[] args) {
//		
//		String test = "500,000.50"; 
//		log.debug(roundUpDecimal(test)); 
//		String test = "                Hello nice to meet u gygy byebye           "; 
//		String finalResult = reformatString(test); 
//		System.out.println("<start>"+finalResult+"<end>");
//
//	}
	
	public static final String mapTransactionPurposeType(Integer intNum){
		String resp = "";
		if(intNum==1){
			resp = "PR001";
		}
		else if(intNum==1){
			resp = "PR002";
		}
		
		return resp;
		
	}
	
	public static final String mapTransactionPurposeTypeDesc(Integer intNum){
		String resp = "";
		if(intNum==1){
			resp = MiBConstant.TRANS_PURPOSE_TYPE_INDIVIDUAL;
		}
		else if(intNum==1){
			resp = MiBConstant.TRANS_PURPOSE_TYPE_BUSINESS;
		}
		
		return resp;
		
	}
	
	public static final String mapRecurringType(Integer i){
		String resp = "";
		if(i!=null){
			resp = MibTypeCode.getRecurringTypeDesc(i);
		}
		
		return resp;
		
	}
	
	public static final String processOmniDate(String date){

		String formatDate = "0000-00-00";
		if(date==null || date.equalsIgnoreCase("00000000")){
			return formatDate;
		}
		else{
			return date;
		}

	}
	
	public static final String processCompareDate(String sourceDate, String targetDate, boolean maxFlag) throws ParseException{

		String emptyDate = "0000-00-00";

		if(sourceDate==null || sourceDate.equalsIgnoreCase("00000000")){
			sourceDate = emptyDate;
		}
		if(targetDate==null || targetDate.equalsIgnoreCase("00000000")){
			targetDate = emptyDate;
		}
		
		if(sourceDate==emptyDate && targetDate==emptyDate){
			return emptyDate;
		}

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateResult = "";
		if(sourceDate!=null && targetDate!=null){
			Date date1 = sdf.parse(sourceDate);
			Date date2 = sdf.parse(targetDate);
			
			if(maxFlag){
				// if maxFlag is true, retrieve the maximum date
				if (date1.compareTo(date2) > 0) {
					dateResult =  sdf.format(date1);
				} else if (date1.compareTo(date2) < 0) {
					dateResult =  sdf.format(date2);
				} else if (date1.compareTo(date2) == 0) {
					dateResult =  sdf.format(date1);
				}
				
			}
			else{
				// if maxFlag is false, retrieve the minimum date
				if (date1.compareTo(date2) > 0) {
					dateResult =  sdf.format(date2);
				} else if (date1.compareTo(date2) < 0) {
					dateResult =  sdf.format(date1);
				} else if (date1.compareTo(date2) == 0) {
					dateResult =  sdf.format(date1);
				} 
			}

		}
		return dateResult;
	}
}

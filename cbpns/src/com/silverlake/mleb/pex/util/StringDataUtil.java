package com.silverlake.mleb.pex.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.mleb.pex.constant.PExConstant;



public class StringDataUtil
{
	
	
	public StringDataUtil()
	{
		
	}
	
	
	public String getFulllength_backBlank(int length,String data, String blankValue)
	{
		if(data!=null && length>=data.length())
		{
			int blankAdd = length-data.length();
			
			for(int i=0;i<blankAdd;i++)
			{
				data = data+blankValue;
			}
			
			return data.substring(0, length);
			
		}
		else if(data==null)
		{
			return getFulllength_backBlank(length,"",blankValue);
		}
		else
		{
			System.out.println("WARNING !!! : FIELD LENGTH NOT MATCH ["+length+"]["+data+"]");
			return data;
		}
	}
	
	
	
	public String getFulllength_frontBlank(int length,String data, String blankValue)
	{
		if(data!=null && length>=data.length())
		{
			int blankAdd = length-data.length();
			
			for(int i=0;i<blankAdd;i++)
			{
				data = blankValue+data;
			}
			
			return data.substring(0, length);
		}
		else if(data==null)
		{
			return getFulllength_frontBlank(length,"",blankValue);
		}
		else
		{
			System.out.println("WARNING !!! : FIELD LENGTH NOT MATCH ["+length+"]["+data+"]");
			return data;
		}
	}
	
	public String convertDecimalToSCSString(BigDecimal data)
	{
		String result = "";
		
		if(data!=null)
		{
			try{
				if(data.compareTo(new BigDecimal(0)) >= 0)
					result = data.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_DOWN).toString();
			}
			catch(Exception e)
			{
				result = "";
			}
		}
		return result;
	}
	
	public BigDecimal convertSCSStringToDecimal(String data)
	{
		BigDecimal result = new BigDecimal("0.00");
		
		if(data!=null && data.length()>0)
		{
			try{
				result = new BigDecimal(data);
				if(result.compareTo(new BigDecimal(0))>0)
					result = result.divide(new BigDecimal(100));
			}
			catch(Exception e)
			{
				result = null;
			}
		}
		return result;
	}
	
	public String getNullIfEmpty(String data)
	{
		System.out.println("REDEEM POINT !!!!!!!!!!!!!!!!!! "+data);
		if(data!=null && data.length()<=0)
		{
			data = "0";
		}
		
		return data;
	}
	
	
	
	public String getLoyalPointDecimal(String data)
	{
		if(data!=null)
		{
			
			double dataDecimal = Double.parseDouble(data)/100;
		
			String CURRENCY_DISPLAY_PRICE_FORMAT = "#,##0";
			NumberFormat pointDisplayFormatter = new DecimalFormat(CURRENCY_DISPLAY_PRICE_FORMAT);
			
			return pointDisplayFormatter.format(dataDecimal);
		}
		else
		{
			return "0";
		}
		
		
	}
	
	public String getLoyalPointDecimalSale(String data)
	{
		try
		{
			if(data!=null)
			{
				
				double dataDecimal = Double.parseDouble(data)/100;
			
				String CURRENCY_DISPLAY_PRICE_FORMAT = "#,###";
				NumberFormat pointDisplayFormatter = new DecimalFormat(CURRENCY_DISPLAY_PRICE_FORMAT);
				
				return pointDisplayFormatter.format(dataDecimal);
			}
			else
			{
				return "0";
			}
		}catch(Exception ex)
		{
			return "0";
		}
		
	}
	
	
	
	public String getLoyalPointSaleInquiryDecimalSale(String data)
	{
		try
		{
			if(data!=null)
			{
				
				double dataDecimal = Double.parseDouble(data)/100;
			
				//Change on 20130114 by Jian
				//String CURRENCY_DISPLAY_PRICE_FORMAT = "#,###.##";
				String CURRENCY_DISPLAY_PRICE_FORMAT = "#,##0.00";
				NumberFormat pointDisplayFormatter = new DecimalFormat(CURRENCY_DISPLAY_PRICE_FORMAT);
				
				return pointDisplayFormatter.format(dataDecimal);
			}
			else
			{
				return "0";
			}
		}catch(Exception ex)
		{
			return "0";
		}
		
	}
	
	
	public String getLoyalPointDecimalSaleWithoutDecimal(String data)
	{
		try
		{
			if(data!=null)
			{
				
				double dataDecimal = Double.parseDouble(data)/100;
			
				String CURRENCY_DISPLAY_PRICE_FORMAT = "#0.00";
				NumberFormat pointDisplayFormatter = new DecimalFormat(CURRENCY_DISPLAY_PRICE_FORMAT);
				
				String finalrs = pointDisplayFormatter.format(dataDecimal);
				
				
				return finalrs.substring(0, finalrs.length()-3);
			}
			else
			{
				return "0";
			}
		}catch(Exception ex)
		{
			return "0";
		}
		
	}
	
	
	
	public String getLoyalDolarDecimal(String data)
	{
		try
		{
			if(data!=null)
			{
				
				double dataDecimal = Double.parseDouble(data);
			
				String CURRENCY_DISPLAY_PRICE_FORMAT = "#0.00";
				NumberFormat pointDisplayFormatter = new DecimalFormat(CURRENCY_DISPLAY_PRICE_FORMAT);
				
				return pointDisplayFormatter.format(dataDecimal);
			}
			else
			{
				return "0.00";
			}
		
		}catch(Exception ex)
		{
			return "0.00";
		}
		
	}
	
	
	
	public String filterGson(String data, String[] maskFieldNames)
	{
		for(String mask: maskFieldNames)
		{
			char b = '"';
			char bx = ']';
			int checkExist = data.indexOf(Character.toString(b)+mask+Character.toString(b)+":");
			int lengthTag = (Character.toString(b)+mask+Character.toString(b)+":").length();
			if(checkExist>0)
			{

				//System.out.println("Index Found "+checkExist);
				String checkCut =  data.substring(checkExist+lengthTag, data.length());
		
				if(mask.equalsIgnoreCase("mPassword"))
				{
					//System.out.println("Cut data msg : "+checkCut.substring(2,indexCut));
					int indexCut = checkCut.indexOf(Character.toString(bx)+",\n");
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(bx)+ data.substring(checkExist+lengthTag+2+indexCut);
					
				}
				else
				{
					int indexCut = checkCut.indexOf(Character.toString(b)+",\n");
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(b)+ data.substring(checkExist+lengthTag+2+indexCut);
				
				}
			}
			
		}
		
		return data;
	}

	
	public Boolean isMobileNoMatch(String inputMobileNo, String dbMobileNo){
		
		String filterInputMobileNo = numberFiltering(inputMobileNo); 
		String filterDbMobileNo = numberFiltering(dbMobileNo);
		if(filterInputMobileNo.equalsIgnoreCase(filterDbMobileNo)){
			return true; 
		}
					
		return false; 
	}
	
	public String numberFiltering(String mobileNo){
		
		String result = ""; 
		String prefix = ""; 
		
		if(mobileNo.startsWith("0")){
			prefix = "0"; 
		}   		
		else if(mobileNo.startsWith("855")){
			prefix = "855"; 
		}
		else if(mobileNo.startsWith("8550")){
			prefix = "8550";
		}

				
		if(!prefix.equalsIgnoreCase("")){
			//String numberCut[] = mobileNo.split(prefix); 
			//result = numberCut[1];
			result = mobileNo.replaceFirst(prefix, ""); 
		}else{
			
			result = mobileNo; 
		}

		return result; 
	}
	
	
	public String getLimitedChac(int stringSize, int startFrom, String txt){
		
		String result = ""; 
		
		if(txt.length() > stringSize){
			result = txt.substring(startFrom,stringSize);
		}else{
			result = txt; 
		}

		return result; 
	}
	
	
public String getAccMaping(String accountType, String accountProductType, String locale){
		
		String result = "";
		String accName = "";
		String currency = ""; 
		String accSubType = "";
		String accOthr = "";
		
		if(locale.equalsIgnoreCase("en")){
						
			//account name identify 
			if(accountProductType.equalsIgnoreCase("S")){
				accName = PExConstant.SAVINGS_ACCOUNT_EN; 

			}else if(accountProductType.equalsIgnoreCase("D")){			
				accName = PExConstant.CURRENT_ACCOUNT_EN; 				
			}
			

			//account type identify
			if(accountType.indexOf(" Indiv")>0 || accountType.indexOf(" INDIVIDUAL")>0 || accountType.indexOf(" Cá nhân")>0){				
				accSubType = PExConstant.INDV_EN; 
				
			}else if(accountType.indexOf(" Staff")>0 || accountType.indexOf(" STAFF")>0 || accountType.indexOf(" Nhân viên")>0){				
				accSubType = PExConstant.STAFF_EN; 
				
			}else if(accountType.indexOf(" Corp")>0 || accountType.indexOf(" Doanh nghiệp")>0){			
				accSubType = PExConstant.CORP_EN;  				
			}
			
			
			//other type identify
			if(accountType.indexOf(" OD")>0){			
				accOthr = PExConstant.OD_EN+" ";  
				
			}else if(accountType.indexOf(" HI SAVER")>0){
				accOthr = PExConstant.HISAVER_EN+" ";
			}
			

		} else{

			//account name identify 
			if(accountProductType.equalsIgnoreCase("S")){
				accName = PExConstant.SAVINGS_ACCOUNT_KH; 

			}else if(accountProductType.equalsIgnoreCase("D")){				
				accName = PExConstant.CURRENT_ACCOUNT_KH; 				
			}
			
						
			//account type identify
			if(accountType.indexOf(" Indiv")>0 || accountType.indexOf(" INDIVIDUAL")>0 || accountType.indexOf(" Cá nhân")>0){				
				accSubType = PExConstant.INDV_KH; 
				
			}else if(accountType.indexOf(" Staff")>0 || accountType.indexOf(" STAFF")>0 || accountType.indexOf(" Nhân viên")>0){				
				accSubType = PExConstant.STAFF_KH; 
				
			}else if(accountType.indexOf(" Corp")>0 || accountType.indexOf(" Doanh nghiệp")>0){				
				accSubType = PExConstant.CORP_KH;  				
			}
			
			
			//other type identify
			if(accountType.indexOf(" OD")>0){			
				accOthr = PExConstant.OD_KH+" ";  
				
			}else if(accountType.indexOf(" HI SAVER")>0){
				accOthr = PExConstant.HISAVER_KH+" ";
			}
			
		}
		
		
		
		
		
		//currency identify
		if(accountType.indexOf(" KHR")>0){			
			currency = MiBConstant.MIB_CURRENCYCODE_CAMBODIA;  
		}else if(accountType.indexOf(" MY")>0){			
			currency = MiBConstant.MIB_CURRENCYCODE_MALAYSIA;  
		}else if(accountType.indexOf(" AUD")>0){
			currency = "AUD"; 
		}else if(accountType.indexOf(" GBP")>0){
			currency = "GBP";
		}else if(accountType.indexOf(" USD")>0){
			currency = MiBConstant.MIB_CURRENCYCODE_USD;
		}else if(accountType.indexOf(" SGD")>0){
			currency = "SGD";
		}else if(accountType.indexOf(" JPY")>0){
			currency = "JPY";
		}else if(accountType.indexOf(" EUR")>0){
			currency = "EUR";
		}
		
		
		result = accName+" "+accOthr+currency+" - "+accSubType; 
		
		return result; 
	}
	
	public static void main(String args[])
	{
		
		StringDataUtil ds = new StringDataUtil();
		//System.out.println(ds.numberFiltering("09123450809112")); 
		String CURRENCY_DISPLAY_PRICE_FORMAT = "#0.00";
		NumberFormat pointDisplayFormatter = new DecimalFormat(CURRENCY_DISPLAY_PRICE_FORMAT);
		String amt = "10";
	
		System.out.println(new BigDecimal(pointDisplayFormatter.format(Double.parseDouble("0000012003")/100)));
		
	}
	
}


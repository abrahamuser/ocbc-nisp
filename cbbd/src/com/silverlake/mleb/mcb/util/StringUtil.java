package com.silverlake.mleb.mcb.util;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.silverlake.mleb.mcb.bean.ObCorporateAccountOverview;
import com.silverlake.mleb.mcb.module.vc.exchangeRate.ExchangeRate;

public class StringUtil {

	public static<T> T nvl(T value, T nvlVal){
		if(value==null) return nvlVal;
		else return value;
	}
	
	public static String rightPadString(String value, int length, String padding){
		return StringUtils.rightPad(value, length, padding);		
	}
	
	public static String leftPadString(String value, int length, String padding){
		return StringUtils.leftPad(value, length, padding);		
	}
	
	public static boolean isEmpty(String in){
		if(in==null){
			return true;}
		else if (in.trim().length()==0){
			return true;}
		else {
			return false;}
		
	}
	
	public static String getSoapPayloadSessId(String payload){
		String result = null;
		
		if(isEmpty(payload)){
			return null;
		}
		
		String [] arr = payload.split(",");
		
		if(arr.length==1){
			result = arr[arr.length-1];
		}else{
			result = arr[arr.length-1];
			result = result.substring(0,result.length()-1);
		}
		return result;
	}
	
	public static String decimalString(String value, int decimal) {
		
		if(null==value || value.isEmpty()  ) {
			value = "0";
		}
		
		String decFomatRegex = "0.";
		for(int i=0; i<decimal; i++)
			decFomatRegex = decFomatRegex + "0";
		
		DecimalFormat df = new DecimalFormat(decFomatRegex);
		String result = df.format(Double.parseDouble(value));
 
		result = result.replace(".", "");
		return result;
	}
	
	public static String StringToDecimalFormat(String value, int decimal) {
		String result = "";
		
		if(value.length()>decimal) {
			result = result + value.substring(0, value.length()-decimal);
			result = result + ".";
			result = result + value.substring(value.length()-decimal, value.length());
		}
		else if(value.length()>decimal) {
			result = "0." + value;
		}
		else {
			while(value.length()<decimal) {
					value = "0" + value;
			}
			result = "0." + value;
		}

		return result;
	}
	
	public static String returnNullForEmpty(String value) {
		if(value != null && !value.isEmpty()) return value;
		else return null;
	}
	
	
	public static void main (String args[])
	{
		
		List<ExchangeRate> list = new ArrayList();
		
		ExchangeRate myr = new ExchangeRate();
		myr.setCcy_code("MYR");
		
		
		ExchangeRate cad = new ExchangeRate();
		cad.setCcy_code("CAD");
		
		ExchangeRate bnd = new ExchangeRate();
		bnd.setCcy_code("BND");
		
		ExchangeRate gbp = new ExchangeRate();
		gbp.setCcy_code("GBP");
		
		ExchangeRate eur = new ExchangeRate();
		eur.setCcy_code("EUR");
		
		ExchangeRate usd = new ExchangeRate();
		usd.setCcy_code("USD");
		
		ExchangeRate al = new ExchangeRate();
		al.setCcy_code("*AL");
		
		list.add(al);
		list.add(myr);
		list.add(cad);
		list.add(bnd);
		list.add(gbp);
		list.add(eur);
		list.add(usd);
		
		 
		Collections.sort(list, compareCCY);
		
		for(ExchangeRate tmp:list)
		{
			System.out.println(tmp.getCcy_code());
		}

	}
	
	static Comparator<ExchangeRate> compareCCY = new Comparator<ExchangeRate>() {
		
 
		
	    @Override
	    public int compare(ExchangeRate o1, ExchangeRate o2) {

	    	List<String> orderCCy = new ArrayList();
	    	//orderCCy.add("*AL");
	    	orderCCy.add("USD");
	    	orderCCy.add("SGD");
	    	orderCCy.add("EUR");
	    	orderCCy.add("GBP");
	    	orderCCy.add("AUD");
	    	orderCCy.add("NZD");
	    	orderCCy.add("JPY");
	    	orderCCy.add("HKD");
	    	orderCCy.add("CHF");
	    	orderCCy.add("CAD");
	    	orderCCy.add("CNH");
	    	
	    	int ccy1 = orderCCy.indexOf(o1.getCcy_code());
	    	ccy1 = ccy1>=0?ccy1:100;
	    	int ccy2 = orderCCy.indexOf(o2.getCcy_code());
	    	ccy2 = ccy2>=0?ccy2:100;

	        return ccy1-ccy2;
	    }
	};
	
	
}

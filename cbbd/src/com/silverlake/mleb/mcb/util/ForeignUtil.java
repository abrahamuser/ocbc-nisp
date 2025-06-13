package com.silverlake.mleb.mcb.util;


import com.silverlake.mleb.mcb.constant.MiBConstant;



public class ForeignUtil
{
	

	private static PropertiesManager prop = new PropertiesManager();
	
	
public static final int checkSameCrossCurrency(String fromCurrencyCode, String toCurrencyCode){

		
		Boolean fromLocal = false; 
		Boolean toLocal = false; 
		Boolean fromForeign = false; 
		Boolean toForeign = false; 
		int result = 0;  
		
		if(fromCurrencyCode == null || fromCurrencyCode.equalsIgnoreCase("") || toCurrencyCode == null || toCurrencyCode.equalsIgnoreCase("")){
			
			result = MiBConstant.CURRENCY_CODE_INVALID; //0
					
		}else{
			
		
				//define currency code whether local / foreign 
				if(fromCurrencyCode.equalsIgnoreCase(MiBConstant.MIB_CURRENCYCODE_IDR)){
					fromLocal = true; 
				}else{
					fromForeign = true; 
				}
				
				if(toCurrencyCode.equalsIgnoreCase(MiBConstant.MIB_CURRENCYCODE_IDR)){
					toLocal = true; 
				}else{
					toForeign = true; 
				}
				
				//check the combination 
				if(fromLocal && toLocal){				
					result = MiBConstant.CURRENCY_CODE_SUCCESS; //3
				}
				else if(fromForeign && toLocal){
					
					result = MiBConstant.CURRENCY_CODE_SUCCESS_X; //4
		
				}
				else if(fromLocal && toForeign){
					result = MiBConstant.CURRENCY_CODE_SUCCESS_X; //4			
				}
				else if(fromForeign && toForeign){
					
					if(fromCurrencyCode.equalsIgnoreCase(toCurrencyCode)){					
						result = MiBConstant.CURRENCY_CODE_SUCCESS; //3
					}else{
						result = MiBConstant.CURRENCY_CODE_FOREIGN_X_FOREIGN; //2  
					}				
				}
			}
			
			
			
						
		
			
		return result;  
	}
	
	
}

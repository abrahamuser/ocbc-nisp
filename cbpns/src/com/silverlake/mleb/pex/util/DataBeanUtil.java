package com.silverlake.mleb.pex.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.mleb.pex.server.socket.bean.HostMbaseMsg;



public class DataBeanUtil
{
	
	
	public DataBeanUtil()
	{
		
	}
	
	public boolean checkLengthData(String data)
	{
		try
		{
			Integer.parseInt(data);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean checkLengthHexData(byte[] data)
	{
		try
		{
			 ByteBuffer.wrap(data).getInt();
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		//Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
		/*DataBeanUtil as = new DataBeanUtil();
		MBaseResponse42 abc = new MBaseResponse42();
		abc.setRspHeader(new MBaseResponseHeader());
		abc.getRspHeader().setRspLength_2_p_0("238");
		abc.setErrorCode_2_p_0("138");
		abc.setApplicationType_1_b_$("M");
		
		int objLen = as.getObjectLength(abc.getRspHeader());
		int objLenx = as.getObjectLength(abc);
		System.out.println(objLen+objLenx);
		byte[] full = as.getFieldNamesAndByte(abc,objLen+objLenx,"CP037");
		System.out.println(new String(full,"CP037"));
		
		
		
		MBaseResponse42 vvv = new MBaseResponse42();
		vvv = (MBaseResponse42) as.setFieldNamesAndByte(vvv, full, "CP037");
		System.out.println("xxx "+ vvv.getRspHeader().getRspLength_2_p_0());
		System.out.println("xxx "+  vvv.getRspHeader().getRspCOD_2_b_$());
		System.out.println("xxx "+  vvv.getErrorCode_2_p_0());
		System.out.println("xxx "+  vvv.getApplicationType_1_b_$());
		
		*/
		BigDecimal sa = new BigDecimal("5.20"); 
		HostMbaseMsg hostMsg = new HostMbaseMsg();
		hostMsg.setField03_10_p_0(sa.toString().replaceAll("\\.", ""));
		System.out.println(hostMsg.getField03_10_p_0());
		  Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			 
			System.out.println(gsonLog.toJson(hostMsg));
	}
	
	
	
	
	public Object setFieldNamesAndValues( Object valueObj,String msgData) throws Exception
	{
		StringDataUtil stringUtil = new StringDataUtil();
		Class c1 = valueObj.getClass();
		String fullMsg = "";
		
		Field[] valueObjFields = c1.getDeclaredFields();
		
		
		for (int i = 0; i < valueObjFields.length; i++)
		{
		
			 String fieldName = valueObjFields[i].getName();

			 
			 String fieldFormat[] = fieldName.split("_");
			 
			 if(fieldFormat.length>3)
			 {
				 
				 int f_lenght = Integer.parseInt(fieldFormat[1]);
			
				 String f_blank_position = fieldFormat[2];
				 String f_blank_value = fieldFormat[3];
				 if(f_blank_value.equalsIgnoreCase("$"))
					 f_blank_value = " ";
				 
				 String valueMsg = null;
				 if(f_blank_position.equalsIgnoreCase("p"))
				 {
					 try
					 {
						 valueMsg = unpk(msgData.substring(0,f_lenght).getBytes());
					 }catch(Exception e){valueMsg = "";}
				 }
				 else
				 {
					 valueMsg = msgData.substring(0,f_lenght);
				 }
				 
				
				 
				 msgData = msgData.substring(f_lenght);
				 valueObjFields[i].setAccessible(true);
			
				 valueObjFields[i].set(valueObj, new String(valueMsg));
			 }
			 else
			 {
				 
				
				 Object newObj = valueObjFields[i].get(valueObj);
				 
				 
				 
				 if(newObj instanceof List)
				 {
				 
					 List listObj = (List) newObj;
					 
					 Object listSubObj = listObj.get(0).getClass().newInstance();
					 int subObjLength = getObjectLength(listSubObj);
					 listObj.clear();
					 while(msgData.length()>=subObjLength)
					 {
						 
						 listObj.add(setFieldNamesAndValues(listSubObj.getClass().newInstance(),msgData));
						 msgData = msgData.substring(subObjLength);
					 }
					 valueObjFields[i].set(valueObj, listObj);
				 
				 }
				 else
				 {
					 Object listSubObj = newObj.getClass().newInstance();
					 int subObjLength = getObjectLength(listSubObj);
					 Object newExtObj = setFieldNamesAndValues(listSubObj.getClass().newInstance(),msgData);
					 msgData = msgData.substring(subObjLength);
					 valueObjFields[i].set(valueObj, newExtObj);
				 }
				
				 
			 }
		 
		}

		return valueObj;
	}
	
	
	
	public int getObjectLength(Object valueObj)throws Exception
	{
		int dataLength = 0;
		Class c1 = valueObj.getClass();
		Field[] valueObjFields = c1.getDeclaredFields();
		
		for (int i = 0; i < valueObjFields.length; i++)
		{
		
			 String fieldName = valueObjFields[i].getName();
			 String fieldFormat[] = fieldName.split("_");
			 if(fieldFormat.length>3)
			 {
				 int f_lenght = Integer.parseInt(fieldFormat[1]);
				 dataLength = dataLength+f_lenght;
				 
			 }

		}
		
		return dataLength;
	}
	
	
	
	public int getFullObjectLength(Object valueObj)throws Exception
	{
		int dataLength = 0;
		Class c1 = valueObj.getClass();
		Field[] valueObjFields = c1.getDeclaredFields();
		
		for (int i = 0; i < valueObjFields.length; i++)
		{
		
			 String fieldName = valueObjFields[i].getName();
			 String fieldFormat[] = fieldName.split("_");
			 valueObjFields[i].setAccessible(true);
				
			 Object newObj = valueObjFields[i].get(valueObj);
			 if(fieldFormat.length>3)
			 {
				 int f_lenght = Integer.parseInt(fieldFormat[1]);
				 dataLength = dataLength+f_lenght;
				 
			 }
			 else
			 {
				 if(newObj instanceof List)
				 {
					 List listObj = (List) newObj;
					 for(int ix=0;ix<listObj.size();ix++)
					 {
						 dataLength = dataLength + getFullObjectLength(listObj.get(ix));
					 }
				 }
				 else
				 {
					 dataLength = dataLength + getFullObjectLength(newObj);
				 }
			 }

		}
		
		return dataLength;
	}
	
	
	
	public String getFieldNamesAndValues( Object valueObj) throws IllegalArgumentException,
    IllegalAccessException
	{
		StringDataUtil stringUtil = new StringDataUtil();
		Class c1 = valueObj.getClass();
		String fullMsg = "";
		
		Field[] valueObjFields = c1.getDeclaredFields();
		
		
		for (int i = 0; i < valueObjFields.length; i++)
		{
		
			 String fieldName = valueObjFields[i].getName();
			
			
			 valueObjFields[i].setAccessible(true);
			
			 Object newObj = valueObjFields[i].get(valueObj);
			
			 //System.out.println(fieldName + " :: " + newObj);
			 
			 String fieldFormat[] = fieldName.split("_");
			 
			 if(fieldFormat.length>3)
			 {
				 int f_lenght = Integer.parseInt(fieldFormat[1]);
			
				 String f_blank_position = fieldFormat[2];
				 String f_blank_value = fieldFormat[3];
				 if(f_blank_value.equalsIgnoreCase("$"))
					 f_blank_value = " ";
				
				 
				 String valueAfterFormat = null==newObj?"":newObj.toString();
				 
				 if(f_blank_position.equalsIgnoreCase("f"))
				 {
					 valueAfterFormat = stringUtil.getFulllength_frontBlank(f_lenght,  valueAfterFormat, f_blank_value);
				 }
				 else if(f_blank_position.equalsIgnoreCase("b"))
				 {
					 valueAfterFormat = stringUtil.getFulllength_backBlank(f_lenght,  valueAfterFormat, f_blank_value);
				 }
				 else if(f_blank_position.equalsIgnoreCase("P"))
				 {
					 valueAfterFormat = stringUtil.getFulllength_frontBlank(f_lenght*2-1,  valueAfterFormat, "0");
					 valueAfterFormat = new String(packx(Long.parseLong(valueAfterFormat.trim()),f_lenght));
					 System.out.println("PACKED VALUE  ["+fieldFormat[0]+"]["+valueAfterFormat+"]: "+toHexString(valueAfterFormat.getBytes()));
				 }
				 
	
				 fullMsg = fullMsg + valueAfterFormat;
			 }
			 else
			 {
				 if(newObj instanceof List)
				 {
					 List listObj = (List) newObj;
					 for(int ix=0;ix<listObj.size();ix++)
					 {
						 fullMsg = fullMsg + getFieldNamesAndValues(listObj.get(ix));
					 }
				 }
				 else
				 {
					 fullMsg = fullMsg + getFieldNamesAndValues(newObj);
				 }
				 
			 }
		 
		}

		return fullMsg;
	}
	
	
	
	
	
	
	
	public Object setFieldNamesAndByte( Object valueObj,byte[] msgData,String msgFormat) throws Exception
	{
		StringDataUtil stringUtil = new StringDataUtil();
		Class c1 = valueObj.getClass();
		String fullMsg = "";
		
		Field[] valueObjFields = c1.getDeclaredFields();
		
		
		for (int i = 0; i < valueObjFields.length; i++)
		{
		
			 String fieldName = valueObjFields[i].getName();

			 
			 String fieldFormat[] = fieldName.split("_");
			 
			 if(fieldFormat.length>3)
			 {
				 
				 int f_lenght = Integer.parseInt(fieldFormat[1]);
			
				 String f_blank_position = fieldFormat[2];
				 String f_blank_value = fieldFormat[3];
				 if(f_blank_value.equalsIgnoreCase("$"))
					 f_blank_value = " ";
				 
				 String valueMsg = null;
				 if(f_blank_position.equalsIgnoreCase("p"))
				 {
					 try
					 {
						 
						byte[] datax =  Arrays.copyOfRange(msgData,0, f_lenght);
						 
						 valueMsg = unpk(datax);
					 }catch(Exception e){valueMsg = "";}
				 }
				 else if(f_blank_position.equalsIgnoreCase("n"))
				 {
					 try
					 {
						 
						byte[] datax =  Arrays.copyOfRange(msgData,0, f_lenght);
						
						 valueMsg = String.valueOf(ByteBuffer.wrap(datax).getInt());
					 }catch(Exception e){valueMsg = "";}
				 }
				 else
				 {
					 byte[] datax =  Arrays.copyOfRange(msgData,0, f_lenght);
					 valueMsg = new String(datax,msgFormat);
				 }
				 
				
				 msgData =  Arrays.copyOfRange(msgData, f_lenght,msgData.length);
		
				 valueObjFields[i].setAccessible(true);
			
				 valueObjFields[i].set(valueObj, new String(valueMsg));
			 }
			 else
			 {
				 
				
				 Object newObj = valueObjFields[i].get(valueObj);
				 
				 
				 
				 if(newObj instanceof List)
				 {
				 
					 List listObj = (List) newObj;
					 
					 Object listSubObj = listObj.get(0).getClass().newInstance();
					 int subObjLength = getFullObjectLength(listSubObj);
					 listObj.clear();
					 while(msgData.length>=subObjLength)
					 {
						 
						 listObj.add(setFieldNamesAndByte(listSubObj.getClass().newInstance(),msgData,msgFormat));
					
						 msgData =  Arrays.copyOfRange(msgData, subObjLength,msgData.length);
					 }
					 valueObjFields[i].set(valueObj, listObj);
				 
				 }
				 else
				 {
					 Object listSubObj = newObj.getClass().newInstance();
					 int subObjLength = getFullObjectLength(listSubObj);
					 
					 Object newExtObj = setFieldNamesAndByte(listSubObj.getClass().newInstance(),msgData,msgFormat);
				
					
					 msgData =  Arrays.copyOfRange(msgData, subObjLength,msgData.length);
					 
					 valueObjFields[i].set(valueObj, newExtObj);
				 }
				
				 
			 }
		 
		}

		return valueObj;
	}
	
	
	public byte[] getFieldNamesAndByte( Object valueObj,int size, String format) throws Exception
	{
		StringDataUtil stringUtil = new StringDataUtil();
		Class c1 = valueObj.getClass();
		byte[] fullMsg = new byte[size] ;
		
		Field[] valueObjFields = c1.getDeclaredFields();
		
		int tallen = 0;
		for (int i = 0; i < valueObjFields.length; i++)
		{
		
			 String fieldName = valueObjFields[i].getName();
			
			
			 valueObjFields[i].setAccessible(true);
			
			 Object newObj = valueObjFields[i].get(valueObj);
			
			 //System.out.println(fieldName + " :: " + newObj);
			 
			 String fieldFormat[] = fieldName.split("_");
			 
			 if(fieldFormat.length>3)
			 {
				 int f_lenght = Integer.parseInt(fieldFormat[1]);
			
				 String f_blank_position = fieldFormat[2];
				 String f_blank_value = fieldFormat[3];
				 if(f_blank_value.equalsIgnoreCase("$"))
					 f_blank_value = " ";
				
				 
				String valueAfterFormatx = null==newObj?"":newObj.toString();
				 
				 if(f_blank_position.equalsIgnoreCase("f"))
				 {
					 String valueAfterFormat = stringUtil.getFulllength_frontBlank(f_lenght,  valueAfterFormatx, f_blank_value);
					 //System.out.println(tallen +" - "+f_lenght );
					 System.arraycopy(valueAfterFormat.getBytes(format), 0, fullMsg, tallen,f_lenght);
				 }
				 else if(f_blank_position.equalsIgnoreCase("b"))
				 {
					 String valueAfterFormat = stringUtil.getFulllength_backBlank(f_lenght,  valueAfterFormatx, f_blank_value);
					 //System.out.println(tallen +" - "+f_lenght );
					 System.arraycopy(valueAfterFormat.getBytes(format), 0, fullMsg, tallen,f_lenght);
				 }
				 else if(f_blank_position.equalsIgnoreCase("P"))
				 {
					 String valueAfterFormat = stringUtil.getFulllength_frontBlank(f_lenght*2-1,  valueAfterFormatx, "0");
					 byte[] packedV = packx(Long.parseLong(valueAfterFormat.trim()),f_lenght);
					 System.arraycopy(packedV, 0, fullMsg,tallen,packedV.length);
					// System.out.println("PACKED VALUE  ["+fieldFormat[0]+"]["+valueAfterFormatx+"]");
					
				 }
				 else if(f_blank_position.equalsIgnoreCase("n"))
				 {
					 
					 if(valueAfterFormatx.trim().length()==0)
					 {
						 valueAfterFormatx = "0";
					 }
					 
					 byte[] packedN =  intTo4ByteArray(Integer.parseInt(valueAfterFormatx));
					  System.arraycopy(packedN, 0, fullMsg,tallen,packedN.length);
					 // System.out.println("4byte integer : "+packedN.length);
			
					
				 }
				 
				 tallen = tallen+ f_lenght;
	
				// fullMsg = fullMsg + valueAfterFormat;
			 }
			 else
			 {
				 if(newObj instanceof List)
				 {
					 List listObj = (List) newObj;
					 for(int ix=0;ix<listObj.size();ix++)
					 {
						 int subLen = getFullObjectLength(listObj.get(ix));
						 byte[] datax =  getFieldNamesAndByte(listObj.get(ix),subLen,format);
						 System.arraycopy(datax, 0, fullMsg, tallen,datax.length);
						 tallen = tallen+ datax.length;
						
					 }
				 }
				 else
				 {
					 int subLen = getFullObjectLength(newObj);
					 byte[] datax = getFieldNamesAndByte(newObj,subLen,format);
					
					 System.arraycopy(datax, 0, fullMsg, tallen,datax.length);
					 tallen = tallen+ datax.length;
					
					
				 }
				 
			 }
		 
		}

		return fullMsg;
	}
	
	
	
	
	
    public byte[] packx(long value, int length) {
        if (length < 1 || length > 16)
            throw new IllegalArgumentException("invalid length");
        BigInteger max = BigInteger.TEN.pow(length * 2 - 1);
        BigInteger abs = BigInteger.valueOf(value).abs();
        if (max.compareTo(abs) < 0) {
            throw new IllegalArgumentException("decimal overflow");
        }
        BigInteger workValue = abs.mod(max);
        byte[] bytes = new BigInteger(workValue.toString()
             + (value < 0 ? "D" : "F"), 16).toByteArray();
        byte[] rv = new byte[length];
        System.arraycopy(bytes, 0, rv, rv.length - bytes.length, bytes.length);
        return rv;
       }
    private BigInteger hundred = BigInteger.valueOf(100);
    public  String unpk(byte[] bytes) {
        BigInteger value = BigInteger.ZERO;
        for (int index = 0; index < bytes.length; index++) {
            int high = (bytes[index] & 0x000000ff) >> 4;
            if (high > 9) {
             throw new RuntimeException("0C7 Invalid data");
            }
            int low = bytes[index] & 0x0000000f;
            if (index < bytes.length - 1) {
             if (low > 9) {
                 throw new RuntimeException("0C7 Invalid data");
             }
             high = 10 * high + low;
            }
            value = value.add(BigInteger.valueOf(high));
            if (index < bytes.length - 2) {
             value = value.multiply(hundred);
            } else if (index == bytes.length - 2) {
             value = value.multiply(BigInteger.TEN);
            } else {
             if (low < 10) {
                 throw new RuntimeException("0C7 Invalid data");
             }
             // Positive: A C E F
             // Negative: B D
             if (low == 11 || low == 13) {
                 value = value.negate();
             }
            }
        }
        return value.toString();
       }
	
    
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
	public String toHexString(byte[] bytes) {
	    char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    char[] hexChars = new char[bytes.length * 2];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j*2] = hexArray[v/16];
	        hexChars[j*2 + 1] = hexArray[v%16];
	    }
	    return new String(hexChars);
	}
	
	
	public  byte[] intTo4ByteArray(int value) {
	    return new byte[] {
	            (byte)(value >>> 24),
	            (byte)(value >>> 16),
	            (byte)(value >>> 8),
	            (byte)value};
	}
	
}

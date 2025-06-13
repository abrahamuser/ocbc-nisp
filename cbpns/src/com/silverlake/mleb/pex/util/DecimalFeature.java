package com.silverlake.mleb.pex.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;


public class DecimalFeature {

    private static BigInteger hundred = BigInteger.valueOf(100);

    
    public static String bytesToStringUTFNIO(byte[] bytes) {
    	
    	 CharBuffer cBuffer = ByteBuffer.wrap(bytes).asCharBuffer();
    	
    	 return cBuffer.toString();
    	
    	}

    
    public static void main(String[] args) {
     try {
    	 byte[] packk = packx(Long.parseLong("28".trim()), 10);
    	 
    	 System.out.println(toHexString(packk));
    	 System.out.println(toHexString(bytesToStringUTFNIO(packk).getBytes()));
    	 
    	 
    	 
         print(packk);
         print(pack("-1234567890", 8));
         print(pack(123456789012345L, 8));
       //  print(pack(1234567890123456L, 8)); // <=== decimal overflow
         
         
         
         String ax = "29835982983";
         
         byte[] axx = ax.getBytes();
         
         String data = new String (axx,"CP037");
         
          String acc = new String(packx(Long.parseLong((ax)),10));
         byte[] accx =  acc.getBytes();
         
          
          
       
    
         
         System.out.println(new String(new String("000000000f".getBytes("cp037")).getBytes()));
         
         
     } catch (Exception e) {
         e.printStackTrace(System.out);
     }
     
     
     
     
     
    }

    public static byte[] pack(BigInteger value, int length) {
     BigInteger workValue = value.abs();
     byte[] bytes = new byte[length];
     int index = length - 1;
     bytes[index] = (byte) (value.compareTo(BigInteger.ZERO) < 0 ? 13 : 12);
     boolean left = true;
     while (!workValue.equals(BigInteger.ZERO)) {
         if (index < 0) {
          throw new RuntimeException("Decimal overflow: " + value
               + " does not fit in " + length + " bytes");
         }
         int digit = workValue.mod(BigInteger.TEN).intValue();
         workValue = workValue.divide(BigInteger.TEN);
         if (left) {
          digit = digit << 4;
          bytes[index--] |= (byte) digit;
          left = false;
         } else {
          bytes[index] = (byte) digit;
          left = true;
         }
     }
     return bytes;
    }

    public static byte[] pack(long value, int length) {
     return pack(BigInteger.valueOf(value), length);
    }

    public static byte[] pack(String value, int length) {
     return pack(new BigInteger(value), length);
    }

    public static String unpk(byte[] bytes) {
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

    
    public static byte[] packx(long value, int length) {
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
    
    
    private static void print(byte[] bytes) throws Exception {
     StringBuilder sb = new StringBuilder();
     for (byte b : bytes) {
         String hex = "0" + Integer.toHexString(b);
         if (hex.length() > 2) {
          hex = hex.substring(hex.length() - 2);
         }
       
         sb.append(hex);
     }
     System.out.println(sb.toString().toUpperCase() + "\t" + unpk(bytes) + " -- "+bytes.length);
     //CP037
    
    
    }

	public static String toHexString(byte[] bytes) {
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
  

}
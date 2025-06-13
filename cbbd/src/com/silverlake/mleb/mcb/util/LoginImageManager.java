package com.silverlake.mleb.mcb.util;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import net.sf.ehcache.Element;





public class LoginImageManager
{
	private static Properties properties ;
	private static final String IMAGE_FOLDER = "loginImage";
	
	private static final String PROMOTION_FOLDER = "promotionImage";
	
	public LoginImageManager()
	{
	

	}
	
	
	
	
	public byte[]  getLoginImage(String imageID) throws IOException
	{
		BufferedImage originalImage = null;
			try
			{
				imageID = null==imageID?"0000":imageID;
				String serverPath = "";
				
					String appPropertyPath = System.getProperty(PropertiesManager.PROPERTIES_SYSTEM_PATH_NAME);
					//System.out.println("app_properties_path :: "+appPropertyPath);
					if(appPropertyPath != null && !appPropertyPath.equals(""))
					{
						serverPath = appPropertyPath;
					}
					
					
					
					originalImage = ImageIO.read(new File(serverPath+"/"+IMAGE_FOLDER+"/"+imageID+".jpg"));
				
				
			}catch(Exception e)
			{
				
			}
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( originalImage, "jpg", baos );
			baos.flush();
			byte[] imageData = baos.toByteArray();
			baos.close();
			
			//byte[] imageData =  ((DataBufferByte) originalImage.getData().getDataBuffer()).getData();
			return imageData;
	}
	
	
	
	public List<String>  getAllLoginImage() throws Exception
	{
		String cacheID = "CACHEIMAGEID";
		 List<String> imageIds = new ArrayList();
		 EhcacheSession ecache = new EhcacheSession();
		 Object dataImageID = ecache.getImageIDCache().get(cacheID);
		 File directory = null;
		 
		 if(null == dataImageID)
		 {
		 
					
					String serverPath = "";
					
						String appPropertyPath = System.getProperty("app.store.path");
						//System.out.println("app_properties_path :: "+appPropertyPath);
						if(appPropertyPath != null && !appPropertyPath.equals(""))
						{
							serverPath = appPropertyPath;
						}
						
						
						directory = new File(serverPath+"/"+IMAGE_FOLDER);
					
					
					
					
					for (File temp : directory.listFiles())
					{
						String imageName = temp.getName().split("\\.")[0];
						
						if(imageName.matches("\\d{4}"))
						{
						
							imageIds.add(temp.getName().split("\\.")[0]);
						}
					}
				
					 Element ex = new Element(cacheID,imageIds);
					 ecache.getImageIDCache().put(ex);
					
			
		 }
		 else
		 {
			 Element ex = (Element) dataImageID;
			 imageIds = (List<String>) ex.getObjectValue();
			 
		 }
			
		 return imageIds;
		
	}
	
	
	
	public List<String>  getRandomRegImage() throws Exception
	{
		 int totalRegImage = 10;
		 List<String> regImage = new ArrayList();
		 List<String> allimage = getAllLoginImage();
		 int size = allimage.size();
		 Random rnd = new Random();
		
		 
		 
		 Hashtable hashData = new Hashtable();
		 
		 while(hashData.size()<totalRegImage)
		 {
			 int x = rnd.nextInt(size);
			 
			 if(!hashData.containsKey(String.valueOf(x)))
			 {
				 hashData.put(String.valueOf(x),allimage.get(x));
			 }
		 }
		 
		 Set<String> keys = hashData.keySet();
         for(String key: keys){
        	 regImage.add(hashData.get(key).toString());
         }
		
			
		 return regImage;
		
	}
	
	
	
	public String getImageID(int a)
	{
		String x = String.valueOf(a);
		while(x.length()<4)
		{
			x = "0"+x;
		}
		
		return x;
	}
	
	
	
	
	public byte[]  getPromotionImage(String imageID) throws IOException
	{
		BufferedImage originalImage = null;
			try
			{
				
				String serverPath = System.getProperty("catalina.base");
				if(null!=serverPath && serverPath.length()>0)
				{

					originalImage = ImageIO.read(new File(serverPath+"/"+PROMOTION_FOLDER+"/"+imageID));
				}
				else
				{
					serverPath = System.getProperty("server.root");
					originalImage = ImageIO.read(new File(serverPath+"/"+PROMOTION_FOLDER+"/"+imageID));
				}
				
			}catch(Exception e)
			{
				
			}
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( originalImage, "jpg", baos );
			baos.flush();
			byte[] imageData = baos.toByteArray();
			baos.close();
			
			//byte[] imageData =  ((DataBufferByte) originalImage.getData().getDataBuffer()).getData();
			return imageData;
	}
	
	
	
	public static void main(String args[]) throws Exception
	{
		LoginImageManager lm = new LoginImageManager();
		 List<String> list = lm.getAllLoginImage();
		 List<String> list2 = lm.getAllLoginImage();
		 for(int i=1;i<=200;i++)
		 {
			 list.add(lm.getImageID(i));
		 }
		 
		 
		 //System.out.println(list);
		 //System.out.println(list2);
		
		   //System.out.println(list);
		   String loginImageUuid = null;
	       int imageCount = list.size();
	       int sum = 0;
	       for (char c : "mobility03xxx".toUpperCase().toCharArray())
	    	   sum += c;
	       
	       
	       loginImageUuid = list.get(sum % imageCount);
	       //System.out.println(""+loginImageUuid);
	}
}
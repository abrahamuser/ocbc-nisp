package com.silverlake.mleb.mcb.task;
//package com.silverlake.mleb.mib.task;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.silverlake.mleb.mib.constant.MiBConstant;
//import com.silverlake.mleb.mib.module.func.TTService;
//import com.silverlake.mleb.mib.module.ib.bean.IBRequest;
//import com.silverlake.mleb.mib.module.ib.bean.IBResponse;
//import com.silverlake.mleb.mib.module.ib.bean.ResponseObject;
//import com.silverlake.mleb.mib.module.ib.bean.Parameters;
//import com.silverlake.mleb.mib.module.ib.ppform.bean.FundTransferForm;
//import com.silverlake.mleb.mib.module.ib.ttApi.TelegraphicTransferService;
//import com.silverlake.mleb.mib.module.ib.ttbean.ListBankSwift;
//import com.silverlake.mleb.mib.module.ib.ttbean.ListCity;
//import com.silverlake.mleb.mib.module.ib.ttbean.ListCountry;
//import com.silverlake.mleb.mib.module.ib.ttbean.TransactionTT;
//import com.silverlake.mleb.mib.util.GsonUtil;
//
//@Service
//public class RefreshTelegraphicTransferBankInfo
//{
//	private static Logger log = LogManager.getLogger(RefreshTelegraphicTransferBankInfo.class);
//	
//	@Autowired ApplicationContext appContext;
//	
//	private List<ListCountry> bankCountryList = null;
//	private List<ListCity> bankCityListForAllCountries = null;
//	
//	private boolean hasError = false;
//	private int bankCityErrorCount = 0;
//	private int bankListErrorCount = 0;
//	@Scheduled(cron="0 10 19,23 * * ?")
//	public void process()
//	{
//		try{
//			
//			log.info("----- START RefreshTelegraphicTransferBankInfo");
//			
//			bankCountryList = null;
//			
//			hasError = false;
//			bankCityErrorCount = 0;
//			bankListErrorCount = 0;
//			
//            TelegraphicTransferService telegraphicTransferService = new TelegraphicTransferService(appContext);
//            TTService ttService = (TTService) appContext.getBean("ttService");
//            
//			IBRequest ibRequest = new IBRequest();
//			
//			// process request for omni 25003
//			log.info("----- Calling OMNI function ID 25003");
//			
//			processRequest25003(ibRequest);
//			IBResponse ibResponse25003 = telegraphicTransferService.loadListCountry25003(ibRequest, "", "");
//			if(ibResponse25003!=null && ibResponse25003.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
//			{
//				ResponseObject responseObject = (ResponseObject) ibResponse25003.getResponseObject();
//				if(responseObject!=null &&
//				   responseObject.getFundTransferForm()!=null &&
//				   responseObject.getFundTransferForm().getTransactionTT()!=null &&
//				   responseObject.getFundTransferForm().getTransactionTT().getListCountry()!=null &&
//				   !responseObject.getFundTransferForm().getTransactionTT().getListCountry().isEmpty())
//				{
//					List<ListCountry> listCountryList = new ArrayList<ListCountry>();
//					for(ListCountry listCountry : responseObject.getFundTransferForm().getTransactionTT().getListCountry())
//					{
//						if(listCountry.getCountryCode()!=null && 
//						   listCountry.getCountryName()!=null && 
//						   listCountry.getCountryName_ID()!=null)
//						{
//							listCountry.setCountryCode(listCountry.getCountryCode().trim());
//							listCountry.setCountryName(listCountry.getCountryName().trim());
//							listCountry.setCountryName_ID(listCountry.getCountryName_ID().trim());
//							
//							listCountryList.add(listCountry);
//						}
//						else
//						{
//							log.info("ERROR ==> "+GsonUtil.getPrettyPrintingGson().toJson(listCountry));
//							listCountry=null;
//						}
//					}
//					
//					log.info("----- RefreshTelegraphicTransferBankInfo::BankCountyList-Deleting & Saving new records");
//					ttService.removeBankCountry();
//					ttService.saveBankCountryList(listCountryList);
//				}
//				else
//				{
//					hasError = true;
//				}
//			}
//			else
//			{
//				hasError = true;
//			}
//			
//			if(hasError==false && bankCountryList!=null && !bankCountryList.isEmpty())
//			{
//				// process request for omni 25004
//				for(ListCountry listCountry : bankCountryList)
//				{
//					bankCityListForAllCountries = new ArrayList<ListCity>();
//					
//					log.info("----- Calling OMNI function ID 25004 for countryCode="+listCountry.getCountryCode());
//					processRequest25004(ibRequest, listCountry);
//					
//					IBResponse ibResponse25004 = telegraphicTransferService.loadListCity25004(ibRequest, "", "");
//					if(ibResponse25004!=null && ibResponse25004.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
//					{
//						ResponseObject responseObject = (ResponseObject) ibResponse25004.getResponseObject();
//						if(responseObject!=null &&
//						   responseObject.getFundTransferForm()!=null &&
//						   responseObject.getFundTransferForm().getTransactionTT()!=null &&
//						   responseObject.getFundTransferForm().getTransactionTT().getListCity()!=null &&
//						   !responseObject.getFundTransferForm().getTransactionTT().getListCity().isEmpty())
//						{
//							List<ListCity> bankCityList = responseObject.getFundTransferForm().getTransactionTT().getListCity();
//							if(bankCityList!=null && !bankCityList.isEmpty())
//							{
//								List<ListCity> bankCityListForSpecificCountryCode = new ArrayList<ListCity>();
//								for(ListCity listCity : bankCityList)
//								{
//									if(listCity.getCityId()!=null && listCity.getCityName()!=null && listCity.getCityName_ID()!=null)
//									{
//										listCity.setCityId(listCity.getCityId().trim());
//										listCity.setCityName(listCity.getCityName().trim());
//										listCity.setCityName_ID(listCity.getCityName_ID().trim());
//										listCity.setCountryCode(listCountry.getCountryCode());
//										listCity.setCountryName(listCountry.getCountryName());
//										listCity.setCountryName_ID(listCountry.getCountryName_ID());
//										
//										bankCityListForSpecificCountryCode.add(listCity);
//										bankCityListForAllCountries.add(listCity);
//									}
//									else
//									{
//										log.info("ERROR ==> "+GsonUtil.getPrettyPrintingGson().toJson(listCity));
//									}
//								}
//								
//								ttService.removeBankCity(listCountry.getCountryCode());
//								ttService.saveBankCityList(bankCityListForSpecificCountryCode);
//							}
//						}
//						else
//						{
//							bankCityErrorCount++;
//						}
//					}
//					else
//					{
//						hasError = true;
//						bankCityErrorCount++;
//					}
//				}
//			}
//			
//			if(hasError==true && bankCityListForAllCountries!=null && !bankCityListForAllCountries.isEmpty())
//			{
//				// process request for omni 25005
//				for(ListCity listCity : bankCityListForAllCountries)
//				{	
//					log.info("----- Calling OMNI function ID 25005 for countryCode="+listCity.getCountryCode() + " and cityName="+listCity.getCityName());
//					processRequest25005(ibRequest, listCity);
//					
//					IBResponse ibResponse25005 = telegraphicTransferService.loadListBeneficaryBankSwift25005(ibRequest, "", "");
//					if(ibResponse25005!=null && ibResponse25005.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
//					{
//						ResponseObject responseObject = (ResponseObject) ibResponse25005.getResponseObject();
//						if(responseObject!=null &&
//						   responseObject.getFundTransferForm()!=null &&
//						   responseObject.getFundTransferForm().getTransactionTT()!=null &&
//						   responseObject.getFundTransferForm().getTransactionTT().getListBankSwift()!=null &&
//						   !responseObject.getFundTransferForm().getTransactionTT().getListBankSwift().isEmpty())
//						{
//							List<ListBankSwift> bankSwiftList = responseObject.getFundTransferForm().getTransactionTT().getListBankSwift();
//							if(bankSwiftList!=null && !bankSwiftList.isEmpty())
//							{
//								List<ListBankSwift> listBankSwift = new ArrayList<ListBankSwift>();
//								for(ListBankSwift bankSwift : bankSwiftList)
//								{
//									if(bankSwift.getBankId()!=null && bankSwift.getBankName()!=null)
//									{
//										bankSwift.setBankId(bankSwift.getBankId().trim());
//										bankSwift.setBankName(bankSwift.getBankName().trim());
//										bankSwift.setCountryCode(listCity.getCountryCode());
//										bankSwift.setCityId(listCity.getCityId());
//										bankSwift.setCountryName(listCity.getCountryName());
//										bankSwift.setCountryName_ID(listCity.getCountryName_ID());
//										bankSwift.setCityCode(listCity.getCityName());
//										bankSwift.setCityName(listCity.getCityName());
//										bankSwift.setCityName_ID(listCity.getCityName_ID());
//										
//										listBankSwift.add(bankSwift);
//									}
//									else
//									{
//										log.info("ERROR ==> "+GsonUtil.getPrettyPrintingGson().toJson(bankSwift));
//									}
//								}
//								
//								ttService.removeBankList(listCity.getCountryCode(), listCity.getCityId());
//								ttService.saveBankSwiftList(listBankSwift);
//							}
//						}
//						else
//						{
//							bankListErrorCount++;
//						}
//					}
//					else
//					{
//						hasError = true;
//						bankListErrorCount++;
//					}
//				}
//			}
//			
//			log.info(printBox(""
//	        		+ "\nbankCityErrorCount : "+bankCityErrorCount
//	        		+ "\nbankListErrorCount : "+bankListErrorCount
//					));
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		log.info("----- END RefreshTelegraphicTransferBankInfo");
//	}
//	
//	private void processRequest25003(IBRequest ibRequest){
//		Parameters parameters = new Parameters();
//		parameters.setLang(MiBConstant.LOCALE_EN.toUpperCase());
//		parameters.setCif("0000000000000000000");
//		parameters.setUserId("000000000000");
//		parameters.setChannel(MiBConstant.CHANNEL_MB);
//		parameters.setOperationCode(MiBConstant.GENERAL_OPERATIONCODE);
//		ibRequest.setParameters(parameters);
//	}
//	
//	private void processRequest25004(IBRequest ibRequest, ListCountry listCountry){
//		Parameters parameters = new Parameters();
//		parameters.setLang(MiBConstant.LOCALE_EN.toUpperCase());
//		parameters.setCif("0000000000000000000");
//		parameters.setUserId("000000000000");
//		parameters.setChannel(MiBConstant.CHANNEL_MB);
//		parameters.setOperationCode(MiBConstant.GENERAL_OPERATIONCODE);
//		parameters.setFundTransferForm(new FundTransferForm());
//		parameters.getFundTransferForm().setTransactionTT(new TransactionTT());
//		parameters.getFundTransferForm().getTransactionTT().setListCountry(new ArrayList<ListCountry>());
//		ListCountry bean = new ListCountry();
//		bean.setCountryCode(listCountry.getCountryCode());
//		parameters.getFundTransferForm().getTransactionTT().getListCountry().add(bean);
//		
//		ibRequest.setParameters(parameters);
//	}
//	
//	private void processRequest25005(IBRequest ibRequest, ListCity listCity){
//		Parameters parameters = new Parameters();
//		parameters.setLang(MiBConstant.LOCALE_EN.toUpperCase());
//		parameters.setCif("0000000000000000000");
//		parameters.setUserId("000000000000");
//		parameters.setChannel(MiBConstant.CHANNEL_MB);
//		parameters.setOperationCode(MiBConstant.GENERAL_OPERATIONCODE);
//		parameters.setFundTransferForm(new FundTransferForm());
//		parameters.getFundTransferForm().setTransactionTT(new TransactionTT());
//		parameters.getFundTransferForm().getTransactionTT().setListCountry(new ArrayList<ListCountry>());
//		parameters.getFundTransferForm().getTransactionTT().setListCity(new ArrayList<ListCity>());
//		ListCountry countryBean = new ListCountry();
//		countryBean.setCountryCode(listCity.getCountryCode());
//		parameters.getFundTransferForm().getTransactionTT().getListCountry().add(countryBean);
//		ListCity cityBean = new ListCity();
//		cityBean.setCityName(listCity.getCityName());
//		parameters.getFundTransferForm().getTransactionTT().getListCity().add(cityBean);
//		
//		ibRequest.setParameters(parameters);
//	}
//	
//	private String printBox(String data)
//	{
//		String key= "*";
//		int lengthx = 0;
//		String printMsg = "\n";
//		
//		String msgs[] = data.split("\n");
//		
//		for(String msg:msgs)
//		{
//			if(msg.length()>lengthx)
//			{
//				lengthx = msg.length();
//			}
//		}
//		
//		printMsg = printMsg+printLine(key,lengthx+4);
//		for(String msg:msgs)
//		{
//			int remainSpace = lengthx-msg.length();
//			printMsg = printMsg+ "\n"+"* "+msg+printLine(" ",remainSpace)+" *";
//		}
//		printMsg = printMsg+"\n"+printLine(key,lengthx+4);
//		
//		return printMsg;
//	}
//	
//	
//	private String printLine(String key, int length)
//	{
//		
//		String line = "";
//		for(int i=0;i<length;i++)
//		{
//			line = line+key;
//		}
//		
//		
//		return line;
//	}
//}
//

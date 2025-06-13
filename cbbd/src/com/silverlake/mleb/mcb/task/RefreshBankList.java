package com.silverlake.mleb.mcb.task;
//package com.silverlake.mleb.mib.task;
//
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.silverlake.mleb.mib.module.ib.ftbean.Parameters;
//import com.silverlake.mleb.mib.bean.FTBankListBean;
//import com.silverlake.mleb.mib.constant.MiBConstant;
//import com.silverlake.mleb.mib.entity.FTBankList;
//import com.silverlake.mleb.mib.entity.JobStatusDetail;
//import com.silverlake.mleb.mib.entity.dao.FundTransferDao;
//import com.silverlake.mleb.mib.entity.dao.JobStatusDetailDAO;
//import com.silverlake.mleb.mib.module.ib.bean.IBRequest;
//import com.silverlake.mleb.mib.module.ib.bean.IBResponse;
//import com.silverlake.mleb.mib.module.ib.ftApi.FTService;
//import com.silverlake.mleb.mib.util.DateUtil;
//
//@Service
//public class RefreshBankList
//{
//	private static Logger log = LogManager.getLogger(RefreshBankList.class);
//
//
//	@Autowired ApplicationContext appContext;
//
//	@Autowired JobStatusDetailDAO jobStatusDetailDAO;
//	
//	@Autowired FundTransferDao fundTransferDao;
//	
////	@Scheduled(cron="0 0/5 * * * ?")
//	@Scheduled(cron="0 0 4,5,6 * * ?")
//	public void process()
//	{
//		try{							
//
//			log.info("----- START RefreshBankList");
//			
//			// If date is null or == today, skip the following process.
//			Date today = new Date();
//			Date existingUpdateDate = null;
//			JobStatusDetail jobStatusDetail = jobStatusDetailDAO.findByJobName(MiBConstant.SCHEDULED_JOB_BANK_LIST);
//			if(jobStatusDetail!=null)
//				existingUpdateDate = jobStatusDetail.getUpdateDate();
//			
//			if(existingUpdateDate==null || (existingUpdateDate!=null && today.after(existingUpdateDate) && !DateUtil.isToday(existingUpdateDate)))
//			{
//				// Retrieve Bank List for ALL 3 transferService: OLT, LLG & RTGS
//				IBRequest ibRequest = new IBRequest();
//				
//				
//				FTService ftService = new FTService(appContext);
//				
//				processRequest(ibRequest, MiBConstant.TRANS_SERVICE_TYPE_OLT);
//				IBResponse ibResponseOLT = ftService.loadBankOfInterbank(ibRequest, "", "");
//				
//				processRequest(ibRequest, MiBConstant.TRANS_SERVICE_TYPE_LLG);
//				IBResponse ibResponseLLG = ftService.loadBankOfInterbank(ibRequest, "", "");
//				
//				processRequest(ibRequest, MiBConstant.TRANS_SERVICE_TYPE_RTGS);
//				IBResponse ibResponseRTGS = ftService.loadBankOfInterbank(ibRequest, "", "");
//				
//				// If ALL 3 transferService: OLT, LLG & RTGS are successful
//				if(ibResponseOLT!=null && ibResponseOLT.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS) && ibResponseOLT.getResponseObject()!=null &&
//				   ibResponseLLG!=null && ibResponseLLG.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS) && ibResponseLLG.getResponseObject()!=null &&
//				   ibResponseRTGS!=null && ibResponseRTGS.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS) && ibResponseRTGS.getResponseObject()!=null)
//				{
//					// delete all records from mleb_ocbc.ft_bank_list
//					int nResult = fundTransferDao.removeBankList();
//					log.info("nResult="+nResult);
//
//					// prepare the bean list that going to insert into mleb_ocbc.ft_bank_list
//					List<FTBankListBean> bankListBeanListOLT = new ArrayList<FTBankListBean>();
//					Map<String, String> bankMapOLT = (Map<String, String>) ibResponseOLT.getResponseObject();
//					for(Map.Entry<String, String> entry : bankMapOLT.entrySet())
//					{
//						String transferService = MiBConstant.TRANS_SERVICE_TYPE_OLT;
//						if(entry.getValue().equalsIgnoreCase(MiBConstant.OCBC_BANKNAME) || entry.getKey().equals("999"))
//							transferService = MiBConstant.TRANS_SERVICE_TYPE_IFT;
//						
//						FTBankList entity = fundTransferDao.getFTBankList(entry.getKey(), entry.getValue(), transferService);
//						if(entity==null)
//						{
//							entity = new FTBankList();
//							
//							entity.setTransferService(MiBConstant.TRANS_SERVICE_TYPE_OLT);
//							entity.setBankCode(entry.getKey());
//							entity.setBankName(entry.getValue());
//							
//							// hard code OCBCNISP transfer service type to IFT
//							if(entry.getValue().equalsIgnoreCase(MiBConstant.OCBC_BANKNAME) || entry.getKey().equals("999"))
//								entity.setTransferService(MiBConstant.TRANS_SERVICE_TYPE_IFT);
//							
//							entity.setBankId(entity.getTransferService()+entry.getKey());
//							
//							fundTransferDao.insertEntity(entity);
//						}
//					}
//					
//					List<FTBankListBean> bankListBeanListLLG = new ArrayList<FTBankListBean>();
//					Map<String, String> bankMapLLG = (Map<String, String>) ibResponseLLG.getResponseObject();
//					for(Map.Entry<String, String> entry : bankMapLLG.entrySet())
//					{
//						FTBankList entity = fundTransferDao.getFTBankList(entry.getKey(), entry.getValue(), MiBConstant.TRANS_SERVICE_TYPE_LLG);
//						if(entity==null)
//						{
//							entity = new FTBankList();
//							entity.setTransferService(MiBConstant.TRANS_SERVICE_TYPE_LLG);
//							entity.setBankCode(entry.getKey());
//							entity.setBankName(entry.getValue());
//							entity.setBankId(entity.getTransferService()+entry.getKey());
//							
//							fundTransferDao.insertEntity(entity);
//						}
//					}
//					
//					List<FTBankListBean> bankListBeanListRTGS = new ArrayList<FTBankListBean>();
//					Map<String, String> bankMapRTGS = (Map<String, String>) ibResponseRTGS.getResponseObject();
//					for(Map.Entry<String, String> entry : bankMapRTGS.entrySet())
//					{
//						FTBankList entity = fundTransferDao.getFTBankList(entry.getKey(), entry.getValue(), MiBConstant.TRANS_SERVICE_TYPE_RTGS);
//						if(entity==null)
//						{
//							entity = new FTBankList();
//							entity.setTransferService(MiBConstant.TRANS_SERVICE_TYPE_RTGS);
//							entity.setBankCode(entry.getKey());
//							entity.setBankName(entry.getValue());
//							entity.setBankId(entity.getTransferService()+entry.getKey());
//							fundTransferDao.insertEntity(entity);
//						}
//					}
//					
//					// update the updateDate in mleb_ocbc.job_status_detail
//					jobStatusDetailDAO.updateUpdateDate(MiBConstant.SCHEDULED_JOB_BANK_LIST);
//				}
//			}
//			
//			log.info("----- END RefreshBankList");
//				
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}
//	
//	private void processRequest(IBRequest request, String transferService)
//	{
//		Parameters parameters = new Parameters();
//		parameters.setTransferService(transferService);
//		parameters.setLang(MiBConstant.LOCALE_EN);
//		parameters.setChannel(MiBConstant.CHANNEL_MB);
//		parameters.setOperationCode(MiBConstant.GENERAL_OPERATIONCODE);
//		
//		request.setParameters(parameters);
//	}
//}
//

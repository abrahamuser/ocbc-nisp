package com.silverlake.mleb.mcb.task;
//package com.silverlake.mleb.mib.task;
//
//
//import java.util.Date;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.silverlake.mleb.mib.constant.MiBConstant;
//import com.silverlake.mleb.mib.entity.JobStatusDetail;
//import com.silverlake.mleb.mib.entity.TransferLimit;
//import com.silverlake.mleb.mib.entity.dao.JobStatusDetailDAO;
//import com.silverlake.mleb.mib.entity.dao.TransferLimitDAO;
//import com.silverlake.mleb.mib.module.ib.bean.IBRequest;
//import com.silverlake.mleb.mib.module.ib.bean.IBResponse;
//import com.silverlake.mleb.mib.module.ib.ftApi.FTService;
//import com.silverlake.mleb.mib.module.ib.ftbean.LoadTrxLimit;
//import com.silverlake.mleb.mib.module.ib.ftbean.Parameters;
//import com.silverlake.mleb.mib.module.ib.ftbean.ResponseObject;
//import com.silverlake.mleb.mib.util.DateUtil;
//
//@Service
//public class RefreshFundTransferTransferLimit {
//	
//	private static Logger log = LogManager.getLogger(RefreshFundTransferTransferLimit.class);
//
//	@Autowired ApplicationContext appContext;
//
//	@Autowired JobStatusDetailDAO jobStatusDetailDAO;
//	
//	@Autowired TransferLimitDAO transferLimitDAO;
//
////	@Scheduled(cron="0 0/5 * * * ?")
//	@Scheduled(cron="0 0 4,5,6 * * ?")
//	public void process()
//	{
//		try{		
//			
//			log.info("----- START RefreshFundTransferTransferLimit");
//			
//			// Check the updateDate in mleb_ocbc.job_status_detail where job_name='FTTL'. 
//			// If date is null or == today, skip the following process.
//			Date today = new Date();
//			Date existingUpdateDate = null;
//			JobStatusDetail jobStatusDetail = jobStatusDetailDAO.findByJobName(MiBConstant.SCHEDULED_JOB_TRXN_LIMIT);
//			if(jobStatusDetail!=null)
//				existingUpdateDate = jobStatusDetail.getUpdateDate();
//			
//			if(existingUpdateDate==null || (existingUpdateDate!=null && today.after(existingUpdateDate) && !DateUtil.isToday(existingUpdateDate)))
//			{
//				FTService ftService = new FTService(appContext);
//				
//				IBRequest trxLimitRequest = new IBRequest();
//				IBResponse trxLimitResponse = new IBResponse();
//				
//				processRequest(trxLimitRequest);
//				// Retrieve Transaction Limit
//				trxLimitResponse = ftService.retrieveTrxLimit(trxLimitRequest, "", "");
//				
//				// If Transaction Limit is successful
//				if(trxLimitResponse != null && trxLimitResponse.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)){
//					// prepare the bean list that going to insert into mleb_ocbc.ft_trx_limit
//					processResponse(trxLimitResponse);
//					
//					// update the updateDate in mleb_ocbc.job_status_detail
//					jobStatusDetailDAO.updateUpdateDate(MiBConstant.SCHEDULED_JOB_TRXN_LIMIT);
//				}
//			}
//			
//			log.info("----- END RefreshFundTransferTransferLimit");
//				
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//
//	}
//	
//	private void processRequest(IBRequest trxLimitRequest){
//		//set request to retrieve transaction limit
//		Parameters limitParam = new Parameters();
//		limitParam.setLang(MiBConstant.LOCALE_EN.toUpperCase());
//		limitParam.setChannel(MiBConstant.CHANNEL_MB);
//		limitParam.setOperationCode("999");
//		trxLimitRequest.setParameters(limitParam);
//	}
//	
//	private void processSetBeanToEntity(LoadTrxLimit bean, TransferLimit transferLimit ){
//		transferLimit.setMinAmount(bean.getMinAmount().toPlainString());
//		transferLimit.setSingleTransAmount(bean.getSingleTransAmount().toPlainString());
//		transferLimit.setDailyAcctTransAmount(bean.getDailyAcctTransAmount().toPlainString());
//		transferLimit.setDailyCIFTransAmount(bean.getDailyCIFTransAmount().toPlainString());
//		transferLimit.setUsedTxnAmount(bean.getUsedTxnAmount().toPlainString());
//		transferLimit.setUsedTxnCIFAmount(bean.getUsedTxnCIFAmount().toPlainString());
//		transferLimit.setAvailableTransAccount(bean.getAvailableTransAccount().toPlainString());
//		transferLimit.setAvailableTransCIF(bean.getAvailableTransCIF().toPlainString());
//	}
//	
//	private void processResponse(IBResponse trxLimitResponse){
//		
//		ResponseObject responseObject = (ResponseObject) trxLimitResponse.getResponseObject();
//		
//		// delete old records
//		transferLimitDAO.deleteTransferLimit();
//		
//		if(responseObject.getOATS() != null){
//			TransferLimit transferLimit = new TransferLimit();
//			transferLimit.setTransferCode(MiBConstant.TRANS_SERVICE_TYPE_OATS);
//			processSetBeanToEntity(responseObject.getOATS(), transferLimit);
//			transferLimitDAO.insertEntity(transferLimit);
//		}
//		
//		if(responseObject.getOATC() != null){
//			TransferLimit transferLimit = new TransferLimit();
//			transferLimit.setTransferCode(MiBConstant.TRANS_SERVICE_TYPE_OATC);
//			processSetBeanToEntity(responseObject.getOATC(), transferLimit);
//			transferLimitDAO.insertEntity(transferLimit);
//		}
//		
//		if(responseObject.getIFTS() != null){
//			TransferLimit transferLimit = new TransferLimit();
//			transferLimit.setTransferCode(MiBConstant.TRANS_SERVICE_TYPE_IFTS);
//			processSetBeanToEntity(responseObject.getIFTS(), transferLimit);
//			transferLimitDAO.insertEntity(transferLimit);
//		}
//		
//		if(responseObject.getIFTC() != null){
//			TransferLimit transferLimit = new TransferLimit();
//			transferLimit.setTransferCode(MiBConstant.TRANS_SERVICE_TYPE_IFTC);
//			processSetBeanToEntity(responseObject.getIFTC(), transferLimit);
//			transferLimitDAO.insertEntity(transferLimit);
//		}
//		
//		if(responseObject.getOLTS() != null){
//			TransferLimit transferLimit = new TransferLimit();
//			transferLimit.setTransferCode(MiBConstant.TRANS_SERVICE_TYPE_OLTS);
//			processSetBeanToEntity(responseObject.getOLTS(), transferLimit);
//			transferLimitDAO.insertEntity(transferLimit);
//		}
//		
//		if(responseObject.getRTGSS() != null){
//			TransferLimit transferLimit = new TransferLimit();
//			transferLimit.setTransferCode(MiBConstant.TRANS_SERVICE_TYPE_RTGSS);
//			processSetBeanToEntity(responseObject.getRTGSS(), transferLimit);
//			transferLimitDAO.insertEntity(transferLimit);
//		}
//		
//		if(responseObject.getLLGS() != null){
//			TransferLimit transferLimit = new TransferLimit();
//			transferLimit.setTransferCode(MiBConstant.TRANS_SERVICE_TYPE_LLGS);
//			processSetBeanToEntity(responseObject.getLLGS(), transferLimit);
//			transferLimitDAO.insertEntity(transferLimit);
//		}
//	}
//	
//	
//}

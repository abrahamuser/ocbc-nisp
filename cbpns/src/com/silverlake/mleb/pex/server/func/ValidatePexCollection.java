package com.silverlake.mleb.pex.server.func;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import net.sf.ehcache.Element;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.mleb.pex.bean.ObEAIHeader;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.eai.EAIService;
import com.silverlake.mleb.pex.entity.PexCollectionCode;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.func.PerformCreationPex;
import com.silverlake.mleb.pex.module.rbs.services.ReleaseEarmarkServices;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.module.services.RBSServices;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPERRResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSAmount;
import com.silverlake.mleb.pex.server.webservice.bean.WSHeaderResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.EhcacheSession;
import com.silverlake.mleb.pex.util.EncryptionServices;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.StringDataUtil;

import org.apache.commons.codec.binary.Base64;


@Service
public class ValidatePexCollection extends PExFunc{

	
	private static Logger log = LogManager.getLogger(ValidatePexCollection.class);
	
	@Autowired MLEBPExDAO dao;
	@Autowired LockRenewSession taskSession;
	private EhcacheSession ehCache = new EhcacheSession();
	//private static ConcurrentMap attemptRecords = new ConcurrentHashMap();  
	private PropertiesManager property = new PropertiesManager();
	
	 //@Autowired @Qualifier("requestMessageQueue")
	 private JmsTemplate requestMessageQueue;
	 
	 //@Autowired @Qualifier("responseMessageQueue")
	 private JmsTemplate responseMessageQueue;
	
	
	@Override
	public WSResponse processService(WSRequest wsRequest) {
		// TODO Auto-generated method stub
		
		EncryptionServices encryptService = new EncryptionServices();
		
		WSPExResponse wsResponse = new WSPExResponse();
		try
		{
			PExServices pexServices = new PExServices(dao);
			Date validateDate = new Date();
			
			
			validateDate = pexServices.checkRBSDate(validateDate);
			
			
			wsResponse.setObHeader(new WSHeaderResponse());
			WSPExRequest pexRequest = (WSPExRequest) wsRequest;
			String collectionCode = pexRequest.getCollectionCode();
			WSAmount amount = pexRequest.getAmount();
			String mobileNo = pexRequest.getMobileNumber();
			String pin = pexRequest.getPin();
			String requestChannel = pexRequest.getObHeader().getChannelId();
			Date transactionDate = new Date();
			
			char[] hexString = Hex.encodeHex(PerformCreationPex.collectionEncryptionKey.getBytes());
			String encrypted_collection_code = encryptService.encrypt(String.valueOf(hexString).getBytes(), collectionCode.getBytes());
			
			
			String sql = "FROM HlbPexProcessTranx where collection_no = :collection_no AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACTIVE+"'";
			Hashtable params01 = new Hashtable();
			params01.put("collection_no", encrypted_collection_code);
			List<PexProcessTranx> sqlResults = dao.selectQueryParam(sql,params01);
			
			log.info("Check PEx Data !!!!!!["+collectionCode+"][][][]!!!!!!!!!!!!! "+sqlResults.size());
			if(sqlResults.size()>0)
			{
				PexProcessTranx pexProcessTranx = sqlResults.get(0);
				
				
				boolean isCollectionData = checkPexDetails(amount,mobileNo,pin,pexProcessTranx,requestChannel);
				
				if(isCollectionData)
				{
			
					wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
					wsResponse.setAmount(amount);
					wsResponse.getAmount().setCurrency(pexProcessTranx.getCurrency());
					wsResponse.setMobileNumber(mobileNo);
					wsResponse.setPexReferenceNo(pexProcessTranx.getRefNo());
					wsResponse.setCif(pexProcessTranx.getCif());
					//new response to Fuzion for new paper drop flow
					wsResponse.setFromAccountNo(pexProcessTranx.getAccountNo());
					wsResponse.setFromCurrencyCode(pexProcessTranx.getCurrency());
					wsResponse.setFromProductTypeCode(pexProcessTranx.getAccountProductType());
				}
				else
				{		
					//RBSServices rbsServices = new RBSServices(dao);
					
					
					String updateRS = updateFailedAttempt(collectionCode,pexProcessTranx,dao,transactionDate);
					if(updateRS.equalsIgnoreCase("1"))
					{
						wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_COLLECTIONDATA);
					}
					else if(updateRS.equalsIgnoreCase("2"))
					{
						//exist failed attempt
						
						
						
						
						wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_SUSPENDED);
						ObAccountBean fromAccBean = new ObAccountBean();
						fromAccBean.setAccountNumber(pexProcessTranx.getAccountNo());
						String tranxAccType = pexProcessTranx.getAccountProductType();
						String dailyRunningNumber = pexServices.updateSequenceNum(validateDate)+"";
						
						
						//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
						EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
						ObEAIHeader resultRelease = service.performReleaseEarmarkAcc(wsRequest.getObHeader().getId(), fromAccBean, pexProcessTranx.getAmountEarmark(),  pexProcessTranx.getCurrency(), tranxAccType, validateDate, pexProcessTranx.getRbsSeqNo(), dailyRunningNumber);
						//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
						
						//String resultRelease = rbsServices.releaseEarmarkKH(false,fromAccBean,tranxAccType, pexProcessTranx.getAmountEarmark(), pexProcessTranx.getCurrency(), pexProcessTranx.getRbsholdRef(), validateDate, dailyRunningNumber,30000);
						/*DataBeanUtil dataBeanUtil = new DataBeanUtil();
						RBS_DSPERRResponse earMarkERRResponse = new RBS_DSPERRResponse();
						
						
						if(resultRelease.getErrorResponseCode()==null){
							
						}
						else if(resultRelease.getResponseCode()=="0"){
							
						}
						else{
							
						}
						
						if(!resultRelease.equalsIgnoreCase(PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE))
						{
							earMarkERRResponse = (RBS_DSPERRResponse) dataBeanUtil.setFieldNamesAndByte(earMarkERRResponse, rbsServices.getFullResponseByte(), "CP037");

						}
						
						System.out.println("Release hold RBS Response");
						Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
						System.out.println(gsonLog.toJson(earMarkERRResponse));
						
						//
						*/
						
						
					}
					else
					{
						
						PexProcessTranx pexProcessTranxFailed =   pexServices.getPExTransactionByRef(pexProcessTranx.getRefNo());
						if(PExConstant.PEX_TRANSACTION_STATUS_SUSPENDED.equalsIgnoreCase(pexProcessTranxFailed.getStatus()))
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_SUSPENDED);
						else if(PExConstant.PEX_TRANSACTION_STATUS_EXPIRED.equalsIgnoreCase(pexProcessTranxFailed.getStatus()))
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_EXPIRED);
						else if(PExConstant.PEX_TRANSACTION_STATUS_CANCELLED.equalsIgnoreCase(pexProcessTranxFailed.getStatus()))
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_CANCELLED);
						else if(PExConstant.PEX_TRANSACTION_STATUS_PROCESSING.equalsIgnoreCase(pexProcessTranxFailed.getStatus()))
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_PROCESSING);
						else if(PExConstant.PEX_TRANSACTION_STATUS_FAILED.equalsIgnoreCase(pexProcessTranxFailed.getStatus()))
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_FAILED);
						else if(PExConstant.PEX_TRANSACTION_STATUS_PAID.equalsIgnoreCase(pexProcessTranxFailed.getStatus()))
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_PAID);
						else if(PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED.equalsIgnoreCase(pexProcessTranxFailed.getStatus()))
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_ACCEPTED);
						else
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_COLLECTIONNO);
					}
				}

			}
			else
			{
				String queryString = "FROM HlbPexCollectionCode where collection_no = :collection_no";
				Hashtable params02 = new Hashtable();
				params02.put("collection_no", encrypted_collection_code);
				List<PexCollectionCode> collectionCodeList = dao.selectQueryParam(queryString,params02);
				if(null!=collectionCodeList && collectionCodeList.size()>0)
				{
					log.debug("collectionCodeList:"+collectionCodeList.size());
					
					PexCollectionCode pexCollectionCode = collectionCodeList.get(0);
					queryString = "FROM HlbPexProcessTranx where ref_no = '" + pexCollectionCode.getRefNo() + "' and collection_no = '"+encrypted_collection_code+"'";
					List<PexProcessTranx> processTranxList = dao.selectQuery(queryString);
					
					if(null!=processTranxList && processTranxList.size()==1)
					{
						log.info("processTranxList:"+processTranxList.size());
						
						PexProcessTranx hlbPexProcessTranx = processTranxList.get(0);
						
						
						
							if(PExConstant.PEX_TRANSACTION_STATUS_SUSPENDED.equalsIgnoreCase(hlbPexProcessTranx.getStatus()))
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_SUSPENDED);
							else if(PExConstant.PEX_TRANSACTION_STATUS_EXPIRED.equalsIgnoreCase(hlbPexProcessTranx.getStatus()))
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_EXPIRED);
							else if(PExConstant.PEX_TRANSACTION_STATUS_CANCELLED.equalsIgnoreCase(hlbPexProcessTranx.getStatus()))
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_CANCELLED);
							else if(PExConstant.PEX_TRANSACTION_STATUS_PROCESSING.equalsIgnoreCase(hlbPexProcessTranx.getStatus()))
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_PROCESSING);
							else if(PExConstant.PEX_TRANSACTION_STATUS_FAILED.equalsIgnoreCase(hlbPexProcessTranx.getStatus()))
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_FAILED);
							else if(PExConstant.PEX_TRANSACTION_STATUS_PAID.equalsIgnoreCase(hlbPexProcessTranx.getStatus()))
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_PAID);
							else if(PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED.equalsIgnoreCase(hlbPexProcessTranx.getStatus()))
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_ACCEPTED);
							else
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_COLLECTIONNO);
						
						
							log.info("hlbPexProcessTranx status:"+hlbPexProcessTranx.getStatus());
					}
					else
						wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_COLLECTIONNO);
				}
				else
					wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_COLLECTIONNO);
			}
			
			
		}catch (Exception e)
		{
			log.info("PEx server Error - validatePEx  ",e);
			wsResponse = new WSPExResponse();
			wsResponse.setObHeader(new WSHeaderResponse());
			wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
		}
		
		

		return wsResponse;
	}


	public String updateFailedAttempt(String collectionCode,PexProcessTranx pexProcessTranx, MLEBPExDAO dao,Date transactionDate) throws InterruptedException
	{
		//1 = success update failed attempt
		//2 = suspended
		//3 = failed to update attempt count
		
		PExServices pexServices = new PExServices(dao);
		SimpleDateFormat tranxDTFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
		String tranxDate = tranxDTFormat.format(transactionDate);
		//Object resultAttempt = ehCache.getValidationPexSession().putIfAbsent(new Element(collectionCode, pexProcessTranx.getCollectionAttempt()));
		boolean lockCode = taskSession.lockTask(collectionCode, 60000);
		//if(null==resultAttempt)
		if(lockCode)
		{

			PexProcessTranx newPexProcessTranx = (PexProcessTranx) dao.findByID(PexProcessTranx.class, pexProcessTranx.getRowId());
			

			
			newPexProcessTranx.setCollectionAttempt(newPexProcessTranx.getCollectionAttempt()+1);
			int failedAttempt = pexServices.getPExConf().getCollectionMaxAttempt();
			
			String sqlUpdateActiveCounter = "Update HlbPexProcessTranx set collection_attempt = "+newPexProcessTranx.getCollectionAttempt()+" ,  collection_date = '"+tranxDate+"'";
			log.info(newPexProcessTranx.getCollectionAttempt()+" -- "+failedAttempt);
			if(newPexProcessTranx.getCollectionAttempt()>= failedAttempt)
			{
				
				sqlUpdateActiveCounter = sqlUpdateActiveCounter+" , collection_flag = '"+PExConstant.PEX_COLLECTION_FLAG_LOCKED+"' , status = '"+PExConstant.PEX_TRANSACTION_STATUS_SUSPENDED+"' , sms_suspend_flag = '"+PExConstant.PEX_SMS_FLAG_PENDING+"' , update_ib_flag='"+PExConstant.PEX_IB_FLAG_PENDING_UPDATED+"'  ";
			}
			
			sqlUpdateActiveCounter = sqlUpdateActiveCounter + " where status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACTIVE+"' AND row_id = "+newPexProcessTranx.getRowId();
			
			log.debug(sqlUpdateActiveCounter);
			int updateRS = dao.updateSQL(sqlUpdateActiveCounter);
			//ehCache.getValidationPexSession().remove(collectionCode);
			taskSession.removeTask(collectionCode);
			
			
			if(updateRS == 1)
			{
				if(newPexProcessTranx.getCollectionAttempt()>= failedAttempt)
				{
					//exist failed attempt
					return "2";
				}
				else
				{
					//failed attempt allow
					return "1";
				}
			
			}
			else
			{
				//PEx transaction processed, unable update attempt count
				return "3";
			}
			
			
		
		}
		else
		{
			Thread.sleep(50);
			return updateFailedAttempt(collectionCode,pexProcessTranx,dao,transactionDate);
		}
		
		
	}
	
	
	
	public boolean checkPexDetails(WSAmount amount, String mobileNumber, String pin, PexProcessTranx pexProcessTranx, String channelID )
	{
		StringDataUtil stringDataUtil = new StringDataUtil(); 

		if(amount.getAmount().compareTo(pexProcessTranx.getAmountPex())!= 0)
		{
			return false;
		}
		if(!stringDataUtil.isMobileNoMatch(mobileNumber, pexProcessTranx.getMobileNo()))
		{
			return false;
		}
		
		if(channelID.equalsIgnoreCase(PExConstant.PEX_COLLECTION_CHANNEL_OCM))
		{
			if(pexProcessTranx.getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_ATM))
			{
				String reqPin = "";
				try {
					
					EncryptionServices encryptService = new EncryptionServices();
					char[] hexString = Hex.encodeHex(PerformCreationPex.collectionEncryptionKey.getBytes());
					byte[] decryptedPin = encryptService.decrypt(String.valueOf(hexString).getBytes(),Base64.decodeBase64(pexProcessTranx.getPin().getBytes()));
					
					
					
					MessageDigest md = MessageDigest.getInstance( "SHA1" );
					md.update(decryptedPin);
					reqPin =  new BigInteger( 1, md.digest() ).toString(16);
					
					log.debug("["+pin+"]-["+reqPin+"]");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				if(!pin.equalsIgnoreCase(reqPin))
				{
					return false;
				}
				
			}
			else
			{
				return false;
			}
		}
		else if(channelID.equalsIgnoreCase(PExConstant.PEX_COLLECTION_CHANNEL_FUZION))
		{
			if(!pexProcessTranx.getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_INTERNET))
			{
				return false;
			}
		}
		
		
		return true;
	}
	
	
	public int updateAttempt(String collectionCode, int currentAttempt)
	{
		

		int newAttempt = currentAttempt+1;
		Object resultAttempt = ehCache.getValidationPexSession().putIfAbsent(new Element(collectionCode+newAttempt, newAttempt));
		if(null==resultAttempt)
		{
			return newAttempt;
		}
		else
		{
			return updateAttempt(collectionCode,newAttempt);
		}
			
		
	}
	
	public void removeAttempt(String collectionCode,int newAttempt)
	{
		ehCache.getValidationPexSession().remove(newAttempt);
	}
	
}


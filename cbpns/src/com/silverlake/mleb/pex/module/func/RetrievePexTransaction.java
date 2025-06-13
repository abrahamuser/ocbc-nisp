package com.silverlake.mleb.pex.module.func;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fuzion.ws.common.endpoint.CutOffTimeResponse;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObPexRequest;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.bean.ObPexTransactionDetails;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.PexConf;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.common.MiBServices;
import com.silverlake.mleb.pex.module.ib.commonServices.CommoService;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.util.EncryptionServices;
import com.silverlake.mleb.pex.util.HLBDateUtil;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.StringDataUtil;







@Service
public class RetrievePexTransaction extends FuncServices
{

	private static Logger log = LogManager.getLogger(RetrievePexTransaction.class);
	private PropertiesManager property = new PropertiesManager();
	
	@Autowired
	MLEBPExDAO dao;
	
	@Autowired ApplicationContext appContext;

	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		//StringDataUtil stringUtil = new StringDataUtil();
		EncryptionServices encryptService = new EncryptionServices();
		
		MICBResponseBean response = new MICBResponseBean();
		ObPexResponse pexResponse = new ObPexResponse();
		pexResponse.setObHeader(new ObHeaderResponse());
		pexResponse.setUserContext(new ObUserContext());
		StringDataUtil stringDataUtil = new StringDataUtil();
		
		try {
		
			SimpleDateFormat dbFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
			ObPexRequest pexRequest = (ObPexRequest) arg0.getBDObject();
			String cif = pexRequest.getObUser().getCifNumber();
			String reqTransactionType = pexRequest.getPexTranxType();
			String transactionType = null;
			int lastXdays = 1000;
			for(String pexStatusType : PExConstant.PEX_TRANX_STATUS_TYPE)
			{
				if(pexStatusType.equalsIgnoreCase(reqTransactionType))
				{
					transactionType = reqTransactionType;
				}
			}
			
			
			//check fuzion login session 
			CommoService commonService = new CommoService(appContext);
			CutOffTimeResponse cutResp = commonService.getCutOffTime(pexRequest.getUserContext(),  arg0.getTranxID());
			if(cutResp.getResponse().getStatusCode() == 0)
			{
				pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+cutResp.getResponse().getErrorCode());
			}



			else if(null==transactionType)
			{
				
				pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_PEX_STATUS_TYPE);
			}
			else
			{
				String[] pex_pending = {PExConstant.PEX_TRANSACTION_STATUS_ACTIVE};
				String[] pex_cancelled = {PExConstant.PEX_TRANSACTION_STATUS_CANCELLED};
				String[] pex_successful = {PExConstant.PEX_TRANSACTION_STATUS_PAID};
				String[] pex_failed = {PExConstant.PEX_TRANSACTION_STATUS_FAILED,PExConstant.PEX_TRANSACTION_STATUS_EXPIRED,PExConstant.PEX_TRANSACTION_STATUS_SUSPENDED};
				String[] pex_inProgress = {PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED,PExConstant.PEX_TRANSACTION_STATUS_PROCESSING};
			
				
				PExServices pexServices = new PExServices(dao);
				List<PexProcessTranx> pexTranxList = null;
				if(transactionType.equalsIgnoreCase(PExConstant.PEX_TRANX_STATUS_TYPE[0]))
				{
					//pending
					pexTranxList = pexServices.getPExTransactionByTypes(pex_pending,cif,lastXdays);
				}
				else if(transactionType.equalsIgnoreCase(PExConstant.PEX_TRANX_STATUS_TYPE[1]))
				{
					//cancel
					pexTranxList = pexServices.getPExTransactionByTypes(pex_cancelled,cif,lastXdays);
				}
				else if(transactionType.equalsIgnoreCase(PExConstant.PEX_TRANX_STATUS_TYPE[2]))
				{
					//cancel
					pexTranxList = pexServices.getPExTransactionByTypes(pex_successful,cif,lastXdays);
				}
				else if(transactionType.equalsIgnoreCase(PExConstant.PEX_TRANX_STATUS_TYPE[3]))
				{
					//failed
					pexTranxList = pexServices.getPExTransactionByTypes(pex_failed,cif,lastXdays);
				}
				else
				{
					//in progress
					pexTranxList = pexServices.getPExTransactionByTypes(pex_inProgress,cif,lastXdays);
				}
			
				
				pexResponse.setPexTransactionHistory(new ArrayList());
				
				for(int i=0;i<pexTranxList.size();i++)
				{
					PexProcessTranx pexData = pexTranxList.get(i);
					ObPexTransactionDetails pexTrnxDetails = new ObPexTransactionDetails();
					pexTrnxDetails.setAmount(String.valueOf(pexData.getAmountPex()));
					pexTrnxDetails.setCollectionAccount(new ObAccountBean());
					pexTrnxDetails.getCollectionAccount().setAccountNumber(pexData.getCollectionAccNo());
					pexTrnxDetails.getCollectionAccount().setAccountName(pexData.getCollectionAccName());
					pexTrnxDetails.getCollectionAccount().setAccountTypeCode(pexData.getCollectionAccProductType());
					
					if(pexData.getCollectionAccType()!=null && pexData.getCollectionAccProductType()!=null){
						
						pexTrnxDetails.getCollectionAccount().setAccountDescription(stringDataUtil.getAccMaping(pexData.getCollectionAccType(),pexData.getCollectionAccProductType(), pexRequest.getUserContext().getLocaleCode()));
					}else{
						pexTrnxDetails.getCollectionAccount().setAccountDescription(pexData.getCollectionAccType());
					}
										
					
					pexTrnxDetails.setCollectionType(pexData.getCollectionType());
					pexTrnxDetails.setCollectionName(pexData.getPayeeName());
					char[] hexString = Hex.encodeHex(PerformCreationPex.collectionEncryptionKey.getBytes());
					
					if(null!=pexData.getCollectionNo())
					{
						byte[] encryptedCNo = null;
						try
						{
							//encryptedCNo = encryptService.decrypt(String.valueOf(hexString).getBytes(),Base64.decode(pexData.getCollectionNo()));
							encryptedCNo = encryptService.decrypt(String.valueOf(hexString).getBytes(),Base64.decodeBase64(pexData.getCollectionNo().getBytes()));
						
						}catch(Exception e)
						{
							encryptedCNo = " ".getBytes();
						}
						pexTrnxDetails.setCollectionCode(new String(encryptedCNo));
					}
					
					if(pexData.getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_ATM))
					{
						pexTrnxDetails.setAtmCollection(true);
						pexTrnxDetails.setCollectionName(PExConstant.PEX_COLLECTION_NAME_ATM);
					}
					else if(pexData.getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_INTERNET))
					{
						pexTrnxDetails.setInternetCollection(true);
						pexTrnxDetails.setCollectionName(PExConstant.PEX_COLLECTION_NAME_INTERNET);
					}
					else if(pexData.getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_DIRECT))
					{
						pexTrnxDetails.setPExDirect(true);
						pexTrnxDetails.setCollectionName(PExConstant.PEX_COLLECTION_NAME_DIRECT);
					}
					
					pexTrnxDetails.setCurrency(pexData.getCurrency());
					
					//SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT_HIST);
					pexTrnxDetails.setDatetime(HLBDateUtil.getTransactionDateByLocale(pexData.getCreationDate(),pexRequest.getUserContext().getLocaleCode()));
					
					if(null!=pexData.getCancelledDate())
					{
						pexTrnxDetails.setCancelDate(HLBDateUtil.getTransactionDateByLocale(pexData.getCancelledDate(),pexRequest.getUserContext().getLocaleCode()));
					}
					
					if(null!=pexData.getCollectionDate())
					{
						pexTrnxDetails.setCollectDate(HLBDateUtil.getTransactionDateByLocale(pexData.getCollectionDate(),pexRequest.getUserContext().getLocaleCode()));
					}

					pexTrnxDetails.setFromAccount(new ObAccountBean());
					pexTrnxDetails.getFromAccount().setAccountNumber(pexData.getAccountNo());
					pexTrnxDetails.getFromAccount().setAccountName(pexData.getSenderName());
					pexTrnxDetails.getFromAccount().setAccountTypeCode(pexData.getAccountProductType());
										
					if(pexData.getAccountType()!=null && pexData.getAccountProductType()!=null){
						pexTrnxDetails.getFromAccount().setAccountDescription(stringDataUtil.getAccMaping(pexData.getAccountType(),pexData.getAccountProductType(), pexRequest.getUserContext().getLocaleCode()));

					}else{
						pexTrnxDetails.getFromAccount().setAccountDescription(pexData.getAccountType());
					}
										
					pexTrnxDetails.setIndexID(String.valueOf(i+1));
					pexTrnxDetails.setPayeeMsisdn(pexData.getMobileNo());
					pexTrnxDetails.setPayeeName(pexData.getPayeeName());
					pexTrnxDetails.setReferenceNumber(pexData.getRefNo());
					pexTrnxDetails.setRemark(pexData.getRemarkMessage());
					pexTrnxDetails.setSenderName(pexData.getSenderName());
					pexTrnxDetails.setServiceCharge(String.valueOf(pexData.getAmountCharges()));
					pexTrnxDetails.setStatus(pexData.getStatus());
					if(null!=pexData.getErrorCode())
						//pexTrnxDetails.setErrorMessage(pexData.getErrorMessage()+"["+pexData.getErrorCode()+"]");
						
						if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
							
							pexTrnxDetails.setErrorMessage(PExConstant.GENERAL_ERR_MSG_KH+"["+pexData.getErrorCode()+"]");
						}else{
							
							pexTrnxDetails.setErrorMessage(PExConstant.GENERAL_ERR_MSG+"["+pexData.getErrorCode()+"]");
						}

					if(pexData.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_FAILED) || pexData.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_SUSPENDED))
					{
						Date failedDate = pexData.getCollectionDate()==null?pexData.getCreationDate():pexData.getCollectionDate();

						pexTrnxDetails.setFailedDate(HLBDateUtil.getTransactionDateByLocale(failedDate,pexRequest.getUserContext().getLocaleCode()));
					}
					
					if(pexData.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_EXPIRED))
					{
						pexTrnxDetails.setFailedDate(HLBDateUtil.getTransactionDateByLocale(pexData.getExpiredDate(),pexRequest.getUserContext().getLocaleCode()));
					}
					else if(pexData.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_SUSPENDED))
					{
						pexTrnxDetails.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
						pexTrnxDetails.setErrorMessage(pexServices.loadMsgx("transaction.failed.collection.data.msg", pexRequest.getUserContext().getLocaleCode()));
						
					}
					else if(pexData.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED))
					{
						pexTrnxDetails.setStatus(PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED_NAME);
						 if(pexData.getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_DIRECT))
						 {
							 pexTrnxDetails.setStatus(PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED_DIRECT_NAME);
						 }
					}
					
					
					if(null!=transactionType && transactionType.equalsIgnoreCase(PExConstant.PEX_TRANX_STATUS_TYPE[0]))
					{
						String notice = pexServices.loadMsgx("notice.pex", pexRequest.getUserContext().getLocaleCode());  //property.getProperty("notice.pex");
						PexConf pexConf = pexServices.getPExConf();
						if(null!=pexConf && null!=notice)
							notice = notice.replaceAll("\\{0\\}", pexConf.getExpiry());
						pexTrnxDetails.setNotice(notice);
					}
					
					pexResponse.getPexTransactionHistory().add(pexTrnxDetails);
				}
				
				
				if(pexResponse.getPexTransactionHistory().size()==0)
				{
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_NO_TRANSACTION_RECORD);
				}
				else
				{
					MiBServices mibService = new MiBServices(dao);
					Collections.sort(pexResponse.getPexTransactionHistory(), pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase(MiBConstant.LOCALE_KH)?mibService.COMPARATOR_PExTrans_vn:mibService.COMPARATOR_PExTrans);
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
				}
				
			
			}
			
			
			
			
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			log.error(this.getClass().toString(), e);
			pexResponse = new ObPexResponse();
			pexResponse.setObHeader(new ObHeaderResponse());
			pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
			pexResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(pexResponse);
		
		return response;
	}


	 
	/*MuleContext muleContext;
	


	@Override
	public MuleContext getMuleContext() {
		// TODO Auto-generated method stub
		return muleContext;
	}


	@Override
	public void setMuleContext(MuleContext arg0) {
		// TODO Auto-generated method stub
		
		
		
		
		muleContext = arg0;
	}*/
	
	
	
	
}
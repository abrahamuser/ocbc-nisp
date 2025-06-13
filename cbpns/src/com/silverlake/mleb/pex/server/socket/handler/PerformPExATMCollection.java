package com.silverlake.mleb.pex.server.socket.handler;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.server.func.ValidatePexCollection;
import com.silverlake.mleb.pex.server.socket.bean.OCM_PExCollectionRequest;
import com.silverlake.mleb.pex.server.socket.bean.OCM_PExCollectionResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSAmount;
import com.silverlake.mleb.pex.server.webservice.bean.WSHeaderRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.StringDataUtil;


@Service
public class PerformPExATMCollection
{
  
	private static Logger log = LogManager.getLogger(PerformPExATMCollection.class);
	

	@Autowired ValidatePexCollection validatePex;
	@Autowired MLEBPExDAO dao;


	public OCM_PExCollectionResponse processTask(byte[] rByte, String msgEncoding) throws Exception
	{
		Date collectionDate = new Date();
		
		//SIT TESTING USING FIX DATE  ----START
		
		
		//String dtTest = "271113";
		//SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
		//collectionDate = tranxFormat.parse(dtTest);
		
		//SIT TESTING USING FIX DATE  ----END
		
		OCM_PExCollectionResponse pexCollectionResponse = new OCM_PExCollectionResponse();
		PExServices pexServices = new PExServices(dao);
		NumberFormat pointDisplayFormatter = new DecimalFormat(PExConstant.PEX_TRANSACTION_AMOUNT_NORMAL_FORMAT);
		DataBeanUtil dataBeanUtil = new DataBeanUtil();
		StringDataUtil strDataUtil = new StringDataUtil();
		OCM_PExCollectionRequest pexOCMRequest = new OCM_PExCollectionRequest();
		pexOCMRequest = (OCM_PExCollectionRequest) dataBeanUtil.setFieldNamesAndByte(pexOCMRequest, rByte, msgEncoding);
	
		log.info("PEX SERVER PROCCESS OCM Transaction Code : "+pexOCMRequest.getRequestHeader().getTranxcode_4_f_0());
		String collectionCodeReq = pexOCMRequest.getCollectioncode_40_b_$().trim();
		
		
		WSPExRequest pexRequest = new WSPExRequest();
		
		pexRequest.setObHeader(new WSHeaderRequest());
		pexRequest.getObHeader().setChannelId(pexOCMRequest.getRequestHeader().getChannelid_4_b_$());
		pexRequest.getObHeader().setVersion(pexOCMRequest.getRequestHeader().getVersion_4_f_0());
		pexRequest.setAmount(new WSAmount());
		log.info(pexOCMRequest.getAmount_19_f_0());
		log.debug(pointDisplayFormatter.format(Double.parseDouble(pexOCMRequest.getAmount_19_f_0())/100));
		pexRequest.getAmount().setAmount(new BigDecimal(pointDisplayFormatter.format(Double.parseDouble(pexOCMRequest.getAmount_19_f_0())/100)));
		pexRequest.getAmount().setCurrency(pexOCMRequest.getCurrency_3_b_$());
		pexRequest.setCollectionCode(collectionCodeReq);
		pexRequest.setMobileNumber(pexOCMRequest.getMobile_20_b_$().trim());
		pexRequest.setPin(pexOCMRequest.getPin_40_b_$().trim());
		
		
		
		String collectionCode_deHash = pexServices.getHashPexCollection(collectionCodeReq);
		if(null== collectionCode_deHash)
		{
			
			pexCollectionResponse.getResponseHeader().setResponsecode_20_b_$(PExConstant.PEX_ERR_INVALID_COLLECTIONNO);
		}
		else
		{
			pexRequest.setCollectionCode(collectionCode_deHash);
			WSResponse pexResponse =  validatePex.tcpProcess(pexRequest);
			
			
			if(pexResponse.getObHeader().getStatusCode().equalsIgnoreCase(PExConstant.PEX_ERR_COMMON_SUCCESS))
			{
				String pexRefNo = ((WSPExResponse) pexResponse).getPexReferenceNo();
				String updateSQL = "UPDATE HlbPexProcessTranx SET status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED+"' WHERE ref_no = '"+pexRefNo+"' AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACTIVE+"'";
				
				
				int updateResult = dao.updateSQL(updateSQL);
				log.info("perform pex collection update result : "+updateResult);
				if(updateResult == 1)
				{
					PexProcessTranx collectionTrnx = null;
					try
					{
						String getCollectionSQL = "FROM HlbPexProcessTranx WHERE ref_no = '"+pexRefNo+"' AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED+"'";
						List<PexProcessTranx> collectionTrnxs =  dao.selectQuery(getCollectionSQL);
						collectionTrnx = collectionTrnxs.get(0);
						
						pexCollectionResponse.setEarmarkRefId_40_b_$(collectionTrnx.getRbsholdRef());
						pexCollectionResponse.setPexRefNo_20_b_$(collectionTrnx.getRefNo());
						pexCollectionResponse.setAmount_19_f_0(collectionTrnx.getAmountPex().toString().replace(".", ""));
						pexCollectionResponse.setCharges_19_f_0(collectionTrnx.getAmountCharges().toString().replace(".", ""));
						pexCollectionResponse.setCurrency_3_b_$(collectionTrnx.getCurrency());
						pexCollectionResponse.setTellerBranch_5_b_$("1");
						
						pexCollectionResponse.getResponseHeader().setResponsecode_20_b_$(pexResponse.getObHeader().getStatusCode());
						
					}
					catch(Exception e)
					{
						String errorMsg = e.getMessage().length()>200?e.getMessage().substring(0, 200): e.getMessage();
						collectionTrnx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
						collectionTrnx.setErrorCode(PExConstant.PEX_ERR_COMMON_ERROR);
						collectionTrnx.setErrorMessage(errorMsg);
						collectionTrnx.setCollectionDate(collectionDate);
						dao.updateEntity(collectionTrnx);
						pexCollectionResponse.getResponseHeader().setResponsecode_20_b_$(PExConstant.PEX_ERR_COMMON_ERROR);
					}
					
				}
				else
				{
					pexCollectionResponse.getResponseHeader().setResponsecode_20_b_$(PExConstant.PEX_ERR_INVALID_COLLECTIONNO);
				}
					
					
				
			}
			else
			{
				pexCollectionResponse.getResponseHeader().setResponsecode_20_b_$(pexResponse.getObHeader().getStatusCode());
			}
		
		}
		
		
		
		

		return pexCollectionResponse;
					
							
	}
	
	
	
	
}

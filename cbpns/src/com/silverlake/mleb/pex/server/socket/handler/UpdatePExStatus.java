package com.silverlake.mleb.pex.server.socket.handler;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.entity.dao.PEXDAO;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.server.func.ValidatePexCollection;
import com.silverlake.mleb.pex.server.socket.bean.OCM_UpdatePExStatusRequest;
import com.silverlake.mleb.pex.server.socket.bean.OCM_UpdatePExStatusResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.StringDataUtil;


@Service
public class UpdatePExStatus
{
  
	private static Logger log = LogManager.getLogger(UpdatePExStatus.class);
	

	@Autowired ValidatePexCollection validatePex;
	@Autowired MLEBPExDAO dao;
	@Autowired PEXDAO pexDao;

	public OCM_UpdatePExStatusResponse processTask(byte[] rByte, String msgEncoding) throws Exception
	{
		Date collectionDate = new Date();

		
		OCM_UpdatePExStatusResponse pexCollectionResponse = new OCM_UpdatePExStatusResponse();
		PExServices pexServices = new PExServices(dao);
		NumberFormat pointDisplayFormatter = new DecimalFormat(PExConstant.PEX_TRANSACTION_AMOUNT_FORMAT);
		DataBeanUtil dataBeanUtil = new DataBeanUtil();
		StringDataUtil strDataUtil = new StringDataUtil();
		OCM_UpdatePExStatusRequest pexOCMRequest = new OCM_UpdatePExStatusRequest();
		pexOCMRequest = (OCM_UpdatePExStatusRequest) dataBeanUtil.setFieldNamesAndByte(pexOCMRequest, rByte, msgEncoding);
	
		log.info("PEX SERVER PROCCESS OCM Transaction Code : "+pexOCMRequest.getRequestHeader().getTranxcode_4_f_0());
		
		String pexRefNo = pexOCMRequest.getPexRefNo_20_b_$().trim();
		String earmarkRef = pexOCMRequest.getEarmarkRefId_40_b_$().trim();
		String status = pexOCMRequest.getStatus_12_b_$().trim();

		try
		{
			
			
			String[] result = pexDao.updatePExStatus(pexRefNo, earmarkRef, status, collectionDate);
			pexCollectionResponse.getResponseHeader().setResponsecode_20_b_$(result[0]);
			pexCollectionResponse.setPexRefNo_20_b_$(pexRefNo);
			pexCollectionResponse.setTransactionStatus_50_b_$(result[1]);
			SimpleDateFormat refDateTImeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			pexCollectionResponse.setTransactionDate_23_b_$(refDateTImeFormat.format(collectionDate));
			
		}catch(JpaOptimisticLockingFailureException lockEx)
		{
			
			pexCollectionResponse.getResponseHeader().setResponsecode_20_b_$(PExConstant.PEX_ERR_INVALID_PEX_STATUS_TYPE);
		}
		
		
		return pexCollectionResponse;
					
							
	}
	
	
	
	
}

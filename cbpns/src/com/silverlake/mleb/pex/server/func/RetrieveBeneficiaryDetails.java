package com.silverlake.mleb.pex.server.func;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.server.webservice.bean.WSHeaderResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;
import com.silverlake.mleb.pex.util.EncryptionServices;
import com.silverlake.mleb.pex.util.PropertiesManager;


@Service
public class RetrieveBeneficiaryDetails extends PExFunc{

	
	private static Logger log = LogManager.getLogger(RetrieveBeneficiaryDetails.class);
	
	@Autowired MLEBPExDAO dao;
	
	@Autowired ValidatePexCollection validatePexCollection;
	
	
	private PropertiesManager property = new PropertiesManager();
	
	@Override
	public WSResponse processService(WSRequest wsRequest) {
		// TODO Auto-generated method stub
		
	
		
		EncryptionServices encryptService = new EncryptionServices();
		
		WSPExResponse wsResponse = new WSPExResponse();
		wsResponse.setObHeader(new WSHeaderResponse());
		try
		{
			WSPExRequest pexRequest = (WSPExRequest) wsRequest;
			
			if(null!= pexRequest.getBeneficiaryDetails() && null!=pexRequest.getBeneficiaryDetails().getAccountNumber() && null!=pexRequest.getBeneficiaryDetails().getBankCode())
			{
			
			
			
				wsResponse = (WSPExResponse) validatePexCollection.processService(wsRequest);
				
				log.info("RESULT VALIDATE : "+wsResponse.getObHeader().getStatusCode());
				
				if(wsResponse.getObHeader().getStatusCode().equalsIgnoreCase(PExConstant.PEX_ERR_COMMON_SUCCESS))
				{
					if(pexRequest.getBeneficiaryDetails().getBankCode().equalsIgnoreCase(PExConstant.FT_BENEFICIARY_BANK_CODE))
					{
						
						wsResponse.setBeneficiaryDetails(pexRequest.getBeneficiaryDetails());
						
						//call rbs
					
						boolean rbsResult = true;
						if(rbsResult)
						{
							
							wsResponse.getBeneficiaryDetails().setAccountName("Alex Wong");
						}
						else
						{
							
						}
						
						
						
					}
					else
					{
						
						
						
					}
	
					
					
				}
			
			}
			else
			{
				//missing field
				wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_MISSING_FIELD);
				
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
	
	
	

	
	public int updatePExTransactionStatus(MLEBPExDAO dao, String encryptedCollectionCode, String status, Date paidDate)
	{
		String updateSQL = ""; 
		SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
		if(null!=paidDate)
		{
			updateSQL = "UPDATE HlbPexProcessTranx SET status = '"+status+"' , collection_date='"+refFormat.format(paidDate)+"' WHERE collection_no = '"+encryptedCollectionCode+"' AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_PROCESSING+"'";
		}
		else
		{
			updateSQL = "UPDATE HlbPexProcessTranx SET status = '"+status+"' WHERE collection_no = '"+encryptedCollectionCode+"' AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_PROCESSING+"'";	
		}

		int updateResult =	dao.updateSQL(updateSQL);
		return updateResult;
	}

	
}

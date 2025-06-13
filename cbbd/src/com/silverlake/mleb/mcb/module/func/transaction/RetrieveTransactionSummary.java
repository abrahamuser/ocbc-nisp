package com.silverlake.mleb.mcb.module.func.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObTaskListRequest;
import com.silverlake.mleb.mcb.bean.ObTaskListResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.transaction.TranxRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TranxResponseData;

@Service
public class RetrieveTransactionSummary extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveTransactionSummary.class);
	
	@Autowired
	GeneralCodeDAO dao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObTaskListResponse obTaskListResponse = new ObTaskListResponse();
		obTaskListResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {


			ObTaskListRequest requestData = (ObTaskListRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			VCService userAsyncService = new VCService(appContext); 
			
			TranxRequestData requestReq = new TranxRequestData();
			requestReq.setOrg_cd(orgId);
			requestReq.setUsr_cd(usrId);
			requestReq.setTrx_source(requestData.getTranxSourcelist());
			requestReq.setPymt_master_id(requestData.getPymt_master_id());
			if(requestData.getTrxStatus() != null && !requestData.getTrxStatus().isEmpty()){
				requestReq.setTrx_status(requestData.getTrxStatus());	
			}
		 
			String pageSize = requestData.getPageSize()==null?"10":requestData.getPageSize();
			pageSize = pageSize.trim().length()==0?"10":pageSize;
			
			
			String pageNo = requestData.getPageNo()==null?"1":requestData.getPageNo();
			pageNo = pageNo.trim().length()==0?"1":pageNo;
			
			 
			TranxResponseData responseDataTmp = new TranxResponseData();
			VCResponse vcResponse = new VCResponse();
			 
			vcResponse = userAsyncService.callOmniVC(TranxRequestData.method_transaction_summary, requestReq, responseDataTmp, requestData.getUserContext().getSessionId(),  arg0.getTranxID(), ipAddress);
			
			
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				
				responseDataTmp = (TranxResponseData) vcResponse.getData();
			 
				
				
				obTaskListResponse.setTaskTransactionSum(responseDataTmp.getList_trx_summary());
				
				

				
				obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else
			{
				
				obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obTaskListResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
			
			
			
			
			
		
		} catch (Exception e) {
			
			
		
			log.info(this.getClass().toString(), e);
			obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obTaskListResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(obTaskListResponse);
		
		return response;
	}

	
	
	
	
	 
	
 

	
	
}
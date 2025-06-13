package com.silverlake.mleb.mcb.module.func.authorization;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionSum;

/*Authorization (Filter For Pending Verification List)*/

@Service
public class PerformAuthorizeConfirmation extends FuncServices  
{
	private static Logger log = LogManager.getLogger(PerformAuthorizeConfirmation.class);

	@Autowired
	CustomerDAO dao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired ApplicationContext appContext;

	public MICBResponseBean process(MICBRequestBean arg0) {
		
		MICBResponseBean response = new MICBResponseBean();
		ObAuthorizationResponse obResponse = new ObAuthorizationResponse();
		obResponse.setObHeader(new ObHeaderResponse());

		try {
			ObAuthorizationRequest requestData = (ObAuthorizationRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			boolean isNBApproval = false;
			if(requestData.getSource_trx() != null && requestData.getSource_trx().size() > 0){
				for(String sourceTrx:requestData.getSource_trx()){
					if(sourceTrx.equalsIgnoreCase("NB")){
						isNBApproval = true;
						break;
					}
				}
			}
			
			AuthRequestData vcAuthTransactionRequest = new AuthRequestData();
			VCService usrService = new VCService(appContext);
			vcAuthTransactionRequest.setOrg_cd(orgId);
			vcAuthTransactionRequest.setUsr_cd(usrId);
			
			//Call RES OMNI Auth 
			AuthResponseData vcAuthResponseData = new AuthResponseData();
			
			boolean callInquiryCheck = false;
			
			VCResponse<AuthResponseData> vcAuthResponse = null;
			if(callInquiryCheck)
			{
				vcAuthResponse = usrService.callOmniVC(vcAuthTransactionRequest.method_auth_List_pending_trx,vcAuthTransactionRequest, vcAuthResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);

			}
			else
			{
				vcAuthResponse = new VCResponse();
				vcAuthResponse.setResponse_code(MiBConstant.OMNI_SUCCESS);
			}

			
			if(vcAuthResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcAuthResponseData = vcAuthResponse.getData();
				if (requestData.getList_trx()!=null)
				{
					obResponse.setList_trx(new ArrayList());
					for(Transaction temp:requestData.getList_trx())
					{
						Transaction transaction = new Transaction();
						BeanUtils.copyProperties(temp, transaction);
						obResponse.getList_trx().add(transaction);
					}
					
					//If it is not NB source trx, calculate the transaction sum (summary amount for each currency)
					if(!isNBApproval){
						HashMap<String, ArrayList<Transaction>> hashMap = new HashMap<String, ArrayList<Transaction>>();
						
						for (Transaction listTemp : obResponse.getList_trx()) {
						    String key  = listTemp.getAmount_ccy();
						    if(hashMap.containsKey(key)){
						    	ArrayList<Transaction> list = hashMap.get(key);
						        list.add(listTemp);
	
						    }else{
						    	ArrayList<Transaction> list = new ArrayList<Transaction>();
						        list.add(listTemp);
						        hashMap.put(key, list);
						    }
						}
						obResponse.setTransactionSum(new ArrayList());
						
						for (String i : hashMap.keySet()) {
							BigDecimal amountValue = new BigDecimal("000.00");
						    ArrayList<Transaction> listTransac = hashMap.get(i);
						    TransactionSum test2 = new TransactionSum();
						    
						    for(Transaction transac:listTransac) {
						    	if(transac.getAmount()!=null)
						    	{
						    		amountValue = amountValue.add(transac.getAmount());
						    	}
						    }
						    
						    test2.setAmount_ccy(listTransac.get(0).getAmount_ccy());
						    test2.setAmount(amountValue);
						    test2.setTotal_item( (listTransac.size()));
						    obResponse.getTransactionSum().add(test2);
						}
					}
					
					obResponse.setTotal_transaction(String.valueOf(obResponse.getList_trx().size()));
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
				}
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				obResponse.setIdTransaction(UUID.randomUUID().toString());
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcAuthResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcAuthResponse.getResponse_desc());
			}

		} catch (Exception e) {

			log.info(this.getClass().toString(),e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}

		response.setBDObject(obResponse);

		return response;
	}
}

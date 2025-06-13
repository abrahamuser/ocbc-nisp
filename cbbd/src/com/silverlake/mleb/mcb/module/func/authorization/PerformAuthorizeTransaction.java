package com.silverlake.mleb.mcb.module.func.authorization;

import java.util.ArrayList;

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
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPayment;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthResponseData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthResult;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;

/*Authorization (Approve Part 3)*/

@Service
public class PerformAuthorizeTransaction extends FuncServices  
{
	private static Logger log = LogManager.getLogger(PerformAuthorizeTransaction.class);

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
			String request_id = requestData.getRequestId();
			String responseCode = requestData.getResponseCode();
			String deviceId = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			AuthRequestData vcAuthTransactionRequest = new AuthRequestData();
			VCService usrService = new VCService(appContext);
			vcAuthTransactionRequest.setOrg_cd(orgId);
			vcAuthTransactionRequest.setUsr_cd(usrId);
			vcAuthTransactionRequest.setList_payment(requestData.getList_payment());
			
			//Conditional implementation
			if (vcAuthTransactionRequest.getList_payment()!=null)
			{
				if (vcAuthTransactionRequest.getList_payment().get(0).getAction_cd().equalsIgnoreCase("A")) {
					vcAuthTransactionRequest.setRequest_id(request_id);
					vcAuthTransactionRequest.setResponse_code(responseCode);
					vcAuthTransactionRequest.setDevice_id(deviceId);
				} else {
					vcAuthTransactionRequest.setRequest_id("");
					vcAuthTransactionRequest.setResponse_code("");
					vcAuthTransactionRequest.setDevice_id("");
				}
			}
			
			//Call RES OMNI Auth 
			AuthResponseData vcAuthResponseData = new AuthResponseData();
			
			VCResponse<AuthResponseData> vcAuthResponse = null;
			vcAuthResponse = usrService.callOmniVC(vcAuthTransactionRequest.method_auth_authorize,vcAuthTransactionRequest, vcAuthResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcAuthResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcAuthResponseData = vcAuthResponse.getData();
				if (vcAuthResponseData.getList_auth_result_info()!=null)
				{
					obResponse.setList_payment(vcAuthTransactionRequest.getList_payment());
					obResponse.setList_auth_result_info(new ArrayList());
					for(AuthResult temp:vcAuthResponseData.getList_auth_result_info())
					{
						AuthResult authResult = new AuthResult();
						BeanUtils.copyProperties(temp, authResult);
						obResponse.getList_auth_result_info().add(authResult);
						//Set value date in to list_payment
						for(AuthPayment authPayment:obResponse.getList_payment()){
							if(authPayment.getPymt_master_id().equals(authResult.getPymt_master_id())){
								authPayment.setValue_date(authResult.getValue_date());
								
							}
						}
					}
					
					obResponse.setList_auth_result_info(new ArrayList());
					for(AuthResult temp:vcAuthResponseData.getList_auth_result_info())
					{
						AuthResult authResult = new AuthResult();
						BeanUtils.copyProperties(temp, authResult);
						obResponse.getList_auth_result_info().add(authResult);
						//Set the error message into list_payment
						for(AuthPayment authPayment:obResponse.getList_payment()){
							if(authPayment.getPymt_master_id().equals(authResult.getPymt_master_id())){
								authPayment.setError_message(authResult.getError_message());
								break;
							}
						}
					}
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
				}
				
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcAuthResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcAuthResponse.getResponse_desc());
			}

		} catch (Exception e) {

			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}

		response.setBDObject(obResponse);

		return response;
	}
}

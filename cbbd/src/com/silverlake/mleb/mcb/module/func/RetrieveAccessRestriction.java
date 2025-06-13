package com.silverlake.mleb.mcb.module.func;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAccessRestrictionBean;
import com.silverlake.mleb.mcb.bean.ObAccessRestrictionRequest;
import com.silverlake.mleb.mcb.bean.ObAccessRestrictionResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.AccessRestrictionTransaction;
import com.silverlake.mleb.mcb.entity.dao.AccessRestrictionTransactionDAO;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCAsyncResponseBean;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: Check the access restriction of action button 
 * Current usage in transaction summary module
 * 
 * Omni Web Services:
 * user/access_restriction
 * 
 * If the configuration in database not exist, default to not allowed.
 * 
 * @author Alex Loo
 */
@Service
public class RetrieveAccessRestriction extends SessionFuncServices<ObAccessRestrictionRequest, ObAccessRestrictionResponse>{
	private static Logger log = LogManager.getLogger(RetrieveAccessRestriction.class);
	
	@Autowired AccessRestrictionTransactionDAO accessRestrictionTransactionDAO;
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObAccessRestrictionRequest requestBean, ObAccessRestrictionResponse responseBean, VCGenericService vcService) throws Exception {
		if(requestBean.getProductCode() != null && requestBean.getProductCode().isEmpty()){
			requestBean.setProductCode("ALL");//Set to ALL if empty
		}
		
		AccessRestrictionTransaction accessRestrictionTransaction = accessRestrictionTransactionDAO.get(requestBean.getSourceCode(), requestBean.getProductCode(), requestBean.getStatusCode());
		
		responseBean.setIsNewAllowed(false);
		responseBean.setIsEditAllowed(false);
		responseBean.setIsAuditTrailAllowed(false);
		responseBean.setIsCancelAllowed(false);
		responseBean.setIsDeleteAllowed(false);
		responseBean.setIsNotesAllowed(false);
		responseBean.setIsPrintAllowed(false);
		
		if(accessRestrictionTransaction == null){
			log.info("Access restriction configuration record not found for "+requestBean.getSourceCode()+","+requestBean.getProductCode()+","+requestBean.getStatusCode());
		}
		
		//Check access restriction
		if(accessRestrictionTransaction != null && accessRestrictionTransaction.getMenuId() != null && !accessRestrictionTransaction.getMenuId().isEmpty()){
			responseBean.setIsNewAllowed(true);
			if(!accessRestrictionTransaction.getIsNew().equalsIgnoreCase("Y")){
				responseBean.setIsNewAllowed(false);
			}
			responseBean.setIsEditAllowed(true);
			if(!accessRestrictionTransaction.getIsEdit().equalsIgnoreCase("Y")){
				responseBean.setIsEditAllowed(false);
			}
			responseBean.setIsAuditTrailAllowed(true);
			if(!accessRestrictionTransaction.getIsAudit().equalsIgnoreCase("Y")){
				responseBean.setIsAuditTrailAllowed(false);
			}
			responseBean.setIsCancelAllowed(true);
			if(!accessRestrictionTransaction.getIsCancel().equalsIgnoreCase("Y")){
				responseBean.setIsCancelAllowed(false);
			}
			responseBean.setIsDeleteAllowed(true);
			if(!accessRestrictionTransaction.getIsDelete().equalsIgnoreCase("Y")){
				responseBean.setIsDeleteAllowed(false);
			}
			responseBean.setIsNotesAllowed(true);
			if(!accessRestrictionTransaction.getIsNote().equalsIgnoreCase("Y")){
				responseBean.setIsNotesAllowed(false);
			}
			responseBean.setIsPrintAllowed(true);
			if(!accessRestrictionTransaction.getIsPrint().equalsIgnoreCase("Y")){
				responseBean.setIsPrintAllowed(false);
			}
			
			com.silverlake.mleb.mcb.module.vc.user.RequestData vcAccessRetrictionRequest = new com.silverlake.mleb.mcb.module.vc.user.RequestData();
			vcAccessRetrictionRequest.setOrg_cd(requestBean.getObUser().getOrgId());
			vcAccessRetrictionRequest.setUsr_cd(requestBean.getObUser().getLoginId());
			vcAccessRetrictionRequest.setMn_itm_id(accessRestrictionTransaction.getMenuId());
			com.silverlake.mleb.mcb.module.vc.user.ResponseData vcAccessRetrictionResponseData;
			VCResponse<com.silverlake.mleb.mcb.module.vc.user.ResponseData> vcAccessRetrictionResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.USER_ACCESS_RESTRICTION,
					vcAccessRetrictionRequest,
					com.silverlake.mleb.mcb.module.vc.user.ResponseData.class,
					true,
					accessRestrictionTransaction.getMenuId());
			
			com.silverlake.mleb.mcb.module.vc.user.ResponseData responseData = vcAccessRetrictionResponse.getData();
			if(responseData != null && responseData.getList_restriction() != null){
				for(String ar:responseData.getList_restriction()){
					if(ar.equalsIgnoreCase("mn.act.new") && responseBean.getIsNewAllowed()){
						responseBean.setIsNewAllowed(false);
					}else if(ar.equalsIgnoreCase("mn.act.edit") && responseBean.getIsEditAllowed()){
						responseBean.setIsEditAllowed(false);
					}else if(ar.equalsIgnoreCase("mn.act.cancel") && responseBean.getIsCancelAllowed()){
						responseBean.setIsCancelAllowed(false);
					}else if(ar.equalsIgnoreCase("mn.act.notes") && responseBean.getIsNotesAllowed()){
						responseBean.setIsNotesAllowed(false);
					}else if(ar.equalsIgnoreCase("mn.act.audit") && responseBean.getIsAuditTrailAllowed()){
						responseBean.setIsAuditTrailAllowed(false);
					}else if(ar.equalsIgnoreCase("mn.act.print") && responseBean.getIsPrintAllowed()){
						responseBean.setIsPrintAllowed(false);
					}else if(ar.equalsIgnoreCase("mn.act.delete") && responseBean.getIsDeleteAllowed()){
						responseBean.setIsDeleteAllowed(false);
					}
				}
			}			
		}
	}
}

package com.silverlake.mleb.mcb.module.func.transaction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObBiFastBean;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;

/**
 * Purpose: To retrieve all the related parameters for managing beneficiary
 * Get BENE_SHARED_OPTION list from general code and populate it into sharedBeneficiaryList 
 * 
 * Omni Web Services:
 * None
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionManageBeneficiaryStep1 extends SessionFuncServices<ObTransactionManageBeneficiaryRequest, ObTransactionManageBeneficiaryResponse>{
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean, VCGenericService vcService) throws Exception {
		responseBean.setShareBeneficiaryList(new LinkedHashMap<String, String>(2));
		List<GeneralCode> gnList = gnDao.findByGnCodeType(MiBConstant.BENE_SHARED_OPTION, locale);
		for(GeneralCode gnItem:gnList){
			responseBean.getShareBeneficiaryList().put(gnItem.getGnCode(), gnItem.getGnCodeDescription());
		}
		List<ObBiFastBean> proxyTypeList = new ArrayList<ObBiFastBean>();
		List<GeneralCode> proxy = gnDao.getproxyList(locale);
		for(GeneralCode temp:proxy){
			
			ObBiFastBean proxyLs = new ObBiFastBean();
			proxyLs.setCode(temp.getGnCode());
			proxyLs.setDescription(temp.getGnCodeDescription());
			proxyTypeList.add(proxyLs);
		}
		responseBean.setProxyTypeList(proxyTypeList);
	}
}

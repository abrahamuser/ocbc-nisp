package com.silverlake.mleb.mcb.module.func.registration;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObRegistrationRequest;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.bean.ObRegistrationeUserSessionCache;
import com.silverlake.mleb.mcb.entity.RegCacheData;
import com.silverlake.mleb.mcb.entity.dao.RegCacheDao;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.registration.InputterDetails;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationInquiryResponseData;
import com.silverlake.mleb.mcb.module.vc.registration.SignerDetails;

/**
 * Purpose: To retrieve the Individual inputter signer data
 *  *   
 */
@Service
public class RetrieveInputterSignerData extends CacheSessionFuncServices<ObRegistrationRequest, ObRegistrationResponse, ObRegistrationeUserSessionCache>{
	private static Logger log = LogManager.getLogger(RetrieveInputterSignerData.class);
	
	@Autowired
	RegCacheDao regCacheDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean, ObRegistrationeUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
		
		RegCacheData cacheData = new RegCacheData();
	
		RegCacheData regCacheData =(RegCacheData) regCacheDao.findByID(RegCacheData.class, requestBean.getRecord_id());
		ObjectInputStream objectIn = new ObjectInputStream(
				new ByteArrayInputStream(regCacheData.getRegObject()));
		RegistrationInquiryResponseData inquiryResponseData = (RegistrationInquiryResponseData)objectIn.readObject();
					
		SignerDetails sign = inquiryResponseData.getRegistration_data().getSigner_details();
		for (InputterDetails inp : sign.getSigner_list()) {

			if(inp.getUser_id().equals(requestBean.getUser_id())){
			responseBean.setUser_id(inp.getUser_id());
			responseBean.setName(inp.getName());
			responseBean.setName_masked(inp.getName_masked());
			responseBean.setVersion_no(inp.getVersion_no());
			responseBean.setSequence(inp.getSequence());
			responseBean.setKtp_no(inp.getKtp_no());
			responseBean.setKtp_no_masked(inp.getKtp_no_masked());
			responseBean.setNpwp_no(inp.getNpwp_no());
			responseBean.setNpwp_no_masked(inp.getNpwp_no_masked());
			responseBean.setAddress(inp.getAddress());
			responseBean.setAddress_masked(inp.getAddress_masked());
			responseBean.setEmail(inp.getEmail());
			responseBean.setEmail_masked(inp.getEmail_masked());
			responseBean.setPhone(inp.getPhone());
			responseBean.setPhone_masked(inp.getPhone_masked());
			responseBean.setGender(inp.getGender());
			responseBean.setGender_masked(inp.getGender_masked());
			responseBean.setBirth_place(inp.getBirth_place());
			responseBean.setBirth_place_masked(inp.getBirth_place_masked());
			responseBean.setBirth_date(inp.getBirth_date());
			responseBean.setBirth_date_masked(inp.getBirth_date_masked());
			responseBean.setProvince(inp.getProvince());
			responseBean.setProvince_masked(inp.getProvince_masked());
			responseBean.setCity(inp.getCity());
			responseBean.setCity_masked(inp.getCity_masked());
			responseBean.setKtp_file_name(inp.getKtp_file_name());
			responseBean.setKtp_file_ext(inp.getKtp_file_ext());
			responseBean.setKtp_photo(inp.getKtp_photo());
			responseBean.setNpwp_file_name(inp.getNpwp_file_name());
			responseBean.setNpwp_file_ext(inp.getNpwp_file_ext());
			responseBean.setNpwp_photo(inp.getNpwp_photo());
			
			}
		}

	}
	
}

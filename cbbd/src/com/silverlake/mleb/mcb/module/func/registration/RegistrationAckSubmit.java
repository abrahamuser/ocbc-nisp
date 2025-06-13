package com.silverlake.mleb.mcb.module.func.registration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObRegistrationRequest;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.bean.ObRegistrationeUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.RegCacheData;
import com.silverlake.mleb.mcb.entity.dao.RegCacheDao;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.registration.DocumentDetails;
import com.silverlake.mleb.mcb.module.vc.registration.InputterDetails;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationData;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationSubmitAckRequestData;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationSubmitAckResponseData;
import com.silverlake.mleb.mcb.module.vc.registration.RevisionInfo;
import com.silverlake.mleb.mcb.module.vc.registration.SignerDetails;
import com.silverlake.mleb.mcb.module.vc.registration.VerficationInfo;


/**
 * Purpose: To Submit Acknowledgment
 * Omni Web Services:
 * others/velocity-registration/ack/submit
 *   
 */
@Service
public class RegistrationAckSubmit extends CacheSessionFuncServices<ObRegistrationRequest, ObRegistrationResponse, ObRegistrationeUserSessionCache>{
	private static Logger log = LogManager.getLogger(RegistrationAckSubmit.class);
	
	@Autowired
	RegCacheDao regCacheDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean, ObRegistrationeUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
		RegistrationSubmitAckRequestData requestData = new RegistrationSubmitAckRequestData();
		RegistrationSubmitAckResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<RegistrationSubmitAckResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.REGISTRATION_SUBMIT_ACK, 
				requestData, 
				RegistrationSubmitAckResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		
		
		processResponse(locale, requestData, responseData, requestBean, responseBean);
		
	
	}
	
	private void processRequest(String locale, RegistrationSubmitAckRequestData requestData, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean) throws Exception{
		
		String deviceId = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getDeviceId();
		String deviceOS = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getOs();
		String deviceType = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getModel();
		
		requestData.setRecord_id(requestBean.getRecord_id());
		requestData.setVersion_no(requestBean.getVersion_no());
		requestData.setRegistration_no(requestBean.getRegistration_no());
		requestData.setVerification_no(requestBean.getVerificationNo());;
					
			InputterDetails inp = new InputterDetails();
			
			inp.setName(requestBean.getName());
			inp.setEmail(requestBean.getEmail());
			inp.setKtp_no(requestBean.getKtp_no());
			inp.setNpwp_no(requestBean.getNpwp_no());
			inp.setGender(requestBean.getGender());
			inp.setBirth_place(requestBean.getBirth_place());
			inp.setBirth_date(requestBean.getBirth_date());
			inp.setAddress(requestBean.getAddress());
			inp.setProvince(requestBean.getProvince());
			inp.setCity(requestBean.getCity());
			inp.setPhone(requestBean.getPhone());
			inp.setKtp_file_name(requestBean.getKtp_file_name());
			inp.setKtp_file_ext(requestBean.getKtp_file_ext());
			
			String ktpBase64 = null;
			if(requestBean.getKtpPhoto() != null && requestBean.getIsBase64() != null && requestBean.getIsBase64()) {
				ktpBase64 = new String(requestBean.getKtpPhoto());
				inp.setKtp_photo(ktpBase64);
			}else if(requestBean.getKtpPhoto() != null){
				ktpBase64 = Base64.encode(requestBean.getKtpPhoto());
				inp.setKtp_photo(ktpBase64);
			}
			
			inp.setNpwp_file_name(requestBean.getNpwp_file_name());
			inp.setNpwp_file_ext(requestBean.getNpwp_file_ext());
			
			String npwpBase64 = null;
			if(requestBean.getNpwpPhoto() != null && requestBean.getIsBase64() != null && requestBean.getIsBase64()) {
				npwpBase64 = new String(requestBean.getNpwpPhoto());
				inp.setNpwp_photo(npwpBase64);
			}else if(requestBean.getNpwpPhoto() != null){
				npwpBase64 = Base64.encode(requestBean.getNpwpPhoto());
				inp.setNpwp_photo(npwpBase64);
			}
			
			inp.setSequence(requestBean.getSequence());
			
			requestData.setUser_details(inp);
				
			requestData.setDevice_id(deviceId);
			requestData.setDevice_type(deviceType);
			requestData.setDevice_os(deviceOS);
		
	}
	
	private void processResponse(String locale, RegistrationSubmitAckRequestData requestData, RegistrationSubmitAckResponseData responseData, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean) throws Exception{
		
		regCacheDao.deleteByID(RegCacheData.class, requestBean.getRecord_id());	
		
		RegistrationData reg = responseData.getRegistration_data();
	      
	       responseBean.setRecord_id(reg.getRecord_id());
	       responseBean.setRegistration_no(reg.getRegistration_no());
	       responseBean.setVersion_no(reg.getVersion_no());
	       responseBean.setRevision_no(reg.getRevision_no());
	       responseBean.setSubmission_date(reg.getSubmission_date());
	       responseBean.setLast_revision_date(reg.getLast_revision_date());
	       responseBean.setRegistration_status(reg.getRegistration_status());
	       responseBean.setAccount_details(reg.getAccount_details());
	       responseBean.setDocument_details_list(reg.getDocument_details_list());
	       
	       if (reg.getDocument_details_list() != null) {
	    	   
	    	   for (DocumentDetails doc : reg.getDocument_details_list()) {

	    		   if(doc.getFinal_signed_doc() == null){
	    			   doc.setFinal_signed_doc(doc.getDoc_blob());
	    		   }
	    		       doc.setDoc_blob(null);
				}
	          
	    	   
	       }
	 
	       responseBean.setRole("SG");
	       

			
			responseBean.setInputter_details(reg.getInputter_details());
			
			SignerDetails sign = reg.getSigner_details();
			
		    responseBean.setSigner_details(new SignerDetails());
		
			responseBean.getSigner_details().setSignature_flow(sign.getSignature_flow());
			
			List<InputterDetails> IDList = new ArrayList<InputterDetails>();

			for (InputterDetails inp : sign.getSigner_list()) {

				InputterDetails ipDtl = new InputterDetails();
				
				ipDtl.setUser_id(inp.getUser_id());
				ipDtl.setName(inp.getName());
				ipDtl.setName_masked(inp.getName_masked());
				ipDtl.setKtp_no(inp.getKtp_no());
				ipDtl.setSequence(inp.getSequence());
				ipDtl.setSigner_own("N");
               if(inp.getKtp_no().equals(requestBean.getKtpNo())){
                ipDtl.setSigner_own("Y");
				}
              /* if(inp.getKtp_no().equals(requestBean.getKtpNo())){
               ipDtl.setSignature_details(inp.getSignature_details());
               }else {*/
               	
			if (inp.getSignature_details() != null) {

				if (inp.getSignature_details().getSign_status() != null) {
					if (inp.getSignature_details().getSign_status().equalsIgnoreCase("2")) {
						inp.getSignature_details().setSign_status("Y");
					} else {
						inp.getSignature_details().setSign_status("N");
					}

				} else {
					inp.getSignature_details().setSign_status("N");
				}

				ipDtl.setSignature_details(inp.getSignature_details());
			}

          //     }
               
				if (inp.getRevision_info() != null) {
					ipDtl.setRevision_info(new RevisionInfo());
					RevisionInfo rev = inp.getRevision_info();
					if (rev.getRevision_no() != null || !rev.getRevision_no().isEmpty()) {
						ipDtl.getRevision_info().setRevision_no("Yes");
					}else {
						ipDtl.getRevision_info().setRevision_no("No");
					}
					if (rev.getRevision_used_date() != null) {
						ipDtl.getRevision_info().setRevision_used_date("Yes");
					} else {
						ipDtl.getRevision_info().setRevision_used_date("No");
					}
                   if (rev.getRevision_exp_date() != null) {
						
                   	Date d2=new SimpleDateFormat("yyyy-MM-dd").parse(rev.getRevision_exp_date());
                   	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            		    Date d1 = sdf.parse(sdf.format(new Date()));
            		   int result = d1.compareTo(d2);
						if (result > 0) {
							ipDtl.getRevision_info().setRevision_exp_date("Yes");
						}
					} else {
						ipDtl.getRevision_info().setRevision_exp_date("No");
					}
				} else {
					ipDtl.setRevision_info(new RevisionInfo());
					ipDtl.getRevision_info().setRevision_no("No");
					ipDtl.getRevision_info().setRevision_used_date("No");
					ipDtl.getRevision_info().setRevision_exp_date("No");
				}
				
				if (inp.getVerification_info() != null) {
					ipDtl.setVerification_info(new VerficationInfo());
					VerficationInfo ver = inp.getVerification_info();
					if (ver.getVerification_no() != null) {
						ipDtl.getVerification_info().setVerification_no("Yes");
					} else {
						ipDtl.getVerification_info().setVerification_no("No");
					}
					if (ver.getVerification_used_date() != null) {
						ipDtl.getVerification_info().setVerification_used_date("Yes");
					} else {
						ipDtl.getVerification_info().setVerification_used_date("No");
					}
					    ipDtl.getVerification_info().setVerification_exp_date("No");
				} else {
					ipDtl.setVerification_info(new VerficationInfo());
					ipDtl.getVerification_info().setVerification_no("No");
					ipDtl.getVerification_info().setVerification_used_date("No");
					ipDtl.getVerification_info().setVerification_exp_date("No");
				}
				
               
				IDList.add(ipDtl);

			}
			
			responseBean.getSigner_details().setSigner_list(IDList);
		}
	
	
	
}

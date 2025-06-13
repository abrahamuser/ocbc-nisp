package com.silverlake.mleb.mcb.module.func.registration;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationInquiryRequestData;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationInquiryResponseData;
import com.silverlake.mleb.mcb.module.vc.registration.RevisionInfo;
import com.silverlake.mleb.mcb.module.vc.registration.SignerDetails;
import com.silverlake.mleb.mcb.module.vc.registration.VerficationInfo;

/**
 * Purpose: To retrieve the Registration Details
 * Omni Web Services:
 * others/velocity-registration/inquiry
 *   
 */
@Service
public class RetrieveInquiryRegistration extends CacheSessionFuncServices<ObRegistrationRequest, ObRegistrationResponse, ObRegistrationeUserSessionCache>{
	private static Logger log = LogManager.getLogger(RetrieveInquiryRegistration.class);
	
	@Autowired
	RegCacheDao regCacheDao;
	
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean, ObRegistrationeUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
		RegistrationInquiryRequestData requestData = new RegistrationInquiryRequestData();
		RegistrationInquiryResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<RegistrationInquiryResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.INQUIRY_REGISTRATION, 
				requestData, 
				RegistrationInquiryResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		RegistrationInquiryResponseData cacheData;
		cacheData = vcResponse.getData();
		cacheData.setRole(requestBean.getRole());
		cacheData.setVerificationNo(requestBean.getVerificationNo());
		cacheData.setRevisionNo(requestBean.getRevisionNo());
		cacheData.setKtpNo(requestBean.getKtpNo());
			
		regCacheDao.deleteByID(RegCacheData.class, cacheData.getRegistration_data().getRecord_id());
		
		RegCacheData regCacheData = new RegCacheData();
		regCacheData.setId(cacheData.getRegistration_data().getRecord_id());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oout = new ObjectOutputStream(baos);
		oout.writeObject(cacheData);
		oout.close();
		regCacheData.setRegObject(baos.toByteArray());
		Date date = new Date();
		regCacheData.setCreateDate(date);
		regCacheDao.insertEntity(regCacheData);
		
		processResponse(locale, requestData, responseData, requestBean, responseBean);
		
	
	}
	
	private void processRequest(String locale, RegistrationInquiryRequestData requestData, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean) throws Exception{
	
		String deviceId = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getDeviceId();
		String deviceOS = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getOs();
		String deviceType = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getModel();
		
		
		requestData.setRole(requestBean.getRole());
		requestData.setEmail(requestBean.getEmail());
		requestData.setMobile_no(requestBean.getMobileNo());
		requestData.setKtp_no(requestBean.getKtpNo());
		requestData.setRegistration_no(requestBean.getRegistrationNo());
		requestData.setAccount_name(requestBean.getAccountName());
		requestData.setVerification_no(requestBean.getVerificationNo());
		requestData.setRevision_no(requestBean.getRevisionNo());
		requestData.setDevice_id(deviceId);
		requestData.setDevice_type(deviceType);
		requestData.setDevice_os(deviceOS);
		
	}
	
	private void processResponse(String locale, RegistrationInquiryRequestData requestData, RegistrationInquiryResponseData responseData, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean) throws Exception{
		
		
		RegistrationData reg = responseData.getRegistration_data();
		
		if(requestData.getRole().equalsIgnoreCase("IN")){
			
			if(reg.getRegistration_status().equals("001") || reg.getRegistration_status().equals("005") || reg.getRegistration_status().equals("003")){
				if(responseData.getAllow_cancel()){
					responseBean.setAllow_cancel("Y");
				}
			}
			
			if(reg.getRegistration_status().equals("003")) {
					if(responseData.getAllow_edit() && requestBean.getRevisionNo() != null && !requestBean.getRevisionNo().isEmpty()){
					responseBean.setAllow_edit("Y");
				}
			}
		}
		
       if(requestData.getRole().equalsIgnoreCase("SG")){
			
			if(reg.getRegistration_status().equals("001")){
				if(responseData.getAllow_ack() && requestBean.getVerificationNo() != null && !requestBean.getVerificationNo().isEmpty()){
					responseBean.setAllow_ack("Y");
				}
			}
			
			if(reg.getRegistration_status().equals("004")) {
				if(responseData.getAllow_edit() && requestBean.getRevisionNo() != null && !requestBean.getRevisionNo().isEmpty()){
					responseBean.setAllow_edit("Y");
				}
				
			}
		}
       
       responseBean.setRecord_id(reg.getRecord_id());
       responseBean.setRegistration_no(reg.getRegistration_no());
       responseBean.setVersion_no(reg.getVersion_no());
       responseBean.setRevision_no(reg.getRevision_no());
       responseBean.setSubmission_date(reg.getSubmission_date());
       responseBean.setLast_revision_date(reg.getLast_revision_date());
       responseBean.setRegistration_status(reg.getRegistration_status());
       responseBean.setAccount_details(reg.getAccount_details());
       
       if (reg.getDocument_details_list() != null) {
    	   
    	   for (DocumentDetails doc : reg.getDocument_details_list()) {

    		   if(doc.getFinal_signed_doc() == null){
    			   doc.setFinal_signed_doc(doc.getDoc_blob());
    		   }
    		       doc.setDoc_blob(null);
			}
          
    	   
       }
       
       
       responseBean.setDocument_details_list(reg.getDocument_details_list());
 
       responseBean.setRole(requestBean.getRole());
       
		if (requestData.getRole().equalsIgnoreCase("IN")) {
			
			if (reg.getInputter_details().getSignature_details() != null) {

				if (reg.getInputter_details().getSignature_details().getSign_status() != null) {
					if (reg.getInputter_details().getSignature_details().getSign_status().equalsIgnoreCase("2")) {
						reg.getInputter_details().getSignature_details().setSign_status("Y");
					} else {
						reg.getInputter_details().getSignature_details().setSign_status("N");
					}

				} else {
					reg.getInputter_details().getSignature_details().setSign_status("N");
				}

			}
            						
			reg.getInputter_details().setRevision_info(new RevisionInfo());
											
			reg.getInputter_details().getRevision_info().setRevision_no("No");
				 
			reg.getInputter_details().getRevision_info().setRevision_used_date("No");
				             
			reg.getInputter_details().getRevision_info().setRevision_exp_date("No");
					
			reg.getInputter_details().setVerification_info(new VerficationInfo());
			
			reg.getInputter_details().getVerification_info().setVerification_no("No");
			
			reg.getInputter_details().getVerification_info().setVerification_used_date("No");
			
			reg.getInputter_details().getVerification_info().setVerification_exp_date("No");
			
						
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
				IDList.add(ipDtl);
               
				Collections.sort(IDList, compareSequence);
			}
           
			responseBean.getSigner_details().setSigner_list(IDList);

		}
		
		 	if(requestData.getRole().equalsIgnoreCase("SG")){
			
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
                	/*ipDtl.setVersion_no(inp.getVersion_no());
                	ipDtl.setSequence(inp.getSequence());
                	ipDtl.setKtp_no(inp.getKtp_no());
                	ipDtl.setKtp_no_masked(inp.getKtp_no_masked());
                	ipDtl.setNpwp_no(inp.getNpwp_no());
                	ipDtl.setNpwp_no_masked(inp.getNpwp_no_masked());
                	ipDtl.setAddress(inp.getAddress());
                	ipDtl.setAddress_masked(inp.getAddress_masked());
                	ipDtl.setEmail(inp.getEmail());
                	ipDtl.setEmail_masked(inp.getEmail_masked());
                	ipDtl.setPhone(inp.getPhone());
                	ipDtl.setPhone_masked(inp.getPhone_masked());
                	ipDtl.setGender(inp.getGender());
                	ipDtl.setGender_masked(inp.getGender_masked());
                	ipDtl.setBirth_place(inp.getBirth_place());
                	ipDtl.setBirth_place_masked(inp.getBirth_place_masked());
                	ipDtl.setBirth_date(inp.getBirth_date());
                	ipDtl.setBirth_date_masked(inp.getBirth_date_masked());
                	ipDtl.setProvince(inp.getProvince());
                	ipDtl.setProvince_masked(inp.getProvince_masked());
                	ipDtl.setCity(inp.getCity());
                	ipDtl.setCity_masked(inp.getCity_masked());
                	ipDtl.setKtp_file_name(inp.getKtp_file_name());
                	ipDtl.setKtp_file_ext(inp.getKtp_file_ext());
                	ipDtl.setKtp_photo(inp.getKtp_photo());
                	ipDtl.setNpwp_file_name(inp.getNpwp_file_name());
                	ipDtl.setNpwp_file_ext(inp.getNpwp_file_ext());
                	ipDtl.setNpwp_photo(inp.getNpwp_photo());
*/                	ipDtl.setSigner_own("Y");
				}
              /*  if(inp.getKtp_no().equals(requestBean.getKtpNo())){
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

            //    }
                
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
	
	Comparator<InputterDetails> compareSequence = new Comparator<InputterDetails>() {
		@Override
		public int compare(InputterDetails o1, InputterDetails o2) {
			return o1.getSequence()-o2.getSequence();
		}
	};

	
	
}

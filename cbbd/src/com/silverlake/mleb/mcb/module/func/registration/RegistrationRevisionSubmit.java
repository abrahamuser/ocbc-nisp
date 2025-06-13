package com.silverlake.mleb.mcb.module.func.registration;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.silverlake.mleb.mcb.bean.SignerList;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.RegCacheData;
import com.silverlake.mleb.mcb.entity.dao.RegCacheDao;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.registration.DocumentDetails;
import com.silverlake.mleb.mcb.module.vc.registration.InputterDetails;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationData;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationInquiryResponseData;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationSubmitRevisionRequestData;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationSubmitRevisionResponseData;
import com.silverlake.mleb.mcb.module.vc.registration.RevisionInfo;
import com.silverlake.mleb.mcb.module.vc.registration.SignerDetails;
import com.silverlake.mleb.mcb.module.vc.registration.VerficationInfo;


/**
 * Purpose: To Submit Revision
 * Omni Web Services:
 * others/velocity-registration/revision/submit
 *   
 */
@Service
public class RegistrationRevisionSubmit extends CacheSessionFuncServices<ObRegistrationRequest, ObRegistrationResponse, ObRegistrationeUserSessionCache>{
	private static Logger log = LogManager.getLogger(RegistrationRevisionSubmit.class);
	
	@Autowired
	RegCacheDao regCacheDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean, ObRegistrationeUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
		RegistrationSubmitRevisionRequestData requestData = new RegistrationSubmitRevisionRequestData();
		RegistrationSubmitRevisionResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<RegistrationSubmitRevisionResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.REGISTRATION_SUBMIT_REVISION, 
				requestData, 
				RegistrationSubmitRevisionResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		
		
		processResponse(locale, requestData, responseData, requestBean, responseBean);
		
	
	}
	
	private void processRequest(String locale, RegistrationSubmitRevisionRequestData requestData, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean) throws Exception{
		
		String deviceId = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getDeviceId();
		String deviceOS = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getOs();
		String deviceType = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getModel();
		
		requestData.setRole(requestBean.getRole());
		requestData.setRecord_id(requestBean.getRecord_id());
		requestData.setVersion_no(requestBean.getVersion_no());
		requestData.setRegistration_no(requestBean.getRegistration_no());
		requestData.setRevision_no(requestBean.getRevision_no());
		
		if(requestBean.getRole().equalsIgnoreCase("IN")){
			
			InputterDetails inp = new InputterDetails();
			
			inp.setName(requestBean.getName());
			inp.setKtp_no(requestBean.getKtp_no());
			inp.setAddress(requestBean.getAddress());
			inp.setEmail(requestBean.getEmail());
			inp.setPhone(requestBean.getPhone());
			
			requestData.setUser_details(inp);
			
			
			RegCacheData regCacheData =(RegCacheData) regCacheDao.findByID(RegCacheData.class, requestBean.getRecord_id());
			ObjectInputStream objectIn = new ObjectInputStream(
					new ByteArrayInputStream(regCacheData.getRegObject()));
			RegistrationInquiryResponseData inquiryResponseData = (RegistrationInquiryResponseData)objectIn.readObject();
			
			RegistrationData reg = inquiryResponseData.getRegistration_data();
		
			requestData.setDocument_details_list(reg.getDocument_details_list());
			
			if(regCacheData.getSignerObject() == null){
				requestData.setSigner_details(new SignerDetails());
				requestData.getSigner_details().setSignature_flow(requestBean.getSignature_flow());
					
				List<InputterDetails> IDList = new ArrayList<InputterDetails>();
				
				for (InputterDetails inpu : reg.getSigner_details().getSigner_list()) {

					for (SignerList seq : requestBean.getSignerList()) {

						if (inpu.getKtp_no().equalsIgnoreCase(seq.getKtp_no())) {

						     	InputterDetails ipDtl = new InputterDetails();
								ipDtl.setUser_id(inpu.getUser_id());
								ipDtl.setVersion_no(inpu.getVersion_no());
								ipDtl.setSequence(seq.getSequence());
								ipDtl.setName(inpu.getName());
								ipDtl.setName_masked(inpu.getName_masked());
								ipDtl.setKtp_no(inpu.getKtp_no());
								ipDtl.setKtp_no_masked(inpu.getKtp_no_masked());
								ipDtl.setNpwp_no(inpu.getNpwp_no());
								ipDtl.setNpwp_no_masked(inpu.getNpwp_no_masked());
								ipDtl.setAddress(inpu.getAddress());
								ipDtl.setAddress_masked(inpu.getAddress_masked());
								ipDtl.setEmail(inpu.getEmail());
								ipDtl.setEmail_masked(inpu.getEmail_masked());
								ipDtl.setPhone(inpu.getPhone());
								ipDtl.setPhone_masked(inpu.getPhone_masked());
								ipDtl.setGender(inpu.getGender());
								ipDtl.setGender_masked(inpu.getGender_masked());
								ipDtl.setBirth_place(inpu.getBirth_place());
								ipDtl.setBirth_place_masked(inpu.getBirth_place_masked());
								ipDtl.setBirth_date(inpu.getBirth_date());
								ipDtl.setBirth_date_masked(inpu.getBirth_date_masked());
								ipDtl.setProvince(inpu.getProvince());
								ipDtl.setProvince_masked(inpu.getProvince_masked());
								ipDtl.setCity(inpu.getCity());
								ipDtl.setCity_masked(inpu.getCity_masked());
								ipDtl.setKtp_photo(inpu.getKtp_photo());
								ipDtl.setKtp_file_name(inpu.getKtp_file_name());
								ipDtl.setKtp_file_ext(inpu.getKtp_file_ext());
								ipDtl.setNpwp_photo(inpu.getNpwp_photo());
								ipDtl.setNpwp_file_name(inpu.getNpwp_file_name());
								ipDtl.setNpwp_file_ext(inpu.getNpwp_file_ext());
								
								ipDtl.setSignature_details(inpu.getSignature_details());
								ipDtl.setRevision_info(inpu.getRevision_info());
								ipDtl.setVerification_info(inpu.getVerification_info());
								
								IDList.add(ipDtl);
				               
								Collections.sort(IDList, compareSequence);
							
							requestData.getSigner_details().setSigner_list(IDList);
							
						}

					}

				}
			}else {
				
				        ObjectInputStream objectsign = new ObjectInputStream(
						new ByteArrayInputStream(regCacheData.getSignerObject()));
				        SignerDetails signerResponseData = (SignerDetails)objectsign.readObject();
				
				    requestData.setSigner_details(new SignerDetails());
					requestData.getSigner_details().setSignature_flow(requestBean.getSignature_flow());
					
					List<InputterDetails> IDList = new ArrayList<InputterDetails>();
					
					for (InputterDetails inpu : signerResponseData.getSigner_list()) {

						for (SignerList seq : requestBean.getSignerList()) {

							if (inpu.getKtp_no().equalsIgnoreCase(seq.getKtp_no())) {

							     	InputterDetails ipDtl = new InputterDetails();
									ipDtl.setUser_id(inpu.getUser_id());
									ipDtl.setVersion_no(inpu.getVersion_no());
									ipDtl.setSequence(seq.getSequence());
									ipDtl.setName(inpu.getName());
									ipDtl.setName_masked(inpu.getName_masked());
									ipDtl.setKtp_no(inpu.getKtp_no());
									ipDtl.setKtp_no_masked(inpu.getKtp_no_masked());
									ipDtl.setNpwp_no(inpu.getNpwp_no());
									ipDtl.setNpwp_no_masked(inpu.getNpwp_no_masked());
									ipDtl.setAddress(inpu.getAddress());
									ipDtl.setAddress_masked(inpu.getAddress_masked());
									ipDtl.setEmail(inpu.getEmail());
									ipDtl.setEmail_masked(inpu.getEmail_masked());
									ipDtl.setPhone(inpu.getPhone());
									ipDtl.setPhone_masked(inpu.getPhone_masked());
									ipDtl.setGender(inpu.getGender());
									ipDtl.setGender_masked(inpu.getGender_masked());
									ipDtl.setBirth_place(inpu.getBirth_place());
									ipDtl.setBirth_place_masked(inpu.getBirth_place_masked());
									ipDtl.setBirth_date(inpu.getBirth_date());
									ipDtl.setBirth_date_masked(inpu.getBirth_date_masked());
									ipDtl.setProvince(inpu.getProvince());
									ipDtl.setProvince_masked(inpu.getProvince_masked());
									ipDtl.setCity(inpu.getCity());
									ipDtl.setCity_masked(inpu.getCity_masked());
									ipDtl.setKtp_photo(inpu.getKtp_photo());
									ipDtl.setKtp_file_name(inpu.getKtp_file_name());
									ipDtl.setKtp_file_ext(inpu.getKtp_file_ext());
									ipDtl.setNpwp_photo(inpu.getNpwp_photo());
									ipDtl.setNpwp_file_name(inpu.getNpwp_file_name());
									ipDtl.setNpwp_file_ext(inpu.getNpwp_file_ext());
									
									ipDtl.setSignature_details(inpu.getSignature_details());
									ipDtl.setRevision_info(inpu.getRevision_info());
									ipDtl.setVerification_info(inpu.getVerification_info());
									
									IDList.add(ipDtl);
					               
									Collections.sort(IDList, compareSequence);
								
								requestData.getSigner_details().setSigner_list(IDList);
								
							}

						}

					}

				}
			
			requestData.setSigners_changed(requestBean.getSigners_changed());
		}
		
		if(requestBean.getRole().equalsIgnoreCase("SG")){
			
			/*ObRegistrationeUserSessionCache obRegistrationeUserSessionCache = (ObRegistrationeUserSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_INQUIRY_REGISTRATION.getId()));
			if(obRegistrationeUserSessionCache.getRegistrationInquiryResponseData().getRegistration_data() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Registration cache is not found");
				return;
			}
			
			
			
			RegistrationData reg = obRegistrationeUserSessionCache.getRegistrationInquiryResponseData().getRegistration_data();
			
			SignerDetails sign = reg.getSigner_details();
			
			for (InputterDetails input : sign.getSigner_list()) {
				
				if(input.getSigner_own().equalsIgnoreCase("Y")){
				
					inp.setSignature_details(input.getSignature_details());
					inp.setRevision_info(input.getRevision_info());
					inp.setVerification_info(input.getVerification_info());
					
					
				}
			}
			*/
			
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
			inp.setSequence(requestBean.getSequence());
			
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
			
			requestData.setUser_details(inp);
			
		}
		
		requestData.setDevice_id(deviceId);
		requestData.setDevice_type(deviceType);
		requestData.setDevice_os(deviceOS);
		
	}
	
	private void processResponse(String locale, RegistrationSubmitRevisionRequestData requestData, RegistrationSubmitRevisionResponseData responseData, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean) throws Exception{
		
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
	       
			if (requestBean.getRole().equalsIgnoreCase("IN")) {
				
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
			
			 	if(requestBean.getRole().equalsIgnoreCase("SG")){
				
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

	              //  }
	                
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

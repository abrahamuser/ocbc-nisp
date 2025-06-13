package com.silverlake.mleb.mcb.module.func.registration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObRegistrationRequest;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.bean.ObRegistrationeUserSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.RegCacheData;
import com.silverlake.mleb.mcb.entity.dao.RegCacheDao;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.registration.DocumentDetails;
import com.silverlake.mleb.mcb.module.vc.registration.InputterDetails;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationData;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationInquiryResponseData;
import com.silverlake.mleb.mcb.module.vc.registration.RevisionInfo;
import com.silverlake.mleb.mcb.module.vc.registration.SignerDetails;
import com.silverlake.mleb.mcb.module.vc.registration.VerficationInfo;

/**
 * Purpose: To add delete signer data
 * 
 *   
 */
@Service
public class AddDeleteSignerData extends CacheSessionFuncServices<ObRegistrationRequest, ObRegistrationResponse, ObRegistrationeUserSessionCache>{
	private static Logger log = LogManager.getLogger(AddDeleteSignerData.class);
		
	@Autowired
	RegCacheDao regCacheDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean, ObRegistrationeUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
		
		if(requestBean.getAction().equalsIgnoreCase("DISCARD")){
									
			RegCacheData cacheData = new RegCacheData();
			RegCacheData regCacheData =(RegCacheData) regCacheDao.findByID(RegCacheData.class, requestBean.getRecord_id());
			ObjectInputStream objectIn = new ObjectInputStream(
					new ByteArrayInputStream(regCacheData.getRegObject()));
			RegistrationInquiryResponseData inquiryResponseData = (RegistrationInquiryResponseData)objectIn.readObject();
			
			log.info("---signer----"+regCacheData.getSignerObject());
			if(regCacheData.getSignerObject() != null){
				regCacheData.setSignerObject(null);
				regCacheDao.updateEntity(regCacheData);
				
			}
			
		if(inquiryResponseData.getRegistration_data() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Registration cache is not found");
				return;
			}
			
			RegistrationData reg = inquiryResponseData.getRegistration_data();
			
			if(inquiryResponseData.getRole().equalsIgnoreCase("IN")){
				
				if(reg.getRegistration_status().equals("001") || reg.getRegistration_status().equals("005") || reg.getRegistration_status().equals("003")){
					if(inquiryResponseData.getAllow_cancel()){
						responseBean.setAllow_cancel("Y");
					}
				}
				
				if(reg.getRegistration_status().equals("003")) {
					if(inquiryResponseData.getAllow_edit() && inquiryResponseData.getRevisionNo() != null
							&& !inquiryResponseData.getRevisionNo().isEmpty()){
						responseBean.setAllow_edit("Y");
					}
				}
			}
			
	       if(inquiryResponseData.getRole().equalsIgnoreCase("SG")){
				
				if(reg.getRegistration_status().equals("001")){
					if(inquiryResponseData.getAllow_ack() && inquiryResponseData.getVerificationNo() != null
							&& !inquiryResponseData.getVerificationNo().isEmpty()){
						responseBean.setAllow_ack("Y");
					}
				}
				
				if(reg.getRegistration_status().equals("004")) {
					if(inquiryResponseData.getAllow_edit() && inquiryResponseData.getRevisionNo() != null
							&& !inquiryResponseData.getRevisionNo().isEmpty()){
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
	       responseBean.setRole(inquiryResponseData.getRole());
	       
			if (inquiryResponseData.getRole().equalsIgnoreCase("IN")) {
				
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
			
			
			if(inquiryResponseData.getRole().equalsIgnoreCase("SG")){
				
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
	                if(inp.getKtp_no().equals(inquiryResponseData.getKtpNo())){
	                	ipDtl.setSigner_own("Y");
					}
	              /*  if(inp.getKtp_no().equals(inquiryResponseData.getKtpNo())){
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
				

			
		}else {
		
		    RegCacheData regCacheData =(RegCacheData) regCacheDao.findByID(RegCacheData.class, requestBean.getRecord_id());
			ObjectInputStream objectIn = new ObjectInputStream(
					new ByteArrayInputStream(regCacheData.getRegObject()));
			RegistrationInquiryResponseData inquiryResponseData = (RegistrationInquiryResponseData)objectIn.readObject();
										
		if(regCacheData.getSignerObject() == null){
						
			if(requestBean.getAction().equalsIgnoreCase("D")){
				
				RegistrationData reg = inquiryResponseData.getRegistration_data();
				SignerDetails sign = reg.getSigner_details();
				Iterator<InputterDetails> signerDetls = sign.getSigner_list().iterator();
				
				while(signerDetls.hasNext()){
					InputterDetails inp = signerDetls.next();
					if(inp.getKtp_no().equals(requestBean.getKtp_no())){
						signerDetls.remove();
					}
				}
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oout = new ObjectOutputStream(baos);
				oout.writeObject(sign);
				oout.close();
				regCacheData.setSignerObject(baos.toByteArray());
				regCacheDao.updateEntity(regCacheData);
				
	
				responseBean.setSigner_details(new SignerDetails());
				responseBean.getSigner_details().setSignature_flow(sign.getSignature_flow());

				List<InputterDetails> IDList = new ArrayList<InputterDetails>();

				for (InputterDetails inp : sign.getSigner_list()) {

					InputterDetails ipDtl = new InputterDetails();

					ipDtl.setUser_id(inp.getUser_id());
					ipDtl.setName(inp.getName());
					ipDtl.setName_masked(inp.getName_masked());
					ipDtl.setKtp_no(inp.getKtp_no());
					IDList.add(ipDtl);

				}

				responseBean.getSigner_details().setSigner_list(IDList);
				
				
			}
			
	        if(requestBean.getAction().equalsIgnoreCase("A")){
				InputterDetails inputterDetails = new InputterDetails();
				RegistrationData reg = inquiryResponseData.getRegistration_data();
				SignerDetails sign = reg.getSigner_details();
				
               Iterator<InputterDetails> signerDetls = sign.getSigner_list().iterator();
				
				while(signerDetls.hasNext()){
					InputterDetails inp = signerDetls.next();
					if(inp.getKtp_no().equals(requestBean.getKtp_no()) || inp.getEmail().equalsIgnoreCase(requestBean.getEmail()) || inp.getPhone().equalsIgnoreCase(requestBean.getPhone())){
						responseBean.getObHeader().setStatusCode(MiBConstant.MIB_DUPLICATE);
						log.info("Duplicate error");
						return;
					}
				}
								
				inputterDetails.setName(requestBean.getName());
				inputterDetails.setKtp_no(requestBean.getKtp_no());
				inputterDetails.setNpwp_no(requestBean.getNpwp_no());
				inputterDetails.setGender(requestBean.getGender());
				inputterDetails.setBirth_place(requestBean.getBirth_place());
				inputterDetails.setBirth_date(requestBean.getBirth_date());
				inputterDetails.setAddress(requestBean.getAddress());
				inputterDetails.setCity(requestBean.getCity());
				inputterDetails.setProvince(requestBean.getProvince());
				inputterDetails.setEmail(requestBean.getEmail());
				inputterDetails.setPhone(requestBean.getPhone());
				inputterDetails.setKtp_file_name(requestBean.getKtp_file_name());
				inputterDetails.setKtp_file_ext(requestBean.getKtp_file_ext());
						
				String ktpBase64 = null;
				if(requestBean.getKtpPhoto() != null && requestBean.getIsBase64() != null && requestBean.getIsBase64()) {
					ktpBase64 = new String(requestBean.getKtpPhoto());
					inputterDetails.setKtp_photo(ktpBase64);
				}else if(requestBean.getKtpPhoto() != null){
					ktpBase64 = Base64.encode(requestBean.getKtpPhoto());
				//	ktpBase64 = Base64.encodeBase64String(requestBean.getKtpPhoto());
					inputterDetails.setKtp_photo(ktpBase64);
				}
				
				inputterDetails.setNpwp_file_name(requestBean.getNpwp_file_name());
				inputterDetails.setNpwp_file_ext(requestBean.getNpwp_file_ext());
				
				String npwpBase64 = null;
				if(requestBean.getNpwpPhoto() != null && requestBean.getIsBase64() != null && requestBean.getIsBase64()) {
					npwpBase64 = new String(requestBean.getNpwpPhoto());
					inputterDetails.setNpwp_photo(npwpBase64);
				}else if(requestBean.getNpwpPhoto() != null){
					npwpBase64 = Base64.encode(requestBean.getNpwpPhoto());
				//	npwpBase64 = Base64.encodeBase64String(requestBean.getNpwpPhoto());
					inputterDetails.setNpwp_photo(npwpBase64);
				}
										
				sign.getSigner_list().add(inputterDetails);
							
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oout = new ObjectOutputStream(baos);
				oout.writeObject(sign);
				oout.close();
				regCacheData.setSignerObject(baos.toByteArray());
				regCacheDao.updateEntity(regCacheData);
			
				
				responseBean.setSigner_details(new SignerDetails());
				responseBean.getSigner_details().setSignature_flow(sign.getSignature_flow());

				List<InputterDetails> IDList = new ArrayList<InputterDetails>();

				for (InputterDetails inp : sign.getSigner_list()) {

					InputterDetails ipDtl = new InputterDetails();

					ipDtl.setUser_id(inp.getUser_id());
					ipDtl.setName(inp.getName());
					ipDtl.setName_masked(inp.getName_masked());
					ipDtl.setKtp_no(inp.getKtp_no());
					IDList.add(ipDtl);

				}

				responseBean.getSigner_details().setSigner_list(IDList);

			}
		
		} else {
			
			ObjectInputStream objectsign = new ObjectInputStream(
					new ByteArrayInputStream(regCacheData.getSignerObject()));
			SignerDetails signerResponseData = (SignerDetails)objectsign.readObject();
			
			
          if(requestBean.getAction().equalsIgnoreCase("D")){
				
	
				Iterator<InputterDetails> signerDetls = signerResponseData.getSigner_list().iterator();
				
				while(signerDetls.hasNext()){
					InputterDetails inp = signerDetls.next();
					if(inp.getKtp_no().equals(requestBean.getKtp_no())){
						signerDetls.remove();
					}
				}
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oout = new ObjectOutputStream(baos);
				oout.writeObject(signerResponseData);
				oout.close();
				regCacheData.setSignerObject(baos.toByteArray());
				regCacheDao.updateEntity(regCacheData);
				
						
				responseBean.setSigner_details(new SignerDetails());
				responseBean.getSigner_details().setSignature_flow(signerResponseData.getSignature_flow());

				List<InputterDetails> IDList = new ArrayList<InputterDetails>();

				for (InputterDetails inp : signerResponseData.getSigner_list()) {

					InputterDetails ipDtl = new InputterDetails();

					ipDtl.setUser_id(inp.getUser_id());
					ipDtl.setName(inp.getName());
					ipDtl.setName_masked(inp.getName_masked());
					ipDtl.setKtp_no(inp.getKtp_no());
					IDList.add(ipDtl);

				}

				responseBean.getSigner_details().setSigner_list(IDList);

			}
			
	        if(requestBean.getAction().equalsIgnoreCase("A")){
	        		        		        	
               Iterator<InputterDetails> signerDetls = signerResponseData.getSigner_list().iterator();
				
				while(signerDetls.hasNext()){
					InputterDetails inp = signerDetls.next();
					if(inp.getKtp_no().equals(requestBean.getKtp_no()) || inp.getEmail().equalsIgnoreCase(requestBean.getEmail()) || inp.getPhone().equalsIgnoreCase(requestBean.getPhone())){
						responseBean.getObHeader().setStatusCode(MiBConstant.MIB_DUPLICATE);
						log.info("Duplicate error");
						return;
					}
				}
	        		        	
				InputterDetails inputterDetails = new InputterDetails();
		
				inputterDetails.setUser_id(requestBean.getUser_id());
				inputterDetails.setName(requestBean.getName());
				inputterDetails.setKtp_no(requestBean.getKtp_no());
				inputterDetails.setNpwp_no(requestBean.getNpwp_no());
				inputterDetails.setGender(requestBean.getGender());
				inputterDetails.setBirth_place(requestBean.getBirth_place());
				inputterDetails.setBirth_date(requestBean.getBirth_date());
				inputterDetails.setAddress(requestBean.getAddress());
				inputterDetails.setProvince(requestBean.getProvince());
				inputterDetails.setEmail(requestBean.getEmail());
				inputterDetails.setPhone(requestBean.getPhone());
				inputterDetails.setKtp_file_name(requestBean.getKtp_file_name());
				inputterDetails.setKtp_file_ext(requestBean.getKtp_file_ext());
				String ktpBase64 = null;
				if(requestBean.getKtpPhoto() != null && requestBean.getIsBase64() != null && requestBean.getIsBase64()) {
					ktpBase64 = new String(requestBean.getKtpPhoto());
					inputterDetails.setKtp_photo(ktpBase64);
				}else if(requestBean.getKtpPhoto() != null){
					ktpBase64 = Base64.encode(requestBean.getKtpPhoto());
				//	ktpBase64 = Base64.encodeBase64String(requestBean.getKtpPhoto());
					inputterDetails.setKtp_photo(ktpBase64);
				}
				inputterDetails.setNpwp_file_name(requestBean.getNpwp_file_name());
				inputterDetails.setNpwp_file_ext(requestBean.getNpwp_file_ext());
				String npwpBase64 = null;
				if(requestBean.getNpwpPhoto() != null && requestBean.getIsBase64() != null && requestBean.getIsBase64()) {
					npwpBase64 = new String(requestBean.getNpwpPhoto());
					inputterDetails.setNpwp_photo(npwpBase64);
				}else if(requestBean.getNpwpPhoto() != null){
					npwpBase64 = Base64.encode(requestBean.getNpwpPhoto());
				//	npwpBase64 = Base64.encodeBase64String(requestBean.getNpwpPhoto());
					inputterDetails.setNpwp_photo(npwpBase64);
				}
							
				signerResponseData.getSigner_list().add(inputterDetails);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oout = new ObjectOutputStream(baos);
				oout.writeObject(signerResponseData);
				oout.close();
				regCacheData.setSignerObject(baos.toByteArray());
				regCacheDao.updateEntity(regCacheData);
							

				responseBean.setSigner_details(new SignerDetails());
				responseBean.getSigner_details().setSignature_flow(signerResponseData.getSignature_flow());

				List<InputterDetails> IDList = new ArrayList<InputterDetails>();

				for (InputterDetails inp : signerResponseData.getSigner_list()) {

					InputterDetails ipDtl = new InputterDetails();

					ipDtl.setUser_id(inp.getUser_id());
					ipDtl.setName(inp.getName());
					ipDtl.setName_masked(inp.getName_masked());
					ipDtl.setKtp_no(inp.getKtp_no());
					IDList.add(ipDtl);

				}

				responseBean.getSigner_details().setSigner_list(IDList);

			}

			
		
		}
		
	  } 
	
	}
	
	Comparator<InputterDetails> compareSequence = new Comparator<InputterDetails>() {
		@Override
		public int compare(InputterDetails o1, InputterDetails o2) {
			return o1.getSequence()-o2.getSequence();
		}
	};
		
}

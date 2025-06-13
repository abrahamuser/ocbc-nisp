package com.silverlake.mleb.mcb.module.func.accountMgmt;



import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.net.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewRequest;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.common.MiBServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;







@Service
public class RetrieveStatementFile extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveStatementFile.class);
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObAccountOverviewResponse obResponse = new ObAccountOverviewResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {
			MiBServices mibService  = new MiBServices(dao);
			ObAccountOverviewRequest requestData = (ObAccountOverviewRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData vcAcctStatementRequest = new RequestData();
			VCService usrService = new VCService(appContext);
			vcAcctStatementRequest.setOrg_cd(orgId);
			vcAcctStatementRequest.setUsr_cd(usrId);
			vcAcctStatementRequest.setAcct_no(requestData.getAccountNo());
			vcAcctStatementRequest.setAcct_ccy(requestData.getAccountCcy());
			 	
			 
			String fromDate = requestData.getFromDate();
			String toDate = requestData.getToDate();
			String noDays = requestData.getNoDays();
			
			fromDate = fromDate==null?null:(fromDate.trim().length()==0?null:fromDate);
			toDate = toDate==null?null:(toDate.trim().length()==0?null:toDate);
			noDays = noDays==null?null:(noDays.trim().length()==0?null:noDays);
			if(noDays==null && toDate==null && fromDate==null)
			{
				//default history nodays
				noDays = MiBConstant.DEFAULT_STATEMENT_DAYS;
			}
			
			

			if(noDays!=null )
			{
				List<String> calDate = mibService.getStatementDate(noDays);
				fromDate = calDate.get(0);
				toDate = calDate.get(1);
			}

		 
			vcAcctStatementRequest.setStart_date(fromDate);
			vcAcctStatementRequest.setEnd_date(toDate);

			VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponseStatement = usrService.callOmniVC(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_STATEMENT_DOWNLOAD,vcAcctStatementRequest, new ResponseData(), requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
				
			if(vcResponseStatement.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcStatementResponse = new ResponseData();
				vcStatementResponse = vcResponseStatement.getData();
				String urlPath = vcStatementResponse.getDoc_path();
				
				URLConnection con = null;
				BufferedInputStream in = null;
				try {
					ByteArrayOutputStream bais = new ByteArrayOutputStream(); 
					URL url = new URL(urlPath);
					con = url.openConnection();
					con.setConnectTimeout(5000);
					con.setReadTimeout(60000);
					con.connect();
					int code = -1;
					if(con instanceof HttpsURLConnection){
						code = ((HttpsURLConnection)con).getResponseCode(); //set the HTTP method
					}else if(con instanceof HttpURLConnection){
						code = ((HttpURLConnection)con).getResponseCode(); //set the HTTP method
					}
					log.info("Http(s)URLConnection connection code: "+code);
					if (code==200){
						in = new BufferedInputStream(con.getInputStream());
						byte dataBuffer[] = new byte[1024];
						int bytesRead;
						while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
							bais.write(dataBuffer, 0, bytesRead);
						}
						byte[] file = bais.toByteArray();
						String base64String = Base64.encodeBase64String(file);
						obResponse.setStatementFileData(base64String);
						obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			        }else{
						obResponse.getObHeader().setStatusCode(MiBConstant.MCB_ERROR_DOWNLOADING_STATEMENT);
			        }
				} catch (IOException ioe) {
					log.catching(ioe);
					obResponse.getObHeader().setStatusCode(MiBConstant.MCB_ERROR_DOWNLOADING_STATEMENT);
				} finally{
					try{
						if(in != null){
							in.close();
						}
						if(con != null){
							if(con instanceof HttpsURLConnection){
								((HttpsURLConnection)con).disconnect();
							}else if(con instanceof HttpURLConnection){
								((HttpURLConnection)con).disconnect();
							}
						}
					}catch(Exception e){
						//Closing connection exeption shouldnt effect the process
						log.error("Unable to close connection due to "+e.getMessage());
					}
				}
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseStatement.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponseStatement.getResponse_desc());
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

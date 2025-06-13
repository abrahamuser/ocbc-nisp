package com.silverlake.mleb.mcb.module.func.accountMgmt;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.net.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAccountOverviewRequest;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.func.payment.PaymentInquiryBiller;
import com.silverlake.mleb.mcb.module.vc.accountManagement.EstatementDownloadRequestData;
import com.silverlake.mleb.mcb.module.vc.accountManagement.EstatementDownloadResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: To  get soft copy (in pdf format) of estatement from selected account.
 * 
 * Omni Web Services:
 *acct_mgmt/estatement/download
 *
 * @author Hemanth
 *
 */
@Service
public class EstatementDownload extends SessionFuncServices<ObAccountOverviewRequest, ObAccountOverviewResponse> {
	private static Logger log = LogManager.getLogger(PaymentInquiryBiller.class); 

    @Override
    public void processInternal(String locale, String sessionId, String trxId, ObAccountOverviewRequest requestBean, ObAccountOverviewResponse responseBean,VCGenericService vcService) throws Exception {
    	    	    	
    	EstatementDownloadRequestData requestData = new EstatementDownloadRequestData();
    	EstatementDownloadResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<EstatementDownloadResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.ESTATEMENT_DOWNLOAD, 
				requestData, 
				EstatementDownloadResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
	    
    }
   	

	private void processRequest(EstatementDownloadRequestData requestData, ObAccountOverviewRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAcct_no(requestBean.getAcct_no());
		requestData.setAcct_ccy(requestBean.getAcct_ccy());
		requestData.setPeriode(requestBean.getPeriode());
		requestData.setDevice_type(requestBean.getObDevice().getModel());
		requestData.setDevice_os(requestBean.getObDevice().getType()+" "+requestBean.getObDevice().getOs());
		}
		
	
	private void processResponse(EstatementDownloadResponseData responseData, ObAccountOverviewResponse responseBean) throws Exception{
		
		String urlPath = responseData.getDoc_path();

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
			if (con instanceof HttpsURLConnection) {
				code = ((HttpsURLConnection) con).getResponseCode(); // set the HTTP method											// HTTP
			} else if (con instanceof HttpURLConnection) {
				code = ((HttpURLConnection) con).getResponseCode();  // set the HTTP method	
			}
			log.info("Http(s)URLConnection connection code: " + code);
			if (code == 200) {
				in = new BufferedInputStream(con.getInputStream());
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					bais.write(dataBuffer, 0, bytesRead);
				}
				byte[] file = bais.toByteArray();
				String base64String = Base64.encodeBase64String(file);
				responseBean.setEstatementData(base64String);

			} else {
				responseBean.getObHeader().setStatusCode(MiBConstant.MCB_ERROR_DOWNLOADING_STATEMENT);
			}
		} catch (IOException ioe) {
			log.catching(ioe);
			responseBean.getObHeader().setStatusCode(MiBConstant.MCB_ERROR_DOWNLOADING_STATEMENT);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (con != null) {
					if (con instanceof HttpsURLConnection) {
						((HttpsURLConnection) con).disconnect();
					} else if (con instanceof HttpURLConnection) {
						((HttpURLConnection) con).disconnect();
					}
				}
			} catch (Exception e) {
				// Closing connection exeption shouldnt effect the process
				log.error("Unable to close connection due to " + e.getMessage());
			}
		}
	}		

}

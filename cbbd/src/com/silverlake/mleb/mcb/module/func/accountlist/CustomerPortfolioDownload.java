package com.silverlake.mleb.mcb.module.func.accountlist;

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

import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListRequest;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListResponse;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.func.payment.PaymentInquiryBiller;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerLoanDatav2;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioDownloadRequestData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioDownloadResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: To Download the customer portfolio for one of the  Loan, Unit Trust,Bonds  and SPT Statement.
 * 
 * Omni Web Services:
 *acct_mgmt/portfolio/download
 *
 * @author Hemanth
 *
 */
@Service
public class CustomerPortfolioDownload extends CacheSessionFuncServices<ObAccountListRequest, ObAccountListResponse, ObAccountListSessionCache> {
	private static Logger log = LogManager.getLogger(PaymentInquiryBiller.class); 

    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAccountListRequest requestBean, ObAccountListResponse responseBean, ObAccountListSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	if((requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_ESPT)) && (requestBean.getPeriodFrom() == null || requestBean.getPeriodFrom().isEmpty() || requestBean.getPeriodTo() == null || requestBean.getPeriodTo().isEmpty())){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Invalid Period");
			return;
		}
    	    	
    	CustomerPortfolioDownloadRequestData requestData = new CustomerPortfolioDownloadRequestData();
    	CustomerPortfolioDownloadResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<CustomerPortfolioDownloadResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.CUSTOMER_PORTFOLIO_DOWNLOAD, 
				requestData, 
				CustomerPortfolioDownloadResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
	    
    }
   	

	private void processRequest(CustomerPortfolioDownloadRequestData requestData, ObAccountListRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setCustomer_number(requestBean.getCustomerNumber());
		requestData.setProd_cd(requestBean.getProductCode());
		requestData.setPeriod_from(requestBean.getPeriodFrom());
		requestData.setPeriod_to(requestBean.getPeriodTo());
		if(requestBean.getCustomer_loan_data() != null && !requestBean.getCustomer_loan_data().isEmpty()){
		
			for(CustomerLoanDatav2 loanData : requestBean.getCustomer_loan_data()){
			CustomerLoanDatav2 loan = new CustomerLoanDatav2();
			loan.setAgreement_id(loanData.getAgreement_id());
			loan.setDocument_id(loanData.getDocument_id());
			loan.setCurrency_code(loanData.getCurrency_code());
			loan.setAmount_limit(loanData.getAmount_limit());
			loan.setOutstanding_amt(loanData.getOutstanding_amt());
			loan.setAvailable_amt(loanData.getAvailable_amt());
			loan.setPeriod_from(loanData.getPeriod_from());
			loan.setPeriod_to(loanData.getPeriod_to());
			
			requestData.setSummary(loan);
			
			}
			
		}
		requestData.setDevice_type(requestBean.getObDevice().getModel());
		requestData.setDevice_os(requestBean.getObDevice().getType()+" "+requestBean.getObDevice().getOs());
		}
		
	
	private void processResponse(CustomerPortfolioDownloadResponseData responseData, ObAccountListResponse responseBean) throws Exception{
		
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
				responseBean.setDocumentFileData(base64String);

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

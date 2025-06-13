package com.silverlake.mleb.mcb.module.func.transaction;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.net.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObNoteBean;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionReceiptRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionReceiptResponseData;

/**
 * Purpose: Get receipt of transaction
 * 
 * Omni Web Services:
 * transaction/receipt
 * 
 * @author Alex Loo
 *
 */
@Service
public class RetrieveTransactionReceipt extends SessionFuncServices<ObTransactionSummaryRequest, ObTransactionSummaryResponse>{
	private static Logger log = LogManager.getLogger(RetrieveTransactionReceipt.class);
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTransactionSummaryRequest requestBean, ObTransactionSummaryResponse responseBean, VCGenericService vcService) throws Exception {
		TransactionReceiptRequestData requestData = new TransactionReceiptRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setPymt_master_id(requestBean.getPymtMasterId());
		//Null from request, default to Y
		requestData.setIs_detail((requestBean.getIsRetrieveDetails() == null || requestBean.getIsRetrieveDetails())?"Y":"N");
		
		if(requestBean.getIs_additional_info() != null &&  !requestBean.getIs_additional_info().isEmpty()){
			requestData.setIs_additional_info(requestBean.getIs_additional_info());
		}else {
			requestData.setIs_additional_info("N");
		}
		
		
		
		VCResponse<TransactionReceiptResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_TXN_RECEIPT, 
				requestData, 
				TransactionReceiptResponseData.class, 
				true);
		
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		TransactionReceiptResponseData responseData = vcResponse.getData();
		processResponse(locale, responseData, responseBean);
	}
	
	private void processResponse(String locale, TransactionReceiptResponseData responseData, ObTransactionSummaryResponse responseBean) throws Exception{
		responseBean.setTrxNotesList(new ArrayList<ObNoteBean>());
		if(responseData.getDoc_path() != null){
			URLConnection con = null;
			BufferedInputStream in = null;
			try {
				ByteArrayOutputStream bais = new ByteArrayOutputStream(); 
				URL url = new URL(responseData.getDoc_path());
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
					responseBean.setStatementFileData(base64String);
		        }else{
		        	responseBean.getObHeader().setStatusCode(MiBConstant.MCB_ERROR_DOWNLOADING_STATEMENT);
		        }
			} catch (IOException ioe) {
				log.catching(ioe);
				responseBean.getObHeader().setStatusCode(MiBConstant.MCB_ERROR_DOWNLOADING_STATEMENT);
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
	}
}

package com.silverlake.mleb.pex.module.func;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fuzion.ws.account.endpoint.AccountSummaryResponse;
import com.fuzion.ws.account.endpoint.AccountVO;
import com.fuzion.ws.transaction.endpoint.FromAccountListRequest;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObPexRequest;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.ib.AccountServices.AccountService;
import com.silverlake.mleb.pex.module.services.PExServices;






@Service
public class CreationPex extends FuncServices  
{

	private static Logger log = LogManager.getLogger(CreationPex.class);
	
	
	@Autowired
	MLEBPExDAO dao;
	
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
	
		
		ObPexResponse pexResponse = new ObPexResponse();
		pexResponse.setObHeader(new ObHeaderResponse());
		pexResponse.setUserContext(new ObUserContext());
		
		
		try {
			
			
			ObPexRequest pexRequest = (ObPexRequest) arg0.getBDObject();
			String loginID = pexRequest.getUserContext().getLoginId();
			
			
			
			
			FromAccountListRequest accListReq = new FromAccountListRequest();
			accListReq.setTypeCode(PExConstant.FROM_ACC_LIST_IB_TYPE);
			/*TransactionServices transactionServices = new TransactionServices(muleContext);
			FromAccountListResponse fromAccountList = transactionServices.fuzionPerformFromAccountList(pexRequest.getUserContext(), accListReq, arg0.getTranxID());
			*/
			
			AccountService accService = new AccountService(appContext);
			AccountSummaryResponse resp = accService.fuzionPerformAccountSummary(pexRequest.getUserContext(), arg0.getTranxID(), false);
			
			//vietnam no need call debitcardlist
			//DebitCardListResponse debirCardList = accService.fuzionGetDebitCardList(pexRequest.getUserContext(), arg0.getTranxID());
			
			
			
			
			
			if(resp.getResponse().getStatusCode()== 1)
			{
			
				List<AccountVO> voList = resp.getAccountList();
				//List<DebitCardVO> debitList = debirCardList.getDebitCardList();
				
				
				//String cardNo = debitList.size()>0?debitList.get(0).getDebitCardNumber():"";
				
				
				pexResponse.setFromListAccount(new ArrayList());
				for(AccountVO acc : voList)
				{
					
					
					if(acc.isAllowTransactionFrom() && acc.isAllowDisplay()
							&& (!MiBConstant.MIB_PRODUCT_STATUS_CODE_FAIL.equalsIgnoreCase(acc.getProductStatusCode()))
							&& (!MiBConstant.MARCH_TYPE_DREAMJAR.equalsIgnoreCase(acc.getMachProductInd()))
							&& acc.getCurrencyCode().equalsIgnoreCase(MiBConstant.MIB_CURRENCYCODE_USD)){
							
						
					 ObAccountBean obFromAccountTypeList = new ObAccountBean();


						obFromAccountTypeList.setAccountName(acc.getAccountName()==null?"":acc.getAccountName()); 
						obFromAccountTypeList.setAccountNumber(acc.getAccountNumber()==null?"":acc.getAccountNumber()); 
						obFromAccountTypeList.setAccountDescription(acc.getAccountDescription()==null?"":acc.getAccountDescription());
						obFromAccountTypeList.setCurrencyCode(acc.getCurrencyCode()==null?"":acc.getCurrencyCode()); 
						obFromAccountTypeList.setProductTypeCode(acc.getProductTypeCode()==null?"":acc.getProductTypeCode()); 
						obFromAccountTypeList.setAllowDisplay(acc.isAllowDisplay()==null?false:acc.isAllowDisplay()); 
						obFromAccountTypeList.setAllowTransactionFrom(acc.isAllowTransactionFrom()==null?false:acc.isAllowTransactionFrom());
						obFromAccountTypeList.setAllowTransactionTo(acc.isAllowTransactionTo()==null?false:acc.isAllowTransactionTo()); 
						obFromAccountTypeList.setIslamic(acc.isIsIslamic()==null?false:acc.isIsIslamic());
						obFromAccountTypeList.setMach(acc.isIsMach()==null?false:acc.isIsMach());
						obFromAccountTypeList.setAvailableBalance(acc.getAvailableBalance()==null?new BigDecimal(0.00) :acc.getAvailableBalance().setScale(2, RoundingMode.HALF_UP)); 
						obFromAccountTypeList.setBankCode(acc.getAccountBranchCode());
						pexResponse.getFromListAccount().add(obFromAccountTypeList);	 
					 
				 }

				}
				
				
				if(pexResponse.getFromListAccount().size()>0)
				{
					PExServices pexService = new PExServices(dao);
					String firstAccount = pexService.getfirstDisplayAccount(voList);
					List<ObAccountBean> listVo = pexService.filterAccount(pexResponse.getFromListAccount(), pexRequest.getObUser().getCifNumber(),firstAccount);	
					pexResponse.setFromListAccount(listVo);
					
			
					
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
				}
				else
				{
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_FROM_ACCOUNT_NOT_FOUND);
				}
			
			}
			else
			{
				//if(resp.getResponse().getStatusCode()==0)
				//{
					pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+resp.getResponse().getErrorCode());
				//}
				/*	else
				{
					pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+debirCardList.getResponse().getErrorCode());
				}*/
			}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			log.error(this.getClass().toString(), e);
			pexResponse = new ObPexResponse();
			pexResponse.setObHeader(new ObHeaderResponse());
			pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
			pexResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(pexResponse);	
		return response;
	}


	/*@Override
	public void setMuleContext(MuleContext arg0) {
		// TODO Auto-generated method stub
		muleContext = arg0;
	}



	@Override
	public MuleContext getMuleContext() {
		// TODO Auto-generated method stub
		return muleContext;
	}*/



	
	
	
}
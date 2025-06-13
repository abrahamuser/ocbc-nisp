package com.silverlake.mleb.pex.module.func;



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
import com.silverlake.mleb.pex.bean.ObPexUserDetails;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.PexAccount;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.ib.AccountServices.AccountService;
import com.silverlake.mleb.pex.module.services.PExServices;






@Service
public class RetrievePexDirectAccount extends FuncServices
{

	private static Logger log = LogManager.getLogger(RetrievePexDirectAccount.class);
	
	
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
			String cif = pexRequest.getObUser().getCifNumber();
			String mobileNo = pexRequest.getObUser().getMobileNumber();
			FromAccountListRequest accListReq = new FromAccountListRequest();
			accListReq.setTypeCode(PExConstant.FROM_ACC_LIST_IB_TYPE);
			/*TransactionServices transactionServices = new TransactionServices(muleContext);
			FromAccountListResponse fromAccountList = transactionServices.fuzionPerformFromAccountList(pexRequest.getUserContext(), accListReq, arg0.getTranxID());
			*/
			
			
			if(null==pexRequest.getObUser().getMobileNumber() || pexRequest.getObUser().getMobileNumber().trim().length()==0)
			{
				pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_MOBILE_NUMBER_NOT_FOUND);
			}
			else
			{
			
				AccountService accService = new AccountService(appContext);
				AccountSummaryResponse resp = accService.fuzionPerformAccountSummary(pexRequest.getUserContext(), arg0.getTranxID(), false);
				
				
				if(resp.getResponse().getStatusCode()== 1)
				{
					
				
				
					List<AccountVO> voList = resp.getAccountList();
					
					
					pexResponse.setFromListAccount(new ArrayList());
					for(AccountVO accVo : voList)
					{
						ObAccountBean acc = new ObAccountBean();
						
						
						
						if(accVo.isAllowTransactionTo() && accVo.isAllowDisplay()
							&& MiBConstant.MIB_CURRENCYCODE_USD.equalsIgnoreCase(accVo.getCurrencyCode())
							&& !MiBConstant.MIB_PRODUCT_STATUS_CODE_FAIL.equalsIgnoreCase(accVo.getProductStatusCode())){
							
							ObAccountBean obFromAccountTypeList = new ObAccountBean();
							obFromAccountTypeList.setAccountName(accVo.getAccountName()); 
							obFromAccountTypeList.setAccountNumber(accVo.getAccountNumber()); 
							obFromAccountTypeList.setAccountDescription(accVo.getAccountDescription());
							obFromAccountTypeList.setCurrencyCode(accVo.getCurrencyCode()); 
							obFromAccountTypeList.setProductTypeCode(accVo.getProductTypeCode()); 
							obFromAccountTypeList.setAllowTransactionFrom(accVo.isAllowTransactionFrom()); 
							obFromAccountTypeList.setIslamic(accVo.isIsIslamic());
							obFromAccountTypeList.setMach(accVo.isIsMach());
							obFromAccountTypeList.setAvailableBalance(accVo.getAvailableBalance());
							obFromAccountTypeList.setAccountTypeCode(accVo.getProductTypeCode());
							pexResponse.getFromListAccount().add(obFromAccountTypeList);						
						
						}
						
					}
	
					PExServices pexServices = new PExServices(dao);
					String firstAccount = pexServices.getfirstDisplayAccount(voList);
					List<ObAccountBean> listVo  = pexServices.filterAccount(pexResponse.getFromListAccount(), pexRequest.getObUser().getCifNumber(),firstAccount);
					
					pexResponse.setFromListAccount(listVo);
					pexResponse.setMobileNumber(pexRequest.getObUser().getMobileNumber());
					
					
					
					
					
					
					List<PexAccount> hlbPexAccs = pexServices.getDirectPexUserActiveAcc(cif,mobileNo,pexResponse.getFromListAccount());
					List<ObPexUserDetails> pexAccounts = new ArrayList();
					
					if(hlbPexAccs.size()>0)
					{
						
						
						
						
						for(ObAccountBean pexAcc : pexResponse.getFromListAccount() )
						{
							if(pexAcc.getAccountNumber().equalsIgnoreCase(hlbPexAccs.get(0).getAccountNumber()))
							{
								ObPexUserDetails pexUserAcc = new ObPexUserDetails();
								pexUserAcc.setStatus(hlbPexAccs.get(0).getStatus());
								pexUserAcc.setCreateDate(hlbPexAccs.get(0).getCreationDate().toString());
								pexUserAcc.setMobileNumber(hlbPexAccs.get(0).getMobileNo());
								pexUserAcc.setPexAccount(new ObAccountBean());
								pexUserAcc.getPexAccount().setAccountDescription(hlbPexAccs.get(0).getAccountDesc());
								pexUserAcc.getPexAccount().setAccountName(hlbPexAccs.get(0).getAccountName());
								pexUserAcc.getPexAccount().setAccountNumber(hlbPexAccs.get(0).getAccountNumber());
								pexUserAcc.getPexAccount().setProductCode(hlbPexAccs.get(0).getAccountType());
								pexResponse.setPexUserDetails(pexUserAcc);
							}
						}	
		
						
					}
					
					if(pexResponse.getFromListAccount().size()==0)
					{
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_FROM_ACCOUNT_NOT_FOUND);
					}
					else
					{
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
					}
					
					
	
				}
				else
				{
					pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+resp.getResponse().getErrorCode());
				}
			
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




/*	
	@Override
	public MuleContext getMuleContext() {
		// TODO Auto-generated method stub
		return muleContext;
	}


	@Override
	public void setMuleContext(MuleContext arg0) {
		// TODO Auto-generated method stub
		
		
		
		
		muleContext = arg0;
	}*/
	
	
	
	
}
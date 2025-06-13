package com.silverlake.mleb.pex.module.ib.AccountServices;



import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.fuzion.ws.account.endpoint.AccountSummaryResponse;
import com.fuzion.ws.account.endpoint.AccountVO;
import com.fuzion.ws.account.endpoint.DebitCardListResponse;
import com.fuzion.ws.account.endpoint.DebitCardVO;
import com.fuzion.ws.account.endpoint.EndpointResponseHeader;
import com.fuzion.ws.account.endpoint.EndpointUserContext;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.mleb.pex.module.common.IBWSService;
import com.silverlake.mleb.pex.util.EhcacheSession;
import com.silverlake.mleb.pex.util.PropertiesManager;

public class AccountService extends IBWSService
{

	private static Logger log = LogManager.getLogger(AccountService.class);
	EhcacheSession ehcahce = new EhcacheSession();

	private PropertiesManager pmgr = new PropertiesManager();
	public AccountService (ApplicationContext appContext)
	{
		this.appContext = appContext;
	}

	
	
	public AccountSummaryResponse fuzionPerformAccountSummary(ObUserContext userContext, String mleb_tranx_id, boolean cache) throws Exception{
		
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext = processAccountRequestParam(userContext,null,null,methodName,mleb_tranx_id);
		
		AccountSummaryResponse resp = new AccountSummaryResponse();
		if(null!=pmgr.getProperty("fuzion.mock") /*&& enpointUserContext.getLoginId().startsWith(pmgr.getProperty("fuzion.mock").toString())*/  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")   )
		{
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
			
			AccountVO accv01 = new AccountVO();
			accv01.setAccountNumber("101010000000962");
			accv01.setAccountName("lulala");
			accv01.setAllowDisplay(true);
			accv01.setAllowTransactionFrom(true);
			accv01.setAllowTransactionTo(true);
			accv01.setIsIslamic(false);
			accv01.setIsMach(false);
			accv01.setCurrencyCode("USD");
			accv01.setProductTypeCode("D");
			accv01.setProductStatusCode("1");
			accv01.setAvailableBalance(new BigDecimal("100000.00"));
			accv01.setAccountDescription("Current Account");
			resp.getAccountList().add(accv01);
			
			
			AccountVO accv02 = new AccountVO();
			accv02.setAccountNumber("111010000000587");
			accv02.setAccountName("lumama");
			accv02.setAllowDisplay(true);
			accv02.setAllowTransactionFrom(true);
			accv02.setAllowTransactionTo(true);
			accv02.setCurrencyCode("USD");
			accv02.setProductStatusCode("1");
			accv02.setIsIslamic(false);
			accv02.setIsMach(false);
			accv02.setProductTypeCode("S");
			accv02.setAvailableBalance(new BigDecimal("100000.00"));
			accv02.setAccountDescription("Saving Account");
			resp.getAccountList().add(accv02);
			
			
			
	
			
			
			/*resp.getAccountList().add(arg0)
			 <accountList>
             <accountDescription>Current Account</accountDescription>
             <accountName>CURRENT ACCOUNT</accountName>
             <accountNumber>00100000168</accountNumber>
             <allowDisplay>true</allowDisplay>
             <allowTransactionFrom>true</allowTransactionFrom>
             <allowTransactionTo>true</allowTransactionTo>
             <availableBalance>100.00</availableBalance>
             <currencyCode>MYR</currencyCode>
             <currentBalance>300.00</currentBalance>
             <equivalentAvailableBalance>100.00</equivalentAvailableBalance>
             <equivalentCurrentBalance>300.00</equivalentCurrentBalance>
             <isIslamic>false</isIslamic>
             <isMach>false</isMach>
             <productCode>SDA</productCode>
             <productStatusCode>A</productStatusCode>
             <productTypeCode>D</productTypeCode>
             <relationshipCode>INO</relationshipCode>
             <relationshipModeCode>E</relationshipModeCode>
          </accountList>*/
			
		}
		else
		{
			AccountWSConnection connection = new AccountWSConnection();
			resp = connection.getAccountPort().getAccountSummary(enpointUserContext);
			
		
		}
		processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);
		
		return resp; 
	}
	
	
	
	public DebitCardListResponse fuzionGetDebitCardList(ObUserContext userContext, String mleb_tranx_id) throws Exception{
			
			String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
			EndpointUserContext enpointUserContext = processAccountRequestParam(userContext,null,null,methodName,mleb_tranx_id);
			
			DebitCardListResponse resp = new DebitCardListResponse();
			if(null!=pmgr.getProperty("fuzion.mock") && enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString())  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")  )
			{
				resp.setResponse(new EndpointResponseHeader());
				DebitCardVO a = new DebitCardVO();
				a.setDebitCardNumber("123321");
				
				resp.getDebitCardList().add(a);
				resp.getResponse().setStatusCode(1);
			}
			else
			{
				AccountWSConnection connection = new AccountWSConnection();
				resp = connection.getAccountPort().getDebitCardList(enpointUserContext); 
			}
			
			
			processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);
			
			return resp; 
		}

}
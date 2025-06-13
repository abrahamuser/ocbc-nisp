package com.silverlake.mleb.ccmcb.module.mpos;
/*package com.silverlake.mleb.ccws.module.mpos;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;





import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.ccws.dispatcher.MlebDispatcher;
import com.silverlake.mleb.ccws.module.MiBServices;
import com.silverlake.mleb.ccws.util.UserLoginSession;
import com.silverlake.mleb.mib.bean.ObRequest;
import com.silverlake.mleb.mib.bean.ObResponse;

public class VerifySalesPaymentStatus extends MiBServices {


	public VerifySalesPaymentStatus(WebServiceContext wsContext) {
		super(wsContext);
		// TODO Auto-generated constructor stub
	}



	private static Logger log = LogManager.getLogger(VerifySalesPaymentStatus.class);
	private ObResponse obResponse;
	private String mlebTransactionID;
	
	UserLoginSession userloginsession = new UserLoginSession();
	MICBResponseBean rsBean = null;

	MlebDispatcher muleDispatcher = new MlebDispatcher();
	ObVerifySalesPaymentStatusRequest obrequest; 
	
	public void processData(String batchId,String traceId,String terminalId){
		
		obrequest = new ObVerifySalesPaymentStatusRequest();
		
		obrequest.setTerminalId(terminalId);
		obrequest.setTraceId(traceId);
		obrequest.setBatchId(batchId);

		obrequest.setUserContext(new ObUserContext());
		obrequest.getUserContext().setLoginId("MPOS");
	}
	
	public ObResponse getObResponse() {
		return obResponse;
	}



	public void setObResponse(ObResponse obResponse) {
		this.obResponse = obResponse;
	}

	@Override
	public ObRequest getBDObject() {
		// TODO Auto-generated method stub
		func_id = MposConstant.FUNC_MPOS_VARIFYSALESSTATUS;
		return obrequest;
	}

	@Override
	public void processResponse() {
		// TODO Auto-generated method stub
		
	}
	
}
*/
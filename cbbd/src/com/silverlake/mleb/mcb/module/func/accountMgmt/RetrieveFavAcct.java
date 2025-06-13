package com.silverlake.mleb.mcb.module.func.accountMgmt;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.util.MappingUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewRequest;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.bean.ObCorporateAccountOverview;
import com.silverlake.mleb.mcb.bean.ObDashboardInfoRequest;
import com.silverlake.mleb.mcb.bean.ObDashboardInfoResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObTaskListResponse;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.bean.vctransaction.ObVcTranxResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.CustomerState;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.func.RetrieveDashBoardInfo;
import com.silverlake.mleb.mcb.module.func.RetrieveFutureTranx;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFUnbindAck;
import com.silverlake.mleb.mcb.module.func.task.RetrieveTaskList;
import com.silverlake.mleb.mcb.module.vc.accountManagement.Inquiry;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.transaction.TranxRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TranxResponseData;
import com.silverlake.mleb.mcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.util.StringUtil;







@Service
public class RetrieveFavAcct extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveFavAcct.class);
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObDashboardInfoResponse obResponse = new ObDashboardInfoResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {
			
			ObDashboardInfoRequest requestData = (ObDashboardInfoRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			 
			RetrieveDashBoardInfo dashboardService = new RetrieveDashBoardInfo();
			obResponse = dashboardService.getFirst5FavAccountInfo(obResponse, orgId, usrId,requestData.getUserContext().getSessionId(), arg0.getTranxID(),appContext, ipAddress);
			
			
		} catch (Exception e) {
		
			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(obResponse);
		
		return response;
	}
	
	
	 
	 
}

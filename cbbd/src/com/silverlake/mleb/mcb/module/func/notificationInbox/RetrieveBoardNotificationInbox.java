package com.silverlake.mleb.mcb.module.func.notificationInbox;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRetrieveNotificationInboxRequest;
import com.silverlake.mleb.mcb.bean.ObRetrieveNotificationInboxResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.notification.BoardNotification;
import com.silverlake.mleb.mcb.module.vc.notification.RequestData;
import com.silverlake.mleb.mcb.module.vc.notification.ResponseData;


@Service
public class RetrieveBoardNotificationInbox extends FuncServices  
{
	private static Logger log = LogManager.getLogger(RetrieveBoardNotificationInbox.class);

	@Autowired
	CustomerDAO dao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired ApplicationContext appContext;

	public MICBResponseBean process(MICBRequestBean arg0) {
		
		MICBResponseBean response = new MICBResponseBean();
		ObRetrieveNotificationInboxResponse obResponse = new ObRetrieveNotificationInboxResponse();
		obResponse.setObHeader(new ObHeaderResponse());
		try {
			ObRetrieveNotificationInboxRequest requestData = (ObRetrieveNotificationInboxRequest) arg0.getBDObject();
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			//Call REQ OMNI BoardNotification
			VCService usrService = new VCService(appContext);
			RequestData vcBoardNotificationRequest = new RequestData();
			vcBoardNotificationRequest.setOrg_cd(orgId);
			vcBoardNotificationRequest.setUsr_cd(usrId);
			
			String pageSize = requestData.getPage_size()==null?"10":requestData.getPage_size();
			pageSize = pageSize.trim().length()==0?"10":pageSize;
			
			String pageNo = (String) (requestData.getPage_no()==null?"1":requestData.getPage_no());
			pageNo = pageNo.trim().length()==0?"1":pageNo;
			
			vcBoardNotificationRequest.setPage_size(pageSize);
			vcBoardNotificationRequest.setPage_no(pageNo);
			//Call RES OMNI BoardNotification 
			ResponseData vcBoardNotificationResponseData = new ResponseData();
			VCResponse vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.NOTIFICATION_BOARD_LIST,vcBoardNotificationRequest, vcBoardNotificationResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcBoardNotificationResponseData = (ResponseData) vcResponse.getData();
				if(vcBoardNotificationResponseData.getList_notif_board()!=null)
				{
					obResponse.setList_notif_board(new ArrayList());
					for(BoardNotification temp:vcBoardNotificationResponseData.getList_notif_board())
					{
						BoardNotification boardNotification = new BoardNotification();
						BeanUtils.copyProperties(temp, boardNotification);
						obResponse.getList_notif_board().add(boardNotification);
					}
					obResponse.setTotal_rows(vcBoardNotificationResponseData.getTotal_rows());
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_NOTIFICATION_FOUND);
				}
				
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
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

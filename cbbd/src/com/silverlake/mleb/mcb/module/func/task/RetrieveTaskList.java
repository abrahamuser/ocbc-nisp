package com.silverlake.mleb.mcb.module.func.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObTaskListRequest;
import com.silverlake.mleb.mcb.bean.ObTaskListResponse;
import com.silverlake.mleb.mcb.bean.TaskInfo;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCAsyncResponseBean;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.task_list.TaskCount;
import com.silverlake.mleb.mcb.module.vc.task_list.TaskListCountRequestData;
import com.silverlake.mleb.mcb.module.vc.task_list.TaskListCountResponseData;
import com.silverlake.mleb.mcb.module.vc.task_list.TaskStatusCount;

/**
 * This for dashboard usage, which allowed multiple transaction source to be queried.
 * 
 * @author Alex Loo
 */

@Service
public class RetrieveTaskList extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveTaskList.class);
	
	@Autowired
	CustomerDAO dao;
	
	
	
	@Autowired
	GeneralCodeDAO gnDao;
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		MICBResponseBean response = new MICBResponseBean();
		ObTaskListResponse obTaskListResponse = new ObTaskListResponse();
		obTaskListResponse.setObHeader(new ObHeaderResponse());
			
		try {
			ObTaskListRequest requestData = (ObTaskListRequest) arg0.getBDObject();
			List<String> trnxSourceList = getTrnxSourc(requestData.getTranxSourcelist());
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			TaskListCountRequestData taskListCountRequestData = processRequest(
					requestData.getObUser().getOrgId(), 
					requestData.getObUser().getLoginId(), 
					trnxSourceList);
			
			VCGenericService vcService = new VCGenericService(appContext, arg0.getTranxID(), requestData.getUserContext().getSessionId(), ipAddress);
			VCResponse<TaskListCountResponseData> vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.TASK_LIST_COUNT, 
					taskListCountRequestData, 
					TaskListCountResponseData.class, 
					true);
			
			processResponse(vcResponse, obTaskListResponse, arg0.getLocaleCode(), gnDao);
			
		} catch (Exception e) {
			log.info(this.getClass().toString(), e);
			obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obTaskListResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		response.setBDObject(obTaskListResponse);
		
		return response;
	}
	
	public VCAsyncResponseBean<TaskListCountRequestData, VCResponse> getTaskListResultAsync(ApplicationContext appContext, ObTaskListResponse obTaskListResponse, String orgId, String loginId, List<String> trnxSourceList, String sessionID, String mlebTrxId, String locale) throws Exception{		
		TaskListCountRequestData taskListCountRequestData = processRequest(orgId, loginId, trnxSourceList);
		
		VCGenericService vcService = new VCGenericService(appContext, mlebTrxId, sessionID, null);
		VCAsyncResponseBean<TaskListCountRequestData, VCResponse> vcAccessRetrictionResponse = vcService.callOmniVCAsync(
				VCMethodConstant.REST_METHODS.TASK_LIST_COUNT,
				taskListCountRequestData,
				true);
		
		return vcAccessRetrictionResponse;
	}
	
	public void getTaskListResultAsync(VCAsyncResponseBean<TaskListCountRequestData, VCResponse> vcAsyncResponseBean, ApplicationContext appContext, ObTaskListResponse obTaskListResponse, String sessionID, String mlebTrxId, String locale, GeneralCodeDAO gnlDao) throws Exception {
		VCGenericService vcService = new VCGenericService(appContext, mlebTrxId, sessionID, null);
		VCResponse<TaskListCountResponseData> vcResponse = vcService.getAscyncResponseData(
				VCMethodConstant.REST_METHODS.TASK_LIST_COUNT.getUri(), 
				vcAsyncResponseBean, TaskListCountResponseData.class);
		processResponse(vcResponse, obTaskListResponse, locale, gnlDao);
	}
	
	public TaskListCountRequestData processRequest(String orgId, String loginId, List<String> trnxSourceList) {
		TaskListCountRequestData taskListCountRequestData = new TaskListCountRequestData();
		taskListCountRequestData.setOrg_cd(orgId);
		taskListCountRequestData.setUsr_cd(loginId);
		taskListCountRequestData.setList_source(trnxSourceList);
		
		return taskListCountRequestData;
	}
	
	public void processResponse(VCResponse<TaskListCountResponseData> vcResponse, ObTaskListResponse obTaskListResponse, String locale, GeneralCodeDAO gnlDao) throws Exception {
		obTaskListResponse.getObHeader().setStatusCode(null);
		if(!vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)){
			obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
			obTaskListResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
		} else if(vcResponse.getData() == null){
			obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
			obTaskListResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
		}else{
			List<GeneralCode> gnTranxStatusCode = null;
			if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN)){
				gnTranxStatusCode = gnlDao.getAllTaskTranxStatusCode_IN();
			} else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
				gnTranxStatusCode = gnlDao.getAllTaskTranxStatusCode_CN();
			} else {
				gnTranxStatusCode = gnlDao.getAllTaskTranxStatusCode();
			}
			
			obTaskListResponse.setTaskInfoList(new ArrayList<TaskInfo>());
			TaskListCountResponseData responseData = vcResponse.getData();
			for(TaskStatusCount taskStatusCount:responseData.getList_task_count()){
				TaskInfo taskInfo = new TaskInfo();
				taskInfo.setTranxSource(taskStatusCount.getSource());
				if(taskStatusCount.getList_task_count_detail() !=null && taskStatusCount.getList_task_count_detail().size()>0){
					for(TaskCount tranxCount : taskStatusCount.getList_task_count_detail()){
						for(GeneralCode gn:gnTranxStatusCode){
							if(gn.getGnCode().equalsIgnoreCase(tranxCount.getStatus())){
								tranxCount.setStatus_desc(gn.getGnCodeDescription());
								break;
							}
						}
					}
				}
				taskInfo.setTaskCountList(taskStatusCount.getList_task_count_detail());
				obTaskListResponse.getTaskInfoList().add(taskInfo);
			}
			obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
		}
	}
	
	public List<String> getTrnxSourc(String trnxSrcList){
		List<String> resultList = new ArrayList<String>();
		//MN Manual
		//NB Non-Bulk File Upload
		//BLIDR Bulk File Upload IDR
		//BLFCY Bulk File Upload FCY
		//TON Etax Upload Online
		//TOF Etax Upload Batch
		//VA VA Member Upload
		if(null==trnxSrcList || trnxSrcList.trim().length()==0){
			// query all task 
			resultList.add("MN");
			resultList.add("NB");
			resultList.add("BLIDR");
			resultList.add("BLFCY");
			resultList.add("TON");
			resultList.add("TOF");
			resultList.add("VA");
			resultList.add("NT");
		}else {
			String trnxSrcTmp[] = trnxSrcList.split(",");
			
			for(String source:trnxSrcTmp){
				if(source.trim().length()>0){
					resultList.add(source);
				}
			}
		}
		
		return resultList;
	}



	
	
}
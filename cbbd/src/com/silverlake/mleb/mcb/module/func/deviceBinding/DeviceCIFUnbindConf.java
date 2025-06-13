package com.silverlake.mleb.mcb.module.func.deviceBinding;



import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingRequest;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingResponse;
import com.silverlake.mleb.mcb.bean.ObDeviceCifBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;

import comx.silverlake.mleb.mcb.entity.ExtDeviceProfile;






@Service
public class DeviceCIFUnbindConf extends FuncServices  
{

	private static Logger log = LogManager.getLogger(DeviceCIFUnbindConf.class);


	@Autowired
	CustomerDAO dao;

	@Autowired
	DeviceCIFDao custDao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired ApplicationContext appContext;


	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub



		MICBResponseBean response = new MICBResponseBean();
		ObDeviceBindingResponse devResponse = new ObDeviceBindingResponse();
		devResponse.setObHeader(new ObHeaderResponse());
		devResponse.setUserContext(new ObUserContext());

		try {



			ObDeviceBindingRequest requestData = (ObDeviceBindingRequest) arg0.getBDObject();	
			//String cif = requestData.getCif();
			String deviceID = requestData.getDevId();

			DeviceProfile devPro = custDao.getCIFByDeviceID(deviceID);


			if(null!=devPro )
			{
				//bind device

				devResponse.setDeviceListing(new ArrayList());
				ObDeviceCifBean dvBean = new ObDeviceCifBean();
				dvBean.setCif(devPro.getCif());
				SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dvBean.setDateBind(sDF.format(devPro.getDateBind()));
				dvBean.setTransId(devPro.getTransId());
				dvBean.setDeviceStatus(devPro.getStatus());
				dvBean.setDevModel(devPro.getDeviceModel());
				dvBean.setDevOs(devPro.getOs());
				dvBean.setDevType(devPro.getDeviceType());
				dvBean.setDevId(devPro.getDeviceId());
				dvBean.setDevIsRooted(devPro.getIsRooted());
				dvBean.setBindBy(devPro.getBindBy());
				devResponse.getDeviceListing().add(dvBean);
				devResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);


			}
			else
			{
				//unbind rejected, no binded dev found
				devResponse.getObHeader().setStatusCode(MiBConstant.MIB_DEVAPI_BINDREJECTED);
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"


			log.info(this.getClass().toString(), e);
			devResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			devResponse.getObHeader().setStatusMessage(e.getMessage());

		}
		devResponse.getObHeader().setId(arg0.getTranxID());
		response.setBDObject(devResponse);

		return response;
	}






}
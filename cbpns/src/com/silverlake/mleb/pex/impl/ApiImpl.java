package com.silverlake.mleb.pex.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.api.ApiInterface;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.listerner.MuleDispatcher;
import com.silverlake.mleb.pex.listerner.PExSolution;




@Service("ApiImpl")
//@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class ApiImpl implements ApiInterface
{

	@Autowired ApplicationContext appContext;
	@Autowired PExSolution mibSolution;
	
	
	@Override
	public MICBResponseBean processSend(MICBRequestBean object) {
		//PExSolution mibSolution = (PExSolution) appContext.getBean("PExSolution");
		boolean mibResp = false;
		if(null==object.getTranxID())
		{
			mibResp = true;
			object.setTranxID(UUID.randomUUID().toString());
		}
		
		MICBResponseBean rs = mibSolution.onCall(object);
		//MICBResponseBean rs= new MICBResponseBean();
		if(mibResp)
		{
			rs.setTranxID(object.getTranxID());
			rs.setResponseCode("00000");
			rs.setResponseMessage("MIB");
		}
		//rs= new MICBResponseBean();rs.setResponseCode("99999");
		return rs;
	}

	
	
	
	
	

}
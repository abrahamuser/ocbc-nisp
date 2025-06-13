package com.silverlake.mleb.mcb.impl;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;





import com.silverlake.micb.core.api.ApiInterface;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.listerner.MCBSolution;
import com.silverlake.mleb.mcb.listerner.MiBSolution;




@Service("ApiImpl")
//@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class ApiImpl implements ApiInterface
{
	//@Autowired MiBSolution mibSolution;
	@Autowired MCBSolution mcbSolution;
	
	@Autowired ApplicationContext appContext;
	@Override
	public MICBResponseBean processSend(MICBRequestBean object) {
		//MiBSolution mibSolution = (MiBSolution) appContext.getBean("MiBSolution");
		boolean mibResp = false;
		if(null==object.getTranxID())
		{
			mibResp = true;
			object.setTranxID(UUID.randomUUID().toString());
		}
		
		MICBResponseBean rs = mcbSolution.onCall(object);
		//MICBResponseBean rs= new MICBResponseBean();
		if(mibResp)
		{
			rs.setTranxID(object.getTranxID());
			rs.setResponseCode("00000");
			rs.setResponseMessage("MCB");
		}
		//rs= new MICBResponseBean();rs.setResponseCode("99999");
		return rs;
	}

	
	
	
	
	

}
package com.silverlake.mleb.mcb.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.McbConf;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.entity.dao.MessagePropertiesDAO;
import com.silverlake.mleb.mcb.module.common.MiBServices;

@Service
public class RefreshMsgProperties
{
	private static Logger log = LogManager.getLogger(RefreshMsgProperties.class);
	
	
	@Autowired
	MLEBMIBDAO dao;
	
	@Autowired
	MessagePropertiesDAO msgdao;
	
	@Autowired TaskRenewSession renewSession;
	
	//@Scheduled(fixedDelay=(60000*5)) //Run every 5 min
	public void process()
	{
		MiBServices mibService = new MiBServices(dao);
		McbConf mibConf = mibService.getMcBConf();
		
		if(null!=mibConf.getDescription() && mibConf.getDescription().startsWith(MiBConstant.RESET_ERROR_MSG))
		{
			log.info("RESET MSG : Check reload msg....");
			//mibService.updateReloadMsgCheck(mibConf.getDescription(), mibConf.getUpdateDate(),msgdao);
		}
		
		
	}
	
	
}

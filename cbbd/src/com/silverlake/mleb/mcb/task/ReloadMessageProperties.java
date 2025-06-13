package com.silverlake.mleb.mcb.task;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.McbConf;
import com.silverlake.mleb.mcb.entity.MessageProperties;
import com.silverlake.mleb.mcb.entity.MessagePropertiesI18n;
import com.silverlake.mleb.mcb.entity.dao.ConfDao;
import com.silverlake.mleb.mcb.entity.dao.MessagePropertiesDAO;
import com.silverlake.mleb.mcb.util.MessageManager;
import com.silverlake.mleb.mcb.util.PropertiesManager;

@Service
public class ReloadMessageProperties
{
	private static Logger log = LogManager.getLogger(ReloadMessageProperties.class);

	@Autowired ConfDao confDao;
	@Autowired MessagePropertiesDAO msgdao;
	
	private MessageManager msgPro = new MessageManager();
	
	public void reloadMsg()
	{
		//LimKN 11-Nov-2017
		// Need to handle the case when the ccmsg.properties or ccmsg_in.properties is empty
		Properties en_pro = msgPro.getDefaultProperties();
		Properties in_pro = msgPro.getINProperties();
		Properties cn_pro = msgPro.getCNProperties();
		if(en_pro.isEmpty() || in_pro.isEmpty() || cn_pro.isEmpty())
		{
			List<MessagePropertiesI18n> en_db = msgdao.retrieveAllMessageByLocale("en");
			List<MessagePropertiesI18n> in_db = msgdao.retrieveAllMessageByLocale("in");
			List<MessagePropertiesI18n> cn_db = msgdao.retrieveAllMessageByLocale("cn");
			
			String newline = System.getProperty("line.separator"); 
			for(MessagePropertiesI18n msg:en_db)
			{
				
				String valueMsg = msg.getErrMessage();
				valueMsg = valueMsg==null?"":valueMsg;
				valueMsg = valueMsg.replace("\\n", newline);
				valueMsg = valueMsg.replace("\\", ""); 
				en_pro.setProperty(msg.getErrMessageCode(), valueMsg);
			}
			msgPro.updateDefaultPropertiesFile();
			
			for(MessagePropertiesI18n msg:in_db)
			{
				String valueMsg = msg.getErrMessage();
				valueMsg = valueMsg==null?"":valueMsg;
				valueMsg = valueMsg.replace("\\n", newline);
				valueMsg = valueMsg.replace("\\", ""); 
				in_pro.setProperty(msg.getErrMessageCode(), valueMsg);
			}
			msgPro.updateINPropertiesFile();

			for(MessagePropertiesI18n msg:cn_db)
			{
				String valueMsg = msg.getErrMessage();
				valueMsg = valueMsg==null?"":valueMsg;
				valueMsg = valueMsg.replace("\\n", newline);
				valueMsg = valueMsg.replace("\\", ""); 
				cn_pro.setProperty(msg.getErrMessageCode(), valueMsg);
			}
			msgPro.updateCNPropertiesFile();
		}
		else
		{
			McbConf mibConf = confDao.getMibConf();
			
			if(null!=mibConf && null!=mibConf.getDescription() && mibConf.getDescription().startsWith(MiBConstant.RESET_ERROR_MSG))
			{
				log.info("RESET MSG : Check reload msg status ...");
				int clusterServer = 1;
				PropertiesManager propertiesManager = new PropertiesManager();
				if(propertiesManager.getProperty("server.cluster.count") != null){
					try{
						clusterServer = Integer.parseInt(propertiesManager.getProperty("server.cluster.count"));
					}catch(Exception e){
						log.error("Error while extracting value of server.cluster.count from properites. Using default cluster server count of "+clusterServer);
					}
				}
				String hostname = "";
				try{
					String ip = InetAddress.getLocalHost().getHostAddress().toString();

					hostname = InetAddress.getLocalHost().getHostName();

				} catch (UnknownHostException e){
					log.info("Update message hostname exception ", e);
				}

				String[] descUpdate = mibConf.getDescription().split(",");
				List<String> descList = new ArrayList<String>();
				for (String desc : descUpdate)
				{
					descList.add(desc);
				}

				if (!descList.contains(hostname))
				{
					// update properties file
					log.info("RESET MSG : Update Properties [" + hostname + "]["+descList.size()+"/"+clusterServer+"]");
					log.info("---------- START updatePropertiesMessage");
					
					// ADD , MODIFY , DELETE
					List<MessagePropertiesI18n> messageList = msgdao.getModifyMessage();
					if (descList.size() >= (clusterServer)){//Last cluster server to update
						int rs = confDao.resetReloadMessageStatus(mibConf.getUpdateDate(), mibConf.getDescription(), "UPDATED");
						if (rs > 0){
							reloadMsg(messageList, en_pro, in_pro, cn_pro);
							// update message database
							updateDBMessageStatus(messageList);
						}
					} else {
						int rs = confDao.resetReloadMessageStatus(mibConf.getUpdateDate(), mibConf.getDescription(), mibConf.getDescription() + "," + hostname);
						if (rs > 0){
							reloadMsg(messageList, en_pro, in_pro, cn_pro);
						}
					}
					
					log.info("---------- END updatePropertiesMessage");
				}else{
					log.info("Reload of message is not required due to updated before.");
				}
			}else if(mibConf!=null && null!=mibConf.getDescription() && !mibConf.getDescription().startsWith(MiBConstant.RESET_ERROR_MSG)){
				log.info("Reload of message is not required due to flag value ["+mibConf.getDescription()+"].");
			}else{
				log.info("Reload of message unable to determine due to empty flag value.");
			}
		}
	}
	
	public void reloadMsg(List<MessagePropertiesI18n> messageList, Properties en_pro, Properties in_pro, Properties cn_pro){
		boolean modify_en = false;
		boolean modify_vn = false;
		boolean modify_cn = false;
		log.info("RESET MSG Size :" + messageList.size());
		for (MessagePropertiesI18n s : messageList)
		{
			String keyMsg = s.getErrMessageCode();
			String valueMsg = s.getErrMessage();

			log.info("RESET MSG data : " + s.getMessageId() + " - " + s.getErrMessageCode() + " - "
					+ s.getMessageProperties().getStatus());
			if (s.getMessageProperties().getStatus().equalsIgnoreCase(MiBConstant.MSG_ADDED)
					|| s.getMessageProperties().getStatus()
							.equalsIgnoreCase(MiBConstant.MSG_MODIFY))
			{
				// added & modify

				String newline = System.getProperty("line.separator");
				valueMsg = valueMsg.replace("\\n", newline);
				valueMsg = valueMsg.replace("\\", "");

				if (s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_EN))
				{
					en_pro.setProperty(keyMsg, valueMsg);
					modify_en = true;
				} else if(s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_CN))
				{
					cn_pro.setProperty(keyMsg, valueMsg);
					modify_cn = true;
				} else 
				{
					in_pro.setProperty(keyMsg, valueMsg);
					modify_vn = true;
				}

			} else
			{
				// delete
				if (s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_EN))
				{
					en_pro.remove(keyMsg);
					modify_en = true;
				} else if(s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_CN))
				{
					cn_pro.remove(keyMsg);
					modify_cn = true;
				} else 
				{
					in_pro.remove(keyMsg);
					modify_vn = true;
				}
			}

		}

		// update to properties file
		if (modify_en)
		{
			msgPro.updateDefaultPropertiesFile();
		}

		if (modify_vn)
		{
			msgPro.updateINPropertiesFile();
		}

		if (modify_cn)
		{
			msgPro.updateCNPropertiesFile();
		}
	}
	
	public void updateDBMessageStatus(List<MessagePropertiesI18n> messageList)
	{

		log.info("---------- START updateDBMessageStatus");

		for (MessagePropertiesI18n m18n : messageList)
		{
			if (!m18n.getMessageProperties().getStatus()
					.equalsIgnoreCase(MiBConstant.MSG_DELETED))
			{
				MessageProperties messageProperties = msgdao.findByMainMsgCode(m18n.getErrMessageCode());
				messageProperties.setStatus(MiBConstant.MSG_ACTIVE);
				msgdao.updateEntity(messageProperties);
			}
		}
		
		log.info("---------- END updateDBMessageStatus");
	}
}


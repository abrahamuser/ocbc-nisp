package com.silverlake.mleb.mcb.task;
/*package com.silverlake.mleb.mib.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mib.entity.PostalCode;
import com.silverlake.mleb.mib.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mib.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mib.entity.dao.PostalCodeDao;
import com.silverlake.mleb.mib.module.common.MiBServices;
import com.silverlake.mleb.mib.util.FTPUtil;
import com.silverlake.mleb.mib.util.PropertiesManager;

@Service
public class RefreshPostalCode {
	private static Logger log = LogManager.getLogger(RefreshPostalCode.class);

	@Autowired
	MLEBMIBDAO dao;

	@Autowired 
	ApplicationContext appContext;

	@Autowired
	GeneralCodeDAO generalCodeDao;

	@Autowired
	PostalCodeDao postalCodeDao;

	private PropertiesManager pmgr = new PropertiesManager();

	//@Scheduled(fixedDelay=(60000*60)) //Run every 60 mins
	@Scheduled(cron="0 0 6 ? * MON")
	public void process()
	{
		log.info("----- START RefreshPostalCode");
		try{

			FTPUtil ftpUtil = new FTPUtil();
			String ftpUrl = pmgr.getProperty("ftp.url");
			int ftpPort = Integer.parseInt(pmgr.getProperty("ftp.port")==null?"21":pmgr.getProperty("ftp.port"));
			String username = pmgr.getProperty("ftp.username");
			String password = pmgr.getProperty("ftp.password");
			String dirPath = pmgr.getProperty("ftp.dir.path");
			String chkFileName = pmgr.getProperty("ftp.chk.file.name");
			String datFileName = pmgr.getProperty("ftp.dat.file.name");

			if(ftpUrl!=null && username!=null && password!=null 
					&& dirPath!=null && chkFileName!=null && datFileName!=null){

				FTPClient ftpClient = ftpUtil.readFTPFile(ftpUrl, ftpPort, username, password, dirPath);

				if(ftpClient!=null){
					List<PostalCode> lsPostalCode = ftpUtil.scanFile(ftpClient, chkFileName, datFileName);
					log.info("List Postal Code Size from FTP :: " + lsPostalCode.size());
					if(lsPostalCode!=null && lsPostalCode.size()>0){
						postalCodeDao.removePostalCode();
						for(PostalCode postalCode : lsPostalCode){
							postalCodeDao.insertEntity(postalCode);
						}
						log.info("Postal Code Insert Complete");
					}
				}
			}
		}
		catch(Exception e)
		{
			log.info("EXCEPTION PROCESSING POSTALCODE LISTS :: "+e);
			e.printStackTrace();
		}
		log.info("----- END RefreshPostalCode");
	}
}
*/
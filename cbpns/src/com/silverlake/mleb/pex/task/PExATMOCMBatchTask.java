package com.silverlake.mleb.pex.task;





import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.ATMBatchFile;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBDAO;
import com.silverlake.mleb.pex.entity.dao.PEXDAO;
import com.silverlake.mleb.pex.server.socket.bean.OCM_UpdatePExStatusBatch;
import com.silverlake.mleb.pex.task.TaskRenewSession;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.EncryptionServices;
import com.silverlake.mleb.pex.util.FTPClientUtil;
import com.silverlake.mleb.pex.util.PropertiesManager;



@Service
public class PExATMOCMBatchTask
{
	private static Logger log = LogManager.getLogger(PExATMOCMBatchTask.class);

	private static final String KEYLOCK = "PEX_ATM_BATCH_UPDATE_STATUS_TASK";

	
	@Autowired MLEBDAO dao;
	@Autowired TaskRenewSession renewSession;
	@Autowired PEXDAO pexDao;
	
	@Scheduled(fixedDelay=60000)
	public void process()
	{
		try
		{
			PropertiesManager property = new PropertiesManager();
			String getProccessTime = property.getProperty("HLB.OCM.FTP.TIME")==null?"xx:xx":property.getProperty("HLB.OCM.FTP.TIME");
			
			if(checkCurrentProccessTime(getProccessTime))
			{
			
			
				boolean data = renewSession.checkTask(KEYLOCK);
				if(data)
				{
					Future<String>future  = renewSession.processTask(KEYLOCK);
					try
					{
						
						taskProcess();
					
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
	
					
					future.cancel(true);
					
					
				}
			}
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//taskProcess();
	}
	
	
	
	// No longer needed to FTP to OCM/EBS
	// EBS will FTP the file to our local.
//	@PostConstruct
//	public void initIt() throws Exception {
//		try
//		{	EncryptionServices en = new EncryptionServices();
//			PropertiesRealTimeManager property = new PropertiesRealTimeManager();
//			String ftpCheck = property.getProperty("HLB.OCM.FTP.CHECK");
//			if(null!=ftpCheck && ftpCheck.equalsIgnoreCase("true"))
//			{
//				String ftpIp = property.getProperty("HLB.OCM.FTP.IP");
//				String ftpPort = property.getProperty("HLB.OCM.FTP.PORT");
//				String ftpUsername = property.getProperty("HLB.OCM.FTP.username");
//				String ftpPassword = property.getProperty("HLB.OCM.FTP.password");
//				ftpPassword = new String(en.decrypt(String.valueOf(Hex.encodeHex((PExConstant.encryptionKey).getBytes())).getBytes(), Base64.decodeBase64(ftpPassword.getBytes())));
//				
//				Thread.sleep(2000);
//				log.info("-------------------- Initializing Check Start --------------------");
//				
//				FTPClientUtil ftpUtil = new  FTPClientUtil();
//				if(ftpPort.equalsIgnoreCase("22"))
//				{
//					log.info("-------------------- SFTP ["+ftpIp+"]-["+ftpUsername+"] --------------------");
//					ftpUtil.checkSecureConnection(ftpIp, ftpUsername, ftpPassword);
//					log.info("-------------------- SFTP Connection Established Successfully --------------------");
//				}
//				else
//				{
//					log.info("-------------------- FTP ["+ftpIp+"]-["+ftpUsername+"] --------------------");
//					ftpUtil.checkConnection(ftpIp, ftpUsername, ftpPassword);
//					log.info("-------------------- FTP Connection Established Successfully --------------------");
//				}
//				
//				
//				
//			}
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//			Thread.sleep(5000);
//		}
//	}
	
	
	/*public void taskProcess()
	{
		log.info("Initializing Schedule Task");
		
		
		
		
		
		try
		{
			PropertiesReloadManager pmgr = new PropertiesReloadManager();
			StringDataUtil stringUtil = new StringDataUtil();
			String serverDir = null==MiPayConstant.SERVERPATH?MiPayConstant.SERVERLOCALPATH:MiPayConstant.SERVERPATH;
//			String serverDir = "f:\\apps\\apache-tomcat-7.0.27_epay";
			String ftpIp = pmgr.getProperty("SCS.FTP.IP");
			String ftpPort = pmgr.getProperty("SCS.FTP.PORT");
			String ftpUsername = pmgr.getProperty("SCS.FTP.username");
			String ftpPassword = pmgr.getProperty("SCS.FTP.password");
			String ftpRemoteFilePath = pmgr.getProperty("SCS.FTP.TREG.FILE.PATH");
			
			
			
			
			FTPClientUtil ftpUtil = new  FTPClientUtil();
			Date currentDate = new Date();
			
			SimpleDateFormat sdfx = new SimpleDateFormat(MiPayConstant.FILEDATE); 
			SimpleDateFormat sdfFTP = new SimpleDateFormat(MiPayConstant.FILEFTPDATE); 
			String localFileTmp = serverDir+MiPayConstant.FILEDATADIR+"/SCS/TM"+sdfFTP.format(currentDate)+".dat";
			File theDir = new File(serverDir+MiPayConstant.FILEDATADIR+"/SCS");
			
			
			//rename remote file date name
			ftpRemoteFilePath = ftpRemoteFilePath.replaceAll("<date>", sdfFTP.format(currentDate));
			log.info("FTP IP : "+ftpIp);
			log.info("FTP USERNAME : "+ftpUsername);
			log.info("FTP RemoteFilePath : "+ftpRemoteFilePath);
			
			if (!theDir.exists())
			{
				theDir.mkdirs();
			}
			FileWriter fwrite = new FileWriter(localFileTmp,false);
			
			PrintWriter printW = new PrintWriter(fwrite);
		
		
			String db_Entry_Date =  "";
			String db_Terminal_ID = "";
			String db_Merchant_Id = "";
			String db_Description = "";
			String db_Draft_Capture = "";
			String db_status = "";
			String db_POS = "";
			String db_PIN_Capture_Code = "";
			String db_Country_Code = "";
			String db_Currency_Code = "";
			
			//capture row_id for update
			String row_ids_update = "";
			int countEntryReg = 0;
			
			List<TblMipayTerminalProfile>  terminalProfiles = miPayDao.selectQuery("SELECT * FROM "+TblMipayTerminalProfile.class.getAnnotation(Table.class).name()+" WHERE ftp_flag='"+MiPayConstant.TERMINAL_FTP_PENDINGFTPFLAG+"'",TblMipayTerminalProfile.class);
			
			for(TblMipayTerminalProfile mipayTerminalProfile : terminalProfiles)
			{
				log.info("Terminal ID : "+mipayTerminalProfile.getTerminalId());
				
				db_Entry_Date = sdfx.format(mipayTerminalProfile.getEntryDate());
				db_Terminal_ID = mipayTerminalProfile.getTerminalId();
				db_Merchant_Id = mipayTerminalProfile.getMerchantId();
				db_Description = mipayTerminalProfile.getDescription();
				
				db_status = mipayTerminalProfile.getStatus();
				
				db_status = db_status.substring(0,1);
				
				db_Draft_Capture = mipayTerminalProfile.getDraftCapture();
				db_POS = mipayTerminalProfile.getPos();
				db_PIN_Capture_Code = mipayTerminalProfile.getPinCaptureCode();
				db_Country_Code = mipayTerminalProfile.getCountryCode();
				db_Currency_Code = mipayTerminalProfile.getCurrencyCode();
				
				db_Draft_Capture = null==db_Draft_Capture?"Y":db_Draft_Capture;
				db_POS = null==db_POS?"03":db_POS;
				db_PIN_Capture_Code = null==db_PIN_Capture_Code?"04":db_PIN_Capture_Code;
				db_Country_Code = null==db_Country_Code?"   ":db_Country_Code;
				db_Currency_Code = null==db_Currency_Code?"   ":db_Currency_Code;
				
				
				
			
				db_Merchant_Id = stringUtil.getFulllength_frontBlank(11,db_Merchant_Id,"0");
				db_Description = stringUtil.getFulllength_frontBlank(25,""," ");
				
				
				String regEntry = db_Terminal_ID+db_Merchant_Id+db_Description+db_Draft_Capture+db_status+db_POS+db_PIN_Capture_Code+db_Country_Code+db_Currency_Code;
				//log.info("FTP ENTRY : "+regEntry);
				printW.println(regEntry);
				
				countEntryReg++;
				row_ids_update = row_ids_update+mipayTerminalProfile.getRowId()+",";
			}
			
			
			
			log.info("FTP TERMINAL COUNT x : "+countEntryReg);
			printW.close();
			log.info("FTP TERMINAL COUNT y : "+countEntryReg);
			if(countEntryReg>0)
			{
				log.info("Terminal Registration Record ["+countEntryReg+"] : File FTP ");
				
				
				File f = new File(localFileTmp);
				
		        FileInputStream fileInputStream = new FileInputStream(f);
		       
		        String rs = ftpUtil.uploadSSHFTP(ftpIp, ftpUsername, ftpPassword, fileInputStream, "TM"+sdfFTP.format(currentDate)+".dat", ftpRemoteFilePath);
		        
		        fileInputStream.close();
				
				if(rs.equalsIgnoreCase("SUCCESS"))
				{
					Date updateDate = new Date();
					for(TblMipayTerminalProfile mipayTerminalProfile : terminalProfiles)
					{
						mipayTerminalProfile.setFtpFlag(MiPayConstant.TERMINAL_FTP_REGFLAG);
						mipayTerminalProfile.setDateUpdate(updateDate);
						miPayDao.updateEntity(mipayTerminalProfile);
					}
				}
				
				
				
			}
			else
			{
				
				log.info("No Terminal Registration Record : Blank File FTP ");
				
				File f = new File(localFileTmp);
				
		        FileInputStream fileInputStream = new FileInputStream(f);
		        
		        String rs = ftpUtil.uploadSSHFTP(ftpIp, ftpUsername, ftpPassword, fileInputStream, "TM"+sdfFTP.format(currentDate)+".dat", ftpRemoteFilePath);
		        
		        fileInputStream.close();
				
			}
			
			
			log.info("Daily MiPay Terminal Registration Batch - FTP DONE ["+countEntryReg+"] ");
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}*/
	
	
	
	public int taskProcess()
	{
		//download file to /apps if got new
			//check sql all the collection in progress pex
				//find the status from the file
					//update the status base on the file
		Calendar c=new GregorianCalendar();
		int checklastXday = 30;
		c.add(Calendar.DATE, -checklastXday);
		String no_file = "NO_FILE";
		String processed = "PROCCESSED";
		String fileFormat = ".TXT";
		
		//ocm_<date>
		SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyyMMdd"); 
		PropertiesManager property = new PropertiesManager();
		try
		{
//			String ftpCheck = property.getProperty("HLB.OCM.FTP.CHECK");
//			if(null==ftpCheck || ftpCheck.equalsIgnoreCase("false"))
//			{
//				return 0;
//			}
			
			EncryptionServices en = new EncryptionServices();
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			Date processDate = new Date();
			//String ftpCheck = property.getProperty("HLB.OCM.FTP.CHECK");
			//------------------------------------------------------------
			// Not going to use FTP, EBS will prepare the file in local
			//------------------------------------------------------------
//			String ftpIp = property.getProperty("HLB.OCM.FTP.IP");
//			String ftpPort = property.getProperty("HLB.OCM.FTP.PORT");
//			String ftpUsername = property.getProperty("HLB.OCM.FTP.username");
//			String ftpPassword = property.getProperty("HLB.OCM.FTP.password");
			
			String localfile = property.getProperty("HLB.OCM.FTP.local");
//			String remotefile = property.getProperty("HLB.OCM.FTP.remote");
			
//			ftpPassword = new String(en.decrypt(String.valueOf(Hex.encodeHex((PExConstant.encryptionKey).getBytes())).getBytes(), Base64.decodeBase64(ftpPassword.getBytes())));
//			remotefile = remotefile==null?"/":(remotefile.trim().length()==0?"/":remotefile+"/");
			//------------------------------------------------------------
			
			String todayFile = "PEXTRN_"+fileDateFormat.format(processDate);
			
			String [] batch_file_date = new String[checklastXday];
			String sqlList = "(";
			for(int i=checklastXday;i>0;i--)
			{
				Calendar cTmp = new GregorianCalendar();
				cTmp.add(Calendar.DATE, -(i-1));
				Date batchFileDate = new Date(cTmp.getTimeInMillis());
				batch_file_date[i-1] = "PEXTRN_"+fileDateFormat.format(batchFileDate);
				sqlList = sqlList+"'PEXTRN_"+fileDateFormat.format(batchFileDate)+"',";
			}
			
			sqlList = sqlList.substring(0, sqlList.length()-1)+")";
			
			String sqlCheckFile = "From HlbATMBatchFile where file_name in "+sqlList+"";
			List<ATMBatchFile> checkFileRs = dao.selectQuery(sqlCheckFile);
			for(String xbatchFile : batch_file_date)
			{
				boolean exist_check=false;
				for(ATMBatchFile hlbATMFile:checkFileRs)
				{
					if(xbatchFile.equalsIgnoreCase(hlbATMFile.getFileName()))
					{
						exist_check = true;
						break;
					}
				}
				
				if(!exist_check)
				{
					ATMBatchFile batchFile = new ATMBatchFile();
					batchFile.setEntryDate(new Date());
					batchFile.setFileName(xbatchFile);
					batchFile.setStatus(no_file);
					
					dao.insertEntity(batchFile);
				}
				
			}
			
			/*String sqlCheckFile = "From HlbATMBatchFile where file_name = '"+todayFile+"'";
			List<HlbATMBatchFile> checkFileRs = dao.selectQuery(sqlCheckFile);
			if(checkFileRs.size()==0)
			{
				
				
				HlbATMBatchFile batchFile = new HlbATMBatchFile();
				batchFile.setEntryDate(new Date());
				batchFile.setFileName(todayFile);
				batchFile.setStatus(no_file);
				
				dao.insertEntity(batchFile);
				
			}*/
			
		
			Date checkDate = new Date(c.getTimeInMillis());
			SimpleDateFormat sdf = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT); 
			//String sqlgetFile = "From HlbATMBatchFile where status = '"+no_file+"' and entry_date > '"+sdf.format(checkDate)+"' order by entry_date asc";
			String sqlgetFile = "From HlbATMBatchFile where status = '"+no_file+"' and file_name in "+sqlList;
			log.info("PROCCESS SQL : "+sqlgetFile);
			List<ATMBatchFile> rsDownload = dao.selectQuery(sqlgetFile);
			
//			FTPClientUtil ftpUtil = new  FTPClientUtil();
			for(ATMBatchFile rsBatch : rsDownload)
			{
				
				log.info("PROCCESS FILE : "+rsBatch.getFileName());
//				boolean downFileCheck = false;
				
//				if(ftpPort.equalsIgnoreCase("22"))
//				{
//
//					downFileCheck = ftpUtil.secureDownload(ftpIp, ftpUsername, ftpPassword, localfile+"/"+rsBatch.getFileName()+fileFormat, remotefile+rsBatch.getFileName()+fileFormat);
//					
//				}
//				else
//				{
//
//					downFileCheck = ftpUtil.download(ftpIp, ftpUsername, ftpPassword, localfile+"/"+rsBatch.getFileName()+fileFormat, remotefile+rsBatch.getFileName()+fileFormat);
//					
//				}
				
				//System.out.println(remotefile+rsBatch.getFileName()+fileFormat+":"+downFileCheck);

//				if(downFileCheck)
				log.info("LOCAL FILE : " + localfile);
				log.info("FILE NAME : " + rsBatch.getFileName());
				log.info("FILE FORMAT : " + fileFormat);
				File file = new File(localfile+"/"+rsBatch.getFileName()+fileFormat);
				if (file.exists())
				{
					String sqlPExCollectionInProgress = "FROM HlbPexProcessTranx WHERE ( collection_type = '"+PExConstant.PEX_COLLECTION_TYPE_ATM+"') AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED+"'";
					List<PexProcessTranx> pexAceptedData = dao.selectQuery(sqlPExCollectionInProgress);
					
					
					
					BufferedReader br = null;
					String sCurrentLine;
					 
					br = new BufferedReader(new FileReader(localfile+"/"+rsBatch.getFileName()+fileFormat));
					int countTotal = 0;
					int countSuccess = 0;
					int countFailed = 0;
					while ((sCurrentLine = br.readLine()) != null) {
						//System.out.println(sCurrentLine);
						String ref = "";
						
						try
						{
							OCM_UpdatePExStatusBatch pexStatusBatch = new OCM_UpdatePExStatusBatch();
							
							pexStatusBatch = (OCM_UpdatePExStatusBatch) dataBeanUtil.setFieldNamesAndByte(pexStatusBatch, sCurrentLine.getBytes(), "UTF-8");
							ref = pexStatusBatch.getPexRefNo_20_b_$().trim();
							String status = pexStatusBatch.getStatus_12_b_$().trim();
							String terminalID = null==pexStatusBatch.getTerminalid_10_b_$()?"":pexStatusBatch.getTerminalid_10_b_$().trim();
							//yyyyMMddHHmmssSSS
							SimpleDateFormat sdf_batch = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
							Date tranxDate = sdf_batch.parse(pexStatusBatch.getTransacDate_17_b_$());
							
							Date cdate = null;
							String[] resultBatch={"",""};
							for(PexProcessTranx pexData : pexAceptedData)
							{
								
								if(ref.equalsIgnoreCase(pexData.getRefNo()))
								{
									
									
									resultBatch = pexDao.updatePExStatus(ref, pexStatusBatch.getEarmarkRefId_40_b_$(), status, tranxDate,terminalID,pexStatusBatch.getTranCode_10_b_$(),pexStatusBatch.getSeqNumber_9_f_0() );
									
									
									break;
								}
							}
							log.info("ATM OCM PEX-REF : "+ref +" ["+status+"]-"+resultBatch[0]);
							if(PExConstant.PEX_ERR_COMMON_SUCCESS.equalsIgnoreCase(resultBatch[0]))
							{
								//countSuccess++;
							}
							else
							{
								//countFailed++;
							}
							
							countSuccess++;
							
						}	
						catch(Exception exx)
						{	log.error("BATCH PEX ATM EXCEPTION",exx);
							log.info("ATM OCM PEX-REF : "+ref +"-[EXCEPTION]");
							countFailed++;
						}
						
						countTotal++;
						
						
					}
				
					
					
					String updateSqlFile = "UPDATE HlbATMBatchFile set status = '"+processed+"', total_records = '"+countTotal+"', processed_records = '"+countSuccess+"', failed_records='"+countFailed+"' WHERE row_id = "+rsBatch.getRowId();
					dao.updateSQL(updateSqlFile);
				
				
				}
			
			
			}
			
			
			
			
			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	
	//conf time = 11:00 , proccess time will valid from 11:00-11:59
	//conf time = 11:40 , proccess time will valid from 11:40-11:59
	//conf time = xx:xx , proccess time will not start
	public boolean checkCurrentProccessTime(String proccessTime)
	{
		Date current = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		if(proccessTime.equalsIgnoreCase("xx:xx"))
		{
			return false;
		}
		else if(df.format(current).compareTo(proccessTime)>0 && df.format(current).startsWith(proccessTime.substring(0,2)))
		{
			return true;
		}
		
		return false;
	}
	
	public static void main(String args[])
	{
		PExATMOCMBatchTask test = new PExATMOCMBatchTask();
		
	}
	

}
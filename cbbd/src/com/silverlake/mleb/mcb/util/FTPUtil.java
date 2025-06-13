package com.silverlake.mleb.mcb.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import comx.silverlake.mleb.mcb.entity.PostalCode;

public class FTPUtil
{
	private static Logger log = LogManager.getLogger(FTPUtil.class);
	public enum PostalCodeFields
	{
		POSTAL_CODE             (5),
		KELURAHAN               (40),
		KECAMATAN               (40),
		KABUPATEN        	   (40),
		PROVINCE      		   (40),
		;

		private int fieldSize;

		/** Private constructor */
		private PostalCodeFields(int fieldSize)
		{
			this.fieldSize = fieldSize;
		}

		public int getFieldSize()
		{
			return fieldSize;
		}
	}

	public FTPUtil()
	{

	}



	public FTPClient readFTPFile(String ftpUrl, int port, String username, String password, String workDirectory) throws SocketException, IOException
	{

		FTPClient ftpClient = new FTPClient();
		boolean status = false;
		ftpClient.connect(ftpUrl, port);
		status = ftpClient.login(username, password);
		if(status){
			ftpClient.enterLocalPassiveMode();

			//if the file is not in root we need to change directory
			ftpClient.changeWorkingDirectory(workDirectory);

			return ftpClient;
		}
		else{
			//Login failed
			ftpClient.disconnect();
			return null;
		}

	}

	public List<PostalCode> scanFile(FTPClient ftpClient, String chkFileName, String datFileName) throws IOException
	{
		List<PostalCode> lsPostalCode = new ArrayList<PostalCode>();
		if(checkFileExists(ftpClient, chkFileName))
		{
			InputStream inputStream = ftpClient.retrieveFileStream(datFileName);
			int returnCode = ftpClient.getReplyCode();
			log.info("Reply Code from DAT :: " + ftpClient.getReplyCode());
			if (inputStream == null || returnCode == 550) {
				ftpClient.disconnect();
				log.info("DAT File does not exist");	
			}
			else{
				Scanner sc = new Scanner(inputStream);

				//Reading the file line by line and printing the same
				while (sc.hasNextLine()) 
				{	
					// Fill in the blank
					parseGetRecord(sc.nextLine(), lsPostalCode);
				}
				//Closing the channels
				sc.close();
				inputStream.close();
				ftpClient.disconnect();
			}
		}
		return lsPostalCode;
	}

	private boolean checkFileExists(FTPClient ftpClient, String filePath) throws IOException 
	{
		InputStream inputStream = ftpClient.retrieveFileStream(filePath);
		int returnCode = ftpClient.getReplyCode();
		log.info("Return Code from CHK :: " + returnCode);
		if (inputStream == null || returnCode == 550) {
			log.info("CHK File does not exist");
			ftpClient.disconnect();
			return false;
		}
		else{
			inputStream.close();
			ftpClient.completePendingCommand();
			return true;
		}
		


	}

	private void parseGetRecord(String record, List<PostalCode> lsPostalCode)
	{
		int posn = 0;
		int endIdx = 0;

		PostalCode postalCode = new PostalCode();
		for (PostalCodeFields f: PostalCodeFields.values())
		{
			endIdx = posn + f.getFieldSize();
			if(record.length()<endIdx){
				endIdx = record.length();
			}

			//System.out.println("Parsing field " + f.toString() +", value = " + record.substring(posn, endIdx));
			if(f.toString().equalsIgnoreCase("POSTAL_CODE")){
				postalCode.setPostalCode(record.substring(posn, endIdx).trim());
			}
			else if(f.toString().equalsIgnoreCase("KELURAHAN")){
				postalCode.setKelurahan(record.substring(posn, endIdx).trim());
			}
			else if(f.toString().equalsIgnoreCase("KECAMATAN")){
				postalCode.setKecamatan(record.substring(posn, endIdx).trim());
			}
			else if(f.toString().equalsIgnoreCase("KABUPATEN")){
				postalCode.setKabupaten(record.substring(posn, endIdx).trim());
			}
			else if(f.toString().equalsIgnoreCase("PROVINCE")){
				postalCode.setProvince(record.substring(posn, endIdx).trim());
			}

			posn = endIdx;

		}

		lsPostalCode.add(postalCode);
	}
}


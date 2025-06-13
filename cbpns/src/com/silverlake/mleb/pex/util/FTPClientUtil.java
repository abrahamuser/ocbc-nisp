package com.silverlake.mleb.pex.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.ChannelSftp.LsEntry;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystem;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.Selectors;
import org.apache.commons.vfs.VFS;
import org.apache.commons.vfs.impl.StandardFileSystemManager;
import org.apache.commons.vfs.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs.provider.sftp.SftpFileSystemConfigBuilder;
import org.apache.commons.io.IOUtils;

public class FTPClientUtil {
	
	
	private String ip;
	private int port;


   public FTPClientUtil() {
	
	}

   
  
   Session session = null;
   ChannelSftp sftpChannel = null;
   public void connectSftp(String ip, int port, String username, String password) throws JSchException
   {
	   JSch jsch = new JSch();
	  
	   session = jsch.getSession(username,ip, port);
	  
       //session.setConfig("StrictHostKeyChecking", "no");
       
       session.setPassword(password);
      
       session.connect();
       
       Channel channel = session.openChannel("sftp");
      
       channel.connect();
      
       sftpChannel = (ChannelSftp) channel;
 
   }
   
   
   
   public void closeSftpConnection()
   {
	   if(sftpChannel.isConnected())
		   sftpChannel.exit();
	   if(session.isConnected())
		   session.disconnect();
   }
   
   
   
   public void SFTP() throws Exception
   {
	   JSch jsch = new JSch();
       Session session = null;
       ChannelSftp sftpChannel = null;
       session = jsch.getSession("root", "192.168.71.12", 22);
      //session.setConfig("StrictHostKeyChecking", "no");
       session.setPassword("123456");
       session.connect();
       
       Channel channel = session.openChannel("sftp");
       channel.connect();
       sftpChannel = (ChannelSftp) channel;
       Vector file = sftpChannel.ls("/home/wastest/esapi");
       for(Object obj : file)
       {
    	   LsEntry ftpFile = (LsEntry) obj;
    	   //File f = new File("as");
			System.out.println("Download FTP file Check : "+ftpFile.getFilename());
			/*if(!f.exists())
			{
				 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("e:/abc.txt"));
					
				 sftpChannel.get(ftpFile.getFilename(), out);
				out.flush();
				out.close();
			}*/
       }
       sftpChannel.exit();
       
       session.disconnect();
   }
   
   
   
   public FTPClient ftpClient;
   public FTPClient connectFtp(String ip, int port, String username, String password) throws SocketException, IOException
   {
	   ftpClient = new FTPClient();
	   ftpClient.connect("192.168.71.12",22);
	   ftpClient.login("root", "12345");
	   return ftpClient;
   }
   
   

   
   
   
   public void FTP() throws SocketException, IOException
   {
	   
		 FTPFile[] filelist = ftpClient.listFiles("/home/wastest/esapi");
		 for(FTPFile ftpFile : filelist)
		{
			 
			 System.out.println("file : "+ftpFile.getName());
				/*File f = new File("localpath/filename");
				 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("e:/file.txt"));
				 boolean boolDownloaded = ftpClient.retrieveFile(ftpFile.getName(), out);
                 if (boolDownloaded)
                 {
                	 out.close();
                 }
					*/
		 
		}
		 ftpClient.disconnect();
   }


   
   
   
   
   public static void main(String args[])
   {
	   FTPClientUtil ftp = new FTPClientUtil();
	   try {
		/*//ftp.SFTP();
		   //ftp.connectSftp("192.168.71.63", 22, "ftpuser", "Abc123456");
		  // ftp.connectFtp("192.168.71.63", 21, "ftpuser", "Abc123456");
		   System.out.println("a'a".replaceAll("\\'", ""));
		   
		   FileSystemManager fsManager = null;
	        FileSystem fs = null;
	        FileSystemOptions opts = new FileSystemOptions();
	        fsManager = VFS.getManager();

	        FileObject path = fsManager.resolveFile("ftp://ftpuser:Abc123456@192.168.71.63/temp/", opts);

	        fs = path.getFileSystem();
	          System.out.println(path.getChildren()[0].getName());
	        fsManager.closeFileSystem(fs);
	        
		   long checkfilePeriod = 30 * 1000 * 60 * 60 * 24;
		   System.out.println(checkfilePeriod);
		   
	        
		   String hostName = "192.168.0.106";
	        String userame = "ftpuser";
	        String password = "abc123";
	        String localFilePath = "E:\\logs\\readme.txt";
	        String remoteFilePath = "/ocm/ocm_20130107.txt";
	        //SftpUtils.upload(hostName, userame, password, localFilePath, remoteFilePath);
	        //File f = new File(localFilePath);
	        //if (!f.exists())
	            //throw new RuntimeException("Error. Local file not found");
	        FileInputStream fileInputStream = null;
	       // ftp.uploadSSHFTP(hostName, userame, password, fileInputStream, "test.txt", remoteFilePath);
	        
	        //ftp.downloadSSHFTP(hostName, userame, password, fileInputStream, "test.txt", remoteFilePath);
	        ftp.download(hostName, userame, password, "D:\\ocm_20130107.txt", "/home/ftpuser/ocm/ocm_20130107.txt" );*/
		   String localFilePath = "d:\\pex.properties";
		   File f = new File(localFilePath);
		   FileInputStream  fis = new FileInputStream (f);
		   ftp.uploadSSHFTP("192.168.43.147", "root", "abc123", fis, "pex.properties", "/root/appx/pex.txt");
		   
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
   }
   
   
   public void checkConnection(String hostName, String username,String password) throws FileSystemException
   {
	   FileSystemManager fsManager = null;
       FileSystem fs = null;
       FileSystemOptions opts = new FileSystemOptions();
       fsManager = VFS.getManager();
    
       
       
       FileObject path = fsManager.resolveFile("ftp://"+username+":"+password+"@"+hostName+"/", opts);
       System.out.println(path.getChildren());
       System.out.println(path.getContent());
       System.out.println(path.getFileSystem());
       System.out.println(path.getName().getPath());
       
       path.close();
   }
   
   public String uploadSSHFTP(String hostName, String username,String password, InputStream localInputStream, String localInputStreamName, String remoteFilePath) {
        
      
	   System.out.println("----------------------------------------------- FTP x ");
	   StandardFileSystemManager manager = new StandardFileSystemManager();
       try {
    	 
    	 
           manager.init();
        
         
           // Create local file object
         
           //FileObject localFile = manager.resolveFile(f.getAbsolutePath());
          
           FileObject localFile = manager.resolveFile("ram://path/needed/" + localInputStreamName);
          
           localFile.createFile();
           
           OutputStream localOutputStream = localFile.getContent().getOutputStream();
           
           IOUtils.copy(localInputStream, localOutputStream);
           
           localOutputStream.flush();
          
           // Create remote file object
           FileSystemOptions fileSystemOptions = createDefaultOptions();
         
           FtpFileSystemConfigBuilder.getInstance().setPassiveMode(fileSystemOptions, true); 
           SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(fileSystemOptions, "no");
           FileObject remoteFile = manager.resolveFile(
                   createSecureConnectionString(hostName, username, password,
                		   remoteFilePath), fileSystemOptions);
          
           // Copy local file to sftp server
           remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
           
           return "SUCCESS";
          
       } catch (Exception e) {
    	   System.out.println("ERRRR RRRRRRRRRRRRRRRRRRRRRRRRRRRRR : "+e.getMessage());
          e.printStackTrace();
       } finally {
           manager.close();
       }
       
       return "FAILED";
   }
   
   
   
   public String downloadSSHFTP(String hostName, String username,String password, InputStream localInputStream, String localInputStreamName, String remoteFilePath) {
       
	      
	   System.out.println("----------------------------------------------- FTP x ");
	   StandardFileSystemManager manager = new StandardFileSystemManager();
       try {
    	 
    	 
           manager.init();
        
         
           // Create local file object
         
           //FileObject localFile = manager.resolveFile(f.getAbsolutePath());
          
           FileObject localFile = manager.resolveFile("ram://path/needed/" + localInputStreamName);
          
           //localFile.createFile();
           
           //OutputStream localOutputStream = localFile.getContent().getOutputStream();
           
          // IOUtils.copy(localInputStream, localOutputStream);
           
           //localOutputStream.flush();
          
           // Create remote file object
           FileSystemOptions fileSystemOptions = createDefaultOptions();
         
           FtpFileSystemConfigBuilder.getInstance().setPassiveMode(fileSystemOptions, true); 
           SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(fileSystemOptions, "no");
           FileObject remoteFile = manager.resolveFile(
                   createConnectionString(hostName, username, password,
                		   remoteFilePath), fileSystemOptions);
          
           System.out.println(remoteFile.getContent().getFile().getName());
           
           
           // Copy local file to sftp server
           //remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
           
           return "SUCCESS";
          
       } catch (Exception e) {
    	   System.out.println("ERRRR RRRRRRRRRRRRRRRRRRRRRRRRRRRRR : "+e.getMessage());
          e.printStackTrace();
       } finally {
           manager.close();
       }
       
       return "FAILED";
   }
  
   
   
   public static String createConnectionString(String hostName,
           String username, String password, String remoteFilePath) {
       // result: "sftp://user:123456@domainname.com/resume.pdf
       return "ftp://" + username + ":" + password + "@" + hostName
               + remoteFilePath;
   }
   
   public static String createSecureConnectionString(String hostName,
           String username, String password, String remoteFilePath) {
       // result: "sftp://user:123456@domainname.com/resume.pdf
       return "sftp://" + username + ":" + password + "@" + hostName
               + remoteFilePath;
   }

   public static FileSystemOptions createDefaultOptions() throws FileSystemException {
       // Create SFTP options
       FileSystemOptions opts = new FileSystemOptions();
        
       // SSH Key checking
       SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
               opts, "no");
        
       // Root directory set to user home
       SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, false);
        
       // Timeout is count by Milliseconds
       SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);
        
       return opts;
   }
   
   
   
   public boolean download(String hostName, String username,
           String password, String localFilePath, String remoteFilePath) {

   StandardFileSystemManager manager = new StandardFileSystemManager();

   try {
           manager.init();

           String downloadFilePath = localFilePath.substring(0,localFilePath.lastIndexOf("."))
           //+ "_downlaod_from_sftp"
           + localFilePath.substring(localFilePath.lastIndexOf("."),
           localFilePath.length());

           // Create local file object
           FileObject localFile = manager.resolveFile(downloadFilePath);

           // Create remote file object
           FileObject remoteFile = manager.resolveFile(
                           createConnectionString(hostName, username, password,
                                   remoteFilePath), createDefaultOptions());

           // Copy local file to sftp server
           localFile.copyFrom(remoteFile, Selectors.SELECT_SELF);

           System.out.println("File download success");
           
           return true;
   } catch (Exception e) {
           e.printStackTrace();
          
   } finally {
           manager.close();
   }
   
   return false;
}
	
}
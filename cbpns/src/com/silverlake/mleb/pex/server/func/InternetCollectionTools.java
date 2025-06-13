package com.silverlake.mleb.pex.server.func;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.silverlake.mleb.pex.server.webservice.PExInterface;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.PropertiesManager;




@Component("itnsimtools")
public class InternetCollectionTools  implements HttpRequestHandler
{
  
	private static Logger log = LogManager.getLogger(InternetCollectionTools.class);
	
	
	
	private PropertiesManager pmgr = new PropertiesManager();

	    public void doGet(HttpServletRequest request, HttpServletResponse response) {
			try {
				
				log.info("---------------HLB Mobility Server Internet Collection Simulator Tools(Testing)-------------------");
				DataBeanUtil dataBeanUtil = new DataBeanUtil();
				PropertiesManager pm = new PropertiesManager();
				String pdata = pm.getProperty("itnsimtool");
				
				
				if(null!=pdata && pdata.equalsIgnoreCase("true"))
				{
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<head></head>");
					out.println("<body>");
					out.println("<h1>HLB Mobility Server ATM Simulator Tools(Testing)</h1>");
					out.println("<br/>");
					out.println("<form action=\"itnsimtools\">");
				
					
					
				
					out.println("<br/>");
					out.println("Correction Code : <input name=\"ccode\" type=\"text\"  maxlength=\"50\"/>");
					out.println("<br/>");
					out.println("Mobile Number : <input name=\"mnumber\" type=\"text\"  maxlength=\"50\"/>");
					out.println("<br/>");
					out.println("Amount : <input name=\"amount\" type=\"text\"  maxlength=\"50\"/>");
					out.println("<br/>");
					
					
				
					
					
					
					out.println("<input  type=\"submit\"/>");
				
					
					

					
					String amount = request.getParameter("amount");
					String ccode = request.getParameter("ccode");
					String mnumber = request.getParameter("mnumber");
					
					
					amount = ""==amount?null:(null==amount?null:amount.replaceAll("\\.", ""));;
					ccode = ""==ccode?null:ccode;
					mnumber = ""==mnumber?null:mnumber;
					
					log.info("["+amount+"] : ["+ccode+"] : ["+mnumber+"]");
					
					if( null!=amount && null!=ccode && null!=mnumber)
					{
						out.println("<br/>");
						
						
						int serverPort = request.getServerPort();
						URL wsdlURL = new URL("http://localhost:"+serverPort+"/pex_ws/ws/PExWSServices?wsdl");
						QName SERVICE_NAME = new QName("http://apache.org/hello_world_soap_http", "SOAPService");
						Service service = Service.create(wsdlURL, SERVICE_NAME);
						PExInterface client = service.getPort(PExInterface.class);
						
						WSPExRequest wsReq = new WSPExRequest();
						
						WSPExResponse result = (WSPExResponse) client.validatePExCollection(wsReq);
						
						out.println("<p style=\"color:blue;\">Result Validate Input :: "+result.getObHeader().getStatusCode()+"</p>");
						
						
					}
					else if( null!=amount || null!=ccode || null!=mnumber)
					{
						log.info("----invalid Input-----");
				
						out.println("<p style=\"color:red;\">Invalid Input Data</p>");
					}
					
					out.println("</form>");
					
					
					
					out.println("</body>");
					out.println("</html>");	
					
				}
				else
				{
					//do nothing
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	
	

	@Override
	public void handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(arg0,arg1);
	}
	
	
    


}

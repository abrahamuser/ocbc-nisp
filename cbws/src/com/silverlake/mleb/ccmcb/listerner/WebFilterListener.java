package com.silverlake.mleb.ccmcb.listerner;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.util.PropertiesManager;



public class WebFilterListener implements Filter   {

	private static Logger log = LogManager.getLogger(WebFilterListener.class);
	private static PropertiesManager pro = new PropertiesManager();
	public static ConcurrentHashMap cchashMap = new ConcurrentHashMap();
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String uuidReq = UUID.randomUUID().toString();
		cchashMap.put(uuidReq,uuidReq);
		HttpServletRequest httpRequest = (HttpServletRequest) arg0;
		/*if(null!=httpRequest.getQueryString() && httpRequest.getQueryString().toLowerCase().equalsIgnoreCase("wsdl"))
		{
			PrintWriter out = arg1.getWriter();
			out.println("<html>");
			out.println("<body><h3>No services have been found.</h3></body>");
			out.println("<html>");
			return;
		}*/
		String showWSDL = pro.getProperty("show.wsdl");
		if(null!=showWSDL && showWSDL.equalsIgnoreCase("true"))
		{
			//show wsdl info
		}
		else
		{
			if((httpRequest.getRequestURI().toLowerCase().endsWith("MiBWSServices".toLowerCase()) ||  httpRequest.getRequestURI().toLowerCase().endsWith("MiBWSApi".toLowerCase()) ) && null!=httpRequest.getQueryString() && httpRequest.getQueryString().toLowerCase().equalsIgnoreCase("wsdl") )
			{
				PrintWriter out = arg1.getWriter();
				out.println("<html>");
				out.println("<body><span class='heading'>No services have been found.</span></body>");
				out.println("<html>");
				return;
			}
		}
		
		
		HttpServletResponse rsp = (HttpServletResponse) arg1;
		//modifySetCookie(httpRequest,rsp);
		rsp.setHeader("Pragma", "no-cache");
		// Pass request back down the filter chain
		arg2.doFilter(arg0,arg1);
		cchashMap.remove(uuidReq);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	private void modifySetCookie(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse ){
		String sessionId = httpServletRequest.getSession().getId();
		String key = "Set-Cookie";
		String value = "JSESSIONID="+sessionId+"; HttpOnly";
		httpServletResponse.setHeader(key,value);
	}
		
	

	

}

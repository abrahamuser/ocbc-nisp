package com.silverlake.mleb.pex.server.socket.bean;

import java.util.ArrayList;
import java.util.List;


public class TestResponse 
{

	public TestData extDatax = new TestData() ;
	public String headerType_5_b_$;
	public String deviceName_15_b_$;
	public String socketNumber_9_f_0;
	public String portNumber_5_f_0;
	
	public List<TestData> extDatas=new ArrayList();
	
	
	public TestResponse() {
		extDatas.add(new TestData());
	}
	
	public String getHeaderType_5_b_$() {
		return headerType_5_b_$;
	}
	public void setHeaderType_5_b_$(String headerType_5_b_$) {
		this.headerType_5_b_$ = headerType_5_b_$;
	}
	public String getDeviceName_15_b_$() {
		return deviceName_15_b_$;
	}
	public void setDeviceName_15_b_$(String deviceName_15_b_$) {
		this.deviceName_15_b_$ = deviceName_15_b_$;
	}
	public String getSocketNumber_9_f_0() {
		return socketNumber_9_f_0;
	}
	public void setSocketNumber_9_f_0(String socketNumber_9_f_0) {
		this.socketNumber_9_f_0 = socketNumber_9_f_0;
	}
	public String getPortNumber_5_f_0() {
		return portNumber_5_f_0;
	}
	public void setPortNumber_5_f_0(String portNumber_5_f_0) {
		this.portNumber_5_f_0 = portNumber_5_f_0;
	}
	public List<TestData> getExtDatas() {
		return extDatas;
	}
	public void setExtDatas(List<TestData> extDatas) {
		this.extDatas = extDatas;
	}

	public TestData getExtDatax() {
		return extDatax;
	}

	public void setExtDatax(TestData extDatax) {
		this.extDatax = extDatax;
	}
	

	
	
}

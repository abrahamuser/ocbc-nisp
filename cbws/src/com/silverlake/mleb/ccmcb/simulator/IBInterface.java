package com.silverlake.mleb.ccmcb.simulator;

import javax.jws.WebParam;
import javax.jws.WebService;




//@WebService
public interface  IBInterface
{
	public String performIBLogin(@WebParam(name="userID")String userID, @WebParam(name="password")String password);
	
	public int getExpirationPeriod();
}
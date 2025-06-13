package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

public class ObEstatementCreditCardPDFResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -6859872267036345708L;
	private String fileName;
	private String historyPDF;
	private String responseMessage;

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getHistoryPDF()
	{
		return historyPDF;
	}

	public void setHistoryPDF(String historyPDF)
	{
		this.historyPDF = historyPDF;
	}

	@XmlTransient
	public String getResponseMessage()
	{
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}
}

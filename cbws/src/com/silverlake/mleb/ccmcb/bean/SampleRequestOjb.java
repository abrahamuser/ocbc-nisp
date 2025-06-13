package com.silverlake.mleb.ccmcb.bean;

import java.io.Serializable;

public class SampleRequestOjb implements Serializable {

	private RequestBody requestBody;

    private RequestHeader requestHeader;

    public RequestBody getRequestBody ()
    {
        return requestBody;
    }

    public void setRequestBody (RequestBody requestBody)
    {
        this.requestBody = requestBody;
    }

    public RequestHeader getRequestHeader ()
    {
        return requestHeader;
    }

    public void setRequestHeader (RequestHeader requestHeader)
    {
        this.requestHeader = requestHeader;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [requestBody = "+requestBody+", requestHeader = "+requestHeader+"]";
    }
}

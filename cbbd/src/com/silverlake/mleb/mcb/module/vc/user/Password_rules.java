package com.silverlake.mleb.mcb.module.vc.user;

import java.io.Serializable;

public class Password_rules implements Serializable
{
	  
	private String param_value;

    private String param_name;

    public String getParam_value ()
    {
        return param_value;
    }

    public void setParam_value (String param_value)
    {
        this.param_value = param_value;
    }

    public String getParam_name ()
    {
        return param_name;
    }

    public void setParam_name (String param_name)
    {
        this.param_name = param_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [param_value = "+param_value+", param_name = "+param_name+"]";
    }
 
 
}



	

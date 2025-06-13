package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class OmniPojoBean implements Serializable {
    private String paramCode;
    private String paramValue;
    private String paramValueID;
    private String paramDesc;
    private String moduleName;

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamValueID() {
        return paramValueID;
    }

    public void setParamValueID(String paramValueID) {
        this.paramValueID = paramValueID;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}

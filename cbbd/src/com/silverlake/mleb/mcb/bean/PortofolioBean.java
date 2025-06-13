package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class PortofolioBean implements Serializable {
    private String portofolioCode;
    private String portofolioDesc;
    private Integer portofolioValue;

    public String getPortofolioCode() {
        return portofolioCode;
    }

    public void setPortofolioCode(String portofolioCode) {
        this.portofolioCode = portofolioCode;
    }

    public String getPortofolioDesc() {
        return portofolioDesc;
    }

    public void setPortofolioDesc(String portofolioDesc) {
        this.portofolioDesc = portofolioDesc;
    }

    public Integer getPortofolioValue() {
        return portofolioValue;
    }

    public void setPortofolioValue(Integer portofolioValue) {
        this.portofolioValue = portofolioValue;
    }
}

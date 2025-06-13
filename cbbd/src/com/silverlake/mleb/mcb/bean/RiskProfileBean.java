package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class RiskProfileBean implements Serializable {
    private String riskProfileCode;
    private String riskProfileScore;
    private String riskProfileTitle;
    private String riskProfileDesc;
    private List<PortofolioBean> portofolioComposition;

    public String getRiskProfileCode() {
        return riskProfileCode;
    }

    public void setRiskProfileCode(String riskProfileCode) {
        this.riskProfileCode = riskProfileCode;
    }

    public String getRiskProfileScore() {
        return riskProfileScore;
    }

    public void setRiskProfileScore(String riskProfileScore) {
        this.riskProfileScore = riskProfileScore;
    }

    public String getRiskProfileTitle() {
        return riskProfileTitle;
    }

    public void setRiskProfileTitle(String riskProfileTitle) {
        this.riskProfileTitle = riskProfileTitle;
    }

    public String getRiskProfileDesc() {
        return riskProfileDesc;
    }

    public void setRiskProfileDesc(String riskProfileDesc) {
        this.riskProfileDesc = riskProfileDesc;
    }

    public List<PortofolioBean> getPortofolioComposition() {
        return portofolioComposition;
    }

    public void setPortofolioComposition(List<PortofolioBean> portofolioComposition) {
        this.portofolioComposition = portofolioComposition;
    }
}

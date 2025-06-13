package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class OriginBean implements Serializable {
    private Boolean isWNI;
    private Boolean isHaveKTP;
    private Boolean isHaveNPWP;
    private Boolean isHavePassport;

    public Boolean getWNI() {
        return isWNI;
    }

    public void setWNI(Boolean WNI) {
        isWNI = WNI;
    }

    public Boolean getHaveKTP() {
        return isHaveKTP;
    }

    public void setHaveKTP(Boolean haveKTP) {
        isHaveKTP = haveKTP;
    }

    public Boolean getHaveNPWP() {
        return isHaveNPWP;
    }

    public void setHaveNPWP(Boolean haveNPWP) {
        isHaveNPWP = haveNPWP;
    }

    public Boolean getHavePassport() {
        return isHavePassport;
    }

    public void setHavePassport(Boolean havePassport) {
        isHavePassport = havePassport;
    }
}

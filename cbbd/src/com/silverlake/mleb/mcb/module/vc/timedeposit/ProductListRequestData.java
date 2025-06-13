package com.silverlake.mleb.mcb.module.vc.timedeposit;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

import java.util.List;

public class ProductListRequestData extends VCRequest {
    private String ccy_cd;

    public String getCcy_cd() {
        return ccy_cd;
    }

    public void setCcy_cd(String ccy_cd) {
        this.ccy_cd = ccy_cd;
    }
}

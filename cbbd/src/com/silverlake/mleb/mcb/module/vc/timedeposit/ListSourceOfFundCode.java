package com.silverlake.mleb.mcb.module.vc.timedeposit;

import java.io.Serializable;

public class ListSourceOfFundCode implements Serializable {
    private String source_fund_code;
    private String source_fund_desc_en;
    private String source_fund_desc_id;
    private String source_fund_desc_cn;

    public String getSource_fund_code() {
        return source_fund_code;
    }

    public void setSource_fund_code(String source_fund_code) {
        this.source_fund_code = source_fund_code;
    }

    public String getSource_fund_desc_en() {
        return source_fund_desc_en;
    }

    public void setSource_fund_desc_en(String source_fund_desc_en) {
        this.source_fund_desc_en = source_fund_desc_en;
    }

    public String getSource_fund_desc_id() {
        return source_fund_desc_id;
    }

    public void setSource_fund_desc_id(String source_fund_desc_id) {
        this.source_fund_desc_id = source_fund_desc_id;
    }

    public String getSource_fund_desc_cn() {
        return source_fund_desc_cn;
    }

    public void setSource_fund_desc_cn(String source_fund_desc_cn) {
        this.source_fund_desc_cn = source_fund_desc_cn;
    }
}

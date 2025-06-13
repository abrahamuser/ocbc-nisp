package com.silverlake.mleb.mcb.module.vc.timedeposit;

import java.io.Serializable;

public class ListPurposeCode implements Serializable {
    private String purpose_code;
    private String purpose_desc_en;
    private String purpose_desc_id;
    private String purpose_desc_cn;

    public String getPurpose_code() {
        return purpose_code;
    }

    public void setPurpose_code(String purpose_code) {
        this.purpose_code = purpose_code;
    }

    public String getPurpose_desc_en() {
        return purpose_desc_en;
    }

    public void setPurpose_desc_en(String purpose_desc_en) {
        this.purpose_desc_en = purpose_desc_en;
    }

    public String getPurpose_desc_id() {
        return purpose_desc_id;
    }

    public void setPurpose_desc_id(String purpose_desc_id) {
        this.purpose_desc_id = purpose_desc_id;
    }

    public String getPurpose_desc_cn() {
        return purpose_desc_cn;
    }

    public void setPurpose_desc_cn(String purpose_desc_cn) {
        this.purpose_desc_cn = purpose_desc_cn;
    }
}

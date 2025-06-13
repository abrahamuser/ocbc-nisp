package com.silverlake.mleb.mcb.module.vc.timedeposit;

import java.io.Serializable;

public class ListRolloverCode implements Serializable {
    private String rollover_code;
    private String rollover_desc_en;
    private String rollover_desc_id;
    private String rollover_desc_cn;

    public String getRollover_code() {
        return rollover_code;
    }

    public void setRollover_code(String rollover_code) {
        this.rollover_code = rollover_code;
    }

    public String getRollover_desc_en() {
        return rollover_desc_en;
    }

    public void setRollover_desc_en(String rollover_desc_en) {
        this.rollover_desc_en = rollover_desc_en;
    }

    public String getRollover_desc_id() {
        return rollover_desc_id;
    }

    public void setRollover_desc_id(String rollover_desc_id) {
        this.rollover_desc_id = rollover_desc_id;
    }

    public String getRollover_desc_cn() {
        return rollover_desc_cn;
    }

    public void setRollover_desc_cn(String rollover_desc_cn) {
        this.rollover_desc_cn = rollover_desc_cn;
    }
}

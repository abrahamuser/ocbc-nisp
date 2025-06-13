package com.silverlake.mleb.mcb.module.vc.timedeposit;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

import java.util.List;

public class ParameterResponseData extends VCResponseData {

    List<ListPurposeCode> purpose_code_list;
    List<ListSourceOfFundCode> source_fund_list;
    List<ListRolloverCode> rollover_type_list;

    public List<ListPurposeCode> getPurpose_code_list() {
        return purpose_code_list;
    }

    public void setPurpose_code_list(List<ListPurposeCode> purpose_code_list) {
        this.purpose_code_list = purpose_code_list;
    }

    public List<ListSourceOfFundCode> getSource_fund_list() {
        return source_fund_list;
    }

    public void setSource_fund_list(List<ListSourceOfFundCode> source_fund_list) {
        this.source_fund_list = source_fund_list;
    }

    public List<ListRolloverCode> getRollover_type_list() {
        return rollover_type_list;
    }

    public void setRollover_type_list(List<ListRolloverCode> rollover_type_list) {
        this.rollover_type_list = rollover_type_list;
    }
}

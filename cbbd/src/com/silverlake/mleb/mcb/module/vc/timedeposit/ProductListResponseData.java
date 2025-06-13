package com.silverlake.mleb.mcb.module.vc.timedeposit;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

import java.util.List;

public class ProductListResponseData extends VCResponseData {
    List<ListTimeDepositProduct> prod_detail_list;

    public List<ListTimeDepositProduct> getProd_detail_list() {
        return prod_detail_list;
    }

    public void setProd_detail_list(List<ListTimeDepositProduct> prod_detail_list) {
        this.prod_detail_list = prod_detail_list;
    }
}

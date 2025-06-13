package com.silverlake.mleb.mcb.module.vc.transaction.management;


import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ProductConfigurationResponseData extends VCResponseData{
	private List<ProductConfig> product_config_list;

	public List<ProductConfig> getProduct_config_list() {
		return product_config_list;
	}

	public void setProduct_config_list(List<ProductConfig> product_config_list) {
		this.product_config_list = product_config_list;
	}
}

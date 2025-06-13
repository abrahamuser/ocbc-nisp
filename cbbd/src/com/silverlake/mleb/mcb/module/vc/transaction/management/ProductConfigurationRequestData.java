package com.silverlake.mleb.mcb.module.vc.transaction.management;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class ProductConfigurationRequestData extends VCRequest implements Serializable {
	  
	private List<String> list_prod_cd;

	public List<String> getList_prod_cd() {
		return list_prod_cd;
	}

	public void setList_prod_cd(List<String> list_prod_cd) {
		this.list_prod_cd = list_prod_cd;
	}
}



	

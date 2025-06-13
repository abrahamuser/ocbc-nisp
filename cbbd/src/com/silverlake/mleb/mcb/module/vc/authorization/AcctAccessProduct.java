package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class AcctAccessProduct  implements Serializable
{
	  private String product_group;
	  private List<String> product_code_list;
	  private String prod_group_name;
	  private List<String> prod_name_list;
	  
	public String getProduct_group() {
		return product_group;
	}
	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}
	public List<String> getProduct_code_list() {
		return product_code_list;
	}
	public void setProduct_code_list(List<String> product_code_list) {
		this.product_code_list = product_code_list;
	}
	public String getProd_group_name() {
		return prod_group_name;
	}
	public void setProd_group_name(String prod_group_name) {
		this.prod_group_name = prod_group_name;
	}
	public List<String> getProd_name_list() {
		return prod_name_list;
	}
	public void setProd_name_list(List<String> prod_name_list) {
		this.prod_name_list = prod_name_list;
	}
		  
	   
}



	

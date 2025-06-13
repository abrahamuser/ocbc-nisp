package com.silverlake.mleb.mcb.module.vc.accountManagement;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

 
public class RequestData extends VCRequest{

	
	 	public static String method_acct_mgmt_favorite  = "acct_mgmt/favorite";
		
	    private String search_acct_no;
	    private String search_acct_name;
	    private String page_no;
	    private String page_size;
	    //Inquiry
	    private String acct_no;
	    private String acct_ccy;
	    private String acct_type;
	    private String balance_check;
	    
	    
	    private String start_date;
	    private String end_date ;
	    private String order;
	    
	    
	    private String prod_cd;
	    private List<FavAccount> favorite_acct_map;
	    
	    
	    
		public String getSearch_acct_no() {
			return search_acct_no;
		}
		public void setSearch_acct_no(String search_acct_no) {
			this.search_acct_no = search_acct_no;
		}
		public String getSearch_acct_name() {
			return search_acct_name;
		}
		public void setSearch_acct_name(String search_acct_name) {
			this.search_acct_name = search_acct_name;
		}
		public String getPage_no() {
			return page_no;
		}
		public void setPage_no(String page_no) {
			this.page_no = page_no;
		}
		public String getPage_size() {
			return page_size;
		}
		public void setPage_size(String page_size) {
			this.page_size = page_size;
		}
		public String getAcct_no() {
			return acct_no;
		}
		public void setAcct_no(String acct_no) {
			this.acct_no = acct_no;
		}
		public String getAcct_ccy() {
			return acct_ccy;
		}
		public void setAcct_ccy(String acct_ccy) {
			this.acct_ccy = acct_ccy;
		}
		public String getStart_date() {
			return start_date;
		}
		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}
		public String getEnd_date() {
			return end_date;
		}
		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
		public String getAcct_type() {
			return acct_type;
		}
		public void setAcct_type(String acct_type) {
			this.acct_type = acct_type;
		}
		public String getBalance_check() {
			return balance_check;
		}
		public void setBalance_check(String balance_check) {
			this.balance_check = balance_check;
		}
		public List<FavAccount> getFavorite_acct_map() {
			return favorite_acct_map;
		}
		public void setFavorite_acct_map(List<FavAccount> favorite_acct_map) {
			this.favorite_acct_map = favorite_acct_map;
		}
		public String getProd_cd() {
			return prod_cd;
		}
		public void setProd_cd(String prod_cd) {
			this.prod_cd = prod_cd;
		}
	 
		
  

}

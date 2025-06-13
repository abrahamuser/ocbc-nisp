package com.silverlake.mleb.mcb.module.vc.transaction;
import java.util.List;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class MT103InquiryResponseData extends VCResponseData{
	
	private List<Mt103Data> mt103_data ;

	public List<Mt103Data> getMt103_data() {
		return mt103_data;
	}

	public void setMt103_data(List<Mt103Data> mt103_data) {
		this.mt103_data = mt103_data;
	}
	
	
}

package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.bean.TimeBatchDataBean;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class InquiryTransTimeBatchResponseData extends VCResponseData {

	private List<TimeBatchDataBean> time_batch_list;

	public List<TimeBatchDataBean> getTime_batch_list() {
		return time_batch_list;
	}

	public void setTime_batch_list(List<TimeBatchDataBean> time_batch_list) {
		this.time_batch_list = time_batch_list;
	}

	

	}

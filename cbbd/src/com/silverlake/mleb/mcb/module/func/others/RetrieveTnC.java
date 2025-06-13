package com.silverlake.mleb.mcb.module.func.others;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.silverlake.mleb.mcb.bean.ObTNCCache;
import com.silverlake.mleb.mcb.bean.ObTNCCheckbox;
import com.silverlake.mleb.mcb.bean.ObTNCRequest;
import com.silverlake.mleb.mcb.bean.ObTNCResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.Tnc;
import com.silverlake.mleb.mcb.entity.TncCheckbox;
import com.silverlake.mleb.mcb.entity.dao.TncCheckboxDAO;
import com.silverlake.mleb.mcb.entity.dao.TncDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.TNCRequestData;
import com.silverlake.mleb.mcb.module.vc.others.TNCResponseData;
import com.silverlake.mleb.mcb.util.MapperUtil;

/**
 * Purpose: Request term and condition content  
 * Omni Web Services:
 * others/tnc
 * 
 * @author Alex Loo
 *
 */
@Service
public class RetrieveTnC extends CacheSessionFuncServices<ObTNCRequest, ObTNCResponse, ObTNCCache>{
	
	@Autowired
	TncDAO tncDAO;

	@Autowired
	TncCheckboxDAO tncCheckboxDAO;
	
	private static Comparator<ObTNCCheckbox> SORT_TNC_SEQ_NO = new Comparator<ObTNCCheckbox>() {
		public int compare(ObTNCCheckbox arg0, ObTNCCheckbox arg1) {
			return arg0.getSeqNo().compareTo(arg1.getSeqNo());
		}
	};
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTNCRequest requestBean, ObTNCResponse responseBean, ObTNCCache cacheBean, VCGenericService vcService) throws Exception {
		//If isLocal flag is true, get tnc content from local db.
		if(requestBean.getIsLocal() != null && requestBean.getIsLocal()) {
			List<Tnc> listTnc = tncDAO.getTnCbyProductCode(requestBean.getTncType(), locale);

			if (listTnc == null) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_ONA_EMPTY_TNC);
				return;
			}

			responseBean.setListTnC(new ArrayList<String>());
			for (Tnc tnc : listTnc) {
				if (tnc.getTncUrl() != null && !tnc.getTncUrl().isEmpty()) {
					if (tnc.getTncUrl().toLowerCase().startsWith("http")) {//If it is http url link, send it as link
						responseBean.setTncContent(tnc.getTncUrl());
					} else {
						InputStream inputStream = null;
						try {
							inputStream = new FileInputStream(tnc.getTncUrl());
							String x = new String(FileCopyUtils.copyToByteArray(inputStream));
							responseBean.setTncContent(x);
						}finally {
							if(inputStream != null) inputStream.close();
						}
					}
					responseBean.setTncType(tnc.getFooter());
					responseBean.setTncName("tnc");
				}
			}

			List<TncCheckbox> tncCheckboxList = tncCheckboxDAO.getTncCheckbox(requestBean.getTncType(), locale);
			if (tncCheckboxList != null && tncCheckboxList.size() > 0) {
				responseBean.setTncCheckboxList(new ArrayList<ObTNCCheckbox>());
				for (TncCheckbox tncCheckbox : tncCheckboxList) {
					ObTNCCheckbox destbean = new ObTNCCheckbox();
					MapperUtil.copyFields(tncCheckbox, destbean);
					responseBean.getTncCheckboxList().add(destbean);
				}
				Collections.sort(responseBean.getTncCheckboxList(), SORT_TNC_SEQ_NO);
			}
			
			return;
		}
		
		TNCRequestData requestData = new TNCRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setTnc_type(requestBean.getTncType());
		VCResponse<TNCResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_TNC, 
				requestData, 
				TNCResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		TNCResponseData responseData = vcResponse.getData();
		if(responseData.getFile() != null){
			responseBean.setTncContent(responseData.getFile().getData());
			responseBean.setTncType(responseData.getFile().getMime_type());
			responseBean.setTncName(responseData.getFile().getFile_name());
		}else {
			responseBean.setTncContent("");
			responseBean.setTncType("");
			responseBean.setTncName("");
		}
		
		cacheBean.setOmniResponse(responseData);
		cacheBean.setRequestData(requestBean);
	}
}

package com.silverlake.mleb.mcb.module.func.others;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRetrieveFaqRequest;
import com.silverlake.mleb.mcb.bean.ObRetrieveFaqResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.others.ListFaq;
import com.silverlake.mleb.mcb.module.vc.others.ResponseData;


@Service
public class RetrieveFaq extends FuncServices  
{
	private static Logger log = LogManager.getLogger(RetrieveFaq.class);

	@Autowired
	CustomerDAO dao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired ApplicationContext appContext;

	public MICBResponseBean process(MICBRequestBean arg0) {
		MICBResponseBean response = new MICBResponseBean();
		ObRetrieveFaqResponse obResponse = new ObRetrieveFaqResponse();
		obResponse.setObHeader(new ObHeaderResponse());

		try {
			ObRetrieveFaqRequest requestData = (ObRetrieveFaqRequest) arg0.getBDObject();	
			String faq_cat_cd = requestData.getFaq_cat_cd();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			//Call REQ OMNI RetrievFaq
			VCService usrService = new VCService(appContext);
			com.silverlake.mleb.mcb.module.vc.others.RequestData vcFaqRequest = new com.silverlake.mleb.mcb.module.vc.others.RequestData();
			vcFaqRequest.setFaq_cat_cd(faq_cat_cd);
			//Call RES OMNI RetrievFaq 
			com.silverlake.mleb.mcb.module.vc.others.ResponseData vcFaqResponseData = new com.silverlake.mleb.mcb.module.vc.others.ResponseData();
			VCResponse vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.FAQ,vcFaqRequest, vcFaqResponseData, null, arg0.getTranxID(), ipAddress);
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcFaqResponseData = (ResponseData) vcResponse.getData();
				if(vcFaqResponseData.getList_faq()!= null)
				{
					obResponse.setFaq(new ArrayList());
					for(ListFaq temp:vcFaqResponseData.getList_faq())
					{
						com.silverlake.mleb.mcb.bean.ListFaq faq = new com.silverlake.mleb.mcb.bean.ListFaq();
						/*BeanUtils.copyProperties(temp, faq);*/
						faq.setCat_cd(temp.getCat_cd());
						faq.setId(temp.getId());
						faq.setSeq(temp.getSeq());
						
						if (arg0.getLocaleCode().equalsIgnoreCase(MiBConstant.LOCALE_IN)) {
							faq.setCat_desc(temp.getCat_desc_id());
							faq.setContent(temp.getContent_id());
							faq.setTitle(temp.getTitle_id());
						} else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
							faq.setCat_desc(temp.getCat_desc_cn());
							faq.setContent(temp.getContent_cn());
							faq.setTitle(temp.getTitle_cn());
						} else {
							faq.setCat_desc(temp.getCat_desc_en());
							faq.setContent(temp.getContent_en());
							faq.setTitle(temp.getTitle_en());
						}
						
						
						obResponse.getFaq().add(faq);
						log.debug(obResponse.getFaq().get(0).getId());
					}
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_FAQ_FOUND);
				}
				
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
			
			

		} catch (Exception e) {

			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}

		response.setBDObject(obResponse);

		return response;
	}
}
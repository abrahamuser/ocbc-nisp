package com.silverlake.mleb.mcb.module.func.accountMgmt;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.util.MappingUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewBean;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewRequest;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.bean.ObCorporateAccountOverview;
import com.silverlake.mleb.mcb.bean.ObFavAccountRequest;
import com.silverlake.mleb.mcb.bean.ObFavAccountResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.FavAccount;
import com.silverlake.mleb.mcb.module.vc.accountManagement.Inquiry;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;







@Service
public class PerformUpdateFavAcct extends FuncServices  
{

	private static Logger log = LogManager.getLogger(PerformUpdateFavAcct.class);
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObFavAccountResponse obResponse = new ObFavAccountResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {

			ObFavAccountRequest requestData = (ObFavAccountRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData vcAcctInquiryRequest = new RequestData();
			VCService usrService = new VCService(appContext);
			vcAcctInquiryRequest.setOrg_cd(orgId);
			vcAcctInquiryRequest.setUsr_cd(usrId);
			
			vcAcctInquiryRequest.setFavorite_acct_map(new ArrayList());
		    int seq = 1;
			for(ObCorporateAccountOverview tmp:requestData.getNewFavAccountList())
			{
				FavAccount fav = new FavAccount();
				fav.setAcct_ccy(tmp.getAcct_ccy());
				fav.setAcct_no(tmp.getAcct_no());
				fav.setSeq(seq+"");
				
				//favOrder set during account list when it under STMT 
				if(tmp.getFavOrder()==null || tmp.getFavOrder().trim().length()==0)
				{
					fav.setAction_cd("I");
				}
				else
				{
					fav.setAction_cd("U");
				}
				seq++;
				
				vcAcctInquiryRequest.getFavorite_acct_map().add(fav);
				
				Iterator<ObCorporateAccountOverview> itr = requestData.getOldFavAccountList().iterator();
				while(itr.hasNext())
				{
					ObCorporateAccountOverview checkTmp = itr.next();
					if(checkTmp.getAcct_no().equalsIgnoreCase(tmp.getAcct_no()) && checkTmp.getAcct_ccy().equalsIgnoreCase(tmp.getAcct_ccy())) 
					{
						itr.remove();
					}
				}
				
			}
			
			
			for(ObCorporateAccountOverview deltmp : requestData.getOldFavAccountList())
			{
				if(deltmp.getFavOrder()!=null)
				{
					FavAccount fav = new FavAccount();
					fav.setAcct_ccy(deltmp.getAcct_ccy());
					fav.setAcct_no(deltmp.getAcct_no());
					fav.setSeq(deltmp.getFavOrder().substring(5));
					fav.setAction_cd("D");
					
					vcAcctInquiryRequest.getFavorite_acct_map().add(fav);
				}
			}
			
			if(requestData.getPrd_cd()==null || requestData.getPrd_cd().trim().length()==0)
			{
				vcAcctInquiryRequest.setProd_cd("STMT");
			}
			
			 
			//Call RES OMNI AccInquiry
			com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcAcctInquiryResponseData = new ResponseData();
			VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponseUpdate = usrService.callOmniVC(vcAcctInquiryRequest.method_acct_mgmt_favorite,vcAcctInquiryRequest, vcAcctInquiryResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			if(vcResponseUpdate.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				 
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else 
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseUpdate.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponseUpdate.getResponse_desc());
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

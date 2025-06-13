package com.silverlake.mleb.mcb.module.func.timedeposit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositRequest;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositResponse;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositSessionCache;
import com.silverlake.mleb.mcb.bean.timedeposit.TimeDepositProduct;
import com.silverlake.mleb.mcb.bean.timedeposit.TimeDepositTenor;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListTimeDepositProduct;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ProductListRequestData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ProductListResponseData;

@Service
public class TimeDepositAccountOpeningProductList extends CacheSessionFuncServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache> {

    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, ObTimeDepositSessionCache cacheBean, VCGenericService vcService) throws Exception {
        ProductListRequestData productListRequestData = new ProductListRequestData();
        processRequestProductList(productListRequestData, requestBean);
        VCResponse<ProductListResponseData> vcProductListResponseData = vcService.callOmniVC(
                VCMethodConstant.REST_METHODS.TIME_DEPOSIT_OPENING_LIST_PRODUCT,
                productListRequestData,
                ProductListResponseData.class,
                true
        );
        if (processVCResponseError(vcProductListResponseData, responseBean)) {
            return;
        }

        ProductListResponseData productListResponseData = vcProductListResponseData.getData();
        processResponseProductList(productListResponseData, responseBean);
        cacheBean.setProductListResponseData(productListResponseData);
    }

    private void processRequestProductList(ProductListRequestData productListRequestData, ObTimeDepositRequest requestBean) {
        productListRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
        productListRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
        if(requestBean.getCurrencyCode()!=null && !requestBean.getCurrencyCode().isEmpty()) {
            productListRequestData.setCcy_cd(requestBean.getCurrencyCode());
        }
    }

    private void processResponseProductList(ProductListResponseData productListResponseData, ObTimeDepositResponse responseBean) {
        responseBean.setProductList(new ArrayList<TimeDepositProduct>());
        //Group the product by CURRENCY > TENOR TYPE 
        //Determine the min and max amount by category
        for(ListTimeDepositProduct listTimeDepositProduct : productListResponseData.getProd_detail_list()){
        	TimeDepositProduct key= new TimeDepositProduct();
        	key.setCurrencyCode(listTimeDepositProduct.getCcy_code());
        	key.setTenorType(listTimeDepositProduct.getTenor_type());
        	
        	Collections.sort(responseBean.getProductList(), timeDepositGroupingComparator);
        	int result = Collections.binarySearch(responseBean.getProductList(), key, timeDepositGroupingComparator);
        	if(result >= 0) {
        		//Inserted group
        		TimeDepositProduct timeDepositProduct = responseBean.getProductList().get(result);
        		//Set the smallest min amount
        		if(listTimeDepositProduct.getMin_amount().compareTo(timeDepositProduct.getMinAmount())<0) {
        			timeDepositProduct.setMinAmount(listTimeDepositProduct.getMin_amount());
        		}
        		//Set the biggest max amount
        		if(listTimeDepositProduct.getMax_amount().compareTo(timeDepositProduct.getMaxAmount())>0) {
        			timeDepositProduct.setMaxAmount(listTimeDepositProduct.getMax_amount());
        		}
        		//Set the smallest interest rate
        		if(timeDepositProduct.getInterestRate() == null) {
        			timeDepositProduct.setInterestRate(listTimeDepositProduct.getInterest_rate());
        		}else if(listTimeDepositProduct.getInterest_rate() != null && 
        				listTimeDepositProduct.getInterest_rate().compareTo(timeDepositProduct.getInterestRate())<0) {
        			timeDepositProduct.setInterestRate(listTimeDepositProduct.getInterest_rate());
        		}
        		TimeDepositTenor timeDepositTenor = new TimeDepositTenor();
                timeDepositTenor.setTenor(listTimeDepositProduct.getTenor_value());
                String[] interestTermValue = listTimeDepositProduct.getInterest_term_value().split(",");
                List<Integer> interestTermValueInteger = new ArrayList<Integer>();
                for(String str : interestTermValue){
                    interestTermValueInteger.add(Integer.parseInt(str.trim()));
                }
                timeDepositTenor.setInterestTerm(interestTermValueInteger);
                timeDepositTenor.setInterestTermType(listTimeDepositProduct.getInterest_term_type());
                
                timeDepositProduct.getTenor().add(timeDepositTenor);
        	}else {
        		//new group
        		TimeDepositProduct timeDepositProduct = new TimeDepositProduct();
                timeDepositProduct.setProductName(listTimeDepositProduct.getProd_name());
                timeDepositProduct.setCurrencyCode(listTimeDepositProduct.getCcy_code());
                timeDepositProduct.setMinAmount(listTimeDepositProduct.getMin_amount());
                timeDepositProduct.setMaxAmount(listTimeDepositProduct.getMax_amount());
                timeDepositProduct.setInterestRate(listTimeDepositProduct.getInterest_rate());
                timeDepositProduct.setTenorType(listTimeDepositProduct.getTenor_type());
                
                TimeDepositTenor timeDepositTenor = new TimeDepositTenor();
                timeDepositTenor.setTenor(listTimeDepositProduct.getTenor_value());
                String[] interestTermValue = listTimeDepositProduct.getInterest_term_value().split(",");
                List<Integer> interestTermValueInteger = new ArrayList<Integer>();
                for(String str : interestTermValue){
                    interestTermValueInteger.add(Integer.parseInt(str.trim()));
                }
                timeDepositTenor.setInterestTerm(interestTermValueInteger);
                timeDepositTenor.setInterestTermType(listTimeDepositProduct.getInterest_term_type());
                
                timeDepositProduct.setTenor(new ArrayList<TimeDepositTenor>());
                timeDepositProduct.getTenor().add(timeDepositTenor);
                responseBean.getProductList().add(timeDepositProduct);
        	}
        }
    }
    
    private static Comparator<TimeDepositProduct> timeDepositGroupingComparator = new Comparator<TimeDepositProduct>() {
		@Override
		public int compare(TimeDepositProduct o1, TimeDepositProduct o2) {
			int result = o1.getCurrencyCode().compareTo(o2.getCurrencyCode());
			if(result != 0) {
				return result;
			}
			return o1.getTenorType().compareTo(o2.getTenorType());
		}
	};

}

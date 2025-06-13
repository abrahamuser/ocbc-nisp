package com.silverlake.mleb.mcb.module.func.transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionDataListRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionDataListResponseData;
import com.silverlake.mleb.mcb.util.MapperUtil;
import com.silverlake.mleb.mcb.util.StringUtil;

/**
 * Purpose: Get transaction data list
 * 
 * Omni Web Services:
 * transaction/data_list
 * 
 * @author Alex Loo
 *
 */
@Service
public class RetrieveTransactionDataList extends CacheSessionFuncServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean, ObAuthorizationSessionCache cacheBean, VCGenericService vcService) throws Exception {
		TransactionDataListRequestData requestData = new TransactionDataListRequestData();
		TransactionDataListResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<TransactionDataListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_DATA_LIST, 
				requestData, 
				TransactionDataListResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(locale, responseData, requestBean, responseBean, cacheBean);
	}
	
	private void processRequest(TransactionDataListRequestData requestData, ObAuthorizationRequest requestBean) throws Exception{
		String pageSize = requestBean.getPage_size()==null?"10":requestBean.getPage_size();
		pageSize = pageSize.trim().length()==0?"10":pageSize;
		
		String pageNo = (String) (requestBean.getPage_no()==null?"1":requestBean.getPage_no());
		pageNo = pageNo.trim().length()==0?"1":pageNo;
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setPage_size(pageSize);
		requestData.setPage_no(pageNo);
		requestData.setSource_trx(MapperUtil.filterEmptyValue(requestBean.getSource_trx()));
		requestData.setProd_cd(MapperUtil.filterEmptyValue(requestBean.getProdCodeList()));
		requestData.setTrx_status(StringUtil.returnNullForEmpty(requestBean.getTrx_status()));
		requestData.setValue_date_from(StringUtil.returnNullForEmpty(requestBean.getValue_date_from()));
		requestData.setValue_date_to(StringUtil.returnNullForEmpty(requestBean.getValue_date_to()));
		requestData.setPymt_master_id(requestBean.getPymtMasterId());
	}
	
	private void processResponse(String locale, TransactionDataListResponseData responseData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean, ObAuthorizationSessionCache cacheBean) throws Exception{
		String pageSize = requestBean.getPage_size()==null?"10":requestBean.getPage_size();
		pageSize = pageSize.trim().length()==0?"10":pageSize;
		
		String pageNo = (String) (requestBean.getPage_no()==null?"1":requestBean.getPage_no());
		pageNo = pageNo.trim().length()==0?"1":pageNo;
		if(responseData.getList_trx()!=null){
			List<GeneralCode> productCodeList = new ArrayList<GeneralCode>();
			if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN)){
				productCodeList = gnDao.getAllTaskTranxProductCode_IN();
			}else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
				productCodeList = gnDao.getAllTaskTranxProductCode_CN();
			}else{
				productCodeList = gnDao.getAllTaskTranxProductCode();
			}
			List<GeneralCode> uploadFormat = gnDao.findByGnCodeType(MiBConstant.TRANXUPLOADFORMAT, locale);
			List<GeneralCode> rolloverTypeList = gnDao.findByGnCodeType(MiBConstant.ROLLOVERTYPE, locale);
			//Get all the status list of transaction
			List<GeneralCode> dbTranxStatusCodeList = new ArrayList();
			if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN)){
				dbTranxStatusCodeList = gnDao.getAllTaskTranxStatusCode_IN();
			} else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
				dbTranxStatusCodeList = gnDao.getAllTaskTranxStatusCode_CN();
			} else {
				dbTranxStatusCodeList = gnDao.getAllTaskTranxStatusCode();
			}
			responseBean.setList_trx(new ArrayList<Transaction>());
			int index = 1 + ((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
			for(Transaction temp:responseData.getList_trx()){
				if(null!=temp.getProd_cd()){
					for(GeneralCode gnTmp:productCodeList){
						if(temp.getProd_cd().equalsIgnoreCase(gnTmp.getGnCode())){
							temp.setProd_desc(gnTmp.getGnCodeDescription());
							break;
						}
					}
				}
				
				if(null!=temp.getUpload_format()){
					for(GeneralCode gnTmp:uploadFormat){
						if(temp.getUpload_format().equalsIgnoreCase(gnTmp.getGnCode())){
							temp.setUpload_format_desc(gnTmp.getGnCodeDescription());
							break;
						}
					}
				}
				
				if(null!=temp.getTrx_status()){
					for(GeneralCode gnTmp:dbTranxStatusCodeList){
						if(temp.getTrx_status().equalsIgnoreCase(gnTmp.getGnCode())){
							temp.setTrx_status_desc(gnTmp.getGnCodeDescription());
							break;
						}
					}
				}
				
				if(null!=temp.getRollover_type()){
					for(GeneralCode gnTmp:rolloverTypeList){
						if(temp.getRollover_type().equalsIgnoreCase(gnTmp.getGnCode())){
							temp.setRollover_type_desc(gnTmp.getGnCodeDescription());
							break;
						}
					}
				}
				
				if(null!=temp.getRollover_type_new()){
					for(GeneralCode gnTmp:rolloverTypeList){
						if(temp.getRollover_type_new().equalsIgnoreCase(gnTmp.getGnCode())){
							temp.setRollover_type_new_desc(gnTmp.getGnCodeDescription());
							break;
						}
					}
				}
				
				Transaction transaction = new Transaction();
				BeanUtils.copyProperties(temp, transaction);
				transaction.setIndex(String.valueOf(index));
				//VELO2UAT-370 - calculate onfx selling and buying amount.
				if(transaction.getProd_cd().equalsIgnoreCase(MiBConstant.PRODUCT_CODE_ONFX) &&
						transaction.getDebit_acct_ccy() != null &&
						transaction.getAmount_ccy() != null &&
						transaction.getAmount() != null &&
						transaction.getFx_rate() != null) {
					/**
					 * Amount that filled up by customer set to either selling amount or buying amount depending on the amount currency that is being chosen.
					 * Calculate selling amount when buying amount is set
					 * Calculate buying amount when selling amount is set
					 */
					if(transaction.getDebit_acct_ccy().equals(transaction.getAmount_ccy())){
						transaction.setSellingAmount(transaction.getAmount());
						transaction.setBuyingAmount(calculateAmount(transaction.getAmount(), transaction.getAmount_ccy(), transaction.getFx_rate()));
					} else{
						transaction.setBuyingAmount(transaction.getAmount());
						transaction.setSellingAmount(calculateAmount(transaction.getAmount(), transaction.getAmount_ccy(), transaction.getFx_rate()));
					}
				}
				responseBean.getList_trx().add(transaction);
				
				index++;
			}
			responseBean.setTotal_rows(String.valueOf(responseData.getTotal_rows()));
			
			ObAuthorizationRequest cacheRequestBean = new ObAuthorizationRequest();
			BeanUtils.copyProperties(requestBean, cacheRequestBean);
			cacheRequestBean.setSessionCache(null);
			ObAuthorizationSessionCache authorizationSessionCache = null;
			if(requestBean.getSessionCache() != null){
				authorizationSessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_DATA_LIST.getId());
			}
			if(authorizationSessionCache == null || authorizationSessionCache.getTransactionList() == null || authorizationSessionCache.getTransactionList().isEmpty()){
				cacheBean.setRequestBean(cacheRequestBean);
				cacheBean.setTransactionList(responseBean.getList_trx());//Set the transaction list into the cache
			}else{
				boolean isSameFilterRemained = true;
				if(requestBean.getTrx_status() == null){
					if(authorizationSessionCache.getRequestBean().getTrx_status() != null){
						isSameFilterRemained = false;
					}
				}else if(!requestBean.getTrx_status().equals(authorizationSessionCache.getRequestBean().getTrx_status())){
					isSameFilterRemained = false;
				}
				if(requestBean.getValue_date_from() == null){
					if(authorizationSessionCache.getRequestBean().getValue_date_from() != null){
						isSameFilterRemained = false;
					}
				}else if(!requestBean.getValue_date_from().equals(authorizationSessionCache.getRequestBean().getValue_date_from())){
					isSameFilterRemained = false;
				}
				if(requestBean.getValue_date_to() == null){
					if(authorizationSessionCache.getRequestBean().getValue_date_to() != null){
						isSameFilterRemained = false;
					}
				}else if(!requestBean.getValue_date_to().equals(authorizationSessionCache.getRequestBean().getValue_date_to())){
					isSameFilterRemained = false;
				}
				if(requestBean.getPymtMasterId() == null){
					if(authorizationSessionCache.getRequestBean().getPymtMasterId() != null){
						isSameFilterRemained = false;
					}
				}else if(!requestBean.getPymtMasterId().equals(authorizationSessionCache.getRequestBean().getPymtMasterId())){
					isSameFilterRemained = false;
				}
				if(authorizationSessionCache.getRequestBean().getSource_trx().size() != requestBean.getSource_trx().size()){
					isSameFilterRemained = false;
				}else{
					for(String cacheSourceTrx:authorizationSessionCache.getRequestBean().getSource_trx()){
						boolean isMatched = false;
						for(String requestedSourceTrx:requestBean.getSource_trx()){
							if(requestedSourceTrx.equals(cacheSourceTrx)){
								isMatched = true;
								break;
							}
						}
						if(!isMatched){
							isSameFilterRemained = false;
						}
					}
				}
				//If the filter value no change, append the result into the cache
				if(isSameFilterRemained){
					addIndexData(authorizationSessionCache.getTransactionList(), responseBean.getList_trx());
					cacheBean.setRequestBean(cacheRequestBean);
					cacheBean.setTransactionList(authorizationSessionCache.getTransactionList());//Set the transaction list into the cache
				}else{
					//If the filter value changed, reset the cache
					cacheBean.setRequestBean(cacheRequestBean);
					cacheBean.setTransactionList(responseBean.getList_trx());//Set the transaction list into the cache
				}
			}
		}
	}
	
	public void addIndexData(List<Transaction> sessionData, List<Transaction> responseData){
		for(Transaction tranx:responseData){
			boolean addTranx = true;
			 for(Transaction tmp:sessionData){
				 if(tmp.getIndex().equalsIgnoreCase(tranx.getIndex())){
					 BeanUtils.copyProperties(tranx, tmp);
					 addTranx = false;
					 break;
				 }
			 }
			 
			 if(addTranx){
				 sessionData.add(tranx);
			 }
		}
	}
	
	/**
	 * ONFX selling/buying amount usage 
	 * @param sellingAmount
	 * @param sellingAmountCcy
	 * @param exchangeRate
	 * @return
	 */
	public BigDecimal calculateAmount(BigDecimal sellingAmount, String sellingAmountCcy, BigDecimal exchangeRate){
		if(exchangeRate != null && sellingAmount != null && sellingAmountCcy != null){
			if(sellingAmountCcy.equalsIgnoreCase(MiBConstant.CURRENCY_IDR)){
				return sellingAmount.divide(exchangeRate, 2, RoundingMode.HALF_UP);
			}else{
				return sellingAmount.multiply(exchangeRate);
			}
		}else{
			return null;
		}
	}
}

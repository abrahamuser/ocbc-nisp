package com.silverlake.mleb.mcb.entity.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.bean.ObCurrencyCodeBean;
import com.silverlake.mleb.mcb.bean.ObGeneralCodeBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;

import com.silverlake.mleb.mcb.entity.GeneralCode;

@Service
@Repository
@Transactional
public class GeneralCodeDAO {

	@Autowired DozerBeanMapper mapper;

	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;

	//private static Logger log = LogManager.getLogger(MicbDAO.class);

	
	public Object findByID(Class objClass, Object obj) {
		Object rsObj = em.find(objClass, obj);
		return rsObj;
	}
	
	public List<ObCurrencyCodeBean> getObCurrencyCode() throws Exception 
	{

		List<ObCurrencyCodeBean> responseList = new ArrayList<ObCurrencyCodeBean>();
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = :gnCodeType and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCodeType", "CCY").setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				ObCurrencyCodeBean ccyBean = new ObCurrencyCodeBean();
				
				ccyBean.setCurrencyCode(gnCodeList.getGnCode());
				
				responseList.add(ccyBean);
			}
		}
		
		return responseList;
	}
	
	public String getAllListTypeDesc(String intervalCode) throws Exception 
	{
		//this function is to map the interval type to interval description
		List<ObGeneralCodeBean> responseList = new ArrayList<ObGeneralCodeBean>();
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status and gnCodeType = :gnCodeType";
		
//		Hashtable params = new Hashtable();
//		params.put("intervalCode", intervalCode);
		
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", intervalCode).setParameter("status", "A").setParameter("gnCodeType", MiBConstant.FUNC_OCBC_GN_RECURRINGTYPE).getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				ObGeneralCodeBean gnBean = new ObGeneralCodeBean();
				gnBean.setGnCodeDescription(gnCodeList.getGnCodeDescription());
			
				responseList.add(gnBean);
			}
			return responseList.get(0).getGnCodeDescription();
		}
		else{
			return "";
		}
		
		
	}
	
	public GeneralCode findByGnCode(String gnCode)  
	{
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status";
		List<GeneralCode> list = em.createQuery(sql).setParameter("gnCode", gnCode).setParameter("status", "A").getResultList();
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}
	
	public GeneralCode findByGnCodeAndLanguage(String gnCode, String locale) throws Exception 
	{
		// if(locale == null || locale.isEmpty() || !locale.equalsIgnoreCase(MiBConstant.LANG_EN))
		// 	locale = MiBConstant.LANG_IN;
		// else 
		// 	locale = MiBConstant.LANG_EN;

		if (locale == null || locale.isEmpty()) {
			// Default to LANG_IN if null/empty
			locale = MiBConstant.LANG_IN;
		} 
		else if (locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) {
			// Explicitly handle Chinese
			locale = MiBConstant.LANG_CN;
		} 
		else if (locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
			// Explicitly handle English
			locale = MiBConstant.LANG_EN;
		} 
		else {
			// Fallback for any other value
			locale = MiBConstant.LANG_IN;
		}
		
	
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and gnCodeType = :localeCode and status = :status";
		List<GeneralCode> list = em.createQuery(sql).setParameter("gnCode", gnCode).setParameter("localeCode", locale).setParameter("status", "A").getResultList();
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}
	
	public GeneralCode findByGnCodeAndGnCodeType(String gnCode, String gnCodeType) throws Exception {
	
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and gnCodeType = :gnCodeType and status = :status";
		List<GeneralCode> list = em.createQuery(sql).setParameter("gnCode", gnCode).setParameter("gnCodeType", gnCodeType).setParameter("status", "A").getResultList();
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}
	
	public GeneralCode findByGnCodeAndGnCodeType(String gnCode, String gnCodeType, String locale) throws Exception 
	{
		// if(locale == null || locale.isEmpty() || !locale.equalsIgnoreCase(MiBConstant.LANG_EN))
		// 	locale = MiBConstant.LANG_IN;
		// else 
		// 	locale = MiBConstant.LANG_EN;

		if (locale == null || locale.isEmpty()) {
			// Default to LANG_IN if null/empty
			locale = MiBConstant.LANG_IN;
		} 
		else if (locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) {
			// Explicitly handle Chinese
			locale = MiBConstant.LANG_CN;
		} 
		else if (locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
			// Explicitly handle English
			locale = MiBConstant.LANG_EN;
		} 
		else {
			// Fallback for any other value
			locale = MiBConstant.LANG_IN;
		}
	
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and gnCodeType = :gnCodeType and status = :status";
		List<GeneralCode> list = em.createQuery(sql).setParameter("gnCode", gnCode).setParameter("gnCodeType", gnCodeType+"_"+locale).setParameter("status", "A").getResultList();
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}
	
	public List<GeneralCode> findByGnCodeType(String gnCodeType, String locale) throws Exception 
	{
		// if(locale == null || locale.isEmpty() || !locale.equalsIgnoreCase(MiBConstant.LANG_EN))
		// 	locale = MiBConstant.LANG_IN;
		// else 
		// 	locale = MiBConstant.LANG_EN;

		if (locale == null || locale.isEmpty()) {
			// Default to LANG_IN if null/empty
			locale = MiBConstant.LANG_IN;
		} 
		else if (locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) {
			// Explicitly handle Chinese
			locale = MiBConstant.LANG_CN;
		} 
		else if (locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
			// Explicitly handle English
			locale = MiBConstant.LANG_EN;
		} 
		else {
			// Fallback for any other value
			locale = MiBConstant.LANG_IN;
		}
	
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = :gnCodeType and status = :status";
		List<GeneralCode> list = em.createQuery(sql).setParameter("gnCodeType", gnCodeType+"_"+locale).setParameter("status", "A").getResultList();
		if(list != null){
			return list;
		}else{
			return Collections.<GeneralCode>emptyList();
		}
	}
	
	public List<GeneralCode> findByGnCodeAndLanguageStartWith(String gnCode, String locale) throws Exception 
	{
		// if(locale == null || locale.isEmpty() || !locale.equalsIgnoreCase(MiBConstant.LANG_EN))
		// 	locale = MiBConstant.LANG_IN;
		// else 
		// 	locale = MiBConstant.LANG_EN;

		if (locale == null || locale.isEmpty()) {
			// Default to LANG_IN if null/empty
			locale = MiBConstant.LANG_IN;
		} 
		else if (locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) {
			// Explicitly handle Chinese
			locale = MiBConstant.LANG_CN;
		} 
		else if (locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
			// Explicitly handle English
			locale = MiBConstant.LANG_EN;
		} 
		else {
			// Fallback for any other value
			locale = MiBConstant.LANG_IN;
		}
		
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode like '"+ gnCode + "_%" +"' and gnCodeType = :localeCode and status = :status";
		List<GeneralCode> list = em.createQuery(sql).setParameter("localeCode", locale).setParameter("status", "A").getResultList();
		if (list != null && !list.isEmpty())
			return list;
		return null;
	}
	
	public String getModuleTypeFlag() throws Exception 
	{

		String response="";
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", "module.type").setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				if(gnCodeList.getGnCodeDescription()==null || gnCodeList.getGnCodeDescription()==""){
					response = "";
				}
				else{
					response = gnCodeList.getGnCodeDescription();
				}
				
			}
		}
		return response;
		
	
	}
	
	
	public String getAccountSummaryPageSize()
	{
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", "AcctSummaryPageSize").setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			return resultlist.get(0).getGnCodeDescription();
		}
		
		return null;
	}
	
	public String getTransactionDescription(String locale, String trx)
	{
		// if(locale == null || locale.isEmpty() || !locale.equalsIgnoreCase(MiBConstant.LANG_EN))
		// 	locale = MiBConstant.LANG_IN;
		// else 
		// 	locale = MiBConstant.LANG_EN;

		if (locale == null || locale.isEmpty()) {
			// Default to LANG_IN if null/empty
			locale = MiBConstant.LANG_IN;
		} 
		else if (locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) {
			// Explicitly handle Chinese
			locale = MiBConstant.LANG_CN;
		} 
		else if (locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
			// Explicitly handle English
			locale = MiBConstant.LANG_EN;
		} 
		else {
			// Fallback for any other value
			locale = MiBConstant.LANG_IN;
		}
		
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and gnCodeType = :gnCodeType";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", trx).setParameter("gnCodeType", locale).getResultList();
		if(resultlist!=null && resultlist.size()>0){
			return resultlist.get(0).getGnCodeDescription();
		}
		
		return null;
	}
	
	/*public List<ObTransferPurposeTypeBean> getObTransferPurposeType() throws Exception 
	{

		List<ObTransferPurposeTypeBean> responseList = new ArrayList<ObTransferPurposeTypeBean>();
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where 1=1 and gnCodeType = 'PP' and status = 'A'";
		List<GeneralCode> resultlist = em.createQuery(sql).getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				ObTransferPurposeTypeBean bean = new ObTransferPurposeTypeBean();
				bean.setTransPurposeType(gnCodeList.getGnCode());
				bean.setTransPurposeTypeDesc(gnCodeList.getGnCodeDescription());
			
				responseList.add(bean);
			}
		}
		
		return responseList;
	}*/
	
	public String getTransferTypeDesc(String intervalCode) throws Exception 
	{
		
		List<ObGeneralCodeBean> responseList = new ArrayList<ObGeneralCodeBean>();
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status and gnCodeType = :gnCodeType";
		
		if(intervalCode.startsWith(MiBConstant.TRANS_SERVICE_TYPE_OL)){
			intervalCode = MiBConstant.TRANS_SERVICE_TYPE_OL;
		}
		
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", intervalCode).setParameter("status", "A").setParameter("gnCodeType", MiBConstant.FUNC_OCBC_GN_TRANSSERVICE).getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				ObGeneralCodeBean gnBean = new ObGeneralCodeBean();
				gnBean.setGnCodeDescription(gnCodeList.getGnCodeDescription());
			
				responseList.add(gnBean);
			}
			return responseList.get(0).getGnCodeDescription();
		}
		else{
			return intervalCode;
		}
		
		
	}
	
	
	public int updateMaxDevice(String maxDev)
	{
		String sql = "UPDATE "+GeneralCode.class.getSimpleName()+" SET gncode_description= :maxDev where gncode = '"+MiBConstant.MAXDEVICEBINDKEY+"' ";
		
		return em.createQuery(sql).setParameter("maxDev", maxDev).executeUpdate();
	}
	
	public String getTermsAndConditionDesc(String tncType){

		String response="";
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", tncType).setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				if(gnCodeList.getGnCodeDescription()==null || gnCodeList.getGnCodeDescription()==""){
					response = "";
				}
				else{
					response = gnCodeList.getGnCodeDescription();
				}
				
			}
		}
		return response;
		
	}
	
	public String getStaffDirFlag(){

		String response="";
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", "staffLoginFlag").setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				if(gnCodeList.getGnCodeDescription()==null || gnCodeList.getGnCodeDescription()==""){
					response = "false";
				}
				else{
					response = gnCodeList.getGnCodeDescription();
				}
				
			}
		}
		return response;
		
	}
	
	
	
	public List<GeneralCode> getRedirectUrl(){

	 
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = :gnCodeType and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCodeType", "redirectUrl").setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			 return resultlist;
		}
		return new ArrayList();
		
	}
	
	
	
	public List<GeneralCode> getRedirectUrlAndMenuAccess(){

		 
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where (gnCodeType = 'redirectUrl' or gnCodeType='menuAccess')  and status = 'A'";
		List<GeneralCode> resultlist = em.createQuery(sql).getResultList();
		if(resultlist!=null && resultlist.size()>0){
			 return resultlist;
		}
		return new ArrayList();
		
	}
	
	public List<GeneralCode> getproxyList(String locale){

		String sql;
		if(locale.equalsIgnoreCase(MiBConstant.LANG_IN)){
			sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = 'proxyListIN'  and status = 'A'";
		}
		else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = 'proxyListCN'  and status = 'A'";
		}
		else { 
		   sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = 'proxyList'  and status = 'A'";
		}
		List<GeneralCode> resultlist = em.createQuery(sql).getResultList();
		if(resultlist!=null && resultlist.size()>0){
			 return resultlist;
		}
		return new ArrayList();
		
	}
	
	public List<GeneralCode> gettrxPurposeList(String locale){

		String sql;
		if(locale.equalsIgnoreCase(MiBConstant.LANG_IN)){
		 sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = 'trxPurposeListIN'  and status = 'A'";
		}
		else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
		 sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = 'trxPurposeListCN'  and status = 'A'";
		}
		else { 
		 sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = 'trxPurposeList'  and status = 'A'";
			
		}
				 
		List<GeneralCode> resultlist = em.createQuery(sql).getResultList();
		if(resultlist!=null && resultlist.size()>0){
			 return resultlist;
		}
		return new ArrayList();
		
	}
	
	public List<GeneralCode> getMenuAccess(){
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType='menuAccess' and status = 'A'";
		List<GeneralCode> resultlist = em.createQuery(sql).getResultList();
		if(resultlist!=null && resultlist.size()>0){
			 return resultlist;
		}
		return Collections.<GeneralCode>emptyList();
	}
	
	
	public List<GeneralCode> getBindingSMSConf()
	{

		 
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = :gnCodeType and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCodeType", "dev.binding.sms").setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			 return resultlist;
		}
		
		return new ArrayList();
		
	}
	
	public String getOCRFlag(){

		String response="";
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", "ocrFlag").setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				if(gnCodeList.getGnCodeDescription()==null || gnCodeList.getGnCodeDescription()==""){
					response = "false";
				}
				else{
					response = gnCodeList.getGnCodeDescription();
				}
				
			}
		}
		return response;
		
	}
	
	public String getMaxYearsForTakaBunga(){

		String response="";
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", MiBConstant.MAX_YEAR_TAKABUNGA).setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				if(gnCodeList.getGnCodeDescription()==null || gnCodeList.getGnCodeDescription()==""){
					response = "1";
				}
				else{
					response = gnCodeList.getGnCodeDescription();
				}
				
			}
		}
		else
		{
			response = "1";
		}
		return response;
		
	}
	
	public String getNumDaysToCacheTakaData(){

		String response="";
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("gnCode", MiBConstant.NUMDAYS_CACHE_TAKADATA).setParameter("status", "A").getResultList();
		if(resultlist!=null && resultlist.size()>0){
			for(GeneralCode gnCodeList: resultlist) 
			{
				if(gnCodeList.getGnCodeDescription()==null || gnCodeList.getGnCodeDescription()==""){
					response = "30";
				}
				else{
					response = gnCodeList.getGnCodeDescription();
				}
				
			}
		}
		else
		{
			response = "30";
		}
		
		return response;
		
	}
	
	public GeneralCode getTimeDepositSyariahParameter(String gnCode) throws Exception 
	{
	
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and gnCodeType = :localeCode and status = :status";
		List<GeneralCode> list = em.createQuery(sql).setParameter("gnCode", gnCode).setParameter("localeCode", MiBConstant.GN_CODE_ONA_TDS).setParameter("status", "A").getResultList();
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}
	
	public BigDecimal getQRPayMinAmount(){
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and gnCodeType = :type and status = :status";
		List<GeneralCode> list = em.createQuery(sql).setParameter("gnCode", MiBConstant.GN_CODE_QR_MIN_AMOUNT).setParameter("type", MiBConstant.GN_CODE_QR).setParameter("status", "A").getResultList();
		String minAmt = "0";
		if (list != null && !list.isEmpty()){
			minAmt =  list.get(0).getGnCodeDescription();
		}
		if(minAmt != null && !minAmt.isEmpty()){
			try{
				return new BigDecimal(minAmt);
			}catch(Exception e){
				return BigDecimal.ZERO;
			}
		}
		return BigDecimal.ZERO;
	}
	
	
	
	public GeneralCode getTnC(String tncCode)
	{
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCode = :gnCode and gnCodeType = :type and status = :status";
		
		List<GeneralCode> list = em.createQuery(sql).setParameter("gnCode", tncCode).setParameter("type", MiBConstant.TNC_TYPE).setParameter("status", "A").getResultList();
		
		if (list != null && !list.isEmpty())
			return list.get(0);
		
		return null;
	}
	
	
	
	
	public List<GeneralCode> getAllTaskTranxProductCode() throws Exception 
	{

		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.TRANXPRODCODE+"' and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
		
	
	}
	
	
	public List<GeneralCode> getUploadFormatDesc() throws Exception 
	{

		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.TRANXUPLOADFORMAT+"' and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
		
	
	}
	
	public List<GeneralCode> getAllTaskTranxProductCode_IN() throws Exception 
	{

		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.TRANXPRODCODE_IN+"' and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
		
	
	}

	public List<GeneralCode> getAllTaskTranxProductCode_CN() throws Exception 
	{

		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.TRANXPRODCODE_CN+"' and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
		
	
	}
	
	public List<GeneralCode> getAllTaskTranxStatusCode() throws Exception 
	{

		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.TRANXSTATUSCODE+"' and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
		
	
	}
	
	
	public List<GeneralCode> getAllTaskTranxStatusCode_IN() throws Exception 
	{

		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.TRANXSTATUSCODE_IN+"' and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
		
	
	}
	
	public List<GeneralCode> getAllTaskTranxStatusCode_CN() throws Exception 
	{

		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.TRANXSTATUSCODE_CN+"' and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
		
	
	}
	
	public List<GeneralCode> getAllBlockStatusState() throws Exception 
	{

		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.BLOCK_STATUS_STATE_TYPE+"' and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
		
	
	}
	
	
	public List<GeneralCode> getAllTASKMapProdCode() throws Exception 
	{

		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.TASKMAPPRODCODE+"' and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
		
	
	}
	
	public List<GeneralCode> getAllPaymentGroupImage() throws Exception {
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.PYMT_GRP_IMG+"' and status = :status order by gnCode";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
	}
	
	public List<GeneralCode> getAllPaymentGroupImageDark() throws Exception {
		String sql = "From "+ GeneralCode.class.getSimpleName() +" where gnCodeType = '"+MiBConstant.PYMT_GRP_IMG_DARK+"' and status = :status order by gnCode";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;
	}
	
	
	public List<GeneralCode>  getLoginGnCode() throws Exception 
	{
		String sql = "From "+ GeneralCode.class.getSimpleName() +
				" where (gnCodeType = '"+MiBConstant.BLOCK_STATUS_STATE_TYPE+"' "+
				"or gnCodeType = 'redirectUrl' or gnCodeType='menuAccess' "
				+ "or gnCode = '"+MiBConstant.MAXDEVICEBINDKEY+"' "
						+ "or gnCode = '"+MiBConstant.TNC_GENERAL_CODE+"')"
			   + " and status = :status";
		List<GeneralCode> resultlist = em.createQuery(sql).setParameter("status", "A").getResultList();
		 
		resultlist = resultlist==null?new ArrayList():resultlist;
		
		return resultlist;

		
	}
}

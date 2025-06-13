package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.bean.ObLocationBean;
import com.silverlake.mleb.mcb.bean.ObServiceLocationRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

@Service
@Repository
@Transactional
public class LocationDAO extends DAO
{

	@Autowired
	MLEBMIBDAO dao;

	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;

	private static Logger log = LogManager.getLogger(LocationDAO.class);
	
	
	private String inputFilter(String inputData){

		String result = null;
		String matchCriteria = "_";			
		result = inputData.replaceAll(matchCriteria, " "); 

		return result; 
	}
	
	
	public String getWhereStatement(){
		
		String whereStm = " where 1=1 "; 
		
		return whereStm;  
	}
	
	
	public String parseSearchString(ObServiceLocationRequest requestData, String localecode)
	{
		
		 String searchString = " and MLEB_HLB.HLB_SERVICE_LOCATION.status <> '" + MiBConstant.PRO_DEACTIVATED + "' ";


		if(requestData.getObLocationBean()!=null){
			
			 
			if(requestData.getObLocationBean().getServiceType()!=null){
				
				if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("ATM")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_atm = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Branch")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_branch = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("eBanking Centre")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_ebanking = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Bureau de Change")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_bureau = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Business Centre")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_businessCenter = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Cash Deposit Machine")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_cashDepoMach = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Cheque Deposit Machine")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_chequeDepoMach = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("HLeBroking")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_hleBroking = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Priority Bank")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_priorityBank = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Trade Service")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_tradeService = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Hong Leong Credit Card")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_HLBCreditCard = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Hong Leong Debit Card")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_HLBDebitCard = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Kids Eat For Free")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_EatFree = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Full Fledged Islamic")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_islamic = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("MACH")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_mach = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Global Market")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_GlobMarket = 'Y' "; 
				}
				else if(requestData.getObLocationBean().getServiceType().equalsIgnoreCase("Consumer Loans")){
					searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_Loan = 'Y' "; 
				}
				 			
			}
			
			if(localecode!=null){
				searchString += "and MLEB_HLB.HLB_SERVICE_LOCATION_I18N.language_code = '" + localecode + "' "; 
			}
			
//			if(requestData.getObLocationBean().getLocationName()!=null){
//				searchString += "and LCASE(MLEB_HLB.HLB_SERVICE_LOCATION.location_name) = 'LCASE("+inputFilter(requestData.getObLocationBean().getLocationName().trim())+")' "; 			
//			}

			
			if(requestData.getObLocationBean().getState()!=null){
				searchString += "and LCASE(MLEB_HLB.HLB_SERVICE_LOCATION_I18N.state) like LCASE('%"+ inputFilter(requestData.getObLocationBean().getState().trim().replace("'", "''"))+"%') "; 			
			}
			
			if(requestData.getObLocationBean().getAddress()!=null){
				
				//searchString += "and LCASE(MLEB_HLB.HLB_SERVICE_LOCATION_I18N.address) like LCASE('%"+  inputFilter(requestData.getObLocationBean().getAddress().trim())+"%') "; 			
				searchString += "and LCASE(MLEB_HLB.HLB_SERVICE_LOCATION_I18N.street) like LCASE('%"+  inputFilter(requestData.getObLocationBean().getAddress().trim().replace("'", "''"))+"%') "; 
				searchString += "or LCASE(MLEB_HLB.HLB_SERVICE_LOCATION_I18N.street2) like LCASE('%"+  inputFilter(requestData.getObLocationBean().getAddress().trim().replace("'", "''"))+"%') "; 
				searchString += "or LCASE(MLEB_HLB.HLB_SERVICE_LOCATION_I18N.street3) like LCASE('%"+  inputFilter(requestData.getObLocationBean().getAddress().trim().replace("'", "''"))+"%') "; 
				searchString += "or LCASE(MLEB_HLB.HLB_SERVICE_LOCATION_I18N.state) like LCASE('%"+  inputFilter(requestData.getObLocationBean().getAddress().trim().replace("'", "''"))+"%') "; 
				searchString += "or LCASE(MLEB_HLB.HLB_SERVICE_LOCATION_I18N.country) like LCASE('%"+  inputFilter(requestData.getObLocationBean().getAddress().trim().replace("'", "''"))+"%') "; 
			}
			
			
			
		}
		
		return searchString; 
	}
	
	
	
	public List findbyDistance(ObServiceLocationRequest requestData, String localecode)
	{
		List<ObLocationBean> list = null;
		String whereStat = getWhereStatement(); 
		String othSearch = parseSearchString(requestData, localecode);
		

	
		String queryString = "select location_name as locationName, phone_no_1, address, branchTime, bizTime, atmTime, fax_no_1,"+
		         "serviceType_atm, serviceType_branch, serviceType_ebanking, serviceType_bureau, "+
		         "serviceType_businessCenter,  serviceType_cashDepoMach, serviceType_chequeDepoMach, "+
	 			 "serviceType_hleBroking, serviceType_priorityBank, serviceType_tradeService, "+
	 			 "serviceType_HLBCreditCard, serviceType_HLBDebitCard,  serviceType_EatFree, "+
	 			 "promo, ebiz_time, longtitude, latitude, state, service_type, serviceType_islamic, serviceType_mach, street, street2, street3, country, distance, "+
	 			 "serviceType_GlobMarket, serviceType_Loan " + //new for KH
	             "from (select MLEB_HLB.HLB_SERVICE_LOCATION_I18N.location_name, MLEB_HLB.HLB_SERVICE_LOCATION.phone_no_1, MLEB_HLB.HLB_SERVICE_LOCATION_I18N.address, " +
	             "MLEB_HLB.HLB_SERVICE_LOCATION.branchTime, MLEB_HLB.HLB_SERVICE_LOCATION.bizTime, MLEB_HLB.HLB_SERVICE_LOCATION.atmTime, " +
	             "MLEB_HLB.HLB_SERVICE_LOCATION.fax_no_1, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_atm, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_branch, " +
	             "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_ebanking, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_bureau, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_businessCenter,  " +
		         "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_cashDepoMach, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_chequeDepoMach, "+
	 			 "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_hleBroking, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_priorityBank, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_tradeService, "+
	 			 "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_HLBCreditCard, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_HLBDebitCard, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_EatFree, "+
	 			 "MLEB_HLB.HLB_SERVICE_LOCATION_I18N.promo, MLEB_HLB.HLB_SERVICE_LOCATION.ebiz_time, MLEB_HLB.HLB_SERVICE_LOCATION.longtitude, MLEB_HLB.HLB_SERVICE_LOCATION.latitude, " +
	 			 "MLEB_HLB.HLB_SERVICE_LOCATION_I18N.state, MLEB_HLB.HLB_SERVICE_LOCATION.service_type,"+ "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_islamic, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_mach, " +
	 			 "MLEB_HLB.HLB_SERVICE_LOCATION_I18N.street, MLEB_HLB.HLB_SERVICE_LOCATION_I18N.street2, MLEB_HLB.HLB_SERVICE_LOCATION_I18N.street3, MLEB_HLB.HLB_SERVICE_LOCATION_I18N.country, " +
	             "(acos(sin("+ requestData.getObLocationBean().getLatitude()+" * 0.0174532925)*sin(cast(MLEB_HLB.HLB_SERVICE_LOCATION.LATITUDE as decimal(12,8))  * 0.0174532925) + cos ("+ requestData.getObLocationBean().getLatitude()+" * 0.0174532925)*cos(cast(MLEB_HLB.HLB_SERVICE_LOCATION.LATITUDE as decimal(12,8)) * 0.0174532925)*cos((cast(MLEB_HLB.HLB_SERVICE_LOCATION.LONGTITUDE as decimal(12,8)) * 0.0174532925) - ("+ requestData.getObLocationBean().getLongtitude()+" * 0.0174532925)))) * 6373 As distance, "+
	             "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_GlobMarket, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_Loan " + //new for KH
	             "FROM MLEB_HLB.HLB_SERVICE_LOCATION " +
	             "left join MLEB_HLB.HLB_SERVICE_LOCATION_I18N on MLEB_HLB.HLB_SERVICE_LOCATION.LOC_ID = MLEB_HLB.HLB_SERVICE_LOCATION_I18N.LOC_ID "+ 
	             whereStat + othSearch +") as distance where distance <= "+requestData.getObLocationBean().getDistance()+" order by distance asc"; 
		/*
		String queryString = "select location_name as locationName, distance, phone_no_1, address, branchTime, bizTime, atmTime "+
				             "from (select location_name, " +
				             "(acos(sin("+ requestData.getObLocationBean().getLatitude()+" * 0.0174532925)*sin(cast(MLEB_HLB.HLB_SERVICE_LOCATION.LATITUDE as decimal(12,8))  * 0.0174532925) + cos ("+ requestData.getObLocationBean().getLatitude()+" * 0.0174532925)*cos(cast(MLEB_HLB.HLB_SERVICE_LOCATION.LATITUDE as decimal(12,8)) * 0.0174532925)*cos((cast(MLEB_HLB.HLB_SERVICE_LOCATION.LONGTITUDE as decimal(12,8)) * 0.0174532925) - ("+ requestData.getObLocationBean().getLongtitude()+" * 0.0174532925)))) * 6373 As distance "+
				             "FROM MLEB_HLB.HLB_SERVICE_LOCATION ) as distance where distance <= 12 "; 
		*/
		
		 
		
		String query = queryString;
		//log.info("**********************  query  dis**** "+query); 
		//List <ObLocationBean> LocationList = 
		String [] scalarList = {"locationName", "phone_no_1", "address",  "branchTime", "bizTime", "atmTime", "fax_no_1",
				"serviceType_atm", "serviceType_branch", "serviceType_ebanking", "serviceType_bureau", 
				"serviceType_businessCenter",  "serviceType_cashDepoMach", "serviceType_chequeDepoMach", 
				"serviceType_hleBroking", "serviceType_priorityBank", "serviceType_tradeService", 
				"serviceType_HLBCreditCard", "serviceType_HLBDebitCard", "serviceType_EatFree",
				"promo", "ebiz_time", "longtitude", "latitude", "state", "service_type", "serviceType_islamic", "serviceType_mach",
				"street", "street2", "street3", "country", "distance", "serviceType_GlobMarket", "serviceType_Loan"};
		//list = dao.selectSQLQuery(query,scalarList );    
		

		EntityManager em  = getEntityManager();
		Query qy = em.createNativeQuery(query);
	
		SQLQuery sqy = qy.unwrap(SQLQuery.class);
		
		for(String sc:scalarList)
		{
			sqy.addScalar(sc);
		}
		
		
        return qy.getResultList();		
	}
	
	

	
	
	
	
	
	
	
	public List findByAddress(ObServiceLocationRequest requestData, String localecode)
	{
		List<ObLocationBean> list = null;
		String whereStat = getWhereStatement(); 
		String othSearch = parseSearchString(requestData, localecode); 
			
		String queryString = "select location_name as locationName, phone_no_1, address, branchTime, bizTime, atmTime, fax_no_1,"+
		         "serviceType_atm, serviceType_branch, serviceType_ebanking, serviceType_bureau, "+
		         "serviceType_businessCenter,  serviceType_cashDepoMach, serviceType_chequeDepoMach, "+
	 			 "serviceType_hleBroking, serviceType_priorityBank, serviceType_tradeService, "+ 
	 			 "serviceType_HLBCreditCard, serviceType_HLBDebitCard,  serviceType_EatFree, "+
	 			 "promo, ebiz_time, longtitude, latitude, state, service_type, serviceType_islamic, serviceType_mach, " +
	 			 "street, street2, street3, country, addrInt, "+
	 			 "serviceType_GlobMarket, serviceType_Loan " + //new for KH
	             "from (select MLEB_HLB.HLB_SERVICE_LOCATION_I18N.location_name, MLEB_HLB.HLB_SERVICE_LOCATION.phone_no_1, MLEB_HLB.HLB_SERVICE_LOCATION_I18N.address, " +
	             "MLEB_HLB.HLB_SERVICE_LOCATION.branchTime, MLEB_HLB.HLB_SERVICE_LOCATION.bizTime, MLEB_HLB.HLB_SERVICE_LOCATION.atmTime, " +
	             "MLEB_HLB.HLB_SERVICE_LOCATION.fax_no_1, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_atm, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_branch, " +
	             "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_ebanking, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_bureau, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_businessCenter,  " +
		         "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_cashDepoMach, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_chequeDepoMach, "+
	 			 "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_hleBroking, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_priorityBank, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_tradeService, "+		        
	 			 "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_HLBCreditCard, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_HLBDebitCard, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_EatFree, "+
	 			 "MLEB_HLB.HLB_SERVICE_LOCATION_I18N.promo, MLEB_HLB.HLB_SERVICE_LOCATION.ebiz_time, MLEB_HLB.HLB_SERVICE_LOCATION.longtitude, MLEB_HLB.HLB_SERVICE_LOCATION.latitude, " +
	 			 "MLEB_HLB.HLB_SERVICE_LOCATION_I18N.state, MLEB_HLB.HLB_SERVICE_LOCATION.service_type,"+ "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_islamic, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_mach, " +
	             "MLEB_HLB.HLB_SERVICE_LOCATION_I18N.street, MLEB_HLB.HLB_SERVICE_LOCATION_I18N.street2, MLEB_HLB.HLB_SERVICE_LOCATION_I18N.street3, MLEB_HLB.HLB_SERVICE_LOCATION_I18N.country, " +
	             "MLEB_HLB.HLB_SERVICE_LOCATION_I18N.LOC_ID  As addrInt, "+
	             "MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_GlobMarket, MLEB_HLB.HLB_SERVICE_LOCATION.serviceType_Loan " + //new for KH
	             "FROM MLEB_HLB.HLB_SERVICE_LOCATION " +
	             "left join MLEB_HLB.HLB_SERVICE_LOCATION_I18N on MLEB_HLB.HLB_SERVICE_LOCATION.LOC_ID = MLEB_HLB.HLB_SERVICE_LOCATION_I18N.LOC_ID "+ 
	             whereStat + othSearch +") as addrInt where addrInt > 0  order by location_name asc"; 
		
		
		 		
		String query = queryString;
		
		log.info("*  ***  query  address*********************** "+query); 
		 
		String [] scalarList = {"locationName", "phone_no_1", "address", "branchTime", "bizTime", "atmTime", "fax_no_1",
				"serviceType_atm", "serviceType_branch", "serviceType_ebanking", "serviceType_bureau", 
				"serviceType_businessCenter",  "serviceType_cashDepoMach", "serviceType_chequeDepoMach", 
				"serviceType_hleBroking", "serviceType_priorityBank", "serviceType_tradeService",  //new for KH
				"serviceType_HLBCreditCard", "serviceType_HLBDebitCard", "serviceType_EatFree",
				"promo", "ebiz_time", "longtitude", "latitude", "state", "service_type", "serviceType_islamic", "serviceType_mach",
				"street", "street2", "street3", "country", "addrInt", "serviceType_GlobMarket", "serviceType_Loan"};
		//list = dao.selectSQLQuery(query,scalarList );    
		

		EntityManager em  = getEntityManager();
		Query qy = em.createNativeQuery(query);
	
		SQLQuery sqy = qy.unwrap(SQLQuery.class);
		
		for(String sc:scalarList)
		{
			sqy.addScalar(sc);
		}
		
		
        return qy.getResultList();		
	}
	
	
	
	
	
	
	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	

	
}
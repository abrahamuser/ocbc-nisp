package com.silverlake.mleb.mcb.constant;


public class MiBLocationConstant
{
	
	public static final Integer SERVICETYPE_SIZE = 8;
	
	
	public static final String ATM = "ATM";	
	public static final String BRANCH = "Branch";
	public static final String EBANKING = "eBanking Centre";
	public static final String BUREAU = "Bureau de Change";
	public static final String BIZCENTER = "Business Centre";
	public static final String CASH_DEPO = "Cash Deposit Machine";
	public static final String CHEQUE_DEPO = "Cheque Deposit Machine";
	public static final String HLEBROKING = "HLeBroking";
	public static final String PRIOTY = "Priority Bank";
	public static final String TRADE = "Trade Service";
	public static final String GLOBAL = "Global Market";
	public static final String LOAN = "Consumer Loan";
	public static final String CC = "Hong Leong Credit Card";
	public static final String DB = "Hong Leong Debit Card";
	public static final String KIDS_EAT_FREE = "Kids Eat For Free";
	public static final String FF_ISLAMIC = "Full Fledged Islamic";
	public static final String MACH = "MACH"; 
	
	
	public static final String getTypeCode(int idxServiceType, String locale)
	{
		String serviceTypeCode = "";
		
		String[] serviceType_MY = new String[8];
	 	
		serviceType_MY[0] = "ATM";	
		serviceType_MY[1] = "Branch";
		serviceType_MY[2] = "eBanking Centre";
//		serviceType_MY[3] = "Bureau de Change";
		serviceType_MY[3] = "Business Centre";
//		serviceType_MY[5] = "Cash Deposit Machine";
//		serviceType_MY[6] = "Cheque Deposit Machine";
//		serviceType_MY[7] = "HLeBroking";
		serviceType_MY[4] = "Priority Bank";
		serviceType_MY[5] = "Trade Service";
		serviceType_MY[6] = "Global Market";
		serviceType_MY[7] = "Consumer Loans";
//		serviceType_MY[10] = "Hong Leong Credit Card";
//		serviceType_MY[6] = "Hong Leong Debit Card";
//		serviceType_MY[12] = "Kids Eat For Free";
//		serviceType_MY[10] = "Full Fledged Islamic";
//		serviceType_MY[11] = "MACH"; 
		
		String[] serviceType_KH = new String[6];
	 	
		serviceType_KH[0] = "MÃ¡y ATM";	
		serviceType_KH[1] = "Chi nhÃ¡nh ";
		serviceType_KH[2] = "NgÃ¢n hÃ ng Ä�iá»‡n tá»­ ";
//		serviceType_VN[3] = "Bureau de Change";
		serviceType_KH[3] = "KhÃ¡ch hÃ ng Doanh nghiá»‡p ";
//		serviceType_VN[5] = "Cash Deposit Machine";
//		serviceType_VN[6] = "Cheque Deposit Machine";
//		serviceType_VN[7] = "HLeBroking";
		serviceType_KH[4] = "NgÃ¢n hÃ ng Æ¯u tiÃªn ";
		serviceType_KH[5] = "Thanh toÃ¡n quá»‘c táº¿";
//		serviceType_VN[10] = "Hong Leong Credit Card";
//		serviceType_VN[6] = "Hong Leong Debit Card";
//		serviceType_VN[12] = "Kids Eat For Free";
//		serviceType_VN[10] = "Full Fledged Islamic";
//		serviceType_VN[11] = "MACH"; 
		
		if(locale.equalsIgnoreCase("km_KH"))
		{
			return serviceType_KH[idxServiceType];	
		}
		else
		{
			return serviceType_MY[idxServiceType];	
		}
	}
	
	
	
	
}
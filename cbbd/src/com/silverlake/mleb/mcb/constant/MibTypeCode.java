package com.silverlake.mleb.mcb.constant;

import com.silverlake.mleb.mcb.util.PropertiesManager;


public class MibTypeCode
{
	
	public static final Integer TYPECODE_SIZE = 4;
	private static PropertiesManager pmgr = new PropertiesManager();
	public static final String getTypeCode(int idxTypecode){
	 		
	 String[] typeCode = new String[4];

	 typeCode[0] = "OWN_ACCT_FT";	
	 typeCode[1] = "INTRA_FT";
	 typeCode[2] = "INTER_FT";
	 typeCode[3] = "OVERSEA_FT";
	 
	 
	
//	 typeCode[0] = "OWN_ACCT_FT";	
//	 typeCode[1] = "INTRA_FT";
//	 typeCode[2] = "INTER_FT";
//	 typeCode[3] = "IBG";
//	 typeCode[4] = "PAY";
//	 typeCode[5] = "BILL_PYMT";
//	 typeCode[6] = "LOAN_PYMT";
//	 typeCode[7] = "CARD_PYMT";
//	 typeCode[8] = "PP_RLD";
//	 typeCode[9] = "JDC_RLD";
//	 typeCode[10] = "HL_EBROKING";
//	 typeCode[11] = "FD_PLCMT";
//	 typeCode[12] = "PEX";
//	 typeCode[13] = "CLOSE_MBDA";
//	 typeCode[14] = "CLOSE_DA";
//	 typeCode[15] = "CLOSE_DJSA";
//	 typeCode[16] = "BILL_PYMT_CC";
//	 typeCode[17] = "BILL_PYMT_FT";
//	 typeCode[18] = "FD_WITHDRAWAL";
//	 typeCode[19] = "DJ_WITHDRAWAL";
//	 typeCode[20] = "DJ_FAST_TRACK";
//	 typeCode[21] = "HL_EARN_FT";
	 
	 return typeCode[idxTypecode];	
	}
	
	public static final Integer ACC_TYPECODE_SIZE = 5;
	public static final Integer ACC_TYPECODE_SIZE_THIRDPT = 1;
	public static final Integer CURR_TYPECODE_SIZE = 3;
	public static final Integer RECURR_TYPECODE_SIZE = 3;
	
	public static final String getAccTypeCode(int idxTypecode){
 		
		String[] accTypeCode = new String[5];
		accTypeCode[0]=MiBConstant.ACCOUNT_TYPE_CODE_CASA;
		accTypeCode[1]=MiBConstant.ACCOUNT_TYPE_CODE_ATM_DEBIT_CARD;
		accTypeCode[2]=MiBConstant.ACCOUNT_TYPE_CODE_CREDITCARD;
		accTypeCode[3]=MiBConstant.ACCOUNT_TYPE_CODE_LOAN;
		accTypeCode[4]=MiBConstant.ACCOUNT_TYPE_CODE_OTHER;
		

		return accTypeCode[idxTypecode];	
	}

	public static final String getTypeDesc(int idxTypeDesc, String locale){
		//pmgr
		//String[] typeDesc_MY = new String[5];
	/*	typeDesc_MY[0]="Own Accounts Transfer";
		typeDesc_MY[1]="3rd Party HLB Transfer";
		typeDesc_MY[2]="FD Placement";
		typeDesc_MY[3]="FD Withdrawal";*/
		//return pmgr.getProperty(locale+"_hist_"+idxTypeDesc);
		
		String[] typeDesc = new String[5];
		typeDesc[0]="Own Accounts Transfer";
		typeDesc[1]="Other Accounts at HLBCAM";
		typeDesc[2]="Local Transfer";
		typeDesc[3]="Overseas Transfer";
		
		return typeDesc[idxTypeDesc];
		/*typeDesc_MY[0]= pmgr.getProperty(locale+"_hist_"+idxTypeDesc);
		typeDesc_MY[1]="3rd Party HLB Transfer";
		typeDesc_MY[2]="FD Placement";
		typeDesc_MY[3]="FD Withdrawal";
		
	
		String[] typeDesc_VN = new String[4];
		typeDesc_VN[0]="Own Accounts Transfer";
		typeDesc_VN[1]="3rd Party HLB Transfer";
		typeDesc_VN[2]="FD Placement";
		typeDesc_VN[3]="FD Withdrawal";
		
		if(locale.equalsIgnoreCase("km_KH"))
		{
			return typeDesc_VN[idxTypeDesc];
		}
		else
		{
			return typeDesc_MY[idxTypeDesc];
		}*/
		
		
	//typeDesc[0]="Own Accounts Transfer";
//		typeDesc[1]="3rd Party HLB Transfer";
//		typeDesc[2]="Interbank Transfer (IBG & Pay+)";
//		typeDesc[3]="Interbank GIRO (IBG)";
//		typeDesc[4]="Immediate (Pay+)";
//		typeDesc[5]="Online Bill / Retail Transaction";
//		typeDesc[6]="Loan/Financing Payment";
//		typeDesc[7]="Credit Card Payment";
//		typeDesc[8]="Prepaid Reload";
//		typeDesc[9]="Junior Debit Card Reload";
//		typeDesc[10]="HLeBroking Transfer";
//		typeDesc[11]="Mach FD Placement";
//		typeDesc[12]="Payment Express (PEx)";
//		typeDesc[13]="Closing of Money Box Deposit Account";
//		typeDesc[14]="Closing of Drive Account";
//		typeDesc[15]="Closing of Dream Jar Savings Account";
//		typeDesc[16]="Bill Payment (Credit Card)";
//		typeDesc[17]="Bill Payment";
//		typeDesc[18]="FD Withdrawal";
//		typeDesc[19]="Dream Jar withdrawal";
//		typeDesc[20]="Dream Jar Fast Track";
//		typeDesc[21]="Fortune Credit Card Transfer";

		
	}
	
	
	public static final String getRecurringTypeCode(int idxTypeDesc)
	{
		String[] typeCode = new String[3];
		typeCode[0]="D";
		typeCode[1]="W";
		typeCode[2]="M";
		
		return typeCode[idxTypeDesc];
	}
	
	public static final String getRecurringTypeDesc(int idxTypeDesc)
	{
		String[] typeDesc = new String[3];
		typeDesc[0]="Daily";
		typeDesc[1]="Weekly";
		typeDesc[2]="Monthly";
		
		return typeDesc[idxTypeDesc];
	}
	
	
	
//	public final static String TRANSTYPE_CODE_OWN_ACCT_FT = "OWN_ACCT_FT"; 
//	public final static String TRANSTYPE_CODE_INTRA_FT =  "INTRA_FT"; 
//	public final static String TRANSTYPE_CODE_INTER_FT =  "INTER_FT"; 
//	public final static String TRANSTYPE_CODE_IBG =  "IBG"; 
//	public final static String TRANSTYPE_CODE_PAYPLUS =  "PAY"; 
//	public final static String TRANSTYPE_CODE_BILL_PYMT =  "BILL_PYMT"; 
//	public final static String TRANSTYPE_CODE_LOAN_PYMT =   "LOAN_PYMT"; 
//	public final static String TRANSTYPE_CODE_CARD_PYMT =  "CARD_PYMT"; 
//	public final static String TRANSTYPE_CODE_PP_RLD =   "PP_RLD"; 
//	public final static String TRANSTYPE_CODE_JDC_RLD =  "JDC_RLD"; 
//	public final static String TRANSTYPE_CODE_HL_EBROKING =  "HL_EBROKING"; 
//	public final static String TRANSTYPE_CODE_FD_PLCMT = "FD_PLCMT"; 
//	public final static String TRANSTYPE_CODE_PEX =  "PEX"; 
//	public final static String TRANSTYPE_CODE_CLOSE_MBDA =  "CLOSE_MBDA"; 
//	public final static String TRANSTYPE_CODE_CLOSE_DA =  "CLOSE_DA"; 
//	public final static String TRANSTYPE_CODE_CLOSE_DJSA = "CLOSE_DJSA"; 
//	public final static String TRANSTYPE_CODE_BILL_PYMT_CC =  "BILL_PYMT_CC"; 
//	public final static String TRANSTYPE_CODE_BILL_PYMT_FT  = "BILL_PYMT_FT"; 
//	public final static String TRANSTYPE_CODE_FD_WITHDRAWAL =  "FD_WITHDRAWAL"; 
//	public final static String TRANSTYPE_CODE_DJ_WITHDRAWAL =  "DJ_WITHDRAWAL"; 
//	public final static String TRANSTYPE_CODE_DJ_FAST_TRACK =  "DJ_FAST_TRACK"; 
//	public final static String TRANSTYPE_CODE_HL_EARN_FT = "HL_EARN_FT"; 

		
//	public final static String TRANSTYPE_DESC_FT = "Own Accounts Transfer";
//	public final static String TRANSTYPE_DESC_THIRDPARTY =   "3rd Party HLB Transfer";
//	public final static String TRANSTYPE_DESC_INTERBANK =  "Interbank Transfer (IBG & Pay+)";
//	public final static String TRANSTYPE_DESC_IBG = "Interbank GIRO (IBG)";
//	public final static String TRANSTYPE_DESC_PAYPLUS = "Immediate (Pay+)";
//	public final static String TRANSTYPE_DESC_ONLINE = "Online Bill / Retail Transaction"; 
//	public final static String TRANSTYPE_DESC_LOAN = "Loan/Financing Payment"; 
//	public final static String TRANSTYPE_DESC_CC = "Credit Card Payment"; 
//	public final static String TRANSTYPE_DESC_PREPAID = "Prepaid Reload"; 
//	public final static String TRANSTYPE_DESC_JUNIOR = "Junior Debit Card Reload"; 
//	public final static String TRANSTYPE_DESC_HLEBROK = "HLeBroking Transfer"; 
//	public final static String TRANSTYPE_DESC_MACHFD = "Mach FD Placement";
//	public final static String TRANSTYPE_DESC_EXPRESS  = "Payment Express (PEx)";
//	public final static String TRANSTYPE_DESC_C_MB = "Closing of Money Box Deposit Account";
//	public final static String TRANSTYPE_DESC_C_DRIVE = "Closing of Drive Account";
//	public final static String TRANSTYPE_DESC_C_DJS = "Closing of Dream Jar Savings Account";
//	public final static String TRANSTYPE_DESC_BILLCC = "Bill Payment (Credit Card)";
//	public final static String TRANSTYPE_DESC_BILL = "Bill Payment";
//	public final static String TRANSTYPE_DESC_FDWITH = "FD Withdrawal";
//	public final static String TRANSTYPE_DESC_DJWITH = "Dream Jar withdrawal";
//	public final static String TRANSTYPE_DESC_DJFAST = "Dream Jar Fast Track";
//	public final static String TRANSTYPE_DESC_EARN =  "HL Earning Transfer";
	

	
	
	
}
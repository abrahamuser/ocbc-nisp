package comx.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

////@Entity
//@Table(name = "operation_hours")
public class OperationHours implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private Integer locId;
	
	private String atmDay1;
	private String atmDay2;
	private String atmDay3;
	private String atmDay4;
	private String atmDay5;
	private String atmTime1;
	private String atmTime2;
	private String atmTime3;
	private String atmTime4;
	private String atmTime5;
	private String atmTime6;
	private String atmTime7;
	private String atmTime8;
	private String atmTime9;
	private String atmTime10;
	
	private String bankingDay1;
	private String bankingDay2;
	private String bankingDay3;
	private String bankingDay4;
	private String bankingDay5;
	private String bankingTime1;
	private String bankingTime2;
	private String bankingTime3;
	private String bankingTime4;
	private String bankingTime5;
	private String bankingTime6;
	private String bankingTime7;
	private String bankingTime8;
	private String bankingTime9;
	private String bankingTime10;
	
	private String eBankingDay1;
	private String eBankingDay2;
	private String eBankingDay3;
	private String eBankingDay4;
	private String eBankingDay5;
	private String eBankingTime1;
	private String eBankingTime2;
	private String eBankingTime3;
	private String eBankingTime4;
	private String eBankingTime5;
	private String eBankingTime6;
	private String eBankingTime7;
	private String eBankingTime8;
	private String eBankingTime9;
	private String eBankingTime10;
	
	private String ccdmDay1;
	private String ccdmDay2;
	private String ccdmDay3;
	private String ccdmDay4;
	private String ccdmDay5;
	private String ccdmTime1;
	private String ccdmTime2;
	private String ccdmTime3;
	private String ccdmTime4;
	private String ccdmTime5;
	private String ccdmTime6;
	private String ccdmTime7;
	private String ccdmTime8;
	private String ccdmTime9;
	private String ccdmTime10;
	
	private String bdcDay1;
	private String bdcDay2;
	private String bdcDay3;
	private String bdcDay4;
	private String bdcDay5;
	private String bdcTime1;
	private String bdcTime2;
	private String bdcTime3;
	private String bdcTime4;
	private String bdcTime5;
	private String bdcTime6;
	private String bdcTime7;
	private String bdcTime8;
	private String bdcTime9;
	private String bdcTime10;
	
	private ServiceLocation serviceLocation;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "OperationHours_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "loc_id", nullable = false)
	public Integer getLocId() {
		return locId;
	}

	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="loc_id",referencedColumnName="loc_id",insertable=false,updatable=false)
	public ServiceLocation getServiceLocation() {
		return serviceLocation;
	}

	public void setServiceLocation(ServiceLocation serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "atm_day_1", length = 20,nullable = true)
	public String getAtmDay1() {
		return atmDay1;
	}

	public void setAtmDay1(String atmDay1) {
		this.atmDay1 = atmDay1;
	}

	@Column(name = "atm_day_2", length = 20,nullable = true)
	public String getAtmDay2() {
		return atmDay2;
	}

	public void setAtmDay2(String atmDay2) {
		this.atmDay2 = atmDay2;
	}

	@Column(name = "atm_day_3", length = 20,nullable = true)
	public String getAtmDay3() {
		return atmDay3;
	}

	public void setAtmDay3(String atmDay3) {
		this.atmDay3 = atmDay3;
	}

	@Column(name = "atm_day_4", length = 20,nullable = true)
	public String getAtmDay4() {
		return atmDay4;
	}

	public void setAtmDay4(String atmDay4) {
		this.atmDay4 = atmDay4;
	}

	@Column(name = "atm_day_5", length = 20,nullable = true)
	public String getAtmDay5() {
		return atmDay5;
	}

	public void setAtmDay5(String atmDay5) {
		this.atmDay5 = atmDay5;
	}

	@Column(name = "atm_time_1", length = 20,nullable = true)
	public String getAtmTime1() {
		return atmTime1;
	}

	public void setAtmTime1(String atmTime1) {
		this.atmTime1 = atmTime1;
	}

	@Column(name = "atm_time_2", length = 20,nullable = true)
	public String getAtmTime2() {
		return atmTime2;
	}

	public void setAtmTime2(String atmTime2) {
		this.atmTime2 = atmTime2;
	}

	@Column(name = "atm_time_3", length = 20,nullable = true)
	public String getAtmTime3() {
		return atmTime3;
	}

	public void setAtmTime3(String atmTime3) {
		this.atmTime3 = atmTime3;
	}

	@Column(name = "atm_time_4", length = 20,nullable = true)
	public String getAtmTime4() {
		return atmTime4;
	}

	public void setAtmTime4(String atmTime4) {
		this.atmTime4 = atmTime4;
	}

	@Column(name = "atm_time_5", length = 20,nullable = true)
	public String getAtmTime5() {
		return atmTime5;
	}

	public void setAtmTime5(String atmTime5) {
		this.atmTime5 = atmTime5;
	}

	@Column(name = "atm_time_6", length = 20,nullable = true)
	public String getAtmTime6() {
		return atmTime6;
	}

	public void setAtmTime6(String atmTime6) {
		this.atmTime6 = atmTime6;
	}

	@Column(name = "atm_time_7", length = 20,nullable = true)
	public String getAtmTime7() {
		return atmTime7;
	}

	public void setAtmTime7(String atmTime7) {
		this.atmTime7 = atmTime7;
	}

	@Column(name = "atm_time_8", length = 20,nullable = true)
	public String getAtmTime8() {
		return atmTime8;
	}

	public void setAtmTime8(String atmTime8) {
		this.atmTime8 = atmTime8;
	}

	@Column(name = "atm_time_9", length = 20,nullable = true)
	public String getAtmTime9() {
		return atmTime9;
	}

	public void setAtmTime9(String atmTime9) {
		this.atmTime9 = atmTime9;
	}

	@Column(name = "atm_time_10", length = 20,nullable = true)
	public String getAtmTime10() {
		return atmTime10;
	}

	public void setAtmTime10(String atmTime10) {
		this.atmTime10 = atmTime10;
	}

	@Column(name = "banking_day_1", length = 20,nullable = true)
	public String getBankingDay1() {
		return bankingDay1;
	}

	public void setBankingDay1(String bankingDay1) {
		this.bankingDay1 = bankingDay1;
	}
	
	@Column(name = "banking_day_2", length = 20,nullable = true)
	public String getBankingDay2() {
		return bankingDay2;
	}

	public void setBankingDay2(String bankingDay2) {
		this.bankingDay2 = bankingDay2;
	}

	@Column(name = "banking_day_3", length = 20,nullable = true)
	public String getBankingDay3() {
		return bankingDay3;
	}

	public void setBankingDay3(String bankingDay3) {
		this.bankingDay3 = bankingDay3;
	}

	@Column(name = "banking_day_4", length = 20,nullable = true)
	public String getBankingDay4() {
		return bankingDay4;
	}

	public void setBankingDay4(String bankingDay4) {
		this.bankingDay4 = bankingDay4;
	}

	@Column(name = "banking_day_5", length = 20,nullable = true)
	public String getBankingDay5() {
		return bankingDay5;
	}

	public void setBankingDay5(String bankingDay5) {
		this.bankingDay5 = bankingDay5;
	}

	@Column(name = "banking_time_1", length = 20,nullable = true)
	public String getBankingTime1() {
		return bankingTime1;
	}

	public void setBankingTime1(String bankingTime1) {
		this.bankingTime1 = bankingTime1;
	}

	@Column(name = "banking_time_2", length = 20,nullable = true)
	public String getBankingTime2() {
		return bankingTime2;
	}

	public void setBankingTime2(String bankingTime2) {
		this.bankingTime2 = bankingTime2;
	}

	@Column(name = "banking_time_3", length = 20,nullable = true)
	public String getBankingTime3() {
		return bankingTime3;
	}

	public void setBankingTime3(String bankingTime3) {
		this.bankingTime3 = bankingTime3;
	}

	@Column(name = "banking_time_4", length = 20,nullable = true)
	public String getBankingTime4() {
		return bankingTime4;
	}

	public void setBankingTime4(String bankingTime4) {
		this.bankingTime4 = bankingTime4;
	}
	
	@Column(name = "banking_time_5", length = 20,nullable = true)
	public String getBankingTime5() {
		return bankingTime5;
	}

	public void setBankingTime5(String bankingTime5) {
		this.bankingTime5 = bankingTime5;
	}

	@Column(name = "banking_time_6", length = 20,nullable = true)
	public String getBankingTime6() {
		return bankingTime6;
	}

	public void setBankingTime6(String bankingTime6) {
		this.bankingTime6 = bankingTime6;
	}

	@Column(name = "banking_time_7", length = 20,nullable = true)
	public String getBankingTime7() {
		return bankingTime7;
	}

	public void setBankingTime7(String bankingTime7) {
		this.bankingTime7 = bankingTime7;
	}

	@Column(name = "banking_time_8", length = 20,nullable = true)
	public String getBankingTime8() {
		return bankingTime8;
	}

	public void setBankingTime8(String bankingTime8) {
		this.bankingTime8 = bankingTime8;
	}

	@Column(name = "banking_time_9", length = 20,nullable = true)
	public String getBankingTime9() {
		return bankingTime9;
	}

	public void setBankingTime9(String bankingTime9) {
		this.bankingTime9 = bankingTime9;
	}

	@Column(name = "banking_time_10", length = 20,nullable = true)
	public String getBankingTime10() {
		return bankingTime10;
	}

	public void setBankingTime10(String bankingTime10) {
		this.bankingTime10 = bankingTime10;
	}

	@Column(name = "ebanking_day_1", length = 20,nullable = true)
	public String geteBankingDay1() {
		return eBankingDay1;
	}

	public void seteBankingDay1(String eBankingDay1) {
		this.eBankingDay1 = eBankingDay1;
	}

	@Column(name = "ebanking_day_2", length = 20,nullable = true)
	public String geteBankingDay2() {
		return eBankingDay2;
	}

	public void seteBankingDay2(String eBankingDay2) {
		this.eBankingDay2 = eBankingDay2;
	}

	@Column(name = "ebanking_day_3", length = 20,nullable = true)
	public String geteBankingDay3() {
		return eBankingDay3;
	}

	public void seteBankingDay3(String eBankingDay3) {
		this.eBankingDay3 = eBankingDay3;
	}

	@Column(name = "ebanking_day_4", length = 20,nullable = true)
	public String geteBankingDay4() {
		return eBankingDay4;
	}

	public void seteBankingDay4(String eBankingDay4) {
		this.eBankingDay4 = eBankingDay4;
	}
	
	@Column(name = "ebanking_day_5", length = 20,nullable = true)
	public String geteBankingDay5() {
		return eBankingDay5;
	}

	public void seteBankingDay5(String eBankingDay5) {
		this.eBankingDay5 = eBankingDay5;
	}

	@Column(name = "ebanking_time_1", length = 20,nullable = true)
	public String geteBankingTime1() {
		return eBankingTime1;
	}

	public void seteBankingTime1(String eBankingTime1) {
		this.eBankingTime1 = eBankingTime1;
	}

	@Column(name = "ebanking_time_2", length = 20,nullable = true)
	public String geteBankingTime2() {
		return eBankingTime2;
	}

	public void seteBankingTime2(String eBankingTime2) {
		this.eBankingTime2 = eBankingTime2;
	}

	@Column(name = "ebanking_time_3", length = 20,nullable = true)
	public String geteBankingTime3() {
		return eBankingTime3;
	}

	public void seteBankingTime3(String eBankingTime3) {
		this.eBankingTime3 = eBankingTime3;
	}

	@Column(name = "ebanking_time_4", length = 20,nullable = true)
	public String geteBankingTime4() {
		return eBankingTime4;
	}

	public void seteBankingTime4(String eBankingTime4) {
		this.eBankingTime4 = eBankingTime4;
	}

	@Column(name = "ebanking_time_5", length = 20,nullable = true)
	public String geteBankingTime5() {
		return eBankingTime5;
	}

	public void seteBankingTime5(String eBankingTime5) {
		this.eBankingTime5 = eBankingTime5;
	}

	@Column(name = "ebanking_time_6", length = 20,nullable = true)
	public String geteBankingTime6() {
		return eBankingTime6;
	}

	public void seteBankingTime6(String eBankingTime6) {
		this.eBankingTime6 = eBankingTime6;
	}

	@Column(name = "ebanking_time_7", length = 20,nullable = true)
	public String geteBankingTime7() {
		return eBankingTime7;
	}

	public void seteBankingTime7(String eBankingTime7) {
		this.eBankingTime7 = eBankingTime7;
	}

	@Column(name = "ebanking_time_8", length = 20,nullable = true)
	public String geteBankingTime8() {
		return eBankingTime8;
	}

	public void seteBankingTime8(String eBankingTime8) {
		this.eBankingTime8 = eBankingTime8;
	}

	@Column(name = "ebanking_time_9", length = 20,nullable = true)
	public String geteBankingTime9() {
		return eBankingTime9;
	}

	public void seteBankingTime9(String eBankingTime9) {
		this.eBankingTime9 = eBankingTime9;
	}

	@Column(name = "ebanking_time_10", length = 20,nullable = true)
	public String geteBankingTime10() {
		return eBankingTime10;
	}

	public void seteBankingTime10(String eBankingTime10) {
		this.eBankingTime10 = eBankingTime10;
	}

	@Column(name = "ccdm_day_1", length = 20,nullable = true)
	public String getCcdmDay1() {
		return ccdmDay1;
	}

	public void setCcdmDay1(String ccdmDay1) {
		this.ccdmDay1 = ccdmDay1;
	}

	@Column(name = "ccdm_day_2", length = 20,nullable = true)
	public String getCcdmDay2() {
		return ccdmDay2;
	}

	public void setCcdmDay2(String ccdmDay2) {
		this.ccdmDay2 = ccdmDay2;
	}

	@Column(name = "ccdm_day_3", length = 20,nullable = true)
	public String getCcdmDay3() {
		return ccdmDay3;
	}

	public void setCcdmDay3(String ccdmDay3) {
		this.ccdmDay3 = ccdmDay3;
	}

	@Column(name = "ccdm_day_4", length = 20,nullable = true)
	public String getCcdmDay4() {
		return ccdmDay4;
	}

	public void setCcdmDay4(String ccdmDay4) {
		this.ccdmDay4 = ccdmDay4;
	}

	@Column(name = "ccdm_day_5", length = 20,nullable = true)
	public String getCcdmDay5() {
		return ccdmDay5;
	}

	public void setCcdmDay5(String ccdmDay5) {
		this.ccdmDay5 = ccdmDay5;
	}
	
	@Column(name = "ccdm_time_1", length = 20,nullable = true)
	public String getCcdmTime1() {
		return ccdmTime1;
	}

	public void setCcdmTime1(String ccdmTime1) {
		this.ccdmTime1 = ccdmTime1;
	}

	@Column(name = "ccdm_time_2", length = 20,nullable = true)
	public String getCcdmTime2() {
		return ccdmTime2;
	}

	public void setCcdmTime2(String ccdmTime2) {
		this.ccdmTime2 = ccdmTime2;
	}

	@Column(name = "ccdm_time_3", length = 20,nullable = true)
	public String getCcdmTime3() {
		return ccdmTime3;
	}

	public void setCcdmTime3(String ccdmTime3) {
		this.ccdmTime3 = ccdmTime3;
	}

	@Column(name = "ccdm_time_4", length = 20,nullable = true)
	public String getCcdmTime4() {
		return ccdmTime4;
	}

	public void setCcdmTime4(String ccdmTime4) {
		this.ccdmTime4 = ccdmTime4;
	}

	@Column(name = "ccdm_time_5", length = 20,nullable = true)
	public String getCcdmTime5() {
		return ccdmTime5;
	}

	public void setCcdmTime5(String ccdmTime5) {
		this.ccdmTime5 = ccdmTime5;
	}

	@Column(name = "ccdm_time_6", length = 20,nullable = true)
	public String getCcdmTime6() {
		return ccdmTime6;
	}

	public void setCcdmTime6(String ccdmTime6) {
		this.ccdmTime6 = ccdmTime6;
	}

	@Column(name = "ccdm_time_7", length = 20,nullable = true)
	public String getCcdmTime7() {
		return ccdmTime7;
	}

	public void setCcdmTime7(String ccdmTime7) {
		this.ccdmTime7 = ccdmTime7;
	}

	@Column(name = "ccdm_time_8", length = 20,nullable = true)
	public String getCcdmTime8() {
		return ccdmTime8;
	}

	public void setCcdmTime8(String ccdmTime8) {
		this.ccdmTime8 = ccdmTime8;
	}

	@Column(name = "ccdm_time_9", length = 20,nullable = true)
	public String getCcdmTime9() {
		return ccdmTime9;
	}

	public void setCcdmTime9(String ccdmTime9) {
		this.ccdmTime9 = ccdmTime9;
	}

	@Column(name = "ccdm_time_10", length = 20,nullable = true)
	public String getCcdmTime10() {
		return ccdmTime10;
	}

	public void setCcdmTime10(String ccdmTime10) {
		this.ccdmTime10 = ccdmTime10;
	}

	@Column(name = "bdc_day_1", length = 20,nullable = true)
	public String getBdcDay1() {
		return bdcDay1;
	}

	public void setBdcDay1(String bdcDay1) {
		this.bdcDay1 = bdcDay1;
	}

	@Column(name = "bdc_day_2", length = 20,nullable = true)
	public String getBdcDay2() {
		return bdcDay2;
	}

	public void setBdcDay2(String bdcDay2) {
		this.bdcDay2 = bdcDay2;
	}

	@Column(name = "bdc_day_3", length = 20,nullable = true)
	public String getBdcDay3() {
		return bdcDay3;
	}

	public void setBdcDay3(String bdcDay3) {
		this.bdcDay3 = bdcDay3;
	}
	
	@Column(name = "bdc_day_4", length = 20,nullable = true)
	public String getBdcDay4() {
		return bdcDay4;
	}

	public void setBdcDay4(String bdcDay4) {
		this.bdcDay4 = bdcDay4;
	}

	@Column(name = "bdc_day_5", length = 20,nullable = true)
	public String getBdcDay5() {
		return bdcDay5;
	}

	public void setBdcDay5(String bdcDay5) {
		this.bdcDay5 = bdcDay5;
	}

	@Column(name = "bdc_time_1", length = 20,nullable = true)
	public String getBdcTime1() {
		return bdcTime1;
	}

	public void setBdcTime1(String bdcTime1) {
		this.bdcTime1 = bdcTime1;
	}

	@Column(name = "bdc_time_2", length = 20,nullable = true)
	public String getBdcTime2() {
		return bdcTime2;
	}

	public void setBdcTime2(String bdcTime2) {
		this.bdcTime2 = bdcTime2;
	}

	@Column(name = "bdc_time_3", length = 20,nullable = true)
	public String getBdcTime3() {
		return bdcTime3;
	}

	public void setBdcTime3(String bdcTime3) {
		this.bdcTime3 = bdcTime3;
	}

	@Column(name = "bdc_time_4", length = 20,nullable = true)
	public String getBdcTime4() {
		return bdcTime4;
	}

	public void setBdcTime4(String bdcTime4) {
		this.bdcTime4 = bdcTime4;
	}

	@Column(name = "bdc_time_5", length = 20,nullable = true)
	public String getBdcTime5() {
		return bdcTime5;
	}

	public void setBdcTime5(String bdcTime5) {
		this.bdcTime5 = bdcTime5;
	}

	@Column(name = "bdc_time_6", length = 20,nullable = true)
	public String getBdcTime6() {
		return bdcTime6;
	}

	public void setBdcTime6(String bdcTime6) {
		this.bdcTime6 = bdcTime6;
	}

	@Column(name = "bdc_time_7", length = 20,nullable = true)
	public String getBdcTime7() {
		return bdcTime7;
	}

	public void setBdcTime7(String bdcTime7) {
		this.bdcTime7 = bdcTime7;
	}

	@Column(name = "bdc_time_8", length = 20,nullable = true)
	public String getBdcTime8() {
		return bdcTime8;
	}

	public void setBdcTime8(String bdcTime8) {
		this.bdcTime8 = bdcTime8;
	}

	@Column(name = "bdc_time_9", length = 20,nullable = true)
	public String getBdcTime9() {
		return bdcTime9;
	}

	public void setBdcTime9(String bdcTime9) {
		this.bdcTime9 = bdcTime9;
	}

	@Column(name = "bdc_time_10", length = 20,nullable = true)
	public String getBdcTime10() {
		return bdcTime10;
	}

	public void setBdcTime10(String bdcTime10) {
		this.bdcTime10 = bdcTime10;
	}

}

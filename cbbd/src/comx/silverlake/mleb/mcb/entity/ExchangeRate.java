package comx.silverlake.mleb.mcb.entity;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "EXCHANGE_RATE")
public class ExchangeRate implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private String currencyCode;
	private BigDecimal bankBuyRate;
	private BigDecimal bankSellRate;
	private BigDecimal ttBuyRate;
	private BigDecimal ttSellRate;
	private BigDecimal cbBuyRate;
	private BigDecimal cbSellRate;
	private BigDecimal cbRate;
	private Integer currencyUnit;
	private Integer priorityIndex;
	private Date timeCreated;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "EXCHANGE_RATE_ID")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "currency_code")
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Column(name = "bank_buy_rate")
	public BigDecimal getBankBuyRate() {
		return bankBuyRate;
	}

	public void setBankBuyRate(BigDecimal bankBuyRate) {
		this.bankBuyRate = bankBuyRate;
	}
	@Column(name = "bank_sell_rate")
	public BigDecimal getBankSellRate() {
		return bankSellRate;
	}

	public void setBankSellRate(BigDecimal bankSellRate) {
		this.bankSellRate = bankSellRate;
	}
	@Column(name = "tt_buy_rate")
	public BigDecimal getTtBuyRate() {
		return ttBuyRate;
	}

	public void setTtBuyRate(BigDecimal ttBuyRate) {
		this.ttBuyRate = ttBuyRate;
	}
	@Column(name = "tt_sell_rate")
	public BigDecimal getTtSellRate() {
		return ttSellRate;
	}

	public void setTtSellRate(BigDecimal ttSellRate) {
		this.ttSellRate = ttSellRate;
	}
	@Column(name = "cb_buy_rate")
	public BigDecimal getCbBuyRate() {
		return cbBuyRate;
	}

	public void setCbBuyRate(BigDecimal cbBuyRate) {
		this.cbBuyRate = cbBuyRate;
	}
	@Column(name = "cb_sell_rate")
	public BigDecimal getCbSellRate() {
		return cbSellRate;
	}

	public void setCbSellRate(BigDecimal cbSellRate) {
		this.cbSellRate = cbSellRate;
	}
	@Column(name = "cb_rate")
	public BigDecimal getCbRate() {
		return cbRate;
	}

	public void setCbRate(BigDecimal cbRate) {
		this.cbRate = cbRate;
	}
	@Column(name = "currency_unit")
	public Integer getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(Integer currencyUnit) {
		this.currencyUnit = currencyUnit;
	}
	@Column(name = "priority_index")
	public Integer getPriorityIndex() {
		return priorityIndex;
	}

	public void setPriorityIndex(Integer priorityIndex) {
		this.priorityIndex = priorityIndex;
	}
	@Column(name = "time_created")
	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	
}

package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "td_product_category")
public class TDProductCategory implements java.io.Serializable{

	private Integer groupSeq;
	private String groupTypeEN;
	private String groupTypeIN;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "td_product_category_id")
	@Column(name="group_seq")
	public Integer getGroupSeq() {
		return groupSeq;
	}

	public void setGroupSeq(Integer groupSeq) {
		this.groupSeq = groupSeq;
	}

	@Column(name = "group_type_en")
	public String getGroupTypeEN() {
		return groupTypeEN;
	}

	public void setGroupTypeEN(String groupTypeEN) {
		this.groupTypeEN = groupTypeEN;
	}

	@Column(name = "group_type_in")
	public String getGroupTypeIN() {
		return groupTypeIN;
	}

	public void setGroupTypeIN(String groupTypeIN) {
		this.groupTypeIN = groupTypeIN;
	}
}

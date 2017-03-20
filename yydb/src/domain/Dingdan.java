package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dingdan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="dingdan")
public class Dingdan implements java.io.Serializable {

	// Fields
	@Id
    @Column(name = "orderId", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	@Column(name = "shopId")
	private Integer shopId;
	@Column(name = "goodId")
	private Integer goodId;
	@Column(name = "issueId")
	private Integer issueId;
	@Column(name = "lottlenum")
	private String lottlenum;
	@Column(name = "ext1")
	private String ext1;
	@Column(name = "ext2")
	private String ext2;

	// Constructors

	/** default constructor */
	public Dingdan() {
	}

	/** full constructor */
	public Dingdan(Integer shopId, Integer goodId, Integer issueId,
			String lottlenum, String ext1, String ext2) {
		this.shopId = shopId;
		this.goodId = goodId;
		this.issueId = issueId;
		this.lottlenum = lottlenum;
		this.ext1 = ext1;
		this.ext2 = ext2;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getGoodId() {
		return this.goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getIssueId() {
		return this.issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public String getLottlenum() {
		return this.lottlenum;
	}

	public void setLottlenum(String lottlenum) {
		this.lottlenum = lottlenum;
	}

	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return this.ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

}
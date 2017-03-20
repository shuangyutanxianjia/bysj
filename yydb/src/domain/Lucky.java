package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Lucky entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="lucky")
public class Lucky implements java.io.Serializable {

	// Fields
	@Id
    @Column(name = "luckId", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer luckId;
	@Column(name = "issueId")
	private Integer issueId;
	@Column(name = "orderId")
	private Integer orderId;
	@Column(name = "lucknum")
	private String lucknum;

	// Constructors

	/** default constructor */
	public Lucky() {
	}

	/** minimal constructor */
	public Lucky(Integer luckId) {
		this.luckId = luckId;
	}

	/** full constructor */
	public Lucky(Integer luckId, Integer issueId, Integer orderId,
			String lucknum) {
		this.luckId = luckId;
		this.issueId = issueId;
		this.orderId = orderId;
		this.lucknum = lucknum;
	}

	// Property accessors

	public Integer getLuckId() {
		return this.luckId;
	}

	public void setLuckId(Integer luckId) {
		this.luckId = luckId;
	}

	public Integer getIssueId() {
		return this.issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getLucknum() {
		return this.lucknum;
	}

	public void setLucknum(String lucknum) {
		this.lucknum = lucknum;
	}

}
package domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Lottle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="lottle")
public class Lottle implements java.io.Serializable {

	// Fields
	@Id
    @Column(name = "lottleId", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lottleId;
	@Column(name = "issueId")
	private Integer issueId;
	@Column(name = "goodId")
	private Integer goodId;
	@Column(name = "userId")
	private Integer userId;
	@Column(name = "lottlenum")
	private String lottlenum;
	@Column(name = "createDate")
	private Date createDate;
	@Column(name = "doneDate")
	private Date doneDate;
	@Column(name = "ext1")
	private String ext1;
	@Column(name = "ext2")
	private String ext2;

	// Constructors

	/** default constructor */
	public Lottle() {
	}

	/** full constructor */
	public Lottle(Integer issueId, Integer goodId, Integer userId,
			String lottlenum, Timestamp createDate, Timestamp doneDate,
			String ext1, String ext2) {
		this.issueId = issueId;
		this.goodId = goodId;
		this.userId = userId;
		this.lottlenum = lottlenum;
		this.createDate = createDate;
		this.doneDate = doneDate;
		this.ext1 = ext1;
		this.ext2 = ext2;
	}

	// Property accessors

	public Integer getLottleId() {
		return this.lottleId;
	}

	public void setLottleId(Integer lottleId) {
		this.lottleId = lottleId;
	}

	public Integer getIssueId() {
		return this.issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public Integer getGoodId() {
		return this.goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLottlenum() {
		return this.lottlenum;
	}

	public void setLottlenum(String lottlenum) {
		this.lottlenum = lottlenum;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDoneDate() {
		return this.doneDate;
	}

	public void setDoneDate(Date doneDate) {
		this.doneDate = doneDate;
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
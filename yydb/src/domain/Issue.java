package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Issue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="issue")
public class Issue implements java.io.Serializable {

	// Fields
	@Id
    @Column(name = "issueId", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer issueId;
	@Column(name = "goodId")
	private Integer goodId;
	@Column(name = "goodIssue")
	private Integer goodIssue;
	@Column(name = "totalnum")
	private Integer totalnum;
	@Column(name = "soldnum")
	private Integer soldnum;
	@Column(name = "isdraw")
	private Integer isdraw;
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
	public Issue() {
	}

	/** minimal constructor */
	public Issue(Integer issueId) {
		this.issueId = issueId;
	}

	/** full constructor */
	public Issue(Integer issueId, Integer goodId, Integer goodIssue,
			Integer totalnum, Integer soldnum, Integer isdraw, Date createDate,
			Date doneDate, String ext1, String ext2) {
		this.issueId = issueId;
		this.goodId = goodId;
		this.goodIssue = goodIssue;
		this.totalnum = totalnum;
		this.soldnum = soldnum;
		this.isdraw = isdraw;
		this.createDate = createDate;
		this.doneDate = doneDate;
		this.ext1 = ext1;
		this.ext2 = ext2;
	}

	// Property accessors

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

	public Integer getGoodIssue() {
		return this.goodIssue;
	}

	public void setGoodIssue(Integer goodIssue) {
		this.goodIssue = goodIssue;
	}

	public Integer getTotalnum() {
		return this.totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public Integer getSoldnum() {
		return this.soldnum;
	}

	public void setSoldnum(Integer soldnum) {
		this.soldnum = soldnum;
	}

	public Integer getIsdraw() {
		return this.isdraw;
	}

	public void setIsdraw(Integer isdraw) {
		this.isdraw = isdraw;
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
package domain;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment")
public class Comment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	@Id
	@Column(name = "commentId", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	@Column(name = "userId")
	private Integer userId;
	@Column(name = "goodId")
	private Integer goodId;
	@Column(name = "sortId")
	private Integer sortId;
	@Column(name = "content")
	private String content;
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
	public Comment() {
	}

	/** full constructor */
	public Comment(Integer userId, Integer goodId, Integer sortId,
			String content, Date createDate, Date doneDate,
			String ext1, String ext2) {
		this.userId = userId;
		this.goodId = goodId;
		this.sortId = sortId;
		this.content = content;
		this.createDate = createDate;
		this.doneDate = doneDate;
		this.ext1 = ext1;
		this.ext2 = ext2;
	}

	// Property accessors

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGoodId() {
		return this.goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getSortId() {
		return this.sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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
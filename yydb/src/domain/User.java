package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user")
public class User implements java.io.Serializable {

	// Fields
	@Id
    @Column(name = "userId", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "userName")
	private String userName;
	@Column(name = "userPsw")
	private String userPsw;
	@Column(name = "userIcon")
	private String userIcon;
	@Column(name = "userPhone")
	private String userPhone;
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
	public User() {
	}

	/** minimal constructor */
	public User(Integer userId) {
		this.userId = userId;
	}

	/** full constructor */
	public User(Integer userId, String userName, String userPsw,
			String userIcon, String userPhone, Date createDate, Date doneDate,
			String ext1, String ext2) {
		this.userId = userId;
		this.userName = userName;
		this.userPsw = userPsw;
		this.userIcon = userIcon;
		this.userPhone = userPhone;
		this.createDate = createDate;
		this.doneDate = doneDate;
		this.ext1 = ext1;
		this.ext2 = ext2;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPsw() {
		return this.userPsw;
	}

	public void setUserPsw(String userPsw) {
		this.userPsw = userPsw;
	}

	public String getUserIcon() {
		return this.userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
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
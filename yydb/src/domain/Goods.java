package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="goods")
public class Goods implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5894701771720653318L;
	/**
	 * 
	 */
	// Fields
	@Id
    @Column(name = "goodId", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer goodId;
	@Column(name = "goodName")
	private String goodName;
	@Column(name = "typeId")
	private Integer typeId;
	@Column(name = "goodIcon")
	private String goodIcon;
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
	public Goods() {
	}

	/** minimal constructor */
	public Goods(Integer goodId) {
		this.goodId = goodId;
	}

	/** full constructor */
	public Goods(Integer goodId, String goodName, Integer typeId,
			String goodIcon, Date createDate, Date doneDate, String ext1,
			String ext2) {
		this.goodId = goodId;
		this.goodName = goodName;
		this.typeId = typeId;
		this.goodIcon = goodIcon;
		this.createDate = createDate;
		this.doneDate = doneDate;
		this.ext1 = ext1;
		this.ext2 = ext2;
	}

	// Property accessors

	public Integer getGoodId() {
		return this.goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return this.goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getGoodIcon() {
		return this.goodIcon;
	}

	public void setGoodIcon(String goodIcon) {
		this.goodIcon = goodIcon;
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
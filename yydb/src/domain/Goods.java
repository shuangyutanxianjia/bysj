package domain;

import java.util.Date;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private Integer goodId;
	private String goodName;
	private Integer typeId;
	private String goodIcon;
	private Date createDate;
	private Date doneDate;
	private String ext1;
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
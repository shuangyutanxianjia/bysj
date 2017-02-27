package domain;

import java.util.Date;

/**
 * Goodtype entity. @author MyEclipse Persistence Tools
 */

public class Goodtype implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;
	private Date createDate;
	private Date doneDate;

	// Constructors

	/** default constructor */
	public Goodtype() {
	}

	/** minimal constructor */
	public Goodtype(Integer typeId) {
		this.typeId = typeId;
	}

	/** full constructor */
	public Goodtype(Integer typeId, String typeName, Date createDate,
			Date doneDate) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.createDate = createDate;
		this.doneDate = doneDate;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

}
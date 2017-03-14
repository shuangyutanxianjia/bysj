package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Goodtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="goodtype")
public class Goodtype implements java.io.Serializable {

	// Fields
	@Id
    @Column(name = "typeId", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer typeId;
	@Column(name = "typeName")
	private String typeName;
	@Column(name = "createDate")
	private Date createDate;
	@Column(name = "doneDate")
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
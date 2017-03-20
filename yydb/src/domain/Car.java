package domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Car entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="car")
public class Car  implements java.io.Serializable {


    // Fields    
	@Id
    @Column(name = "carId", insertable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Integer carId;
	@Column(name = "userId")
     private Integer userId;
	@Column(name = "goodId")
     private Integer goodId;
	@Column(name = "issueId")
     private Integer issueId;
	@Column(name = "createDate")
     private Timestamp createDate;
	@Column(name = "doneDate")
     private Timestamp doneDate;
	@Column(name = "ext1")
     private String ext1;
	@Column(name = "ext2")
     private String ext2;



    // Constructors

    /** default constructor */
    public Car() {
    }

	/** minimal constructor */
    public Car(Integer carId) {
        this.carId = carId;
    }
    
    /** full constructor */
    public Car(Integer carId, Integer userId, Integer goodId, Integer issueId, Timestamp createDate, Timestamp doneDate, String ext1, String ext2) {
        this.carId = carId;
        this.userId = userId;
        this.goodId = goodId;
        this.issueId = issueId;
        this.createDate = createDate;
        this.doneDate = doneDate;
        this.ext1 = ext1;
        this.ext2 = ext2;
    }

   
    // Property accessors

    public Integer getCarId() {
        return this.carId;
    }
    
    public void setCarId(Integer carId) {
        this.carId = carId;
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

    public Integer getIssueId() {
        return this.issueId;
    }
    
    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getDoneDate() {
        return this.doneDate;
    }
    
    public void setDoneDate(Timestamp doneDate) {
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
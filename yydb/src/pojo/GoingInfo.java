package pojo;

import java.util.Date;

import domain.Goods;

/**
 * 用于记录商品表 以及对应期号
 * @author xuguowei
 *
 */
public class GoingInfo {
	private Goods goods; //商品信息
	private int soldnum;
	private int lessnum;
	public int getLessnum() {
		return lessnum;
	}
	public void setLessnum(int lessnum) {
		this.lessnum = lessnum;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	private int totalnum;
	private String percent;
	private int issueId;	//对应期号
	private Date endDate;   //开奖时间
	
	
	
	public int getSoldnum() {
		return soldnum;
	}
	public void setSoldnum(int soldnum) {
		this.soldnum = soldnum;
	}
	
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}

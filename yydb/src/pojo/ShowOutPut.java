package pojo;

import java.util.List;

public class ShowOutPut {
	private List<GoingInfo> goingList;  //正在进行中的商品,记录对应期号
	private List<GoingInfo> openingList;
	private List<GoingInfo> openedList;
	public List<GoingInfo> getGoingList() {
		return goingList;
	}
	public void setGoingList(List<GoingInfo> goingList) {
		this.goingList = goingList;
	}
	public List<GoingInfo> getOpeningList() {
		return openingList;
	}
	public void setOpeningList(List<GoingInfo> openingList) {
		this.openingList = openingList;
	}
	public List<GoingInfo> getOpenedList() {
		return openedList;
	}
	public void setOpenedList(List<GoingInfo> openedList) {
		this.openedList = openedList;
	}
	
	
}

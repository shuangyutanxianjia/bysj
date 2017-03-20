package action;

import java.util.List;

import javax.annotation.Resource;

import pojo.LottleOutput;
import pojo.ShowOutPut;

import service.ShowService;

import com.opensymphony.xwork2.ActionSupport;

public class ShowAction extends ActionSupport{
	@Resource
	ShowService showService;
	
	private ShowOutPut output;
	



	public ShowOutPut getOutput() {
		return output;
	}



	public void setOutput(ShowOutPut output) {
		this.output = output;
	}



	/**
	 * 展示商品信息获取
	 */
	public String showGoods(){
		output = showService.getallgoods();
		return "success";
	}
	
	
}

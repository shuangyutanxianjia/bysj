package action;

import javax.annotation.Resource;

import pojo.LottleOutput;
import service.ShowService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LottleAction extends ActionSupport{
	@Resource
	ShowService showService;
	
	private LottleOutput lottle;
	public LottleOutput getLottle() {
		return lottle;
	}



	public void setLottle(LottleOutput lottle) {
		this.lottle = lottle;
	}
	
	/**
	 * 获取中奖信息
	 */
	public String getlottle(){
		lottle = showService.getLottle();
		return "success";
	}
}

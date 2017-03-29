package action;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import pojo.GoodsInfoOutput;
import service.GoodServiceImpl;
import service.ShopServiceImpl;
@Component
@SuppressWarnings("serial")
public class ShopAction extends ActionSupport{
	@Resource
	GoodServiceImpl goodService;
	@Resource
	ShopServiceImpl shopService;
	//入参
	private String shopnum;
	private String userId;
	private String goodIssue;
	private String goodsId;	
	public void setGoodIssue(String goodIssue) {
		this.goodIssue = goodIssue;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public void setShopnum(String shopnum) {
		this.shopnum = shopnum;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//出参
	private GoodsInfoOutput goodoutput;	
	private String retMsg;
	public GoodsInfoOutput getGoodoutput() {
		return goodoutput;
	}
	public String getRetMsg() {
		return retMsg;
	}
	
	/**
	 * 获取商品类别
	 * @return
	 */
	public String getGoodsInfo() {
		// 获取商品信息
		goodoutput = goodService.getGoodsInfo(goodsId, goodIssue);
		return "success";
	}

	/**
	 * 商品购买
	 * @throws ParseException 
	 */
	public String shopGoods() throws ParseException{
		retMsg = shopService.shopping(goodsId, goodIssue, shopnum, userId);
		return "shop_success";
	}
}

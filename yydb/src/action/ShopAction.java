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
	
	private String shopnum;
	private String userId;
	private String goodIssue;
	private String goodsId;
	private GoodsInfoOutput goodoutput;

	public String getGoodIssue() {
		return goodIssue;
	}

	public void setGoodIssue(String goodIssue) {
		this.goodIssue = goodIssue;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public GoodsInfoOutput getGoodoutput() {
		return goodoutput;
	}

	public void setGoodoutput(GoodsInfoOutput goodoutput) {
		this.goodoutput = goodoutput;
	}
	
	public String getShopnum() {
		return shopnum;
	}

	public void setShopnum(String shopnum) {
		this.shopnum = shopnum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		shopService.shopping(goodsId, goodIssue, shopnum, userId);
		return "success";
	}
}

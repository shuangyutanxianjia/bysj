package action;

import javax.annotation.Resource;

import pojo.GoodsInfoOutput;
import service.GoodServiceImpl;

public class ShopAction {
	@Resource
	GoodServiceImpl goodService;
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
	/**
	 * 获取商品类别
	 * @return
	 */
	public String getGoodsInfo() {
		// 获取商品信息
		goodoutput = goodService.getGoodsInfo(goodsId, goodIssue);
		return "success";
	}
}

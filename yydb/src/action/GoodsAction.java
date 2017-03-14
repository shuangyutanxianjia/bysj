package action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import pojo.GoodsInfoOutput;
import pojo.GoodsTypeOutput;
import pojo.addGoodsInput;

import service.GoodServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import domain.Goods;

@Component
@SuppressWarnings("serial")
public class GoodsAction extends ActionSupport {
	@Resource
	GoodServiceImpl goodService;

	private String goodType;
	private GoodsTypeOutput output;

	private String goodsTitle;
	private String goodsTypeId;
	private String details;

	private File Icon1;
	private File Icon2;
	private File Icon3;
	private File Icon4;
	private File Icon5;
	private List<Goods> goods;
	private String goodsId;
	private String totalnum;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public File getIcon1() {
		return Icon1;
	}

	public void setIcon1(File icon1) {
		Icon1 = icon1;
	}

	public File getIcon2() {
		return Icon2;
	}

	public void setIcon2(File icon2) {
		Icon2 = icon2;
	}

	public GoodsTypeOutput getOutput() {
		return output;
	}

	public void setOutput(GoodsTypeOutput output) {
		this.output = output;
	}

	public File getIcon3() {
		return Icon3;
	}

	public void setIcon3(File icon3) {
		Icon3 = icon3;
	}

	public File getIcon4() {
		return Icon4;
	}

	public void setIcon4(File icon4) {
		Icon4 = icon4;
	}

	public File getIcon5() {
		return Icon5;
	}

	public void setIcon5(File icon5) {
		Icon5 = icon5;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getGoodType() {
		return goodType;
	}

	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}

	/**
	 * 添加商品类别
	 * 
	 * @return
	 */
	public String addType() {
		goodService.addgoodType(goodType);
		System.out.println(666);
		return "success";
	}

	public String searchType() {
		output = goodService.searchType();
		return "success";
	}

	public String addGoods() {
		addGoodsInput input = new addGoodsInput();
		input.setGoodsTitle(goodsTitle);
		input.setGoodsTypeId(goodsTypeId);
		input.setDetails(details);
		File[] files = { Icon1, Icon2, Icon3, Icon4, Icon5 };
		input.setFiles(files);
		goodService.addgoods(input);
		return "success";
	}

	// 根据商品类型获取商品
	public String searchByType() {

		goods = goodService.searchByType(goodsTypeId);
		return "success";
	}

	// 设置商品期号
	public String addIssue() {
		goodService.addIssue(goodsId, totalnum);
		return "success";
	}

}

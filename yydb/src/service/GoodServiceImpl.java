package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pojo.GoodsInfoOutput;
import pojo.GoodsTypeOutput;
import pojo.addGoodsInput;

import util.PictureUpload;


import dao.GoodsDAO;
import dao.GoodtypeDAO;
import dao.IssueDAO;
import domain.Goods;
import domain.Goodtype;
import domain.Issue;

@Service
public class GoodServiceImpl {
	@Autowired
	GoodtypeDAO goodtypeDAO;
	@Autowired
	GoodsDAO goodsDAO;
	@Autowired
	IssueDAO issueDAO;
	
	/**
	 * 添加商品类型
	 * @param goodType
	 */
	@Transactional
	public void addgoodType(String goodTypeName){
		Goodtype goodtype = new Goodtype();
		//String goodTypename = formatString.String2unicode(goodTypeName);
		goodtype.setTypeName(goodTypeName);		
		goodtypeDAO.save(goodtype);
	}
	
	/**
	 * 查看商品类型
	 * 
	 */

	@Transactional
	public GoodsTypeOutput searchType(){
		GoodsTypeOutput output = new GoodsTypeOutput();
		List<Goodtype> goodtypes = new ArrayList();
		goodtypes = goodtypeDAO.findAll();
		output.setGoodtypes(goodtypes);
		
		return output;
	}
	/**
	 * 根据商品类别获取商品信息
	 */
	@Transactional
	public List<Goods> searchByType(String goodsType){
		List<Goods> goods;
		
		goods = goodsDAO.findByTypeId(Integer.parseInt(goodsType));
		
		return goods;
		
		
	}
	/**
	 * 添加商品信息
	 */
	@SuppressWarnings("static-access")
	@Transactional
	public void addgoods(addGoodsInput input){
		PictureUpload upload = new PictureUpload();
		Goods good = new Goods();
		good.setGoodName(input.getGoodsTitle());
		System.out.println(input.getGoodsTypeId());
		int typeId = Integer.parseInt(input.getGoodsTypeId());
		good.setTypeId(typeId);
		System.out.println(good.getTypeId());
		good.setGoodIcon("/goods");
		good.setExt1(input.getDetails());
		
		goodsDAO.save(good);
		
		String Url = "/goods/"+good.getGoodId()+"/"+good.getGoodId();
		try {
			//图片上传
			for(int i = 0;i<5;i++){
				upload.uploadpic(Url+i+".jpg", (input.getFiles())[i]);
			}
		} catch (IOException e) {
			// TODO 保存图片出错 事务回滚
			goodsDAO.delete(good);
			e.printStackTrace();
		}
		
	}
	/**
	 * 设置商品期号
	 */
	@Transactional
	public void addIssue(String goodId,String totalnum){
		Issue issue = new Issue();
		issue.setGoodId(Integer.parseInt(goodId));
		issue.setIsdraw(0);
		issue.setSoldnum(0);
		issue.setTotalnum(Integer.parseInt(totalnum));
		issueDAO.save(issue);
		
	}
	
	/**
	 * 获取商品信息
	 */
	@Transactional
	public GoodsInfoOutput getGoodsInfo(String goodId,String issueId){
		GoodsInfoOutput output = new GoodsInfoOutput();
		//通过商品id 获取商品的基本信息
		Goods good = new Goods();
		int GoodId = Integer.parseInt(goodId);
		good = goodsDAO.findById(GoodId);
		
		//通过商品id 获取商品是否已经开奖(issue表)
		Issue issue = new Issue(); 
		int IssueId =Integer.parseInt(issueId);
		issue = issueDAO.findById(IssueId);
		
		output.setGood(good);
		output.setIssue(issue);		
		return output;
	}
	
	
}

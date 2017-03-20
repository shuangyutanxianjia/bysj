package service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.DingdanDAO;
import dao.IssueDAO;
import dao.ShopDAO;
import domain.Dingdan;
import domain.Issue;
import domain.Shop;

@Service
public class ShopServiceImpl {
	@Autowired
	ShopDAO shopDAO;
	@Autowired
	DingdanDAO dingdanDAO;
	@Autowired
	IssueDAO issueDAO;
	@Autowired
	CreateLuckyNumService createLucky;

	/**
	 * 商品购买
	 * 
	 * @throws ParseException
	 */
	@Transactional
	public void shopping(String goodsId, String issueId, String shopnum,
			String userId) throws ParseException {
		// 插入购物表
		Shop shop = new Shop();
		shop.setUserId(Integer.parseInt(userId));
		Date date = new Date();
		shop.setCreateDate(date);
		shop.setExt1(shopnum); //记录购买数量
		shopDAO.save(shop);
		int shopNum = Integer.parseInt(shopnum);
		// 更新期表
		Issue issue = issueDAO.findById(Integer.parseInt(issueId));
		int soldnum = issue.getSoldnum() + shopNum;
		int totalnum = issue.getTotalnum();
		if (soldnum < totalnum) {
			issue.setSoldnum(soldnum);
		} else {
			issue.setSoldnum(soldnum);
			issue.setIsdraw(1);// 将是否开奖置为待揭晓
			Date doneDate = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   long rightTime = (long) (doneDate.getTime() + 10 * 60 * 1000); //把当前得到的时间用date.getTime()的方法写成时间戳的形式，再加上8小时对应的毫秒数
			   String newtime = sd.format(rightTime);//把得到的新的时间戳再次格式化成时间的格式
			   doneDate = sd.parse(newtime);
			   issue.setDoneDate(doneDate);
			System.out.println("----------------------------------------------设置开奖时间"+doneDate);
			System.out.println("------------商品再次上架");
			Issue newissue = new Issue();
			newissue.setGoodId(issue.getGoodId());
			newissue.setIsdraw(0);
			newissue.setTotalnum(issue.getTotalnum());
			newissue.setSoldnum(0);
			Date createDate = new Date();
			newissue.setCreateDate(createDate);
			issueDAO.save(newissue);
			System.out.println(createDate+"第"+newissue.getIssueId()+"期开售");
			
		}
		issueDAO.save(issue);
		
		// 生成幸运数字
		for (int i = 0; i < shopNum; i++) {
			String lottlenum = createLucky.creatLuckyNum(issueId,
					shop.getShopId());
			Dingdan dingdan = new Dingdan();
			dingdan.setGoodId(Integer.parseInt(goodsId));
			dingdan.setIssueId(Integer.parseInt(issueId));
			dingdan.setShopId(shop.getShopId());
			System.out.println("幸运号码：" + lottlenum);
			dingdan.setLottlenum(lottlenum);
			// 保存订单表
			dingdanDAO.save(dingdan);
		}

	}
}

package service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cached.IssuedBasisCache;
import dao.DingdanDAO;
import dao.IssueDAO;
import dao.ShopDAO;
import domain.Dingdan;
import domain.Issue;
import domain.Shop;
import domain.User;

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
	@Autowired
	IssuedBasisCache issueBasisCache;
	@Autowired
	UserServiceImpl userservice;

	/**
	 * 商品购买
	 * update 2017-03-29  增加用户余额判断，增加缓存机制 by xgw
	 * @throws ParseException
	 */
	@Transactional
	public String shopping(String goodsId, String issueId, String shopnum,
			String userId) throws ParseException {
		String retMsg = ""; //返回前台信息
		double balance = 0;
		int shopNum = Integer.parseInt(shopnum); //获取是购买数量
		//购买流程开始
		try {
			//使用memcached缓存进行商品缓存更新
			Issue issue = issueBasisCache.get(Integer.parseInt(issueId)); //通过id获取缓存中的信息
			int soldnum = issue.getSoldnum() + shopNum;
			int totalnum = issue.getTotalnum();
			// 更新期表
			if (soldnum < totalnum) { //直接更新
				issue.setSoldnum(soldnum);
				issueBasisCache.replace(Integer.parseInt(issueId), issue); //更新缓存信息
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
				issueBasisCache.replace(Integer.parseInt(issueId), issue); //更新缓存信息
			}
			String payresult = userservice.ShopPay(Integer.parseInt(userId), shopNum); //支付结果
			
			if(payresult.equals("pay_success")){
				//进行数据库操作
				// 插入购物表
				Shop shop = new Shop();
				shop.setUserId(Integer.parseInt(userId));
				Date date = new Date();
				shop.setCreateDate(date);
				shop.setExt1(shopnum); //记录购买数量
				shopDAO.save(shop);
				
				if(soldnum == totalnum){ 
					//后台自动添加商品期号
					System.out.println("------------商品再次上架");
					Issue newissue = new Issue();
					newissue.setGoodId(issue.getGoodId());
					newissue.setIsdraw(0);
					newissue.setTotalnum(issue.getTotalnum());
					newissue.setSoldnum(0);
					Date createDate = new Date();
					newissue.setCreateDate(createDate);
					issueDAO.save(newissue);
					issueBasisCache.set(newissue); //新增商品缓存信息加载
					System.out.println(createDate+"第"+newissue.getIssueId()+"期开售");									
				}
				Issue issue2 =new Issue();
				issue2 = issue;
				issueDAO.save(issue2);  //数据库进行操作
				retMsg = "sold_success";
			}else{
				issue = issueBasisCache.get(Integer.parseInt(issueId)); //通过id获取缓存中的信息
				int replacenum = issue.getSoldnum() - shopNum;
				issue.setSoldnum(replacenum);
				 issueBasisCache.replace(Integer.parseInt(issueId), issue);
				retMsg = payresult;				
			}
			
		} catch (Exception e) {
			retMsg = "sold_failed";
		}					
			return retMsg;		
		
		/*		 更新期表未使用缓存（商品销售没有进行用户余额判断）	
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
		*/
		
// 生成幸运数字 （没有用处的一张表）
//		for (int i = 0; i < shopNum; i++) {
//			String lottlenum = createLucky.creatLuckyNum(issueId,
//					shop.getShopId());
//			Dingdan dingdan = new Dingdan();
//			dingdan.setGoodId(Integer.parseInt(goodsId));
//			dingdan.setIssueId(Integer.parseInt(issueId));
//			dingdan.setShopId(shop.getShopId());
//			System.out.println("幸运号码：" + lottlenum);
//			dingdan.setLottlenum(lottlenum);
//			// 保存订单表
//			dingdanDAO.save(dingdan);
//		}

	}
	
}

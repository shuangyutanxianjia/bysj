package Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cached.GoodsBasisCache;

import dao.GoodsDAO;
import dao.IssueDAO;
import dao.LottleDAO;
import dao.LuckyDAO;
import dao.ShopDAO;
import domain.Goods;
import domain.Issue;
import domain.Lottle;
import domain.Lucky;
import domain.Shop;
@Component
public class OpenLottleTask {
	@Autowired
	IssueDAO issueDAO;
	@Autowired
	ShopDAO shopDAO;
	@Autowired
	LuckyDAO luckDAO;
	@Autowired
	LottleDAO lottleDAO;
	@Autowired
	GoodsDAO goodsDAO;
	@Autowired
	GoodsBasisCache goodsBasisCache;
	
	@Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次    
	@Transactional
	public void openLottle(){
		Date  date = new Date();
		System.out.println(date+"--------------开始执行后台开奖流程");
		List<Issue> issueList = new ArrayList<Issue>();//获取所有进行中的stage
		issueList = issueDAO.findByIsdraw(1);
		for (Issue issue : issueList) {
			Date  endDate= issue.getDoneDate();
			Date  date1 = new Date();		
			if(endDate.getTime() <= date.getTime()){
				System.out.println(date1+"第"+issue.getIssueId()+"期开奖");
				System.out.println("--------------启动生成中奖号码");
				List<Shop> tempList = shopDAO.get20shop();
				Long jishu = (long) 0;
				for (Shop temp : tempList) {
					Date time = temp.getCreateDate();
					DateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");;
					jishu += Long.parseLong(formate.format(time));
					System.out.println(jishu);
				}
				String lottlenum = jishu%issue.getTotalnum()+1000001+"";
				System.out.println("-----------------中奖号码为"+lottlenum);
				
				//查找中奖者信息
				Lucky lucky = new Lucky();
				lucky.setIssueId(issue.getIssueId());
				lucky.setLucknum(lottlenum);
				List<Lucky> luckyList = luckDAO.findByExample(lucky);
				for (Lucky lucky2 : luckyList) {
					Shop shop = shopDAO.findById(lucky2.getOrderId());
					System.out.println("-------中奖者id:"+shop.getUserId());
					Lottle lottle = new Lottle();
					lottle.setGoodId(issue.getGoodId());
					lottle.setLottlenum(lottlenum);
					lottle.setIssueId(issue.getIssueId());
					lottle.setUserId(shop.getUserId());
					Date createDate = new Date();
					lottle.setCreateDate(createDate);
					lottleDAO.save(lottle);
					System.out.println("------------------------中奖表已更新");
				}
				
				issue.setIsdraw(2);
				issueDAO.save(issue);
			}
		}
	}
	
	@Scheduled(cron="0 0 12 * * ?")   //每天12点触发
	//@Scheduled(cron="0/5 * *  * * ? ") //每6小时执行一次缓存加载
	@Transactional
	public void InitCached(){
		//加载商品缓存
		Date  date = new Date();
		System.out.println(date+"--------------开始商品缓存加载");
		List<Goods> goodList = new ArrayList<Goods>();//获取所有进行中的stage
		goodList = goodsDAO.findAll();
		if(!goodList.isEmpty()){
			for (Goods goods : goodList) {
				goodsBasisCache.set(goods);
			}
			System.out.println("----------------产品缓存加载成功");
		}else{
			System.out.println("----------------商品查询出错，缓存加载失败！");
		}
	}
}

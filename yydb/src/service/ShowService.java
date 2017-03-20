package service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pojo.GoingInfo;
import pojo.LottleInfo;
import pojo.LottleOutput;
import pojo.ShowOutPut;

import dao.GoodsDAO;
import dao.IssueDAO;
import dao.LottleDAO;
import dao.UserDAO;
import domain.Goods;
import domain.Issue;
import domain.Lottle;
import domain.User;

@Service
public class ShowService {
	@Autowired
	GoodsDAO goodsDAO;
	@Autowired
	IssueDAO issueDAO;
	@Autowired
	LottleDAO lottleDAO;
	@Autowired
	UserDAO userDAO;
	/**
	 * 获取所有商品用于前台展示
	 * 
	 */
	@SuppressWarnings("null")
	@Transactional
	public ShowOutPut getallgoods() {
		List<Goods> goodList = goodsDAO.findAll();
		ShowOutPut showoutput = new ShowOutPut();
		List<GoingInfo> goingList = new ArrayList<GoingInfo>();
		List<GoingInfo> openingList =new ArrayList<GoingInfo>();
		List<GoingInfo> openedList = new ArrayList<GoingInfo>();
		for (Goods goods : goodList) {
			Issue issue = new Issue();
			issue.setGoodId(goods.getGoodId());
			// 开始查找商品
			for (int i = 0; i < 3; i++) {
				issue.setIsdraw(i);
				List<Issue> issueList = issueDAO.findByExample(issue);
				if (i == 0) { // 正在进行中
					for (Issue issue2 : issueList) {
						GoingInfo going = new GoingInfo();
						going.setGoods(goods);
						going.setIssueId(issue2.getIssueId());
						going.setEndDate(issue2.getDoneDate());
						going.setSoldnum(issue2.getSoldnum());
						going.setTotalnum(issue2.getTotalnum());
						int soldnum = issue2.getSoldnum();
						int totalnum = issue2.getTotalnum();
						int lessnum = totalnum-soldnum;
						going.setLessnum(lessnum);
						double percent = (double)soldnum/totalnum;
						NumberFormat num = NumberFormat.getPercentInstance(); 
						num.setMaximumIntegerDigits(3); 
						num.setMaximumFractionDigits(2); 
						going.setPercent(num.format(percent)+"");						
						goingList.add(going);
					}
				} else if (i == 1) { // 开奖中
					for (Issue issue2 : issueList) {
						GoingInfo going = new GoingInfo();
						going.setGoods(goods);
						going.setIssueId(issue2.getIssueId());
						going.setEndDate(issue2.getDoneDate());
						openingList.add(going);
					}

				} else {
					for (Issue issue2 : issueList) {
						GoingInfo going = new GoingInfo();
						going.setGoods(goods);
						going.setIssueId(issue2.getIssueId());
						going.setEndDate(issue2.getDoneDate());
						openedList.add(going);
					}
				}
			}

		}

		showoutput.setGoingList(goingList);
		showoutput.setOpenedList(openedList);
		showoutput.setOpeningList(openingList);
		return showoutput;

	}
	/**
	 * 进行中奖信息查询
	 */
	@Transactional
	public LottleOutput getLottle(){
		LottleOutput output = new LottleOutput();
		List<LottleInfo> lottleInfoList = new ArrayList<LottleInfo>();
		List<Lottle> lottleList = lottleDAO.get5lottle();
		for (Lottle lottle : lottleList) {
			LottleInfo lottleInfo = new LottleInfo();
			lottleInfo.setLottle(lottle);
			User user = new User();
			user = userDAO.findById(lottle.getUserId());
			lottleInfo.setUserName(user.getUserName());
			lottleInfoList.add(lottleInfo);
		}
		output.setLottleinfo(lottleInfoList);
		return output;
	}
}

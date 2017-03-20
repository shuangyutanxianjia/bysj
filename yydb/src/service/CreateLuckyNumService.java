package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import util.SuiJiShu;

import dao.IssueDAO;
import dao.LuckyDAO;
import domain.Issue;
import domain.Lucky;

@Service
public class CreateLuckyNumService {
	@Autowired
	IssueDAO issueDAO;
	@Autowired
	LuckyDAO luckyDAO;
	/**
	 * 购买时创建随机数
	 * @param issueId
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public String creatLuckyNum(String issueId, int shopId) {
		//查找出已经生成过的幸运号码
		List<Lucky> luckList = luckyDAO.findByIssueId(Integer.parseInt(issueId));
		List<String> sl = new ArrayList<String>();
		if(!luckList.isEmpty()){
			for(Lucky luck :luckList){
				sl.add(luck.getLucknum());
			}
		}
		Issue issue = issueDAO.findById(Integer.parseInt(issueId)); //获取对应期号实体
		int num = issue.getTotalnum();
		System.out.println(issue.getSoldnum());
		int ln = SuiJiShu.getSuijishu(num, sl);	//获取随机数
		String lnum = ln+"";
		Lucky lucky = new Lucky();
		lucky.setIssueId(Integer.parseInt(issueId));
		lucky.setOrderId(shopId);
		lucky.setLucknum(lnum);
		luckyDAO.save(lucky);
		return lnum;
	}
}

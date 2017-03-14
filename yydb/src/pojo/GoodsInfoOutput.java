package pojo;

import java.util.List;

import domain.Goods;
import domain.Issue;

public class GoodsInfoOutput {
	Goods good;
	public Goods getGood() {
		return good;
	}
	public void setGood(Goods good) {
		this.good = good;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	Issue issue;
}

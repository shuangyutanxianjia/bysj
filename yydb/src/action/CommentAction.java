package action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import pojo.CommentOutput;
import service.CommentService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class CommentAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1568591658758974373L;

	@Resource
	CommentService commentService;
	//入参
	private String goodsId;
	private String userId;
	private String sortId;
	private String content;
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	//出参
	private List<CommentOutput> output;
	private CommentOutput comoutput;
	public CommentOutput getComoutput() {
		return comoutput;
	}
	public List<CommentOutput> getOutput() {
		return output;
	}

	/**
	 * 获取产品评论信息
	 * @return
	 */
	public String getComment(){
		output = commentService.getComment(goodsId);
		return "getcomment_success";
	}
	
	/**
	 * 更新评论信息
	 * @return
	 */
	public String reflashComment(){
		comoutput = commentService.reflashComment(goodsId, sortId, userId, content);
		return "reflash_success";
	}
}

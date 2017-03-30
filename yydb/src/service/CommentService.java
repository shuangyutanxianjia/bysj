package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pojo.CommentOutput;

import dao.CommentDAO;
import dao.UserDAO;
import domain.Comment;
import domain.User;

@Service
public class CommentService {
	@Resource
	CommentDAO commentDAO;
	@Resource
	UserDAO userDAO;
	@Transactional
	public List<CommentOutput> getComment(String goodsId){
		List<CommentOutput> outputList = new ArrayList<CommentOutput>();
		//查找对应商品所有的评论
		Comment comment = new Comment();
		comment.setGoodId(Integer.parseInt(goodsId));
		List<Comment> commentList = commentDAO.findByExample(comment);
		if(commentList.isEmpty()){
			//评论为空
		}else{
			for (Comment comment2 : commentList) {
				CommentOutput commentoutput = new CommentOutput();
				//获取用户名
				User user = new User();
				user = userDAO.findById(comment2.getUserId());
				commentoutput.setCommentDate(comment2.getCreateDate());
				commentoutput.setCommentId(comment2.getCommentId());
				commentoutput.setContent(comment2.getContent());
				commentoutput.setSortID(comment2.getSortId());
				commentoutput.setUserId(comment2.getUserId());
				commentoutput.setUserIcon(user.getUserIcon());
				commentoutput.setUserName(user.getUserName());
				outputList.add(commentoutput);
			}
		}	
		return outputList;
	}
	@Transactional
	public CommentOutput reflashComment(String goodsId,String sortId,String userId,String content){
		Comment comment = new Comment();
		comment.setGoodId(Integer.parseInt(goodsId));
		comment.setSortId(Integer.parseInt(sortId));
		comment.setUserId(Integer.parseInt(userId));
		comment.setContent(content);
		Date date = new Date();
		comment.setCreateDate(date);
		commentDAO.save(comment);
		//返回该条评论信息
		CommentOutput output = new CommentOutput();
		User user = new User();
		user = userDAO.findById(comment.getUserId());
		output.setCommentDate(comment.getCreateDate());
		output.setCommentId(comment.getCommentId());
		output.setContent(comment.getContent());
		output.setSortID(comment.getSortId());
		output.setUserId(comment.getUserId());
		output.setUserIcon(user.getUserIcon());
		output.setUserName(user.getUserName());
		
		return output;
	}
}

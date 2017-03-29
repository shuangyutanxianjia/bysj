package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pojo.LoginOutPut;
import util.SendMaliUtil;

import dao.UserDAO;
import domain.User;



@Service
public class UserServiceImpl {
	@Autowired
	UserDAO userDAO ;
	
	@SuppressWarnings("null")
	/**
	 * 登录信息校验
	 * @param username
	 * @param password
	 * @return
	 */
	@Transactional
	public LoginOutPut LoginChk(String loginname ,String password,String loginType){
		LoginOutPut output = new LoginOutPut();
		User user = new User();
		if(loginType.equals("1")){
			user.setUserPhone(loginname);
		}else{
			user.setExt1(loginname);
		}
		user.setUserPsw(password);
		
		ArrayList<User> users = (ArrayList<User>) userDAO.findByExample(user);
		if(users.isEmpty()){
			output.setFlag(false);
		}else{
			output.setUsers(users);
			output.setFlag(true);
		}
		return output;
	}
	/**
	 * 用户注册验证
	 * @param username
	 * @param password
	 */
	@Transactional
	public String UserRegistCode(String Registname,String RegistType,String RegistCode){
		SendMaliUtil sendMailUtil = new SendMaliUtil();
		String retMsg ="";
		if(RegistType.equals("1")){ //手机号注册
			retMsg = "mailsend_success"; //手机验证默认成功，因为要花钱接API
		}else{						//邮箱注册
			//调用邮箱发送接口
			String Title = "注册通知";
			String Content = "亲爱的"+Registname+"：您好！您本次验证码为："+RegistCode+"，1元云购绝对不会向您索要验证码，为了您的账户安全，打死都不要告诉任何人，如非本人操作，可不用理会。--该邮件为毕设所用";
			if(sendMailUtil.sendMsg(Registname, Title, Content)){
				retMsg = "mailsend_success";
			}else{
				retMsg = "mailsend_failed";
			}			
		}
		return retMsg;		
	}
	/**
	 * 用户注册
	 */
	@Transactional
	public String UserRegist(String Registname,String RegistType,String RegistPsw){
		User user = new User();
		if(RegistType.equals("1")){ //手机号注册
			user.setUserPhone(Registname);		
		}else{						//邮箱注册
			user.setExt1(Registname);			
		}
		user.setUserPsw(RegistPsw); //保存密码
		user.setUserIcon("defaultIcon/default.jpg"); //初始化头像
		user.setExt2("0.0");//初始化用户金额
		userDAO.save(user);
		
		String userName = "User."+user.getUserId();	//生成用户初始化昵称
		user.setUserName(userName);
		userDAO.save(user);		
		return "regist_success"; //注册成功
	}
	
	/**
	 * 用户头像设置
	 * @param userId
	 * @param iconUrl
	 */
	@Transactional
	public void UserIconSet(int userId,String iconUrl){
		User user = new User();
		//ͨ通过用户ID查找
		user = userDAO.findById(userId);
		//设置头像路径
		user.setUserIcon(iconUrl);
		//保存
		userDAO.save(user);
	}
	
	/**
	 * 注册用户名校验
	 */
	@Transactional
	public String RegistNameChk(String RegistName,String RegistType){
		String retMsg = "success";
		List<User> user = new ArrayList<User>();
		if(RegistType.equals("1")){ //手机号注册
			user = userDAO.findByUserPhone(RegistName);
			if(!user.isEmpty()){
				retMsg = "该手机号码已被注册";
			}
		}else{						//邮箱注册
			user = userDAO.findByExt1(RegistName);
			if(!user.isEmpty()){
				retMsg = "该邮箱已被注册";
			}
		}	
		return retMsg;
	}
	
	/**
	 * 获取用户余额信息
	 */
	@Transactional
	public String getBalance(String UserId){
		String retMsg ="";
		User user = new User();
		user = userDAO.findById(Integer.parseInt(UserId));
		if(user!=null){
			retMsg = user.getExt2();
		}else{
			retMsg = "获取余额失败，请联系管理员";
		}
		return retMsg;
	}
	/**
	 * 修改用户信息
	 */
	@Transactional
	public String changeUserInfo(String userId,String changeName ,String changeType){
		String retMsg = "";
		User user = new User();
		user = userDAO.findById(Integer.parseInt(userId));
		if(user!=null){	//修改昵称
			if("1".equals(changeType)){
				user.setUserName(changeName);
			}else if("2".equals(changeType)){ //修改绑定手机
				user.setUserPhone(changeName);
			}else if("3".equals(changeType)){ //修改绑定邮箱
				user.setExt1(changeName);
			}else{
				retMsg = "用户信息修改失败";
			}
			retMsg = "change_success";
		}else{
			retMsg = "用户信息获取失败，请联系管理员";
		}
		return retMsg;
	}
	
	/**
	 * 注册用户获取随机6位验证码
	 */
	@Transactional
	public String GetRadom(){
		 int randNum = 1 + (int)(Math.random() * ((999999 - 1) + 1));
		  return randNum+"";
	}
	
	/**
	 * 用户消费
	 */
	@Transactional
	public String ShopPay(int userId,int pay){
		String payflag= "";
		try {
			User user = new User();
			user = userDAO.findById(userId);
			double balance =Double.parseDouble(user.getExt2()); //获取用户余额信息
			if(balance >pay){
				balance = balance - pay; //进行扣款操作
				user.setExt2(balance+"");
				userDAO.save(user);
				payflag = "pay_success";	//支付成功
			}else{
				payflag = "noenough_balance"; //用户余额不足
			}
		} catch (Exception e) {
			payflag = "pay_failed"; //系统原因导致支付失败
		}
		return payflag;
	}
}

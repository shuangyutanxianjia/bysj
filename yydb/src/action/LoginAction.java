package action;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import pojo.LoginOutPut;

import service.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

import domain.User;
@Component
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	@Resource
	private UserServiceImpl userServiceImpl;
	private String username;
	private String userpassword;
	private LoginOutPut output;
	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public LoginOutPut getOutput() {
		return output;
	}

	public void setOutput(LoginOutPut output) {
		this.output = output;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	
	
	
	public String usernameCheck() throws Exception{
		return null;
	}

	public String loginChk(){
		//获取request对象 
		HttpServletRequest request = ServletActionContext.getRequest();			
		 //获取session
        HttpSession httpSession = request.getSession();
        
        User user = (User) httpSession.getAttribute("User");
        if(user == null){
        	flag = "false";
        }else{
        	flag = "true";
        }
		
		return "success";
		
	}
	
	public String userLogin() {
		
		try {
			//获取request对象 
			HttpServletRequest request = ServletActionContext.getRequest();			
			 //获取session
	        HttpSession httpSession = request.getSession();
			 
			System.out.println("------------------session="+httpSession.getId());
			output = userServiceImpl.LoginChk(username, userpassword);
			if(output.isFlag()){
				ArrayList<User> users = output.getUsers();
				for (User user: users) {
					httpSession.setAttribute("User", user);
				}											
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	public String userRegist(){
		
		userServiceImpl.UserRegist(username, userpassword);
		flag = "true";
		return "success";		
	}

	@Override
	public String toString() {
		return "LoginAction [username=" + username + ", userpassword="
				+ userpassword + "]";
	}
}

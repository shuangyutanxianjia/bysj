package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import util.PictureUpload;

import com.opensymphony.xwork2.ActionSupport;

import domain.User;
@Component
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	@Resource
	private UserServiceImpl userServiceImpl;
	
	private String loginname;
	private String loginType;
	private String userpassword;
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	

	private LoginOutPut output;
	private String flag;
	private File avatar_file;
	
	public File getAvatar_file() {
		return avatar_file;
	}

	public void setAvatar_file(File avatar_file) {
		this.avatar_file = avatar_file;
	}

	public String getFlag() {
		return flag;
	}

	public LoginOutPut getOutput() {
		return output;
	}

	public void setOutput(LoginOutPut output) {
		this.output = output;
	}

	/**
	 * 检查登录状态
	 * @return
	 */
	public String loginChk(){
		//获取request对象
		HttpServletRequest request = ServletActionContext.getRequest();			
		//获取httpsession对象
        HttpSession httpSession = request.getSession();        
        User user = (User) httpSession.getAttribute("User");
        if(user == null){
        	flag = "false";
        }else{
        	flag = "true";
        }		
		return "loginChk_success";
		
	}
	/**
	 * 用户登录
	 * @return
	 */
	public String userLogin() {
		
		try {
			//获取request对象
			HttpServletRequest request = ServletActionContext.getRequest();			
			 //获取session对象
	        HttpSession httpSession = request.getSession();
			 
			System.out.println("------------------session="+httpSession.getId());
			output = userServiceImpl.LoginChk(loginname, userpassword,loginType);
			if(output.isFlag()){
				ArrayList<User> users = output.getUsers();
				for (User user: users) {
					httpSession.setAttribute("User", user);
				}											
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login_success";
		
	}
	

	/**
	 * 
	 * @throws IOException 
	 */
	public String userIcon() throws IOException{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession httpSession = request.getSession();

		User user = (User) httpSession.getAttribute("User");
		
		PictureUpload upload = new PictureUpload();
		File toFile = upload.uploadpic("/User/"+user.getUserName()+"/"+user.getUserName()+".jpg", avatar_file);
		
        String userIconUrl = "User/"+user.getUserName()+"/"+toFile.getName();
        userServiceImpl.UserIconSet(user.getUserId(), userIconUrl);
        
        
		System.out.println("file_name ="+avatar_file.getName());
		System.out.println("----------------");
		return "success";
	}
	
}

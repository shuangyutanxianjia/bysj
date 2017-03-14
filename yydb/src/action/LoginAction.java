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
	private String username;
	private String userpassword;
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
	
	
	/**
	 * �û�����
	 * @return
	 * @throws Exception
	 */
	
	public String usernameCheck() throws Exception{
		return null;
	}

	/**
	 *ڵ�½״̬
	 * @return
	 */
	public String loginChk(){
		//
		HttpServletRequest request = ServletActionContext.getRequest();			
		 //
        HttpSession httpSession = request.getSession();
        
        User user = (User) httpSession.getAttribute("User");
        if(user == null){
        	flag = "false";
        }else{
        	flag = "true";
        }
		
		return "success";
		
	}
	/**
	 * 
	 * @return
	 */
	public String userLogin() {
		
		try {
			//
			HttpServletRequest request = ServletActionContext.getRequest();			
			 //
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
	
	/**
	 * 
	 * @return
	 */
	public String userRegist(){
		
		userServiceImpl.UserRegist(username, userpassword);
		flag = "true";
		return "success";		
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
		
//        InputStream is = new FileInputStream(avatar_file);  
//          
//        String uploadPath = ServletActionContext.getServletContext()  
//                .getRealPath("/upload");  
//          
//        System.out.println(uploadPath);
//        File toFile = new File(uploadPath,avatar_file.getName());  
//          
//        OutputStream os = new FileOutputStream(toFile);  
//  
//        byte[] buffer = new byte[1024];  
//  
//        int length = 0;  
//  
//        while ((length = is.read(buffer)) > 0) {  
//            os.write(buffer, 0, length);  
//        }  
//         
//        is.close();  
//          
//        os.close();  
		
        String userIconUrl = "User/"+user.getUserName()+"/"+toFile.getName();
        userServiceImpl.UserIconSet(user.getUserId(), userIconUrl);
        
        
		System.out.println("file_name ="+avatar_file.getName());
		System.out.println("----------------");
		return "success";
	}
	
	@Override
	public String toString() {
		return "LoginAction [username=" + username + ", userpassword="
				+ userpassword + "]";
	}
}

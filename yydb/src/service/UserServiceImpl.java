package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pojo.LoginOutPut;

import dao.UserDAO;
import domain.User;



@Service
public class UserServiceImpl {
	@Autowired
	UserDAO userDAO ;
	@SuppressWarnings("null")
	@Transactional
	public LoginOutPut LoginChk(String username ,String password){
		LoginOutPut output = new LoginOutPut();
		User user = new User();
		user.setUserName(username);
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
	@Transactional
	public void UserRegist(String username,String password){
		User user = new User();
		user.setUserName(username);
		user.setUserPsw(password);
		
		userDAO.save(user);

		
	}
	
}

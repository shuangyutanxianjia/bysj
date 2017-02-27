package pojo;

import java.util.ArrayList;


import domain.User;

public class LoginOutPut {
	ArrayList<User> users = null;
	boolean flag = true;  
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}

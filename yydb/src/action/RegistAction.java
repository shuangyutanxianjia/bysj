package action;

import javax.annotation.Resource;

import service.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RegistAction extends ActionSupport{
	@Resource
	private  UserServiceImpl userServiceImpl;
	private String RegistName;
	private String RegistType;
	public void setRegistName(String registName) {
		RegistName = registName;
	}
	public void setRegistType(String registType) {
		RegistType = registType;
	}

	private String retMsg; //返回信息
	public String getRetMsg() {
		return retMsg;
	}
	
	
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public String loginNameChk(){
		
		retMsg = userServiceImpl.RegistNameChk(RegistName, RegistType);
		return "nameChk_success";
	}
}

package action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import service.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RegistAction extends ActionSupport{
	@Resource
	private  UserServiceImpl userServiceImpl;
	private String RegistName;//注册用户名
	private String RegistType;//注册类型（1、手机注册 2、邮箱注册）
	private String RegistPsw;//注册用户密码
	private String RegistCode;//邮箱注册时所需要的验证码
	public void setRegistName(String registName) {
		RegistName = registName;
	}
	public void setRegistType(String registType) {
		RegistType = registType;
	}
	public void setRegistPsw(String registPsw) {
		RegistPsw = registPsw;
	}
	public void setRegistCode(String registCode){
		RegistCode = registCode;
	}
	private String retMsg; //返回信息
	public String getRetMsg() {
		return retMsg;
	}
	
	
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	/**
	 * 注册用户名校验
	 * @return
	 */
	public String loginNameChk(){
		
		retMsg = userServiceImpl.RegistNameChk(RegistName, RegistType);
		return "nameChk_success";
	}
	
	/**
	 * 用户注册验证码生成
	 */
	public String userRegistCode(){
		//获取request对象
		HttpServletRequest request = ServletActionContext.getRequest();			
		//获取session对象
        HttpSession httpSession = request.getSession();
        //随机生成验证码
        String registCode = userServiceImpl.GetRadom();
        httpSession.setAttribute("registCode", registCode);
        //记录注册信息
        httpSession.setAttribute("registName", RegistName);
        httpSession.setAttribute("registType", RegistType);
        httpSession.setAttribute("registPsw", RegistPsw);	
		String retMsg = userServiceImpl.UserRegistCode(RegistName, RegistType,registCode);		
		return "registcode_success";		
	}
	
	/**
	 * 用户注册
	 */
	public String userRegist(){
		//获取request对象
				HttpServletRequest request = ServletActionContext.getRequest();			
				//获取session对象
		        HttpSession httpSession = request.getSession();		        
		       
		        if(RegistType.equals("2")){
		        //用户注册
			    String YCode = (String)httpSession.getAttribute("registCode");
		        if(YCode.equals(RegistCode)){ //验证码校验
		        retMsg = userServiceImpl.UserRegist(
		        		httpSession.getAttribute("registName")+"",
		        		httpSession.getAttribute("registType")+"",
		        		httpSession.getAttribute("registPsw")+"");
		        }else{
		        	retMsg = "regist_failed";
		        }
		        }else{
		        retMsg = userServiceImpl.UserRegist(RegistName, RegistType, RegistPsw);
		        }		        
		        return "regist_success";
	}
	
}

package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getDate(){
		Date date = new Date();
	    DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return format.format(date);
	} 
	/**
	 * 获取10位时间戳
	 * @return
	 */
    public static long time10wei() {
    	return Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0,10));//当前时间戳  
	}
    
    /**
	 * 10位时间戳转13位
	 * @return
	 */
    public static String time10wei213wei(String time10) {
    	return time10+"000";
	}
    
    /**
	 * 13位时间戳转10位
	 * @return
	 */
    public static String time13wei210wei(String time13) {
    	return time13.substring(0,10);
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

}

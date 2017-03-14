package util;

public class FormatString {
	//汉字转为unicode 防止中文乱码问题
	public String String2unicode(String string){
	 StringBuffer unicode = new StringBuffer();
     
     for (int i = 0; i < string.length(); i++) {
   
         // 取出每一个字符
         char c = string.charAt(i);
   
         // 转换为unicode
         unicode.append("\\u" + Integer.toHexString(c));
     }
   
     return unicode.toString();
 }
	//unicode 转中文
	public String Unicode2string(String utfString){  
	    StringBuilder sb = new StringBuilder();  
	    int i = -1;  
	    int pos = 0;  
	      
	    while((i=utfString.indexOf("\\u", pos)) != -1){  
	        sb.append(utfString.substring(pos, i));  
	        if(i+5 < utfString.length()){  
	            pos = i+6;  
	            sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
	        }  
	    }  
	      
	    return sb.toString();  
	}
	
	
}


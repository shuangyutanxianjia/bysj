package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

public class PictureUpload {
	public static File uploadpic(String Url,File file) throws IOException{
	     InputStream is = new FileInputStream(file);  
         
	        String uploadPath = ServletActionContext.getServletContext()  
	                .getRealPath("/upload/"+Url);  
	       
	        File f = new File(uploadPath);  
	        // 如果路径不存在,则创建  
	        if (!f.getParentFile().exists()) {  
	            f.getParentFile().mkdirs();  
	        }  
	        try {
				f.createNewFile();
				  OutputStream os = new FileOutputStream(f);  
				  
			        byte[] buffer = new byte[1024];  
			  
			        int length = 0;  
			  
			        while ((length = is.read(buffer)) > 0) {  
			            os.write(buffer, 0, length);  
			        }  
			         
			       
			        is.close();  
			          
			        os.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			return f;  
	     
	          
	      
	}
}

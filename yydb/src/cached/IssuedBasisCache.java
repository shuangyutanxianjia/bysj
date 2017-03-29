package cached;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.IssueDAO;
import domain.Issue;
/**
 * 获取期表缓存
 * @author xuguowei
 *
 */
@Component
public class IssuedBasisCache extends  MemcachedBasis{
	@Autowired
	IssueDAO issueDAO;
	
	/**
	 * 设置缓存
	 */
	public boolean set(Issue issue){
		boolean result = false;
		try {
			System.out.println(getCacheKey(issue.getIssueId()));
			result = memcachedClient.set(getCacheKey(issue.getIssueId()), super.Exptime, issue);
		} catch (Exception e) {
			System.out.println(""+e);
		}
		
			return result;
	}
	
	/**
	 * 获取缓存
	 * 
	 * @param id
	 *            期表ID
	 * @return
	 */
	public Issue get(int id) {
	    Issue entity = new Issue();
	    try {
	    	System.out.println("getCacheKey(id)="+getCacheKey(id));
	        entity = memcachedClient.get(getCacheKey(id));
	        //未进行缓存加载
	        if (entity == null || entity.getIssueId() <= 0) {
	           //从数据库中读取 
	        	entity = issueDAO.findById(id);	        	
	            this.set(entity);
	        }
	    } catch (Exception e) {
	        entity = issueDAO.findById(id);
	    }
	    return entity;
	}
	
	/**
	 * 删除缓存
	 * 
	 * @param id
	 *            商品ID
	 * @return
	 */
	public Boolean delete(int id) {
	    try {
	        return memcachedClient.delete(getCacheKey(id));
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return false;
	}
	
	/**
	 * 更新缓存信息
	 * @param id
	 * @param issue
	 * @return
	 */
	public Boolean replace(int id,Issue issue){
		boolean result = false;
		try {
			return memcachedClient.replace(getCacheKey(id), super.Exptime, issue);
		} catch (Exception e) {
			System.out.println("缓存replace失败"+e);
		}
		return result;
	}
	
	/**
	 * 获取缓存 Key
	 * 
	 * @param id
	 *            用户ID
	 * @return
	 */
	private String getCacheKey(int id) {
	    return super.Prefix + "IssuedBasis:" + id+"cached";
	}		
}

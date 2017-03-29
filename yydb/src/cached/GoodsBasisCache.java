package cached;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.GoodsDAO;
import domain.Goods;

/**
 * 获取商品缓存
 * @author xuguowei
 *
 */
@Component
public class GoodsBasisCache extends  MemcachedBasis{
	@Autowired
	GoodsDAO goodsDAO;
	
	/**
	 * 设置缓存
	 */
	public boolean set(Goods goods){
		boolean result = false;
		try {
			System.out.println(getCacheKey(goods.getGoodId()));
			result = memcachedClient.set(getCacheKey(goods.getGoodId()), super.Exptime, goods);
		} catch (Exception e) {
			System.out.println(""+e);
		}
		
			return result;
	}
	
	/**
	 * 获取缓存
	 * 
	 * @param id
	 *            商品ID
	 * @return
	 */
	public Goods get(int id) {
	    Goods entity = new Goods();
	    try {
	    	System.out.println("getCacheKey(id)="+getCacheKey(id));
	        entity = memcachedClient.get(getCacheKey(id));
	        //未进行缓存加载
	        if (entity == null || entity.getGoodId() <= 0) {
	           //从数据库中读取 
	        	entity = goodsDAO.findById(id);	        	
	            this.set(entity);
	        }
	    } catch (Exception e) {
	        entity = goodsDAO.findById(id);
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
	 * 获取缓存 Key
	 * 
	 * @param id
	 *            用户ID
	 * @return
	 */
	private String getCacheKey(int id) {
	    return super.Prefix + "GoodsBasis:" + id+"cached";
	}	
}

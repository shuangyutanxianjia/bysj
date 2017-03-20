package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import domain.Dingdan;

/**
 * A data access object (DAO) providing persistence and search support for
 * Dingdan entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see domain.Dingdan
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DingdanDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(DingdanDAO.class);
	// property constants
	public static final String SHOP_ID = "shopId";
	public static final String GOOD_ID = "goodId";
	public static final String ISSUE_ID = "issueId";
	public static final String LOTTLENUM = "lottlenum";
	public static final String EXT1 = "ext1";
	public static final String EXT2 = "ext2";

	public void save(Dingdan transientInstance) {
		log.debug("saving Dingdan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Dingdan persistentInstance) {
		log.debug("deleting Dingdan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dingdan findById(java.lang.Integer id) {
		log.debug("getting Dingdan instance with id: " + id);
		try {
			Dingdan instance = (Dingdan) getSession().get("domain.Dingdan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Dingdan instance) {
		log.debug("finding Dingdan instance by example");
		try {
			List results = getSession().createCriteria("domain.Dingdan")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Dingdan instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Dingdan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByShopId(Object shopId) {
		return findByProperty(SHOP_ID, shopId);
	}

	public List findByGoodId(Object goodId) {
		return findByProperty(GOOD_ID, goodId);
	}

	public List findByIssueId(Object issueId) {
		return findByProperty(ISSUE_ID, issueId);
	}

	public List findByLottlenum(Object lottlenum) {
		return findByProperty(LOTTLENUM, lottlenum);
	}

	public List findByExt1(Object ext1) {
		return findByProperty(EXT1, ext1);
	}

	public List findByExt2(Object ext2) {
		return findByProperty(EXT2, ext2);
	}

	public List findAll() {
		log.debug("finding all Dingdan instances");
		try {
			String queryString = "from Dingdan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Dingdan merge(Dingdan detachedInstance) {
		log.debug("merging Dingdan instance");
		try {
			Dingdan result = (Dingdan) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Dingdan instance) {
		log.debug("attaching dirty Dingdan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dingdan instance) {
		log.debug("attaching clean Dingdan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import domain.Lottle;

/**
 * A data access object (DAO) providing persistence and search support for
 * Lottle entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see domain.Lottle
 * @author MyEclipse Persistence Tools
 */
@Repository
public class LottleDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(LottleDAO.class);
	// property constants
	public static final String ISSUE_ID = "issueId";
	public static final String GOOD_ID = "goodId";
	public static final String USER_ID = "userId";
	public static final String LOTTLENUM = "lottlenum";
	public static final String EXT1 = "ext1";
	public static final String EXT2 = "ext2";

	public void save(Lottle transientInstance) {
		log.debug("saving Lottle instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Lottle persistentInstance) {
		log.debug("deleting Lottle instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Lottle findById(java.lang.Integer id) {
		log.debug("getting Lottle instance with id: " + id);
		try {
			Lottle instance = (Lottle) getSession().get("domain.Lottle", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Lottle instance) {
		log.debug("finding Lottle instance by example");
		try {
			List results = getSession().createCriteria("domain.Lottle")
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
		log.debug("finding Lottle instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Lottle as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIssueId(Object issueId) {
		return findByProperty(ISSUE_ID, issueId);
	}

	public List findByGoodId(Object goodId) {
		return findByProperty(GOOD_ID, goodId);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
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
		log.debug("finding all Lottle instances");
		try {
			String queryString = "from Lottle";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Lottle merge(Lottle detachedInstance) {
		log.debug("merging Lottle instance");
		try {
			Lottle result = (Lottle) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Lottle instance) {
		log.debug("attaching dirty Lottle instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Lottle instance) {
		log.debug("attaching clean Lottle instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List get5lottle(){
		log.debug("查找最近5个中奖记录");
		try {
			String queryString = "from Lottle as t order by t.lottleId desc";
			Query queryObject = getSession().createQuery(queryString);			
			queryObject.setFirstResult(0);
			queryObject.setMaxResults(4);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}
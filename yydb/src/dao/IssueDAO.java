package dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import domain.Issue;

/**
 * A data access object (DAO) providing persistence and search support for Issue
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see domain.Issue
 * @author MyEclipse Persistence Tools
 */
@Repository
public class IssueDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IssueDAO.class);
	// property constants
	public static final String GOOD_ID = "goodId";
	public static final String GOOD_ISSUE = "goodIssue";
	public static final String TOTALNUM = "totalnum";
	public static final String SOLDNUM = "soldnum";
	public static final String ISDRAW = "isdraw";
	public static final String EXT1 = "ext1";
	public static final String EXT2 = "ext2";

	public void save(Issue transientInstance) {
		log.debug("saving Issue instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Issue persistentInstance) {
		log.debug("deleting Issue instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Issue findById(java.lang.Integer id) {
		log.debug("getting Issue instance with id: " + id);
		try {
			Issue instance = (Issue) getSession().get("domain.Issue", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Issue instance) {
		log.debug("finding Issue instance by example");
		try {
			List results = getSession().createCriteria("domain.Issue")
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
		log.debug("finding Issue instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Issue as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGoodId(Object goodId) {
		return findByProperty(GOOD_ID, goodId);
	}

	public List findByGoodIssue(Object goodIssue) {
		return findByProperty(GOOD_ISSUE, goodIssue);
	}

	public List findByTotalnum(Object totalnum) {
		return findByProperty(TOTALNUM, totalnum);
	}

	public List findBySoldnum(Object soldnum) {
		return findByProperty(SOLDNUM, soldnum);
	}

	public List findByIsdraw(Object isdraw) {
		return findByProperty(ISDRAW, isdraw);
	}

	public List findByExt1(Object ext1) {
		return findByProperty(EXT1, ext1);
	}

	public List findByExt2(Object ext2) {
		return findByProperty(EXT2, ext2);
	}

	public List findAll() {
		log.debug("finding all Issue instances");
		try {
			String queryString = "from Issue";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Issue merge(Issue detachedInstance) {
		log.debug("merging Issue instance");
		try {
			Issue result = (Issue) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Issue instance) {
		log.debug("attaching dirty Issue instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Issue instance) {
		log.debug("attaching clean Issue instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
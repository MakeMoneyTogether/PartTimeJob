package com.partjob.utils;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.*;
import java.util.*;


/**
 * 数据访问对象基类。
 */
@SuppressWarnings("unchecked")
public abstract class HibernateBaseDao<T, PK extends Serializable> {

    protected static final Logger logger = Logger.getLogger(HibernateBaseDao.class);

    protected Class<T> entityClass;
    @Resource
    private SessionFactory sessionFactory;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HibernateTemplate hibernateTemplate;


    /**
     * 用于扩展的DAO子类使用的构造函数.
     * <p/>
     * 通过子类的范型定义取得对象类型Class. eg. public class UserDao extends
     * SimpleHibernateDao<User, Long>
     */
    public HibernateBaseDao() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
    }

    protected  Session getSession() {
//        System.out.println(hibernateTemplate);
//        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
//        if(session==null)
//            return hibernateTemplate.getSessionFactory().openSession();
//        else return session;
        return sessionFactory.getCurrentSession();
//        return sessionFactory.openSession();
    }

    /**
     * 批量添加、修改信息
     * @param entitys
     */
    public void saveOrUpdate(Collection<T> entitys){
    	 hibernateTemplate.saveOrUpdate(entitys);
    }
    
    /**
     * 批量添加、修改信息
     * @param entity
     */
    public void saveOrUpdate(final T entity){
    	EntityUtils.initEntityAttribute(entity);
    	hibernateTemplate.saveOrUpdate(entity);
    }

    /**
     * 保存新增或修改的对象.
     */
    public void save(final T entity) {

//        Assert.notNull(entity);
//        EntityUtils.initEntityAttribute(entity);
        hibernateTemplate.save(entity);
    }

    /**
     * 保存新增或修改的对象.
     */
    public void modify(final T entity) {
    	
//    	Assert.notNull(entity);
//    	EntityUtils.initEntityAttribute(entity);
    	hibernateTemplate.update(entity);
    }
    
    /**
     * 删除对象.
     *
     * @param entity 对象必须是session中的对象或含id属性的transient对象.
     */
    public void delete(final T entity) {
        if (entity == null) {
            return;
        }

        hibernateTemplate.delete(entity);
    }

    /**
     * 按id删除对象.
     */
    public void delete(final PK id) {
        if (id == null) {
            return;
        }
        delete(get(id));
    }

    /**
     * 按id获取对象.
     */
    public T get(final PK id) {
        Assert.notNull(id);
        return (T) hibernateTemplate.get(entityClass, id);
    }

    /**
     * 获取全部对象.
     */
    public List<T> getAll() {
        return findByCriteria();
    }

    /**
     * 按属性查找对象列表,匹配方式为相等.
     */
    public List<T> findByProperty(final String propertyName, final Object value) {
        Assert.hasText(propertyName);
        Criterion criterion = Restrictions.eq(propertyName, value);
        return findByCriteria(criterion);
    }

    /**
     * 按属性查找唯一对象,匹配方式为相等.
     */
    public T findUniqueByProperty(final String propertyName, final Object value) {
        Assert.hasText(propertyName);
        Criterion criterion = Restrictions.eq(propertyName, value);
        return (T) createCriteria(criterion).uniqueResult();
    }

    /**
     * 按HQL查询对象列表.
     *
     * @param values 数量可变的参数
     */
    public int update(final String hql, final Object... values) {
        return createQuery(hql, values).executeUpdate();
    }

    /**
     * 按HQL查询对象列表.
     *
     * @param values 数量可变的参数
     */
    public List<T> find(final String hql, final Object... values) {
        return createQuery(hql, values).list();
    }


    /**
     * 按HQL查询对象列表.
     *
     * @param values 数量可变的参数
     */
    public List<Object[]> findArray(final String hql, final Object... values) {
        return createQuery(hql, values).list();
    }

    /**
     * 按HQL查询对象列表.
     *
     * @param values 数量可变的参数
     */
    public List<T> findPage(final String hql,int limit,int size, final Object... values) {
    	return createQuery(hql, values).setFirstResult(limit).setMaxResults(size).list();
    }
    
    /**
     * 按HQL查询对象列表.
     *
     * @param values 数量可变的参数
     */
    public List<Object> findList(final String hql, final Object... values) {
        return createQuery(hql, values).list();
    }


    /**
     * 按HQL查询唯一对象.
     */
    public T findUnique(final String hql, final Object... values) {
        return (T) createQuery(hql, values).uniqueResult();
    }

    /**
     * 按HQL查询Integer类型结果.
     */
    public Integer findInt(final String hql, final Object... values) {
        return (Integer) findUnique(hql, values);
    }

    /**
     * 按HQL查询Long类型结果.
     */
    public Long findLong(final String hql, final Object... values) {
        return (Long) findUnique(hql, values);
    }

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * <p/>
     * 返回对象类型不是Entity时可用此函数灵活查询.
     *
     * @param values 数量可变的参数
     */
    public Query createQuery(final String queryString, final Object... values) {
        Query query = getSession().createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    
    public Query createHqlQuery(String queryString,List values){
        Assert.hasText(queryString);
        Query query = getSession().createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.size(); i++) {
                query.setParameter(i, values.get(i));
            }
        }
        return query;
    }
    /**
     * 根据sql语句与参数列表创建SQLQuery
     * @since 
     * @param queryString sql语句
     * @param values 参数列表
     * @return
     */
    public SQLQuery createSQLQuery(final String queryString, List<String>values){
        Assert.hasText(queryString);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.size(); i++) {
                query.setParameter(i, values.get(i));
            }
        }
        return query;
    }

    /**
     * 按Criteria查询对象列表.
     *
     * @param criterions 数量可变的Criterion.
     */
    public List<T> findByCriteria(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    public T findUnique(final Criterion... criterions) {
        return (T) createCriteria(criterions).uniqueResult();
    }

    /**
     * 根据Criterion条件创建Criteria.
     * <p/>
     * 返回对象类型不是Entity时可用此函数灵活查询.
     *
     * @param criterions 数量可变的Criterion.
     */
    public Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 判断对象的属性值在数据库内是否唯一.
     * <p/>
     * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
     */
    public boolean isPropertyUnique(final String propertyName,
                                    final Object newValue, final Object orgValue) {
        if (newValue == null || newValue.equals(orgValue)) return true;
        Object object = findUniqueByProperty(propertyName, newValue);
        return (object == null);
    }

    /**
     * 取得对象的主键名.
     */
    public String getIdName() {
        ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(entityClass);
        Assert.notNull(meta, "Class " + entityClass.getSimpleName()
                + " not define in HibernateSessionFactory.");
        return meta.getIdentifierPropertyName();
    }


    /**
     * 通用的sql查询语句返回列表实现 values中的值代替sql中的？
     */
    public List<T> findBySql(final String sql) {
        if (sql != null) {
            List<T> list = (List<T>) getSession().createQuery(sql).list();
            return list;
        } else {
            return null;
        }
    }


    private void setParameters(final Object[] values, Query q) {
        if (null != values && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                q.setParameter(i, values[i]);
            }
        }
    }
    

    private void setParameters(final List<Object> values, Query q) {
        if (null != values && values.size() > 0) {
            for (int i = 0; i < values.size(); i++) {
                q.setParameter(i, values.get(i));
            }
        }
    }

    /**
     * 通用的sql查询语句返回结果数的实现,sql以from开头 values中的值代替sql中的？
     */
    @SuppressWarnings("rawtypes")
    public Long findCountBySql(final String sql, final Object[] values) {
        if (sql != null) {
            Object count = hibernateTemplate.execute(new HibernateCallback() {
                public Object doInHibernate(Session session)
                        throws HibernateException {
                    SQLQuery q = session.createSQLQuery(sql);
                    setParameters(values, q);

                    Long count = ((Integer) q.uniqueResult()).longValue();

                    return count;

                }
            });
            return (Long) count;
        } else {
            return 0L;
        }
    }
    
    /**
     * 通用的sql查询语句返回结果数的实现,sql以from开头 values中的值代替sql中的？
     */
    public Object findCountBySql(final String sql, final List<Object> values) {
        if (sql != null) {
                SQLQuery q = this.getSession().createSQLQuery(sql);
                setParameters(values, q);
                return q.uniqueResult();
        } else {
            return 0;
        }
    }
    
    
    public int sqlExec(String sql){
        return getSession().createSQLQuery(sql).executeUpdate();
    }
    
    /**
     * 
     * 方法名 ：findMapBySQL<BR>
     * 方法说明 ：根据默认ds查询记录<BR>
     * 备注：无<BR>
     * 
     * @param sql
     *            原生sql语句<BR>
     * <BR>
     * @return 记录结果集，返回字段对应Map集合<BR>
     * @throws SQLException 
     */
    public List<Map<String, Object>> findMapBySQL(String sql) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> result = null;

        try {
            result = new ArrayList<Map<String, Object>>();
            conn = jdbcTemplate.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(toMap(rs));
            }
            return result;
        }  finally {
            
            if (rs != null) {
                rs.close();
            }
            
            if (stmt != null) {
                stmt.close();
            }
            
            if (conn != null) {
                conn.close();
            }

        }
        
     
    }
    
    /**
     * 
     * 方法名 ：findMapBySQL<BR>
     * 方法说明 ：根据ds查询记录<BR>
     * 备注：无<BR>
     * 
     * @param sql
     *            原生sql语句<BR>
     * @param param
     *            参数列表<BR>
     * <BR>
     * @return 记录结果集，返回字段对应Map集合<BR>
     */
    public List<Map<String, Object>> findMapBySQL(String sql, List<Object> param){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> result = null;

        try {
            result = new ArrayList<Map<String, Object>>();
            conn = jdbcTemplate.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            fillStatement(stmt, param);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(toMap(rs));
            }
            return result;
        } catch (SQLException e) {
           logger.error("",e);//:TODO 后续要引入异常框架
        } finally {
            try {
                closeAll(rs, stmt, conn);
            } catch (SQLException e) {
                logger.error("",e);//:TODO 后续要引入异常框架
            }
        }
        
        return new ArrayList<Map<String,Object>>();
    }
    
    private Map<String, Object> toMap(ResultSet rs) throws SQLException {
        Map<String, Object> result = new HashMap<String, Object>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();

        for (int i = 1; i <= cols; i++) {
            result.put(rsmd.getColumnLabel(i).toUpperCase(), rs.getObject(i));
        }

        return result;
    }
    
    private void closeAll(ResultSet rs,PreparedStatement stmt,Connection conn) throws SQLException{
        if (rs != null) {
            rs.close();
        }
        
        if (stmt != null) {
            stmt.close();
        }
        
        if (conn != null) {
            conn.close();
        }
    }
    
    private void fillStatement(PreparedStatement stmt, List<Object> param)
            throws SQLException {

        // check the parameter count, if we can

        ParameterMetaData pmd = stmt.getParameterMetaData();
        int stmtCount = pmd.getParameterCount();
        int paramsCount = param == null ? 0 : param.size();

        if (stmtCount != paramsCount) {
            throw new SQLException("Wrong number of parameters: expected "
                    + stmtCount + ", was given " + paramsCount);
        }

        // nothing to do here
        if (param == null) {
            return;
        }

        for (int i = 0; i < param.size(); i++) {
            if (param.get(i) != null) {
                stmt.setObject(i + 1, param.get(i));
            } else {
                // VARCHAR works with many drivers regardless
                // of the actual column type. Oddly, NULL and
                // OTHER don't work with Oracle's drivers.
                int sqlType = Types.VARCHAR;

                try {
                    sqlType = pmd.getParameterType(i + 1);
                } catch (SQLException e) {
                    // pmdKnownBroken = true;
                }

                stmt.setNull(i + 1, sqlType);
            }
        }
    }
}

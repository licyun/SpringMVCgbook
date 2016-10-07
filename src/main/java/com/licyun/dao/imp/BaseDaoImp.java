package com.licyun.dao.imp;

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import com.licyun.dao.BaseDao;

@Transactional
public  class BaseDaoImp<T> extends HibernateDaoSupport implements BaseDao<T> {

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Transactional(readOnly=false)
    public void save(T entity) {
        // TODO Auto-generated method stu
        getHibernateTemplate().save(entity);
    }

    @Transactional(readOnly=true)
    @SuppressWarnings("unchecked")
    public T get(Class<?> clazz, Integer id) {
        // TODO Auto-generated method stub
        return (T)getHibernateTemplate().get(clazz, id);

    }

    @Transactional(readOnly=false)
    public void update(T entity) {
        // TODO Auto-generated method stub
        getHibernateTemplate().update(entity);
    }

    @Transactional(readOnly=false)
    public void delete(T entity) {
        // TODO Auto-generated method stub
        getHibernateTemplate().delete(entity);
    }

    @Transactional(readOnly=false)
    public void saveOrUpdate(T entity) {
        // TODO Auto-generated method stub
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Transactional(readOnly=true)
    @SuppressWarnings("unchecked")
    public List<T> find(String queryName, String values) {
        // TODO Auto-generated method stub
        List<T> list = (List<T>) getHibernateTemplate().find(queryName, values);
        return list;
    }

    @Transactional(readOnly=true)
    @SuppressWarnings("unchecked")
    public List<T> find(String queryName,String[] paramNames , Object[] values) {
        // TODO Auto-generated method stub
        List<T> list = (List<T>) getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
        return list;
    }
    @Transactional(readOnly=true)
    public List<T> findByPage(final String hql,final int pageNo, final int pageSize){

        List<T> list = (List<T>) getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

            @SuppressWarnings("unchecked")
            public List<T> doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                List<T> result = session.createQuery(hql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
                return result;
            }
        });
        return list;
    }
    @Transactional(readOnly=true)
    public List<T> findByPage(final String hql,final int pageNo, final int pageSize, final String[] params, final String[] values){

        List<T> list = (List<T>) getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

            @SuppressWarnings("unchecked")
            public List<T> doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                Query query = session.createQuery(hql);
                for(int i=0;i<params.length;i++){
                    query.setString(params[i], values[i]);
                }
                List<T> result = query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
                return result;
            }
        });
        return list;
    }

}

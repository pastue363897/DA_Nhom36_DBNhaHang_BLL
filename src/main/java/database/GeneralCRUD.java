/**
 * Created on: 15:20:30 24 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GeneralCRUD<T> {
  
  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  private final Class<T> type;
  
  public GeneralCRUD(Class<T> type) {
    super();
    this.type = type;
  }
  public String save(final T o) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      String id = (String) session.save(o);
      tr.commit();
      return id;
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return null;
  }
  public T get(final String id) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      T ob = (T) session.get(type, id);
      tr.commit();
      return ob;
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return null;
  }
  public List<T> getAll(){
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      List<T> list = session.createCriteria(type).list();
      tr.commit();
      return list;
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return null;
  }
  public T update(final T o) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      T ob = (T) session.merge(o);
      tr.commit();
      return ob;
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return null;
  }
  public boolean delete(final Object o){
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      session.remove(o);
      tr.commit();
      return true;
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return false;
  }
  public boolean saveOrUpdate(final T o) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      session.saveOrUpdate(o);
      tr.commit();
      return true;
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return false;
  }
  
}

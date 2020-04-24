/**
 * Created on: 15:20:30 24 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GeneralCRUD<T> {
  
  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  private final Class<T> type;
  
  public GeneralCRUD(Class<T> type) {
    super();
    this.type = type;
  }
  public T save(final T o) {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    T ob = (T) session.save(o);
    session.getTransaction().commit();
    return ob;
  }
  public T get(final String id) {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    T ob = (T) session.get(type, id);
    session.getTransaction().commit();
    return ob;
  }
  public List<T> getAll(){
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    List<T> list = session.createCriteria(type).list();
    session.getTransaction().commit();
    return list;
  }
  public T update(final T o) {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    T ob = (T) session.merge(o);
    session.getTransaction().commit();
    return ob;
  }
  public void delete(final Object o){
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    session.remove(o);
    session.getTransaction().commit();
  }
  public void saveOrUpdate(final T o) {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    session.saveOrUpdate(o);
    session.getTransaction().commit();
  }
  
}

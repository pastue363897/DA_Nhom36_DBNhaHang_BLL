/**
 * Created on: 15:47:06 24 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entites.Account;
import entites.Customer;

public class CustomerDAO extends GeneralCRUD<Customer>{

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
  public CustomerDAO() {
    super(Customer.class);
  }
  /**
   * get last ID in alphanumeric order of MonAn
   * 
   * @return the ID in string
   */
  private String getLastUsedID() {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    String preparedQL = "select top 1 * from KhachHang order by maKH desc";
    try {
      Customer c = (Customer) session.createNativeQuery(preparedQL, Customer.class).getSingleResult();
      session.getTransaction().commit();
      return c.getMaKH();
    } catch (NoResultException ex) {
      session.getTransaction().commit();
      return "";
    }
  }

  /**
   * generate new ID for MonAn, it should be just take last used id then substring
   * the number part out then add by one
   * 
   * @return usable ID string for new object
   */
  private String generateID() {
    String lastUsed = getLastUsedID();
    if (lastUsed.equals("")) {
      return "KH000001";
    }
    String number = lastUsed.substring(2);
    return "KH" + String.format("%06d", Integer.parseInt(number) + 1);
  }
  
  public Customer getCustomer(Account account) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    Customer c = null;
    try {
      String preparedQL = "select top 1 * from KhachHang where taiKhoan = '" + account.getUsername() + "'";
      tr.begin();
      c = session.createNativeQuery(preparedQL, Customer.class).getSingleResult();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return c;
  }
  
  public boolean addCustomer(Customer customer) {
    customer.setMaKH(generateID());
    if (save(customer) != null) {
      return true;
    }
    return false;
  }
}

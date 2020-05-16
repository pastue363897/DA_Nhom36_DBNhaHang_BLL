/**
 * Created on: 15:47:06 24 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import javax.persistence.NoResultException;
import javax.persistence.Query;

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
      return c.getTaiKhoan().getMaTK();
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
      String preparedQL = "select top 1 * from KhachHang where maKH = '" + account.getMaTK() + "'";
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
    AccountDAO accDao = new AccountDAO();
    customer.getTaiKhoan().setMaTK(generateID());
    accDao.addAccount(customer.getTaiKhoan());
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      session.save(customer);
      tr.commit();
      return true;
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return false;
  }
  
  private String getVLLastUsedID() {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    String preparedQL = "select top 1 * from KhachHang where maKH like 'VL%' order by maKH desc";
    try {
      Customer c = (Customer) session.createNativeQuery(preparedQL, Customer.class).getSingleResult();
      session.getTransaction().commit();
      return c.getTaiKhoan().getMaTK();
    } catch (NoResultException ex) {
      session.getTransaction().commit();
      return "";
    }
  }
  private String generateVLID() {
    String lastUsed = getVLLastUsedID();
    if (lastUsed.equals("")) {
      return "VL000001";
    }
    String number = lastUsed.substring(2);
    return "VL" + String.format("%06d", Integer.parseInt(number) + 1);
  }
  
  public boolean addVLCustomer(Customer customer) {
    AccountDAO accDao = new AccountDAO();
    customer.getTaiKhoan().setMaTK(generateVLID());
    accDao.addAccount(customer.getTaiKhoan());
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      session.save(customer);
      tr.commit();
      return true;
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean updateCustomer(Customer customer) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    boolean result = false;
    try {
      String sql = "update Customer set hoTen = :hoTen, diaChi = :diaChi, cmnd = :cmnd, sdt = :sdt, email = :email where maKH = :maKH";
      tr.begin();
      Query query = session.createQuery(sql);
      query.setParameter("hoTen", customer.getHoTen());
      query.setParameter("diaChi", customer.getDiaChi());
      query.setParameter("cmnd", customer.getCmnd());
      query.setParameter("sdt", customer.getSdt());
      query.setParameter("email", customer.getEmail());
      query.setParameter("maKH", customer.getTaiKhoan().getMaTK());
      if(query.executeUpdate() > 0) {
        result = true;
      }
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return result;
  }
}

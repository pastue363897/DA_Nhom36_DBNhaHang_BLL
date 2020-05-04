/**
 * Created on: 14:59:01 25 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entites.CTTTBanDatMonAn;
import entites.MonAn;

public class MonAnDAO extends GeneralCRUD<MonAn>{

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  public MonAnDAO() {
    super(MonAn.class);
  }
  /**
   * get last ID in alphanumeric order of MonAn
   * @return the ID in string
   */
  private String getLastUsedID() {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    String preparedQL = "select top 1 * from MonAn order by maMA desc";
    try {
      MonAn ma = (MonAn) session.createNativeQuery(preparedQL, MonAn.class).getSingleResult();
      session.getTransaction().commit();
      return ma.getMaMA();
    }
    catch (NoResultException ex)
    {
      session.getTransaction().commit();
      return "";
    }
  }
  
  /**
   * generate new ID for MonAn, it should be just take last used id then
   * substring the number part out then add by one
   * @return usable ID string for new object
   */
  private String generateID() {
    String lastUsed = getLastUsedID();
    if(lastUsed.equals("")) {
      return "MA000001";
    }
    String number = lastUsed.substring(2);
    return "MA"+String.format("%06d", Integer.parseInt(number)+1);
  }
  
  public String addMonAn(MonAn monAn) {
    monAn.setMaMA(generateID());
    return this.save(monAn);
  }
  
  public boolean checkPreviouslyBooked(MonAn monAn) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      String sql = "select top 1 * from CTTTBanDatMonAn where monAn = '" + monAn.getMaMA() + "'";
      CTTTBanDatMonAn ct = session.createNativeQuery(sql, CTTTBanDatMonAn.class).getSingleResult();
      tr.commit();
      if (ct != null) {
        return true;
      }
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return false;
  }
  public boolean setIsDeleted(MonAn monAn) {
    monAn.setDeleted(true);
    return this.saveOrUpdate(monAn);
  }
}

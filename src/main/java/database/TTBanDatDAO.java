/**
 * Created on: 14:59:22 25 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entites.ChiTietThanhToan;
import entites.MonAn;
import entites.TTBanDat;

public class TTBanDatDAO extends GeneralCRUD<TTBanDat> {

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
	public TTBanDatDAO() {
		super(TTBanDat.class);
	}
	
	/**
   * get last ID in alphanumeric order of MonAn
   * 
   * @return the ID in string
   */
  private String getLastUsedID() {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    String preparedQL = "select top 1 * from TTBanDat order by maBD desc";
    try {
      TTBanDat b = (TTBanDat) session.createNativeQuery(preparedQL, TTBanDat.class).getSingleResult();
      session.getTransaction().commit();
      return b.getMaBD();
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
      return "BD000001";
    }
    String number = lastUsed.substring(2);
    return "BD" + String.format("%06d", Integer.parseInt(number) + 1);
  }
  
  public String addBanDat(TTBanDat ttBD) {
    ttBD.setMaBD(generateID());
    return this.save(ttBD);
  }
  public boolean updateTongTien(String maBD, long tongTien) {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    boolean result = false;
    String sql = "update TTBanDat set tongTien = :tongTien where maBD = :maBD";
    Query query = session.createQuery(sql);
    query.setParameter("tongTien", tongTien);
    query.setParameter("maBD", maBD);
    try {
      if (query.executeUpdate() > 0) {
        result = true;
      }
      session.getTransaction().commit();
    } catch (NoResultException ex) {
      session.getTransaction().rollback();
    }
    return result;
  }

}

/**
 * Created on: 14:57:41 25 thg 4, 2020
 * @author Dinh Van Dung YKNB, Ta Khanh Hoang
 */

package database;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entites.BanAn;

public class BanAnDAO extends GeneralCRUD<BanAn>{

  public BanAnDAO() {
    super(BanAn.class);
  }
  
  /**
   * get last ID in alphanumeric order of BanAn
   * @return the ID in string
   */
  private String getLastUsedID() {
	  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	  Session session = sessionFactory.getCurrentSession();
	  session.getTransaction().begin();
	  String preparedQL = "select top 1 * from BanAn order by maBA desc";
	  try {
		  BanAn ba = (BanAn) session.createNativeQuery(preparedQL, BanAn.class).getSingleResult();
		  session.getTransaction().commit();
		  return ba.getMaBA();
	  }
	  catch (NoResultException ex)
	  {
		  session.getTransaction().commit();
		  return "";
	  }
  }
  
  /**
   * generate new ID for BanAn, it should be just take last used id then
   * substring the number part out then add by one
   * @return usable ID string for new object
   */
  private String generateID() {
	  String lastUsed = getLastUsedID();
	  if(lastUsed.equals("")) {
		  return "BA000001";
	  }
	  String number = lastUsed.substring(2);
	  return "BA"+String.format("%06d", Integer.parseInt(number)+1);
  }
  
  public String addBanAn(BanAn banAn) {
    banAn.setMaBA(generateID());
    return this.save(banAn);
  }
  
}

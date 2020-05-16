package database;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entites.HoaDon;

public class HoaDonDAO extends GeneralCRUD<HoaDon> {

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
	public HoaDonDAO() {
		super(HoaDon.class);
	}
	
	/*private String getLastUsedID() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		String preparedQL = "select top 1 * from HoaDon order by maHD desc";
		try {
			HoaDon ct = (HoaDon) session.createNativeQuery(preparedQL, HoaDon.class)
					.getSingleResult();
			session.getTransaction().commit();
			return ct.getMaHD();
		} catch (NoResultException ex) {
			session.getTransaction().commit();
			return "";
		}
	}*/

	/*private String generateID() {
		String lastUsed = getLastUsedID();
		if (lastUsed.equals("")) {
			return "HD000001";
		}
		String number = lastUsed.substring(2);
		return "HD" + String.format("%06d", Integer.parseInt(number) + 1);
	}*/
	
	public boolean themHoaDon(HoaDon hoaDon) {
	  Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      session.save(hoaDon);
      tr.commit();
      return true;
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return false;
	}
}

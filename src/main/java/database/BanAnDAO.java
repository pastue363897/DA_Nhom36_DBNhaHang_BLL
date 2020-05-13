/**
 * Created on: 14:57:41 25 thg 4, 2020
 * @author Dinh Van Dung YKNB, Ta Khanh Hoang
 */

package database;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entites.BanAn;

public class BanAnDAO extends GeneralCRUD<BanAn> {

	public BanAnDAO() {
		super(BanAn.class);
	}

	/**
	 * get last ID in alphanumeric order of BanAn
	 * 
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
		} catch (NoResultException ex) {
			session.getTransaction().commit();
			return "";
		}
	}

	/**
	 * generate new ID for BanAn, it should be just take last used id then substring
	 * the number part out then add by one
	 * 
	 * @return usable ID string for new object
	 */
	private String generateID() {
		String lastUsed = getLastUsedID();
		if (lastUsed.equals("")) {
			return "BA000001";
		}
		String number = lastUsed.substring(2);
		return "BA" + String.format("%06d", Integer.parseInt(number) + 1);
	}

	public String addBanAn(BanAn banAn) {
		banAn.setMaBA(generateID());
		String tempPath = banAn.getHinhAnh();
		String tempExtension = FilenameUtils.getExtension(tempPath);
		String newPath = FilenameUtils.getPath(tempPath);
		newPath += banAn.getMaBA() + "_table_image" + "." + tempExtension;
		banAn.setHinhAnh(newPath);
		return this.save(banAn);
	}

	public BanAn suaBanAn(BanAn banAn) {
		String tempPath = banAn.getHinhAnh();
		String tempExtension = FilenameUtils.getExtension(tempPath);
		String newPath = FilenameUtils.getPath(tempPath);
		newPath += banAn.getMaBA() + "_table_image" + "." + tempExtension;
		banAn.setHinhAnh(newPath);
		return this.update(banAn);
	}
	public List<BanAn> danhSachBanAnCoTheDat() {
	  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<BanAn> list = null;
    try {
      tr.begin();
      String sql = "select * from BanAn where isDeleted = 0";
      list = session.createNativeQuery(sql, BanAn.class).getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
  
	public List<BanAn> danhSachBanAnHome(int count) {
	  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	  Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<BanAn> list = null;
    try {
      tr.begin();
      String sql = "select top " + count + " * from BanAn where maBA in (select top " + count
          + " maBA from BanAn where isDeleted = 0 order by newid())";
      list = session.createNativeQuery(sql, BanAn.class).getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
	}
}

/**
 * Created on: 14:59:01 25 thg 4, 2020
 * 
 * @author Dinh Van Dung YKNB
 */

package database;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entites.CTHoaDonBanDat;
import entites.MonAn;

public class MonAnDAO extends GeneralCRUD<MonAn> {
  
  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
  public MonAnDAO() {
    super(MonAn.class);
  }
  
  /**
   * get last ID in alphanumeric order of MonAn
   * 
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
      return "MA000001";
    }
    String number = lastUsed.substring(2);
    return "MA" + String.format("%06d", Integer.parseInt(number) + 1);
  }
  
  public String addMonAn(MonAn monAn) {
    monAn.setMaMA(generateID());
    String tempPath = monAn.getHinhAnhMA();
    String tempExtension = FilenameUtils.getExtension(tempPath);
    String newPath = FilenameUtils.getPath(tempPath);
    newPath += monAn.getMaMA() + "_meal_image" + "." + tempExtension;
    monAn.setHinhAnhMA(newPath);
    return this.save(monAn);
  }
  
  public MonAn suaMonAn(MonAn monAn) {
    String tempPath = monAn.getHinhAnhMA();
    String tempExtension = FilenameUtils.getExtension(tempPath);
    String newPath = FilenameUtils.getPath(tempPath);
    newPath += monAn.getMaMA() + "_meal_image" + "." + tempExtension;
    monAn.setHinhAnhMA(newPath);
    return this.update(monAn);
  }
  
  public boolean checkPreviouslyBooked(MonAn monAn) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      String sql = "select top 1 * from CTTTBanDatMonAn where maMA = '" + monAn.getMaMA() + "'";
      CTHoaDonBanDat ct = session.createNativeQuery(sql, CTHoaDonBanDat.class).getSingleResult();
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
    monAn.setDaHuy(true);
    return this.saveOrUpdate(monAn);
  }
  
  public List<MonAn> danhSachMonAnCoTheDat() {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<MonAn> list = null;
    try {
      tr.begin();
      String sql = "select * from MonAn where daHuy = 0";
      list = session.createNativeQuery(sql, MonAn.class).getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
  
  public List<MonAn> danhSachMonAnHome(int count) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<MonAn> list = null;
    try {
      tr.begin();
      String sql = "select top " + count + " * from MonAn where maMA in (select top " + count
          + " maMA from MonAn where daHuy = 0 order by newid())";
      list = session.createNativeQuery(sql, MonAn.class).getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
}

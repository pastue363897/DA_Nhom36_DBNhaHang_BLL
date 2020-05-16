/**
 * Created on: 20:58:54 13 thg 5, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entites.CTHoaDonBanDat;

public class CTHoaDonBanDatDAO extends GeneralCRUD<CTHoaDonBanDat>{

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  public CTHoaDonBanDatDAO() {
    super(CTHoaDonBanDat.class);
  }
  
  public List<CTHoaDonBanDat> getDSCTTBanDatMonAnTheoMaBD(String maBD){
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<CTHoaDonBanDat> list = null;
    try {
      tr.begin();
      String sql = "select * from CTHoaDonBanDat where maBD = '" + maBD + "'";
      list = session.createNativeQuery(sql, CTHoaDonBanDat.class).getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
  
}
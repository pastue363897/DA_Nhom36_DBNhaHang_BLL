/**
 * Created on: 20:58:54 13 thg 5, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entites.CTTTBanDatMonAn;

public class CTTTBanDatMonAnDAO extends GeneralCRUD<CTTTBanDatMonAn>{

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  public CTTTBanDatMonAnDAO() {
    super(CTTTBanDatMonAn.class);
  }
  
  public List<CTTTBanDatMonAn> getDSCTTBanDatMonAnTheoMaBD(String maBD){
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<CTTTBanDatMonAn> list = null;
    try {
      tr.begin();
      String sql = "select * from CTTTBanDatMonAn where ttBanDat = '" + maBD + "'";
      list = session.createNativeQuery(sql, CTTTBanDatMonAn.class).getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
  
}

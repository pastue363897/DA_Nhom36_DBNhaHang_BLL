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
  
  public boolean addCTHoaDonBanDat(CTHoaDonBanDat ctHoaDonBanDat) {
	  Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      tr.begin();
	      session.save(ctHoaDonBanDat);
	      tr.commit();
	      return true;
	    } catch (Exception e) {
	      tr.rollback();
	      e.printStackTrace();
	    }
	    return false;
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
  
  public List<CTHoaDonBanDat> getDSCTTBanDatDaThanhToan(){
	    Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    List<CTHoaDonBanDat> list = null;
	    try {
	      tr.begin();
	      String sql = "select * from CTHoaDonBanDat c join HoaDonBanDat t on c.maBD = t.maBD join MonAn m on m.maMA = c.maMA where t.daThanhToan = 1 order by c.maMA asc, t.ngayThanhToan desc";
	      list = session.createNativeQuery(sql, CTHoaDonBanDat.class).getResultList();
	      tr.commit();
	    } catch (Exception e) {
	      tr.rollback();
	      e.printStackTrace();
	    }
	    return list;
	  }
  public List<CTHoaDonBanDat> getDSCTTBanDatTheoKhachHang(){
	    Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    List<CTHoaDonBanDat> list = null;
	    try {
	      tr.begin();
	      String sql = "select * from CTHoaDonBanDat c join HoaDonBanDat t on c.maBD = t.maBD join MonAn m on m.maMA = c.maMA join "
	      		+ "KhachHang k on k.maKH = t.maKH where t.daThanhToan = 1 AND t.maKH like 'KH%' order by t.maKH asc, c.maMA asc, t.ngayThanhToan desc";
	      list = session.createNativeQuery(sql, CTHoaDonBanDat.class).getResultList();
	      tr.commit();
	    } catch (Exception e) {
	      tr.rollback();
	      e.printStackTrace();
	    }
	    return list;
	  }
  public List<CTHoaDonBanDat> getDSCTTBanDatTheoKhachHangMonConBan(){
	    Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    List<CTHoaDonBanDat> list = null;
	    try {
	      tr.begin();
	      String sql = "select * from CTHoaDonBanDat c join HoaDonBanDat t on c.maBD = t.maBD join MonAn m on m.maMA = c.maMA join "
	      		+ "KhachHang k on k.maKH = t.maKH where t.daThanhToan = 1 AND t.maKH like 'KH%' AND m.daHuy = 0 order by t.maKH asc, c.maMA asc, t.ngayThanhToan desc";
	      list = session.createNativeQuery(sql, CTHoaDonBanDat.class).getResultList();
	      tr.commit();
	    } catch (Exception e) {
	      tr.rollback();
	      e.printStackTrace();
	    }
	    return list;
	  }
}

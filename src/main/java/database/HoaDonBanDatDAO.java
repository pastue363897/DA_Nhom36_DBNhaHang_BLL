/**
 * Created on: 14:59:22 25 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import entites.BanAn;
import entites.CTHoaDonBanDat;
import entites.MonAn;
import entites.HoaDonBanDat;

public class HoaDonBanDatDAO extends GeneralCRUD<HoaDonBanDat> {

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
	public HoaDonBanDatDAO() {
		super(HoaDonBanDat.class);
	}
	
	/**
   * get last ID in alphanumeric order of MonAn
   * 
   * @return the ID in string
   */
  private String getLastUsedID() {
    Session session = sessionFactory.getCurrentSession();
    session.getTransaction().begin();
    String preparedQL = "select top 1 * from HoaDonBanDat order by maBD desc";
    try {
      HoaDonBanDat b = (HoaDonBanDat) session.createNativeQuery(preparedQL, HoaDonBanDat.class).getSingleResult();
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
  
  public String addBanDat(HoaDonBanDat ttBD) {
    ttBD.setMaBD(generateID());
    return this.save(ttBD);
  }
  
  public boolean checkBanDaDat(String maBA, Timestamp date) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    boolean result = false;
    try {
      tr.begin();
      String sql = "select h.maBD from HoaDonBanDat h inner join CTHoaDonBanDat c on h.maBD = c.maBD" + 
          " where maBA = :maBA and daThanhToan = 0" + 
          " group by h.maBD, h.ngayPhucVu" + 
          " having :date between h.ngayPhucVu and dateadd(minute, count(h.maBD) * 10 + 20, h.ngayPhucVu)";
      List list = session.createNativeQuery(sql).setParameter("maBA", maBA).setParameter("date", date).list();
      tr.commit();
      if (list != null && list.size() > 0) {
        result = true;
      }
    } catch (Exception e) {
      //tr.rollback();
      e.printStackTrace();
    }
    return result;
  }
  
  public boolean checkSoLuongMonAnHoaDonBanDat(String maBA, Timestamp date, int soLuongMonAn) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    boolean result = false;
    try {
      tr.begin();
      String sql = "select h.maBD from HoaDonBanDat h inner join CTHoaDonBanDat c on h.maBD = c.maBD" + 
          " where maBA = :maBA and daThanhToan = 0" + 
          " group by h.maBD, h.ngayPhucVu" + 
          " having dateadd(minute, :time, :date) between h.ngayPhucVu and dateadd(minute, count(h.maBD) * 10 + 20, h.ngayPhucVu)";
      int time = 20;
      time += 10 * soLuongMonAn;
      List list = session.createNativeQuery(sql).setParameter("maBA", maBA)
                                                .setParameter("date", date).setParameter("time", time).list();
      tr.commit();
      if (list != null && list.size() > 0) {
        result = true;
      }
    } catch (Exception e) {
      //tr.rollback();
      e.printStackTrace();
    }
    return result;
  }
  
  public boolean addBanDatVL(HoaDonBanDat ttBD) {
    ttBD.setMaBD(generateID());
    return this.saveOrUpdate(ttBD);
  }
  /*
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
  }*/
  public List<HoaDonBanDat> getDSTTBanDatTheoCustomer(String maKH){
	    Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    List<HoaDonBanDat> list = null;
	    try {
	      tr.begin();
	      String sql = "select b from HoaDonBanDat b join fetch b.dsMonAn where maKH = '" + maKH + "'";
	      list = session.createQuery(sql, HoaDonBanDat.class).getResultList();
	      tr.commit();
	      if (list != null) {
	        for (int i = 0; i < list.size() - 1; i++) {
	          while((i + 1) < list.size() && list.get(i).getMaBD().equals(list.get(i + 1).getMaBD())) {
	            list.remove(i+1);
	          }
	        }
	      }
	    } catch (Exception e) {
	      //tr.rollback();
	      e.printStackTrace();
	    }
	    return list;
	}
  public List<HoaDonBanDat> getDSTTBanDat(){
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<HoaDonBanDat> list = null;
    try {
      tr.begin();
      String sql = "select b from HoaDonBanDat b join fetch b.dsMonAn";
      list = session.createQuery(sql, HoaDonBanDat.class).getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
  public HoaDonBanDat getTTBanDat(String maBD){
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<HoaDonBanDat> list = null;
    try {
      tr.begin();
      String sql = "select b from HoaDonBanDat b join fetch b.dsMonAn where b.maBD = '" + maBD + "'";
      list = session.createQuery(sql, HoaDonBanDat.class).getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list != null ? list.get(0) : null;
  }
  public List<HoaDonBanDat> getDSHoaDonFrom(Timestamp from, boolean onlyKhachHang) {
	  Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<HoaDonBanDat> list = null;
    try {
      tr.begin();
      String sql = "select b from HoaDonBanDat b join fetch b.khachHang k join fetch k.taiKhoan t where ngayThanhToan >= :value";
      if (onlyKhachHang)
    	  sql += " AND t.maTK like 'KH%'";
      Query query = session.createQuery(sql, HoaDonBanDat.class);
      query.setParameter("value", from);
      list = query.getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
  public List<HoaDonBanDat> getDSHoaDonTo(Timestamp to, boolean onlyKhachHang) {
	  Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<HoaDonBanDat> list = null;
    try {
      tr.begin();
      String sql = "select b from HoaDonBanDat b join fetch b.khachHang k join fetch k.taiKhoan t where ngayThanhToan <= :value";
      if (onlyKhachHang)
    	  sql += " AND t.maTK like 'KH%'";
      Query query = session.createQuery(sql, HoaDonBanDat.class);
      query.setParameter("value", to);
      list = query.getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
  public List<HoaDonBanDat> getDSHoaDonFromTo(Timestamp from, Timestamp to, boolean onlyKhachHang) {
	  Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<HoaDonBanDat> list = null;
    try {
      tr.begin();
      String sql = "select b from HoaDonBanDat b join fetch b.khachHang k join fetch k.taiKhoan t where ngayThanhToan >= :valueFrom AND ngayThanhToan <= :valueTo";
      if (onlyKhachHang)
    	  sql += " AND t.maTK like 'KH%'";
      Query query = session.createQuery(sql, HoaDonBanDat.class);
      query.setParameter("valueFrom", from);
      query.setParameter("valueTo", to);
      list = query.getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
  public Map<Timestamp, Timestamp> thoiGianBanDaDatTrongMotNgay(Date date) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    Map<Timestamp, Timestamp> map = null;
    try {
      tr.begin();
      StringBuilder sql = new StringBuilder("select dateadd(minute, -30, h.ngayPhucVu) as s, dateadd(minute, count(h.maBD) * 10 + 20, h.ngayPhucVu) as e " + 
          "  from HoaDonBanDat h inner join CTHoaDonBanDat c on h.maBD = c.maBD" + 
          "  where daThanhToan = 0 and :date = Convert(date, h.ngayPhucVu)" + 
          "  group by h.maBD, h.ngayPhucVu" + 
          "  order by s");
      List<List<Object>> list = session.createNativeQuery(sql.toString()).setParameter("date", date).setResultTransformer(Transformers.TO_LIST).list();
      if (list != null && list.size() > 0) {
        map = new HashMap<Timestamp, Timestamp>();
        for (List<Object> row : list) {
          map.put((Timestamp) row.get(0), (Timestamp) row.get(1));
        }
      }
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return map;
  }
}

/**
 * Created on: 14:59:22 25 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import entites.BanAn;
import entites.CTHoaDonBanDat;
import entites.MonAn;
import enums.ETinhTrangHoaDon;
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
  
  @SuppressWarnings("rawtypes")
public boolean checkBanDaDat(String maBA, Timestamp date) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    boolean result = false;
    try {
      tr.begin();
      String sql = "select h.maBD from HoaDonBanDat h inner join CTHoaDonBanDat c on h.maBD = c.maBD" + 
          " where maBA = :maBA and daThanhToan = 0" + 
          " group by h.maBD, h.ngayPhucVu" + 
          " having :date between h.ngayPhucVu and dateadd(minute, count(h.maBD) * 15 + 40, h.ngayPhucVu)";
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
  
  @SuppressWarnings("rawtypes")
public boolean checkSoLuongMonAnHoaDonBanDat(String maBA, Timestamp date, int soLuongMonAn) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    boolean result = false;
    try {
      tr.begin();
      String sql = "select h.maBD from HoaDonBanDat h inner join CTHoaDonBanDat c on h.maBD = c.maBD" + 
          " where maBA = :maBA and daThanhToan = 0" + 
          " group by h.maBD, h.ngayPhucVu" + 
          " having dateadd(minute, :time, :date) between h.ngayPhucVu and dateadd(minute, count(h.maBD) * 15 + 40, h.ngayPhucVu)";
      int time = 40;
      time += 15 * soLuongMonAn;
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
  public List<HoaDonBanDat> getDSTTBanDatTheoCustomer(String maKH, Date ngayPhucVu, ETinhTrangHoaDon tt){
	    Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    List<HoaDonBanDat> list = null;
	    try {
	      tr.begin();
	      String sql = "select b from HoaDonBanDat b join fetch b.dsMonAn where maKH = '" + maKH + "'";
	      if (ngayPhucVu != null) {
	        sql += " and convert(date, ngayPhucVu) = :ngayPhucVu";
	      }
	      if (tt != null) {
	        sql += " and " + tt.getLoaiTT() + " = :tt";
	      }
	      Query query = session.createQuery(sql, HoaDonBanDat.class);
	      if (ngayPhucVu != null) {
          query.setParameter("ngayPhucVu", ngayPhucVu);
        }
        if (tt != null) {
          query.setParameter("tt", tt.isTrangThai());
        }
        list = query.getResultList();
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
  
  public List<HoaDonBanDat> getDSTTBanDatUnique(){
	    Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    List<HoaDonBanDat> list = null;
	    try {
	      tr.begin();
	      String sql = "select b from HoaDonBanDat b join fetch b.dsMonAn order by daThanhToan asc";
	      list = session.createQuery(sql, HoaDonBanDat.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).getResultList();
	      tr.commit();
	    } catch (Exception e) {
	      tr.rollback();
	      e.printStackTrace();
	    }
	    return list;
	  }
  @SuppressWarnings("deprecation")
public List<HoaDonBanDat> getDSTTBanDatTheoKhachHang(){
	    Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    List<HoaDonBanDat> list = null;
	    try {
	      tr.begin();
	      String sql = "select b from HoaDonBanDat b join fetch b.dsMonAn where maKH like 'KH%' order by maKH asc, ngayThanhToan desc";
	      list = session.createQuery(sql, HoaDonBanDat.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).getResultList();
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
  @SuppressWarnings("unchecked")
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
  @SuppressWarnings("unchecked")
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
  @SuppressWarnings("unchecked")
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
  @SuppressWarnings("unchecked")
public List<HoaDonBanDat> timHoaDon(LocalDate date, String tenKH, String soCMND, String soDT) {
	Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    List<HoaDonBanDat> list = null;
    try {
      tr.begin();
      String sql = "select b from HoaDonBanDat b join b.khachHang k where b.maBD like 'BD%'";
      if(date != null) {
    	  sql += " and datediff(day, ngayPhucVu, :value) = 0";
      }
      if(!tenKH.isEmpty()) {
    	  sql += " and hoTen like '%' + :tenKH + '%'";
      }
      if(!soCMND.isEmpty()) {
    	  sql += " and cmnd like '%' + :soCMND + '%'";
      }
      if(!soDT.isEmpty()) {
    	  sql += " and sdt like '%' + :soDT + '%'";
      }
      sql += " order by daThanhToan asc, ngayDatBan desc";
      Query query = session.createQuery(sql, HoaDonBanDat.class);
      if(date != null) {
    	  query.setParameter("value", date);
      }
      if(!tenKH.isEmpty()) {
    	  query.setParameter("tenKH", tenKH);
      }
      if(!soCMND.isEmpty()) {
    	  query.setParameter("soCMND", soCMND);
      }
      if(!soDT.isEmpty()) {
    	  query.setParameter("soDT", soDT);
      }
      list = query.getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
  }
  @SuppressWarnings({ "unchecked", "deprecation" })
public Map<Timestamp, Timestamp> thoiGianBanDaDatTrongMotNgay(String maBA, Date date) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    Map<Timestamp, Timestamp> map = null;
    try {
      tr.begin();
      String sql = "select dateadd(minute, -55, h.ngayPhucVu) as s, dateadd(minute, count(h.maBD) * 15 + 40, h.ngayPhucVu) as e " + 
          "  from HoaDonBanDat h inner join CTHoaDonBanDat c on h.maBD = c.maBD" + 
          "  where daThanhToan = 0 and maBA = :maBA and :date = Convert(date, h.ngayPhucVu)" + 
          "  group by h.maBD, h.ngayPhucVu" + 
          "  order by s";
      List<List<Object>> list = session.createNativeQuery(sql.toString()).setParameter("date", date).setParameter("maBA", maBA)
                                                                      .setResultTransformer(Transformers.TO_LIST).list();
      if (list != null && list.size() > 0) {
        map = new LinkedHashMap<Timestamp, Timestamp>();
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

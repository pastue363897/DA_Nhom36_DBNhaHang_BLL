/**
 * Created on: 14:57:41 25 thg 4, 2020
 * @author Dinh Van Dung YKNB, Ta Khanh Hoang
 */

package database;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entites.BanAn;
import entites.MonAn;

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
      String sql = "select * from BanAn where coBan = 1";
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
          + " maBA from BanAn where coBan = 1 order by newid())";
      list = session.createNativeQuery(sql, BanAn.class).getResultList();
      tr.commit();
    } catch (Exception e) {
      tr.rollback();
      e.printStackTrace();
    }
    return list;
	}
	
	public List<BanAn> timBanAn(String keyword) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    List<BanAn> list = null;
	    try {
	      tr.begin();
	      int num = Integer.parseInt(keyword);
	      String sql = "select * from BanAn where coBan = 1 and ( kySoBA like '%" + keyword + "%' OR moTaBA like N'%" + keyword + "%' OR soLuongGhe = "
	    		  + num + ")";
	      System.out.println(sql);
	      list = session.createNativeQuery(sql, BanAn.class).getResultList();
	      tr.commit();
	    } catch (NumberFormatException e) {
	      String sql = "select * from BanAn where coBan = 1 and ( kySoBA like '%" + keyword + "%' OR moTaBA like N'%" + keyword + "%' OR soLuongGhe = "
	    		  + 0 + ")";
	      System.out.println(sql);
	      list = session.createNativeQuery(sql, BanAn.class).getResultList();
	      tr.commit();
	    } catch (Exception e) {
	      tr.rollback();
	      e.printStackTrace();
	    }
	    return list;
	}
	
	public List<BanAn> timBanAn(String moTaBA, String kySo, int soLuong) {
	    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	      Session session = sessionFactory.getCurrentSession();
	      Transaction tr = session.getTransaction();
	      List<BanAn> list = null;
	      try {
	        tr.begin();
	        String sql = "select * from BanAn where coBan = 1";
	        if (moTaBA != null) {
	          moTaBA = moTaBA.trim();
	        }
	        if (kySo != null && !kySo.trim().isEmpty()) {
	        	kySo = kySo.trim();
	        	sql += " and kySoBA like '%' + :kySo + '%'";
	        }
	        if (moTaBA != null && !moTaBA.isEmpty()) {
	          sql += " and moTaBA like N'%' + :moTa + '%'";
	        }
	        if (soLuong > 0) {
	          sql += " and soLuongGhe <= :soLuong";
	        }
	        
	        Query query = session.createNativeQuery(sql, BanAn.class);
	        
	        if (kySo != null && !kySo.isEmpty()) {
	          query.setParameter("kySo", kySo);
	        }
	        if (moTaBA != null && !moTaBA.isEmpty()) {
	          query.setParameter("moTa", moTaBA);
	        }
	        if (soLuong > 0) {
	          query.setParameter("soLuong", soLuong);
	        }
	        list = query.getResultList();
	        tr.commit();
	      } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	      }
	      return list;
	  }
	
	public List<BanAn> timBanAn(String moTaBA, String gio, Timestamp ngayPhucVu, int soLuong) {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      Session session = sessionFactory.getCurrentSession();
      Transaction tr = session.getTransaction();
      List<BanAn> list = null;
      try {
        tr.begin();
        String sql = "select * from BanAn where coBan = 1";
        if (moTaBA != null) {
          moTaBA = moTaBA.trim();
        }
        if (gio != null) {
          gio = gio.trim();
        }
        if (ngayPhucVu != null) {
          sql += " and maBA <> all (" + 
              "    select maBA from HoaDonBanDat where maBD in ( " + 
              "    select h.maBD from HoaDonBanDat h inner join CTHoaDonBanDat c on h.maBD = c.maBD" + 
              "    where daThanhToan = 0" + 
              "    group by h.maBD, h.ngayPhucVu" + 
              "    having :date between dateadd(minute, -30, h.ngayPhucVu) and dateadd(minute, count(h.maBD) * 10 + 20, h.ngayPhucVu)" + 
              "  )" + 
              " )";
        } else if (gio != null && !gio.isEmpty()) {
          sql += " and maBA <> all (" + 
              "    select maBA from HoaDonBanDat where maBD in ( " + 
              "    select h.maBD from HoaDonBanDat h inner join CTHoaDonBanDat c on h.maBD = c.maBD" + 
              "    where daThanhToan = 0" + 
              "    group by h.maBD, h.ngayPhucVu" + 
              "    having dateadd(day, DATEDIFF(day, 0, h.ngayPhucVu), :gio) between dateadd(minute, -30, h.ngayPhucVu) and dateadd(minute, count(h.maBD) * 10 + 20, h.ngayPhucVu)" + 
              "  )" + 
              " )";
        }
        if (moTaBA != null && !moTaBA.isEmpty()) {
          sql += " and moTaBA like N'%' + :moTa + '%'";
        }
        if (soLuong > 0) {
          sql += " and soLuongGhe <= :soLuong";
        }
        
        Query query = session.createNativeQuery(sql, BanAn.class);
        
        if (ngayPhucVu != null) {
          query.setParameter("date", ngayPhucVu);
        } else if (gio != null && !gio.isEmpty()) {
          query.setParameter("gio", gio);
        }
        if (moTaBA != null && !moTaBA.isEmpty()) {
          query.setParameter("moTa", moTaBA);
        }
        if (soLuong > 0) {
          query.setParameter("soLuong", soLuong);
        }
        list = query.getResultList();
        tr.commit();
      } catch (Exception e) {
        tr.rollback();
        e.printStackTrace();
      }
      return list;
  }
	public List<BanAn> timBanAn(String moTaBA, Date date, int soLuong) {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      Session session = sessionFactory.getCurrentSession();
      Transaction tr = session.getTransaction();
      List<BanAn> list = null;
      try {
        tr.begin();
        String sql1 = "declare @hd table (id int identity(1,1), ma varchar(8))" + 
            " insert into @hd" + 
            " select maBA from HoaDonBanDat h1 where daThanhToan = 0 and :date = Convert(date, h1.ngayPhucVu) group by maBA" + 
            " declare @b table (maBA varchar(8), kySoBA varchar(10), soLuongGhe int, moTaBA nvarchar(500), phuGia bigint, coBan bit, hinhAnh nvarchar(100))" + 
            " declare @length int" + 
            " set @length = (select count(*) from @hd)" + 
            " declare @ma varchar(8)" + 
            " declare @i int" + 
            " set @i = 1" + 
            " while @i <= @length" + 
            " begin" + 
            "  set @ma = (select ma from @hd where id = @i)" + 
            "  declare @table table (id int, s datetime, e datetime)" + 
            "  delete from @table" + 
            "  insert into @table(id, s, e)  " + 
            "    select row_number() over (order by h.ngayPhucVu), dateadd(minute, -30, h.ngayPhucVu) as s, dateadd(minute, count(h.maBD) * 10 + 20, h.ngayPhucVu) as e " + 
            "    from HoaDonBanDat h inner join CTHoaDonBanDat c on h.maBD = c.maBD" + 
            "    where daThanhToan = 0 and h.maBA = @ma and :date = Convert(date, h.ngayPhucVu)" + 
            "    group by h.maBD, h.ngayPhucVu" + 
            "    order by s asc" + 
            "  declare @time datetime" + 
            "  set @time = dateadd(day, DATEDIFF(day, 0, :date), '00:20:00.000')" + 
            "  declare @count int" + 
            "  set @count = (select count(*) from @table)" + 
            "  declare @j int" + 
            "  set @j = 1" + 
            "  while @j <= @count" + 
            "  begin" + 
            "    set @time = dateadd(minute, 10, @time)" + 
            "    set @time = ( select e from @table where id = @j and @time between s and e );" + 
            "    if @time is null" + 
            "      break;" + 
            "    set @j = @j + 1;" + 
            "  end" + 
            "  if @time is null" + 
            "    insert into @b select * from BanAn where maBA = @ma" + 
            "  else if @time < dateadd(day, DATEDIFF(day, 0, :date), '23:30:00.000')" + 
            "    insert into @b select * from BanAn where maBA = @ma" + 
            "  set @i = @i + 1;" + 
            " end" + 
            " select * from @b where coBan = 1";
        String sql2 = " union" + 
            " select * from BanAn where coBan = 1 and maBA <> all (" + 
            "  select ma from @hd" + 
            " )";
        if (moTaBA != null) {
          moTaBA = moTaBA.trim();
        }
        
        if (moTaBA != null && !moTaBA.isEmpty()) {
          sql1 += " and moTaBA like N'%' + :moTa + '%'";
          sql2 += " and moTaBA like N'%' + :moTa + '%'";
        }
        if (soLuong > 0) {
          sql1 += " and soLuongGhe <= :soLuong";
          sql2 += " and soLuongGhe <= :soLuong";
        }
        
        Query query = session.createNativeQuery(sql1 + sql2, BanAn.class);
        query.setParameter("date", date);
        if (moTaBA != null && !moTaBA.isEmpty()) {
          query.setParameter("moTa", moTaBA);
        }
        if (soLuong > 0) {
          query.setParameter("soLuong", soLuong);
        }
        
        list = query.getResultList();
        tr.commit();
      } catch (Exception e) {
        tr.rollback();
        e.printStackTrace();
      }
      return list;
  }
}

/**
 * Created on: 08:12:19 23 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package entites;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import database.CTHoaDonBanDatDAO;

@Entity
@Table(name = "HoaDonBanDat")
public class HoaDonBanDat implements Serializable{
  private static final long serialVersionUID = -6630803645917114804L;
  @Id
  private String maBD;
  @ManyToOne
  @JoinColumn(name = "maKH")
  private Customer khachHang;
  @ManyToOne
  @JoinColumn(name = "maNV")
  private Admin nhanVien;
  private Timestamp ngayDatBan;
  private Timestamp ngayPhucVu;
  @ManyToOne
  @JoinColumn(name = "maBA")
  private BanAn banAn;
  private boolean daHuy;
  @OneToMany(mappedBy = "ttBanDat")
  private List<CTHoaDonBanDat> dsMonAn;
  private boolean daThanhToan;
  private long phuGiaBanAn;
  private long tienDaDua;
  private Timestamp ngayThanhToan;
  public HoaDonBanDat() {
    super();
  }
  public HoaDonBanDat(String maBD, Customer khachHang, Timestamp ngayDatBan, Timestamp ngayPhucVu, BanAn banAn,
    boolean daHuy, List<CTHoaDonBanDat> dsMonAn) {
    super();
    this.maBD = maBD;
    this.khachHang = khachHang;
    this.ngayDatBan = ngayDatBan;
    this.ngayPhucVu = ngayPhucVu;
    this.banAn = banAn;
    this.phuGiaBanAn= banAn.getPhuGia();
    this.daHuy = daHuy;
    this.dsMonAn = dsMonAn;
  }
  
  public HoaDonBanDat(String maBD, Customer khachHang, Admin nhanVien, Timestamp ngayDatBan, Timestamp ngayPhucVu, BanAn banAn,
    boolean daHuy, List<CTHoaDonBanDat> dsMonAn) {
    super();
    this.maBD = maBD;
    this.nhanVien = nhanVien;
    this.khachHang = khachHang;
    this.ngayDatBan = ngayDatBan;
    this.ngayPhucVu = ngayPhucVu;
    this.banAn = banAn;
    this.daHuy = daHuy;
    this.dsMonAn = dsMonAn;
  }
  
  public HoaDonBanDat(Customer khachHang, Timestamp ngayDatBan, Timestamp ngayPhucVu, BanAn banAn) {
    super();
    this.khachHang = khachHang;
    this.ngayDatBan = ngayDatBan;
    this.ngayPhucVu = ngayPhucVu;
    this.banAn = banAn;
    this.phuGiaBanAn= banAn.getPhuGia();
  }
  public String getMaBD() {
    return maBD;
  }
  public void setMaBD(String maBD) {
    this.maBD = maBD;
  }
  public Admin getNhanVien() {
	return nhanVien;
  }
  public void setNhanVien(Admin nhanVien) {
	this.nhanVien = nhanVien;
  }
  public Customer getKhachHang() {
    return khachHang;
  }
  public void setKhachHang(Customer khachHang) {
    this.khachHang = khachHang;
  }
  public Timestamp getNgayDatBan() {
    return ngayDatBan;
  }
  public void setNgayDatBan(Timestamp ngayDatBan) {
    this.ngayDatBan = ngayDatBan;
  }
  public Timestamp getNgayPhucVu() {
    return ngayPhucVu;
  }
  public void setNgayPhucVu(Timestamp ngayPhucVu) {
    this.ngayPhucVu = ngayPhucVu;
  }
  public BanAn getBanAn() {
    return banAn;
  }
  public void setBanAn(BanAn banAn) {
    this.banAn = banAn;
  }
  public boolean isDaHuy() {
    return daHuy;
  }
  public void setDaHuy(boolean daHuy) {
    this.daHuy = daHuy;
  }
  public List<CTHoaDonBanDat> getDsMonAn() {
    return dsMonAn;
  }
  public void setDsMonAn(List<CTHoaDonBanDat> dsMonAn) {
    this.dsMonAn = dsMonAn;
  }
  public boolean isDaThanhToan() {
	return daThanhToan;
}
	public void setDaThanhToan(boolean daThanhToan) {
		this.daThanhToan = daThanhToan;
	}
	public long getPhuGiaBanAn() {
		return phuGiaBanAn;
	}
	public void setPhuGiaBanAn(long phuGiaBanAn) {
		this.phuGiaBanAn = phuGiaBanAn;
	}
	public long getTienDaDua() {
		return tienDaDua;
	}
	public void setTienDaDua(long tienDaDua) {
		this.tienDaDua = tienDaDua;
	}
	public Timestamp getNgayThanhToan() {
		return ngayThanhToan;
	}
	public void setNgayThanhToan(Timestamp ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}
	public long tinhTongTien() {
	     long tt = 0;
	     dsMonAn = new CTHoaDonBanDatDAO().getDSCTTBanDatMonAnTheoMaBD(maBD);
	     for(CTHoaDonBanDat s : dsMonAn) {
	       tt += s.getDonGia() * s.getSoLuong();
	     }
	     return tt;
	  }
	@Override
	public String toString() {
		return "HoaDonBanDat [maBD=" + maBD + ", khachHang=" + khachHang + ", ngayDatBan=" + ngayDatBan + ", ngayPhucVu="
				+ ngayPhucVu + ", banAn=" + banAn + ", daHuy=" + daHuy + ", dsMonAn=" + dsMonAn + ", daThanhToan="
				+ daThanhToan + ", tongTien=" + phuGiaBanAn + ", tienDaDua=" + tienDaDua + ", ngayThanhToan=" + ngayThanhToan
				+ "]";
	}
	
}

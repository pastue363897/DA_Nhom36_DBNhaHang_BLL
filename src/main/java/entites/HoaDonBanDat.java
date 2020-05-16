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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HoaDonBanDat")
public class HoaDonBanDat implements Serializable{
  private static final long serialVersionUID = -6630803645917114804L;
  @Id
  private String maBD;
  @ManyToOne
  @JoinColumn(name = "maKH")
  private Customer khachHang;
  private Timestamp ngayDatBan;
  private Timestamp ngayPhucVu;
  @ManyToOne
  @JoinColumn(name = "maBA")
  private BanAn banAn;
  private boolean daHuy;
  @OneToMany(mappedBy = "ttBanDat")
  private List<CTHoaDonBanDat> dsMonAn;
  private boolean daThanhToan;
  private long tongTien;
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
    this.daHuy = daHuy;
    this.dsMonAn = dsMonAn;
  }
  
  public HoaDonBanDat(Customer khachHang, Timestamp ngayDatBan, Timestamp ngayPhucVu, BanAn banAn) {
    super();
    this.khachHang = khachHang;
    this.ngayDatBan = ngayDatBan;
    this.ngayPhucVu = ngayPhucVu;
    this.banAn = banAn;
  }
  public String getMaBD() {
    return maBD;
  }
  public void setMaBD(String maBD) {
    this.maBD = maBD;
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
	public long getTongTien() {
		return tongTien;
	}
	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
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
	     long tt = banAn.getPhuGia();
	     for(CTHoaDonBanDat s : dsMonAn) {
	       tt += s.getDonGia() * s.getSoLuong();
	     }
	     return tt;
	  }
	@Override
	public String toString() {
		return "HoaDonBanDat [maBD=" + maBD + ", khachHang=" + khachHang + ", ngayDatBan=" + ngayDatBan + ", ngayPhucVu="
				+ ngayPhucVu + ", banAn=" + banAn + ", daHuy=" + daHuy + ", dsMonAn=" + dsMonAn + ", daThanhToan="
				+ daThanhToan + ", tongTien=" + tongTien + ", tienDaDua=" + tienDaDua + ", ngayThanhToan=" + ngayThanhToan
				+ "]";
	}
	
}

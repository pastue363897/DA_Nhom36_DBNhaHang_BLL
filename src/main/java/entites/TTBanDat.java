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
@Table(name = "TTBanDat")
public class TTBanDat implements Serializable{
  private static final long serialVersionUID = -6630803645917114804L;
  @Id
  private String maBD;
  @ManyToOne
  @JoinColumn(name = "khachHang")
  private Customer khachHang;
  private Timestamp ngayDatBan;
  private Timestamp ngayPhucVu;
  @ManyToOne
  @JoinColumn(name = "banAn")
  private BanAn banAn;
  private long tongTien;
  private boolean daThanhToan;
  private boolean daHuy;
  @OneToMany(mappedBy = "ttBanDat")
  private List<CTTTBanDatMonAn> dsMonAn;
  @OneToOne
  @JoinColumn(name = "chiTietThanhToan", referencedColumnName = "maHD")
  private ChiTietThanhToan chiTietThanhToan;
  public TTBanDat() {
    super();
  }
  public TTBanDat(String maBD, Customer khachHang, Timestamp ngayDatBan, Timestamp ngayPhucVu, BanAn banAn, long tongTien,
      boolean daThanhToan, boolean daHuy, List<CTTTBanDatMonAn> dsMonAn) {
    super();
    this.maBD = maBD;
    this.khachHang = khachHang;
    this.ngayDatBan = ngayDatBan;
    this.ngayPhucVu = ngayPhucVu;
    this.banAn = banAn;
    this.tongTien = tongTien;
    this.daThanhToan = daThanhToan;
    this.daHuy = daHuy;
    this.dsMonAn = dsMonAn;
  }
  
  public TTBanDat(Customer khachHang, Timestamp ngayDatBan, Timestamp ngayPhucVu, BanAn banAn) {
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
  public long getTongTien() {
    return tongTien;
  }
  public void setTongTien(long tongTien) {
    this.tongTien = tongTien;
  }
  public boolean isDaThanhToan() {
    return daThanhToan;
  }
  public void setDaThanhToan(boolean daThanhToan) {
    this.daThanhToan = daThanhToan;
  }
  public boolean isDaHuy() {
    return daHuy;
  }
  public void setDaHuy(boolean daHuy) {
    this.daHuy = daHuy;
  }
  public List<CTTTBanDatMonAn> getDsMonAn() {
    return dsMonAn;
  }
  public void setDsMonAn(List<CTTTBanDatMonAn> dsMonAn) {
    this.dsMonAn = dsMonAn;
  }
	public ChiTietThanhToan getChiTietThanhToan() {
		return chiTietThanhToan;
	}
	public void setChiTietThanhToan(ChiTietThanhToan chiTietThanhToan) {
		this.chiTietThanhToan = chiTietThanhToan;
	}
	@Override
	public String toString() {
		return "TTBanDat [maBD=" + maBD + ", khachHang=" + khachHang + ", ngayDatBan=" + ngayDatBan + ", ngayPhucVu="
				+ ngayPhucVu + ", banAn=" + banAn + ", tongTien=" + tongTien + ", daThanhToan=" + daThanhToan
				+ ", daHuy=" + daHuy + ", dsMonAn=" + dsMonAn + ", chiTietThanhToan=" + chiTietThanhToan + "]";
	}
    
}

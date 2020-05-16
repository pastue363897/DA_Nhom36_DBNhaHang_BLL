/**
 * Created on: 08:12:01 23 thg 4, 2020
 * @author Ta Khanh Hoang
 */

package entites;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HoaDon")
public class HoaDon implements Serializable {
	private static final long serialVersionUID = 1631184720491949435L;
	@Id
	@OneToOne
	@JoinColumn(name = "maHD", referencedColumnName = "maBD")
	private TTBanDat ttBanDat;
	private long tongTien;
	private long tienDaDua;
	private long tienThoiLai;
	private Timestamp ngayThanhToan;
	public TTBanDat getMaHD() {
		return ttBanDat;
	}
	public void setMaHD(TTBanDat ttBanDat) {
		this.ttBanDat = ttBanDat;
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
	public long getTienThoiLai() {
		return tienThoiLai;
	}
	public void setTienThoiLai(long tienThoiLai) {
		this.tienThoiLai = tienThoiLai;
	}
	public Timestamp getNgayThanhToan() {
		return ngayThanhToan;
	}
	public void setNgayThanhToan(Timestamp ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}
	public HoaDon(TTBanDat ttBanDat, long tongTien, long tienDaDua, long tienThoiLai,
			Timestamp ngayThanhToan) {
		super();
		this.ttBanDat = ttBanDat;
		this.tongTien = tongTien;
		this.tienDaDua = tienDaDua;
		this.tienThoiLai = tienThoiLai;
		this.ngayThanhToan = ngayThanhToan;
	}
	public HoaDon() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ttBanDat == null) ? 0 : ttBanDat.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		if (ttBanDat == null) {
			if (other.ttBanDat != null)
				return false;
		} else if (!ttBanDat.equals(other.ttBanDat))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "HoaDon [ttBanDat=" + ttBanDat + ", tongTien=" + tongTien + ", tienDaDua=" + tienDaDua
				+ ", tienThoiLai=" + tienThoiLai + ", ngayThanhToan=" + ngayThanhToan + "]";
	}
}

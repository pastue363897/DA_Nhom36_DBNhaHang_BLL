/**
 * Created on: 08:12:01 23 thg 4, 2020
 * @author Ta Khanh Hoang
 */

package entites;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ChiTietThanhToan")
public class ChiTietThanhToan implements Serializable {
	private static final long serialVersionUID = 1631184720491949435L;
	@Id
	private String maHD;
	private long tongTien;
	private long tienDaDua;
	private long tienThoiLai;
	private Timestamp ngayThanhToan;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
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
	public ChiTietThanhToan(String maHD, long tongTien, long tienDaDua, long tienThoiLai,
			Timestamp ngayThanhToan) {
		super();
		this.maHD = maHD;
		this.tongTien = tongTien;
		this.tienDaDua = tienDaDua;
		this.tienThoiLai = tienThoiLai;
		this.ngayThanhToan = ngayThanhToan;
	}
	public ChiTietThanhToan() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHD == null) ? 0 : maHD.hashCode());
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
		ChiTietThanhToan other = (ChiTietThanhToan) obj;
		if (maHD == null) {
			if (other.maHD != null)
				return false;
		} else if (!maHD.equals(other.maHD))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChiTietThanhToan [maHD=" + maHD + ", tongTien=" + tongTien + ", tienDaDua=" + tienDaDua
				+ ", tienThoiLai=" + tienThoiLai + ", ngayThanhToan=" + ngayThanhToan + "]";
	}
}

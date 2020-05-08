/**
 * Created on: 08:11:41 23 thg 4, 2020
 * @author Dinh Van Dung YKNB, Ta Khanh Hoang
 */

package entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KhachHang")
public class Customer implements Serializable{
  private static final long serialVersionUID = -2418817978735275622L;
  @Id
  private String maKH;
  private String hoTen;
  private String diaChi;
  private String cmnd;
  private String sdt;
  private String email;
  @OneToOne
  @JoinColumn(name = "taiKhoan")
  private Account taiKhoan;
  public Customer() {
    super();
  }
  public Customer(String hoTen, String diaChi, String cmnd, String sdt, String email, Account taiKhoan) {
    super();
    this.hoTen = hoTen;
    this.diaChi = diaChi;
    this.cmnd = cmnd;
    this.sdt = sdt;
    this.email = email;
    this.taiKhoan = taiKhoan;
  }

  public String getMaKH() {
    return maKH;
  }
  public void setMaKH(String maKH) {
    this.maKH = maKH;
  }
  public String getHoTen() {
    return hoTen;
  }
  public void setHoTen(String hoTen) {
    this.hoTen = hoTen;
  }
  public String getDiaChi() {
    return diaChi;
  }
  public void setDiaChi(String diaChi) {
    this.diaChi = diaChi;
  }
  public String getCmnd() {
    return cmnd;
  }
  public void setCmnd(String cmnd) {
    this.cmnd = cmnd;
  }
  public String getSdt() {
    return sdt;
  }
  public void setSdt(String sdt) {
    this.sdt = sdt;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Account getTaiKhoan() {
    return taiKhoan;
  }
  public void setTaiKhoan(Account taiKhoan) {
    this.taiKhoan = taiKhoan;
  }
  @Override
  public String toString() {
    return "KhachHang [maKH=" + maKH + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", cmnd=" + cmnd + ", taiKhoan="
        + taiKhoan.toString() + "]";
  }
  
}

/**
 * Created on: 08:11:41 23 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable{
  private static final long serialVersionUID = -2418817978735275622L;
  @Id
  private String maKH;
  private String hoTen;
  private String diaChi;
  private String cmnd;
  @OneToOne
  @JoinColumn(name = "taiKhoan")
  private Account taiKhoan;
  public Customer() {
    super();
  }
  public Customer(String maKH, String hoTen, String diaChi, String cmnd, Account taiKhoan) {
    super();
    this.maKH = maKH;
    this.hoTen = hoTen;
    this.diaChi = diaChi;
    this.cmnd = cmnd;
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
  public Account getTaiKhoan() {
    return taiKhoan;
  }
  public void setTaiKhoan(Account taiKhoan) {
    this.taiKhoan = taiKhoan;
  }
  @Override
  public String toString() {
    return "Customer [maKH=" + maKH + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", cmnd=" + cmnd + ", taiKhoan="
        + taiKhoan.toString() + "]";
  }
  
}

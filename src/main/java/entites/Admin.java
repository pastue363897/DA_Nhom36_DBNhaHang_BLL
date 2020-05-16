/**
 * Created on: 08:11:32 23 thg 4, 2020
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
@Table(name = "NguoiQuanLy")
public class Admin implements Serializable{
  private static final long serialVersionUID = -6175789101923072450L;

  private String hoTen;
  @Id
  @OneToOne
  @JoinColumn(name = "maNV", referencedColumnName = "maTK")
  private Account taiKhoan;
  public Admin() {
    super();
  }
  public Admin(String hoTen, Account taiKhoan) {
    super();
    this.hoTen = hoTen;
    this.taiKhoan = taiKhoan;
  }
  public String getHoTen() {
    return hoTen;
  }
  public void setHoTen(String hoTen) {
    this.hoTen = hoTen;
  }
  public Account getTaiKhoan() {
    return taiKhoan;
  }
  public void setTaiKhoan(Account taiKhoan) {
    this.taiKhoan = taiKhoan;
  }
  @Override
  public String toString() {
    return "Admin [hoTen=" + hoTen + ", taiKhoan=" + taiKhoan + "]";
  }
  
}

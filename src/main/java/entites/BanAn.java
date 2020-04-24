/**
 * Created on: 08:12:01 23 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BanAn")
public class BanAn implements Serializable{
  private static final long serialVersionUID = 1188768848735680153L;
  @Id
  private String maBA;
  private String kySoBA;
  private int soLuongGhe;
  private String motaBA;
  private long giaTien;
  private boolean isUsable;
  private boolean isDeleted;
  private String hinhAnh;
  public BanAn() {
    super();
  }
  public BanAn(String maBA, String kySoBA, int soLuongGhe, String motaBA, long giaTien, boolean isUsable,
      boolean isDeleted, String hinhAnh) {
    super();
    this.maBA = maBA;
    this.kySoBA = kySoBA;
    this.soLuongGhe = soLuongGhe;
    this.motaBA = motaBA;
    this.giaTien = giaTien;
    this.isUsable = isUsable;
    this.isDeleted = isDeleted;
    this.hinhAnh = hinhAnh;
  }
  public String getMaBA() {
    return maBA;
  }
  public void setMaBA(String maBA) {
    this.maBA = maBA;
  }
  public String getKySoBA() {
    return kySoBA;
  }
  public void setKySoBA(String kySoBA) {
    this.kySoBA = kySoBA;
  }
  public int getSoLuongGhe() {
    return soLuongGhe;
  }
  public void setSoLuongGhe(int soLuongGhe) {
    this.soLuongGhe = soLuongGhe;
  }
  public String getMotaBA() {
    return motaBA;
  }
  public void setMotaBA(String motaBA) {
    this.motaBA = motaBA;
  }
  public long getGiaTien() {
    return giaTien;
  }
  public void setGiaTien(long giaTien) {
    this.giaTien = giaTien;
  }
  public boolean isUsable() {
    return isUsable;
  }
  public void setUsable(boolean isUsable) {
    this.isUsable = isUsable;
  }
  public boolean isDeleted() {
    return isDeleted;
  }
  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }
  public String getHinhAnh() {
    return hinhAnh;
  }
  public void setHinhAnh(String hinhAnh) {
    this.hinhAnh = hinhAnh;
  }
  @Override
  public String toString() {
    return "BanAn [maBA=" + maBA + ", kySoBA=" + kySoBA + ", soLuongGhe=" + soLuongGhe + ", motaBA=" + motaBA
        + ", giaTien=" + giaTien + ", isUsable=" + isUsable + ", isDeleted=" + isDeleted + ", hinhAnh=" + hinhAnh + "]";
  }
  
}

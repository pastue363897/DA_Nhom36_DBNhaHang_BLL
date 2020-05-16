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
  private long phuGia;
  private boolean coBan;
  private String hinhAnh;
  public BanAn() {
    super();
  }
  public BanAn(String maBA, String kySoBA, int soLuongGhe, String motaBA, long giaTien, boolean coBan,
      boolean isDeleted, String hinhAnh) {
    super();
    this.maBA = maBA;
    this.kySoBA = kySoBA;
    this.soLuongGhe = soLuongGhe;
    this.motaBA = motaBA;
    this.phuGia = giaTien;
    this.coBan = coBan;
    this.hinhAnh = hinhAnh;
  }
  public BanAn(String kySoBA, int soLuongGhe, String motaBA, long giaTien, boolean coBan, boolean isDeleted,
      String hinhAnh) {
    super();
    this.kySoBA = kySoBA;
    this.soLuongGhe = soLuongGhe;
    this.motaBA = motaBA;
    this.phuGia = giaTien;
    this.coBan = coBan;
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
  public long getPhuGia() {
    return phuGia;
  }
  public void setPhuGia(long phuGia) {
    this.phuGia = phuGia;
  }
  public boolean isCoBan() {
    return coBan;
  }
  public void setCoBan(boolean coBan) {
    this.coBan = coBan;
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
        + ", phuGia=" + phuGia + ", coBan=" + coBan + ", hinhAnh=" + hinhAnh + "]";
  }
  
}

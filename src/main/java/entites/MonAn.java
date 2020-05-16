/**
 * Created on: 08:12:07 23 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MonAn")
public class MonAn implements Serializable{
  private static final long serialVersionUID = -7559203192270594668L;
  @Id
  private String maMA;
  private String tenMA;
  private String nguyenLieu;
  private String moTaMA;
  private int soLuongNguoi;
  private String hinhAnhMA;
  private long giaTien;
  private boolean daHuy;
  public MonAn() {
    super();
  }
  public MonAn(String maMA, String tenMA, String moTaMA, int soLuongNguoi, String hinhAnhMA, long giaTien, boolean daHuy) {
    super();
    this.maMA = maMA;
    this.tenMA = tenMA;
    this.moTaMA = moTaMA;
    this.soLuongNguoi = soLuongNguoi;
    this.hinhAnhMA = hinhAnhMA;
    this.giaTien = giaTien;
    this.daHuy = daHuy;
  }
  public MonAn(String tenMA, String moTaMA, int soLuongNguoi, String hinhAnhMA, long giaTien) {
    super();
    this.tenMA = tenMA;
    this.moTaMA = moTaMA;
    this.soLuongNguoi = soLuongNguoi;
    this.hinhAnhMA = hinhAnhMA;
    this.giaTien = giaTien;
    this.daHuy = false;
  }
  
  public MonAn(String maMA, String tenMA, String nguyenLieu, String moTaMA, int soLuongNguoi, String hinhAnhMA,
      long giaTien, boolean daHuy) {
    super();
    this.maMA = maMA;
    this.tenMA = tenMA;
    this.nguyenLieu = nguyenLieu;
    this.moTaMA = moTaMA;
    this.soLuongNguoi = soLuongNguoi;
    this.hinhAnhMA = hinhAnhMA;
    this.giaTien = giaTien;
    this.daHuy = daHuy;
  }
  public String getMaMA() {
    return maMA;
  }
  public void setMaMA(String maMA) {
    this.maMA = maMA;
  }
  public String getTenMA() {
    return tenMA;
  }
  public void setTenMA(String tenMA) {
    this.tenMA = tenMA;
  }
  
  public String getNguyenLieu() {
    return nguyenLieu;
  }
  public void setNguyenLieu(String nguyenLieu) {
    this.nguyenLieu = nguyenLieu;
  }
  public String getMoTaMA() {
    return moTaMA;
  }
  public void setMoTaMA(String moTaMA) {
    this.moTaMA = moTaMA;
  }
  public int getSoLuongNguoi() {
    return soLuongNguoi;
  }
  public void setSoLuongNguoi(int soLuongNguoi) {
    this.soLuongNguoi = soLuongNguoi;
  }
  public String getHinhAnhMA() {
    return hinhAnhMA;
  }
  public void setHinhAnhMA(String hinhAnhMA) {
    this.hinhAnhMA = hinhAnhMA;
  }
  public long getGiaTien() {
    return giaTien;
  }
  public void setGiaTien(long giaTien) {
    this.giaTien = giaTien;
  }
  public boolean isDaHuy() {
    return daHuy;
  }
  public void setDaHuy(boolean daHuy) {
    this.daHuy = daHuy;
  }
  @Override
  public String toString() {
    return "MonAn [maMA=" + maMA + ", tenMA=" + tenMA + ", nguyenLieu=" + nguyenLieu + ", moTaMA=" + moTaMA
        + ", soLuongNguoi=" + soLuongNguoi + ", hinhAnhMA=" + hinhAnhMA + ", giaTien=" + giaTien + ", daHuy=" + daHuy
        + "]";
  }
}

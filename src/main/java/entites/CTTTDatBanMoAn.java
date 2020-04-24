/**
 * Created on: 08:12:31 23 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CTTTDatBanMoAn")
@IdClass(PK_BanDat_MonAn.class)
public class CTTTDatBanMoAn implements Serializable{
  private static final long serialVersionUID = -8856029055109251762L;
  @Id
  @ManyToOne
  @JoinColumn(name = "ttBanDat", referencedColumnName = "maBD")
  private TTBanDat ttBanDat;
  @Id
  @ManyToOne
  @JoinColumn(name = "monAn", referencedColumnName = "maMA")
  private MonAn monAn;
  private int soLuong;
  private long donGia;
  public CTTTDatBanMoAn() {
    super();
  }
  public CTTTDatBanMoAn(TTBanDat ttBanDat, MonAn monAn, int soLuong, long donGia) {
    super();
    this.ttBanDat = ttBanDat;
    this.monAn = monAn;
    this.soLuong = soLuong;
    this.donGia = donGia;
  }
  public TTBanDat getTtBanDat() {
    return ttBanDat;
  }
  public void setTtBanDat(TTBanDat ttBanDat) {
    this.ttBanDat = ttBanDat;
  }
  public MonAn getMonAn() {
    return monAn;
  }
  public void setMonAn(MonAn monAn) {
    this.monAn = monAn;
  }
  public int getSoLuong() {
    return soLuong;
  }
  public void setSoLuong(int soLuong) {
    this.soLuong = soLuong;
  }
  public long getDonGia() {
    return donGia;
  }
  public void setDonGia(long donGia) {
    this.donGia = donGia;
  }
  @Override
  public String toString() {
    return "CTTTDatBanMoAn [ttBanDat=" + ttBanDat.toString() + ", monAn=" + monAn.toString() + ", soLuong=" + soLuong + ", donGia=" + donGia
        + "]";
  }
  
}

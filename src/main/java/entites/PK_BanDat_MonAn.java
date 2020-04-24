/**
 * Created on: 12:28:58 23 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package entites;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PK_BanDat_MonAn implements Serializable{
  private static final long serialVersionUID = -6820397562433955009L;
  private String ttBanDat;
  private String monAn;
  public PK_BanDat_MonAn() {
    super();
  }
  public PK_BanDat_MonAn(String ttBanDat, String monAn) {
    super();
    this.ttBanDat = ttBanDat;
    this.monAn = monAn;
  }
  public String getTtBanDat() {
    return ttBanDat;
  }
  public void setTtBanDat(String ttBanDat) {
    this.ttBanDat = ttBanDat;
  }
  public String getMonAn() {
    return monAn;
  }
  public void setMonAn(String monAn) {
    this.monAn = monAn;
  }
  
}

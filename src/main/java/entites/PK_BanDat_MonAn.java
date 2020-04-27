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
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((monAn == null) ? 0 : monAn.hashCode());
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
    PK_BanDat_MonAn other = (PK_BanDat_MonAn) obj;
    if (monAn == null) {
      if (other.monAn != null)
        return false;
    } else if (!monAn.equals(other.monAn))
      return false;
    if (ttBanDat == null) {
      if (other.ttBanDat != null)
        return false;
    } else if (!ttBanDat.equals(other.ttBanDat))
      return false;
    return true;
  }
  
  
}

/**
 * Created on: 11:31:36 16 thg 6, 2020
 * @author Dinh Van Dung YKNB
 */

package enums;

public enum ETinhTrangHoaDon {
  TatCa(true, "true = :tt"), DaThanhToan(true, "daThanhToan = :tt"), ChuaThanhToan(false, "(daThanhToan= :tt and daHuy = :tt)"), DaHuy(true, "daHuy = :tt");
  private boolean trangThai;
  private String loaiTT;
  private ETinhTrangHoaDon(boolean trangThai, String loaiTT) {
    this.trangThai = trangThai;
    this.loaiTT = loaiTT;
  }
  public boolean isTrangThai() {
    return trangThai;
  }
  public String getLoaiTT() {
    return loaiTT;
  }
}

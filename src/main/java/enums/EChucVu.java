/**
 * Created on: 16:27:28 16 thg 7, 2020
 * @author Dinh Van Dung YKNB
 */

package enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

public enum EChucVu {
  NguoiQuanLy("QuanLy"), NhanVien("NhanVien");
  private String chucVu;

  private EChucVu(String chucVu) {
    this.chucVu = chucVu;
  }

  public String getChucVu() {
    return chucVu;
  }

  public void setChucVu(String chucVu) {
    this.chucVu = chucVu;
  }
  
  private static EChucVu fromString(String chucVu) {
    for (EChucVu eChucVu : EChucVu.values()) {
      if (eChucVu.getChucVu().compareToIgnoreCase(chucVu) == 0) {
        return eChucVu;
      }
    }
    return EChucVu.NhanVien;
  }
  
  @Convert
  public static class GenderConverter implements AttributeConverter<EChucVu, String> {
    
    @Override
    public String convertToDatabaseColumn(EChucVu chucVu) {
      return chucVu.getChucVu();
    }

    @Override
    public EChucVu convertToEntityAttribute(String chucVu) {
      if (chucVu == null) {
        return EChucVu.NhanVien;
      }
      return EChucVu.fromString(chucVu);
    }
    
  }
  
}

/**
 * Created on: 08:11:32 23 thg 4, 2020
 * @author Dinh Van Dung YKNB, Ta Khanh Hoang
 */

package entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NguoiQuanLy")
public class Admin implements Serializable {
	private static final long serialVersionUID = -6175789101923072450L;

	@Id
	private String maNV;
	private String hoTen;
	@Column(unique = true)
	private String username;
	private String passwordHash;
	private String salt;

	public Admin() {
		super();
	}

	public Admin(String maNV, String hoTen, String username, String passwordHash, String salt) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.username = username;
		this.passwordHash = passwordHash;
		this.salt = salt;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}

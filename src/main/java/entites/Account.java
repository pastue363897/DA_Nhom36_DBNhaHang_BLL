/**
 * Created on: 08:11:20 23 thg 4, 2020
 * @author Dinh Van Dung YKNB, Ta Khanh Hoang
 */

package entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.bytebuddy.utility.RandomString;

@Entity
@Table(name = "TaiKhoan")
public class Account implements Serializable{
  private static final long serialVersionUID = -2609248807630820423L;
  @Id
  private String maTK;
  @Column(unique = true)
  private String username;
  private String passwordHash;
  private String salt;
  public Account() {
    super();
  }
  public Account(String username, String passwordHash, String salt) {
    super();
    this.username = username;
    this.passwordHash = passwordHash;
    this.salt = salt;
  }
  public Account(String username, String passwordHash) {
    super();
    this.username = username;
    this.passwordHash = passwordHash;
    setSalt();
  }
  public String getMaTK() {
    return maTK;
  }
  public void setMaTK(String maTK) {
    this.maTK = maTK;
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
  public void setSalt() {
    this.salt = new RandomString(15).nextString();
  }
  @Override
  public String toString() {
    return "Account [maTK=" + maTK + ", username=" + username + ", passwordHash=" + passwordHash + ", salt=" + salt
        + "]";
  }
}

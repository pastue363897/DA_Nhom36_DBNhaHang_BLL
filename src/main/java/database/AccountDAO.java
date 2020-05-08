/**
 * Created on: 15:44:42 24 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import entites.Account;

public class AccountDAO extends GeneralCRUD<Account>{

  public AccountDAO() {
    super(Account.class);
  }
  private String toHexString(byte[] hash) {
    String ret = "";
    int length = hash.length;
    for (int i = 0; i < length; i++) {
      ret += String.format("%02x", hash[i]);
    }
    return ret;
  }

  public int signIn(String username, String password) {
    Account chk = get(username);
    if (chk == null)
      return 0;
    String merge = password + chk.getSalt();
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA-256");
      byte[] res = md.digest(merge.getBytes(StandardCharsets.UTF_8));
      String candidateHash = toHexString(res);
      String realHash = chk.getPasswordHash();
      if(candidateHash.equalsIgnoreCase(realHash)) {
        return 1;
      }
      return 0;
      
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return 0;
    }
  }
  public boolean addAccount(Account account) {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA-256");
      String merge = account.getPasswordHash() + account.getSalt();
      byte[] res = md.digest(merge.getBytes(StandardCharsets.UTF_8));
      String realHash = toHexString(res);
      account.setPasswordHash(realHash);
      if (save(account) != null) {
        return true;
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return false;
  }
}

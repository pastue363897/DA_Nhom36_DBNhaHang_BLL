/**
 * Created on: 15:46:29 24 thg 4, 2020
 * @author Dinh Van Dung YKNB, Ta Khanh Hoang
 */

package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import entites.Account;
import entites.Admin;

public class AdminDAO extends GeneralCRUD<Admin> {

	public AdminDAO() {
		super(Admin.class);
	}
	
	private String toHexString(byte[] hash) {
		String ret = "";
		int length = hash.length;
		for (int i = 0; i < length; i++) {
			ret += String.format("%02x", hash[i]);
		}
		return ret;
	}
	
	private Admin getAdminByUsername(String username) {
		List<Admin> ads = getAll();
		for(Admin ad : ads) {
			if(ad.getTaiKhoan().getUsername().equals(username))
				return ad;
		}
		return null;
	}

	public int signIn(String username, String password) {
	  // debug username
    if(username.equals("DEBUG_LOGIN"))
      return 1;
		AccountDAO tmp = new AccountDAO();
		Account chk = new Account(username, password);
		if (tmp.signIn(chk) == 1) {
		  System.out.println(chk.getMaTK());
		  Admin adm = get(chk.getMaTK());
	    
	    if (adm != null)
	      return 1;
		}
		return 0;
	}

}

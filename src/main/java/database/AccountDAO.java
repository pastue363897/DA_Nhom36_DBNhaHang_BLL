/**
 * Created on: 15:44:42 24 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entites.Account;

public class AccountDAO extends GeneralCRUD<Account>{

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
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
  
  public Account getAccountByUsername(String username) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tr = session.getTransaction();
    try {
      tr.begin();
      String sql = "select top 1 * from TaiKhoan where username = '" + username + "'";
      Account ct = session.createNativeQuery(sql, Account.class).getSingleResult();
      tr.commit();
      if (ct != null) {
        return ct;
      }
    } catch (Exception e) {
      tr.rollback();
    }
    return null;
  }

  public int signIn(Account account) {
    Account chk = getAccountByUsername(account.getUsername());
    if (chk == null)
      return 0;
    String merge = account.getPasswordHash() + chk.getSalt();
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA-256");
      byte[] res = md.digest(merge.getBytes(StandardCharsets.UTF_8));
      String candidateHash = toHexString(res);
      String realHash = chk.getPasswordHash();
      System.out.println(candidateHash);
      System.out.println(realHash);
      if(candidateHash.equalsIgnoreCase(realHash)) {
        account.setMaTK(chk.getMaTK());
        System.out.println("OK");
        return 1;
      }
      System.out.println("Not OK");
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
  public boolean updateAccount(Account account, String userNameOld) {
    MessageDigest md;
    boolean result = false;
    try {
      Account ac = getAccountByUsername(userNameOld);
      ac.setUsername(account.getUsername());
      System.out.println(ac.getSalt());
      md = MessageDigest.getInstance("SHA-256");
      String merge = account.getPasswordHash() + ac.getSalt();
      byte[] res = md.digest(merge.getBytes(StandardCharsets.UTF_8));
      String realHash = toHexString(res);
      if (!realHash.equals(ac.getPasswordHash())) {
        account = new Account(account.getUsername(), account.getPasswordHash());
        merge = account.getPasswordHash() + account.getSalt();
        System.out.println(account.getSalt());
        res = md.digest(merge.getBytes(StandardCharsets.UTF_8));
        realHash = toHexString(res);
      }
      else
        account = ac;
      account.setPasswordHash(realHash);
      Session session = sessionFactory.getCurrentSession();
      Transaction tr = session.getTransaction();
      try {
        String sql = "update Account set username = :username, passwordHash = :password, salt = :salt where username = :usernameold";
        tr.begin();
        Query query = session.createQuery(sql);
        query.setParameter("username", account.getUsername());
        query.setParameter("password", account.getPasswordHash());
        query.setParameter("salt", account.getSalt());
        query.setParameter("usernameold", userNameOld);
        if(query.executeUpdate() > 0) {
          result = true;
        }
        tr.commit();
      } catch (Exception e) {
        tr.rollback();
        e.printStackTrace();
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return result;
  }
}

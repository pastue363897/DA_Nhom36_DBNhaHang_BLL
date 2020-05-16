/**
 * Created on: 08:55:09 23 thg 4, 2020
 * 
 * @author Dinh Van Dung YKNB
 */

package test_database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import database.AccountDAO;
import database.AdminDAO;
import database.BanAnDAO;
import database.HoaDonDAO;
import database.CustomerDAO;
import database.HibernateUtil;
import database.MonAnDAO;
import database.TTBanDatDAO;
import entites.Account;
import entites.Admin;
import entites.BanAn;
import entites.HoaDon;
import entites.Customer;
import entites.MonAn;
import entites.TTBanDat;

public class TestDatabase {
  public static void main(String[] args) {
    AccountDAO accManager = new AccountDAO();
    AdminDAO adManger = new AdminDAO();
    CustomerDAO cusManger = new CustomerDAO();
    Account acc = new Account("khachhang", "pass");
    Customer cus = new Customer("customer", "123 abc", "83247341234", "09494949498", "a@g.com", acc);
    cusManger.addCustomer(cus);
    //accManager.save(acc);
    //acc = new Account("admin1", "pass");
    //accManager.save(acc);
    //Admin ad = new Admin("admin", acc);
    //adManger.save(ad);
    
    
    
    List<Account> dsAccount = accManager.getAll();
    for (Account account : dsAccount) {
      System.out.println(account);
    }
    List<Admin> dsAdmin = adManger.getAll();
    for (Admin admin : dsAdmin) {
      System.out.println(admin);
    }
    List<Customer> dsCustomer = cusManger.getAll();
    for (Customer customer : dsCustomer) {
      System.out.println(customer);
    }
    
    
    TTBanDatDAO bdMananger = new TTBanDatDAO();
    BanAnDAO baMananger = new BanAnDAO();/*
    MonAnDAO maManager = new MonAnDAO();
    List<MonAn> dsMonAn = new ArrayList<MonAn>();
    dsMonAn.add(maManager.get("MA001"));
    dsMonAn.add(maManager.get("MA002"));
    TTBanDat bd = new TTBanDat("BD001",
    		cusManger.get("KH001"),
    		new Date(2019, 10, 1), new Date(2019, 10, 3),
    		baMananger.get("BA001"), 150000L, false, false, null);
    bdMananger.save(bd);*/
    
    System.out.println(bdMananger.get("BD000001").toString());
    
    /*
    TTBanDatDAO bdMananger2 = new TTBanDatDAO();
    HoaDonDAO ctttManager = new HoaDonDAO();
    HoaDon cs =
    		new HoaDon("HD001",
    				bdMananger2.get("BD001"), 150000, 200000, 50000, new Date(2019, 11, 18));
    ctttManager.save(cs);*/
  }
}

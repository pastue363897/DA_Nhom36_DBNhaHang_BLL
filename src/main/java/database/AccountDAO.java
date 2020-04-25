/**
 * Created on: 15:44:42 24 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import entites.Account;

public class AccountDAO extends GeneralCRUD<Account>{

  public AccountDAO() {
    super(Account.class);
  }
  
}

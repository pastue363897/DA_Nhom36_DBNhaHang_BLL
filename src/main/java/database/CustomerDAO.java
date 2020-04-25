/**
 * Created on: 15:47:06 24 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import entites.Customer;

public class CustomerDAO extends GeneralCRUD<Customer>{

  public CustomerDAO() {
    super(Customer.class);
  }
  
}

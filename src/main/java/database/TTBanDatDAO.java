/**
 * Created on: 14:59:22 25 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entites.ChiTietThanhToan;
import entites.TTBanDat;

public class TTBanDatDAO extends GeneralCRUD<TTBanDat> {

	public TTBanDatDAO() {
		super(TTBanDat.class);
	}

}

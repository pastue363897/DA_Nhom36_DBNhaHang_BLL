package database;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entites.ChiTietThanhToan;

public class ChiTietThanhToanDAO  extends GeneralCRUD<ChiTietThanhToan> {

	public ChiTietThanhToanDAO() {
		super(ChiTietThanhToan.class);
	}
	
	private String getLastUsedID() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		String preparedQL = "select top 1 * from ChiTietThanhToan order by maHD desc";
		try {
			ChiTietThanhToan ct = (ChiTietThanhToan) session.createNativeQuery(preparedQL, ChiTietThanhToan.class)
					.getSingleResult();
			session.getTransaction().commit();
			return ct.getMaHD();
		} catch (NoResultException ex) {
			session.getTransaction().commit();
			return "";
		}
	}

	private String generateID() {
		String lastUsed = getLastUsedID();
		if (lastUsed.equals("")) {
			return "HD000001";
		}
		String number = lastUsed.substring(2);
		return "HD" + String.format("%06d", Integer.parseInt(number) + 1);
	}

	public String saveChiTietThanhToan(ChiTietThanhToan chiTietThanhToan) {
		chiTietThanhToan.setMaHD(generateID());
		return this.save(chiTietThanhToan);
	}
}

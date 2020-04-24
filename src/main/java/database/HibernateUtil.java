/**
 * Created on: 08:39:32 23 thg 4, 2020
 * @author Dinh Van Dung YKNB
 */

package database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
  private static final SessionFactory sessionFactory = buildSessionFactory();
  private HibernateUtil() {
    super();
  }
  private static SessionFactory buildSessionFactory() {
    ServiceRegistry service = new StandardServiceRegistryBuilder().configure().build();
    Metadata metaData = new MetadataSources(service).getMetadataBuilder().build();
    return metaData.getSessionFactoryBuilder().build();
  }
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
  public static void closeSessionFactory() {
    getSessionFactory().close();
  }
}

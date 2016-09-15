package utn.edu.avanzada2.ElectronicaDonPepe.datalayer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DataLayerImple<T> implements DataLayer<T> {

	public void guardar(T t) {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}

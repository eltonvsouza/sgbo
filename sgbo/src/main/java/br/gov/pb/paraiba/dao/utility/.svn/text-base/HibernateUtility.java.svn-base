package br.gov.pb.paraiba.dao.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtility {

	private static SessionFactory sessionFactory;
	private static Session session;

	public static Session getSession() {
		try {
			sessionFactory = new AnnotationConfiguration().configure(
					"/hibernate.cfg.xml").buildSessionFactory();
		} catch (Exception e) {
			sessionFactory = null;
			e.printStackTrace();
		}
		return session = sessionFactory.openSession();
	}
}


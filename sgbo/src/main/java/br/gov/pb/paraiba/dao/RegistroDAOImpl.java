package br.gov.pb.paraiba.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.gov.pb.paraiba.dao.utility.HibernateUtility;
import br.gov.pb.paraiba.model.Registro;


public class RegistroDAOImpl<T> extends GenericDAOImpl<T> implements RegistroDAO {
	
	
	
	public T carregar(Integer id) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		Object object = null;
		try {
			Query select = session
					.createQuery("SELECT c FROM Registro c WHERE c.id = :id");
			select.setParameter("id", id);
			object = select.uniqueResult();
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
			System.gc();
		}
		return (T) object;
	}

	@Override
	protected Class getClasseConsulta() {
		return Registro.class;
	}
}

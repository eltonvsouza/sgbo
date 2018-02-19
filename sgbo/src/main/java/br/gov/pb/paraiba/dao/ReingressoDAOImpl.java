package br.gov.pb.paraiba.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.gov.pb.paraiba.dao.utility.HibernateUtility;

public class ReingressoDAOImpl<T> extends GenericDAOImpl<T> implements ReingressoDAO{
	
	public List<T> listar() {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		List<T> objects = null;
		try {
			Query select = session.createQuery("Select o from Reingresso o");
			objects = select.list();
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
			System.gc();
		}
		return objects;
	}
	
	public T carregar(Integer id) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		Object object = null;
		try {
			Query select = session
					.createQuery("SELECT c FROM Reingresso c WHERE c.id = :id");
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
	protected Class<T> getClasseConsulta() {
		// TODO Auto-generated method stub
		return null;
	}
}

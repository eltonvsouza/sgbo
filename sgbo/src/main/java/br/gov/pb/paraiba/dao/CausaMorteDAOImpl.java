package br.gov.pb.paraiba.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.gov.pb.paraiba.dao.utility.HibernateUtility;
import br.gov.pb.paraiba.model.Causamorte;

public class CausaMorteDAOImpl<T> extends GenericDAOImpl<T> implements CausaMorteDAO {
	public Causamorte verificarCausaMorteCadastrada(Integer id) {
		
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		
		Causamorte causaMorte = null;
		
		try {
			Query select = session
					.createQuery("SELECT c FROM causamorte c WHERE c.idcausaMorte = :id");
			select.setParameter("idcausaMorte", id);
			causaMorte = (Causamorte) select.uniqueResult();
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
			System.gc();
		}
		return causaMorte;
	}
	
	public List<T> listar() {
		Session session = HibernateUtility.getSession();
		List<T> objects = null;
		try {
			Query select = session.createQuery("Select o from Causamorte o");
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
		Object object = null;
		try {
			Query select = session
					.createQuery("SELECT c FROM Causamorte c WHERE c.id = :id");
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

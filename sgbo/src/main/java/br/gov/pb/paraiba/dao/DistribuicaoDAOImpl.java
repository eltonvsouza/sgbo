package br.gov.pb.paraiba.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.gov.pb.paraiba.dao.utility.HibernateUtility;
import br.gov.pb.paraiba.model.Distribuicao;
import br.gov.pb.paraiba.model.Registro;

public class DistribuicaoDAOImpl<T> extends GenericDAOImpl<T> implements DistribuicaoDAO{

	public Distribuicao verificarDistribuicaoCadastrado(Registro registro) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		Distribuicao d = null;
		try {
			Query select = session
					.createQuery("SELECT d FROM Distribuicao  WHERE d.registro = :registro");
			select.setParameter("registro", registro);
			d = (Distribuicao) select.uniqueResult();
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
			System.gc();
		}
		return d;
	}
	
	public List<T> listar() {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		List<T> objects = null;
		try {
			Query select = session.createQuery("Select o from Distribuicao o");
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
					.createQuery("SELECT c FROM Distribuicao c WHERE c.id = :id");
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

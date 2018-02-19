package br.gov.pb.paraiba.dao;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import br.gov.pb.paraiba.dao.utility.HibernateUtility;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
	
	private SessionFactory sessionFactory;
	
	@Override
	public Collection<T> listar() throws Exception {

		Collection<T> lista = null;

		Session session = HibernateUtility.getSession();
		Query consulta = session.createQuery("from "
				+ getClasseConsulta().getName());

		lista = consulta.list();

		return lista;
	}
	
	/**
	 * Retorna a classe que será utilizada para consulta ou listagem.
	 * @return a classe que será utilizada para consulta ou listagem.
	 */
	protected abstract Class<T> getClasseConsulta();
	
	
	public void salvar(T object) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(object);
			tx.commit();
			System.out.println("Objeto Salvo"+ object.getClass().getName());
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
			System.gc();
		}
	}
	
	

	
	public void salvarOuAtualizar(T object) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(object);
			tx.commit();
		} catch (NonUniqueObjectException e) {
			session.merge(object);
		}

		// Envia as alterações do objeto para o banco e retira o objeto da
		// sessão.
		session.flush();
		session.evict(object);

	}
	
	public void excluir(T object) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(object);
			tx.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
			System.gc();
		}
		
	}
	


}

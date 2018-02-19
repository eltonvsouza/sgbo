package br.gov.pb.paraiba.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.gov.pb.paraiba.dao.utility.HibernateUtility;
import br.gov.pb.paraiba.model.Estabelecimento;

public class EstabelecimentoDAOImpl<T> extends GenericDAOImpl<T> implements EstabelecimentoDAO {
	public Estabelecimento verificarEstabelecimentoCadastrado(String endereco, String nome, String telefone) {
		
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		
		Estabelecimento e = null;
		
		try {
			Query select = session
					.createQuery("SELECT e FROM estabelecimento e WHERE e.endereco = :endereco AND e.nome= :nome AND e.telefone = :telefone");
			select.setParameter("endereco", endereco);
			select.setParameter("nome", nome);
			select.setParameter("telefone", telefone);
			e = (Estabelecimento) select.uniqueResult();
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
			System.gc();
		}
		return e;
	}
	
	public List<T> listar() {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		List<T> objects = null;
		try {
			Query select = session.createQuery("Select o from Estabelecimento o");
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
					.createQuery("SELECT c FROM Estabelecimento c WHERE c.id = :id");
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

	@SuppressWarnings("unchecked")
	@Override
	protected Class getClasseConsulta() {
		return Estabelecimento.class;
	}
	
}

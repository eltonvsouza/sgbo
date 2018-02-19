package br.gov.pb.paraiba.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.gov.pb.paraiba.dao.utility.HibernateUtility;
import br.gov.pb.paraiba.model.Medico;

public class MedicoDAOImpl<T> extends GenericDAOImpl<T> implements MedicoDAO {

	public Medico verificarMedicoCadastrado(String crm, String endereco, String especialidade, String nome, String telefone) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		Medico m = null;
		try {
			Query select = session
					.createQuery("SELECT m FROM Medico m WHERE m.crm = :crm AND m.distribuicoes = :distribuicoes AND m.endereco = :endereco AND m.especialidade = :especialidade AND m.nome = :nome AND m.telefone = :telefone ");
			select.setParameter("crm", crm);
			select.setParameter("endereco", endereco);
			select.setParameter("especialidade", especialidade);
			select.setParameter("nome", nome);
			select.setParameter("telefone", telefone);
			
			m = (Medico) select.uniqueResult();
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
			System.gc();
		}
		return m;
	}
	
	public List<T> listar() {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		List<T> objects = null;
		try {
			Query select = session.createQuery("Select o from Medico o");
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
					.createQuery("SELECT c FROM Medico c WHERE c.id = :id");
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

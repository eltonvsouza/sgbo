package br.gov.pb.paraiba.dao;

import java.util.Collection;


public abstract interface GenericDAO<T> {
	public abstract void salvar(T object);
	public abstract Collection<T> listar() throws Exception;
	public T carregar(Integer id);
	public abstract void salvarOuAtualizar(T object);
	public abstract void excluir(T object);
}

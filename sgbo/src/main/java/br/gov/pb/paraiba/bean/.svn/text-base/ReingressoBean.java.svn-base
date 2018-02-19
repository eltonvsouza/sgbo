package br.gov.pb.paraiba.bean;

import java.util.List;

import br.gov.pb.paraiba.dao.ReingressoDAOImpl;
import br.gov.pb.paraiba.model.Reingresso;

public class ReingressoBean {
	private Reingresso reingresso;
	private ReingressoDAOImpl<Reingresso> reingressoDAOImpl;
	private List<Reingresso> reingressos;
	private Integer idBusca;

	
	public Integer getIdBusca() {
		return idBusca;
	}


	public void setIdBusca(Integer idBusca) {
		this.idBusca = idBusca;
	}


	public Reingresso getReingresso() {
		return reingresso;
	}


	public void setReingresso(Reingresso reingresso) {
		this.reingresso = reingresso;
	}


	public ReingressoDAOImpl<Reingresso> getReingressoDAOImpl() {
		return reingressoDAOImpl;
	}


	public void setReingressoDAOImpl(ReingressoDAOImpl<Reingresso> reingressoDAOImpl) {
		this.reingressoDAOImpl = reingressoDAOImpl;
	}

	
	public String carregarCadastrar(){
		return "cadastrarReingresso";
	}
	
	public String carregarListar(){
		return "listarReingresso";
	}
	
	public void limpar(){
		reingresso = null;
	}
	
	public String cadastrar() {
		if((reingresso != null)){
			reingressoDAOImpl.salvar(reingresso);
			limpar();
			System.out.println("\nSalvo\n");
			return "reingressoSalvo";
		}else{
			System.out.println("\nErro ao salvar\n");			
			return "erroAoSalvar";
		}
	}
	
//	public Usuario verificaUsusario(Usuario usuario){
//		Usuario user = null;
//		user = usuarioDAOImpl.verificarUsuarioCadastrado(usuario.getNome(), usuario.getLogin());
//		return user;
//	}
	
	public List<Reingresso> getReingressos() {
		try {
			reingressos = reingressoDAOImpl.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reingressos;
	}
	
	public String carregarAlterar() {
		reingresso = reingressoDAOImpl.carregar(idBusca);
		return "alterarReingresso";
	}
	
	public String alterar() {
		limpar();
		reingresso = reingressoDAOImpl.carregar(idBusca);
		reingressoDAOImpl.salvarOuAtualizar(reingresso);
		return "reingressoAlterado";
	}
	
	public void excluir(){
		reingresso = reingressoDAOImpl.carregar(idBusca);
		reingressoDAOImpl.excluir(reingresso);
	}

}

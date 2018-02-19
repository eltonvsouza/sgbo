package br.gov.pb.paraiba.bean;

import java.util.List;

import br.gov.pb.paraiba.dao.DistribuicaoDAOImpl;
import br.gov.pb.paraiba.model.Distribuicao;

public class DistribuicaoBean {

	private Distribuicao distribuicao;
	private DistribuicaoDAOImpl<Distribuicao> distribuicaoDAOImpl;
	private List<Distribuicao> distribuicoes;
	private Integer idBusca;

	
	public Integer getIdBusca() {
		return idBusca;
	}


	public void setIdBusca(Integer idBusca) {
		this.idBusca = idBusca;
	}


	public Distribuicao getDistribuicao() {
		return distribuicao;
	}


	public void setDistribuicao(Distribuicao distribuicao) {
		this.distribuicao = distribuicao;
	}


	public DistribuicaoDAOImpl<Distribuicao> getDistribuicaoDAOImpl() {
		return distribuicaoDAOImpl;
	}


	public void setDistribuicaoDAOImpl(DistribuicaoDAOImpl<Distribuicao> distribuicaoDAOImpl) {
		this.distribuicaoDAOImpl = distribuicaoDAOImpl;
	}

	
	public String carregarCadastrar(){
		return "cadastrarDistribuicao";
	}
	
	public String carregarListar(){
		return "listarDistribuicao";
	}
	
	public void limpar(){
		distribuicao = null;
	}
	
	public String cadastrar() {
		//Distribuicao user = null;
		//user = verificaUsusario(Distribuicao);
		if((distribuicao != null)){
			distribuicaoDAOImpl.salvar(distribuicao);
			limpar();
			System.out.println("\nSalvo\n");
			return "distribuicaoSalva";
		}else{
			System.out.println("\nErro ao salvar\n");			
			return "erroAoSalvar";
		}
	}
	
//	public Distribuicao verificaUsusario(Distribuicao Distribuicao){
//		Distribuicao user = null;
//		user = DistribuicaoDAOImpl.verificarDistribuicaoCadastrado(Distribuicao.getNome(), Distribuicao.getLogin());
//		return user;
//	}
	
	public List<Distribuicao> getDistribuicoes() {
		try {
			distribuicoes = distribuicaoDAOImpl.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return distribuicoes;
	}
	
	public String carregarAlterar() {
		distribuicao = distribuicaoDAOImpl.carregar(idBusca);
		return "alterarDistribuicao";
	}
	
	public String alterar() {
		limpar();
		distribuicao = distribuicaoDAOImpl.carregar(idBusca);
		distribuicaoDAOImpl.salvarOuAtualizar(distribuicao);
		return "DistribuicaoAlterado";
	}
	
	public void excluir(){
		distribuicao = distribuicaoDAOImpl.carregar(idBusca);
		distribuicaoDAOImpl.excluir(distribuicao);
	}

}

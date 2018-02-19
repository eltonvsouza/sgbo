package br.gov.pb.paraiba.bean;

import java.util.List;

import br.gov.pb.paraiba.dao.EstabelecimentoDAOImpl;
import br.gov.pb.paraiba.model.Estabelecimento;

public class EstabelecimentoBean {
	
	private Estabelecimento estabelecimento;
	private EstabelecimentoDAOImpl<Estabelecimento> estabelecimentoDAOImpl;
	private List<Estabelecimento> estabelecimentos;
	private Integer idBusca;

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public EstabelecimentoDAOImpl<Estabelecimento> getEstabelecimentoDAOImpl() {
		return estabelecimentoDAOImpl;
	}

	public void setEstabelecimentoDAOImpl(
			EstabelecimentoDAOImpl<Estabelecimento> estabelecimentoDAOImpl) {
		this.estabelecimentoDAOImpl = estabelecimentoDAOImpl;
	}

	public Integer getIdBusca() {
		return idBusca;
	}

	public void setIdBusca(Integer idBusca) {
		this.idBusca = idBusca;
	}

	public String carregarCadastrar(){
		estabelecimento = new Estabelecimento();
		return "cadastrarEstabelecimento";
	}
	
	public String carregarListar(){
		return "listarEstabelecimento";
	}
	
	public void limpar(){
		estabelecimento = null;
	}
	
	public String cadastrar() {
		Estabelecimento e = null;
//		e = verificaEstabelecimento(estabelecimento);
		if((estabelecimento != null)){
			estabelecimentoDAOImpl.salvar(estabelecimento);
			limpar();
			System.out.println("\nSalvo\n");
			return "estabelecimentoSalvo";
		}else{
			System.out.println("\nErro ao salvar\n");			
			return "erroAoSalvar";
		}
	}
	
	public Estabelecimento verificaEstabelecimento(Estabelecimento estabelecimento){
		Estabelecimento e = null;
		e = estabelecimentoDAOImpl.verificarEstabelecimentoCadastrado(estabelecimento.getEndereco(), estabelecimento.getNome(), estabelecimento.getTelefone());
		return e;
	}
	
	public List<Estabelecimento> getEstabelecimentos() {
		try {
			estabelecimentos = estabelecimentoDAOImpl.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estabelecimentos;
	}
	
	public String carregarAlterar() {
		estabelecimento = estabelecimentoDAOImpl.carregar(idBusca);
		return "alterarEstabelecimento";
	}
	
	public String alterar() {
		estabelecimentoDAOImpl.salvarOuAtualizar(estabelecimento);
		return "estabelecimentoAlterado";
	}
	
	public void excluir(){
		estabelecimento = estabelecimentoDAOImpl.carregar(idBusca);
		estabelecimentoDAOImpl.excluir(estabelecimento);
	}

}

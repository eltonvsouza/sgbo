package br.gov.pb.paraiba.bean;

import java.util.List;

import br.gov.pb.paraiba.dao.CausaMorteDAOImpl;
import br.gov.pb.paraiba.model.Causamorte;

public class CausaMorteBean {

	private Causamorte causaMorte;
	private CausaMorteDAOImpl<Causamorte> causaMorteDAOImpl;
	private List<Causamorte> causaMortes;
	private Integer idBusca;
	
	public Integer getIdBusca() {
		return idBusca;
	}

	public void setIdBusca(Integer idBusca) {
		this.idBusca = idBusca;
	}

	public Causamorte getCausaMorte() {
		return causaMorte;
	}

	public void setCausaMorte(Causamorte causaMorte) {
		this.causaMorte = causaMorte;
	}

	public CausaMorteDAOImpl<Causamorte> getCausaMorteDAOImpl() {
		return causaMorteDAOImpl;
	}


	public void setCausaMorteDAOImpl(CausaMorteDAOImpl<Causamorte> causaMorteDAOImpl) {
		this.causaMorteDAOImpl = causaMorteDAOImpl;
	}

	public void limpar(){
		causaMorte = null;
	}
	
	public Causamorte verificaCausaMorte(Causamorte causamorte){
		Causamorte cm = null;
		cm = causaMorteDAOImpl.verificarCausaMorteCadastrada(causaMorte.getIdCausaMorte());
		return cm;
	}
	
	public List<Causamorte> getcausaMortes() {
		try {
			causaMortes = causaMorteDAOImpl.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return causaMortes;
	}
	
	public String carregarCadastrar(){
		causaMorte = new Causamorte();
		return "cadastrarCausaMorte";
	}
	
	public String carregarListar(){
		return "listarCausaMorte";
	}
	
	public String carregarAlterar() {
		causaMorte = causaMorteDAOImpl.carregar(idBusca);
		return "alterarCausaMorte";
	}
	
	public String cadastrar() {
		if((causaMorte != null)){
			causaMorteDAOImpl.salvar(causaMorte);
			limpar();
			System.out.println("\nSalvo\n");
			return "causaMorteSalva";
		}else{
			System.out.println("\nErro ao salvar\n");			
			return "erroAoSalvar";
		}
	}
	
	public String alterar() {
		causaMorteDAOImpl.salvarOuAtualizar(causaMorte);
		return "causaMorteAlterada";
	}
	
	public void excluir(){
		causaMorte = causaMorteDAOImpl.carregar(idBusca);
		causaMorteDAOImpl.excluir(causaMorte);
	}
}

package br.gov.pb.paraiba.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import br.gov.pb.paraiba.dao.CausaMorteDAOImpl;
import br.gov.pb.paraiba.dao.EstabelecimentoDAOImpl;
import br.gov.pb.paraiba.dao.RegistroDAOImpl;
import br.gov.pb.paraiba.model.Causamorte;
import br.gov.pb.paraiba.model.Estabelecimento;
import br.gov.pb.paraiba.model.Registro;
import br.gov.pb.paraiba.report.RelatorioRegistro;

public class RegistroBean {
	private Registro registro;
	private RegistroDAOImpl<Registro> registroDAOImpl;
	private CausaMorteDAOImpl<Causamorte> causaMorteDAOImpl;
	private EstabelecimentoDAOImpl<Estabelecimento> estabelecimentoDAOImpl;
	private Collection<Registro> registros;
	private Integer idBusca;
	private ArrayList<SelectItem> causaMortes;
	private ArrayList<SelectItem> estabelecimentos;


	
	
	public List<SelectItem> getCausaMortes() {
		causaMortes = new ArrayList<SelectItem>();
		List<Causamorte> causaMorte = causaMorteDAOImpl.listar();
		for (Causamorte c : causaMorte) {
			causaMortes.add(new SelectItem(c.getIdCausaMorte(), c.getDescricao()));	
		}
		return causaMortes;
	}
	
	public List<SelectItem> getEstabelecimentos() {
		estabelecimentos = new ArrayList<SelectItem>();
		List<Estabelecimento> estabelecimento = estabelecimentoDAOImpl.listar();
		for (Estabelecimento e : estabelecimento) {
			estabelecimentos.add(new SelectItem(e.getIdEstabelecimento(), e.getNome()));	
		}
		return estabelecimentos;
	}


	
	public void limpar(){
		registro = null;
		registro = new Registro();
	}
	
	public String cadastrar() {
				
		String retorno = "";		
		//if(registro != null){ errado, a entidade nunca vai estar nula, o que pode vir nulo são suas propriedades dela.
		if(ehRegistroValido(registro)){
			registroDAOImpl.salvar(registro);
			limpar();
			System.out.println("\nRegistro Salvo\n");
			retorno = "cadastrarRegistro";
		}else{
			System.out.println("\nErro ao salvar\n");			
			retorno = "erroCadastro";
		}
		
		return retorno;
	}

	
	private boolean ehRegistroValido(Registro registro){
		boolean retorno = true;
		
		if(registro.getDataCaptacao()==null){
			retorno = false;
		}
		
		if(registro.getDataEntreda()==null){
			retorno = false;
		}
		
		// e assim por diante para as propriedades que não podem ser nulas.
		
		return retorno;
	}
	
	
	
	
	public EstabelecimentoDAOImpl<Estabelecimento> getEstabelecimentoDAOImpl() {
		return estabelecimentoDAOImpl;
	}

	public void setEstabelecimentoDAOImpl(
			EstabelecimentoDAOImpl<Estabelecimento> estabelecimentoDAOImpl) {
		this.estabelecimentoDAOImpl = estabelecimentoDAOImpl;
	}

	public CausaMorteDAOImpl<Causamorte> getCausaMorteDAOImpl() {
		return causaMorteDAOImpl;
	}

	public void setCausaMorteDAOImpl(CausaMorteDAOImpl<Causamorte> causaMorteDAOImpl) {
		this.causaMorteDAOImpl = causaMorteDAOImpl;
	}


	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public RegistroDAOImpl<Registro> getRegistroDAOImpl() {
		return registroDAOImpl;
	}

	public void setRegistroDAOImpl(RegistroDAOImpl<Registro> registroDAOImpl) {
		this.registroDAOImpl = registroDAOImpl;
	}
	
	public String carregarCadastrar(){
		registro = new Registro();
		return "cadastrarRegistro";
	}
	
	public String carregarListar(){
		return "listarRegistro";
	}
	
	
	public Collection<Registro> getRegistros() {
		try {
			registros = registroDAOImpl.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registros;
	}
	
	public String carregarAlterar() {
		registro = registroDAOImpl.carregar(idBusca);
		return "alterarRegistro";
	}
	
	public String alterar() {
		registroDAOImpl.salvarOuAtualizar(registro);
		return "registroAlterado";
	}
	
	public void excluir(){
		registro = registroDAOImpl.carregar(idBusca);
		registroDAOImpl.excluir(registro);
	}
	
	public void relatorioGeral() {
		RelatorioRegistro relatorioRegistro = new RelatorioRegistro();
		try {
			relatorioRegistro.gerarRelatorio();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

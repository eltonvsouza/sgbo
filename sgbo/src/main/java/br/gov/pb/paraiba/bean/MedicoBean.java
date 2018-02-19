package br.gov.pb.paraiba.bean;

import java.util.List;

import br.gov.pb.paraiba.dao.MedicoDAOImpl;
import br.gov.pb.paraiba.model.Medico;

public class MedicoBean {
	private Medico medico;
	private MedicoDAOImpl<Medico> medicoDAOImpl;
	private List<Medico> medicos;
	private Integer idBusca;

	public Integer getIdBusca() {
		return idBusca;
	}

	public void setIdBusca(Integer idBusca) {
		this.idBusca = idBusca;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public MedicoDAOImpl<Medico> getMedicoDAOImpl() {
		return medicoDAOImpl;
	}

	public void setMedicoDAOImpl(MedicoDAOImpl<Medico> medicoDAOImpl) {
		this.medicoDAOImpl = medicoDAOImpl;
	}

	
	public String carregarCadastrar(){
		medico = new Medico();
		return "cadastrarMedico";
	}
	
	public String carregarListar(){
		return "listarMedico";
	}
	
	public void limpar(){
		medico = null;
	}
	
	public String cadastrar() {
		Medico m = null;
//		m = verificaMedico(medico);
		if((medico != null)){
			medicoDAOImpl.salvar(medico);
			limpar();
			System.out.println("\nSalvo\n");
			return "medicoSalvo";
		}else{
			System.out.println("\nErro ao salvar\n");			
			return "erroAoSalvar";
		}
	}
	
	public Medico verificaMedico(Medico medico){
		Medico m = null;
		m = medicoDAOImpl.verificarMedicoCadastrado(medico.getCrm(), medico.getEndereco(), medico.getEspecialidade(), medico.getNome(), medico.getTelefone());
		return m;
	}
	
	public List<Medico> getMedicos() {
		try {
			medicos = medicoDAOImpl.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicos;
	}
	
	public String carregarAlterar() {
		medico = medicoDAOImpl.carregar(idBusca);
		return "alterarMedico";
	}
	
	public String alterar() {
		medicoDAOImpl.salvarOuAtualizar(medico);
		return "medicoAlterado";
	}
	
	public void excluir(){
		medico = medicoDAOImpl.carregar(idBusca);
		medicoDAOImpl.excluir(medico);
	}
}

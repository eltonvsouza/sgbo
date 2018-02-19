package br.gov.pb.paraiba.bean;

import java.util.List;

import br.gov.pb.paraiba.dao.UsuarioDAOImpl;
import br.gov.pb.paraiba.model.Usuario;
import br.gov.pb.paraiba.report.RelatorioUsuario;

public class UsuarioBean {

	private Usuario usuario;
	private UsuarioDAOImpl<Usuario> usuarioDAOImpl;
	private List<Usuario> usuarios;
	private Integer idBusca;

	public Integer getIdBusca() {
		return idBusca;
	}


	public void setIdBusca(Integer idBusca) {
		this.idBusca = idBusca;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public UsuarioDAOImpl<Usuario> getUsuarioDAOImpl() {
		return usuarioDAOImpl;
	}


	public void setUsuarioDAOImpl(UsuarioDAOImpl<Usuario> usuarioDAOImpl) {
		this.usuarioDAOImpl = usuarioDAOImpl;
	}

	
	public String carregarCadastrar(){
		usuario = new Usuario();
		return "cadastrarUsuario";
	}
	
	public String carregarListar(){
		return "listarUsuario";
	}
	
	public void limpar(){
		usuario = null;
	}
	
	public String cadastrar() {
		//Usuario user = null;
		//user = verificaUsusario(usuario);
		if((usuario != null)){
			usuarioDAOImpl.salvar(usuario);
			limpar();
			System.out.println("\nSalvo\n");
			return "usuarioSalvo";
		}else{
			System.out.println("\nErro ao salvar\n");			
			return "erroAoSalvar";
		}
	}
	
	public Usuario verificaUsusario(Usuario usuario){
		Usuario user = null;
		user = usuarioDAOImpl.verificarUsuarioCadastrado(usuario.getNome(), usuario.getLogin());
		return user;
	}
	
	public List<Usuario> getUsuarios() {
		try {
			usuarios = usuarioDAOImpl.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public String carregarAlterar() {
		usuario = usuarioDAOImpl.carregar(idBusca);
		return "alterarUsuario";
	}
	
	public String alterar() {
		System.out.println("Saida: "+ usuario.getNome());
		usuarioDAOImpl.salvarOuAtualizar(usuario);
		return "usuarioAlterado";
	}
	
	public void excluir(){
		usuario = usuarioDAOImpl.carregar(idBusca);
		usuarioDAOImpl.excluir(usuario);
	}
	
	public void relatorioGeral() {
		RelatorioUsuario relatorioUsuario = new RelatorioUsuario();
		try {
			relatorioUsuario.gerarRelatorio();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

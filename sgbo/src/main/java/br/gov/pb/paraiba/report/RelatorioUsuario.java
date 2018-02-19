package br.gov.pb.paraiba.report;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.gov.pb.paraiba.dao.UsuarioDAOImpl;
import br.gov.pb.paraiba.report.connection.Conexao;

public class RelatorioUsuario {
	private UsuarioDAOImpl usuarioDAOImpl;
	private String saida;
	Conexao conexao;

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public UsuarioDAOImpl getUsuarioDAOImpl() {
		return usuarioDAOImpl;
	}

	public void setUsuarioDAOImpl(UsuarioDAOImpl usuarioDAOImpl) {
		this.usuarioDAOImpl = usuarioDAOImpl;
	}

	public void gerarRelatorio() {
		saida = "";
		String jasper = "/br/gov/pb/paraiba/report/jasper/relatorioUsuario.jasper";
		try {
			conexao = new Conexao();
			JRResultSetDataSource jrsds = new JRResultSetDataSource(getResultSet(conexao.getConexao()));
			InputStream is = getClass().getResourceAsStream(jasper);
			JasperPrint print = JasperFillManager.fillReport(is, null,jrsds);
			exibePdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conexao != null)
					conexao.fechaConexao();
			} catch (SQLException e) {
			}
		}
	}

	private String getDiretorioReal(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

	private String getContextPath() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getContextPath();
	}

	private void exibePdf(JasperPrint print) throws JRException, IOException {
		byte[] bytes = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext
				.getExternalContext().getContext();
		HttpServletResponse response = (HttpServletResponse) FacesContext
		.getCurrentInstance().getExternalContext().getResponse();
		
		bytes = JasperExportManager.exportReportToPdf(print);
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		servletOutputStream.write(bytes, 0, bytes.length);
		servletOutputStream.flush();
		servletOutputStream.close();
		facesContext.responseComplete();
	}

	private ResultSet getResultSet(Connection conexao) throws SQLException,
			ClassNotFoundException {
		Statement stmt = conexao.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT "
				+ "usuario.nome AS usuario_nome, "
				+ "usuario.login AS usuario_login, "
				+ "usuario.funcao AS usuario_funcao, "
				+ "usuario.idUsuario AS usuario_idUsuario " 
				+ "FROM "
				+ "usuario usuario");
		
		return rs;
	}

}

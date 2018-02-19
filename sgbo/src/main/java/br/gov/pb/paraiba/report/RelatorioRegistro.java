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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.gov.pb.paraiba.dao.RegistroDAOImpl;
import br.gov.pb.paraiba.model.Registro;
import br.gov.pb.paraiba.report.connection.Conexao;

public class RelatorioRegistro {
	private RegistroDAOImpl<Registro> registroDAOImpl;
	private String saida;
	Conexao conexao;

	public RegistroDAOImpl<Registro> getRegistroDAOImpl() {
		return registroDAOImpl;
	}

	public void setRegistroDAOImpl(RegistroDAOImpl<Registro> registroDAOImpl) {
		this.registroDAOImpl = registroDAOImpl;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public void gerarRelatorio() {
		saida = "";
		String jasper = "/br/gov/pb/paraiba/report/jasper/relatorioRegistro.jasper";
		try {
			conexao = new Conexao();
			JRResultSetDataSource jrsds = new JRResultSetDataSource(
					getResultSet(conexao.getConexao()));
			InputStream is = getClass().getResourceAsStream(jasper);
			JasperPrint print = JasperFillManager.fillReport(is, null, jrsds);
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
				+ "registro.numero AS registro_numero, "
				+ "registro.nomeDoador AS registro_nomeDoador, "
				+ "registro.causaMorteDoador AS registro_causaMorteDoador, "
				+ "registro.dataObito AS registro_dataObito, "
				+ "registro.sorologia AS registro_sorologia, "
				+ "registro.preservada AS registro_preservada, "
				+ "registro.validade AS registro_validade " + "FROM "
				+ "registro registro");

		return rs;
	}
}

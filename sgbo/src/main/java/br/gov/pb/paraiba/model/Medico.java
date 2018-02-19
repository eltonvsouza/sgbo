package br.gov.pb.paraiba.model;

// default package
// Generated 19/11/2009 16:03:54 by Hibernate Tools 3.2.4.GA

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Medico generated by hbm2java
 */
@Entity
@Table(name = "medico", catalog = "sgbo")
public class Medico implements java.io.Serializable {

	private Integer idMedico;
	private String nome;
	private String endereco;
	private String telefone;
	private String crm;
	private String especialidade;
	private Set<Distribuicao> distribuicaos = new HashSet<Distribuicao>(0);

	public Medico() {
	}

	public Medico(String nome, String crm) {
		this.nome = nome;
		this.crm = crm;
	}

	public Medico(String nome, String endereco, String telefone, String crm,
			String especialidade, Set<Distribuicao> distribuicaos) {
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.crm = crm;
		this.especialidade = especialidade;
		this.distribuicaos = distribuicaos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idMedico", unique = true, nullable = false)
	public Integer getIdMedico() {
		return this.idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	@Column(name = "nome", nullable = false, length = 50)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "endereco", length = 100)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "telefone", length = 9)
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "crm", nullable = false, length = 10)
	public String getCrm() {
		return this.crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	@Column(name = "especialidade", length = 45)
	public String getEspecialidade() {
		return this.especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medico")
	public Set<Distribuicao> getDistribuicaos() {
		return this.distribuicaos;
	}

	public void setDistribuicaos(Set<Distribuicao> distribuicaos) {
		this.distribuicaos = distribuicaos;
	}

}

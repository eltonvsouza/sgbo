package br.gov.pb.paraiba.model;

// default package
// Generated 17/11/2009 11:35:41 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Causamorte generated by hbm2java
 */
@Entity
@Table(name = "causamorte", catalog = "sgbo")
public class Causamorte implements java.io.Serializable {

	private Integer idCausaMorte;
	private String descricao;
	private Set<Registro> registros = new HashSet<Registro>(0);

	public Causamorte() {
	}

	public Causamorte(String descricao) {
		this.descricao = descricao;
	}

	public Causamorte(String descricao, Set<Registro> registros) {
		this.descricao = descricao;
		this.registros = registros;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idcausaMorte", unique = true, nullable = false)
	public Integer getIdCausaMorte() {
		return this.idCausaMorte;
	}

	public void setIdCausaMorte(Integer idCausaMorte) {
		this.idCausaMorte = idCausaMorte;
	}

	@Column(name = "descricao", nullable = false, length = 50)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
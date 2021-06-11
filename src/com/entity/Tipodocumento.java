package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipodocumento database table.
 * 
 */
@Entity
@NamedQuery(name="Tipodocumento.findAll", query="SELECT t FROM Tipodocumento t")
public class Tipodocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column
	private String desripcion;

	//bi-directional many-to-one association to Votante
	@OneToMany(mappedBy="tipodocumentoBean")
	private List<Votante> votantes;

	public Tipodocumento() {
	}

	public Tipodocumento(int id) {
		super();
		this.id = id;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesripcion() {
		return this.desripcion;
	}

	public void setDesripcion(String desripcion) {
		this.desripcion = desripcion;
	}

	public List<Votante> getVotantes() {
		return this.votantes;
	}

	public void setVotantes(List<Votante> votantes) {
		this.votantes = votantes;
	}

	public Votante addVotante(Votante votante) {
		getVotantes().add(votante);
		votante.setTipodocumentoBean(this);

		return votante;
	}

	public Votante removeVotante(Votante votante) {
		getVotantes().remove(votante);
		votante.setTipodocumentoBean(null);

		return votante;
	}

}
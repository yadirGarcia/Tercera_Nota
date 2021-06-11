package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="Candidato.findAll", query="SELECT c FROM Candidato c")
public class Candidato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String apellido;

	@Column
	private String documento;

	private String nombre;

	private int numero;

	@ManyToOne
	@JoinColumn(name="eleccion")
	private Eleccion eleccionBean;

	@OneToMany(mappedBy="candidatoBean")
	private List<Voto> votos;

	public Candidato() {
	}
	
	public Candidato(int id, String apellido, String documento, String nombre, int numero, Eleccion eleccionBean) {
		this.id = id;
		this.apellido = apellido;
		this.documento = documento;
		this.nombre = nombre;
		this.numero = numero;
		this.eleccionBean = eleccionBean;
	}
	
	public Candidato(String apellido, String documento, String nombre, int numero, Eleccion eleccionBean) {
		this.apellido = apellido;
		this.documento = documento;
		this.nombre = nombre;
		this.numero = numero;
		this.eleccionBean = eleccionBean;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Eleccion getEleccionBean() {
		return this.eleccionBean;
	}

	public void setEleccionBean(Eleccion eleccionBean) {
		this.eleccionBean = eleccionBean;
	}

	public List<Voto> getVotos() {
		return this.votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public Voto addVoto(Voto voto) {
		getVotos().add(voto);
		voto.setCandidatoBean(this);

		return voto;
	}

	public Voto removeVoto(Voto voto) {
		getVotos().remove(voto);
		voto.setCandidatoBean(null);

		return voto;
	}

}
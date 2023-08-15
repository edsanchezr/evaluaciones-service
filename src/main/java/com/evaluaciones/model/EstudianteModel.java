package com.evaluaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estudiante")
public class EstudianteModel implements Serializable {

	private static final long serialVersionUID = 6520394591551867069L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_estudiante")
	private Long idEstudiante;
	@Column (name = "id_zona_horaria")
	private int idZonaHoraria;
	
	private String nombre;
	@Column (name = "ap_paterno")
	private String apellidoPaterno;
	@Column (name = "ap_materno")
	private String apellidoMaterno;
	private boolean estatus;
	
	public Long getIdEstudiante() {
		return idEstudiante;
	}
	public void setIdEstudiante(Long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}
	public int getIdZonaHoraria() {
		return idZonaHoraria;
	}
	public void setIdZonaHoraria(int idZonaHoraria) {
		this.idZonaHoraria = idZonaHoraria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
}

package com.evaluaciones.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="calendario_examenes")
public class CalendarioExamenModel implements Serializable {
	
	private static final long serialVersionUID = 1502840849872045505L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_calendario")
	private Long id;
	
	@Column (name = "id_examen")
	private Long examen;
	
	@Column (name = "id_estudiante")
	private Long estudiante;
	
	@Column (name = "fecha_examen")
	private Date fechaExamen;
	
	@Column (name = "fecha_registro")
	private Date registro;
	
	private boolean aprobado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getExamen() {
		return examen;
	}
	public void setExamen(Long examen) {
		this.examen = examen;
	}
	public Long getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Long estudiante) {
		this.estudiante = estudiante;
	}
	public Date getFechaExamen() {
		return fechaExamen;
	}
	public void setFechaExamen(Date fechaExamen) {
		this.fechaExamen = fechaExamen;
	}
	public Date getRegistro() {
		return registro;
	}
	public void setRegistro(Date registro) {
		this.registro = registro;
	}
	public boolean isAprobado() {
		return aprobado;
	}
	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}
}

package com.evaluaciones.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="examen_preguntas")
public class PreguntasExamenModel implements Serializable {
	
	private static final long serialVersionUID = -4940234618642447775L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_pregunta")
	private Long id;
	
	@Column (name = "id_examen")
	private Long examen;
	
	@Column (name = "definicion_pregunta")
	private String pregunta;
	
	@Column (name = "respuesta_pregunta")
	private String respuesta;
	
	@Column (name = "puntuacion_examen")
	private BigDecimal puntuacion;
	
	@Column (name = "estatus_pregunta")
	private boolean estatus;
	
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
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public BigDecimal getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(BigDecimal puntuacion) {
		this.puntuacion = puntuacion;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
}

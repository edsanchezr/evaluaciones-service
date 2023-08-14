package com.evaluaciones.ws.request;

import java.math.BigDecimal;

public class PreguntaExamenRequest {
	
	private Long id;
	private Long examen;
	private String pregunta;
	private String respuesta;
	private BigDecimal puntuacion;
	private boolean estatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public Long getExamen() {
		return examen;
	}
	public void setExamen(Long examen) {
		this.examen = examen;
	}
	public BigDecimal getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(BigDecimal puntuacion) {
		this.puntuacion = puntuacion;
	}
}

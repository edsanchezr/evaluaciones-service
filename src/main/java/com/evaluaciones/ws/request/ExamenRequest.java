package com.evaluaciones.ws.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExamenRequest {
	
	private Long id;
	
	@JsonIgnore
	private String version;
	
	@JsonIgnore
	private Integer nivel;
	
	@JsonIgnore
	private boolean estatus;
	
	@JsonIgnore
	private List <PreguntaExamenRequest> preguntas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public List<PreguntaExamenRequest> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<PreguntaExamenRequest> preguntas) {
		this.preguntas = preguntas;
	}	
}

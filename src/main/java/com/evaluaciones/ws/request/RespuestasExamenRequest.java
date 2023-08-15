package com.evaluaciones.ws.request;

import java.util.List;

public class RespuestasExamenRequest {

	private Long id;
	private Long calendario;
	private Long estudiante;
	private List<RespuestaExamenRequest> respuestas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCalendario() {
		return calendario;
	}
	public void setCalendario(Long calendario) {
		this.calendario = calendario;
	}
	public Long getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Long estudiante) {
		this.estudiante = estudiante;
	}
	public List<RespuestaExamenRequest> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(List<RespuestaExamenRequest> respuestas) {
		this.respuestas = respuestas;
	}
	
}

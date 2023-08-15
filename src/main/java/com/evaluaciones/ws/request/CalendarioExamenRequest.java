package com.evaluaciones.ws.request;

import java.util.Date;

public class CalendarioExamenRequest {
	
	private Long id;
	private Long examen;
	private Long estudiante;
	private String fechaExamen;
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
	public String getFechaExamen() {
		return fechaExamen;
	}
	public void setFechaExamen(String fechaExamen) {
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

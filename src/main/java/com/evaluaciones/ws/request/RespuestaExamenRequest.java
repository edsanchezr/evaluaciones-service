package com.evaluaciones.ws.request;

import java.math.BigDecimal;
import java.util.Date;

public class RespuestaExamenRequest {
	
	private Long pregunta;
	private String respuesta;
	private String correcta;
	private BigDecimal puntuacion;
	private Date registro;
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getCorrecta() {
		return correcta;
	}
	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}
	public BigDecimal getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(BigDecimal puntuacion) {
		this.puntuacion = puntuacion;
	}
	public Date getRegistro() {
		return registro;
	}
	public void setRegistro(Date registro) {
		this.registro = registro;
	}
	public Long getPregunta() {
		return pregunta;
	}
	public void setPregunta(Long pregunta) {
		this.pregunta = pregunta;
	}
}

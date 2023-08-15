package com.evaluaciones.ws.response;

import java.math.BigDecimal;

public class EvaluacionResponse extends MetadataResponse {
	
	private BigDecimal puntuacion;

	public BigDecimal getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(BigDecimal puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}

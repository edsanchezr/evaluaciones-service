package com.evaluaciones.ws.response;

import com.evaluaciones.ws.request.ExamenRequest;

public class ExamenResponse extends MetadataResponse {
	
	private ExamenRequest examen = new ExamenRequest();

	public ExamenRequest getExamen() {
		return examen;
	}

	public void setExamen(ExamenRequest examen) {
		this.examen = examen;
	}
}

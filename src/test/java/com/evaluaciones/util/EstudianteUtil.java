package com.evaluaciones.util;

import java.util.Random;

import com.evaluaciones.model.EstudianteModel;
import com.evaluaciones.ws.request.EstudianteRequest;

public class EstudianteUtil {
	
	public static EstudianteRequest getRequest () {
		EstudianteRequest request = new EstudianteRequest();
		request.setNombre("Ernesto");
		request.setApellidoPaterno("Gonzalez");
		request.setEstatus(true);
		request.setZonaHoraria("1");
		return request;
	}
	
	public static EstudianteModel getModel () {
		EstudianteModel model = new EstudianteModel();
		model.setNombre("Ernesto");
		model.setApellidoPaterno("Gonzalez");
		model.setEstatus(true);
		model.setIdZonaHoraria(1);
		model.setIdEstudiante(new Random().nextLong() + Math.abs(-1));
		return model;
	}
}

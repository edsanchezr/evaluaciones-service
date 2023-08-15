package com.evaluaciones.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.evaluaciones.model.CalendarioExamenModel;
import com.evaluaciones.model.EstudianteModel;
import com.evaluaciones.model.RespuestasExamenModel;
import com.evaluaciones.ws.request.RespuestaExamenRequest;
import com.evaluaciones.ws.request.RespuestasExamenRequest;

public class ManualMapper {

	public static List<RespuestasExamenModel> respuestasRequestToRespuestasModel 
			(RespuestasExamenRequest request, EstudianteModel estudianteModel,
					CalendarioExamenModel calendarioModel) {
		List<RespuestasExamenModel> respuestas = new ArrayList<>();
		for (RespuestaExamenRequest req: request.getRespuestas()) {
			RespuestasExamenModel model = new RespuestasExamenModel();
			model.setCalendario(calendarioModel);
			model.setEstudiante(estudianteModel);
			model.setPuntuacion(req.getPuntuacion());
			model.setCorrecta(req.getCorrecta());
			model.setRespuesta(req.getRespuesta());
			model.setRegistro(new Date());
			respuestas.add(model);
		}
		return respuestas;
	}
	
}

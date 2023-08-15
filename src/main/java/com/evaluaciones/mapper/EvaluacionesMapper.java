package com.evaluaciones.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.evaluaciones.model.CalendarioExamenModel;
import com.evaluaciones.model.EstudianteModel;
import com.evaluaciones.model.ExamenModel;
import com.evaluaciones.model.PreguntasExamenModel;
import com.evaluaciones.util.MapperUtil;
import com.evaluaciones.ws.request.CalendarioExamenRequest;
import com.evaluaciones.ws.request.EstudianteRequest;
import com.evaluaciones.ws.request.ExamenRequest;
import com.evaluaciones.ws.request.PreguntaExamenRequest;

@Component
@Mapper (componentModel = "spring", uses = MapperUtil.class)
public interface EvaluacionesMapper {

	@Mapping (source = "zonaHoraria", target = "idZonaHoraria")
	EstudianteModel estudianteRequestToEstudianteModel (EstudianteRequest request);
	
	@Mapping (source = "id", target = "idExamen")
	@Mapping (source = "version", target = "versionExamen")
	@Mapping (source = "nivel", target = "nivelExamen")
	@Mapping (source = "estatus", target = "estatusExamen")
	ExamenModel examenRequestToExamenModel (ExamenRequest request);
	
	PreguntasExamenModel preguntasExamenRequestToPreguntasExamenModel (PreguntaExamenRequest request);
	
	@Mapping (target = "id", source = "idExamen")
	@Mapping (target = "version", source = "versionExamen")
	@Mapping (target = "nivel", source = "nivelExamen")
	@Mapping (target = "estatus", source = "estatusExamen")
	ExamenRequest examenModelToExamenRequest (ExamenModel model);
	
	PreguntaExamenRequest preguntasExamenModelToPreguntasExamenRequest (PreguntasExamenModel model);
	
	@Mapping (source = "fechaExamen", target = "fechaExamen", 
			qualifiedByName = "convertStringToDate")
	CalendarioExamenModel calendarioRequestToCalendarioModel (CalendarioExamenRequest request);
}

package com.evaluaciones.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evaluaciones.common.enums.ErroresEnum;
import com.evaluaciones.common.exception.TransactionDataException;
import com.evaluaciones.dao.CalendarioExamenDao;
import com.evaluaciones.mapper.EvaluacionesMapper;
import com.evaluaciones.model.CalendarioExamenModel;
import com.evaluaciones.model.EstudianteModel;
import com.evaluaciones.model.ExamenModel;
import com.evaluaciones.ws.request.CalendarioExamenRequest;
import com.evaluaciones.ws.response.CalendarioResponse;

@Service
public class CalendarioService {

	@Autowired
	private CalendarioExamenDao calendarioExamenDao;
	
	@Autowired
	private EstudianteService estudianteService;
	
	@Autowired
	private ExamenService examenService;
	
	@Autowired
	private EvaluacionesMapper mapper;
	
	public ResponseEntity <CalendarioResponse> agendarExamen (CalendarioExamenRequest request) {
		CalendarioResponse response = new CalendarioResponse();
		
		Optional <ExamenModel> examen = examenService.getExamenById(request.getExamen());
		if (!examen.isPresent()) {
			response.setMetadata("ERROR", ErroresEnum.DOSENT_EXIST_EXAMEN.getCode(), 
					"No existe el examen");
			return new ResponseEntity <CalendarioResponse> (response, HttpStatus.NOT_FOUND);
		}
		
		Optional <EstudianteModel> estudiante = 
				estudianteService.getEstudianteById(request.getEstudiante());
		if (!estudiante.isPresent()) {
			response.setMetadata("ERROR", ErroresEnum.DOSENT_EXIST_ESTUDIANTE.getCode(), 
					"No existe el examen");
			return new ResponseEntity <CalendarioResponse> (response, HttpStatus.NOT_FOUND);
		}
		
		CalendarioExamenModel examenModel = mapper.calendarioRequestToCalendarioModel(request);
		examenModel.setRegistro(new Date());
		examenModel = saveCalendarioExamen (examenModel);
		response.setId(examenModel.getId());
		response.setMetadata("Ok", "200", "Examen Programado");
		return new ResponseEntity <CalendarioResponse> (response, HttpStatus.OK);
	}
	
	@Transactional
	public CalendarioExamenModel saveCalendarioExamen(CalendarioExamenModel examenModel) {
		try {
			return calendarioExamenDao.save(examenModel);
		} catch (Exception e) {
			throw new 
			TransactionDataException(ErroresEnum.INSERT_ERROR.getCode(), 
					"Error al insertar la programacion del examen", "SAVE-DATA");
		}
	}

	@Transactional(readOnly = true)
	public Optional <CalendarioExamenModel> getCalendarioById (Long id) {
		try {
			return calendarioExamenDao.findById(id);
		} catch (Exception e) {
			throw new 
			TransactionDataException(ErroresEnum.SELECT_ERROR.getCode(), 
					"Error al consultar la existencia del calendario", "GET-DATA");
		}
	}
}

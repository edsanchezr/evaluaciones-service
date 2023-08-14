package com.evaluaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evaluaciones.common.enums.ErroresEnum;
import com.evaluaciones.common.exception.TransactionDataException;
import com.evaluaciones.dao.EstudianteDao;
import com.evaluaciones.mapper.EvaluacionesMapper;
import com.evaluaciones.model.EstudianteModel;
import com.evaluaciones.ws.request.EstudianteRequest;
import com.evaluaciones.ws.response.EstudianteResponse;

@Service
public class EstudianteService {
	
	@Autowired
	private EstudianteDao estudianteDao;
	
	@Autowired
	private EvaluacionesMapper mapper;
	
	public ResponseEntity<EstudianteResponse> saveEstudiante (EstudianteRequest request) {
		EstudianteResponse response = new EstudianteResponse();
		
			List <EstudianteModel> consulta = getEstudianteByName(request.getNombre(), 
							request.getApellidoPaterno(), request.getApellidoMaterno());
			
		if (consulta == null || consulta.size() == 0) {
			EstudianteModel estudianteModel = mapper.estudianteRequestToEstudianteModel(request);
			EstudianteModel estudianteSaved = persistEstudiante(estudianteModel);
			if (estudianteSaved.getIdEstudiante() != null 
					&& estudianteSaved.getIdEstudiante() > 0) {
				response.setId(estudianteSaved.getIdEstudiante());
				response.setMetadata("Ok", "200", "Estudiante Almacenado");
				return new ResponseEntity<EstudianteResponse>(response, HttpStatus.OK);
			} else {
				response.setMetadata("Error", ErroresEnum.ID_NOT_GENERATE.getCode(), 
						"Estudiante No Almacenado");
				return new ResponseEntity<EstudianteResponse>(response, HttpStatus.FORBIDDEN);
			}
		} else {
			response.setMetadata("Error", ErroresEnum.EXIST_ESTUDENT.getCode(), 
					"Estudiante Ya Existe");
			response.setId(consulta.get(0).getIdEstudiante());
			return new ResponseEntity<EstudianteResponse>(response, HttpStatus.FORBIDDEN);
		}
	}
	
	@Transactional(readOnly = true)
	public List <EstudianteModel> getEstudianteByName (
			String nombre, String apPaterno, String apMaterno) {
		try {
			return estudianteDao
					.findByNombreAndApellidoPaternoAndApellidoMaterno(nombre, 
							apPaterno, apMaterno);
		} catch (Exception e) {
			e.printStackTrace();
			throw new 
			TransactionDataException(ErroresEnum.SELECT_ERROR.getCode(), 
					"Error al consultar la existencia del estudiante", "GET-DATA");
		}
	}
	
	@Transactional
	public EstudianteModel persistEstudiante (EstudianteModel model) {
		try {
			return estudianteDao.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new 
			TransactionDataException(ErroresEnum.INSERT_ERROR.getCode(), 
					"Error al almacenar la existencia del estudiante", "SAVE-DATA");
		}
	}
}

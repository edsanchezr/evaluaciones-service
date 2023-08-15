package com.evaluaciones.rs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.evaluaciones.dao.EstudianteDao;
import com.evaluaciones.mapper.EvaluacionesMapper;
import com.evaluaciones.model.EstudianteModel;
import com.evaluaciones.service.CalendarioService;
import com.evaluaciones.service.EstudianteService;
import com.evaluaciones.service.ExamenService;
import com.evaluaciones.util.EstudianteUtil;
import com.evaluaciones.util.MapperUtil;
import com.evaluaciones.ws.controller.EvaluacionesController;
import com.evaluaciones.ws.request.EstudianteRequest;
import com.evaluaciones.ws.response.EstudianteResponse;

@SpringBootTest
@ContextConfiguration (classes = {EvaluacionesController.class, 
		EstudianteService.class, EvaluacionesMapper.class, MapperUtil.class})
public class EvaluacionesControllerTest {

	@Autowired
	private EvaluacionesController evaluacionesController;
	
	@MockBean
	private EstudianteDao estudianteDao;
	
	@MockBean
	private EvaluacionesMapper evaluacionesMapper;
	
	@MockBean
	private ExamenService examenService;
	
	@MockBean
	private CalendarioService calendarioService;
	
	@Test
	public void saveEstudiante () {
		
		EstudianteRequest estudianteRequest = EstudianteUtil.getRequest();
		
		List<EstudianteModel> lstEstudiante = null;
		
		when(estudianteDao
				.findByNombreAndApellidoPaternoAndApellidoMaterno 
				(anyString(), anyString (), anyString()))
			.thenReturn(lstEstudiante);
		
		when(estudianteDao
				.save(any(EstudianteModel.class)))
			.thenReturn(EstudianteUtil.getModel());
		
		when (evaluacionesMapper.estudianteRequestToEstudianteModel(any (EstudianteRequest.class)))
			.thenReturn(EstudianteUtil.getModel());
		
		ResponseEntity<EstudianteResponse> response = evaluacionesController
				.saveEstudiante(estudianteRequest);
		
		assertEquals (HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void saveEstudianteListEstudianteModelNotNull () {
		
		EstudianteRequest estudianteRequest = EstudianteUtil.getRequest();
		
		List<EstudianteModel> lstEstudiante = new ArrayList<>();
		when(estudianteDao
				.findByNombreAndApellidoPaternoAndApellidoMaterno 
				(anyString(), anyString (), anyString()))
			.thenReturn(lstEstudiante);
		
		when(estudianteDao
				.save(any(EstudianteModel.class)))
			.thenReturn(EstudianteUtil.getModel());
		
		when (evaluacionesMapper.estudianteRequestToEstudianteModel(any (EstudianteRequest.class)))
			.thenReturn(EstudianteUtil.getModel());
		
		ResponseEntity<EstudianteResponse> response = evaluacionesController
				.saveEstudiante(estudianteRequest);
		
		assertEquals (HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void saveEstudianteAndEstudianteModelNull () {
		
		EstudianteRequest estudianteRequest = EstudianteUtil.getRequest();
		
		List<EstudianteModel> lstEstudiante = new ArrayList<>();
		EstudianteModel estudianteModel = EstudianteUtil.getModel();
		when(estudianteDao
				.findByNombreAndApellidoPaternoAndApellidoMaterno 
				(anyString(), anyString (), anyString()))
			.thenReturn(lstEstudiante);
		
		when (evaluacionesMapper.estudianteRequestToEstudianteModel(any (EstudianteRequest.class)))
			.thenReturn(estudianteModel);
		estudianteModel.setIdEstudiante(null);
		when(estudianteDao
				.save(any(EstudianteModel.class)))
			.thenReturn(estudianteModel);
		
		ResponseEntity<EstudianteResponse> response = evaluacionesController
				.saveEstudiante(estudianteRequest);
		
		assertEquals (HttpStatus.FORBIDDEN, response.getStatusCode());
	}
	
	@Test
	public void saveEstudianteAndEstudianteModelIdZero () {
		
		EstudianteRequest estudianteRequest = EstudianteUtil.getRequest();
		
		List<EstudianteModel> lstEstudiante = new ArrayList<>();
		EstudianteModel estudianteModel = EstudianteUtil.getModel();
		when(estudianteDao
				.findByNombreAndApellidoPaternoAndApellidoMaterno 
				(anyString(), anyString (), anyString()))
			.thenReturn(lstEstudiante);
		
		when (evaluacionesMapper.estudianteRequestToEstudianteModel(any (EstudianteRequest.class)))
			.thenReturn(estudianteModel);
		estudianteModel.setIdEstudiante(0L);
		when(estudianteDao
				.save(any(EstudianteModel.class)))
			.thenReturn(estudianteModel);
		
		ResponseEntity<EstudianteResponse> response = evaluacionesController
				.saveEstudiante(estudianteRequest);
		
		assertEquals (HttpStatus.FORBIDDEN, response.getStatusCode());
	}
}

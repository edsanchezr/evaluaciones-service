package com.evaluaciones.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluaciones.service.CalendarioService;
import com.evaluaciones.service.EstudianteService;
import com.evaluaciones.service.ExamenService;
import com.evaluaciones.ws.request.CalendarioExamenRequest;
import com.evaluaciones.ws.request.EstudianteRequest;
import com.evaluaciones.ws.request.ExamenRequest;
import com.evaluaciones.ws.request.RespuestasExamenRequest;
import com.evaluaciones.ws.response.CalendarioResponse;
import com.evaluaciones.ws.response.EstudianteResponse;
import com.evaluaciones.ws.response.EvaluacionResponse;
import com.evaluaciones.ws.response.ExamenResponse;

@RestController
@RequestMapping("/api/evaluaciones/v1")
public class EvaluacionesController {
	
	@Autowired
	private EstudianteService service;
	
	@Autowired
	private ExamenService examenService;
	
	@Autowired
	private CalendarioService calendarioService;
	
	@PostMapping ("/estudiantes")
	public ResponseEntity<EstudianteResponse> saveEstudiante 
		(@RequestBody EstudianteRequest request ) {
		return service.saveEstudiante(request);
	}
	
	@PostMapping ("/examen")
	public ResponseEntity<ExamenResponse> saveExamen 
		(@RequestBody ExamenRequest request ) {
		return examenService.crearExamen(request);
	}
	
	@PostMapping ("/examen-evaluacion")
	public ResponseEntity<EvaluacionResponse> calificaExamen 
		(@RequestBody RespuestasExamenRequest request ) {
		return examenService.evaluarExamen(request);
	}
	
	@PostMapping ("/examen-agendamiento")
	public ResponseEntity<CalendarioResponse> agendarExamen 
		(@RequestBody CalendarioExamenRequest request ) {
		return calendarioService.agendarExamen(request);
	}
}

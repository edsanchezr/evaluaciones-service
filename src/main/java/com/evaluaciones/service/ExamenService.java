package com.evaluaciones.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evaluaciones.common.enums.ErroresEnum;
import com.evaluaciones.common.exception.TransactionDataException;
import com.evaluaciones.dao.CalendarioExamenDao;
import com.evaluaciones.dao.ExamenDao;
import com.evaluaciones.dao.HistorialRespuestasDao;
import com.evaluaciones.dao.PreguntasDao;
import com.evaluaciones.mapper.EvaluacionesMapper;
import com.evaluaciones.mapper.ManualMapper;
import com.evaluaciones.model.CalendarioExamenModel;
import com.evaluaciones.model.EstudianteModel;
import com.evaluaciones.model.ExamenModel;
import com.evaluaciones.model.PreguntasExamenModel;
import com.evaluaciones.model.RespuestasExamenModel;
import com.evaluaciones.ws.request.ExamenRequest;
import com.evaluaciones.ws.request.PreguntaExamenRequest;
import com.evaluaciones.ws.request.RespuestaExamenRequest;
import com.evaluaciones.ws.request.RespuestasExamenRequest;
import com.evaluaciones.ws.response.EvaluacionResponse;
import com.evaluaciones.ws.response.ExamenResponse;

@Service
public class ExamenService {

	private static final Logger log = LoggerFactory.getLogger(ExamenService.class);
	
	@Autowired
	private ExamenDao examenDao;
	@Autowired
	private PreguntasDao preguntasDao;
	@Autowired
	private EvaluacionesMapper mapper;
	@Autowired
	private CalendarioExamenDao calendarioExamenDao;
	@Autowired
	private EstudianteService estudianteService;
	@Autowired
	private HistorialRespuestasDao historialRespuestasDao;
	private CalendarioExamenModel calModel;
	private EstudianteModel estModel;
	
	public ResponseEntity <ExamenResponse> crearExamen (ExamenRequest examenRequest) {
		ExamenResponse response = new ExamenResponse ();
		List<ExamenModel> existe = 
				getExamen(examenRequest.getVersion(), examenRequest.getNivel(), examenRequest.isEstatus());
		if (existe == null || existe.size() == 0 ) {
			
			
			BigDecimal sumaPuntuaciones = new BigDecimal(examenRequest.getPreguntas()
					.stream().mapToDouble(l -> l.getPuntuacion().doubleValue()).sum());
			
			if (sumaPuntuaciones.setScale(2, RoundingMode.HALF_DOWN).toString().equals("100.00")) {
				ExamenModel nuevoExamen = mapper.examenRequestToExamenModel(examenRequest);
				nuevoExamen = saveExamen(nuevoExamen);
				ExamenRequest request = mapper.examenModelToExamenRequest(nuevoExamen);
				request.setPreguntas(examenRequest.getPreguntas());
				if (!request.getPreguntas().isEmpty()) {
					List <PreguntasExamenModel> preguntasModel = new ArrayList<>();
					for (PreguntaExamenRequest per : request.getPreguntas()) {
						PreguntasExamenModel pem = mapper
								.preguntasExamenRequestToPreguntasExamenModel(per);
						pem.setExamen(request.getId());
						preguntasModel.add(pem);
					}
					preguntasModel = savePreguntas(preguntasModel);
					
					request.setPreguntas(new ArrayList <> ());
					for (PreguntasExamenModel pm: preguntasModel) {
						PreguntaExamenRequest pr = mapper.preguntasExamenModelToPreguntasExamenRequest(pm);
						request.getPreguntas().add(pr);
					}
				}
				response.setExamen(request);
				response.setMetadata("Ok", "200", "Examen Creado con éxito");
				return new ResponseEntity<ExamenResponse>(response, HttpStatus.OK);
			} else {
				throw new 
				TransactionDataException(ErroresEnum.PUNTUACION_ERROR.getCode(), 
						"La sumatoria de la puntuacion de las preguntas no cubre los 100 puntos", "SAVE-DATA");
			}
		} else {
			throw new 
			TransactionDataException(ErroresEnum.EXIST_EXAMEN.getCode(), 
					"El examen ya existe con ID: " +  existe.get(0).getIdExamen(), "SAVE-DATA");
		}
	}
	
	public ResponseEntity <EvaluacionResponse> evaluarExamen (RespuestasExamenRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();
		boolean error = false;
		HttpStatus status = null;
		if (validCalendarioAndEstudiante(request)) {
			log.info("Existen los datos...");
			for (int i = 0; i < request.getRespuestas().size(); i ++) {
				Optional <PreguntasExamenModel> preguntaModel = 
						getRespuesta(request.getRespuestas().get(i).getPregunta());
				if (preguntaModel.isPresent()) {
					RespuestaExamenRequest respuesta = request.getRespuestas().get(i);
					respuesta.setCorrecta(preguntaModel.get().getRespuesta());
					respuesta.setPuntuacion(
							(respuesta.getCorrecta().equals(respuesta.getRespuesta())
									? preguntaModel.get().getPuntuacion() : BigDecimal.ZERO));
					request.getRespuestas().set(i, respuesta);
				} else {
					log.info("No existe la pregunta...");
					response
						.setMetadata("Error", ErroresEnum.DOSENT_EXIST_PREGUNTA.getCode(), 
								"No existe la pregunta: " + request.getRespuestas().get(i).getPregunta());
					status = HttpStatus.NOT_FOUND;
					error = true;
					break;
				}
			}
			
			if (!error) {
				log.info("Se recorrieron todas las preguntas");
				List<RespuestasExamenModel> respuestasModel =
						ManualMapper.respuestasRequestToRespuestasModel(request, estModel, calModel);
				guardarHistorialPreguntas(respuestasModel);
				
				BigDecimal puntuacionFinal = new BigDecimal(respuestasModel
						.stream()
						.mapToDouble(l -> l.getPuntuacion().doubleValue())
						.sum()).setScale(2, RoundingMode.HALF_DOWN);
				response.setPuntuacion(puntuacionFinal);
				response.setMetadata("Ok", "200", "Examen Registrado");
				status = HttpStatus.OK;
			}
		} else {
			log.info("No existe estudiante y calendario...");
			response.setMetadata("Error", ErroresEnum.DOSENT_EXIST_CALENDAR.getCode(), "No existe el calendario");
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<EvaluacionResponse> (response, status);
	}
	
	private boolean validCalendarioAndEstudiante (RespuestasExamenRequest request) {
		log.info("Validacion estudiante y calendario...");
		Optional <CalendarioExamenModel> calendario = 
				calendarioExamenDao.findById(request.getCalendario());
		Optional <EstudianteModel> estudiante = estudianteService.getEstudianteById(request.getEstudiante());
		
		if (calendario.isPresent() && estudiante.isPresent()) {
			estModel = estudiante.get();
			calModel = calendario.get();
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional(readOnly = true)
	public List<ExamenModel> getExamen (String versionExamen, Integer nivelExamen, boolean estatus) {
		try {
			return examenDao.findByVersionExamenAndNivelExamenAndEstatusExamen(versionExamen, nivelExamen, estatus);
		} catch (Exception e) {
			e.printStackTrace();
			throw new 
			TransactionDataException(ErroresEnum.SELECT_ERROR.getCode(), 
					"Error al consultar la existencia del examen", "GET-DATA");
		}
	}
	
	@Transactional(readOnly = true)
	public Optional <ExamenModel> getExamenById (Long id) {
		try {
			return examenDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new 
			TransactionDataException(ErroresEnum.SELECT_ERROR.getCode(), 
					"Error al consultar la existencia del examen", "GET-DATA");
		}
	}
	
	@Transactional
	public ExamenModel saveExamen (ExamenModel model) {
		try {
			return examenDao.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new 
			TransactionDataException(ErroresEnum.INSERT_ERROR.getCode(), 
					"Error al guardar el examen", "SAVE-DATA");
		}
	}
	
	@Transactional
	public List <PreguntasExamenModel> savePreguntas (List <PreguntasExamenModel> model) {
		try {
			return (List<PreguntasExamenModel>) preguntasDao.saveAll(model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new 
			TransactionDataException(ErroresEnum.INSERT_ERROR.getCode(), 
					"Error al guardar las preguntas del examen con ID: " + model.get(0).getExamen(), "SAVE-DATA");
		}
	}
	
	@Transactional(readOnly = true)
	public Optional <PreguntasExamenModel> getRespuesta (Long id) {
		try {
			return preguntasDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new 
			TransactionDataException(ErroresEnum.SELECT_ERROR.getCode(), 
					"Error al consultar la pregunta con ID: " + id, "GET-DATA");
		}
	}
	
	@Transactional
	public void guardarHistorialPreguntas (List <RespuestasExamenModel> respuestas) {
		try {
			historialRespuestasDao.saveAll(respuestas);
		} catch (Exception e) {
			e.printStackTrace();
			throw new 
			TransactionDataException(ErroresEnum.INSERT_ERROR.getCode(), 
					"Error al almacenar las respuestas. ", "SAVE-DATA");
		}
	}
}

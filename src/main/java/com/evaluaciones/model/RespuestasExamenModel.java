package com.evaluaciones.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="historial_respuestas")
public class RespuestasExamenModel implements Serializable {
	
	private static final long serialVersionUID = 1311583220770461401L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_historial")
	private Long id;
	
	@ManyToOne
	@JoinColumn (name = "id_calendario")
	private CalendarioExamenModel calendario;
	
	@ManyToOne
	@JoinColumn (name = "id_estudiante")
	private EstudianteModel estudiante;
	
	@Column (name = "respuesta_obtenida")
	private String respuesta;
	
	@Column (name = "respuesta_correcta")
	private String correcta;
	
	@Column (name = "puntuacion_pregunta")
	private BigDecimal puntuacion;
	
	@Column (name = "fecha_registro")
	private Date registro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CalendarioExamenModel getCalendario() {
		return calendario;
	}

	public void setCalendario(CalendarioExamenModel calendario) {
		this.calendario = calendario;
	}

	public EstudianteModel getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(EstudianteModel estudiante) {
		this.estudiante = estudiante;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getCorrecta() {
		return correcta;
	}

	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

	public BigDecimal getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(BigDecimal puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}
}

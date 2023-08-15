package com.evaluaciones.common.enums;

public enum ErroresEnum {
	
	SELECT_ERROR ("ERR-001", "Error en select a bd"),
	INSERT_ERROR ("ERR-002", "Error en insert a bd"),
	UPDATE_ERROR ("ERR-003", "Error en update a bd"),
	EXIST_ESTUDENT("ERR-004", "Ya exise el estudiante"),
	ID_NOT_GENERATE ("ERR-005", "ID no generado"),
	EXIST_EXAMEN("ERR-006", "Ya exise el estudiante"),
	PUNTUACION_ERROR("ERR-007", "No cumple la sumatoria de puntuacion"),
	DOSENT_EXIST_CALENDAR ( "ERR-008", "No existe el calendario"),
	DOSENT_EXIST_PREGUNTA ( "ERR-009", "No existe la pregunta"),
	DOSENT_EXIST_EXAMEN ( "ERR-010", "No existe EL EXAMEN"),
	DOSENT_EXIST_ESTUDIANTE ( "ERR-011", "No existe el estudiante"),
	PARSE_ERROR ("ERR-012", "Error en concersion de valor");
	
	private final String code;
	private final String description;
	
	private ErroresEnum (String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}

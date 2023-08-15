package com.evaluaciones.dao;

import org.springframework.data.repository.CrudRepository;

import com.evaluaciones.model.RespuestasExamenModel;

public interface HistorialRespuestasDao extends CrudRepository<RespuestasExamenModel, Long> {

}

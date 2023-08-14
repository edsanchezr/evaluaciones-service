package com.evaluaciones.dao;

import org.springframework.data.repository.CrudRepository;

import com.evaluaciones.model.PreguntasExamenModel;

public interface PreguntasDao extends CrudRepository<PreguntasExamenModel, Long> {

}

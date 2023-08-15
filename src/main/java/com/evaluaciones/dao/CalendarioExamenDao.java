package com.evaluaciones.dao;

import org.springframework.data.repository.CrudRepository;

import com.evaluaciones.model.CalendarioExamenModel;

public interface CalendarioExamenDao extends CrudRepository<CalendarioExamenModel, Long> {

}

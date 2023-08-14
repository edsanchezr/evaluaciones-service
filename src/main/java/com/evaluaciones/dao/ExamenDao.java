package com.evaluaciones.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.evaluaciones.model.ExamenModel;

public interface ExamenDao extends CrudRepository<ExamenModel, Long> {
	List<ExamenModel> 
		findByVersionExamenAndNivelExamenAndEstatusExamen 
			(String versionExamen, int nivelExamen, boolean estatus);
}

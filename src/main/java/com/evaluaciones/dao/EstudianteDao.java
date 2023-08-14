package com.evaluaciones.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.evaluaciones.model.EstudianteModel;

public interface EstudianteDao extends CrudRepository<EstudianteModel, Integer> {

	List<EstudianteModel> findByNombreAndApellidoPaternoAndApellidoMaterno 
		(String nombre, String apellidoPaterno, String apellidoMaterno);
}

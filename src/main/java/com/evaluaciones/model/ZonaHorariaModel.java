package com.evaluaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zona_horaria")
public class ZonaHorariaModel implements Serializable {

	private static final long serialVersionUID = -4186073052969586720L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_zona_horaria")
	private int idZonaHoraria;
	@Column (name = "descripcion_zona_horaria")
	private String descripcionZonaHoraria;
	
	public int getIdZonaHoraria() {
		return idZonaHoraria;
	}
	public void setIdZonaHoraria(int idZonaHoraria) {
		this.idZonaHoraria = idZonaHoraria;
	}
	public String getDescripcionZonaHoraria() {
		return descripcionZonaHoraria;
	}
	public void setDescripcionZonaHoraria(String descripcionZonaHoraria) {
		this.descripcionZonaHoraria = descripcionZonaHoraria;
	}
}

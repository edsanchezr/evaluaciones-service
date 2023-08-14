package com.evaluaciones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="examen")
public class ExamenModel implements Serializable {
	
	private static final long serialVersionUID = 6122188677549507020L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_examen")
	private Long idExamen;
	
	@Column (name = "version_examen")
	private String versionExamen;
	
	@Column (name = "nivel_examen")
	private Integer nivelExamen;
	
	@Column (name = "estatus_examen")
	private boolean estatusExamen;
	
	public Long getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(Long idExamen) {
		this.idExamen = idExamen;
	}
	public String getVersionExamen() {
		return versionExamen;
	}
	public void setVersionExamen(String versionExamen) {
		this.versionExamen = versionExamen;
	}
	public Integer getNivelExamen() {
		return nivelExamen;
	}
	public void setNivelExamen(Integer nivelExamen) {
		this.nivelExamen = nivelExamen;
	}
	public boolean isEstatusExamen() {
		return estatusExamen;
	}
	public void setEstatusExamen(boolean estatusExamen) {
		this.estatusExamen = estatusExamen;
	}
}

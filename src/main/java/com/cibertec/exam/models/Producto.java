package com.cibertec.exam.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ide_pro")
	Long idProducto;
	
	@Column(name = "nom_pro")
	String nomProducto;
	
	@Column(name = "des_pro")
	String desProducto;
	
	@Column(name = "fec_pro")
	Date fecProducto;


	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNomProducto() {
		return nomProducto;
	}

	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}

	public String getDesProducto() {
		return desProducto;
	}

	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}

	public Date getFecProducto() {
		return fecProducto;
	}

	public void setFecProducto(Date fecProducto) {
		this.fecProducto = fecProducto;
	}

	public Producto(long idProducto, String nomProducto, String desProducto, Date fecProducto) {
		this.idProducto = idProducto;
		this.nomProducto = nomProducto;
		this.desProducto = desProducto;
		this.fecProducto = fecProducto;
	}

	public Producto() {
	}
	
	

}

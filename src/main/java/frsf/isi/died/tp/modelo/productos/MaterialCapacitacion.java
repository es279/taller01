/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.util.ArrayList;
import java.util.Date;
import frsf.isi.died.tp.modelo.nodosDeMateriales.*;
import frsf.isi.died.tp.util.Ordenable;

/**
 * Representa de manera abstracta los materiales de capacitación
 * 
 * @author mdominguez
 * 
 */
public abstract class MaterialCapacitacion implements Ordenable, Comparable<MaterialCapacitacion> {
	protected Integer id;
	/**
	 * Titulo del material
	 */
	protected String titulo;

	/**
	 * Costo básico que debe sumarse al precio por el mero hecho de publicarlo en el
	 * portal
	 */
	protected Double costo;
	
	protected Integer calificacion;
	
	protected Date fecha_publicacion;
	
	protected Relevancia relevancia;
	
	protected Tema tema;
	
	/**
	 * Constructor por defecto
	 */
	public MaterialCapacitacion() {
		this(0,"en desarrollo",0.0);
	}

	/**
	 * Constructor que recibe como argumento un ID y un Titulo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id, String titulo) {
		this(id,titulo,0.0);
	}

	/**
	 * Constructor que recibe como argumento un ID y un costo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id,String titulo, Double costo) {
		this.id =id;
		this.titulo = titulo;
		this.costo = costo;
	}


	//TODO 01 implementar los metodos getters y setters y escribir el javadoc
	// AYUDA: para implementar estos metodos usar un atajo del IDE 
	// elegir el menu "Source" --> "Generate getters y setters" y elegir alli que metodos generar.
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Integer getCalificacion() {
		return calificacion;
	}
	
	public Date getFechaPublicacion() {
		return fecha_publicacion;
	}
	
	public Relevancia getRelevancia() {
		return relevancia;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setFechaPublicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public void setRelevancia(Relevancia relevancia) {
		this.relevancia = relevancia;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	/**
	 * El precio de un material se define según el tipo del material y toma como
	 * base el costo del mismo
	 * 
	 * @return
	 */
	public abstract Double precio();
	
	/**
	 * Retorna verdadero si es una instancia de libro, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esLibro();

	/**
	 * Retorna verdadero si es una instancia de video, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esVideo();
	
	//TODO 02: sobrescribir el metodo toString de la clase "Object"
	//	el método toString retorna un string que representa el material actual
	//  retornando el titulo, y el precio 	 * usando el formato : 
	// [Titulo: <titulo> ; Precio: <precio> ]
	
	public String toString() {
		return "[Titulo " + this.titulo + " ; Precio: " + this.precio()+"]";
	}
	
	// TODO 10: implementar Ordenable
	public final int valor() {
		return this.precio().intValue();
	}
	//RESPUESTA AL INCISO 7.b.ii
	//El m�todo funciona ya que nunca ser� ejecutado desde la clase abstracta en cuesti�n, 
	//sino por sus clases de especializaci�n. Cabe aclarar que el m�todo precio() deber� ser implementado (redefinido)
	//en tales clases de especializaci�n
	
	@Override
	public boolean equals(Object aux) {
		if(aux instanceof MaterialCapacitacion) {
			if(this.titulo.equalsIgnoreCase(((MaterialCapacitacion)aux).titulo)) {
				return true;
			}
			
		}
		return false;
	}

	@Override
	public int compareTo(MaterialCapacitacion i){
		
		if(this.equals(i)) {
			return this.precio().compareTo(i.precio());
		}
		else {
			if(this.titulo.compareTo(i.titulo) < 0) {
				return -1;
			}
			else {
				return 1;
			}
		}
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialCapacitacion other = (MaterialCapacitacion) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}*/
	
	public boolean setCalificacion(int x) {
		if(x>-1 && x<101) {
			calificacion=x;
			return true;
		}
		return false;
	}

}



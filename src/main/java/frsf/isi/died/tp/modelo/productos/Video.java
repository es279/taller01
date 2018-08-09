package frsf.isi.died.tp.modelo.productos;

import java.util.Date;

public class Video extends MaterialCapacitacion {

	public static final Double COSTOSEGUNDO = 0.15;
	private int duracion;
	
	
	public Video(){
		
	}
	public Video(Integer id,String titulo){
		this.id = id;
		this.titulo = titulo;
	}
	public Video(Integer id,String titulo, Double costo, int duracion){
		this.id = id;
		this.titulo = titulo;
		this.costo = costo;
		this.duracion=duracion;
	}
	
	public Video(Integer idendt, String titul, Double cost, Integer durac, Integer calific, Date fechaPublicacion, String relev, String tem) {
		this(idendt, titul, cost, durac);
		this.calificacion = calific;
		this.fecha_publicacion = fechaPublicacion;
		this.relevancia = Relevancia.setRelevancia(relev);
		this.tema = Tema.setTema(tem);
	}
	
	public Double precio() {
		return this.COSTOSEGUNDO * this.duracion + this.costo;
	}

	
	public Boolean esLibro() {
		return false;
	}

	
	public Boolean esVideo() {
		return true;
	}
	
	@Override
	public boolean equals(Object aux) {
		if(aux instanceof Video) {
			if(this.titulo.equalsIgnoreCase(((Video)aux).titulo)) {
				return true;
			}
		}
		return false;
	}
	
	public Integer getDuracion() {
		return this.duracion;
	}
	
	public void setDuracion(int x) {
		this.duracion = x;
	}

}

package frsf.isi.died.tp.modelo.productos;

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
	
	public Double precio() {
		return this.COSTOSEGUNDO * this.duracion + this.costo;
	}

	
	public Boolean esLibro() {
		return false;
	}

	
	public Boolean esVideo() {
		return true;
	}

}

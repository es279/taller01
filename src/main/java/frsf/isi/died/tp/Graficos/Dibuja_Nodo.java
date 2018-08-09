package frsf.isi.died.tp.Graficos;
import frsf.isi.died.tp.estructuras.Vertice;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class Dibuja_Nodo {
	
	private int coordx;
	private int coordy;
	private int radio;
	private String nombre;
	private Boolean esLibro;
	private Vertice<MaterialCapacitacion> mat;
	
	public Dibuja_Nodo(int x, int y, int r, String nombre, Boolean esLibro, MaterialCapacitacion material){
		this.coordx = x=0;
		this.coordy = y=0;
		this.radio = r=30;
		this.nombre = nombre;
		this.esLibro = esLibro;
		this.mat = new Vertice<MaterialCapacitacion>(material);
	}
	
	public Dibuja_Nodo(){
	}
	
	public void pintar (Graphics g) {
		Graphics2D circulo = (Graphics2D) g;
		if(this.esLibro) {
			circulo.setPaint(Color.ORANGE);
		}
		else {
			circulo.setPaint(Color.GREEN);
		}
		circulo.setStroke(new BasicStroke(10.f));
		circulo.fillOval(coordx-radio, coordy-radio, 2*radio, 2*radio);
		//Reestablezco valores iniciales y dibujo bordes de círculos y nombres:
		circulo.setStroke(new BasicStroke(1.f));
		circulo.setPaint(Color.BLACK);
		g.drawString(nombre,coordx-this.nombre.length()*3, coordy+this.radio+11);
		circulo.setPaint(Color.LIGHT_GRAY);
		g.drawOval(coordx-radio, coordy-radio, 2*radio, 2*radio);
		circulo.setPaint(Color.BLACK);
	}
	
	public Boolean getesLibro() {
		return this.esLibro;
	}
	
	public void setesLibro(Boolean esLibro) {
		this.esLibro = esLibro;
	}
	
	public String getnombre() {
		return this.nombre;
	}
	
	public void setnombre( String Nombre ) {
		this.nombre=Nombre;
	}
	
	
	public void setmat(Vertice mat) {
		this.mat=mat;
	}
	
	public Vertice getmat() {
		return this.mat;
	}

	public int getCoordx() {
		return coordx;
	}

	public void setCoordx(int coordx) {
		this.coordx = coordx;
	}

	public int getCoordy() {
		return coordy;
	}

	public void setCoordy(int coordy) {
		this.coordy = coordy;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}
	
	
}

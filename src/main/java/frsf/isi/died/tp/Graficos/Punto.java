package frsf.isi.died.tp.Graficos;
import java.awt.Point;
import java.math.*;
import java.util.ArrayList;

public class Punto {
	private Point p;
	private int xNodo;
	private int yNodo;
	
	
	public Punto (int x, int y, int xNodo, int yNodo) {
		p =new Point();
		this.p.x= x;
		this.p.y = y;
		this.xNodo = xNodo;
		this.yNodo = yNodo;
	}
	
	public Boolean estaEnArrayList(int x,int y,ArrayList<Dibuja_arista> arreglo) {
		for(Dibuja_arista arista : arreglo) {
			if(arista.getCentro2().x==x && arista.getCentro2().y==y && arista.getCentro1().x==this.xNodo && arista.getCentro1().y==this.yNodo) {
				return true;
			}
		}
		return false;
	}
	
	static Boolean estaEnCirculo(int radio, int centrox, int centroy,Point punto) {
		if(Math.sqrt(Math.pow(punto.x-centrox,2)+Math.pow(punto.y-centroy,2)) <= radio) {
			return true;
		}
		else {
			return false;
		}
	}

	public Point getP() {
		return p;
	}


	public void setP(Point p) {
		this.p = p;
	}


	public int getxNodo() {
		return xNodo;
	}


	public void setxNodo(int xNodo) {
		this.xNodo = xNodo;
	}


	public int getyNodo() {
		return yNodo;
	}


	public void setyNodo(int yNodo) {
		this.yNodo = yNodo;
	}


}

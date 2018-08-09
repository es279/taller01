package frsf.isi.died.tp.Graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Dibuja_arista {
	
	private int x1;
	private int y1;
	private Point centro1;
	private int x2;
	private int y2;
	private Point centro2;
	
	public Dibuja_arista(int x1, int y1, int x2, int y2, int centro1x, int centro1y, int centro2x, int centro2y) {
		this.setCentro1(centro1x, centro1y);
		this.setCentro2(centro2x, centro2y);
		this.centro1=centro1;
		this.centro2=centro2;
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}
	
	public void pintar (Graphics g) {
		g.drawLine(x1, y1, x2, y2);
		Graphics2D circulo = (Graphics2D) g;
		circulo.setPaint(Color.RED);
		circulo.setStroke(new BasicStroke(10.f));
		circulo.fillOval(x2-3, y2-3, 6, 6);
		//Reestablezco valores iniciales y dibujo bordes de círculos y nombres:
		circulo.setStroke(new BasicStroke(1.f));
		circulo.setPaint(Color.BLACK);
		g.drawOval(x2-3, y2-3, 6, 6);
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public Point getCentro1() {
		return centro1;
	}

	public void setCentro2(int x, int y) {
		centro2 = new Point();
		centro2.x=x;
		centro2.y=y;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public Point getCentro2() {
		return centro2;
	}

	public void setCentro1(int x, int y) {
		centro1 = new Point();
		centro1.x=x;
		centro1.y=y;
	}
	
	

}

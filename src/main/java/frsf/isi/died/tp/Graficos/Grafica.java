package frsf.isi.died.tp.Graficos;
import java.util.ArrayList;
import java.math.*;

import frsf.isi.died.tp.estructuras.*;
import frsf.isi.died.tp.inteface.MainFrame;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import java.util.Vector;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
//import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Grafica extends JPanel implements MouseListener{
	
	private ArrayList<Dibuja_Nodo> vectorNodos;
	private ArrayList<Dibuja_arista> vectorAristas;
	private List<Arista<MaterialCapacitacion>> vectorAristasMaterial;
	private Punto p1;
	private Punto p2;
	private Vertice<MaterialCapacitacion> p1mat;
	static int separacionEntreNodos = 30;
	static int RadioDeVertices = 50;
	static int cantidadNodosx = 5;
	public void agregaNodo(int x, int y, int r, MaterialCapacitacion material) {
		Dibuja_Nodo nodo = new Dibuja_Nodo(x, y, r,material.getTitulo(),material.esLibro(), material);
		this.vectorNodos.add(nodo);
	}
	
	public void agregaArista(int x1, int y1, int x2, int y2, int centro1x, int centro1y, int centro2x, int centro2y) {
		Dibuja_arista arista = new Dibuja_arista(x1, y1, x2, y2, centro1x, centro1y, centro2x, centro2y);
		vectorAristas.add(arista);
	}
	
	public Grafica () {
		super.setBackground(Color.PINK);
		this.vectorNodos = new ArrayList<>();
		this.vectorAristas = new ArrayList<>();
		this.vectorAristasMaterial = new ArrayList<>();
		MaterialCapacitacion mater = new Libro(-1, "Título");
		this.p1mat = new Vertice<MaterialCapacitacion>(mater);
		this.addMouseListener(this);
	}
	
	public Grafica(ArrayList<Vertice<MaterialCapacitacion>> vertices) {
		super.setBackground(Color.PINK);
		this.vectorNodos = new ArrayList<>();
		this.vectorAristas = new ArrayList<>();
		this.vectorAristasMaterial = new ArrayList<>();
		MaterialCapacitacion mater = new Libro(-1, "Título");
		this.p1mat = new Vertice<MaterialCapacitacion>(mater);
		this.addMouseListener(this);
		
		for(Vertice<MaterialCapacitacion> vert : vertices) {
			Dibuja_Nodo nod = new Dibuja_Nodo();
			nod.setmat(vert);
			nod.setnombre(((MaterialCapacitacion) vert.getValor()).getTitulo());
			nod.setesLibro(((MaterialCapacitacion) vert.getValor()).esLibro());
			vectorNodos.add(nod);
		}
	}
	
	@Override
	public void paint (Graphics g) {
		Integer cont = 1;
		Point p = new Point();
		p.x= (int) (separacionEntreNodos/2)+vectorNodos.get(0).getRadio();
		p.y= (int) (separacionEntreNodos/2)+vectorNodos.get(0).getRadio();
		for(Dibuja_Nodo nodo : vectorNodos) {
			if(nodo.getCoordx()==0 && nodo.getCoordy()==0) {
				nodo.setCoordx(p.x);
				nodo.setCoordy(p.y);
				if(cantidadNodosx==cont) {
					p.y=p.y+nodo.getRadio()*2+separacionEntreNodos;
					p.x= (int) (separacionEntreNodos/2)+vectorNodos.get(0).getRadio();
					cont=1;
					
				}
				else {
					p.x=p.x+separacionEntreNodos+nodo.getRadio()*2;
					cont=cont+1;
				}
			}
			nodo.pintar(g);
		}
		
		for(Dibuja_arista arista : vectorAristas) {
			arista.pintar(g);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(cantidadNodosx);
		int cont=0;
		Boolean p1act=false;
		if(e.getButton() == MouseEvent.BUTTON3){
			for(Dibuja_Nodo nodo : vectorNodos){
				cont=cont+1;
				if(Punto.estaEnCirculo(nodo.getRadio(), nodo.getCoordx(), nodo.getCoordy(), e.getPoint())) {
					if(p1==null) {
						p1 = new Punto(e.getPoint().x,e.getPoint().y, nodo.getCoordx(), nodo.getCoordy());
						
						//Vértice inicial de la arista física
						p1mat = nodo.getmat();
						
						p1act=true;
					}
					else {
						
						if(!Punto.estaEnCirculo(nodo.getRadio(), nodo.getCoordx(), nodo.getCoordy(), p1.getP())) {
								if(!(p1.estaEnArrayList(nodo.getCoordx(), nodo.getCoordy(), vectorAristas))) {
									p2 = new Punto(e.getPoint().x,e.getPoint().y,nodo.getCoordx(),nodo.getCoordy());
									this.agregaArista(p1.getP().x, p1.getP().y, p2.getP().x,p2.getP().y, p1.getxNodo(), p1.getyNodo(), p2.getxNodo(), p2.getyNodo());
									
									//aris es el vértice final de la arista física
									Arista aris = new Arista(p1mat,nodo.getmat());
									this.vectorAristasMaterial.add(aris);
									System.out.println(((MaterialCapacitacion)p1mat.getValor()).getTitulo()+" "+((MaterialCapacitacion)nodo.getmat().getValor()).getTitulo());
									
									repaint();
									p1 = null;
									p2 = null;
								}
							}
						else {
							p1=null;
						}
					}
				}
				if(cont == vectorNodos.size() && p1!=null && p2==null && p1act==false) {
					p1=null;
				}
			}
		}
	}
	
	public static Grafica GraficarNodos(ArrayList<Vertice<MaterialCapacitacion>> lista, Integer width) {
		Grafica graf = new Grafica();
		graf.cantidadNodosx=(width)/(separacionEntreNodos+2*RadioDeVertices);
		for(Vertice<MaterialCapacitacion> vert : lista) {
			graf.agregaNodo(0, 0, RadioDeVertices, vert.getValor());
		}
		
		return graf;
	}
	
	//-------------------
	public static Integer[][] matrizDeAdyac(List<Vertice<MaterialCapacitacion>> nodos, List<Arista<MaterialCapacitacion>> aristas) {
		Integer[][] matriz = new Integer [nodos.size()][nodos.size()];
		
		for(int o=0; o<nodos.size();o++) {
			for(int p=0; p<nodos.size();p++) {
				matriz[o][p]=0;
			}
		}
		
		for(Arista<MaterialCapacitacion> ar : aristas) {
			matriz[nodos.indexOf(ar.getInicio())][nodos.indexOf(ar.getFin())]=1;
		}
		
		return matriz;
	}
	
	public static Double[] PageRank(List<Vertice<MaterialCapacitacion>> nodos, List<Arista<MaterialCapacitacion>> aristas,Double d, Double e){
		Double[] arregloinicial = new Double[nodos.size()];
		for(int i=0;i<arregloinicial.length;i++) {
			arregloinicial[i]=1.0;
		}
		Double[] arreglofinal = new Double[nodos.size()];
		for(int i=0;i<arreglofinal.length;i++) {
			arreglofinal[i]=0.0;
		}
		
		Integer matriz[][];
		
		matriz = matrizDeAdyac(nodos,aristas);
		Boolean bul=false;
		Double pr;
		Double c;
		int cont=0;
		
		while(!bul && cont<51) {
			cont=cont+1;
			for(int q=0;q<nodos.size();q++) {
				for(int w=0;w<nodos.size();w++) {
					pr=0.0;
					c=0.0;
					if(matriz[w][q]==1) {
						pr=pr+arregloinicial[w];
						for(int h=0;h<nodos.size();h++) {
							c=c+matriz[w][h];
						}
						if(c==0.0) {
							c=1.0;
							d=0.0;
						}
						arreglofinal[q]=arreglofinal[q]+d*pr/c;
					}
				}
				arreglofinal[q]=arreglofinal[q]+(1-d);
			}
			
			bul=true;
			for(int k = 0; k<nodos.size();k++) {
				bul=(bul && (Math.abs(arregloinicial[k]-arreglofinal[k])<e));
			}
			
			if(!bul) {
				for (int t=0;t<nodos.size();t++) {
					arregloinicial[t]=arreglofinal[t];
					arreglofinal[t]=0.0;
				}
			}
		}
		
		return arreglofinal;
	}
	//-------------------
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	public ArrayList<Dibuja_Nodo> getVectorNodos() {
		return vectorNodos;
	}

	public void setVectorNodos(ArrayList<Dibuja_Nodo> vectorNodos) {
		this.vectorNodos = vectorNodos;
	}

	public ArrayList<Dibuja_arista> getVectorAristas() {
		return vectorAristas;
	}

	public void setVectorAristas(ArrayList<Dibuja_arista> vectorAristas) {
		this.vectorAristas = vectorAristas;
	}

	public List<Arista<MaterialCapacitacion>> getVectorAristasMaterial() {
		return vectorAristasMaterial;
	}

	public void setVectorAristasMaterial(ArrayList<Arista<MaterialCapacitacion>> vectorAristasMaterial) {
		this.vectorAristasMaterial = vectorAristasMaterial;
	}

	public Punto getP1() {
		return p1;
	}

	public void setP1(Punto p1) {
		this.p1 = p1;
	}

	public Punto getP2() {
		return p2;
	}

	public void setP2(Punto p2) {
		this.p2 = p2;
	}

	public Vertice<MaterialCapacitacion> getP1mat() {
		return p1mat;
	}

	public void setP1mat(Vertice<MaterialCapacitacion> p1mat) {
		this.p1mat = p1mat;
	}

	public static int getCantidadNodosx() {
		return cantidadNodosx;
	}

	public static void setCantidadNodosx(int cantidadNodosx) {
		Grafica.cantidadNodosx = cantidadNodosx;
	}

	public static int getSeparacionEntreNodos() {
		return separacionEntreNodos;
	}

	public static void setSeparacionEntreNodos(int separacionEntreNodos) {
		Grafica.separacionEntreNodos = separacionEntreNodos;
	}
	
	
	
}
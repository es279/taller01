/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.estructuras;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author mdominguez
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class Grafo<T> {

	protected List<Arista<T>> aristas;
	protected List<Vertice<T>> vertices;

	/**
	 * 
	 */
	public Grafo(){
		this.aristas = new ArrayList<Arista<T>>();
		this.vertices = new ArrayList<Vertice<T>>();
	}

	/**
	 * @param nodo
	 */
	public void addNodo(T nodo){
		this.addNodo(new Vertice<T>(nodo));
	}

	/**
	 * @param nodo
	 */
	public void addNodo(Vertice<T> nodo){
		this.vertices.add(nodo);
	}
	
	/**
	 * @param n1
	 * @param n2
	 */
	public void conectar(T n1,T n2){
		this.conectar(getNodo(n1), getNodo(n2), 0.0);
	}

        /**
	 * @param nodo1
	 * @param nodo2
	 */
	public Arista<T> conectar(Vertice<T> nodo1,Vertice<T> nodo2){
            Arista<T> arista = new Arista<T>(nodo1,nodo2,0.0);
            this.aristas.add(arista);
            return arista;
	}
        
	/**
	 * @param n1
	 * @param n2
	 * @param valor
	 */
	public void conectar(T n1,T n2,Number valor){
		this.conectar(getNodo(n1), getNodo(n2), valor);
	}

	/**
	 * @param nodo1
	 * @param nodo2
	 * @param valor
	 */
	public void conectar(Vertice<T> nodo1,Vertice<T> nodo2,Number valor){
		this.aristas.add(new Arista<T>(nodo1,nodo2,valor));
	}
	
	/**
	 * @param valor
	 * @return
	 */
	public Vertice<T> getNodo(T valor){
		return this.vertices.get(this.vertices.indexOf(new Vertice<T>(valor)));
	}

	/**
	 * @param valor
	 * @return
	 */
	public List<T> getAdyacentes(T valor){ 
		Vertice<T> unNodo = this.getNodo(valor);
		List<T> salida = new ArrayList<T>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin().getValor());
			}
		}
		return salida;
	}
	

	/**
	 * @param unNodo
	 * @return
	 */
	protected List<Vertice<T>> getAdyacentes(Vertice<T> unNodo){ 
		List<Vertice<T>> salida = new ArrayList<Vertice<T>>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin());
			}
		}
		return salida;
	}
                
	/**
	 * 
	 */
	public void imprimirAristas(){
		System.out.println(this.aristas.toString());
	}


        
	/**
	 * @param v1
	 * @param v2
	 * @return
	 */
	protected boolean esAdyacente(Vertice<T> v1,Vertice<T> v2){
            List<Vertice<T>> ady = this.getAdyacentes(v1);
            for(Vertice<T> unAdy : ady){
                if(unAdy.equals(v2)) return true;
            }
            return false;
    }
        
    public Boolean esVacio(){
    	if(this.vertices!= null && !this.vertices.isEmpty()) return false;
    	return true;
    }
    
    public List<T> listaVertices(){
    	List<T> lista = new ArrayList<>();
    	this.vertices.forEach(v -> lista.add(v.getValor()));
    	return lista;
    }

		/**
	 * @param vertice
	 * @return
	 */
	public Integer gradoEntrada(T v){
		Vertice<T> vertice = this.getNodo(v);
		Integer res =0;
		int k=this.aristas.size();
		
		for(int i=0;i<k;i++) {
    		if(vertice.equals(this.aristas.get(i).getFin())) {
    			res=res+1;
    		}
    	}
		return res;
	}

	/**
	 * @param vertice
	 * @return
	 */
	
	public Integer gradoSalida(T v){
		Vertice<T> vertice = this.getNodo(v);
		Integer res =0;
		int k=this.aristas.size();
		
		for(int i=0;i<k;i++) {
    		if(vertice.equals(this.aristas.get(i).getInicio())) {
    			res=res+1;
    		}
    	}
		return res;
	}
	

    public T primerVerticeGradoK(T v,Integer gradoK) {
    	ArrayList<T> visitados= new ArrayList<>();
    	
    	if(this.gradoSalida(v)==gradoK) {
    		return v;
    	}
    	else {
    		List<T> adyacAux = new ArrayList<T>();
    		
    		List<T> listaAdyac = this.getAdyacentes(v);
    		visitados.add(this.getNodo(v).getValor());
    		
    		int cont=0;
    		
    		while(!listaAdyac.isEmpty()) {
    			cont=cont+1;
    			if(this.gradoSalida(listaAdyac.get(0))==gradoK) {
    				return listaAdyac.get(0);
    			}
    			else {
    				adyacAux.addAll(this.getAdyacentes(listaAdyac.get(0)));
    				for(int i=0;i<adyacAux.size();i++) {
    					if(!visitados.contains(adyacAux.get(i)) && !listaAdyac.contains(adyacAux.get(i))) {
    						listaAdyac.add(adyacAux.get(i));
    					}
    				}
    				visitados.add((listaAdyac.get(0)));
    				listaAdyac.remove(0);
    				adyacAux.clear();
    			}
    			
    		}
    	}
    	
		return null;
    }
  
    public boolean existeCamino(T v) {
		Vertice<T> vertice = this.getNodo(v);
    	return true;
    }
    
    
    /**
     * @param n1
     * @param n2
     * @param valor
     */
    public List<T> buscarCaminoNSaltos(T n1,T n2,Integer saltos){
		Vertice<T> origen = this.getNodo(n1);
		Vertice<T> destino= this.getNodo(n2);
        return this.buscarCaminoNSaltos(origen, destino, saltos, new HashSet<Vertice>());
         
    }
    
    private List<T> buscarCaminoNSaltos(Vertice<T> n1,Vertice<T> n2,Integer saltos,HashSet<Vertice> visitados){
        ArrayList<T> resultado = new ArrayList<>();
       //
        ArrayList<T> adyacAux = new ArrayList<>();
        if(saltos==0) {
        	if(n1==n2) {
        		resultado.add(n2.getValor());
        	}
        	System.out.println(resultado);
        	return resultado;
        }
        else {
        		visitados.add(n1);
        		adyacAux.addAll(this.getAdyacentes(n1.getValor()));
        		for(int i=0;i<adyacAux.size();i++) {
        			Vertice<T> nuevon1 = this.getNodo(adyacAux.get(i));
            		resultado.addAll(buscarCaminoNSaltos(nuevon1, n2, saltos-1, visitados));
            		if(!resultado.isEmpty()) {
            			resultado.add(0, n1.getValor());
                    	System.out.println(resultado);
            			return resultado;
            		}
            	}
        	
        }
    	System.out.println(resultado);
        return resultado;
    }

    
    public List<ArrayList<Vertice>> todosLosCaminos(Vertice ini, Vertice fin){
    	return todosLosCaminos (ini, fin,Integer.MAX_VALUE);
	}
   
	public List<ArrayList<Vertice>> todosLosCaminos(Vertice ini, Vertice fin, int N){
		ArrayList pila = new ArrayList<ArrayList<Vertice<T>>>(); //Almacena los caminos parciales
		ArrayList caminosFinales = new ArrayList<ArrayList<Vertice<T>>>(); //Almacena los caminos finales (return)
   	
		ArrayList cam1 = new ArrayList<T>();
		cam1.add(ini);
   		if(ini.equals(fin)) {
   			caminosFinales.add(cam1);
   		}
   		else {
   			List<Vertice<T>> listaAdy = new ArrayList<Vertice<T>>();
   			ArrayList<Vertice<T>> listaCaminoPrimeroEnCola = new ArrayList<Vertice<T>>();
   			ArrayList<Vertice<T>> aux = new ArrayList<Vertice<T>>();
   			
   			pila.add(cam1);
   		
   			while(!pila.isEmpty()) {
   				listaCaminoPrimeroEnCola = (ArrayList<Vertice<T>>)(pila.get(0));
   				listaAdy = this.getAdyacentes((Vertice<T>)((ArrayList<Vertice<T>>)pila.get(0)).get(((ArrayList<Vertice<T>>)pila.get(0)).size()-1));
   				for(Vertice<T> vert : listaAdy) {
   					if(!listaCaminoPrimeroEnCola.contains(vert)) {
   						aux = ((ArrayList<Vertice<T>>)((ArrayList<T>)listaCaminoPrimeroEnCola).clone());
   						aux.add(vert);
   						if(vert.equals(fin) && aux.size()<=N) {
   							caminosFinales.add(aux);
   							for(Vertice<T> v : aux) {
   								System.out.print(((MaterialCapacitacion)v.getValor()).getTitulo());
   							}
   							System.out.println("");
   						}
   						else {   						
   							if(aux.size()<N) {
   								pila.add(aux);
   							}
   						}
   					}
   				}
   				pila.remove(0);
   			}
   			
   		}
   		return caminosFinales;
	}

	public void setAristas(List<Arista<T>> aristas) {
		this.aristas = aristas;
	}

	public void setVertices(List<Vertice<T>> vertices) {
		this.vertices = vertices;
	}
}


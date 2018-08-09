package frsf.isi.died.tp.inteface;
import java.util.ArrayList;
import java.math.*;

import frsf.isi.died.tp.modelo.productos.*;

public class Monticulo {
	private Boolean tieneMayorPrioridad(MaterialCapacitacion uno, MaterialCapacitacion dos) {
		if( uno.getRelevancia().compareTo(dos.getRelevancia())<0 ) {
			return true;
		}
		else if(uno.getRelevancia().compareTo(dos.getRelevancia())==0) {
			if(uno.getCalificacion()>dos.getCalificacion()) {
				return true;
			}
			else{
				if(uno.getCalificacion()==dos.getCalificacion()) {
						return uno.getCosto()>dos.getCosto();
				}
			}
		}
		
		return false;
	}
	
	private void flotar(int index, ArrayList<MaterialCapacitacion> lista) {
		Monticulo aux = new Monticulo();
		if(index != 0) {
			if(aux.tieneMayorPrioridad(lista.get(index),lista.get((int)(index-1)/2))) {
				MaterialCapacitacion mat;
				mat = lista.get(index);
				lista.add(index, lista.get((int)((index-1)/2)));
				lista.remove(index+1);
				lista.add((int)((index-1)/2), mat);
				lista.remove((int)((index-1)/2)+1);
				flotar((int)((index-1)/2), lista);
			}
		}
	}
	
	private static void aniadirAMont(MaterialCapacitacion elemento, ArrayList<MaterialCapacitacion> lista){
		lista.add(elemento);
		Monticulo aux = new Monticulo();
		aux.flotar(lista.size()-1, lista);
		
	}
	
	public static ArrayList<MaterialCapacitacion> crearMont(ArrayList<MaterialCapacitacion> lista){
		ArrayList<MaterialCapacitacion> listaFinal = new ArrayList<>();
		
		for(MaterialCapacitacion elemento : lista) {
			Monticulo.aniadirAMont(elemento, listaFinal);
		}
		
		listaFinal = Monticulo.Ordenarheap(listaFinal);
		
		return listaFinal;
	}
	
	public static ArrayList<MaterialCapacitacion> Ordenarheap(ArrayList<MaterialCapacitacion> lista) {
		ArrayList<MaterialCapacitacion> listaOrdenada = new ArrayList<MaterialCapacitacion>();
		int cont;
		Monticulo montic = new Monticulo();
		while(lista.size()>1) {
			listaOrdenada.add(lista.get(0));
			lista.remove(0);
			lista.add(0,lista.get(lista.size()-1));
			lista.remove(lista.size()-1);
			MaterialCapacitacion auxCambio;
			cont=0;
			while(2*cont+1<=lista.size()-1) {
				if(2*cont+1==lista.size()-1) {
					if(montic.tieneMayorPrioridad(lista.get(2*cont+1), lista.get(cont))) {
						auxCambio = lista.get(cont);
						lista.add(cont,lista.get(2*cont+1));
						lista.remove(cont+1);
						lista.add(2*cont+1,auxCambio);
						lista.remove(2*cont+2);
						cont=2*cont+1;
					}
					else {
						cont=lista.size();
					}
				}
				else if(montic.tieneMayorPrioridad(lista.get(2*cont+1), lista.get(2*cont+2))) {
					if(montic.tieneMayorPrioridad(lista.get(2*cont+1), lista.get(cont))) {
						auxCambio = lista.get(cont);
						lista.add(cont,lista.get(2*cont+1));
						lista.remove(cont+1);
						lista.add(2*cont+1,auxCambio);
						lista.remove(2*cont+2);
						cont=2*cont+1;
					}
					else {
						cont=lista.size();
					}
				}
				else if (montic.tieneMayorPrioridad(lista.get(2*cont+2), lista.get(2*cont+1))) {
					if(montic.tieneMayorPrioridad(lista.get(2*cont+2), lista.get(cont))) {
						auxCambio = lista.get(cont);
						lista.add(cont,lista.get(2*cont+2));
						lista.remove(cont+1);
						lista.add(2*cont+2,auxCambio);
						lista.remove(2*cont+3);
						cont=2*cont+2;
					}
					else {
						cont=lista.size();
					}
				}
			}
		}
		listaOrdenada.add(lista.get(0));
		
		return listaOrdenada;
	}
	
}

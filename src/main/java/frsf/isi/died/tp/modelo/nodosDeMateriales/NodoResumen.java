package frsf.isi.died.tp.modelo.nodosDeMateriales;

import java.util.ArrayList;

public class NodoResumen  extends NodoMaterial {
	private ArrayList<NodoHoja> parrafos;
	
	public NodoResumen() {
		puedeTenerHijo=true;
		tipo = TipoNodo.RESUMEN;
		parrafos = new ArrayList<NodoHoja>();
	}
	
	public NodoMaterial addParrafo(String nuevoValor) {
		NodoHoja nh = new NodoHoja(TipoNodo.PARRAFO, nuevoValor);
		if(!parrafos.contains(nh)) {
			parrafos.add(nh);
			return nh;
		}
		else{
			System.out.println("El parrafo \"" + nuevoValor + "\" ya se encuentra en la lista de parrafos especificada");
			return null;
		}
	}
	
	public NodoMaterial agregaNodo(String value, TipoNodo tipo) {
		/**
		 * @return Retorna true si se tuvo éxito al crear el nodo hijo. 
		 * Retorna null en otro caso (si el elemento no puede tener hijos del tipo "tipo" 
		 * o si ya fue asignado previamente)
		 */
		if(tipo.equals(TipoNodo.PARRAFO)){
			return this.addParrafo(value);
		}
		return null;
	}
	
	public void imprimirArbol() {
		System.out.println("  " + this.toString());
		for (NodoHoja nodoHoja : this.parrafos) {
			System.out.println("    " + nodoHoja.toString());
		}
	}
	
	public boolean search(String busqueda, TipoNodo tipoNod) {
		if (this.tipo.equals(tipoNod) && this.valor.contains(busqueda)) {
			return true;
		} else if( tipoNod.esContenido( this.tipo.tiposDescendientes() ) ) {
			if (tipoNod.equals(TipoNodo.PARRAFO)) {
				for (NodoHoja nh : parrafos) {
					if(nh.search(busqueda, tipoNod))
						return true;
				}	
			}
		}
		return false;
	}

	
	public ArrayList<NodoHoja> getParrafos() {
		return parrafos;
	}
	
}

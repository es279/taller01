package frsf.isi.died.tp.modelo.nodosDeMateriales;

import java.util.ArrayList;

public class NodoCapitulo extends NodoMaterial {
	private ArrayList<NodoSeccion> secciones;
	private NodoMetadatoDeCapitulo metadatos;
	
	public NodoCapitulo() {
		puedeTenerHijo=true;
		tipo = TipoNodo.CAPITULO;
		secciones = new ArrayList<NodoSeccion>();
	}
	
	public NodoCapitulo(String val) {
		this();
		this.setValor(val);
	}
	
	public NodoMaterial addSeccion(String nuevoValor) {
		NodoSeccion ns = new NodoSeccion(nuevoValor);
		if(!secciones.contains(ns)) {
			secciones.add(ns);
			return ns;
		}
		else{
			System.out.println("La seccion \"" + nuevoValor + "\" ya se encuentra en la lista de secciones especificada");
			return null;
		}
	}
	
	public NodoMaterial setMetadatoDeCapitulo (String metadatoCapitulo) {
		metadatos = new NodoMetadatoDeCapitulo(metadatoCapitulo);
		return metadatos;
	}
	
	public NodoMaterial agregaNodo(String value, TipoNodo tipo) {
		/**
		 * @return Retorna true si se tuvo éxito al crear el nodo hijo. 
		 * Retorna null en otro caso (si el elemento no puede tener hijos del tipo "tipo" 
		 * o si ya fue asignado previamente)
		 */
		if(tipo.equals(TipoNodo.SECCION)){
			return this.addSeccion(value);
		} else if(tipo.equals(TipoNodo.METADATO_DE_CAPITULO)){
			if(this.metadatos==null) {
				return this.setMetadatoDeCapitulo(value);
			}
		}
		return null;
	}
	
	public void imprimirArbol() {
		System.out.println("  " + this.toString());
		for (NodoSeccion nodoHijo : this.secciones) {
			nodoHijo.imprimirArbol();
		}
		if (this.metadatos!=null)
			this.metadatos.imprimirArbol();
	}
	
	public boolean search(String busqueda, TipoNodo tipoNod) {
		if (this.tipo.equals(tipoNod) && this.valor.contains(busqueda)) {
			return true;
		} else if( tipoNod.esContenido( this.tipo.tiposDescendientes() ) ) {
			if (tipoNod.equals(TipoNodo.SECCION)) {
				for (NodoSeccion nh : secciones) {
					if(nh.search(busqueda, tipoNod))
						return true;
				}	
			}
			else if (this.metadatos!=null && tipoNod.equals(TipoNodo.METADATO_DE_CAPITULO)) {
				if (this.metadatos.search(busqueda, tipoNod))
					return true;
			}
		}
		return false;
	}
	
	
	public ArrayList<NodoSeccion> getSecciones() {
		return secciones;
	}

	public NodoMetadatoDeCapitulo getMetadatos() {
		return metadatos;
	}
	
}

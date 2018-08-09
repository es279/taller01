package frsf.isi.died.tp.modelo.nodosDeMateriales;

import java.util.ArrayList;

public class NodoTitulo extends NodoMaterial {
	private Integer ID;
	private NodoMetadato metadatos;
	private NodoResumen resumen;
	private ArrayList<NodoCapitulo> capitulos;
	
	public NodoTitulo() {
		puedeTenerHijo=true;
		tipo = TipoNodo.TITULO;
		capitulos = new ArrayList<NodoCapitulo>();
	}
	
	public NodoMaterial setMetadato(String val) {
		if(metadatos==null) {
			metadatos = new NodoMetadato();
		}
		metadatos.setValor(val);
		return metadatos;
	}
	
	public NodoMaterial setResumen(String val) {
		if(resumen == null) {
			resumen = new NodoResumen();
		}
		resumen.setValor(val);
		return resumen;
	}
	
	public NodoCapitulo addCapitulos(String nuevoValor) {
		NodoCapitulo nc = new NodoCapitulo(nuevoValor);
		if(!capitulos.contains(nc)) {
			capitulos.add(nc);
			return nc;
		}
		else{
			System.out.println("El capitulo \"" + nuevoValor + "\" ya se encuentra en la lista de capitulos especificada");
			return null;
		}
	}
	
	public NodoMaterial agregaNodo(String value, TipoNodo tipo) {
		/**
		 * @return Retorna true si se tuvo éxito al crear el nodo hijo. 
		 * Retorna null en otro caso (si el elemento no puede tener hijos del tipo "tipo" 
		 * o si ya fue asignado previamente)
		 */
		if(tipo.equals(TipoNodo.METADATO)){
			if(this.metadatos==null) {
				return this.setMetadato(value);
			}
		} else if(tipo.equals(TipoNodo.RESUMEN)){
			if(this.resumen==null) {
				return this.setResumen(value);
			}
		} else if(tipo.equals(TipoNodo.CAPITULO)){
			return this.addCapitulos(value);
		}
		return null;
	}
	
	public void imprimirArbol() {
		System.out.println(this.toString());
		if(metadatos!=null)
			metadatos.imprimirArbol();
		if(resumen!=null)
			resumen.imprimirArbol();
		for (NodoCapitulo nodoHijo : capitulos) {
			nodoHijo.imprimirArbol();
		}
	}
	
	public boolean search(String busqueda, TipoNodo tipoNod) {
		if (this.tipo.equals(tipoNod) && this.valor.contains(busqueda)) {
			return true;
		}
		if( this.metadatos!=null && (tipoNod.equals(TipoNodo.METADATO) || tipoNod.esContenido(TipoNodo.METADATO.tiposDescendientes()) ) ) {
			if( this.metadatos.search( busqueda, tipoNod) )
				return true;
		}
		if( this.resumen!=null && (tipoNod.equals(TipoNodo.RESUMEN) || tipoNod.esContenido(TipoNodo.RESUMEN.tiposDescendientes()) ) ) {
			if( this.resumen.search( busqueda, tipoNod) )
				return true;
		}
		if ( tipoNod.equals(TipoNodo.CAPITULO) || tipoNod.esContenido(TipoNodo.CAPITULO.tiposDescendientes()) ) {
			for (NodoCapitulo nc : this.capitulos) {
				if( nc.search( busqueda, tipoNod) )
					return true;
			}
		}

		return false;
	}
	
	public Integer getID() {
		return ID;
	}
	
	public NodoMetadato getMetadatos() {
		return metadatos;
	}

	public NodoResumen getResumen() {
		return resumen;
	}

	public ArrayList<NodoCapitulo> getCapitulos() {
		return capitulos;
	}
	
	public void setID(Integer x) {
		this.ID = x;
	}
	
}

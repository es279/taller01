package frsf.isi.died.tp.modelo.nodosDeMateriales;

import java.util.ArrayList;

public class NodoMetadato extends NodoMaterial{
	
	private ArrayList<NodoHoja> autor;
	private NodoHoja editorial;
	private NodoHoja fechaPublicacion;
	private NodoHoja palabrasClave;
	
	public NodoMetadato() {
		puedeTenerHijo=true;
		tipo = TipoNodo.METADATO;
		autor = new ArrayList<NodoHoja>();
	}
	
	public NodoMaterial setEditorial(String nuevoValor) {
		editorial = new NodoHoja(TipoNodo.EDITORIAL,nuevoValor);
		return editorial;
	}
	
	public NodoMaterial addAutor(String nuevoValor) {
		NodoHoja nh = new NodoHoja(TipoNodo.AUTOR, nuevoValor);
		if(!autor.contains(nh)) {
			autor.add(nh);
			return nh;
		}
		else{
			System.out.println("El autor \"" + nuevoValor + "\" ya se encuentra en la lista de autores especificada");
			return null;
		}
	}
	
	public NodoMaterial setFechaPublicacion (String fecha) {
		fechaPublicacion = new NodoHoja(TipoNodo.FECHA_PUBLICACION,fecha);
		return fechaPublicacion;
	}
	
	public NodoMaterial setPalabrasClave (String palabrClav) {
		palabrasClave = new NodoHoja(TipoNodo.PALABRA_CLAVE,palabrClav);
		return palabrasClave;
	}
	
	public NodoMaterial agregaNodo(String value, TipoNodo tipo) {
		/**
		 * @return Retorna true si se tuvo éxito al crear el nodo hijo. 
		 * Retorna null en otro caso (si el elemento no puede tener hijos del tipo "tipo" 
		 * o si ya fue asignado previamente)
		 */
		if(tipo.equals(TipoNodo.AUTOR)){
			return this.addAutor(value);
		} else if(tipo.equals(TipoNodo.EDITORIAL)){
			if(this.editorial==null) {
				return this.setEditorial(value);
			}
		} else if(tipo.equals(TipoNodo.FECHA_PUBLICACION)){
			if(this.fechaPublicacion==null) {
				return this.setFechaPublicacion(value);
			}
		} else if(tipo.equals(TipoNodo.PALABRA_CLAVE)){
			if(this.palabrasClave==null) {
				return this.setPalabrasClave(value);
			}
		}
		return null;
	}
	
	public void imprimirArbol() {
		System.out.println("  " + this.toString());
		for (NodoHoja nodoHoja : autor) {
			System.out.println("      " + nodoHoja.toString());
		}
		if(editorial!=null)
			System.out.println("      " + this.editorial.toString());
		if(fechaPublicacion!=null)
			System.out.println("      " + this.fechaPublicacion.toString());
		if(palabrasClave!=null)
			System.out.println("      " + this.palabrasClave.toString());
	}
	
	public boolean search(String busqueda, TipoNodo tipoNod) {
		if (this.tipo.equals(tipoNod) && this.valor.contains(busqueda)) {
			return true;
		} else if( tipoNod.esContenido( this.tipo.tiposDescendientes() ) ) {
			if (tipoNod.equals(TipoNodo.AUTOR)) {
				for (NodoHoja nh : autor) {
					if(nh.search(busqueda, tipoNod))
						return true;
				}	
			}
			else if (this.editorial!=null && tipoNod.equals(TipoNodo.EDITORIAL)) {
				if (this.editorial.search(busqueda, tipoNod))
					return true;
			}
			else if (this.fechaPublicacion!=null && tipoNod.equals(TipoNodo.FECHA_PUBLICACION)) {
				if (this.fechaPublicacion.search(busqueda, tipoNod))
					return true;
			}
			else if ( this.palabrasClave!=null && tipoNod.equals(TipoNodo.PALABRA_CLAVE) ) {
				if (this.palabrasClave.search(busqueda, tipoNod))
					return true;
			}
		}
		return false;
	}

	
	public ArrayList<NodoHoja> getAutor() {
		return autor;
	}

	public NodoHoja getEditorial() {
		return editorial;
	}

	public NodoHoja getFechaPublicacion() {
		return fechaPublicacion;
	}

	public NodoHoja getPalabrasClave() {
		return palabrasClave;
	}
	
	
}

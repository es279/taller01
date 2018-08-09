package frsf.isi.died.tp.modelo.nodosDeMateriales;

import java.util.ArrayList;

public class NodoMetadatoDeCapitulo extends NodoMaterial {
	private ArrayList<NodoHoja> websRelacionados;
	private ArrayList<NodoHoja> websDeEjercicios;
	private NodoHoja palabrasClave;
	
	public NodoMetadatoDeCapitulo() {
		puedeTenerHijo=true;
		tipo = TipoNodo.METADATO_DE_CAPITULO;
		websRelacionados = new ArrayList<NodoHoja>();
		websDeEjercicios = new ArrayList<NodoHoja>();
	}
	
	public NodoMetadatoDeCapitulo(String val) {
		this();
		this.valor=val;
	}
	
	public NodoMaterial addWebRelacionada(String nuevoValor) {
		NodoHoja nh = new NodoHoja(TipoNodo.WEB_RELACIONADA, nuevoValor);
		if(!websRelacionados.contains(nh)) {
			websRelacionados.add(nh);
			return nh;
		}
		else{
			System.out.println("La web relacionada \"" + nuevoValor + "\" ya se encuentra en la lista de webs relacionadas especificada");
			return null;
		}
	}
	
	public NodoMaterial addWebEjercicios(String nuevoValor) {
		NodoHoja nh = new NodoHoja(TipoNodo.WEB_EJERCICIOS, nuevoValor);
		if(!websDeEjercicios.contains(nh)) {
			websDeEjercicios.add(nh);
			return nh;
		}
		else{
			System.out.println("La web de ejercicios \"" + nuevoValor + "\" ya se encuentra en la lista de webs ejercicios especificada");
			return null;
		}
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
		if(tipo.equals(TipoNodo.WEB_RELACIONADA)){
			return this.addWebRelacionada(value);
		} else if(tipo.equals(TipoNodo.WEB_EJERCICIOS)){
			return this.addWebEjercicios(value);
		} else if(tipo.equals(TipoNodo.PALABRA_CLAVE)){
			if(this.palabrasClave==null) {
				return this.setPalabrasClave(value);
			}
		}
		return null;
	}
	
	public void imprimirArbol() {
		System.out.println("    " + this.toString());
		for (NodoHoja nodoHoja : this.websRelacionados) {
			System.out.println("      " + nodoHoja.toString());
		}
		for (NodoHoja nodoHoja : this.websDeEjercicios) {
			System.out.println("      " + nodoHoja.toString());
		}
		if (this.palabrasClave!=null)
			System.out.println("      " + this.palabrasClave.toString());
	}
	
	public boolean search(String busqueda, TipoNodo tipoNod) {
		if (this.tipo.equals(tipoNod) && this.valor.contains(busqueda)) {
			return true;
		} else if( tipoNod.esContenido( this.tipo.tiposDescendientes() ) ) {
			if (tipoNod.equals(TipoNodo.WEB_RELACIONADA)) {
				for (NodoHoja nh : websRelacionados) {
					if(nh.search(busqueda, tipoNod))
						return true;
				}	
			}
			else if (tipoNod.equals(TipoNodo.WEB_EJERCICIOS)) {
				for (NodoHoja nh : websDeEjercicios) {
					if(nh.search(busqueda, tipoNod))
						return true;
				}	
			}
			else if (this.palabrasClave!=null && tipoNod.equals(TipoNodo.PALABRA_CLAVE)) {
				if (this.palabrasClave.search(busqueda, tipoNod))
					return true;
			}
		}
		return false;
	}

	
	public ArrayList<NodoHoja> getWebsRelacionados() {
		return websRelacionados;
	}

	public ArrayList<NodoHoja> getWebsDeEjercicios() {
		return websDeEjercicios;
	}

	public NodoHoja getPalabrasClave() {
		return palabrasClave;
	}
	
}

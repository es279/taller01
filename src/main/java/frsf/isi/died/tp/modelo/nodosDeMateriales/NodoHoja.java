package frsf.isi.died.tp.modelo.nodosDeMateriales;

public class NodoHoja extends NodoMaterial {
	public	NodoHoja(TipoNodo tipoNodo){
		puedeTenerHijo=false;
		tipo = tipoNodo;
	}
	
	public	NodoHoja(TipoNodo tipoNodo, String valorNodo){
		puedeTenerHijo=false;
		valor = valorNodo;
		tipo = tipoNodo;
	}
	
	
	@Override
	public boolean equals(Object otroNodoHoja) {
		try {
			if(otroNodoHoja.getClass()!=this.getClass()) {
				return false;
			}
			return this.valor.equals(((NodoHoja) otroNodoHoja).getValor());	
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
}

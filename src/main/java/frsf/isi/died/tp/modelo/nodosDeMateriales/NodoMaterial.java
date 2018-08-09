package frsf.isi.died.tp.modelo.nodosDeMateriales;

public abstract class NodoMaterial {
	protected String valor;
	public TipoNodo tipo;
	protected Boolean puedeTenerHijo;
	
	protected Boolean puedeTenerHijo() {
		return puedeTenerHijo;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String val) {
		this.valor=val;
	}
	
	@Override
	public String toString() {
		if(this.valor==null || this.valor.isEmpty())
			return (this.tipo.toString() + ": NULL");
		return (this.tipo.toString() + ": " + this.valor);
	}
	
	public NodoMaterial agregaNodo(String value, TipoNodo tipo) {
		/**
		 * @return Retorna true si se tuvo éxito al crear el nodo hijo. 
		 * Retorna null en otro caso (si el elemento no puede tener hijos del tipo "tipo" 
		 * o si ya fue asignado previamente)
		 */
		return null;
	}
	
	public boolean search(String busqueda, TipoNodo tipoNod) {
		return (this.tipo.equals(tipoNod) && this.valor.contains(busqueda));
	}
}

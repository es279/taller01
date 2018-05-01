package frsf.isi.died.tp.modelo;

public class NoEncontrado extends RuntimeException{
	
	public NoEncontrado (int costo) {
		
		super("Material de precio " + costo + " no encontrado");
		
	}
	
}

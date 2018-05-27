package frsf.isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.Collection;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class BibliotecaList implements Biblioteca {
	
	private ArrayList<MaterialCapacitacion> materiales;
	
	public BibliotecaList() {
		this.materiales = new ArrayList<>();
		}
	
	public void agregar(MaterialCapacitacion mat) {
		this.materiales.add(mat);
	}


	@Override
	public Integer cantidadMateriales() {
		return this.materiales.size();
	}

	@Override
	public Integer cantidadLibros() {
		int cont=0;
		for(int i=0; i < this.materiales.size(); i++) {
			if(this.materiales.get(i).esLibro()) {
				cont++;
			}
		}
		return cont;
	}

	@Override
	public Integer cantidadVideos() {
		int cont=0;
		for(int i=0; i < this.materiales.size(); i++) {
			if(this.materiales.get(i).esVideo()) {
				cont++;
			}
		}
		return cont;
	}

	@Override
	public Collection<MaterialCapacitacion> materiales() {
		return this.materiales();
	}

	@Override
	public void imprimir() {
		for(int i=0;i<this.materiales.size();i++) {
			System.out.println(this.materiales.get(i));
		}
	}

	@Override
	public void ordenarPorPrecio(Boolean b) {
		if(b) {
			this.materiales.sort((m1,m2) -> m1.precio().compareTo(m2.precio()));
		}
		else {
			this.materiales.sort((m1,m2) -> m1.getTitulo().compareTo(m2.getTitulo()));
		}
	}
	
	@Override
	public MaterialCapacitacion buscar(Integer precio) {
		this.ordenarPorPrecio(true);
		return buscadorBinario(0,this.materiales.size()-1,precio);
	}
	
	private MaterialCapacitacion buscadorBinario(Integer i,Integer f, Integer c){
			if(f<i) {
			throw new RuntimeException ("Material de precio " + c + " no encontrado");
		}
		else {
			if( this.materiales.get((int)((i+f)/2)).getCosto().intValue() == c) {
				return this.materiales.get((int)((i+f)/2));
			}
			else {
				if(this.materiales.get((int)((i+f)/2)).getCosto().intValue() < c) {
					return buscadorBinario((int)((i+f)/2)+1, f, c);
				}
				else {
					return buscadorBinario(i, (int)((i+f)/2)-1, c);
				}
			}
		}
}
	
}

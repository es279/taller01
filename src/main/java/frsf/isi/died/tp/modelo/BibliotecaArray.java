package frsf.isi.died.tp.modelo;

import java.util.Arrays;
import java.util.Collection;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;


/**
 * Esta clase implementa la interface Biblioteca y todas sus operaciones, gestionando todos los
 * @see MaterialCapacitacion en un arreglo.
 * @author mdominguez
 *
 */
public class BibliotecaArray implements Biblioteca{

	/**
	 * Arreglo donde se almacenan todos los materiales de capacitacion
	 */
	private MaterialCapacitacion[] materialCapacitacion;
	/**
	 * Variable interna de control que se utiliza para llevar registro de cuantos materiales 
	 * se llevan insertados. Se utiliza para evitar insertar un nuevo material fuera de rango
	 * y tambien para responder la consulta acerca de cuantos materiales hay insertados
	 */
	private Integer cantidadMaterial;
	
	// TODO 12: crear una variable de tipo ListaService que apuntará a una instancia del servicio de operaciones de lista

	
	public BibliotecaArray() {
		cantidadMaterial=0;
		this.materialCapacitacion= new MaterialCapacitacion[10];
		// TODO 13: inicializar la variable de tipo ListaService para que apunte el servicio de operaciones de listas		
	}

	@Override
	public void agregar(MaterialCapacitacion material) {
		// TODO 06: se agrega un material al arreglo de materiales de capacitacion si hay espacio en el arreglo
		// caso contrario el metodo no agrega ningun elemento y termina su ejecución
		MaterialCapacitacion[] aux = new MaterialCapacitacion[this.cantidadMaterial+1];
		for(int i=0;i<this.cantidadMaterial;i++){
			aux[i]=this.materialCapacitacion[i];
		}
		aux[this.cantidadMaterial]=material;
		this.materialCapacitacion=aux;
		this.cantidadMaterial++;
	}

	@Override
	public Integer cantidadMateriales() {
		// TODO 07: retorna la cantidad de materiales que hay ingresados en el sistema
		return this.materialCapacitacion.length;
	}



	@Override
	public Integer cantidadLibros() {
		// TODO 08: retorna la cantidad de libros registrados en el sistema.
		// No se puede usar para este método el operador "instanceOf" ni realizar ningun tipo de casting. 
		int cont = 0;
		for(int i=0;i<this.cantidadMaterial;i++){
			if(this.materialCapacitacion[i].esLibro()){
				cont++;
			}
		}
		
		return cont;
	}



	@Override
	public Integer cantidadVideos() {
		// TODO 09: retorna la cantidad de videos registrados en el sistema. 
		// No se puede usar para este método el operador "instanceOf" ni realizar ningun tipo de casting. 
		int cont = 0;
		for(int i=0;i<this.cantidadMaterial;i++){
			if(this.materialCapacitacion[i].esVideo()){
				cont++;
			}
		}
		
		return cont;
	}

	@Override
	public void imprimir() {		
		//TODO 14: invocar al método imprimir de la variable de tipo ListaService para que imprima el arreglo 
	}
		

	@Override
	public void ordenarPorPrecio(Boolean b) {
		// TODO 15: invocar al metodo ordenar de la variable de tipo ListaService para que ordene el arreglo 
	}


	@Override
	public Collection<MaterialCapacitacion> materiales() {
		return Arrays.asList(this.materialCapacitacion);
	}



}

package frsf.isi.died.tp.modelo.nodosDeMateriales;

public enum TipoNodo {
	TITULO,
	METADATO,
	RESUMEN,
	CAPITULO,
	AUTOR,
	EDITORIAL,
	FECHA_PUBLICACION,
	PALABRA_CLAVE,
	SECCION,
	PARRAFO,
	METADATO_DE_CAPITULO,
	WEB_RELACIONADA,
	WEB_EJERCICIOS;
	
	public TipoNodo[] tiposDeHijos() {
		if(this==TITULO) {
			return new TipoNodo[] {METADATO,RESUMEN,CAPITULO};
		} else if(this==METADATO) {
			return new TipoNodo[] {AUTOR,EDITORIAL,FECHA_PUBLICACION,PALABRA_CLAVE};
		} else if(this==RESUMEN) {
			return new TipoNodo[] {PARRAFO};
		} else if(this==TipoNodo.CAPITULO) {
			return new TipoNodo[] {SECCION,METADATO_DE_CAPITULO};
		} else if(this==SECCION) {
			return new TipoNodo[] {PARRAFO};
		} else if(this==METADATO_DE_CAPITULO) {
			return new TipoNodo[] {WEB_RELACIONADA,WEB_EJERCICIOS,PALABRA_CLAVE};
		} else {
			return new TipoNodo[0];
		}
	}
	
	public TipoNodo[] tiposDescendientes() {
		if(this==TITULO) {
			return new TipoNodo[] {METADATO,AUTOR,EDITORIAL,FECHA_PUBLICACION,PALABRA_CLAVE,
									RESUMEN,PARRAFO,
									CAPITULO,SECCION,METADATO_DE_CAPITULO};
		} else if(this==TipoNodo.CAPITULO) {
			return new TipoNodo[]  {SECCION,PARRAFO,
									METADATO_DE_CAPITULO,WEB_RELACIONADA,WEB_EJERCICIOS,PALABRA_CLAVE};
		} else {
			return tiposDeHijos();
		}
	}
	
	public boolean esContenido(TipoNodo[] tiposNodos) {
		/**
		 * @return Verifica si el TipoNodo actual (this) está contenido dentro de tiposNodos
		 */
		for (TipoNodo tn : tiposNodos) {
			if(this.equals(tn)) {
				return true;
			}
		}
		return false;
	}
}

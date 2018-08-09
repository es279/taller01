package frsf.isi.died.tp.modelo.productos;

public enum Relevancia {
	ALTA,MEDIA,BAJA;
	
	static Relevancia setRelevancia(String y) {
		if (y.equalsIgnoreCase(Relevancia.ALTA.toString())) {
			return Relevancia.ALTA;
		}
		else if (y.equalsIgnoreCase(Relevancia.MEDIA.toString())) {
			return Relevancia.MEDIA;
		}
		else if (y.equalsIgnoreCase(Relevancia.BAJA.toString())) {
			return Relevancia.BAJA;
		}
		return null;
	}
	
}

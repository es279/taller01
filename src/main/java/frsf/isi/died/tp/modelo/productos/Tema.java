package frsf.isi.died.tp.modelo.productos;

public enum Tema {
	ALGEBRA,ANALISIS_MATEMATICO,DIED,FISICA,;
	
	static Tema setTema(String y) {
		if (y.equalsIgnoreCase(Tema.ALGEBRA.toString())) {
			return Tema.ALGEBRA;
		}
		else if (y.equalsIgnoreCase(Tema.ANALISIS_MATEMATICO.toString())) {
			return Tema.ANALISIS_MATEMATICO;
		}
		else if (y.equalsIgnoreCase(Tema.DIED.toString())) {
			return Tema.DIED;
		}
		else if (y.equalsIgnoreCase(Tema.FISICA.toString())) {
			return Tema.FISICA;
		}
		return null;
	}
}

package frsf.isi.died.tp.inteface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import frsf.isi.died.tp.modelo.nodosDeMateriales.*;

public class TreeRegisterManager {
	public static ArrayList<NodoTitulo> cargarArboles() {
		ArrayList<NodoTitulo> arbol = new ArrayList<NodoTitulo>();
		BufferedReader br = null;
		try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/ArbolBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	String [] fields = line.split(";");
	    	while (line!=null) {
	    		NodoTitulo nt = new NodoTitulo();
	    		if (fields[0].equals("ID")) {
	    			if (fields.length>1) {
		    			try {
		    				nt.setID(Integer.parseInt(fields[1]));
		    			}
		    			catch (NumberFormatException e) { }
		    			
		    			line = br.readLine();
		    			if (line==null) {
			    			fields = new String[] {"Casa"};
		    			}
		    			else {
		    				fields = line.split(";");
		    			}
			    		if (fields[0].equals(TipoNodo.TITULO.toString())) {
				    		if (fields.length>1)
				    			nt.setValor(fields[1]);
			    			line = br.readLine();
			    			if (line==null) {
				    			fields = new String[] {"Casa"};
			    			}
			    			else {
			    				fields = line.split(";");
			    			}
			    		}
			    		if ( fields[0].equals(TipoNodo.METADATO.toString()) ) {		//Agrega METADATO
			    			NodoMetadato md = (NodoMetadato) nt.setMetadato(null);
			    			if (fields.length>1)
			    				md.setValor(fields[1]);
			    			
				    		line = br.readLine();
			    			if (line==null) {
				    			fields = new String[] {"Casa"};
			    			}
			    			else {
			    				fields = line.split(";");
			    			}
				    		if ( fields[0].equals(TipoNodo.AUTOR.toString()) ) {
				    			if(fields.length>1) {
				    				for (int i = 1; i < fields.length; i++) {
				    					md.addAutor(fields[i]);
				    				}
				    			}
				    			line = br.readLine();
				    			if (line==null) {
					    			fields = new String[] {"Casa"};
				    			}
				    			else {
				    				fields = line.split(";");
				    			}
				    		}
				    		if ( fields[0].equals(TipoNodo.EDITORIAL.toString()) ) {
				    			md.setEditorial(fields[1]);
				    			
				    			line = br.readLine();
				    			if (line==null) {
					    			fields = new String[] {"Casa"};
				    			}
				    			else {
				    				fields = line.split(";");
				    			}
				    		}
				    		if ( fields[0].equals(TipoNodo.FECHA_PUBLICACION.toString()) ) {
				    			md.setFechaPublicacion(fields[1]);
				    			
				    			line = br.readLine();
				    			if (line==null) {
					    			fields = new String[] {"Casa"};
				    			}
				    			else {
				    				fields = line.split(";");
				    			}
				    		}
				    		if ( fields[0].equals(TipoNodo.PALABRA_CLAVE.toString()) ) {
				    			md.setPalabrasClave(fields[1]);
				    			
				    			line = br.readLine();
				    			if (line==null) {
					    			fields = new String[] {"Casa"};
				    			}
				    			else {
				    				fields = line.split(";");
				    			}
				    		}
			    		}
			    		
			    		if ( fields[0].equals(TipoNodo.RESUMEN.toString()) ) {		//Agregar RESUMEN
			    			NodoResumen nr = (NodoResumen) nt.setResumen(null);
			    			if (fields.length>1)
			    				nr.setValor(fields[1]);

				    		line = br.readLine();
			    			if (line==null) {
				    			fields = new String[] {"Casa"};
			    			}
			    			else {
			    				fields = line.split(";");
			    			}
				    		if ( fields[0].equals(TipoNodo.PARRAFO.toString()) ) {
				    			if(fields.length>1) {
				    				for (int i = 1; i < fields.length; i++) {
				    					nr.addParrafo(fields[i]);
				    				}
				    			}
				    			
				    			line = br.readLine();
				    			if (line==null) {
					    			fields = new String[] {"Casa"};
				    			}
				    			else {
				    				fields = line.split(";");
				    			}
				    		}
			    		}
			    		
			    		while ( fields[0].equals(TipoNodo.CAPITULO.toString()) ) {		//Agregar CAPITULO
			    			NodoCapitulo nc = (NodoCapitulo) nt.addCapitulos(null);
			    			if (fields.length>1)
			    				nc.setValor(fields[1]);
			    			
			    			line = br.readLine();
			    			if (line==null) {
				    			fields = new String[] {"Casa"};
			    			}
			    			else {
			    				fields = line.split(";");
			    			}
			    			while ( fields[0].equals(TipoNodo.SECCION.toString()) ) {
			    				NodoSeccion ns = (NodoSeccion) nc.addSeccion(null);
			    				if (fields.length>1)
				    				ns.setValor(fields[1]);
			    				
					    		line = br.readLine(); fields = line.split(";");
					    		if ( fields[0].equals(TipoNodo.PARRAFO.toString()) ) {
					    			if(fields.length>1) {
					    				for (int i = 1; i < fields.length; i++) {
					    					ns.addParrafo(fields[i]);
					    				}
					    			}
					    			
					    			line = br.readLine();
					    			if (line==null) {
						    			fields = new String[] {"Casa"};
					    			}
					    			else {
					    				fields = line.split(";");
					    			}
					    		}
			    			}
				    		if ( fields[0].equals(TipoNodo.METADATO_DE_CAPITULO.toString()) ) {
				    			NodoMetadatoDeCapitulo nmc = (NodoMetadatoDeCapitulo) nc.setMetadatoDeCapitulo(null);
				    			if (fields.length>1)
				    				nmc.setValor(fields[1]);
				    			
				    			line = br.readLine();
				    			if (line==null) {
					    			fields = new String[] {"Casa"};
				    			}
				    			else {
				    				fields = line.split(";");
				    			}
				    			if( fields[0].equals(TipoNodo.WEB_RELACIONADA.toString()) ) {
				    				if(fields.length>1) {
					    				for (int i = 1; i < fields.length; i++) {
					    					nmc.addWebRelacionada(fields[i]);
					    				}
					    			}
				    				
					    			line = br.readLine();
					    			if (line==null) {
						    			fields = new String[] {"Casa"};
					    			}
					    			else {
					    				fields = line.split(";");
					    			}
				    			}
				    			if( fields[0].equals(TipoNodo.WEB_EJERCICIOS.toString()) ) {
				    				if(fields.length>1) {
					    				for (int i = 1; i < fields.length; i++) {
					    					nmc.addWebEjercicios(fields[i]);
					    				}
					    			}
				    				
					    			line = br.readLine();
					    			if (line==null) {
						    			fields = new String[] {"Casa"};
					    			}
					    			else {
					    				fields = line.split(";");
					    			}
				    			}
				    			if ( fields[0].equals(TipoNodo.PALABRA_CLAVE.toString()) ) {
					    			nmc.setPalabrasClave(fields[1]);
					    			
					    			line = br.readLine();
					    			if (line==null) {
						    			fields = new String[] {"Casa"};
					    			}
					    			else {
					    				fields = line.split(";");
					    			}
					    		}
				    		}
			    		}
			    		arbol.add(nt);
	    			}
		    		else {
		    			System.out.println("Al menos uno de los arboles no tiene su ID asignado");
		    		}
	    		}
	    		else {
	    			line = br.readLine();
	    			if (line!=null) {
	    				fields = line.split(";");
	    			}
	    		}

	    	}
		}
		catch(Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo ArbolBD.csv", "Info",JOptionPane.NO_OPTION);
		}
		
		return arbol;
	}
	
	public static void guardarArbol(NodoTitulo nt, Integer idMaterial) {
		String aux = "ID;" + idMaterial.toString() + "\n";
		NodoMetadato nMetadato = nt.getMetadatos();
		NodoResumen nResumen = nt.getResumen();
		ArrayList<NodoCapitulo> nCapitulos = nt.getCapitulos();
		aux += nt.tipo.toString() + ";" + nt.getValor();
		
		if (nMetadato != null) {
			aux += "\n" + nMetadato.tipo.toString() + ";";
			if (nMetadato.getValor() != null)
				aux += nMetadato.getValor();
			
			ArrayList<NodoHoja> nAutor = nMetadato.getAutor();
			NodoHoja nEditorial = nMetadato.getEditorial();
			NodoHoja nFechaPublicacion = nMetadato.getFechaPublicacion();
			NodoHoja nPalabrasClave = nMetadato.getPalabrasClave();
			
			if (nAutor.size() > 0) {
				aux += "\n" + TipoNodo.AUTOR.toString();
				for (NodoHoja na : nAutor) {
					aux += ";" + na.getValor();
				}
			}
			if (nEditorial != null) {
				aux += "\n" + TipoNodo.EDITORIAL.toString() + ";";
				if (nEditorial.getValor() != null)
					aux += nEditorial.getValor();
			}
			if (nFechaPublicacion != null) {
				aux += "\n" + TipoNodo.FECHA_PUBLICACION.toString() + ";";
				if (nFechaPublicacion.getValor() != null)
					aux += nFechaPublicacion.getValor();
			}
			if (nPalabrasClave != null) {
				aux += "\n" + TipoNodo.PALABRA_CLAVE.toString() + ";";
				if (nPalabrasClave.getValor() != null)
					aux += nPalabrasClave.getValor();
			}
		}
		
		if (nResumen != null) {
			aux += "\n" + TipoNodo.RESUMEN.toString() + ";";
			if (nResumen.getValor() != null)
				aux += nResumen.getValor();
			
			ArrayList<NodoHoja> nParrafos = nResumen.getParrafos();
			
			if (nParrafos.size() > 0) {
				aux += "\n" + TipoNodo.PARRAFO.toString();
				for (NodoHoja np : nParrafos) {
					aux += ";" + np.getValor();
				}
			}
		}
		
		if (!nCapitulos.isEmpty()) {
			for (NodoCapitulo nc : nCapitulos) {
				aux += "\n" + TipoNodo.CAPITULO.toString() + ";";
				if (nc != null)
					aux += nc.getValor();
				
				ArrayList<NodoSeccion> nSeccion = nc.getSecciones();
				NodoMetadatoDeCapitulo nMetadatoDeCapitulo = nc.getMetadatos();
				
				if (nSeccion.size() > 0) {
					for (NodoSeccion ns : nSeccion) {
						aux += "\n" + TipoNodo.SECCION.toString() + ";";
						if (ns.getValor() != null)
							aux += ns.getValor();
						
						ArrayList<NodoHoja> nParrafos = ns.getParrafos();
						
						if (nParrafos.size() > 0) {
							aux += "\n" + TipoNodo.PARRAFO.toString();
							for (NodoHoja np : nParrafos) {
								aux += ";" + np.getValor();
							}
						}
					}
				}
				if (nMetadatoDeCapitulo != null) {
					aux += "\n" + TipoNodo.METADATO_DE_CAPITULO.toString() + ";";
					if (nMetadatoDeCapitulo.getValor() != null)
						aux += nMetadatoDeCapitulo.getValor();
					
					ArrayList<NodoHoja> nWebRelacionados = nMetadatoDeCapitulo.getWebsRelacionados();
					ArrayList<NodoHoja> nWebEjercicios = nMetadatoDeCapitulo.getWebsDeEjercicios();
					NodoHoja nPalabrasClaves = nMetadatoDeCapitulo.getPalabrasClave();
					
					if (nWebRelacionados.size() > 0) {
						aux += "\n" + TipoNodo.WEB_RELACIONADA.toString();
						for (NodoHoja nh : nWebRelacionados) {
							aux += ";" + nh.getValor();
						}
					}
					if (nWebEjercicios.size() > 0) {
						aux += "\n" + TipoNodo.WEB_EJERCICIOS.toString();
						for (NodoHoja nh : nWebEjercicios) {
							aux += ";" + nh.getValor();
						}
					}
					if (nPalabrasClaves != null) {
						aux += "\n" + TipoNodo.PALABRA_CLAVE.toString() + ";";
						if (nPalabrasClaves.getValor() != null)
							aux += nPalabrasClaves.getValor();
					}
				}
			}
		}
		
		if (!aux.isEmpty()) {
			File arbolBD = new File("src/main/java/frsf/isi/died/tp/archivos/ArbolBD.csv");
			try {	//Verifica si el archivo ya existe
				if(arbolBD.createNewFile()) {
					BufferedWriter bw1 = null;
					bw1 =new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/ArbolBD.csv"));
					bw1.append("TipoNodo;ValorNodo1;ValorNodo2;ValorNodo3;...");
					bw1.close();
				}
			}
			catch(Exception e2) { }
			
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/ArbolBD.csv", true));
				bw.append("\n" + aux);
				bw.close();
			}
			catch (IOException e) {}
		}
	}
	
	public static boolean existeArbolConID(Integer id) {
		BufferedReader br = null;
		try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/ArbolBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (line!=null) {
	    	String [] fields = line.split(";");
	    		if(fields.length > 1) {
	    			if (fields[0].equals("ID")) {
	    				if(fields[1].equals(id.toString())) {
	    					br.close();
	    					return true;
	    				}
	    			}
	    		}
	    		
	    		line = br.readLine();
	    	}
	    	br.close();
		}
		catch (IOException e) {}
		return false;
	}
}

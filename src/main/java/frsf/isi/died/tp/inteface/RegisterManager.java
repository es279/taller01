package frsf.isi.died.tp.inteface;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import frsf.isi.died.tp.estructuras.Vertice;
import frsf.isi.died.tp.modelo.productos.*;
import frsf.isi.died.tp.modelo.usuarios.Suscriptor;

public class RegisterManager {
	
	static public ArrayList<Libro> cargarLibros(){
		ArrayList<Libro> aux = new ArrayList<Libro>();
	     BufferedReader br = null;
	     
	    try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		
	    		Libro lib = new Libro(Integer.parseInt(fields[0]),		//ID
	    				  			  fields[1],							//Título
	    				  			  Double.parseDouble(fields[2]),		//Costo
	    				  			  Double.parseDouble(fields[3]),		//Precio de compra
	    				  			  Integer.parseInt(fields[4]),		//Número de páginas
	    				  			  Integer.parseInt(fields[5]),		//Calificación
	    				  			  new SimpleDateFormat("yyyy-MM-dd").parse(fields[6]),	//Fecha de publicacion
	    				  			  fields[7],							//Relevancia
	    				  			  fields[8]);							//Tema
	    		aux.add(lib);
	    		line = br.readLine();
	    	}
	        br.close();
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo LibroBD.CSV", "Info",JOptionPane.NO_OPTION);
	    }
	      
	    return aux;
	}
	
	static public ArrayList<Libro> cargarLibrosConCriterios(String titulo,String calificacion, String tema,String fDesde,String fHasta){
		/**
		 * @return Retorna un arrayList<Libro> con los libros almacenados en "LibroBD.csv" si tiene éxito. Sino retorna un ArrayList vacío
		 * */
		ArrayList<Libro> aux = new ArrayList<Libro>();
	    BufferedReader br = null;
	    
	    
	    Date fechaDesde = new Date();
	    Date fechaHasta = new Date();
		try {
			fechaDesde = new SimpleDateFormat("yyyy-MM-dd").parse(fDesde);
		} catch (ParseException e2) {}
		try {
			fechaHasta = new SimpleDateFormat("yyyy-MM-dd").parse(fHasta);
		} catch (ParseException e1) {}

	     
	    try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fields[6]);
	    		if(titulo.isEmpty() || titulo.equalsIgnoreCase(fields[1])) {
	    			if(calificacion.isEmpty() || calificacion==fields[5]) {
	    				if(fecha.after(fechaDesde) && fecha.before(fechaHasta)) {
	    					if(tema.equals(fields[8])) {
	    						Libro lib = new Libro(Integer.parseInt(fields[0]),		//ID
	    				  			  fields[1],						//Título
	    				  			  Double.parseDouble(fields[2]),	//Costo
	    				  			  Double.parseDouble(fields[3]),	//Precio de compra
	    				  			  Integer.parseInt(fields[4]),		//Número de páginas
	    				  			  Integer.parseInt(fields[5]),		//Calificación
	    				  			  fecha,							//Fecha de publicacion
	    				  			  fields[7],						//Relevancia
	    				  			  fields[8]);						//Tema
	    						aux.add(lib);
	    					}
	    		    		
	    				}
	    			}
	    		}
	    		line = br.readLine();
	    	}
	        br.close();
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo LibroBD.CSV. No hay Libros para mostrar", "Info",JOptionPane.NO_OPTION);
	    }
	      
	    return aux;
	}
	
	public static ArrayList<Video> cargarVideosConCriterios(String titulo,String calificacion, String tema,String fDesde,String fHasta) {
		/**
		 * @return Retorna un arrayList<Video> con los vídeos almacenados en "VideoBD.csv" si tiene éxito. Sino retorna un ArrayList vacío
		 * */
		ArrayList<Video> aux = new ArrayList<Video>();
	    BufferedReader br = null;
	    
	    Date fechaDesde = new Date();
	    Date fechaHasta = new Date();
		try {
			fechaDesde = new SimpleDateFormat("yyyy-MM-dd").parse(fDesde);
		} catch (ParseException e2) {}
		try {
			fechaHasta = new SimpleDateFormat("yyyy-MM-dd").parse(fHasta);
		} catch (ParseException e1) {}

	     
	    try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fields[5]);
	    		if(titulo.isEmpty() || titulo.equalsIgnoreCase(fields[1])) {
	    			if(calificacion.isEmpty() || calificacion==fields[4]) {
	    				if(fecha.after(fechaDesde) && fecha.before(fechaHasta)) {
	    					if(tema.equals(fields[7])) {
	    						Video vid = new Video(Integer.parseInt(fields[0]),		//ID
	    				  			  fields[1],						//Título
	    				  			  Double.parseDouble(fields[2]),	//Costo
	    				  			  Integer.parseInt(fields[3]),		//Duración
	    				  			  Integer.parseInt(fields[4]),		//Calificación
	    				  			  fecha,							//Fecha de publicacion
	    				  			  fields[6],						//Relevancia
	    				  			  fields[7]);						//Tema
	    						aux.add(vid);
	    					}
	    		    		
	    				}
	    			}
	    		}
	    		line = br.readLine();
	    	}
	        br.close();
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo VideoBD.CSV. No hay Videos para mostrar", "Info",JOptionPane.NO_OPTION);
	    }
	      
	    return aux;
	}
	
	static public void guardarLibros(String titulo, String paginas,String precioCompra,String costo,String fecha,Relevancia relev, Tema tem) {
		Integer id = RegisterManager.getID();
		if (id!=null) {
			BufferedWriter bw = null;
			File libroBD = new File("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv");
			try {	//Verifica si el archivo ya existe
				if(libroBD.createNewFile()) {
					BufferedWriter bw1 =new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv"));
					bw1.append("id;titulo;costo;precio_compra;numero_paginas;calificacion;fecha_publicacion;relevancia;tema");
					bw1.close();
				}
			}
			catch(Exception e) {}
			
			try {
				bw = new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv", true));
				bw.append( "\n" + id + ";" + titulo + ";" + costo + ";" + precioCompra + ";" + paginas +
							";" + "0" + ";" + fecha + ";" + relev.toString() + ";" + tem.toString() );
				bw.close();
			} catch (IOException e) {
		        JOptionPane.showMessageDialog(null, "No se encontró el archivo LibroBD.CSV", "Info",JOptionPane.NO_OPTION);
			}
		}
		JOptionPane.showMessageDialog(null, "El Libro fue guardado con éxito", "Info",JOptionPane.NO_OPTION);
	}
	
	static public void guardarVideos(String titulo, String duracion,String costo,String fecha,Relevancia relev, Tema tem) {
		Integer id = RegisterManager.getID();
		if (id!=null) {
			BufferedWriter bw = null;
			
			File videoBD = new File("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv");
			try {	//Verifica si el archivo ya existe
				if(videoBD.createNewFile()) {
					BufferedWriter bw1 =new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv"));
					bw1.append("id;titulo;costo;duracion;calificacion;fecha_publicacion;relevancia;tema");
					bw1.close();
				}
			}
			catch(Exception e) {}
			
			try {
				bw = new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv", true));
				bw.append( "\n" + id + ";" + titulo + ";" + costo + ";" + duracion + ";" + 
							"0" + ";" + fecha + ";" + relev.toString() + ";" + tem.toString() );
				bw.close();
			} catch (IOException e) {
		        JOptionPane.showMessageDialog(null, "No se encontró el archivo VideoBD.CSV", "Info",JOptionPane.NO_OPTION);
			}
		}
		JOptionPane.showMessageDialog(null, "El Vídeo fue guardado con éxito", "Info",JOptionPane.NO_OPTION);
	}
	
	static public boolean actualizarLibro(Libro lib) {
		/**
		* @return Retorna true si tiene éxito, false en otro caso
		*/
		boolean actualizado = false;
		String auxiliar = "";
	    BufferedReader br = null;
        BufferedWriter bw = null;
	     
	    try {
	    	br = new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv"));
	    	auxiliar = br.readLine() + "\n";
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		if( !actualizado && fields[0].equals(lib.getId().toString())) {
	    			Calendar cal = new GregorianCalendar();
	    			cal.setTime(lib.getFechaPublicacion());
	    	    	String fechaPublicacion = String.format("%04d-%02d-%02d",cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
	    	    	
	    	    	auxiliar += lib.getId() + ";" + lib.getTitulo() + ";" + lib.getCosto() + ";" +
	    	    			lib.getPrecioCompra() + ";" + lib.getPaginas() + ";" + lib.getCalificacion() + ";" +
	    	    			fechaPublicacion + ";" + lib.getRelevancia().toString() + ";" + lib.getTema().toString() + "\n";
	    			actualizado=true;
	    		}
	    		else {
	    			auxiliar += line + "\n";
	    		}
	    		line = br.readLine();
	    	}
	        br.close();
	        try {
				bw = new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv"));
				bw.write(auxiliar);
				bw.close();
	        }
	        catch (IOException e1){
		        JOptionPane.showMessageDialog(null, "No se encontró el archivo LibroBD.CSV", "Info",JOptionPane.NO_OPTION);
		        return false;
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo LibroBD.CSV", "Info",JOptionPane.NO_OPTION);
	        return false;
	    }
	      
	    return actualizado;
	}
	
	public static boolean actualizarVideo(Video vid) {
		/**
		* @return Retorna true si tiene éxito, false en otro caso
		*/
		boolean actualizado = false;
		String auxiliar = "";
	    BufferedReader br = null;
        BufferedWriter bw = null;
	     
	    try {
	    	br = new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv"));
	    	auxiliar = br.readLine();
	    	String line = br.readLine();
	    	while (line!=null) {
	    		String [] fields = line.split(";");
	    		if( !actualizado && fields[0].equals(vid.getId().toString())) {
	    			Calendar cal = new GregorianCalendar();
	    			cal.setTime(vid.getFechaPublicacion());
	    	    	String fechaPublicacion = String.format("%04d-%02d-%02d",cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
	    	    	
	    	    	auxiliar += "\n" + vid.getId() + ";" + vid.getTitulo() + ";" + vid.getCosto() + ";" +
	    	    			vid.getDuracion() + ";" + vid.getCalificacion() + ";" + fechaPublicacion + ";" +
	    	    			vid.getRelevancia().toString() + ";" + vid.getTema().toString();
	    			actualizado=true;
	    		}
	    		else {
	    			auxiliar += "\n" + line;
	    		}
	    		line = br.readLine();
	    	}
	        br.close();
	        try {
				bw = new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv"));
				bw.write(auxiliar);
				bw.close();
	        }
	        catch (IOException e1){
		        JOptionPane.showMessageDialog(null, "No se encontró el archivo VideoBD.CSV", "Info",JOptionPane.NO_OPTION);
		        return false;
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo VideoBD.CSV", "Info",JOptionPane.NO_OPTION);
	        return false;
	    }
	      
	    return actualizado;
	}
	
	static public boolean eliminarLibro(String id){
		/**
		* @return Retorna true si tiene éxito, false en otro caso
		* @param id  - ID del libro a borrar
		*/
		boolean borrado = false;
		String aux = "";
	    BufferedReader br = null;
        BufferedWriter bw = null;
	     
	    try {
	    	br = new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv"));
	    	aux = br.readLine();
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		if(fields[0].equals(id)) {
	    			borrado=true;
	    		}
	    		else {
	    			aux += "\n" + line;
	    		}
	    		line = br.readLine();
	    	}
	        br.close();
	        try {
				bw = new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv"));
				bw.write(aux);
				bw.close();
	        }
	        catch (IOException e1){
		        JOptionPane.showMessageDialog(null, "No se encontró el archivo LibroBD.CSV", "Info",JOptionPane.NO_OPTION);
		        return false;
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo LibroBD.CSV", "Info",JOptionPane.NO_OPTION);
	        return false;
	    }
	      
	    return borrado;
	}
	

	public static boolean eliminarVideo(String id) {
		/**
		* @return Retorna true si tiene éxito, false en otro caso
		* @param id  - ID del video a borrar
		*/
		boolean borrado = false;
		String aux = "";
	    BufferedReader br = null;
        BufferedWriter bw = null;
	     
	    try {
	    	br = new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv"));
	    	aux = br.readLine();
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		if(fields[0].equals(id)) {
	    			borrado=true;
	    		}
	    		else {
	    			aux += "\n" + line;
	    		}
	    		line = br.readLine();
	    	}
	        br.close();
	        try {
				bw = new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv"));
				bw.write(aux);
				bw.close();
	        }
	        catch (IOException e1){
		        JOptionPane.showMessageDialog(null, "No se encontró el archivo VideoBD.CSV", "Info",JOptionPane.NO_OPTION);
		        return false;
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo VideoBD.CSV", "Info",JOptionPane.NO_OPTION);
	        return false;
	    }
	      
	    return borrado;
	}
	
	static public ArrayList<Vertice<MaterialCapacitacion>> cargarVerticesDeTema(String tema){
		ArrayList<Vertice<MaterialCapacitacion>> aux = new ArrayList<Vertice<MaterialCapacitacion>>();
	    BufferedReader br = null;
	     
	    try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		if(tema.equals(fields[8])) {
	    			Libro lib = new Libro(Integer.parseInt(fields[0]),		//ID
	    				  			  fields[1],							//Título
	    				  			  Double.parseDouble(fields[2]),		//Costo
	    				  			  Double.parseDouble(fields[3]),		//Precio de compra
	    				  			  Integer.parseInt(fields[4]),		//Número de páginas
	    				  			  Integer.parseInt(fields[5]),		//Calificación
	    				  			  new SimpleDateFormat("yyyy-MM-dd").parse(fields[6]),	//Fecha de publicacion
	    				  			  fields[7],							//Relevancia
	    				  			  fields[8]);							//Tema
	    			Vertice<MaterialCapacitacion> vert = new Vertice<MaterialCapacitacion>(lib);
	    			aux.add(vert);
	    		}

	    		line = br.readLine();
	    	}
	        br.close();
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo LibroBD.CSV", "Info",JOptionPane.NO_OPTION);
	    }
	    
	    try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		if(tema.equals(fields[7])) {
	    			Video vid = new Video(Integer.parseInt(fields[0]),		//ID
	    				  			  fields[1],							//Título
	    				  			  Double.parseDouble(fields[2]),		//Costo
	    				  			  Integer.parseInt(fields[3]),		//Duración
	    				  			  Integer.parseInt(fields[4]),		//Calificación
	    				  			  new SimpleDateFormat("yyyy-MM-dd").parse(fields[5]),	//Fecha de publicacion
	    				  			  fields[6],							//Relevancia
	    				  			  fields[7]);							//Tema
	    			Vertice<MaterialCapacitacion> vert = new Vertice<MaterialCapacitacion>(vid);
	    			aux.add(vert);
	    		}

	    		line = br.readLine();
	    	}
	        br.close();
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo VideoBD.CSV", "Info",JOptionPane.NO_OPTION);
	    }
	    return aux;
	}
	
	private static Integer getID() {
		Integer id = 0;
	    BufferedReader br = null;
	    BufferedWriter bw = null;
	    
	    
	    try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/UltimoID.csv"));
	    	String line = br.readLine();
	    	if(line!=null) {
	    		String[] fields =line.split(";");
	    		System.out.println(fields[0]);
	    		id = Integer.parseInt(fields[0]);
	    	}
	    	else {
	    		id=0;
	    	}
			br.close();
	    }
	    catch(NumberFormatException e0) {
	    	JOptionPane.showMessageDialog(null, "El archivo UltimoID.CSV debe contener únicamente valores numéricos", "Info",JOptionPane.NO_OPTION);
	    }
	    catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, "No se encontró el archivo UltimoID.CSV", "Info",JOptionPane.NO_OPTION);
	    }
	    id++;
	    try {
	    	bw =new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/UltimoID.csv"));
	    	bw.write(id.toString());
			bw.close();
	    }
	    catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, "No se pudo sobre-escribir el archivo UltimoID.CSV", "Info",JOptionPane.NO_OPTION);
	    	return null;
	    }
	    return id;
	}
	
	public static ArrayList<Suscriptor> cargarUsuarios(){
		ArrayList<Suscriptor> usuarios = new ArrayList<Suscriptor>();
		
	    BufferedReader br = null;
	    try {
	    	br = new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/UsuarioBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (line!=null) {
	    		String [] fields = line.split(";");
	    		Suscriptor sus = new Suscriptor(fields[0]);
	    		if(fields.length>1) {
	    			for(int i = 1; i < fields.length ; i++) {
	    				try {
	    					sus.addToWishlist( Integer.parseInt(fields[i]) );
	    				}
	    				catch (NumberFormatException e1) {
	    					
	    				}
	    			}
	    		}
	    		usuarios.add(sus);
	    		
	    		line = br.readLine();
	    	}
	        br.close();
	    }
	    catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, "No se encontró el archivo UsuarioID.CSV", "Info",JOptionPane.NO_OPTION);
	    	return null;
	    }
	    
		return usuarios;
	}
	
	public static ArrayList<MaterialCapacitacion> cargarWishList(Suscriptor sus){
		ArrayList<MaterialCapacitacion> aux = new ArrayList<MaterialCapacitacion>();
		BufferedReader br = null;
	    
	    try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/LibroBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		
	    		if(sus.getWishlist().contains(Integer.parseInt(fields[0]))) {
		    		Libro lib = new Libro(Integer.parseInt(fields[0]),		//ID
		    							fields[1],							//Título
		    							Double.parseDouble(fields[2]),		//Costo
		    							Double.parseDouble(fields[3]),		//Precio de compra
		    							Integer.parseInt(fields[4]),		//Número de páginas
		    							Integer.parseInt(fields[5]),		//Calificación
		    							new SimpleDateFormat("yyyy-MM-dd").parse(fields[6]),	//Fecha de publicacion
		    							fields[7],							//Relevancia
		    							fields[8]);							//Tema
		    		aux.add(lib);
	    		}
	    		line = br.readLine();
	    	}
	        br.close();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo LibroBD.CSV", "Info",JOptionPane.NO_OPTION);
	    }
	    
	    try {
	    	br =new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/VideoBD.csv"));
	    	br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (null!=line) {
	    		String [] fields = line.split(";");
	    		if(sus.getWishlist().contains(Integer.parseInt(fields[0]))) {
	    			Video vid = new Video(Integer.parseInt(fields[0]),		//ID
	    				  			  fields[1],							//Título
	    				  			  Double.parseDouble(fields[2]),		//Costo
	    				  			  Integer.parseInt(fields[3]),		//Duración
	    				  			  Integer.parseInt(fields[4]),		//Calificación
	    				  			  new SimpleDateFormat("yyyy-MM-dd").parse(fields[5]),	//Fecha de publicacion
	    				  			  fields[6],							//Relevancia
	    				  			  fields[7]);							//Tema
	    			
	    			aux.add(vid);
	    		}

	    		line = br.readLine();
	    	}
	        br.close();
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "No se encontró el archivo VideoBD.CSV", "Info",JOptionPane.NO_OPTION);
	    }
	      
	    return aux;
	}
	
	public static void agregarMaterialAWishlist(Suscriptor sus, String id) {
		String aux = "";
		boolean idEnWishlist = false;
		BufferedReader br = null;
		BufferedWriter bw = null;
	    try {
	    	br = new BufferedReader(new FileReader("src/main/java/frsf/isi/died/tp/archivos/UsuarioBD.csv"));
	    	aux = br.readLine();		//exceptúa de la lectura la cabecera del CSV
	    	String line = br.readLine();
	    	while (line!=null) {
	    		aux += "\n" + line;
	    		String [] fields = line.split(";");
	    		if(!idEnWishlist && fields[0].equals(sus.getNombre())) {
	    			for(int i = 1; i < fields.length ; i++) {
	    				if(fields[i].equals(id)) {
	    					idEnWishlist = true;
	    					JOptionPane.showMessageDialog(null, "El elemento seleccionado ya está en su Wishlist", "Info",JOptionPane.NO_OPTION);
	    					i=fields.length;
	    				}
	    			}
	    			
	    			if(!idEnWishlist) {
	    				aux += ";" + id.toString();
	    				idEnWishlist=true;
	    			}
	    		}
	    		
	    		line = br.readLine();
	    	}
	        br.close();
	        
	        try {
				bw = new BufferedWriter(new FileWriter("src/main/java/frsf/isi/died/tp/archivos/UsuarioBD.csv"));
				bw.write(aux);
				bw.close();
	        }
	        catch (IOException e1){
		        JOptionPane.showMessageDialog(null, "No se encontró el archivo UsuarioBD.CSV", "Info",JOptionPane.NO_OPTION);
	        }
	        
			Integer numericId = new Integer(Integer.parseInt(id));
			if (!sus.getWishlist().contains(numericId)) {
				sus.addToWishlist(numericId);
				JOptionPane.showMessageDialog(null, "El archivo se añadió correctamente a su Wishlist", "Info",JOptionPane.NO_OPTION);
			}
			
	    }
	    catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, "No se encontró el archivo UsuarioBD.CSV", "Info",JOptionPane.NO_OPTION);
	    }
	    
	    
	}
	
}

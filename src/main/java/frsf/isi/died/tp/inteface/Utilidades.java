package frsf.isi.died.tp.inteface;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Utilidades {
	static boolean validarCriterios(String titulo,String calificacion,String fDesde,String fHasta) {
		if(!calificacion.isEmpty()) {
			try {
		    	Integer calif = Integer.parseInt(calificacion);
		    	if(calif<0 || calif>100) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Calificación\" debe ser un número entre 0 y 100", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para \"Calificación\" es incorrecto", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}

	    try {
	    	new SimpleDateFormat("yyyy-MM-dd").parse(fDesde);
		} catch (ParseException e1) {
	        JOptionPane.showMessageDialog(null, "El valor especificado para \"Fecha Desde\" es incorrecto", "Info",JOptionPane.NO_OPTION);
	        return false;
		}
	    try {
			new SimpleDateFormat("yyyy-MM-dd").parse(fHasta);
		} catch (ParseException e1) {
	        JOptionPane.showMessageDialog(null, "El valor especificado para \"Fecha Hasta\" es incorrecto", "Info",JOptionPane.NO_OPTION);
	        return false;
		}
		
		return true;
	}
	
	static boolean validarCriteriosDeLibro(String paginas,String fecha,String precioCompra, String costo) {
		if(!paginas.isEmpty()) {
			try {
		    	Integer pag = Integer.parseInt(paginas);
		    	if(pag<1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Páginas\" debe ser un número mayor a 0", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para \"Páginas\" es incorrecto (únicamente se aceptan números)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especificó el número de \"Páginas\" del libro", "Info",JOptionPane.NO_OPTION);
		}
		
		if(!precioCompra.isEmpty()) {
			try {
		    	Double precioComp = Double.parseDouble(precioCompra);
		    	if(precioComp<0.1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Precio de compra\" debe ser un número mayor o igual a 0.1", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para precio de compra es incorrecto (únicamente se aceptan números con/sin coma)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especificó el precio de compra del libro", "Info",JOptionPane.NO_OPTION);
		}
		
		if(!costo.isEmpty()) {
			try {
		    	Double cost = Double.parseDouble(costo);
		    	if(cost<0.1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Costo\" debe ser un número mayor o igual a 0.1", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para costo es incorrecto (únicamente se aceptan números con/sin coma)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especificó el precio de compra del libro", "Info",JOptionPane.NO_OPTION);
		}
		
	    try {
	    	new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e1) {
	        JOptionPane.showMessageDialog(null, "El valor especificado para \"Fecha de publicación\" es incorrecto", "Info",JOptionPane.NO_OPTION);
	        return false;
		}
		
		return true;
	}

	public static boolean validarCriteriosDeVideo(String duracion, String fecha, String costo) {
		if(!duracion.isEmpty()) {
			try {
		    	Integer pag = Integer.parseInt(duracion);
		    	if(pag<1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Duración\" debe ser un número mayor a 0", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para \"Duración\" es incorrecto (únicamente se aceptan números)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especificó el número de \"Páginas\" del libro", "Info",JOptionPane.NO_OPTION);
		}
		
		if(!costo.isEmpty()) {
			try {
		    	Double cost = Double.parseDouble(costo);
		    	if(cost<0.1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Costo\" debe ser un número mayor o igual a 0.1", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para costo es incorrecto (únicamente se aceptan números con/sin coma)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especificó el precio de compra del libro", "Info",JOptionPane.NO_OPTION);
		}
		
	    try {
	    	new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e1) {
	        JOptionPane.showMessageDialog(null, "El valor especificado para \"Fecha de publicación\" es incorrecto", "Info",JOptionPane.NO_OPTION);
	        return false;
		}
		
		return true;
	}
}

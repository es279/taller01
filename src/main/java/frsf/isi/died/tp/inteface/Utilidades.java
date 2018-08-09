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
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Calificaci�n\" debe ser un n�mero entre 0 y 100", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para \"Calificaci�n\" es incorrecto", "Info",JOptionPane.NO_OPTION);
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
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"P�ginas\" debe ser un n�mero mayor a 0", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para \"P�ginas\" es incorrecto (�nicamente se aceptan n�meros)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especific� el n�mero de \"P�ginas\" del libro", "Info",JOptionPane.NO_OPTION);
		}
		
		if(!precioCompra.isEmpty()) {
			try {
		    	Double precioComp = Double.parseDouble(precioCompra);
		    	if(precioComp<0.1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Precio de compra\" debe ser un n�mero mayor o igual a 0.1", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para precio de compra es incorrecto (�nicamente se aceptan n�meros con/sin coma)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especific� el precio de compra del libro", "Info",JOptionPane.NO_OPTION);
		}
		
		if(!costo.isEmpty()) {
			try {
		    	Double cost = Double.parseDouble(costo);
		    	if(cost<0.1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Costo\" debe ser un n�mero mayor o igual a 0.1", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para costo es incorrecto (�nicamente se aceptan n�meros con/sin coma)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especific� el precio de compra del libro", "Info",JOptionPane.NO_OPTION);
		}
		
	    try {
	    	new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e1) {
	        JOptionPane.showMessageDialog(null, "El valor especificado para \"Fecha de publicaci�n\" es incorrecto", "Info",JOptionPane.NO_OPTION);
	        return false;
		}
		
		return true;
	}

	public static boolean validarCriteriosDeVideo(String duracion, String fecha, String costo) {
		if(!duracion.isEmpty()) {
			try {
		    	Integer pag = Integer.parseInt(duracion);
		    	if(pag<1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Duraci�n\" debe ser un n�mero mayor a 0", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para \"Duraci�n\" es incorrecto (�nicamente se aceptan n�meros)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especific� el n�mero de \"P�ginas\" del libro", "Info",JOptionPane.NO_OPTION);
		}
		
		if(!costo.isEmpty()) {
			try {
		    	Double cost = Double.parseDouble(costo);
		    	if(cost<0.1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Costo\" debe ser un n�mero mayor o igual a 0.1", "Info",JOptionPane.NO_OPTION);
			        return false;
		    	}
		    }catch (NumberFormatException e1) {
		        JOptionPane.showMessageDialog(null, "El valor especificado para costo es incorrecto (�nicamente se aceptan n�meros con/sin coma)", "Info",JOptionPane.NO_OPTION);
		        return false;
			}
		}
		else {
	        JOptionPane.showMessageDialog(null, "No especific� el precio de compra del libro", "Info",JOptionPane.NO_OPTION);
		}
		
	    try {
	    	new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e1) {
	        JOptionPane.showMessageDialog(null, "El valor especificado para \"Fecha de publicaci�n\" es incorrecto", "Info",JOptionPane.NO_OPTION);
	        return false;
		}
		
		return true;
	}
}

package frsf.isi.died.tp.inteface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Tema;

public class ActualizarLibroPanel extends JPanel {
	public ActualizarLibroPanel(Libro libAnt, MainFrame mf) {
		setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx=0;
		gb.gridy=0;
		gb.weightx=0.0;
		gb.weighty=0.0;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_END;
		gb.insets=new Insets(10, 10, 10, 5);
		
		JLabel lab1 = new JLabel("Título:");
		add(lab1,gb);
		JLabel lab2 = new JLabel("Número de páginas:");
		gb.gridy=1;
		add(lab2,gb);
		JLabel lab3 = new JLabel("Precio de compra:");
		gb.gridy=2;
		add(lab3,gb);
		JLabel lab4 = new JLabel("Costo:");
		gb.gridy=3;
		add(lab4,gb);
		JLabel lab5 = new JLabel("Fecha de publicación:");
		gb.gridy=4;
		add(lab5,gb);
		JLabel lab6 = new JLabel("Tema:");
		gb.gridy=5;
		add(lab6,gb);
		JLabel lab7 = new JLabel("Relevancia:");
		gb.gridy=6;
		add(lab7,gb);
		
		gb.gridx=1;
		gb.gridy=0;
		gb.weightx=1.0;
		gb.weighty=0.0;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.anchor=GridBagConstraints.CENTER;
		gb.insets=new Insets(10, 0, 10, 10);
		
		JTextField titulo = new JTextField();
		titulo.setText(libAnt.getTitulo());
		add(titulo,gb);
		
		JTextField numPaginas = new JTextField();
		numPaginas.setText(libAnt.getPaginas().toString());
		gb.gridy=1;
		add(numPaginas,gb);
		JTextField precioComp = new JTextField();
		precioComp.setText(libAnt.getPrecioCompra().toString());
		gb.gridy=2;
		add(precioComp,gb);
		JTextField costoComp = new JTextField();
		costoComp.setText(libAnt.getCosto().toString());
		gb.gridy=3;
		add(costoComp,gb);
		
		String [] dia = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
				 		 "21","22","23","24","25","26","27","28","29","30","31"};
		String [] mes = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		String [] anio = {"2000","2001","2002","2003","2004","2005","2006","2007","2008","2009",
				  		  "2010","2011","2012","2013","2014","2015","2016","2017","2018","2019",
				  		  "2020","2021","2022","2023","2024","2025"};
		
		gb.gridy=4;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_START;
		Calendar cal = new GregorianCalendar();
		cal.setTime(libAnt.getFechaPublicacion());
		JComboBox<String> diaPublic = new JComboBox<String>(dia);
		diaPublic.setSelectedItem((new Integer(cal.get(Calendar.DAY_OF_MONTH))).toString());
		JComboBox<String> mesPublic = new JComboBox<String>(mes);
		mesPublic.setSelectedItem((new Integer(cal.get(Calendar.MONTH)+1)).toString());
		JComboBox<String> anioPublic = new JComboBox<String>(anio);
		anioPublic.setSelectedItem((new Integer(cal.get(Calendar.YEAR))).toString());
		gb.gridy=4;
		gb.insets=new Insets(10, 0, 10, 5);
		add(diaPublic,gb);
		gb.insets=new Insets(10, 40, 10, 5);
		add(mesPublic,gb);
		gb.insets=new Insets(10, 80, 10, 5);
		add(anioPublic,gb);
		gb.insets=new Insets(10, 140, 10, 5);
		
		JComboBox<Tema> tema = new JComboBox<Tema>(Tema.values());
		tema.setSelectedItem(libAnt.getTema());
		gb.gridy=5;
		gb.insets=new Insets(10, 0, 10, 5);
		add(tema,gb);
		
		JComboBox<Relevancia> relevancia = new JComboBox<Relevancia>(Relevancia.values());
		relevancia.setSelectedItem(libAnt.getRelevancia());
		gb.gridy=6;
		add(relevancia,gb);
		
		JButton actualizarLibro = new JButton("Actualizar");
		actualizarLibro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fechaPublic = anioPublic.getSelectedItem()+"-"+mesPublic.getSelectedItem()+"-"+diaPublic.getSelectedItem();
				if(Utilidades.validarCriteriosDeLibro(numPaginas.getText(), fechaPublic, precioComp.getText(), costoComp.getText())) {
					Integer pag = Integer.parseInt(numPaginas.getText());
					libAnt.setPaginas(pag);
					Double preComp = Double.parseDouble(precioComp.getText());
					libAnt.setPrecioCompra(preComp);
					try {
						String fecha = anioPublic.getSelectedItem()+"-"+mesPublic.getSelectedItem()+"-"+diaPublic.getSelectedItem();
						libAnt.setFechaPublicacion(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
					} catch (ParseException e2) {}
					libAnt.setTitulo(titulo.getText());
					libAnt.setTema((Tema) tema.getSelectedItem());
					libAnt.setRelevancia((Relevancia) relevancia.getSelectedItem());
					
					if ( RegisterManager.actualizarLibro(libAnt) ) {
						mf.volverAlMenu();
					}
				}
				
			}
		});
		gb.gridy=7;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_END;
		add(actualizarLibro,gb);
	}

}

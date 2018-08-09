package frsf.isi.died.tp.inteface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Tema;
import frsf.isi.died.tp.modelo.usuarios.Suscriptor;

public class BuscarLibroPanel extends JPanel {
	
	private ArrayList<Libro> resultadoBusqueda;
	private Suscriptor usuario;
	
	public BuscarLibroPanel(Suscriptor sus, MainFrame mf) {
		this.usuario = sus;
		resultadoBusqueda = new ArrayList<Libro>();
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
		JLabel lab2 = new JLabel("Calificación:");
		gb.gridy=1;
		add(lab2,gb);
		JLabel lab3 = new JLabel("Tema:");
		gb.gridy=2;
		add(lab3,gb);
		JLabel lab4 = new JLabel("Fecha de publicación desde:");
		gb.gridy=3;
		add(lab4,gb);
		JLabel lab5 = new JLabel("Fecha de publicación hasta:");
		gb.gridy=4;
		add(lab5,gb);
		
		gb.gridx=1;
		gb.gridy=0;
		gb.weightx=1.0;
		gb.weighty=0.0;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.anchor=GridBagConstraints.CENTER;
		gb.insets=new Insets(10, 0, 10, 10);
		
		JTextField titulo = new JTextField();
		add(titulo,gb);
		
		JTextField calificacion = new JTextField();
		gb.gridy=1;
		add(calificacion,gb);
		
		JComboBox<Tema> tema = new JComboBox<Tema>(Tema.values());
		gb.gridy=2;
		add(tema,gb);
		
		
		String [] dia = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
						 "21","22","23","24","25","26","27","28","29","30","31"};
		String [] mes = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		String [] anio = {"2000","2001","2002","2003","2004","2005","2006","2007","2008","2009",
						  "2010","2011","2012","2013","2014","2015","2016","2017","2018","2019",
						  "2020","2021","2022","2023","2024","2025"};

		JComboBox<String> diaDesde = new JComboBox<String>(dia);
		diaDesde.setSelectedIndex(0);
		JComboBox<String> mesDesde = new JComboBox<String>(mes);
		mesDesde.setSelectedIndex(0);
		JComboBox<String> anioDesde = new JComboBox<String>(anio);
		anioDesde.setSelectedIndex(0);
		gb.gridy=3;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_START;
		gb.insets=new Insets(10, 0, 10, 5);
		add(diaDesde,gb);
		gb.insets=new Insets(10, 40, 10, 5);
		add(mesDesde,gb);
		gb.insets=new Insets(10, 80, 10, 5);
		add(anioDesde,gb);
		gb.insets=new Insets(10, 140, 10, 5);
		
		JComboBox<String> diaHasta = new JComboBox<String>(dia);
		diaHasta.setSelectedIndex(dia.length-1);
		JComboBox<String> mesHasta = new JComboBox<String>(mes);
		mesHasta.setSelectedIndex(mes.length-1);
		JComboBox<String> anioHasta = new JComboBox<String>(anio);
		anioHasta.setSelectedIndex(anio.length-1);
		
		gb.gridy=4;
		gb.insets=new Insets(10, 0, 10, 5);
		add(diaHasta,gb);
		gb.insets=new Insets(10, 40, 10, 5);
		add(mesHasta,gb);
		gb.insets=new Insets(10, 80, 10, 5);
		add(anioHasta,gb);
		gb.insets=new Insets(10, 140, 10, 5);

		MiModelo modelo = new MiModelo(new String[] {"ID","Título","Calificación","Precio","Fecha de publicación","Relevancia"},0);
		JTable tablaDeResultados = new JTable(modelo);
		JScrollPane barraDesplazamiento = new JScrollPane(tablaDeResultados);
		barraDesplazamiento.getViewport().setBackground(Color.WHITE);
		
		gb.gridy=5;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_START;
		gb.insets=new Insets(10, 0, 10, 5);
		JComboBox<String> tipoOrdenResultado = new JComboBox<String>(new String[] {"Título","Calificación","Precio","Fecha de publicación","Relevancia"});
		tipoOrdenResultado.setSelectedIndex(0);
		tipoOrdenResultado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ordenarArrayResultado((String) tipoOrdenResultado.getSelectedItem());
				actualizarTabla(modelo);
			}
		});
		add(tipoOrdenResultado,gb);
		
		
		gb.gridx=1;
		gb.gridy=6;
		gb.weightx=1.0;
		gb.weighty=1.0;
		gb.insets=new Insets(10, 0, 10, 5);
		gb.fill=GridBagConstraints.BOTH;
		add(barraDesplazamiento,gb);
		JButton generarArbol = new JButton("Generar Arbol");
		generarArbol.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tablaDeResultados.getSelectedRow() != -1) {
					boolean existe = TreeRegisterManager.existeArbolConID(resultadoBusqueda.get(tablaDeResultados.getSelectedRow()).getId());
					if (!existe) {
						mf.cambiar(new AgregarArbolMaterialPanel(mf, resultadoBusqueda.get(tablaDeResultados.getSelectedRow()).getId()));
					}
					else {
						JOptionPane.showMessageDialog(null, "El Libro seleccionado ya tiene un árbol asignado", "Info",JOptionPane.NO_OPTION);
					}
				}
			}
		});
		
		JButton actualizar = new JButton("Actualizar");
		actualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = tablaDeResultados.getSelectedRow();
				if(rowIndex != -1) {
					mf.cambiar( new ActualizarLibroPanel( resultadoBusqueda.get(rowIndex), mf) );
				}
				
			}
		});
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = tablaDeResultados.getSelectedRow();
				if(rowIndex != -1) {
					boolean exito = RegisterManager.eliminarLibro( (String) modelo.getValueAt(rowIndex, 0) ) ;
					if(exito) {
						modelo.removeRow(rowIndex);
						resultadoBusqueda.remove(rowIndex);
					}
					else {
						JOptionPane.showMessageDialog(null, "No se encontró el elemento seleccionado para su eliminación", "Info",JOptionPane.NO_OPTION);
					}
				}
			}
		});
		
		JButton agregarAWishlist = new JButton("Agregar a WishList");
		agregarAWishlist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterManager.agregarMaterialAWishlist( usuario, (String) modelo.getValueAt(tablaDeResultados.getSelectedRow(), 0)); 
				
			}
		});
		
		JButton buscar = new JButton("Buscar");
		buscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fechaDesde = anioDesde.getSelectedItem()+"-"+mesDesde.getSelectedItem()+"-"+diaDesde.getSelectedItem();
				String fechaHasta = anioHasta.getSelectedItem()+"-"+mesHasta.getSelectedItem()+"-"+diaHasta.getSelectedItem();
				
				if(Utilidades.validarCriterios(titulo.getText(),calificacion.getText(),fechaDesde,fechaHasta)) {
					resultadoBusqueda = RegisterManager.cargarLibrosConCriterios(titulo.getText(), calificacion.getText(), tema.getSelectedItem().toString(), fechaDesde, fechaHasta);
					ordenarArrayResultado((String) tipoOrdenResultado.getSelectedItem());
					actualizarTabla(modelo);
				}
			}
		});
		
		gb.gridy=7;
		gb.weightx=0.0;
		gb.weighty=0.0;
		gb.anchor=GridBagConstraints.LINE_END;
		gb.insets=new Insets(5, 0, 5, 10);
		gb.fill=GridBagConstraints.NONE;
		
		add(buscar,gb);
		gb.insets=new Insets(5, 0, 5, 97);
		add(agregarAWishlist,gb);
		gb.insets=new Insets(5, 0, 5, 253);
		add(actualizar,gb);
		gb.insets=new Insets(5, 0, 5, 355);
		add(eliminar,gb);
		gb.insets=new Insets(5, 0, 5, 446);
		add(generarArbol,gb);
	}
	
	private void actualizarTabla(DefaultTableModel m) {
		m.setRowCount(0);
		int numResultados = resultadoBusqueda.size();
		
		for (int i=0;i<numResultados;i++) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(resultadoBusqueda.get(i).getFechaPublicacion());
			String fechaPublicacion = String.format("%04d-%02d-%02d",cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
			
			String[] s = new String[6]; 
			s[0]=resultadoBusqueda.get(i).getId().toString();
			s[1]=resultadoBusqueda.get(i).getTitulo();
			s[2]=resultadoBusqueda.get(i).getCalificacion().toString();
			s[3]=String.format("%.2f", resultadoBusqueda.get(i).precio());
			s[4]=fechaPublicacion;
			s[5]=resultadoBusqueda.get(i).getRelevancia().toString();
			m.addRow(s);
		}
	}
	
	private void ordenarArrayResultado(String criterio) {
		if (criterio=="Título") {
			this.resultadoBusqueda.sort((m1,m2) -> m1.getTitulo().compareTo(m2.getTitulo()));
		} else if (criterio=="Calificación") {
			this.resultadoBusqueda.sort((m1,m2) -> m1.getCalificacion().compareTo(m2.getCalificacion()));
		} else if (criterio=="Precio") {
			this.resultadoBusqueda.sort((m1,m2) -> m1.precio().compareTo(m2.precio()));
		} else if (criterio=="Fecha de publicación") {
			this.resultadoBusqueda.sort((m1,m2) -> m1.getFechaPublicacion().compareTo(m2.getFechaPublicacion()));
		} else if (criterio=="Relevancia") {
			this.resultadoBusqueda.sort((m1,m2) -> m1.getRelevancia().compareTo(m2.getRelevancia()));
		}
	}
	
}


class MiModelo extends DefaultTableModel{
	public MiModelo() {
		super();
	}
	
	public MiModelo(String[] strings, int i) {
		super(strings,i);
	}

	public boolean isCellEditable(int fila, int columna) {
		return false;
	}
}
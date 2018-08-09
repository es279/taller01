package frsf.isi.died.tp.inteface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Tema;

public class AgregarVideoPanel extends JPanel {
	public AgregarVideoPanel() {
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
		JLabel lab2 = new JLabel("Duración:");
		gb.gridy=1;
		add(lab2,gb);
		JLabel lab3 = new JLabel("Costo:");
		gb.gridy=2;
		add(lab3,gb);
		JLabel lab4 = new JLabel("Fecha de publicación:");
		gb.gridy=3;
		add(lab4,gb);
		JLabel lab5 = new JLabel("Relevancia:");
		gb.gridy=4;
		add(lab5,gb);
		JLabel lab6 = new JLabel("Tema:");
		gb.gridy=5;
		add(lab6,gb);
		
		gb.gridx=1;
		gb.gridy=0;
		gb.weightx=1.0;
		gb.weighty=0.0;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.anchor=GridBagConstraints.CENTER;
		gb.insets=new Insets(10, 0, 10, 10);
		
		JTextField titulo = new JTextField();
		add(titulo,gb);
		
		JTextField duracion = new JTextField();
		gb.gridy=1;
		add(duracion,gb);
		JTextField costoComp = new JTextField();
		gb.gridy=2;
		add(costoComp,gb);
		
		String [] dia = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
				 		 "21","22","23","24","25","26","27","28","29","30","31"};
		String [] mes = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		String [] anio = {"2000","2001","2002","2003","2004","2005","2006","2007","2008","2009",
				  		  "2010","2011","2012","2013","2014","2015","2016","2017","2018","2019",
				  		  "2020","2021","2022","2023","2024","2025"};
		
		gb.gridy=3;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_START;
		JComboBox<String> diaPublic = new JComboBox<String>(dia);
		JComboBox<String> mesPublic = new JComboBox<String>(mes);
		JComboBox<String> anioPublic = new JComboBox<String>(anio);		
		gb.gridy=3;
		gb.insets=new Insets(10, 0, 10, 5);
		add(diaPublic,gb);
		gb.insets=new Insets(10, 40, 10, 5);
		add(mesPublic,gb);
		gb.insets=new Insets(10, 80, 10, 5);
		add(anioPublic,gb);
		gb.insets=new Insets(10, 140, 10, 5);
		
		JComboBox<Relevancia> relevancia = new JComboBox<Relevancia>(Relevancia.values());
		relevancia.setSelectedIndex(1);
		gb.gridy=4;
		gb.insets=new Insets(10, 0, 10, 5);
		add(relevancia,gb);
		
		JComboBox<Tema> tema = new JComboBox<Tema>(Tema.values());
		tema.setSelectedIndex(1);
		gb.gridy=5;
		add(tema,gb);
		
		JButton agregarLibro = new JButton("Agregar");
		agregarLibro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fecha = anioPublic.getSelectedItem()+"-"+mesPublic.getSelectedItem()+"-"+diaPublic.getSelectedItem();
				if( Utilidades.validarCriteriosDeVideo(duracion.getText(), fecha,costoComp.getText()) ) {
					RegisterManager.guardarVideos(titulo.getText(), duracion.getText(), costoComp.getText(), fecha, (Relevancia) relevancia.getSelectedItem(), (Tema) tema.getSelectedItem());
				}
				
			}
		});
		gb.gridy=6;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_END;
		add(agregarLibro,gb);
	}
}

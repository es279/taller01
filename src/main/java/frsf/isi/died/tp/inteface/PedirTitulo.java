package frsf.isi.died.tp.inteface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import frsf.isi.died.tp.modelo.productos.Tema;

public class PedirTitulo extends JDialog{
	private String tituloPrincipal;
	public PedirTitulo() {
		this.setTitle("Seleccción de usuario");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setResizable(false);
		this.setSize(250, 150);
		
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx=0;
		gb.gridy=0;
		gb.anchor=GridBagConstraints.CENTER;
		
		JLabel lab = new JLabel("Ingrese el titulo del material:");
		this.add(lab,gb);
		
		gb.gridy=1;
		gb.weightx=1;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.insets=new Insets(10, 35, 0, 35);
		
		JTextField titulo = new JTextField();
		this.add(titulo,gb);
		
		gb.gridy = 2;
		gb.weightx=0;
		gb.fill=GridBagConstraints.NONE;
		gb.insets=new Insets(10, 0, 0, 0);
		
		JButton seleccionButton = new JButton("Siguiente");
		this.add(seleccionButton,gb);
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		seleccionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tituloPrincipal = titulo.getText();
				setVisible(false);
			}
		});
		
		this.setVisible(true);
	}
	
	public String getTitulo() {
		return this.tituloPrincipal;
	}
	
}

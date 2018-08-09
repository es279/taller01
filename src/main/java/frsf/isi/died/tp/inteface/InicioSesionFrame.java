package frsf.isi.died.tp.inteface;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import frsf.isi.died.tp.modelo.usuarios.Suscriptor;

public class InicioSesionFrame extends JFrame {
	public InicioSesionFrame() {
		/**
		 * @return Esta ventana simula un inicio de sesión listando todos los usuarios registrados y permitiendo acceder a ellos sin la autenticación correspondiente
		 */
		ArrayList<Suscriptor> aux = RegisterManager.cargarUsuarios();
		Suscriptor[] sus = new Suscriptor[aux.size()];
		for ( int i=0; i<aux.size(); i++ ) {
			sus[i] = aux.get(i);
		}
		
		this.setTitle("Seleccción de usuario");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setResizable(false);
		this.setSize(250, 150);
		
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx=0;
		gb.gridy=0;
		gb.weightx=1;
		gb.anchor=GridBagConstraints.CENTER;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.insets=new Insets(0, 35, 0, 35);
		
		JComboBox<Suscriptor> usuariosCombo = new JComboBox<Suscriptor>(sus);
		this.add(usuariosCombo,gb);
		
		gb.gridy = 1;
		gb.weightx=0;
		gb.fill=GridBagConstraints.NONE;
		gb.insets=new Insets(10, 0, 0, 0);
		
		JButton iniciarButton = new JButton("Iniciar Sesión");
		this.add(iniciarButton,gb);
		iniciarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame f = new MainFrame((Suscriptor) usuariosCombo.getSelectedItem(), getLocation());
				dispose();
			}
		});
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		InicioSesionFrame f = new InicioSesionFrame();
	}
}

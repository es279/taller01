package frsf.isi.died.tp.inteface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import frsf.isi.died.tp.modelo.productos.Tema;
import frsf.isi.died.tp.modelo.usuarios.Suscriptor;

public class SeleccionDeTemaFrame extends JDialog {
	private Tema temaSeleccionado;
	public SeleccionDeTemaFrame() {
		this.setTitle("Seleccción de usuario");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		
		JComboBox<Tema> temasCombo = new JComboBox<Tema>(Tema.values());
		this.add(temasCombo,gb);
		
		gb.gridy = 1;
		gb.weightx=0;
		gb.fill=GridBagConstraints.NONE;
		gb.insets=new Insets(10, 0, 0, 0);
		
		JButton seleccionButton = new JButton("Seleccionar");
		this.add(seleccionButton,gb);
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		seleccionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				temaSeleccionado = (Tema) temasCombo.getSelectedItem();
				setVisible(false);
			}
		});
		
		this.setVisible(true);
	}
	
	public Tema getTema() {
		return this.temaSeleccionado;
	}
	
}

package frsf.isi.died.tp.inteface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.usuarios.Suscriptor;

public class WishListPanel extends JPanel {
	private Suscriptor usuario;
	private ArrayList<MaterialCapacitacion> wishList;
	
	public WishListPanel(Suscriptor sus) {
		this.usuario = sus;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx=0;
		gb.gridy=0;
		gb.weightx=1;
		gb.weighty=1;
		gb.fill=GridBagConstraints.BOTH;
		gb.insets = new Insets(15, 15, 15, 15);
		
		MiModelo mm = new MiModelo(new String[] {"ID","Tipo","Titulo","Relevancia","Clificación","Precio"}, 0);
		JTable resultTable = new JTable(mm);
		
		JScrollPane barraDesplazamiento = new JScrollPane(resultTable);
		barraDesplazamiento.getViewport().setBackground(Color.WHITE);
		this.add(barraDesplazamiento,gb);
		
		this.wishList = Monticulo.crearMont( RegisterManager.cargarWishList(this.usuario) );
		this.llenarTabla(mm);
	}
	
	private void llenarTabla(DefaultTableModel model) {
		for (MaterialCapacitacion x : wishList) {
			String[] s = new String [6];
			s[0] = x.getId().toString();
			if (x.esLibro())
				s[1] = "Libro";
			else
				s[1] = "Vídeo";
			s[2] = x.getTitulo();
			s[3] = x.getRelevancia().toString();
			s[4] = x.getCalificacion().toString();
			s[5] = x.precio().toString();
			
			model.addRow(s);
		}
	}
}


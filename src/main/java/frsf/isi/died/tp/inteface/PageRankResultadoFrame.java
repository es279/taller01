package frsf.isi.died.tp.inteface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.*;

import frsf.isi.died.tp.estructuras.Vertice;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class PageRankResultadoFrame extends JFrame {
	public PageRankResultadoFrame( List<Vertice<MaterialCapacitacion>> elementos, Double [] pageRank) {
		this.setSize(400, 480);
		this.setTitle("Resultado");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		MiModelo modelo = new MiModelo(new String[] {"Nombre del Material", "PageRank"},0);
		
		JTable result = new JTable(modelo);
		JScrollPane scroll = new JScrollPane(result);
		scroll.getViewport().setBackground(Color.WHITE);
		this.add(scroll, BorderLayout.CENTER);
		for (int i=0; i<elementos.size(); i++) {
			String[] aux = new String[] {((MaterialCapacitacion)elementos.get(i).getValor()).getTitulo(), pageRank[i].toString()};
			modelo.addRow(aux);
		}
	}
}

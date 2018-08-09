package frsf.isi.died.tp.inteface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import frsf.isi.died.tp.estructuras.Arista;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.estructuras.Vertice;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.Graficos.*;

public class RelacionarMaterialesPanel extends JPanel{

	public RelacionarMaterialesPanel(Integer width, List<Vertice<MaterialCapacitacion>> vertices) {
		List<Vertice<MaterialCapacitacion>> verticesDelTema = vertices;
		int tamanio = verticesDelTema.size();
		String[] listaNombreNodos=new String[tamanio];

		for(int i = 0; i<tamanio;i++) {
			listaNombreNodos[i]=((MaterialCapacitacion) verticesDelTema.get(i).getValor()).getTitulo();
		}
		
		setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx=0;
		gb.gridy=0;
		gb.gridwidth=6;
		gb.weightx=1.0;
		gb.weighty=1.0;
		gb.fill=GridBagConstraints.BOTH;
		gb.anchor=GridBagConstraints.CENTER;
		gb.insets=new Insets(0, 0, 0, 0);
		
		Grafica panelGrafico = Grafica.GraficarNodos((ArrayList<Vertice<MaterialCapacitacion>>) verticesDelTema, width);
		panelGrafico.setBackground(Color.DARK_GRAY);
		panelGrafico.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));
		JScrollPane barraDesplazamiento = new JScrollPane(panelGrafico);
		barraDesplazamiento.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));
		add(barraDesplazamiento,gb);
		gb.gridx=0;
		gb.gridy=1;
		gb.gridwidth=1;
		gb.weightx=0.0;
		gb.weighty=0.0;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_END;
		gb.insets=new Insets(5, 10, 0, 5);
		
		JLabel labelNodoInicial = new JLabel("Nodo inicial:");
		add(labelNodoInicial,gb);
		
		gb.gridx=2;
		JLabel labelNodoFinal = new JLabel("Nodo final:");
		add(labelNodoFinal,gb);
		
		gb.gridx=4;
		JLabel labelDistancia = new JLabel("Distancia entre nodos:");
		add(labelDistancia,gb);
		
		gb.gridy=2;
		gb.gridx=0;
		JLabel labelFactorD = new JLabel("Factor \"d\":");
		add(labelFactorD,gb);
		
		gb.gridx=2;
		JLabel labelFactorE = new JLabel("Factor \"e\":");
		add(labelFactorE,gb);
		
		gb.gridy=1;
		gb.gridx=1;
		gb.anchor=GridBagConstraints.LINE_START;
		gb.insets=new Insets(5, 5, 0, 10);
		JComboBox<String> comboNodoInicial = new JComboBox<String>(listaNombreNodos);
		add(comboNodoInicial,gb);
		
		gb.gridx=3;
		JComboBox<String> comboNodoFinal = new JComboBox<String>(listaNombreNodos);
		add(comboNodoFinal,gb);
		
		gb.gridx=5;
		JTextField distanciaEntreNodos = new JTextField();
		distanciaEntreNodos.setPreferredSize(new Dimension(70, 25));
		add(distanciaEntreNodos,gb);
		
		gb.gridx=1;
		gb.gridy=2;
		JTextField factorD = new JTextField();
		factorD.setPreferredSize(new Dimension(70, 25));
		add(factorD,gb);
		
		gb.gridx=3;
		JTextField factorE = new JTextField();
		factorE.setPreferredSize(new Dimension(70, 25));
		add(factorE,gb);
		
		gb.gridx=0;
		gb.gridy=4;
		gb.gridwidth=6;
		gb.anchor=GridBagConstraints.LINE_END;
		gb.insets=new Insets(5, 5, 5, 10);
		JButton mostrarCaminos = new JButton("Mostrar los posibles caminos");
		mostrarCaminos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<ArrayList<Vertice<MaterialCapacitacion>>> caminosFinales = new ArrayList<ArrayList<Vertice<MaterialCapacitacion>>>();
				Vertice<MaterialCapacitacion> ini = new Vertice<MaterialCapacitacion>();
				Vertice<MaterialCapacitacion> fin = new Vertice<MaterialCapacitacion>();
				
				for (Vertice<MaterialCapacitacion> v : verticesDelTema) {
					if(v.getValor().getTitulo().equals(comboNodoInicial.getSelectedItem())) {
						ini = v;
					}
					if(v.getValor().getTitulo().equals(comboNodoFinal.getSelectedItem())) {
						fin = v;
					}
				}
				Grafo graf = new Grafo<MaterialCapacitacion>();
				graf.setVertices(verticesDelTema);
				graf.setAristas(panelGrafico.getVectorAristasMaterial());
				caminosFinales = graf.todosLosCaminos(ini, fin);
				
				String [] resultado = new String[caminosFinales.size()];
				
				for (int arra = 0; arra < caminosFinales.size(); arra++) {
					resultado[arra] = caminosFinales.get(arra).get(0).getValor().getTitulo();
					
					for (int vert=1; vert< caminosFinales.get(arra).size(); vert++) {
						resultado[arra] += " --> " + caminosFinales.get(arra).get(vert).getValor().getTitulo();
					}
					System.out.println(resultado[arra]);
				}
				ResultadoList resultFrame = new ResultadoList(resultado);
			}
		});
		
		add(mostrarCaminos,gb);
		
		gb.insets=new Insets(5, 5, 0, 220);
		JButton mostrarCaminosDistanciaN = new JButton("Mostrar los posibles caminos con distancia");
		mostrarCaminosDistanciaN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int num = Integer.parseInt(distanciaEntreNodos.getText());
					if (num > -1) {
						List<ArrayList<Vertice<MaterialCapacitacion>>> caminosFinales = new ArrayList<ArrayList<Vertice<MaterialCapacitacion>>>();
						Vertice<MaterialCapacitacion> ini = new Vertice<MaterialCapacitacion>();
						Vertice<MaterialCapacitacion> fin = new Vertice<MaterialCapacitacion>();
						
						for (Vertice<MaterialCapacitacion> v : verticesDelTema) {
							if(v.getValor().getTitulo().equals(comboNodoInicial.getSelectedItem())) {
								ini = v;
							}
							if(v.getValor().getTitulo().equals(comboNodoFinal.getSelectedItem())) {
								fin = v;
							}
						}
						Grafo graf = new Grafo<MaterialCapacitacion>();
						graf.setVertices(verticesDelTema);
						graf.setAristas(panelGrafico.getVectorAristasMaterial());
						caminosFinales = graf.todosLosCaminos(ini, fin, num);
						
						String [] resultado = new String[caminosFinales.size()];
						
						for (int arra = 0; arra < caminosFinales.size(); arra++) {
							resultado[arra] = caminosFinales.get(arra).get(0).getValor().getTitulo();
							
							for (int vert=1; vert< caminosFinales.get(arra).size(); vert++) {
								resultado[arra] += " --> " + caminosFinales.get(arra).get(vert).getValor().getTitulo();
							}
							System.out.println(resultado[arra]);
						}
						ResultadoList resultFrame = new ResultadoList(resultado);
					}
					else {
						JOptionPane.showMessageDialog(null, "El valor especificado para \"Distancia entre nodos\" debe ser un número mayor o igual a 0 (cero)", "Info",JOptionPane.NO_OPTION);
					}
					
				}
				catch(NumberFormatException e1) {
			        JOptionPane.showMessageDialog(null, "El valor especificado para \"Distancia entre nodos\" debe ser un número entero", "Info",JOptionPane.NO_OPTION);
				}
				
			}
		});
		add(mostrarCaminosDistanciaN,gb);
		
		gb.insets=new Insets(5, 5, 0, 508);
		JButton mostrarPageRank = new JButton("Generar PageRank");
		mostrarPageRank.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double factD = Double.parseDouble(factorD.getText());
					if ((factD > 0) && (factD < 1)) {
						try {
							double factE = Double.parseDouble(factorE.getText());
							if (factE > 0) {
								Double [] pageRanks = Grafica.PageRank( verticesDelTema,panelGrafico.getVectorAristasMaterial(), factD, factE);
								PageRankResultadoFrame rf = new PageRankResultadoFrame(verticesDelTema, pageRanks);
							}
							else {
								JOptionPane.showMessageDialog(null, "El valor especificado para \"Factor e\" puede ser únicamente un número con o sin punto decimal y mayor a 0 (cero)", "Info",JOptionPane.NO_OPTION);
							}
						}
						catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null, "El valor especificado para \"Factor e\" puede ser únicamente un número con o sin punto decimal y mayor a 0 (cero)", "Info",JOptionPane.NO_OPTION);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "El valor especificado para \"Factor d\" debe ser un número mayor a 0 (cero) y menor a 1 (uno)", "Info",JOptionPane.NO_OPTION);
					}
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "El valor especificado para \"Factor d\" puede ser únicamente un número con o sin punto decimal, mayor a 0 (cero) y menor a 1 (uno)", "Info",JOptionPane.NO_OPTION);
				}
				
				
			}
		});
		add(mostrarPageRank,gb);
	}
	
}

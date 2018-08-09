package frsf.isi.died.tp.inteface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;

import frsf.isi.died.tp.estructuras.Vertice;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Tema;
import frsf.isi.died.tp.modelo.usuarios.Suscriptor;

public class MainFrame extends JFrame {
	private JPanel workSpace;
	private JPanel mainPanel;
	private JPanel defaultButtonsPanel;
	private JButton menuPrinc;
	private Suscriptor usuario;
	
	public MainFrame(Suscriptor item, Point location) {
		this.usuario = item;
		this.setLocation(location);
		this.setSize(800, 600);
		this.setTitle("Ventana principal");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		
		defaultButtonsPanel = new JPanel();
		defaultButtonsPanel.setLayout(new BorderLayout(5,5));
		this.add(defaultButtonsPanel, BorderLayout.SOUTH);
		
		Dimension buttonsDim = new Dimension(170, 30);
		menuPrinc = new JButton("Menú Principal");
		menuPrinc.setPreferredSize(new Dimension(130, 25));
		JButton agregarLibro= new JButton("Agregar Libro");
		agregarLibro.setPreferredSize(buttonsDim);
		JButton agregarVideo= new JButton("Agregar Vídeo");
		agregarVideo.setPreferredSize(buttonsDim);
		JButton buttonBuscarLibro= new JButton("Buscar Libro");
		buttonBuscarLibro.setPreferredSize(buttonsDim);
		JButton buttonBuscarVideo= new JButton("Buscar Vídeo");
		buttonBuscarVideo.setPreferredSize(buttonsDim);
		JButton wishList= new JButton("Ver WishList");
		wishList.setPreferredSize(buttonsDim);
		JButton relacionarMatCapa= new JButton("Relacionar Material");
		relacionarMatCapa.setPreferredSize(buttonsDim);
		JButton arbolBusqueda= new JButton("Búsqueda de Texto");
		arbolBusqueda.setPreferredSize(buttonsDim);
		
		menuPrinc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuPrinc.setVisible(false);
				workSpace.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		
		agregarLibro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPrinc.setVisible(true);
				workSpace=new AgregarLibroPanel();
				workSpace.setVisible(true);
				add(workSpace, BorderLayout.CENTER);
				mainPanel.setVisible(false);
			}
		});
		
		agregarVideo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPrinc.setVisible(true);
				workSpace=new AgregarVideoPanel();
				workSpace.setVisible(true);
				add(workSpace, BorderLayout.CENTER);
				mainPanel.setVisible(false);
			}
		});
		
		buttonBuscarLibro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPrinc.setVisible(true);
				workSpace=new BuscarLibroPanel(usuario,(MainFrame)((JButton) e.getSource()).getRootPane().getParent());
				workSpace.setVisible(true);
				add(workSpace, BorderLayout.CENTER);
				mainPanel.setVisible(false);
			}
		});
		
		buttonBuscarVideo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPrinc.setVisible(true);
				workSpace=new BuscarVideoPanel(usuario,(MainFrame)((JButton) e.getSource()).getRootPane().getParent());
				workSpace.setVisible(true);
				add(workSpace, BorderLayout.CENTER);
				mainPanel.setVisible(false);
			}
		});
		
		wishList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPrinc.setVisible(true);
				workSpace=new WishListPanel(usuario);
				workSpace.setVisible(true);
				add(workSpace, BorderLayout.CENTER);
				mainPanel.setVisible(false);
			}
		});
		
		relacionarMatCapa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPrinc.setVisible(true);
				Tema tem = null;
				SeleccionDeTemaFrame seleccion = new SeleccionDeTemaFrame();
				tem = seleccion.getTema();
				if(tem!=null) {
					List<Vertice<MaterialCapacitacion>> vertices = RegisterManager.cargarVerticesDeTema(tem.toString());
					if (vertices.size() > 0) {
						workSpace=new RelacionarMaterialesPanel(((MainFrame)((JButton) e.getSource()).getRootPane().getParent()).getWidth(), vertices);
						workSpace.setVisible(true);
						add(workSpace, BorderLayout.CENTER);
						mainPanel.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "El tema \"" + tem.toString() + "\" no tiene materiales asignados aun", "Info",JOptionPane.NO_OPTION);
					}
				}
				
			}
		});
		
		arbolBusqueda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPrinc.setVisible(true);
				workSpace=new BuscarArbolPanel();
				workSpace.setVisible(true);
				add(workSpace, BorderLayout.CENTER);
				mainPanel.setVisible(false);
			}
		});
		
		GridBagConstraints sb = new GridBagConstraints();
		sb.fill=GridBagConstraints.NONE;
		sb.anchor=GridBagConstraints.WEST;
		sb.insets=new Insets(10, 10, 40, 10);
		defaultButtonsPanel.add(menuPrinc, BorderLayout.EAST);
		menuPrinc.setVisible(false);
		
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx=0;
		gb.gridy=0;
		gb.weightx=0.0;
		gb.weighty=0.0;
		gb.anchor=GridBagConstraints.CENTER;
		gb.insets=new Insets(5, 0, 0, 0);
		
		mainPanel.add(agregarLibro, gb);
		gb.gridy = 1;
		mainPanel.add(agregarVideo, gb);
		gb.gridy = 2;
		mainPanel.add(buttonBuscarLibro, gb);
		gb.gridy = 3;
		mainPanel.add(buttonBuscarVideo, gb);
		gb.gridy = 4;
		mainPanel.add(wishList, gb);
		gb.gridy = 5;
		mainPanel.add(relacionarMatCapa, gb);
		gb.gridy = 6;
		mainPanel.add(arbolBusqueda, gb);
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(defaultButtonsPanel, BorderLayout.SOUTH);
	}
	
	public void cambiar(JPanel nuevoPanel) {
		workSpace.setVisible(false);
		this.workSpace =nuevoPanel;
		workSpace.setVisible(true);
		add(workSpace, BorderLayout.CENTER);
	}
	
	public void volverAlMenu() {
		menuPrinc.setVisible(false);
		this.workSpace.setVisible(false);
		this.mainPanel.setVisible(true);
		this.workSpace = null;
	}
	
}

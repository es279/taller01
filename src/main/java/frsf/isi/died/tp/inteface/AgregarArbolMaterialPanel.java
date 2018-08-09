package frsf.isi.died.tp.inteface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import frsf.isi.died.tp.modelo.nodosDeMateriales.*;

public class AgregarArbolMaterialPanel extends JPanel {
	JTree tree;
	JTextField text;
	DefaultMutableTreeNode raiz;
	
	public AgregarArbolMaterialPanel(MainFrame mf, Integer idMaterial) {
		NodoTitulo t = new NodoTitulo();
		raiz = new DefaultMutableTreeNode(t);
		tree=new JTree(raiz);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx=0;
		gb.gridy=0;
		gb.gridheight=6;
		gb.insets=new Insets(10,10,0,10);
		gb.fill=GridBagConstraints.VERTICAL;
		ScrollPane sp = new ScrollPane();
		sp.add(tree);
		sp.setPreferredSize(new Dimension(220, 340));
		this.add(sp,gb);
		
		gb.gridy=0;
		gb.gridx=1;
		gb.gridheight=1;
		gb.anchor=GridBagConstraints.LINE_END;
		JLabel actualizarLabel = new JLabel("Actualizar atributo:");
		this.add(actualizarLabel,gb);
		
		gb.gridy=2;
		gb.insets=new Insets(180,10,0,10);
		JLabel tipoNodoLabel = new JLabel("Tipo de atributo:");
		this.add(tipoNodoLabel,gb);
		
		gb.gridy=3;
		gb.insets=new Insets(10,10,0,10);
		JLabel valorLabel = new JLabel("Valor del atributo:");
		this.add(valorLabel,gb);
		
		gb.gridx=2;
		gb.gridy=0;
		gb.weightx=1;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.anchor=GridBagConstraints.LINE_START;
		JTextField actualizarField = new JTextField();
		this.add(actualizarField,gb);
		
		gb.gridy=1;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_END;
		JButton actualizarButton = new JButton("Actualizar");
		this.add(actualizarButton,gb);
		
		gb.gridy=2;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_START;
		gb.insets=new Insets(180,10,0,10);
		JComboBox<TipoNodo> tipoNodoCombo = new JComboBox<TipoNodo>();
		tipoNodoCombo.setPreferredSize(new Dimension(140, 25));
		this.add(tipoNodoCombo,gb);
		
		gb.gridy=3;
		gb.weightx=0;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.insets=new Insets(10,10,0,10);
		JTextField valorField = new JTextField();
		this.add(valorField,gb);
		
		gb.gridy=4;
		gb.weightx=0;
		gb.fill=GridBagConstraints.NONE;
		gb.anchor=GridBagConstraints.LINE_END;
		JButton agregarButton = new JButton("Agregar atributo");
		this.add(agregarButton,gb);
		
		gb.gridy=5;
		JButton finalizarButton = new JButton("Finalizar");
		this.add(finalizarButton,gb);
		
		actualizarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!actualizarField.getText().isEmpty()) {
						((NodoMaterial) ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject()).setValor(actualizarField.getText());
						tree.repaint();
					}
				}
				catch (NullPointerException np) {}
			}
		});
		
		agregarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultMutableTreeNode aux1 = ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
					NodoMaterial padre = (NodoMaterial) aux1.getUserObject();
					NodoMaterial hijo = padre.agregaNodo(valorField.getText(), (TipoNodo) tipoNodoCombo.getSelectedItem());
					if(hijo!=null) {
						DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(hijo);
						aux1.add(nodoHijo);
						((DefaultTreeModel) tree.getModel()).reload();
						for (int i = 0; i < tree.getRowCount(); i++) {
					         tree.expandRow(i);
						}
					}
					else {
						System.out.println("Este atributo no admite duplicados");
					}
				}
				catch (NullPointerException np) {
					System.out.println("Seleccione un nodo al cual agregar el nodo hijo especificado");
				}
				

			}
		});
		
		finalizarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t.imprimirArbol();
				if ( (t.getValor() != null) && (!t.getValor().isEmpty()) ) {
					TreeRegisterManager.guardarArbol(t,idMaterial);
					mf.volverAlMenu();
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe especificar el título del material", "Info",JOptionPane.NO_OPTION);
				}
			}
		});
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				try {
					actualizarField.setText(((NodoMaterial) ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject()).getValor());
					tipoNodoCombo.removeAllItems();
					for (TipoNodo x : ((NodoMaterial) ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject()).tipo.tiposDeHijos()) {
						tipoNodoCombo.addItem(x);
					}
					
				} catch (NullPointerException e) {
					
				}
				
			}
		});
		
		PedirTitulo f = new PedirTitulo();
		if(!f.getTitulo().isEmpty()) {
			t.setValor(f.getTitulo());
			tree.repaint();
		}
		f.dispose();
	}
}

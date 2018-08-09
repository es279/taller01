package frsf.isi.died.tp.inteface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;

import frsf.isi.died.tp.modelo.nodosDeMateriales.*;

public class BuscarArbolPanel extends JPanel {
	private ArrayList<NodoTitulo> arboles;
	private JTextPane resultado;
	private Integer nivel0 = 1;
	private Integer nivel1 = 1;
	private Integer nivel2 = 1;
	private Integer nivel3 = 1;
	
	public BuscarArbolPanel() {
		arboles = TreeRegisterManager.cargarArboles();
		TipoNodo[] tipoDeNodosPosibles = TipoNodo.TITULO.tiposDescendientes();
		
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		JPanel criteriosPanel = new JPanel(new GridBagLayout());
		gb.gridx=0;
		gb.gridy=1;
		gb.anchor=GridBagConstraints.LINE_END;
		gb.insets=new Insets(10,10,0,0);
		JLabel crit1Label = new JLabel("Introduzca el criterio Nº1:");
		criteriosPanel.add(crit1Label, gb);
		
		gb.gridy=2;
		gb.insets=new Insets(5,10,0,0);
		JLabel crit2Label = new JLabel("Introduzca el criterio Nº2:");
		criteriosPanel.add(crit2Label, gb);

		gb.gridy=3;
		JLabel crit3Label = new JLabel("Introduzca el criterio Nº3:");
		criteriosPanel.add(crit3Label, gb);

		gb.gridy=4;
		JLabel crit4Label = new JLabel("Introduzca el criterio Nº4:");
		criteriosPanel.add(crit4Label, gb);

		gb.gridy=5;
		JLabel crit5Label = new JLabel("Introduzca el criterio Nº5:");
		criteriosPanel.add(crit5Label, gb);
		
		gb.gridx=1;
		gb.gridy=1;
		gb.weightx=1;
		gb.weighty=0;
		gb.anchor=GridBagConstraints.LINE_START;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.insets=new Insets(10,5,0,10);
		JTextField crit1TextField = new JTextField();
		criteriosPanel.add(crit1TextField, gb);
		
		gb.gridy=2;
		gb.insets=new Insets(5,5,0,10);
		JTextField crit2TextField = new JTextField();
		criteriosPanel.add(crit2TextField, gb);

		gb.gridy=3;
		JTextField crit3TextField = new JTextField();
		criteriosPanel.add(crit3TextField, gb);

		gb.gridy=4;
		JTextField crit4TextField = new JTextField();
		criteriosPanel.add(crit4TextField, gb);

		gb.gridy=5;
		JTextField crit5TextField = new JTextField();
		criteriosPanel.add(crit5TextField, gb);
		
		gb.gridx=2;
		gb.gridy=1;
		gb.weightx=0;
		gb.weighty=0;
		gb.anchor=GridBagConstraints.LINE_START;
		gb.fill=GridBagConstraints.NONE;
		gb.insets=new Insets(10,5,0,10);
		JComboBox<TipoNodo> crit1Combo = new JComboBox<>(tipoDeNodosPosibles);
		criteriosPanel.add(crit1Combo, gb);
		
		gb.gridy=2;
		gb.insets=new Insets(5,5,0,10);
		JComboBox<TipoNodo> crit2Combo = new JComboBox<>(tipoDeNodosPosibles);
		criteriosPanel.add(crit2Combo, gb);

		gb.gridy=3;
		JComboBox<TipoNodo> crit3Combo = new JComboBox<>(tipoDeNodosPosibles);
		criteriosPanel.add(crit3Combo, gb);

		gb.gridy=4;
		JComboBox<TipoNodo> crit4Combo = new JComboBox<>(tipoDeNodosPosibles);
		criteriosPanel.add(crit4Combo, gb);

		gb.gridy=5;
		JComboBox<TipoNodo> crit5Combo = new JComboBox<>(tipoDeNodosPosibles);
		criteriosPanel.add(crit5Combo, gb);
		
		gb.gridx=0;
		gb.gridy=0;
		gb.anchor=GridBagConstraints.CENTER;
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.insets=new Insets(10,10,0,10);
		this.add(criteriosPanel, gb);
		
		gb.gridx=0;
		gb.gridy=1;
		gb.weightx=1.0;
		gb.weighty=1.0;
		gb.fill=GridBagConstraints.BOTH;
		gb.insets=new Insets(5,10,0,10);
		this.resultado = new JTextPane();
		this.resultado.setEditable(false);
		JScrollPane jScroll = new JScrollPane(this.resultado);
		this.add(jScroll, gb);

		gb.gridy=2;
		gb.weightx=0;
		gb.weighty=0;
		gb.anchor=GridBagConstraints.LINE_END;
		gb.fill=GridBagConstraints.NONE;
		gb.insets=new Insets(5,10,10,10);
		JButton buscar = new JButton("Buscar");
		this.add(buscar, gb);
		
		buscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean crit1, crit2, crit3, crit4, crit5;
				resultado.setText("Los siguientes materiales coincidieron con los parámetros de la búsqueda:");
				
				for (NodoTitulo arb : arboles) {
					crit1 = true; crit2 = true; crit3 = true; crit4 = true; crit5 = true;
					if (!crit1TextField.getText().isEmpty()) {
						crit1 = arb.search(crit1TextField.getText(),(TipoNodo) crit1Combo.getSelectedItem());
					}
					if (!crit2TextField.getText().isEmpty()) {
						crit2 = arb.search(crit2TextField.getText(),(TipoNodo) crit2Combo.getSelectedItem());
					}
					if (!crit3TextField.getText().isEmpty()) {
						crit3 = arb.search(crit3TextField.getText(),(TipoNodo) crit3Combo.getSelectedItem());
					}
					if (!crit4TextField.getText().isEmpty()) {
						crit4 = arb.search(crit4TextField.getText(),(TipoNodo) crit4Combo.getSelectedItem());
					}
					if (!crit5TextField.getText().isEmpty()) {
						crit5 = arb.search(crit5TextField.getText(),(TipoNodo) crit5Combo.getSelectedItem());
					}
					
					if ( crit1 && crit2 && crit3 && crit4 && crit5 ) {
						agregarResultado(arb);
						arb.imprimirArbol();
					}
				}
				nivel0=1;
				
			}
		});
	}
	
	private void agregarLinea(Integer espacios, TipoNodo tipo, String contenido) {
		StyledDocument sd = resultado.getStyledDocument();
		
		SimpleAttributeSet flechaAtrib = new SimpleAttributeSet();
		StyleConstants.setForeground(flechaAtrib, Color.GRAY);
		flechaAtrib.addAttribute(StyleConstants.CharacterConstants.Bold, true);
		
		SimpleAttributeSet tipoAtrib = new SimpleAttributeSet();
		tipoAtrib.addAttribute(StyleConstants.CharacterConstants.Bold, true);
		tipoAtrib.addAttribute(StyleConstants.CharacterConstants.Underline, true);
		StyleConstants.setForeground(tipoAtrib, Color.GRAY);
		
		if (espacios==0) {
			try {
				sd.insertString(sd.getLength(), "\n   " + nivel0 + ".- ", null);
			} catch (BadLocationException e) { }
		} else if (espacios==1) {
			try {
				sd.insertString(sd.getLength(), "\n      " + nivel0 + "." + nivel1 + ".- ", null);
			} catch (BadLocationException e) { }
		} else if (espacios==2) {
			try {
				sd.insertString(sd.getLength(), "\n           " + nivel0 + "." + nivel1 + "." + nivel2 + ".- ", null);
			} catch (BadLocationException e) { }
		} else if (espacios==3) {
			try {
				sd.insertString(sd.getLength(), "\n                " + nivel0 + "." + nivel1 + "." + nivel2 + "." + nivel3 + ".- ", null);
			} catch (BadLocationException e) { }
		}
		
		try {
			sd.insertString(sd.getLength(), tipo.toString(), tipoAtrib);
		} catch (BadLocationException e) { }
		try {
			sd.insertString(sd.getLength(), " → ", flechaAtrib);
		} catch (BadLocationException e) { }
		
		if (contenido != null) {
			try {
				sd.insertString(sd.getLength(), contenido, null);
			} catch (BadLocationException e) { }
		}
		else {
			try {
				sd.insertString(sd.getLength(), "NULL", null);
			} catch (BadLocationException e) { }
		}
		
	}
	
	private void agregarResultado(NodoTitulo x) {
		this.agregarLinea(0, x.tipo, x.getValor());
		NodoMetadato nMetadato = x.getMetadatos();
		NodoResumen nResumen = x.getResumen();
		ArrayList<NodoCapitulo> capitulos = x.getCapitulos();
		
		if (nMetadato!=null) {
			this.agregarLinea(1, nMetadato.tipo, nMetadato.getValor());
			ArrayList<NodoHoja> autores = nMetadato.getAutor();
			NodoHoja nEditorial = nMetadato.getEditorial();
			NodoHoja nFechaPublicacion = nMetadato.getFechaPublicacion();
			NodoHoja nPalabrasClave = nMetadato.getPalabrasClave();
			
			if (autores.size() > 0) {
				for (NodoHoja nAutor : autores) {
					this.agregarLinea(2, nAutor.tipo, nAutor.getValor());
					nivel2++;
				}
			}
			if (nEditorial!=null) {
				this.agregarLinea(2, nEditorial.tipo, nEditorial.getValor());
				nivel2++;
			}
			if (nFechaPublicacion!=null) {
				this.agregarLinea(2, nFechaPublicacion.tipo, nFechaPublicacion.getValor());
				nivel2++;
			}
			if (nPalabrasClave!=null) {
				this.agregarLinea(2, nPalabrasClave.tipo, nPalabrasClave.getValor());
				nivel2++;
			}
			nivel1++;
			nivel2=1;
		}
		if (nResumen!=null) {
			this.agregarLinea(1, nResumen.tipo, nResumen.getValor());
			
			ArrayList<NodoHoja> parrafos = nResumen.getParrafos();
			if (parrafos.size() > 0) {
				for (NodoHoja nParrafo : parrafos) {
					this.agregarLinea(2, nParrafo.tipo, nParrafo.getValor());
					nivel2++;
				}
			}
			nivel1++;
			nivel2=1;
		}
		if (capitulos.size() > 0) {
			for (NodoCapitulo nCapitulo : capitulos) {
				if (nCapitulo!=null) {
					this.agregarLinea(1, nCapitulo.tipo, nCapitulo.getValor());
				}
				
				ArrayList<NodoSeccion> secciones = nCapitulo.getSecciones();
				NodoMetadatoDeCapitulo nMetadatoDeCapitulo = nCapitulo.getMetadatos();
				
				if (secciones.size() > 0) {
					for (NodoSeccion nSeccion : secciones) {
						this.agregarLinea(2, nSeccion.tipo, nSeccion.getValor());
						
						ArrayList<NodoHoja> parrafos = nSeccion.getParrafos();
						
						for (NodoHoja nParrafo : parrafos) {
							this.agregarLinea(3, nParrafo.tipo, nParrafo.getValor());
						}
					}
					nivel2++;
					nivel3=1;
				}
				if (nMetadatoDeCapitulo != null) {
					this.agregarLinea(2, nMetadatoDeCapitulo.tipo, nMetadatoDeCapitulo.getValor());
					
					ArrayList<NodoHoja> websRelacionadas = nMetadatoDeCapitulo.getWebsRelacionados();
					ArrayList<NodoHoja> websEjercicios = nMetadatoDeCapitulo.getWebsDeEjercicios();
					NodoHoja nPalabrasClave = nMetadatoDeCapitulo.getPalabrasClave();
					
					for (NodoHoja nWebRelacionada : websRelacionadas) {
						this.agregarLinea(3, nWebRelacionada.tipo, nWebRelacionada.getValor());
						nivel3++;
					}
					for (NodoHoja nWebEjercicio : websEjercicios) {
						this.agregarLinea(3, nWebEjercicio.tipo, nWebEjercicio.getValor());
						nivel3++;
					}
					if (nPalabrasClave != null) {
						this.agregarLinea(3, nPalabrasClave.tipo, nPalabrasClave.getValor());
						nivel3++;
					}
					nivel2++;
					nivel3=1;
				}
				nivel1++;
				nivel2=1;
				nivel3=1;
			}
		}
		nivel0++;
		nivel1=1;
		nivel2=1;
		nivel3=1;
	}
}

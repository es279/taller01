package frsf.isi.died.tp.inteface;

import java.awt.BorderLayout;

import javax.swing.*;

public class ResultadoList extends JFrame {
	
	public ResultadoList(String[] result) {
		this.setSize(400, 480);
		this.setTitle("Resultado");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JList<String> list = new JList<>(result);
		JScrollPane scroll = new JScrollPane(list);
		this.add(scroll, BorderLayout.CENTER);
	}
}

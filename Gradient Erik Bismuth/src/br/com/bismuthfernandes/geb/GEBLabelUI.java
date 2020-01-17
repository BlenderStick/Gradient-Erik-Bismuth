package br.com.bismuthfernandes.geb;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

public class GEBLabelUI extends BasicLabelUI {
	
	public static ComponentUI createUI(JComponent c){return new GEBLabelUI();}

	public GEBLabelUI() {
//		this.paintEnabledText(label, graphics, string, int, int);
	}
	
	public void paint(Graphics g, JComponent c){
		Graphics2D G = (Graphics2D) g.create();
		G.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		super.paint(G, c);
	}

}

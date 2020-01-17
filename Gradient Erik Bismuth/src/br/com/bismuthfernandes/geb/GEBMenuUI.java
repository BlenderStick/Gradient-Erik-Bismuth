package br.com.bismuthfernandes.geb;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;

public class GEBMenuUI extends BasicMenuUI {
	public static ComponentUI createUI(JComponent c){return new GEBMenuUI();}
	
	public void paint(Graphics g, JComponent c){
		Color oldColor = g.getColor();
		g.setColor(new Color(100, 100, 100));
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		g.setColor(oldColor);
	}
}

package br.com.bismuthfernandes.geb;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;

public class GEBPopupMenuUI extends BasicPopupMenuUI {
	public static ComponentUI createUI(JComponent c){return new GEBPopupMenuUI();}
	
	public void paint(Graphics g, JComponent c){
		Color oldColor = g.getColor();
		
		g.setColor(c.getBackground());
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		if(c.getBorder()!=null)
			c.getBorder().paintBorder(c, g, 0, 0, c.getWidth(), c.getHeight());
		g.setColor(c.getForeground());
		g.drawString(popupMenu.getLabel(), 0, 12);
		g.setColor(oldColor);
	}
}

package br.com.bismuthfernandes.geb;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuItemUI;

import sun.swing.SwingUtilities2;

public class GEBMenuItemUI extends BasicMenuItemUI {
	public static ComponentUI createUI(JComponent c){return new GEBMenuItemUI();}
	
	public void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGrag){
		Color oldColor = g.getColor();
		JMenuItem menu = (JMenuItem) c;
		ButtonModel model = menu.getModel();
		Color back;
		Color fore;
		Icon icon;
		
		if(model.isEnabled()){
			if(model.isArmed()){
				back = UIManager.getColor(getPropertyPrefix()+".selectionBackground");
				fore = UIManager.getColor(getPropertyPrefix()+".selectionForeground");
				icon = menu.getSelectedIcon();
			} else {
				back = menu.getBackground();
				fore = menu.getForeground();
				icon = menu.getIcon();
			}
		} else {
			back = UIManager.getColor(getPropertyPrefix()+".backgroundDisable");
			fore = UIManager.getColor(getPropertyPrefix()+".foregroundDisable");
			icon = menu.getDisabledIcon();
		}
		g.setColor(back);
		g.fillRect(0, 0, menu.getWidth(), menu.getHeight());
		g.setColor(fore);
		FontMetrics metrics = SwingUtilities2.getFontMetrics(c, g, c.getFont());
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		int alingX = 0;
		if(icon!=null){
			alingX = icon.getIconWidth()+menu.getIconTextGap();
			icon.paintIcon(c, g, 0, 0);
		}
		SwingUtilities2.drawString(c, g, menu.getText(), alingX, (c.getHeight()/2)+(metrics.getAscent()/2));
		
		
		g.setColor(oldColor);
	}
}

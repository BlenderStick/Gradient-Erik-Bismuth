package br.com.bismuthfernandes.geb;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Path2D;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

import sun.swing.DefaultLookup;

public class GEBComboBoxUI extends BasicComboBoxUI {
	
	public static ComponentUI createUI(JComponent c){return new GEBComboBoxUI();}
	

	private MouseListener comboBoxMouse;
	private ButtonModel comboBoxModel;
	private PopupMenuListener comboBoxPopupListener = new PopupMenuListener() {
		
		@Override public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
		
		@Override public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {comboBox.repaint();}
		
		@Override public void popupMenuCanceled(PopupMenuEvent e) {comboBox.repaint();}
	};
	
	
	private Path2D path;
	
	private MouseListener getComboBoxMouse(){
		if(comboBoxMouse==null)
			comboBoxMouse = new MouseListener() {

				@Override public void mouseClicked(MouseEvent e) {}

				@Override public void mouseEntered(MouseEvent e) {getComboButtonModel().setRollover(true);comboBox.repaint();}

				@Override public void mouseExited(MouseEvent e) {getComboButtonModel().setRollover(false);comboBox.repaint();}

				@Override public void mousePressed(MouseEvent e) {getComboButtonModel().setPressed(true);getComboButtonModel().setSelected(true);comboBox.repaint();}

				@Override public void mouseReleased(MouseEvent e) {getComboButtonModel().setPressed(false);comboBox.repaint();}
				
			};
		return comboBoxMouse;
	}
	private ButtonModel getComboButtonModel(){
		if(comboBoxModel==null)
			comboBoxModel=new ButtonModel() {
			private boolean selected;
			private boolean rollover;
			private boolean pressed;
			private boolean armed;
				
				@Override public Object[] getSelectedObjects() {return new Object[0];}
				
				@Override public void setSelected(boolean b) {selected = b;}
				
				@Override public void setRollover(boolean b) {rollover = b;}
				
				@Override public void setPressed(boolean b) {pressed = b;}
				
				@Override public void setMnemonic(int key) {}
				
				@Override public void setGroup(ButtonGroup group) {}
				
				@Override public void setEnabled(boolean b) {comboBox.setEnabled(b);}
				
				@Override public void setArmed(boolean b) {armed=b;}
				
				@Override public void setActionCommand(String s) {comboBox.setActionCommand(s);}
				
				@Override public void removeItemListener(ItemListener l) {comboBox.removeItemListener(l);}
				
				@Override public void removeChangeListener(ChangeListener l) {}
				
				@Override public void removeActionListener(ActionListener l) {comboBox.removeActionListener(l);}
				
				@Override public boolean isSelected() {return selected;}
				
				@Override public boolean isRollover() {return rollover;}
				
				@Override public boolean isPressed() {return pressed;}
				
				@Override public boolean isEnabled() {return comboBox.isEnabled();}
				
				@Override public boolean isArmed() {return armed;}
				
				@Override public int getMnemonic() {return 0;}
				
				@Override public String getActionCommand() {return comboBox.getActionCommand();}
				
				@Override public void addItemListener(ItemListener l) {comboBox.addItemListener(l);}
				
				@Override public void addChangeListener(ChangeListener l) {}
				
				@Override public void addActionListener(ActionListener l) {comboBox.addActionListener(l);}
			};
		return comboBoxModel;
	}

	protected static Color getNormalTop(){
		return UIManager.getColor("ComboBox.normalTop");
	}
	protected static Color getNormalBottom(){
		return UIManager.getColor("ComboBox.normalBottom");
	}
	protected static Color getPressTop(){
		return UIManager.getColor("ComboBox.pressTop");
	}
	protected static Color getPressBottom(){
		return UIManager.getColor("ComboBox.pressBottom");
	}
	protected static Color getActiveTop(){
		return UIManager.getColor("ComboBox.activeTop");
	}
	protected static Color getActiveBottom(){
		return UIManager.getColor("ComboBox.activeBottom");
	}
	protected static Color getFocusTop(){
		return UIManager.getColor("ComboBox.focusTop");
	}
	protected static Color getFocusBottom(){
		return UIManager.getColor("ComboBox.focusBottom");
	}
	protected static Color getDisableTop(){
		return UIManager.getColor("ComboBox.disableTop");
	}
	protected static Color getDisableBottom(){
		return UIManager.getColor("ComboBox.disableBottom");
	}
	protected static Color getTextNormal(){
		return UIManager.getColor("ComboBox.textNormal");
	}
	protected static Color getTextActive(){
		return UIManager.getColor("ComboBox.textActive");
	}
	protected static Color getTextPress(){
		return UIManager.getColor("ComboBox.textPress");
	}
	protected static Color getTextDisable(){
		return UIManager.getColor("ComboBox.textDisable");
	}
	
	public GEBComboBoxUI(){path = new Path2D.Double();}
	
	public void installDefaults(){
		super.installDefaults();
		comboBox.setOpaque(UIManager.getBoolean("CheckBox.opaque"));
	}
	
	public void installListeners(){
		super.installListeners();
		comboBox.addMouseListener(getComboBoxMouse());
		comboBox.addPopupMenuListener(comboBoxPopupListener);
	}
	
	public void uninstallListeners(){
		super.uninstallListeners();
		comboBox.removeMouseListener(getComboBoxMouse());
		comboBox.removePopupMenuListener(comboBoxPopupListener);
	}
	
	private synchronized void calculePath(Rectangle bounds){
		int roundRect = UIManager.getInt("ComboBox.borderRound");
		double x = bounds.getX();
		double y = bounds.getY();
		double w = bounds.getWidth()-1;
		double h = bounds.getHeight();
		double minX = Math.min(roundRect, w/2)+x;
		double minY = Math.min(roundRect, h/2)+y;
		path.reset();
		path.moveTo(x, minY);
		path.quadTo(x, y, minX, y);
		path.lineTo(x+w, y);
		path.lineTo(x+w, y+h);
		path.lineTo(minX, y+h);
		path.quadTo(x, y+h, x, (y+h)-minY);
		path.closePath();
		if(minY*2<comboBox.getFont().getSize()){
			padding = new Insets(0, (int) minX, 0, 0);
		} else {
			padding = null;
		}
	}
	
	protected JButton createArrowButton(){
		return new GEBArrowButton(SwingConstants.SOUTH, UIManager.getInt("ComboBox.arrowButtonDirection"));
	}
	
	

	@SuppressWarnings("unchecked")
	public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus){
		ListCellRenderer<Object> renderer = comboBox.getRenderer();
		Component c;

		if (hasFocus && !isPopupVisible(comboBox)) {
			c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, true, false);
		} else {
			c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
		}
		c.setFont(comboBox.getFont());
		if (hasFocus && !isPopupVisible(comboBox)) {
			c.setForeground(listBox.getSelectionForeground());
			c.setBackground(listBox.getSelectionBackground());
		} else {
			if (comboBox.isEnabled()) {
				c.setForeground(comboBox.getForeground());
				c.setBackground(comboBox.getBackground());
			} else {
				c.setForeground(DefaultLookup.getColor(comboBox, this, "ComboBox.disabledForeground", null));
				c.setBackground(DefaultLookup.getColor(comboBox, this, "ComboBox.disabledBackground", null));
			}
		}

		@SuppressWarnings("unused")
		int x = bounds.x, y = bounds.y, w = bounds.width, h = bounds.height;
		if (padding != null) {
			x = bounds.x + padding.left;
			y = bounds.y + padding.top;
			w = bounds.width - (padding.left + padding.right);
			h = bounds.height - (padding.top + padding.bottom);
		}
//		((JComponent) c).setOpaque(false);
//		currentValuePane.paintComponent(g, c, comboBox, x, y, w, h, shouldValidate);
		try{
			JLabel label = (JLabel) c;
			
			Graphics2D G = (Graphics2D) g.create();
			int insetsX = x;
			
			G.translate(x + 5, 0);
			if(label.getIcon()!=null){
				Icon icon = label.getIcon();
				insetsX += icon.getIconWidth()+label.getIconTextGap();
				int iconY = (h - icon.getIconHeight()) / 2;
				icon.paintIcon(c, G, 0, iconY);
				
			}
			G.setFont(label.getFont());
			if(comboBox.isEnabled()){
				if(comboBoxModel.isPressed()){
					G.setColor(getTextPress());
				} else if (comboBoxModel.isRollover()){
					G.setColor(getTextActive());
				} else {
					G.setColor(getTextNormal());
				}
			} else {
				G.setColor(getTextDisable());
			}
			G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int insetsY = ((h - label.getFont().getSize()) / 2 + y)+label.getFont().getSize();
			G.drawString(label.getText(), insetsX, insetsY);
			
		}finally{
			
		}
		
	}
	public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus){
		if(!path.getBounds().equals(bounds))
			calculePath(bounds);
		
		Graphics2D G = (Graphics2D) g.create();
		G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color top;
		Color bottom;
		if(comboBox.isEnabled()){
			if(isPopupVisible(comboBox)){
				top = getFocusTop();
				bottom = getFocusBottom();
			} else {
				if(getComboButtonModel().isPressed()){
					top = getPressTop();
					bottom = getPressBottom();
				}else if(getComboButtonModel().isRollover()){
					top = getActiveTop();
					bottom = getActiveBottom();
				}else{
					top = getNormalTop();
					bottom = getNormalBottom();
				}
			}
		} else {
			top = getDisableTop();
			bottom = getDisableBottom();
		}
		GradientPaint paint = new GradientPaint(0, 0, top, 0, bounds.height, bottom);
		G.setPaint(paint);
		
		
		G.fill(path);
		

		G.dispose();
//		super.paintCurrentValueBackground(g, bounds, hasFocus);
	}

	public boolean contains(JComponent c, int x, int y){
		int boundX = x-arrowButton.getX();
		int boundY = y-arrowButton.getY();
//		return (path.contains(x, y)) || (
//				(boundX <= c.getWidth()-path.getBounds().width && boundX >= 0) && 
//				(boundY <= c.getHeight() && boundY >= 0));
		return (path.contains(x, y)) || (arrowButton.contains(boundX, boundY));
	}
}

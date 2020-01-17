package br.com.bismuthfernandes.geb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicCheckBoxUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;

import br.com.bismuthfernandes.geometry.Geometry;
import sun.swing.SwingUtilities2;

public class GEBCheckBoxUI extends BasicCheckBoxUI{
	

	private RoundRectangle2D R = new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20);
	private Rectangle textRect = new Rectangle();
	private int[] lineX = new int[4];
	private int[] lineY = new int[4];
	
	public static ComponentUI createUI(JComponent c){return new GEBCheckBoxUI();}
	
	protected Color getSelectTop(){
		return UIManager.getColor(getPropertyPrefix()+"activeTop");
	}
	protected Color getSelectBottom(){
		return UIManager.getColor(getPropertyPrefix()+"activeBottom");
	}
	protected Color getPressTop(){
		return UIManager.getColor(getPropertyPrefix()+"pressTop");
	}
	protected Color getPressBottom(){
		return UIManager.getColor(getPropertyPrefix()+"pressBottom");
	}
	protected Color getNormalTop(){
		return UIManager.getColor(getPropertyPrefix()+"normalTop");
	}
	protected Color getNormalBottom(){
		return UIManager.getColor(getPropertyPrefix()+"normalBottom");
	}
	protected Color getDisableTop(){
		return UIManager.getColor(getPropertyPrefix()+"disableTop");
	}
	protected Color getDisableBottom(){
		return UIManager.getColor(getPropertyPrefix()+"disableBottom");
	}
	protected Color getDisableText(){
		return UIManager.getColor(getPropertyPrefix()+"disableText");
	}
	protected Color getMarkColor(){
		return UIManager.getColor(getPropertyPrefix()+"markColor");
	}
	
	public synchronized void paint(Graphics g, JComponent c){
		AbstractButton b = (AbstractButton) c;
		ButtonModel model = b.getModel();
		
		Graphics2D G = (Graphics2D) g.create();
		getDefaultIcon().paintIcon(c, G, 20, 20);
		
		Dimension size = c.getSize();
		
		Font f = c.getFont();
			g.setFont(f);
			FontMetrics fm = SwingUtilities2.getFontMetrics(c, g, f);
		
		Rectangle viewRect = new Rectangle(size);
		Rectangle iconRect = new Rectangle();
		Rectangle markRect = new Rectangle();
		Rectangle subViewRect = new Rectangle();
		
		Insets i = c.getInsets();
		viewRect.x += i.left;
		viewRect.y += i.top;
		viewRect.width -= (i.right + viewRect.x);
		viewRect.height -= (i.bottom + viewRect.y);
		
		int s = (int) Math.min(viewRect.width, viewRect.height);
		subViewRect.x = s - viewRect.x;
		subViewRect.y = viewRect.y;
		subViewRect.width = viewRect.width-s;
		subViewRect.height = viewRect.height;
		
		markRect.setFrame(viewRect.x, viewRect.y, s, s);
		
		if(R.getBounds() != viewRect){
			lineX = new int[]{i.left, (int) Geometry.regraDeTres(45, 100, s), s, (int) Geometry.regraDeTres(45, 100, s)};
			lineY = new int[]{s/3, s, i.top+(s/5), (int) (s-s/3.5)};
		}
		
		Icon altIcon = b.getIcon();
		
		String text = SwingUtilities.layoutCompoundLabel(
				c, fm, b.getText(), (altIcon != null ? altIcon : getDefaultIcon()),
				b.getVerticalAlignment(), b.getHorizontalAlignment(),
				b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
				subViewRect, iconRect, textRect, b.getIconTextGap());
		
		if (c.isOpaque()) {
			G.setColor(b.getBackground());
			G.fillRect(0, 0, size.width, size.height);
		}
		
		G.setColor(Color.BLACK);
		
		int round = UIManager.getInt(getPropertyPrefix()+"borderRound");
		if(!b.getSize().equals(R.getBounds().getSize()))
			if(R.getArcWidth()!=round || R.getArcHeight()!=round)
				R.setRoundRect(markRect.x, markRect.y, markRect.width, markRect.height, round, round);
			else
			R.setFrame(markRect);
		
		Color topColor;
		Color bottomColor;
		if(model.isEnabled()){
			if (model.isPressed()){
				topColor = getPressTop();
				bottomColor = getPressBottom();
			} else if(model.isRollover()){
				topColor = getSelectTop();
				bottomColor = getSelectBottom();
			} else {
				topColor = getNormalTop();
				bottomColor = getNormalBottom();
			}
		} else {
			topColor = getDisableTop();
			bottomColor = getDisableBottom();
		}
		G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint p = new GradientPaint(0, 0, topColor, 0, b.getHeight(), bottomColor);
		G.setPaint(p);
		G.fill(R);

		if(text != null) {
			View v = (View) c.getClientProperty(BasicHTML.propertyKey);
			if (v != null) {
				v.paint(g, textRect);
			} else {
				int mnemIndex = b.getDisplayedMnemonicIndex();
				if (model.isEnabled()) {
					// *** paint the text normally
					g.setColor(b.getForeground());
				} else {
					// *** paint the text disabled
//					g.setColor(getDisabledTextColor());
				}
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemIndex, textRect.x,
						textRect.y + fm.getAscent());
			}
			if (b.hasFocus() && b.isFocusPainted() && textRect.width > 0 && textRect.height > 0) {
				paintFocus(g, textRect, size);
			}
		}
//		G.setColor(Color.BLACK);
//		G.fillRect(0, 0, 20, 20);
		c.getBorder().paintBorder(c, g, c.getX(), c.getY(), c.getWidth(), c.getHeight());
		
		if(b.isSelected()){
			G.setColor(getMarkColor());
			G.fillPolygon(lineX, lineY, 4);
		}
		
		
		G.dispose();
	}
	
	public synchronized boolean contains(JComponent c, int x, int y){
		boolean markContain = R.contains(x, y);
		boolean textContain = textRect.contains(x, y);
		return markContain || textContain;
	}
	
	public void installDefaults(AbstractButton b){
		super.installDefaults(b);
		LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
	}
	
}

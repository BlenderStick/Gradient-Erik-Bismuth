package br.com.bismuthfernandes.geb;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;

import sun.swing.SwingUtilities2;

public class GEBToggleButtonUI extends BasicToggleButtonUI {
	public static ComponentUI createUI(JComponent c){return new GEBToggleButtonUI();}

	
	private RoundRectangle2D R = new RoundRectangle2D.Double();
	protected Color activeTop;
	protected Color activeBottom;
	protected Color selectTop;
	protected Color selectBottom;
	protected Color selectActiveTop;
	protected Color selectActiveBottom;
	protected Color pressTop;
	protected Color pressBottom;
	protected Color normalTop;
	protected Color normalBottom;
	protected Color disableTop;
	protected Color disableBottom;
	protected Color textNormal;
	protected Color textSelect;
	protected Color textPress;
	protected Color disableText;
	protected Color focus;
	protected Color focusColor;
	
	public void installDefaults(AbstractButton b){
		super.installDefaults(b);
		LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
	}

	protected Color getActiveTop(){
		activeTop = UIManager.getColor(getPropertyPrefix()+"activeTop");
		return activeTop;
	}
	protected Color getActiveBottom(){
		activeBottom = UIManager.getColor(getPropertyPrefix()+"activeBottom");
		return activeBottom;
	}
	protected Color getSelectTop(){
		selectTop = UIManager.getColor(getPropertyPrefix()+"selectTop");
		return selectTop;
	}
	protected Color getSelectBottom(){
		selectBottom = UIManager.getColor(getPropertyPrefix()+"selectBottom");
		return selectBottom;
	}
	protected Color getSelectActiveTop(){
		selectActiveTop = UIManager.getColor(getPropertyPrefix()+"selectActiveTop");
		return selectActiveTop;
	}
	protected Color getSelectActiveBottom(){
		selectActiveBottom = UIManager.getColor(getPropertyPrefix()+"selectActiveBottom");
		return selectActiveBottom;
	}
	protected Color getPressTop(){
		pressTop = UIManager.getColor(getPropertyPrefix()+"pressTop");
		return pressTop;
	}
	protected Color getPressBottom(){
		pressBottom = UIManager.getColor(getPropertyPrefix()+"pressBottom");
		return pressBottom;
	}
	protected Color getNormalTop(){
		normalTop = UIManager.getColor(getPropertyPrefix()+"normalTop");
		return normalTop;
	}
	protected Color getNormalBottom(){
		normalBottom = UIManager.getColor(getPropertyPrefix()+"normalBottom");
		return normalBottom;
	}
	protected Color getDisableTop(){
		disableTop = UIManager.getColor(getPropertyPrefix()+"disableTop");
		return disableTop;
	}
	protected Color getDisableBottom(){
		disableBottom = UIManager.getColor(getPropertyPrefix()+"disableBottom");
		return disableBottom;
	}
	protected Color getDisableText(){
		disableText = UIManager.getColor(getPropertyPrefix()+"disableText");
		return disableText;
	}
	protected Color getFocus(){
		focus = UIManager.getColor(getPropertyPrefix()+"focus");
		return focus;
	}
	protected Color getFocusColor() {
		focusColor = UIManager.getColor(getPropertyPrefix() + "focus");
		return focusColor;
	}
	
	private void calculeRoundRectangle(JComponent b){
		float size = (float) UIManager.get(getPropertyPrefix()+"borderSize");
		int roundRectangle = UIManager.getInt(getPropertyPrefix()+"borderRound");
		R.setRoundRect(+size/2, +size/2, b.getWidth()-size, b.getHeight()-size, roundRectangle, roundRectangle);
	}
	
	public void paint(Graphics g, JComponent c){
		if(!c.getBounds().getSize().equals(R.getBounds().getSize()))
			calculeRoundRectangle(c);
		
		AbstractButton b = (AbstractButton) c;
		ButtonModel m = b.getModel();
		
		Graphics2D G = (Graphics2D) g.create();
		GradientPaint P;
		float locY = 0;
		float locH = b.getHeight();
		
		if (m.isPressed()) {
			P = new GradientPaint(0, locY, getPressTop(), 0, locH, getPressBottom());
		} else if (m.isRollover()){
			if(m.isSelected()){
				P = new GradientPaint(0, locY, getSelectActiveTop(), 0, locH, getSelectActiveBottom());
			} else {
				P = new GradientPaint(0, locY, getActiveTop(), 0, locH, getActiveBottom());
			}
		} else if (m.isEnabled()){
			if(m.isSelected()){
				P = new GradientPaint(0, locY, getSelectTop(), 0, locH, getSelectBottom());
			} else {
				P = new GradientPaint(0, locY, getNormalTop(), 0, locH, getNormalBottom());
			}
		} else {
			if(m.isSelected()){
				P = new GradientPaint(0, locY, getSelectTop(), 0, locH, getSelectBottom());
			} else {
				P = new GradientPaint(0, locY, getDisableTop(), 0, locH, getDisableBottom());
			}
		}
		
		G.setPaint(P);
		G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		G.fill(R);
		super.paint(g, c);
	}
	
	public void paintText(Graphics g, JComponent c, Rectangle textRect, String text){
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
        int mnemIndex = b.getDisplayedMnemonicIndex();
        Graphics2D G = (Graphics2D) g;
        G.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        /* Draw the Text */
        if(model.isEnabled()) {
            /*** paint the text normally */
            G.setColor(b.getForeground());
        }
        else {
            /*** paint the text disabled ***/
            G.setColor(getDisableText());
        }
        SwingUtilities2.drawStringUnderlineCharAt(c, G,text,mnemIndex,
                                  textRect.x, textRect.y + fm.getAscent());
	}
	public void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect){
		Graphics2D G = (Graphics2D) g.create();
		
        G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        G.setColor(new Color(0, 0, 0, 50));
        G.fill(R);
		
	}
	
	public boolean contains(JComponent c, int x, int y){return R.contains(x, y);}
}

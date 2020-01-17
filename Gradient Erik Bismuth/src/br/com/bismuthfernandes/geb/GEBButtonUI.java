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
import javax.swing.plaf.basic.BasicButtonUI;

import sun.swing.SwingUtilities2;



public class GEBButtonUI extends BasicButtonUI {
	
	private RoundRectangle2D R = new RoundRectangle2D.Double();

	public static ComponentUI createUI(JComponent c) {return new GEBButtonUI();}
	public void installUI(JComponent c){
		super.installUI(c);
//		c.setFocusable(UIManager.getBoolean(getPropertyPrefix()+"focusable"));
	}
	public void installDefaults(AbstractButton b){
		super.installDefaults(b);
		LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
	}
	public void uninstallDefaults(AbstractButton b){
		super.uninstallDefaults(b);
	}
	
	public static String getStaticPropertyPrefix(){
		return "Button.";
	}
	
	protected static Color getActiveTop(){
		return UIManager.getColor(getStaticPropertyPrefix()+"activeTop");
	}
	protected static Color getActiveBottom(){
		return UIManager.getColor(getStaticPropertyPrefix()+"activeBottom");
	}
	protected static Color getPressTop(){
		return UIManager.getColor(getStaticPropertyPrefix()+"pressTop");
	}
	protected static Color getPressBottom(){
		return UIManager.getColor(getStaticPropertyPrefix()+"pressBottom");
	}
	protected static Color getNormalTop(){
		return UIManager.getColor(getStaticPropertyPrefix()+"normalTop");
	}
	protected static Color getNormalBottom(){
		return UIManager.getColor(getStaticPropertyPrefix()+"normalBottom");
	}
	protected static Color getDisableTop(){
		return UIManager.getColor(getStaticPropertyPrefix()+"disableTop");
	}
	protected static Color getDisableBottom(){
		return UIManager.getColor(getStaticPropertyPrefix()+"disableBottom");
	}
	protected static Color getDisableText(){
		return UIManager.getColor(getStaticPropertyPrefix()+"disableText");
	}
	protected static Color getFocus(){
		return UIManager.getColor(getStaticPropertyPrefix()+"focus");
	}
	protected static Color getFocusColor() {
		return UIManager.getColor(getStaticPropertyPrefix() + "focus");
	}
	
	private void calculeRoundRectangle(JComponent b){
		float size = (float) UIManager.get("Button.borderSize");
		int roundRect = UIManager.getInt(getStaticPropertyPrefix()+"borderRound");
		R.setRoundRect(+size/2, +size/2, b.getWidth()-size, b.getHeight()-size, roundRect, roundRect);
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
			P = new GradientPaint(0, locY, getActiveTop(), 0, locH, getActiveBottom());
		} else if (m.isEnabled()){
			P = new GradientPaint(0, locY, getNormalTop(), 0, locH, getNormalBottom());
		} else {
			P = new GradientPaint(0, locY, getDisableTop(), 0, locH, getDisableBottom());
		}
		
		G.setPaint(P);
		G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		G.fill(R);
		super.paint(g, c);
		G.dispose();
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
		G.dispose();
	}
	public void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect){
		Graphics2D G = (Graphics2D) g.create();
		
        G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        G.setColor(new Color(0, 0, 0, 50));
        G.fill(R);

		G.dispose();
	}
	
	public boolean contains(JComponent c, int x, int y){return R.contains(x, y);}
	
}

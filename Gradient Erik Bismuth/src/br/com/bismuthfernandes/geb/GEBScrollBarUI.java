package br.com.bismuthfernandes.geb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * 
 * @author Erik Fernandes
 *
 */
public class GEBScrollBarUI extends BasicScrollBarUI {
	private int mirror = 0;

	private Color thumbNormalTop;
	private Color thumbNormalBottom;
	private Color thumbPressTop;
	private Color thumbPressBottom;
	private Color thumbActiveTop;
	private Color thumbActiveBottom;
	private Color thumbDisableTop;
	private Color thumbDisableBottom;
	private Color directionColor;
	private boolean direction;
	private int value = 0;

//	private int scrollBarValue;
//	
//	private boolean supportsAbsolutePositioning;
	
	private static final String uiClass = "ScrollBar.";

	protected Color getThumbNormalTop(){
		thumbNormalTop = UIManager.getColor(uiClass+"thumbNormalTop");
		return thumbNormalTop;
	}
	protected Color getThumbNormalBottom(){
		thumbNormalBottom = UIManager.getColor(uiClass+"thumbNormalBottom");
		return thumbNormalBottom;
	}
	protected Color getThumbPressTop(){
		thumbPressTop = UIManager.getColor(uiClass+"thumbPressTop");
		return thumbPressTop;
	}
	protected Color getThumbPressBottom(){
		thumbPressBottom = UIManager.getColor(uiClass+"thumbPressBottom");
		return thumbPressBottom;
	}
	protected Color getThumbActiveTop(){
		thumbActiveTop = UIManager.getColor(uiClass+"thumbActiveTop");
		return thumbActiveTop;
	}
	protected Color getThumbActiveBottom(){
		thumbActiveBottom = UIManager.getColor(uiClass+"thumbActiveBottom");
		return thumbActiveBottom;
	}
	protected Color getThumbDisableTop(){
		thumbDisableTop = UIManager.getColor(uiClass+"thumbDisableTop");
		return thumbDisableTop;
	}
	protected Color getThumbDisableBottom(){
		thumbDisableBottom = UIManager.getColor(uiClass+"thumbDisableBottom");
		return thumbDisableBottom;
	}
	protected Color getDirectionColor(){
		directionColor = UIManager.getColor(uiClass+"directionColor");
		return directionColor;
	}
	protected boolean getDirection(){
		direction = UIManager.getBoolean(uiClass+"direction");
		return direction;
	}
	
	public void updateColors(){configureScrollBarColors();}
	
	@Override protected void configureScrollBarColors(){
		getThumbNormalTop();
		getThumbNormalBottom();
		getThumbPressTop();
		getThumbPressBottom();
		getThumbActiveTop();
		getThumbActiveBottom();
		getDirectionColor();
		getDirection();
	}
	
	@Override protected void installDefaults(){
		scrollBarWidth = UIManager.getInt("ScrollBar.width");
		if (scrollBarWidth <= 0) {
		    scrollBarWidth = 16;
		}
		minimumThumbSize = (Dimension) UIManager.get("ScrollBar.minimumThumbSize");
		maximumThumbSize = (Dimension) UIManager.get("ScrollBar.maximumThumbSize");
		
//		Boolean absB = (Boolean)UIManager.get("ScrollBar.allowsAbsolutePositioning");
//		supportsAbsolutePositioning = (absB != null) ? absB.booleanValue() :
//		                              false;
		
		trackHighlight = NO_HIGHLIGHT;
		if (scrollbar.getLayout() == null ||
		             (scrollbar.getLayout() instanceof UIResource)) {
		    scrollbar.setLayout(this);
		}
		configureScrollBarColors();
		
//		scrollBarValue = scrollbar.getValue();
		
		incrGap = UIManager.getInt("ScrollBar.incrementButtonGap");
		decrGap = UIManager.getInt("ScrollBar.decrementButtonGap");
		
		// TODO this can be removed when incrGap/decrGap become protected
		// handle scaling for sizeVarients for special case components. The
		// key "JComponent.sizeVariant" scales for large/small/mini
		// components are based on Apples LAF
		String scaleKey = (String)scrollbar.getClientProperty(
		        "JComponent.sizeVariant");
		if (scaleKey != null){
		    if ("large".equals(scaleKey)){
		        scrollBarWidth *= 1.15;
		        incrGap *= 1.15;
		        decrGap *= 1.15;
		    } else if ("small".equals(scaleKey)){
		        scrollBarWidth *= 0.857;
		        incrGap *= 0.857;
		        decrGap *= 0.714;
		    } else if ("mini".equals(scaleKey)){
		        scrollBarWidth *= 0.714;
		        incrGap *= 0.714;
		        decrGap *= 0.714;
		    }
		}
	}
	
	public static ComponentUI createUI(JComponent c) {return new GEBScrollBarUI();}
	
	public GEBScrollBarUI() {super();}

	//Área da barra
	@Override public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		Graphics2D G = (Graphics2D) g.create();
		G.setColor(Color.RED);
		Color c1 = new Color(100, 100, 100);
		Color c2 = new Color(40, 40, 40);
		double x = trackBounds.getX(), y =  trackBounds.getY();
		double w, h;
		JScrollBar b = (JScrollBar) c;
		w = trackBounds.getWidth();
		h = trackBounds.getHeight();
		double mx = w/(double)2;
		double my = h/(double)2;
		float subx = 0;
		float suby = 0;
		float lx, ly;
		Rectangle2D r1;
		Rectangle2D r2;
		if(b.getOrientation()==SwingConstants.HORIZONTAL){
			lx = 0;
			ly = (float) (h/2);
			suby = ly/4;
			r1 = new Rectangle.Double(x, y, w, my);
			r2 = new Rectangle.Double(x, y+my, w, my);
		} else {
			lx = (float) (w/2);
			ly = 0;
			subx = lx/4;
			r1 = new Rectangle.Double(x, y, mx, h);
			r2 = new Rectangle.Double(x+mx, y, mx, h);
		}
		G.setPaint(new GradientPaint(0, 0, c1, lx-subx, ly-suby, c2));
		G.fill(r1);
		G.setPaint(new GradientPaint(lx*2, ly*2, c1, lx+subx, ly+suby, c2));
		G.fill(r2);
		G.setColor(Color.BLACK);
		G.setStroke(new BasicStroke(1.5f));
		G.draw(trackBounds);
//		G.fill(thumbBounds);
		G.finalize();
	}
	
	//Barra de rolagem
	@Override public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
//		updateColors();
		Graphics2D G = (Graphics2D) g.create();
		Rectangle r = new Rectangle(thumbBounds.x+1, thumbBounds.y+1, thumbBounds.width-2, thumbBounds.height-2);
		JScrollBar s = (JScrollBar) c;
		Color topColor;
		Color bottColor;
		if(isDragging){
			topColor = getThumbPressTop();
			bottColor = getThumbPressBottom();
		} else if(isThumbRollover()){
			topColor = getThumbActiveTop();
			bottColor = getThumbActiveBottom();
		} else if (c.isEnabled()){
			topColor = getThumbNormalTop();
			bottColor = getThumbNormalBottom();
		} else {
			topColor = getThumbDisableTop();
			bottColor = getThumbDisableBottom();
		}
		int lx = thumbBounds.width/2;
		int ly = thumbBounds.height/2;
		if (s.getOrientation()==SwingConstants.HORIZONTAL) {
			int m = thumbBounds.height/5;
			G.setPaint(new GradientPaint(0, ly-m, topColor, 0, ly+m, bottColor));
		} else {
			int m = thumbBounds.width/5;
			G.setPaint(new GradientPaint(lx-m, 0, topColor, lx+m, 0, bottColor));
		}
		G.fill(r);
		G.setColor(Color.BLACK);
		G.drawRect(thumbBounds.x, thumbBounds.y, thumbBounds.width-1, thumbBounds.height-1);
		double setaSize = Math.min(thumbBounds.getWidth(), thumbBounds.getHeight())/2 - 3;
		double newY = (s.getOrientation()==SwingConstants.VERTICAL ? thumbBounds.getY(): thumbBounds.getY());
		double newX = (s.getOrientation()==SwingConstants.VERTICAL ? thumbBounds.getX(): thumbBounds.getX()-(setaSize/2));
		int orientation = CENTER;
		if (direction) {
			if (value - s.getValue() > 0 && isDragging) {
				if (s.getOrientation() == SwingConstants.VERTICAL)
					newY -= setaSize / 2;
				else
					newX -= setaSize / 2;
				orientation = s.getOrientation() == SwingConstants.VERTICAL ? NORTH : WEST;
			} else if (value - s.getValue() < 0 && isDragging) {
				if (s.getOrientation() == SwingConstants.VERTICAL)
					newY += setaSize / 2;
				else
					newX += setaSize / 2;
				orientation = s.getOrientation() == SwingConstants.VERTICAL ? SOUTH : EAST;
			} else {

			}
			if (isDragging) {
				GEBArrowButtonScroll.paintTriangle(G, newX + (thumbBounds.getWidth() / 2 - setaSize / 2),
						newY + (thumbBounds.getHeight() / 2 - setaSize / 2), setaSize, orientation,true,
						getDirectionColor(), null);
			} else {
				paintLines(G, newX + (thumbBounds.getWidth() / 2 - setaSize / 2),
						newY + (thumbBounds.getHeight() / 2 - setaSize / 2), setaSize, s.getOrientation(), getDirectionColor());
			}
		}
		
		G.finalize();
		value = s.getValue();
	}
	
	@Override public boolean contains(JComponent c, int x, int y){
		boolean incr = incrButton.contains(x-incrButton.getX(), y-incrButton.getY());
		boolean decr = decrButton.contains(x-decrButton.getX(), y-decrButton.getY());
		boolean trac = getTrackBounds().contains(x, y);
		return incr||decr||trac;
	}
	
	@Override protected JButton createDecreaseButton(int orientation)  {
	    return new GEBArrowButtonScroll(orientation, getMirror());
	}
	@Override protected JButton createIncreaseButton(int orientation)  {
	    return new GEBArrowButtonScroll(orientation, getMirror());
	}
	
	public void setMirror(int m){mirror = m;
	if(incrButton instanceof GEBArrowButtonScroll)
		((GEBArrowButtonScroll) incrButton).setMirror(m);
	if(decrButton instanceof GEBArrowButtonScroll)
		((GEBArrowButtonScroll) decrButton).setMirror(m);}
	public int getMirror(){return mirror;}
	
	
	public void paintLines(Graphics g, double x, double y, double setaSize, int orientation, Color color){
		g.translate((int) x, (int) y);
		Color oldColor = g.getColor();
		g.setColor(color);
		if(orientation == VERTICAL){
			g.fillRect(0, -2, (int) setaSize, 2);
			g.fillRect(0, 2, (int) setaSize, 2);
			g.fillRect(0, 6, (int) setaSize, 2);
		} else if(orientation == HORIZONTAL){
			g.fillRect(0, 0, 2, (int) setaSize);
			g.fillRect(4, 0, 2, (int) setaSize);
			g.fillRect(8, 0, 2, (int) setaSize);
		}
		g.translate((int) -x, (int) -y);
		g.setColor(oldColor);
	}
}

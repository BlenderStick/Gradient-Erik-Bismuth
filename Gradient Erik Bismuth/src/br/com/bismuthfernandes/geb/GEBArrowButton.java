package br.com.bismuthfernandes.geb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.UIResource;

/**
 * 
 * @author Erik Fernandes
 *
 */
public class GEBArrowButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5045229156302617512L;
	private int orientation;
	private int mirror = 0;
	protected Path2D P;
	
	private Color shadow;
	private Color darkShadow;
	private Color borderColor;

	protected Color getNormalTop(){
		return UIManager.getColor(getPropertyPrefix()+"normalTop");
	}
	protected Color getNormalBottom(){
		return UIManager.getColor(getPropertyPrefix()+"normalBottom");
	}
	protected Color getSelectTop() {
		return UIManager.getColor(getPropertyPrefix()+"activeTop");
	}
	protected Color getSelectBottom() {
		return UIManager.getColor(getPropertyPrefix()+"activeBottom");
	}
	protected Color getPressTop() {
		return UIManager.getColor(getPropertyPrefix()+"pressTop");
	}
	protected Color getPressBottom() {
		return UIManager.getColor(getPropertyPrefix()+"pressBottom");
	}
	protected Color getDisableTop() {
		return UIManager.getColor(getPropertyPrefix()+"disableTop");
	}
	protected Color getDisableBottom() {
		return UIManager.getColor(getPropertyPrefix()+"disableBottom");
	}


	private String getPropertyPrefix() {return "ArrowButton.";}
	
	/**
	 * Cria um Botão com uma seta e que pode ser espelhado para alguma direção, procure as direções em {@link SwingConstants}
	 * @param orientation - define a direção podendo ser NORTH, SOUTH, EAST ou WEAST
	 * @param buttonDirection - Espelha para NORTH, SOUTH, EAST, WEAST ou CENTER
	 */
	public GEBArrowButton(int orientation, int buttonDirection){
		this.orientation=orientation;
		this.mirror=buttonDirection;
		
		this.setOpaque(false);
		this.setRequestFocusEnabled(false);
	}
	/**
	 * Cria um Botão com uma seta e que pode ser espelhado para alguma direção, procure as direções em {@link SwingConstants}
	 * @param orientation - define a direção podendo ser NORTH, SOUTH, EAST ou WEAST
	 */ 
	public GEBArrowButton(int orientation){
		this.orientation=orientation;
		this.mirror=CENTER;
		
		this.setOpaque(false);
		this.setRequestFocusEnabled(false);
	}
	
	private void createPath(){
		Path2D p = new Path2D.Double();
		double w = getWidth();
		double h = getHeight();
		double round = UIManager.getInt(getPropertyPrefix()+"borderRound");
		
		if (mirror==SwingConstants.CENTER){
			p.append(new RoundRectangle2D.Double(0, 0, w, h, round, round), false);
		} else if (mirror==SwingConstants.NORTH){
			p.moveTo(w, h);
			p.lineTo(w, h-round);
			p.quadTo(w, 0, w-round, 0);
			p.quadTo(0, 0, 0, h-round);
			p.lineTo(0, h);
		} else if (mirror==SwingConstants.SOUTH){
			p.moveTo(w, 0);
			p.lineTo(w, h-round);
			p.quadTo(w, h, w-round, h);
			p.quadTo(0, h, 0, h-round);
			p.lineTo(0, 0);
		} else if(mirror==SwingConstants.EAST){
			p.moveTo(w, h);
			p.lineTo(round, h);
			p.quadTo(0, h, 0, h-round);
			p.lineTo(0, round);
			p.quadTo(0, 0, round, 0);
			p.lineTo(w, 0);
		} else if(mirror==SwingConstants.WEST){
			p.moveTo(0, 0);
			p.lineTo(w-round, 0);
			p.quadTo(w, 0, w, round);
			p.lineTo(w, h-round);
			p.quadTo(w, h, w-round, h);
			p.lineTo(0, h);
		}
		p.closePath();
		P = p;
	}
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x, y, width, height);
		createPath();
	}
	
	public int getOrientation() {return orientation;}
	public void setOrientation(int orientation) {this.orientation = orientation;createPath();}
	public int getMirror() {return mirror;}
	public void setMirror(int mirror) {this.mirror = mirror;createPath();}
	public boolean contains(int x, int y){if(P!=null)return P.contains(x, y); return false;}
	
	public void paint(Graphics g){
		Graphics2D G = (Graphics2D) g.create();
		Color origColor = g.getColor();
		Color bottColor = getNormalBottom();//= new Color(30, 30, 30);
		Color topColor = getNormalTop();//new Color(180, 180, 180);
		boolean isPress, isEnabled, isSelect;
		int h, w, size;
		w=getSize().width;
		h=getSize().height;
		isPress=getModel().isPressed();
		isEnabled=isEnabled();
		isSelect=getModel().isRollover();
		
		
		
//		G.setColor(Color.WHITE);
//		G.fillRect(0, 0, w, h);
//		G.clearRect(0, 0, w, h);
		if(isEnabled){
			if(!isPress && !isSelect){
				topColor = getNormalTop();
				bottColor = getNormalBottom();
			} else if (isPress) {
				topColor = getPressTop();
				bottColor = getPressBottom();
			} else if (isSelect){
				topColor = getSelectTop();
				bottColor = getSelectBottom();
			}
		} else {
			topColor = getDisableTop();
			bottColor = getDisableBottom();
		}
		G.setPaint(new GradientPaint(0, 0, topColor, 0, h, bottColor));
		G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(P!=null)
			G.fill(P);
		
		if(getBorder()!=null && !(getBorder() instanceof UIResource)){
			G.setColor(origColor);
			paintBorder(G);
		} else {
			G.setColor(Color.BLACK);
			if(borderColor!=null)
				G.setColor(borderColor);
			G.setStroke(new BasicStroke(1.5f));
			G.draw(P.createTransformedShape(AffineTransform.getScaleInstance(0.95, 0.95)));
		}
		size = Math.min((h - 4) / 3, (w - 4) / 3);
		size = Math.max(size, 2);
		paintTriangle(g, (w - size) / 2, (h - size) / 2,
		                    size, orientation, isEnabled,
		                    darkShadow, shadow);
		G.finalize();
	}
	
//	public Dimension getPreferredSize() {return new Dimension(16, 16);}
//	
//	public Path2D getShape(){return new Path2D.Double(P);}
	
	public static void paintTriangle(Graphics g, double x, double y, double size,
			                                int direction, boolean isEnabled,
			                                Color darkShadow, Color shadow) {
			    Color oldColor = g.getColor();
			    int mid, i, j;
			
			    j = 0;
			    size = Math.max(size, 2);
			    mid = ((int) size / 2);
			
			    g.translate((int) x, (int) y);
			    if(isEnabled)
			        g.setColor(darkShadow);
			    else
			        g.setColor(shadow);
			
			    switch(direction)       {
			    case NORTH:
			        for(i = 0; i < size; i++)      {
			            g.drawLine(mid-i, i, mid+i, i);
			        }
			        if(!isEnabled)  {
			            g.setColor(shadow);//high
			            g.drawLine(mid-i+2, i, mid+i, i);
			        }
			        break;
			    case SOUTH:
			        if(!isEnabled)  {
			            g.translate(1, 1);
			            g.setColor(shadow);//high
			            for(i = (int) size-1; i >= 0; i--)   {
			                g.drawLine(mid-i, j, mid+i, j);
			                j++;
			            }
			            g.translate(-1, -1);
			            g.setColor(shadow);
			        }
			
			        j = 0;
			        for(i = (int) size-1; i >= 0; i--)   {
			            g.drawLine(mid-i, j, mid+i, j);
			            j++;
			        }
			        break;
			    case WEST:
			        for(i = 0; i < size; i++)      {
			            g.drawLine(i, mid-i, i, mid+i);
			        }
			        if(!isEnabled)  {
			            g.setColor(shadow);//highLight
			            g.drawLine(i, mid-i+2, i, mid+i);
			        }
			        break;
			    case EAST:
			        if(!isEnabled)  {
			            g.translate(1, 1);
			            g.setColor(shadow);//highLight
			            for(i = (int) size-1; i >= 0; i--)   {
			                g.drawLine(j, mid-i, j, mid+i);
			                j++;
			            }
			            g.translate(-1, -1);
			            g.setColor(shadow);
			        }
			
			        j = 0;
			        for(i = (int) size-1; i >= 0; i--)   {
			            g.drawLine(j, mid-i, j, mid+i);
			            j++;
			        }
			        break;
			    }
			    g.translate((int) -x, (int) -y);
			    g.setColor(oldColor);
			}
}

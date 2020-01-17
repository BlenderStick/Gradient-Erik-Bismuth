package br.com.bismuthfernandes.geb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;

public class GEBSliderUI extends BasicSliderUI {
	
	public static ComponentUI createUI(JComponent c){return new GEBSliderUI((JSlider) c);}

	public static String getStaticPropertyPrefix(){
		return "Slider.";
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
	
	protected Shape thumbShape;
	protected Shape leftThumb;
	protected Shape rightThumb;
	
	
	public void installDefault(JSlider slider){
		super.installDefaults(slider);
		slider.setOpaque(false);
	}
	public void uninstallDefaults(JSlider slider){
		super.uninstallListeners(slider);
	}

	public GEBSliderUI(JSlider arg0) {
		super(arg0);
//		paint
	}
	
	protected void calculateLeftThumb(){
		int w = trackRect.x;
		int wT = w-thumbRect.width/2;
		int h = thumbRect.height;
		int a = 10;
		
		Path2D path = new Path2D.Double();
		path.moveTo(0, h);
		path.lineTo(w, h);
		path.lineTo(wT, h/2);
		path.lineTo(wT, a);
		path.curveTo(wT, 0, wT, 0, w, 0);
		path.lineTo(0, 0);
		path.closePath();
		leftThumb = path;
	}
	protected void calculateRightThumb(){
		int x = slider.getWidth();
		int w = slider.getWidth()-(trackRect.x+trackRect.width);
		int wT = w-thumbRect.width/2;
		int h = thumbRect.height;
		int a = 10;
		
		Path2D path = new Path2D.Double();
		path.moveTo(x, h);
		path.lineTo(x-w, h);
		path.lineTo(x-wT, h/2);
		path.lineTo(x-wT, a);
		path.curveTo(x-wT, 0, x-wT, 0, x-w, 0);
		path.lineTo(x, 0);
		path.closePath();
		rightThumb = path;
	}
	
	protected void calculateThumbShape(){
		int w = thumbRect.width, h = thumbRect.height, a = 10;
		Path2D path = new Path2D.Double();
		path.moveTo(w-a, 0);
		path.curveTo(w, 0, w, 0, w, a);
		path.lineTo(w, h-a);
		path.lineTo(w/2, h);
		path.lineTo(0, h-a);
		path.lineTo(0, a);
		path.curveTo(0, 0, 0, 0, a, 0);
		path.closePath();
		thumbShape = path;
	}
	public void calculateThumbSize(){
		super.calculateThumbSize();
		getThumbSize().setSize(thumbRect.getSize());
		calculateThumbShape();
		calculateLeftThumb();
		calculateRightThumb();
		System.out.println(contentRect);
	}
	public void calculateTrackRect(){
//		super.calculateTrackRect();
		trackRect.x = 10;
		trackRect.y = 0;
		trackRect.width = slider.getWidth()-20;
		trackRect.height = slider.getHeight();
		calculateLeftThumb();
		calculateRightThumb();
	}
	public void calculateContentRect(){
		super.calculateContentRect();
//		contentRect = trackRect.getBounds();
	}
	
	public void paintThumb(Graphics g){
		Graphics2D G = (Graphics2D) g.create();
		G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		G.drawLine(0, 10, slider.getWidth(), 10);
		Color top;
		Color bot;
		if(slider.isEnabled()){
			if(isDragging()){
				top = getPressTop();
				bot = getPressBottom();
			}
			else {
				top = getNormalTop();
				bot = getNormalBottom();
			}
		}else{
			top = getDisableTop();
			bot = getDisableBottom();
		}

		G.setPaint(new GradientPaint(0, 0, getNormalTop(), 0, slider.getHeight(), getNormalBottom()));
		G.fill(leftThumb);
		G.fill(rightThumb);
		G.setPaint(new GradientPaint(0, 0, top, 0, slider.getHeight(), bot));
		G.translate(thumbRect.x, thumbRect.y);
		G.fill(thumbShape);
		G.setStroke(new BasicStroke(1.5f));
		G.setColor(Color.BLACK);
		G.draw(thumbShape);
		G.translate(-thumbRect.x, -thumbRect.y);
		G.draw(leftThumb);
		G.translate(-1, 0);
		G.draw(rightThumb);
		
	}
	public void paintFocus(Graphics g){}
	public void paintTicks(Graphics g){
		Graphics2D G = (Graphics2D) g.create();
		G.setColor(Color.RED);
//		G.setColor(Color.GREEN);
//		G.fill(tickRect);
	}
	public void paintTrack(Graphics g){
		Graphics2D G = (Graphics2D) g.create();
		G.setColor(Color.RED);
		G.draw(contentRect);
//		G.fill(tickRect);
//		G.drawLine(0, 0, trackBuffer, 0);
//		G.drawLine(slider.getWidth()-trackBuffer, 0, slider.getWidth(), 0);
	}
	
}

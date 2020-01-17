package br.com.bismuthfernandes.geb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class GEBSpinnerUI extends BasicSpinnerUI {
	
	public static ComponentUI createUI(JComponent comp){return new GEBSpinnerUI();}
	
	protected boolean press = false;
	protected int dir = 0;
	protected int select = 0;
	private ML ml = new ML();
	private MML mml = new MML();
	private KFL kl = new KFL();
	private CL cl = new CL();
	protected RoundRectangle2D RG = new RoundRectangle2D.Double();
	private Timer timer;
	
	public void installListeners(){
		super.installListeners();
		spinner.addComponentListener(cl);
		spinner.addMouseListener(ml);
		spinner.addMouseMotionListener(mml);
		((NumberEditor) super.createEditor()).getTextField().addKeyListener(kl);
		((NumberEditor) super.createEditor()).getTextField().addFocusListener(kl);
	}
	public void installDefaults(){
		super.installDefaults();
		spinner.setBorder(null);
		spinner.setBackground(null);
		spinner.setBorder(UIManager.getBorder(getStaticPropertyPrefix()+"border"));
		spinner.setOpaque(false);
//		createEditor().set
	}
	public void uninstallDefaults(){
		super.uninstallDefaults();
		spinner.setBorder(null);
	}
	public void uninstallListeners(){
		super.uninstallListeners();
		spinner.removeComponentListener(cl);
		spinner.removeMouseListener(ml);
		spinner.removeMouseMotionListener(mml);
		spinner.setBorder(null);
		((NumberEditor) super.createEditor()).getTextField().removeKeyListener(kl);
		((NumberEditor) super.createEditor()).getTextField().removeFocusListener(kl);
	}

	public static String getStaticPropertyPrefix(){
		return "Spinner.";
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
	protected static Color getArrowNormal(){
		return UIManager.getColor(getStaticPropertyPrefix()+"arrowNormal");
	}
	protected static Color getArrowActive(){
		return UIManager.getColor(getStaticPropertyPrefix()+"arrowActive");
	}
	protected static Color getArrowPress(){
		return UIManager.getColor(getStaticPropertyPrefix()+"arrowPress");
	}
	protected static Color getFocus(){
		return UIManager.getColor(getStaticPropertyPrefix()+"focus");
	}
	protected static Color getFocusColor() {
		return UIManager.getColor(getStaticPropertyPrefix() + "focus");
	}
	
	public GEBSpinnerUI() {
		super();
		timer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calculeProgress();
			}
		});
		timer.setInitialDelay(500);
	}
	
	public void paint(Graphics g, JComponent c){
		Graphics2D G = (Graphics2D) g.create();
		G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color top;
		Color bottom;
		Color left;
		Color right;
		if(c.isEnabled())
			if(select==0){
				top = getNormalTop();
				bottom = getNormalBottom();
				left = getArrowNormal();
				right= getArrowNormal();
			} else if(select==1){
				top = getActiveTop();
				bottom = getActiveBottom();
				if(dir==1){
					left = getArrowActive();
					right= getArrowNormal();
				}else if(dir==2){
					left = getArrowNormal();
					right= getArrowActive();
				}else{
					left = getArrowNormal();
					right= getArrowNormal();
				}
			} else if(select==2){
				top = getPressTop();
				bottom = getPressBottom();
				if(dir==1){
					left = getArrowPress();
					right= getArrowNormal();
				}else if(dir==2){
					left = getArrowNormal();
					right= getArrowPress();
				}else{
					left = getArrowNormal();
					right= getArrowNormal();
				}
			} else{
				top = Color.BLACK;
				bottom = Color.BLACK;
				left = Color.BLACK;
				right= Color.BLACK;
			}
		else{
			top = getDisableTop();
			bottom = getDisableBottom();
			left = Color.BLACK;
			right= Color.BLACK;
		}
		G.setPaint(new GradientPaint(0, 0, top, 0, c.getHeight(), bottom));
		G.fill(RG);
		QuadCurve2D CC1 = new QuadCurve2D.Float(c.getWidth()-8, 5, c.getWidth()-2, c.getHeight()/2, c.getWidth()-8, c.getHeight()-5);
		QuadCurve2D CC2 = new QuadCurve2D.Float(8, 5, 2, c.getHeight()/2, 8, c.getHeight()-5);
		G.setStroke(new BasicStroke(2));
		G.setColor(right);
		G.draw(CC1);
		G.setColor(left);
		G.draw(CC2);
	}
	
	public LayoutManager createLayout(){
		return new LayoutManager() {
			
			@Override public void removeLayoutComponent(Component comp) {}
			
			@Override public Dimension preferredLayoutSize(Container parent) {return null;}
			
			@Override public Dimension minimumLayoutSize(Container parent) {return null;}
			
			@Override public void layoutContainer(Container parent) {PathCalcule();spinner.revalidate();spinner.repaint();}
			
			@Override public void addLayoutComponent(String name, Component comp) {}
		};
	}

	public JComponent createNextButton(){return null;}
	public JComponent createPreviousButton(){return null;}
	
	public JComponent createEditor(){
		NumberEditor cEditor = (NumberEditor) super.createEditor();
		cEditor.getTextField().setHorizontalAlignment(JTextField.LEFT);
		cEditor.getTextField().setSelectedTextColor(Color.WHITE);
		cEditor.getTextField().setSelectionColor(new Color(18, 131, 214));
		return cEditor;
	}
	
	protected void calculeProgress(){
		try {
			if(dir==2)
				spinner.setValue(spinner.getNextValue());
			else if(dir==1)
				spinner.setValue(spinner.getPreviousValue());
		} catch (Exception e) {}
	}

	protected void PathCalcule(){
		int w = spinner.getWidth()-2, h = spinner.getHeight()-1, a = 10;
		RG.setRoundRect(0, 0, w, h, a, a);
		
		JComponent TF = createEditor();
		TF.setSize(w-20, h-8);
		TF.setLocation(10, 4);
	}
	
	public boolean contains(JComponent c, int x, int y){return RG.contains(x, y);}
	
	private class ML implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			press=false;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			spinner.repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			select = 0;
			dir=0;
			spinner.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			select = 2;
			spinner.repaint();
			calculeProgress();
			timer.start();
			press=true;
			spinner.requestFocus();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			timer.stop();
			press=false;
			//Verifica se o mouse está contido dentro da área do spinner
			if(e.getX()>0&&e.getY()>0&&e.getX()<spinner.getWidth()&&e.getY()<spinner.getHeight()){
				select = 1;
			}else{
				select = 0;
			}
			spinner.repaint();
		}
		
	}
	
	private class MML implements MouseMotionListener{

		@Override public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {
			select = 1;
			boolean right = false;
			boolean left = false;
			if(spinner.getWidth()>20){
				right = (e.getX()>spinner.getWidth()-10);
				left = (e.getX()<10);
				if (right) {
					dir = 2;
				} else if (left){
					dir = 1;
				}else{
					dir = 0;
				}
			}else{
				right = (e.getX()>spinner.getWidth()/2);
				if (right) {
					dir = 2;
				} else {
					dir = 1;
				}
			}
			spinner.repaint();
		}
	}

	private class KFL implements KeyListener, FocusListener{

		@Override public void keyPressed(KeyEvent e) {}

		@Override public void keyReleased(KeyEvent e) {}

		@Override public synchronized void keyTyped(KeyEvent e) {
			if(e.getKeyChar()=='\n'){
				((NumberEditor) createEditor()).getTextField().selectAll();
				e.getComponent().transferFocus();
			}
		}

		@Override public void focusGained(FocusEvent e) {
//			((JSpinner.DefaultEditor) ((JSpinner) jSpinner1).getEditor()).getTextField().requestFocus();
	         new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    try {
	                        Thread.sleep(100);
	                    } catch (InterruptedException ex) {
	                    }
	                    SwingUtilities.invokeLater(new Runnable() {
	                        @Override
	                        public void run() {
	                            ((NumberEditor) ((JSpinner) spinner).getEditor()).getTextField().selectAll();
	                        }
	                    });
	                }
	            }).start();
		}

		@Override
		public void focusLost(FocusEvent e) {}
		
	}
	
	private class CL implements ComponentListener{

		@Override public void componentHidden(ComponentEvent e) {}

		@Override public void componentMoved(ComponentEvent e) {}

		@Override public void componentResized(ComponentEvent e) {spinner.revalidate();}

		@Override public void componentShown(ComponentEvent e) {}
		
	}
	
}

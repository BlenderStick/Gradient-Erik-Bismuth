package br.com.bismuthfernandes.geb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.Field;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;

import br.com.bismuthfernandes.geometry.Geometry;

public final class GEBBorders {
	
	private GEBBorders(){}

	private static Border buttonBorder;
	private static Border checkBoxBorder;
	private static Border spinnerBorder;
	private static Border comboBoxBorder;
	private static Border popupMenuBorder;
	
	public static Border getButtonBorder(){
		if(buttonBorder==null){
			if(UIManager.get("Button.borderSize") instanceof Float && UIManager.get("Button.borderColor") instanceof Color){
				buttonBorder = new BorderUIResource.CompoundBorderUIResource(new GEBBorders.ButtonBorder(),
																			null);
			} else {
				buttonBorder = new BorderUIResource.CompoundBorderUIResource(new GEBBorders.ButtonBorder(1.5f, Color.BLACK),
																			null);
			}
			buttonBorder = new GEBBorders.ButtonBorder();
		}
			
		return buttonBorder;
	}
	public static Border getSpinnerBorder(){
		if(spinnerBorder==null){
			if(UIManager.get("Spinner.borderSize") instanceof Float && UIManager.get("Spinner.borderColor") instanceof Color){
				spinnerBorder = new BorderUIResource.CompoundBorderUIResource(
						new GEBBorders.SpinnerBorder((float) UIManager.get("Spinner.borderSize"), UIManager.getColor("Spinner.borderColor")),
																			null);
			} else {
				spinnerBorder = new BorderUIResource.CompoundBorderUIResource(new GEBBorders.ButtonBorder(1.5f, Color.BLACK),
																			null);
				System.out.println("Instancia 1");
			}
			spinnerBorder = new GEBBorders.SpinnerBorder();
		}
			
		return spinnerBorder;
	}
	public static Border getCheckBoxBorder(){
		if (checkBoxBorder == null) {
			if (UIManager.get("CheckBox.borderSize") instanceof Float
					&& UIManager.get("CheckBox.borderColor") instanceof Border) {
				checkBoxBorder = new BorderUIResource.CompoundBorderUIResource(new GEBBorders.CheckBoxBorder(), null);
			} else {
				checkBoxBorder = new BorderUIResource.CompoundBorderUIResource(
						new GEBBorders.CheckBoxBorder(1.5f, Color.BLACK), null);
			}
			checkBoxBorder = new GEBBorders.CheckBoxBorder();
		}

		return checkBoxBorder;
	}
	public static Border getComboBoxBorder(){
		if (comboBoxBorder == null) {
			if (UIManager.get("ComboBox.borderSize") instanceof Float
					&& UIManager.get("ComboBox.borderColor") instanceof Border) {
				comboBoxBorder = new GEBBorders.ComboBoxBorder();
			} else {
				comboBoxBorder = new GEBBorders.ComboBoxBorder();
			}
			comboBoxBorder = new GEBBorders.ComboBoxBorder();
		}

		return comboBoxBorder;
	}
	public static Border getPopupMenuBorder(){
		if (popupMenuBorder == null) {
			popupMenuBorder = new BorderUIResource.CompoundBorderUIResource(new GEBBorders.PopupMenuBorder(), BorderFactory.createLineBorder(Color.BLACK, 2));
		}

		return popupMenuBorder;
	}
	
	
	
	@SuppressWarnings("serial")
	public static class ButtonBorder extends AbstractBorder implements UIResource{
		protected BasicStroke stroke;
		protected Float size;
		protected Color color;
		
		public ButtonBorder(){update();}
		public ButtonBorder(float size, Color c){
			this.size = size;
			stroke = new BasicStroke(size);
			color = c;
		}
		
		void update(){
			size = UIManager.get("Button.borderSize") != null ? (Float) UIManager.get("Button.borderSize") : 1.5f;
			stroke = new BasicStroke(size);
			color = UIManager.get("Button.borderColor") != null ? UIManager.getColor("Button.borderColor") : Color.BLACK;
		}
		
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
			update();
			
			Graphics2D G = (Graphics2D) g.create();
			double roundRect = 
					(c instanceof JButton ? UIManager.getInt("Button.borderRound") : 
					c instanceof JToggleButton ? UIManager.getInt("ToggleButton.borderRound") : 
					c instanceof JSpinner ? UIManager.getInt("Spinner.borderRound") :
					20
					);
			double X = size/2d;
			double Y = size/2d;
			double W = w-size-0.5d;
			double H = h-size-0.5d;
			RoundRectangle2D R = new RoundRectangle2D.Double(X, Y, W, H, roundRect, roundRect);
			G.setStroke(stroke);
			G.setColor(color);
			G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			G.draw(R);
			
			G.dispose();
		}
		
		public Insets getBorderInsets(Component c, Insets newInsets){
			int i = size.intValue();
			newInsets.set(i, i, i, i);
			return newInsets;
		}
	}
	@SuppressWarnings("serial")
	public static class CheckBoxBorder extends AbstractBorder implements UIResource{
		private BasicStroke stroke;
		private Float size;
		private Color color;
		
		public CheckBoxBorder(){update();}
		public CheckBoxBorder(float size, Color c){
			this.size = size;
			stroke = new BasicStroke(size);
			color = c;
		}
		
		void update(){
			if(UIManager.get("CheckBox.borderSize")!=null && UIManager.get("CheckBox.borderSize")!=size){
				size = (Float) UIManager.get("CheckBox.borderSize");
				stroke = new BasicStroke(size);
			} else {
				size = 1.5f;
			}
			if(UIManager.get("CheckBox.borderColor") != null && color != UIManager.get("CheckBox.borderColor")){
				color = UIManager.getColor("CheckBox.borderColor");
			} else {
				color = Color.BLACK;
			}
		}
		
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
			update();
			int s = (int) Math.min(c.getWidth()-(size.intValue() * 2), c.getHeight()-(size.intValue() * 2));
			
			Graphics2D G = (Graphics2D) g.create();
			int round = UIManager.getInt("CheckBox.borderRound");
			RoundRectangle2D R = new RoundRectangle2D.Double(size.intValue(), size.intValue(), s, s, round, round);
			double w2 = Geometry.regraDeTres(size, (double)w, 1d);
			double h2 = Geometry.regraDeTres(size, (double)h, 1d);
			G.setStroke(stroke);
			G.setColor(color);
			G.translate((double)w/2, (double)h/2);
			G.scale(1d-w2, 1d-h2);
			G.translate(-(double)w/2-w2, -(double)h/2-h2);
			G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			G.draw(R);
		}
		
		public Insets getBorderInsets(Component c, Insets newInsets){
			int i = size.intValue();
			newInsets.set(i, i, i, i);
			return newInsets;
		}
	}
	@SuppressWarnings("serial")
	public static class SpinnerBorder extends ButtonBorder{
		public SpinnerBorder(){super();}
		public SpinnerBorder(float size, Color c){super(size, c);}
		void update(){
			size = UIManager.get("Spinner.borderSize") != null ? (Float) UIManager.get("Spinner.borderSize") : 1.5f;
			stroke = new BasicStroke(size);
			color = UIManager.get("Spinner.borderColor") != null ? UIManager.getColor("Spinner.borderColor") : Color.BLACK;
		}
		
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
			update();
			
			Graphics2D G = (Graphics2D) g.create();
			double roundRect = 
					(c instanceof JButton ? UIManager.getInt("Button.borderRound") : 
					c instanceof JToggleButton ? UIManager.getInt("ToggleButton.borderRound") : 
					c instanceof JSpinner ? UIManager.getInt("Spinner.borderRound") :
					20
					);
			double X = 0;
			double Y = 0;
			double W = w-size-0.5f;
			double H = h-size-0.5f;
			RoundRectangle2D R = new RoundRectangle2D.Double(X, Y, W, H, roundRect, roundRect);
			G.setStroke(stroke);
			G.setColor(color);
			G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			G.draw(R);
			
			G.dispose();
		}
		
		public Insets getBorderInsets(Component c, Insets newInsets){
			int i = size.intValue();
			newInsets.set(i, i, i, i);
			return newInsets;
		}
	}
	@SuppressWarnings("serial")
	public static class ComboBoxBorder extends AbstractBorder implements UIResource{
		private BasicStroke stroke;
		private Float size;
		private Color color;
		
		public ComboBoxBorder(){update();}
		public ComboBoxBorder(float size, Color c){
			this.size = size;
			stroke = new BasicStroke(size);
			color = c;
		}
		
		void update(){
			size = UIManager.get("ComboBox.borderSize") != null ? (Float) UIManager.get("ComboBox.borderSize") : 1.5f;
			stroke = new BasicStroke(size);
			color = UIManager.get("ComboBox.borderColor") != null ? UIManager.getColor("ComboBox.borderColor") : Color.BLACK;
		}
		
		private Shape createPath(Rectangle bounds){
			int roundRect = UIManager.getInt("ComboBox.borderRound");
			double x = bounds.getX();
			double y = bounds.getY();
			double w = bounds.getWidth();
			double h = bounds.getHeight();
			double minX = Math.min(roundRect, w/2)+x;
			double minY = Math.min(roundRect, h/2)+y;
			Path2D path = new Path2D.Double();
			path.moveTo(x, minY);
			path.quadTo(x, y, minX, y);
			path.lineTo(x+w, y);
			path.lineTo(x+w, y+h);
			path.lineTo(minX, y+h);
			path.quadTo(x, y+h, x, (y+h)-minY);
			path.closePath();
			return path;
		}
		
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
			update();
			
			Graphics2D G = (Graphics2D) g.create();
			G.setStroke(stroke);
			G.setColor(color);
			G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if(c instanceof JComboBox){
				JComboBox<?> comp = (JComboBox<?>) c;
				if (comp.getUI() instanceof BasicComboBoxUI){
					Field field = null;
					int scaleX = 0;
					try {
						field = BasicComboBoxUI.class.getDeclaredField("arrowButton");
						if(field!=null){
							field.setAccessible(true);
							JComponent button = (JComponent) field.get(comp.getUI());
							scaleX += c.getWidth() - button.getX();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					G.draw(createPath(new Rectangle(x, y, w-scaleX-1, h-1)));
				} else {
					G.draw(createPath(new Rectangle(x, y, w, h-1)));
				}
			} else {
				G.draw(createPath(new Rectangle(x, y, w, h-1)));
			}
			
			G.dispose();
		}
		
		public Insets getBorderInsets(Component c, Insets newInsets){
			int i = size.intValue();
			newInsets.set(i, i, i, i);
			return newInsets;
		}
	}
	@SuppressWarnings("serial")
	public static class PopupMenuBorder extends AbstractBorder implements UIResource{
		private BasicStroke stroke;
		private Float size;
		private Color color;
		
		public PopupMenuBorder(){update();}
		
		void update(){
			size = 10f;
			stroke = new BasicStroke(size);
			color = Color.BLACK;
		}
		
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
			
			Graphics2D G = (Graphics2D) g.create();
			double w2 = Geometry.regraDeTres(size, (double)w, 1d);
			double h2 = Geometry.regraDeTres(size, (double)h, 1d);
			G.setStroke(stroke);
			G.setColor(color);
			G.translate((double)w/2, (double)h/2);
			G.scale(1d-w2, 1d-h2);
			G.translate(-(double)w/2-w2, -(double)h/2-h2);
			G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			G.fillRect(0, 0, (int) w2, (int) h2);
			
			G.dispose();
		}
		
		public Insets getBorderInsets(Component c, Insets newInsets){
			int i = size.intValue();
			newInsets.set(i, i, i, i);
			return newInsets;
		}
	}
	
}

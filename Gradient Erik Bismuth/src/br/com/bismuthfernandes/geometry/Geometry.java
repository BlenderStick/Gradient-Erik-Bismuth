package br.com.bismuthfernandes.geometry;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Metodos geométricos
 * @author Erik Fernandes
 *
 */
public class Geometry {
	
	/**
	 * Calcula o angulo de A
	 * @param A - Ponto A
	 * @param B - Ponto B
	 * @param C - Ponto C
	 * @return Angulo de A
	 */
	public static double getAngle(Point A, Point B, Point C){
		double a = getDistance(B, C);
		double b = getDistance(A, C);
		double c = getDistance(A, B);
		
		double angulo = getAngle(a, b, c);
		
		return angulo;
	}

	/**
	 * Calcula o angulo de a
	 * @param A - Lado a
	 * @param B - Lado b
	 * @param C - Lado c
	 * @return Angulo de a
	 */
	public static double getAngle(double a, double b, double c){
		double cossA = getCosseno(a, b, c);
		double angulo = Math.toDegrees(Math.acos(cossA));

		return angulo;
	}

	public static double getSeno(double a, double b, double c){return ((b*b+c*c-a*a))/(2*b*c);}
	public static double getCosseno(double a, double b, double c){return ((b*b+c*c-a*a))/(2*b*c);}
	public static double getTangente(double a, double b, double c){return ((b*b+c*c-a*a))/(2*b*c);}
	public static double getCotangente(double a, double b, double c){return ((b*b+c*c-a*a))/(2*b*c);}

	public static double getDistance(Point A, Point B){return Math.sqrt(Math.pow(B.x-A.x, 2)+Math.pow(B.y-A.y, 2));}
	public static double getDistance(Point2D A, Point2D B){return Math.sqrt(Math.pow(B.getX()-A.getX(), 2)+Math.pow(B.getY()-A.getY(), 2));}
	public static double getDistance(double x1, double y1, double x2, double y2){return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));}

	/**
	 * Calcula a nova posição do ponto depois de uma rotação (angulo positivo = sentido horário, angulo negativo = sentido anti-horário)
	 * @param p1 - Ponto de controle
	 * @param p2 - Ponto a rotacionar
	 * @param angle - Angulo da rotação (Em graus)
	 * @return Novo ponto
	 */
	public static Point2D rotacionarPonto(Point2D p1, Point2D p2, double angle){
		double a = Math.toRadians(angle);
		double cos = Math.cos(a);
		double sin = Math.sin(a);
		double x1 = p2.getX()-p1.getX();
		double y1 = p2.getY()-p1.getY();
		double r = getDistance(0, 0, x1, y1);
		double x = r*cos;
		double y = r*sin;
		double x2 = x+p1.getX();
		double y2 = y+p1.getY();
		Point2D p = new Point2D.Double(x2, y2);
		return p;
	}
	
	/**
	 * Calcula a nova posição do ponto depois de uma rotação (angulo positivo = sentido horário, angulo negativo = sentido anti-horário)
	 * @param p1 - Ponto de controle
	 * @param p2 - Ponto a rotacionar
	 * @param angle - Angulo da rotação (Em graus)
	 * @return Novo ponto
	 */
	public static Point rotacionarPonto(Point p1, Point p2, double angle){
		double a = Math.toRadians(angle);
		double cos = Math.cos(a);
		double sin = Math.sin(a);
		double x1 = p2.getX()-p1.getX();
		double y1 = p2.getY()-p1.getY();
		double r = getDistance(0, 0, x1, y1);
		double x = r*cos;
		double y = r*sin;
		double x2 = x+p1.getX();
		double y2 = y+p1.getY();
		Point p = new Point((int)x2, (int)y2);
		return p;
	}
	
//	public static Point rotacionarPonto2(Point p1, Point p2, double angle){
//		double a = Math.toRadians(angle);
//		double cos = Math.cos(a);
//		double sin = Math.sin(a);
//		
//	}
	
	/**
	 * Calcula a nova posição do ponto depois de uma rotação (angulo positivo = sentido horário, angulo negativo = sentido anti-horário)
	 * @param x1 - Posição X de controle
	 * @param y1 - Posição Y de controle
	 * @param x2 - Posição X a rotacionar
	 * @param y2 - Posição Y a rotacionar
	 * @param angle - Angulo da rotação (Em graus)
	 * @return Uma matriz contendo 2 doubles [newX, newY]
	 */
	public static double[] rotacionarPonto(double x1, double y1, double x2, double y2, double angle){
		double a = Math.toRadians(angle);
		double cos = Math.cos(a);
		double sin = Math.sin(a);
		double xR1 = x1-x2;
		double yR1 = y1-y2;
		double r = getDistance(0, 0, xR1, yR1);
		double x = r*cos;
		double y = r*sin;
		double xR2 = x+x1;
		double yR2 = y+y1;
		double[] result = {xR2, yR2};
		return result;
	}
	
	/**
	 * Calcula o valor de down1 que é equivalente ao X
	 * top1 --- top2
	 *  x   --- down2
	 * @param top1
	 * @param top2
	 * @param down2
	 * @return
	 */
	public static double regraDeTres(double top1, double top2, double down2){return (top1*down2)/top2;}
}

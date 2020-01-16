package com.collisionsimulator.simulator;

import java.awt.Shape;

/**
 * Estrutura de Geometria
 * @author Erik Fernandes
 *
 */
public interface StructureSystem {
	
	/**
	 * Adiciona um ponto ao final da lista
	 * @param x
	 * @param y
	 */
	public void addPoint(double x, double y);
	/**
	 * Adiciona um ponto em um indice expec�fico da lista
	 * @param x
	 * @param y
	 * @param index
	 */
	public void addPoint(double x, double y, int index);
	/**
	 * Remove um ponto em um indice expec�fico na lista
	 * @param index
	 */
	public void removePoint(int index);
	/**
	 * Obt�m a posi��o em x do ponto da lista
	 * @param index
	 * @return
	 */
	public double getPointX(int index);
	/**
	 * Obt�m a posi��o em y do ponto da lista
	 * @param index
	 * @return
	 */
	public double getPointY(int index);
	/**
	 * Move o ponto expecificado para novas coordenadas(x, y)
	 * @param newX - Novas coordenadas em X
	 * @param newY - Novas coordenadas em Y
	 * @param index - Indice do ponto
	 */
	public void movePoint(double newX, double newY, int index);
	/**
	 * Retorna o indice do ponto(x, y) na lista, se o ponto n�o existir, o retorno ser� -1
	 * @param x
	 * @param y
	 * @return
	 */
	public int getPointIndex(double x, double y);
	
	public double[] pointsX();
	public double[] pointsY();
	
	/**
	 * Deixa a geometria visivelmente aberta
	 */
	public void openGeometry();
	/**
	 * Deixa a geometria visivelmente fechada
	 */
	public void closeGeometry();

	/**
	 * Verifica a ocorr�ncia de colis�o com um UnitSystem
	 * @param unit
	 * @return
	 */
	public boolean isCollided(UnitSystem unit);
	/**
	 * Verifica a ocorr�ncia de colis�o com um circulo hipot�tico
	 * @param x - posi��o x do circulo
	 * @param y - posi��o y do circulo
	 * @param radiu - raio do circulo
	 * @return Indice do segundo ponto da reta insidente, se n�o houver intersec��o, o retorno � -1
	 */
	public int isCollided(double x, double y, double radiu);
	
	public Shape shape();
	public Shape selectShape();
}

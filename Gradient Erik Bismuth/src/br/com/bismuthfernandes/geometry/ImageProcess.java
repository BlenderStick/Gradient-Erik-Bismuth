package br.com.bismuthfernandes.geometry;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public final class ImageProcess {

	private ImageProcess() {}

	public static boolean contains(BufferedImage image, int x, int y, int alfaLimit){
		if((x<0 || y<0) || (x+1>image.getWidth()||y+1>image.getHeight()))
				return false;
//		image.createGraphics().
//		image.get
		Raster ra = image.getRaster();
		int[] i = ra.getPixel(x, y, new int[255]);
		int a = i[3];
		return a>alfaLimit;
	}
	
	public static Color getPixelColor(BufferedImage image, int x, int y){
		Raster ra = image.getRaster();
		int[] i = ra.getPixel(x, y, new int[255]);
		int r = i[0];
		int g = i[1];
		int b = i[2];
		int a = i[3];
		return new Color(r, g, b, a);
	}

}

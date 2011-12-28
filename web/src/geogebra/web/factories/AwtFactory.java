package geogebra.web.factories;

import geogebra.common.awt.AffineTransform;
import geogebra.common.awt.BasicStroke;
import geogebra.common.awt.BufferedImageAdapter;
import geogebra.common.awt.Color;
import geogebra.common.awt.Dimension;
import geogebra.common.awt.GeneralPath;
import geogebra.common.awt.Line2D;
import geogebra.common.awt.Point2D;
import geogebra.common.awt.Rectangle;
import geogebra.common.awt.Rectangle2D;

public class AwtFactory extends geogebra.common.factories.AwtFactory {

	@Override
	public Color newColor(int RGB) {
		return new geogebra.web.awt.Color(RGB);
	}

	@Override
	public Color newColor(int red, int green, int blue) {
		return new geogebra.web.awt.Color(red, green, blue);
	}

	@Override
	public Color newColor(int red, int green, int blue, int alpha) {
		return new geogebra.web.awt.Color(red, green, blue, alpha);
	}

	@Override
	public Color newColor(float red, float green, float blue, float alpha) {
		return new geogebra.web.awt.Color(red,green,blue,alpha);
	}

	@Override
	public Color newColor(float red, float green, float blue) {
		return new geogebra.web.awt.Color(red, green, blue);
	}

	@Override
	public AffineTransform newAffineTransform() {
		return new geogebra.web.awt.AffineTransform();
	}

	@Override
	public Rectangle2D newRectangle() {
		return new geogebra.web.awt.Rectangle2D();
	}

	@Override
	public Rectangle newRectangle(int x, int y, int w, int h) {
		return new geogebra.web.awt.Rectangle(x, y, w, h);
	}

	@Override
	public BufferedImageAdapter newBufferedImage(int pixelWidth,
	        int pixelHeight, int typeIntArgb) {
		return new geogebra.web.awt.BufferedImage(pixelWidth, pixelHeight, typeIntArgb);
	}

	@Override
	public Dimension newDimension(int width, int height) {
		return new geogebra.web.awt.Dimension(width, height);
	}

	@Override
	public Point2D newPoint2D() {
		return new geogebra.web.awt.Point2D();
	}
	
	@Override
	public Point2D newPoint2D(double x, double y) {
		return new geogebra.web.awt.Point2D(x, y);
	}
	
	@Override
	public Rectangle newRectangle(int x, int y) {
		return new geogebra.web.awt.Rectangle(x, y);
	}

	@Override
	public GeneralPath newGeneralPath() {
		return new geogebra.web.awt.GeneralPath();
	}

	@Override
	public BasicStroke newMyBasicStroke(float f) {
		return new geogebra.web.awt.BasicStroke(f,geogebra.web.awt.BasicStroke.CAP_ROUND,geogebra.web.awt.BasicStroke.JOIN_ROUND);
	}

	@Override
	public BasicStroke newBasicStroke(float width, int endCap, int lineJoin,
	        float miterLimit, float[] dash, float f) {
		return new geogebra.web.awt.BasicStroke(width, endCap, lineJoin, miterLimit, dash, f);
	}

	@Override
	public Line2D newLine2D() {
		return new geogebra.web.awt.Line2D();
	}

	@Override
	public Rectangle newRectangle(Rectangle bb) {
		return new geogebra.web.awt.Rectangle(bb);
	}

}

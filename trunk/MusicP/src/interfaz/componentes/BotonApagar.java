package interfaz.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;

public class BotonApagar extends JButton {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape cuadrado;
	private Shape brillo;
	private Shape boton;
	//puntos para el degradado
	private Point2D punto1;
	private Point2D punto2;

	public BotonApagar() {
		boton = construirBoton();
		cuadrado = construirCuadrado();
		brillo = construirBrillo();
		punto1=new Point2D.Float(0, 27);
		punto2=new Point2D.Float(0, 53);
		this.setToolTipText("Cerrar");
		this.setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.fill(boton);


		ButtonModel bm = this.getModel();
		if (bm.isArmed()) {
			// cuando se oprimio
			g2.setColor(new Color(51, 51, 51));
			g2.draw(boton);

			g2.setStroke(new BasicStroke(2));
			g2.setColor(new Color(102, 102, 102));
			g2.draw(cuadrado);

			g2.setPaint(construirDegradado(new Color(102, 102, 102)));
		} else {
			if (bm.isRollover()) {
				// cuando hay rollover
				g2.setColor(new Color(0, 0, 153));
				g2.draw(boton);

				g2.setStroke(new BasicStroke(2));
				g2.setColor(new Color(204, 204, 204));
				g2.draw(cuadrado);

				g2.setPaint(construirDegradado(new Color(204, 204, 204)));
			} else {
				// al no estar haciendo nada
				g2.draw(boton);

				g2.setStroke(new BasicStroke(2));
				g2.setColor(new Color(153, 153, 153));
				g2.draw(cuadrado);

				g2.setPaint(construirDegradado(new Color(153, 153, 153)));
			}
		}

		g2.fill(brillo);

	}

	private Shape construirBoton() {
		Shape boton = new Ellipse2D.Float(0, 0, 53, 53);
		return boton;
	}

	private Shape construirBrillo() {
		Shape brillo=boton;
		Shape corte=new Ellipse2D.Float(-8, -8, 68, 50);

		Area a1=new Area(brillo);
		Area a2=new Area(corte);

		a1.subtract(a2);
		return a1;
	}

	private Shape construirCuadrado() {
		Shape cuadrado = new RoundRectangle2D.Float(14, 14, 26, 26, 18, 18);

		return cuadrado;
	}

	private Paint construirDegradado(Color c) {
		Color color1=new Color(0.153f, 0.153f, 0.153f, 1f);

		Paint p=new GradientPaint(punto1, color1, punto2, c);

		return p;
	}
}

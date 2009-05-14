package interfaz.componentes.panelReproductor.slider;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

public class Boton extends JButton {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape boton;
	private Paint gradiente;

	public Boton() {
		this.setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (boton == null) {
			boton = new Ellipse2D.Float(0, 0, this.getWidth(), this.getHeight());
			gradiente = new GradientPaint(0, 0, Color.DARK_GRAY, 0, this
					.getHeight(), Color.WHITE);
		}
		g2.setPaint(gradiente);
		g2.fill(boton);
	}

}

package interfaz.componentes.panelReproductor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.QuadCurve2D;

import javax.swing.JButton;

public class BotonVolumen extends JButton {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape parlante;
	private Shape sonido1;
	private Shape sonido2;

	public BotonVolumen() {
		this.setOpaque(false);
		this.setToolTipText("Volumen");
		inicializarShapes();
	}

	private void inicializarShapes() {
		int[] xP = new int[] { 13, 13, 8, 2, 2, 8 };
		int[] yP = new int[] { 1, 20, 15, 15, 8, 8 };
		parlante = new Polygon(xP, yP, 6);

		sonido1 = new QuadCurve2D.Float(16, 8, 20, 11, 16, 14);
		sonido2 = new QuadCurve2D.Float(19, 3, 22, 11, 19, 17);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.WHITE);

		g2.fill(parlante);
		g2.fill(sonido1);
		g2.fill(sonido2);
	}

}

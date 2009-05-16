package interfaz.componentes.panelReproductor;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JButton;

public class BotonEsconder extends JButton {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final boolean SUBIR = true;
	public static final boolean BAJAR = false;
	private Shape bajar;
	private Shape arriba;

	private Paint degradadoSubir;
	private Paint degradadoBajar;

	public boolean subir = false;

	public BotonEsconder() {
		this.setOpaque(false);

		inicializarShapes();
	}

	private void inicializarShapes() {
		int[] xP = new int[] { 1, 13, 7 };
		int[] yP = new int[] { 4, 4, 11 };

		bajar = new Polygon(xP, yP, 3);

		yP = new int[] { 11, 11, 4 };
		arriba = new Polygon(xP, yP, 3);

		degradadoBajar = new GradientPaint(0, 4, Color.WHITE, 0, 11, Color.GRAY);
		degradadoSubir = new GradientPaint(0, 4, Color.GRAY, 0, 11, Color.WHITE);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (subir) {
			g2.setPaint(degradadoSubir);
			g2.fill(arriba);
		} else {
			g2.setPaint(degradadoBajar);
			g2.fill(bajar);
		}
	}

	public void setTipo(boolean t) {
		subir = t;
	}

}

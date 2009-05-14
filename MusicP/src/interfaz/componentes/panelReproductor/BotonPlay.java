package interfaz.componentes.panelReproductor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

public class BotonPlay extends JButton {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape play;
	private Shape pause1;
	private Shape pause2;
	private boolean reproduciendo = false;

	public BotonPlay() {
		this.setOpaque(false);
		this.setToolTipText("Reproducir");
		iniciarShapes();
	}

	private void iniciarShapes() {
		int[] xP = new int[] { 5, 5, 20 };
		int[] yP = new int[] { 2, 20, 11 };
		play = new Polygon(xP, yP, 3);

		pause1 = new Rectangle2D.Float(6, 2, 3, 18);
		pause2 = new Rectangle2D.Float(14, 2, 3, 18);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		if (reproduciendo) {
			g2.fill(pause1);
			g2.fill(pause2);
		} else {
			g2.fill(play);
		}

	}

	public void reproducir() {
		reproduciendo=!reproduciendo;
	}

}

package interfaz.componentes.pantalla;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JButton;

public class Estrella extends JButton {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape estrella;
	private Color color;

	public void setColor(Color c) {
		this.color = c;
		this.repaint();
	}

	public Estrella() {
		this.setOpaque(false);

		int[] xP = new int[] { 9, 12, 18, 13, 15, 9, 3, 5, 0, 6 };
		int[] yP = new int[] { 0, 6, 6, 11, 17, 13, 17, 11, 6, 6 };
		estrella = new Polygon(xP, yP, 10);

		color = Color.WHITE;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(color);
		g2.fill(estrella);

	}

}

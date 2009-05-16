package interfaz.componentes.panelReproductor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.QuadCurve2D;

import javax.swing.JButton;

public class BotonVolumen extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Area icono;

	private boolean mouseIn;

	public BotonVolumen() {
		this.setOpaque(false);
		this.setToolTipText("Volumen");
		inicializarShapes();
	}

	private void inicializarShapes() {
		int[] xP = new int[] { 13, 13, 8, 2, 2, 8 };
		int[] yP = new int[] { 1, 20, 15, 15, 8, 8 };
		Shape parlante = new Polygon(xP, yP, 6);

		Shape sonido1 = new QuadCurve2D.Float(16, 8, 20, 11, 16, 14);
		Shape sonido2 = new QuadCurve2D.Float(19, 3, 22, 11, 19, 17);

		icono = new Area(parlante);
		icono.add(new Area(sonido1));
		icono.add(new Area(sonido2));
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(mouseIn){
			g2.scale(1.1, 1.1);
			g2.translate(-1, -1);
		}
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.WHITE);
		g2.fill(icono);
	}
	
	public void mouseEntered() {
		mouseIn = true;
		repaint();

	}

	public void mouseExited() {
		mouseIn = false;
		repaint();

	}

}

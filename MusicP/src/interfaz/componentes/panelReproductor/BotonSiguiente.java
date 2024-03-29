package interfaz.componentes.panelReproductor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

public class BotonSiguiente extends JButton {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Area icono;
	private int tipo;
	private boolean mouseIn;

	public static final int ADELANTAR = 0;
	public static final int ATRASAR = 1;

	public BotonSiguiente(int tipo) {
		this.tipo=tipo;
		this.setOpaque(false);
		if (tipo == ATRASAR) {
			this.setToolTipText("Atrasar");
		} else {
			this.setToolTipText("Adelantar");
		}
		iniciarShapes();
	}

	private void iniciarShapes() {
		int[] xP = new int[] { 1, 8, 1 };
		int[] yP = new int[] { 2, 11, 20 };
		Shape triangulo1 = new Polygon(xP, yP, 3);

		xP = new int[] { 9, 16, 9 };
		yP = new int[] { 2, 11, 20 };
		Shape triangulo2 = new Polygon(xP, yP, 3);

		Shape barra = new Rectangle2D.Float(17, 2, 3, 18);
		
		icono=new Area(triangulo1);
		icono.add(new Area(triangulo2));
		icono.add(new Area(barra));
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		if(mouseIn){
			g2.scale(1.1, 1.1);
			g2.translate(-1, -1);
		}
		if(tipo==ATRASAR){
			g2.translate(22, 22);
			g2.rotate(Math.PI);
		}
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

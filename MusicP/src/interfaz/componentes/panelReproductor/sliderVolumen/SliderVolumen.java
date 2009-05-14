package interfaz.componentes.panelReproductor.sliderVolumen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SliderVolumen extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape triangulo;
	private JButton boton;
	private Paint gradiente;
	private Polygon fondo;
	protected int yi;

	public SliderVolumen() {
		this.setOpaque(false);
		this.setLayout(null);
		inicializarShapes();
		ponerDragable();
	}

	private void ponerDragable() {
		boton.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent evt) {
				int y = evt.getYOnScreen() - yi;
				if (y <= 50 && y >= 0)
					evt.getComponent()
							.setLocation(evt.getComponent().getX(), y);
			}
		});

		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				yi = evt.getYOnScreen() - evt.getComponent().getY();
			}
		});

	}

	private void inicializarShapes() {
		int[] xP = new int[] { 21, 2, 2 };
		int[] yP = new int[] { 1, 1, 53 };
		triangulo = new Polygon(xP, yP, 3);

		gradiente = new GradientPaint(0, 1, Color.RED, 0, 53, Color.GREEN);

		boton = new BotonSliderVolumen();
		boton.setBounds(0, 23, 23, 6);
		this.add(boton);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setClip(triangulo);

		int[] xP = new int[] { 21, 2, 2 };
		int[] yP = new int[] { (int) boton.getLocation().getY(),
				(int) boton.getLocation().getY(), 53 };
		fondo = new Polygon(xP, yP, 3);

		g2.setPaint(gradiente);
		g2.fill(fondo);

		g2.setClip(new Rectangle2D.Float(0, 0, this.getWidth(), this
				.getHeight()));

		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(2));
		g2.draw(triangulo);
	}

}

package interfaz.componentes.panelReproductor.slider;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Slider extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape trackBar;
	private Shape fillBar;
	private JComponent boton;
	private Paint gradiente1;
	private Paint gradiente2;
	protected int xi;
	protected int yi;

	public Slider() {
		this.setLayout(null);
		this.setOpaque(false);
	}

	private void inicializarShapes() {
		trackBar = new RoundRectangle2D.Float(2, this.getHeight() / 2 - 2, this
				.getWidth() - 4, 4, 20, 20);
		fillBar = new RoundRectangle2D.Float();
		boton = new Boton();
		boton.setBounds(0, 0, 10, 10);
		this.add(boton);

		gradiente1 = new GradientPaint(0, this.getHeight() / 2 - 2,
				Color.DARK_GRAY, 0, this.getHeight() + 2, Color.WHITE);
		gradiente2 = new GradientPaint(0, this.getHeight() / 2 - 2, Color.BLUE
				.brighter().brighter().brighter(), 0, this.getHeight() + 2, Color.LIGHT_GRAY);

		ponerBotonDragable();
	}

	private void ponerBotonDragable() {
		boton.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent evt) {
				int x = evt.getXOnScreen() - xi;
				if (x >= 0 && x <= 231)
					evt.getComponent().setLocation(evt.getXOnScreen() - xi,
							evt.getComponent().getY());
			}
		});

		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				xi = evt.getXOnScreen() - evt.getComponent().getX();
			}
		});
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (fillBar == null) {
			inicializarShapes();
		}
		g2.setPaint(gradiente1);
		g2.fill(trackBar);

		fillBar = new RoundRectangle2D.Float(2, this.getHeight() / 2 - 2,
				(int) boton.getLocation().getX() + 5, 4, 20, 20);

		g2.setPaint(gradiente2);
		g2.fill(fillBar);
	}
}

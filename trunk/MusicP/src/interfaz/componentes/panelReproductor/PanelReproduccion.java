package interfaz.componentes.panelReproductor;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelReproduccion extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape cuadrado;
	private BufferedImage sombra;
	private boolean visible = true;
	private Timer temp;
	private final int velocidad = 1;

	public PanelReproduccion() {
		this.setLayout(null);
		cuadrado = construirCuadrado();
		sombra = construirSombra();
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		temp = new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				animar();
			}
		});
	}

	protected void animar() {
		int x = (int) this.getLocation().getX();
		int y = (int) this.getLocation().getY();
		if (visible) {
			y += velocidad;
		} else {
			y -= velocidad;
		}
		this.setLocation(x, y);
		if (y <= 227 || y >= 300) {
			temp.stop();
			visible = !visible;
		}
	}

	public void empezarAnimacion() {
		temp.start();
	}

	private BufferedImage construirSombra() {
		Rectangle2D b = cuadrado.getBounds2D();
		BufferedImage sombra = new BufferedImage((int) b.getWidth() + 20,
				(int) b.getHeight() + 30, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = sombra.createGraphics();

		g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
		g2.setColor(Color.DARK_GRAY);
		g2.translate(5, 5);
		Shape cuadrado = new RoundRectangle2D.Float(0, 0, 265, 68, 20, 20);
		g2.fill(cuadrado);

		sombra = getBlurFilter(5).filter(sombra, null);

		return sombra;
	}

	private Shape construirCuadrado() {
		Shape cuadrado = new RoundRectangle2D.Float(1, 1, 262, 66, 20, 20);
		return cuadrado;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Composite oldComposite = g2.getComposite();
		//
		// g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
		// g2.setColor(Color.darkGray);
		// g2.translate(6, 6);
		// g2.fill(cuadrado);
		//
		// g2.setComposite(oldComposite);
		// g2.translate(-6, -6);

		g2.drawImage(sombra, null, this);

		g2.setClip(new Rectangle2D.Float(0, 0, 262, 33));
		g2.setColor(new Color(0.51f, 0.51f, 0.51f, 0.3f));
		g2.fill(cuadrado);

		g2.setClip(new Rectangle2D.Float(0, 33, 262, 33));
		g2.setColor(new Color(0, 0, 0, 0.7f));
		g2.fill(cuadrado);

		g2.setClip(new Rectangle2D.Float(0, 0, this.getWidth(), this
				.getHeight()));
		g2.setColor(new Color(153, 153, 153));
		g2.setStroke(new BasicStroke(2));
		g2.draw(cuadrado);
	}

	public static ConvolveOp getBlurFilter(int radius) {
		if (radius < 1) {
			throw new IllegalArgumentException("Radius must be >= 1");
		}
		int size = radius * 2 + 1;
		float weight = 1.0f / (size * size);
		float[] data = new float[size * size];
		for (int i = 0; i < data.length; i++) {
			data[i] = weight;
		}
		Kernel kernel = new Kernel(size, size, data);
		return new ConvolveOp(kernel);
	}

}

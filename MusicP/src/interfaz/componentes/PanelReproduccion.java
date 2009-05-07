package interfaz.componentes;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class PanelReproduccion extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape cuadrado;

	public PanelReproduccion() {
		cuadrado=construirCuadrado();
	}

	private Shape construirCuadrado() {
		Shape cuadrado=new RoundRectangle2D.Float(1, 1, 262, 66, 20, 20);
		return cuadrado;
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Composite oldComposite=g2.getComposite();

		g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
		g2.setColor(Color.darkGray);
		g2.translate(6, 6);
		g2.fill(cuadrado);

		g2.setComposite(oldComposite);
		g2.translate(-6, -6);

		g2.setClip(new Rectangle2D.Float(0, 0, 262, 33));
		g2.setColor(new Color(0.51f, 0.51f, 0.51f, 0.3f));
		g2.fill(cuadrado);

		g2.setClip(new Rectangle2D.Float(0, 33, 262, 33));
		g2.setColor(new Color(0, 0, 0, 0.7f));
		g2.fill(cuadrado);

		g2.setClip(new Rectangle2D.Float(0, 0, this.getWidth(), this.getHeight()));
		g2.setColor(new Color(153, 153, 153));
		g2.setStroke(new BasicStroke(2));
		g2.draw(cuadrado);
	}

}

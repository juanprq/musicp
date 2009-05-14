package interfaz.componentes.panelReproductor.sliderVolumen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class PanelVolumen extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape fondo;
	private SliderVolumen slider;

	public PanelVolumen() {
		this.setOpaque(false);
		this.setLayout(null);
		fondo = new RoundRectangle2D.Float(2, 2, 30, 70, 10, 10);
		slider = new SliderVolumen();
		slider.setBounds(6, 11, 23, 56);
		this.add(slider);
		this.setVisible(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(new Color(0.51f, 0.51f, 0.51f, 0.3f));
		g2.fill(fondo);

		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.WHITE);
		g2.draw(fondo);

	}

	public void ver(){
		boolean visible=!this.isVisible();
		this.setVisible(visible);
	}
}

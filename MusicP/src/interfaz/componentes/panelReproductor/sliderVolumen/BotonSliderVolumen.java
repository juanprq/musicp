package interfaz.componentes.panelReproductor.sliderVolumen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

public class BotonSliderVolumen extends JButton {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape boton;

	public BotonSliderVolumen() {
		this.setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (boton == null)
			inicializarShapes();
		g2.setColor(Color.WHITE);
		g2.fill(boton);
	}

	private void inicializarShapes() {
		boton = new Rectangle2D.Float(0, 0, this.getWidth(), this.getHeight());
	}

}

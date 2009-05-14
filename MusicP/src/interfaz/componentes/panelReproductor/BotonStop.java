package interfaz.componentes.panelReproductor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class BotonStop extends JButton {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape cuadrado;

	public BotonStop() {
		this.setOpaque(false);
		this.setToolTipText("Parar");
		cuadrado = new RoundRectangle2D.Float(2, 2, 18, 18, 0, 0);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fill(cuadrado);
	}
}
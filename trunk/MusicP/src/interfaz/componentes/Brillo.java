package interfaz.componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

public class Brillo extends JComponent {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Image imagen;

	public Brillo() {
		imagen=Toolkit.getDefaultToolkit().getImage("imagenes/brillo.png");
	}

	public void paintComponent(Graphics g) {
		g.drawImage(imagen, 23, 33, this);
	}

}

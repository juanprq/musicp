package interfaz.componentes.menuPrincipal;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FondoFalso2 extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Image imagen;

	public FondoFalso2() {
		this.setOpaque(false);
		imagen = new ImageIcon("imagenes/fondo.png").getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(imagen, -this.getX(), 0, this);
	}

}

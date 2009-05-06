package interfaz;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Fondo extends JPanel{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Image imagen;

	public Fondo() {
		imagen=Toolkit.getDefaultToolkit().getImage("imagenes/fondo.png");
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(imagen, 0, 0, this);
	}

}

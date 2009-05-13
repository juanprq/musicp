package interfaz.componentes;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FondoFalso extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1l;
	private Image imagen;

	public FondoFalso() {
		imagen=new ImageIcon("imagenes/fondo.png").getImage();
	}


	public void paintComponent(Graphics g){
		g.drawImage(imagen, -107, -284,this);
	}

}

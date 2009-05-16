package interfaz.componentes.ListaReproduccion;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class BarraHerramientas  extends JPanel{
	/**
	 * @author Juan Pablo
	 */
	private static final long serialVersionUID = 1L;

	public BarraHerramientas() {
		this.setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		// dibujar lineas
		g2.drawLine(0, 0, getWidth(), 0);
		g2.drawLine(0, getHeight() - 3, getWidth(), getHeight() - 3);

		// dibujar segunda linea
		g2.setColor(new Color(130, 130, 128));
		g2.drawLine(0, 1, getWidth(), 1);

		// dibujar tercera linea
		g2.setColor(new Color(107, 105, 101));
		g2.drawLine(0, 2, getWidth(), 2);

		// llenar el gradiente de arriba
		int puntoMedio = getHeight() / 2;
		int temp_heigth = puntoMedio - 2;
		g2.setPaint((new GradientPaint(0, 3, new Color(78, 78, 77), 0,
				puntoMedio, new Color(44, 44, 45))));
		g2.fillRect(0, 3, getWidth(), temp_heigth);

		// dibujar linea en la mitad
		g2.setColor(Color.BLACK);
		g2.drawLine(0, puntoMedio + 1, getWidth(), puntoMedio + 1);

		// llenar el gradiente de abajo
		temp_heigth = getHeight() - (puntoMedio + 4);
		g2.setPaint(new GradientPaint(0, puntoMedio + 2, Color.BLACK, 0,
				puntoMedio + 2 + temp_heigth, new Color(44, 44, 44)));
		g2.fillRect(0, puntoMedio + 2, getWidth(), temp_heigth);

		// linea final
		g2.setColor(new Color(30, 30, 30));
		g2.drawLine(0, getHeight() - 2, getWidth(), getHeight() - 2);
	}
}

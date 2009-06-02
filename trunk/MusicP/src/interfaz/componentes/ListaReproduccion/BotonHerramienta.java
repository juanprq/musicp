package interfaz.componentes.ListaReproduccion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;

/**
 *
 * @author Juan Pablo
 */
public class BotonHerramienta extends JButton {

	private static final long serialVersionUID = 1L;
	private String label;

	public BotonHerramienta() {
		setOpaque(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setForeground(Color.WHITE);
	}

	@Override
	public void setText(String label) {
		super.setText("holaaaa");
		this.label = label;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
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

		ButtonModel bModel = getModel();

		Shape cuadrado = new Rectangle2D.Float(-3, -3, this.getWidth()+3, this
				.getHeight()+3);
		g2.setColor(Color.DARK_GRAY);
		g2.setStroke(new BasicStroke(3));
		g2.draw(cuadrado);

		// texto
		Font f = new Font("Arial", Font.BOLD, 12);
		TextLayout tl = new TextLayout(label, f, g2.getFontRenderContext());
		g2.setColor(Color.WHITE);
		Rectangle2D r2 = tl.getBounds();
		int x = (int) (getWidth() * 0.5 - r2.getCenterX());
		int y = (int) (getHeight() * 0.5 - r2.getCenterY());
		tl.draw(g2, x, y);

		if (bModel.isArmed()) {
			// cuando se oprimio
		} else if (bModel.isRollover()) {
			Color c1 = new Color(255, 255, 255, 100);
			Color c2 = new Color(255, 255, 255, 0);
			// cuando hay rollover
			Paint gradiente = new GradientPaint(0, 0, c1, 0,
					this.getHeight() / 2, c2);
			g2.setPaint(gradiente);
			Shape rec = new Rectangle2D.Float(0, 0, this.getWidth(), this
					.getHeight() / 2);
			g2.fill(rec);
		}
	}
}

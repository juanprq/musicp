package interfaz.componentes.dialogos.descarga;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class PanelFondo extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape fondo;
	private Paint gradiente;

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (fondo == null) {
			construirGeometrias();
		}

		g2.setPaint(gradiente);
		g2.fill(fondo);

	}

	private void construirGeometrias() {
		fondo = new Rectangle2D.Float(0, 0, this.getWidth(), this.getHeight());
		gradiente = new GradientPaint(0, 0, Color.BLACK, 0, this.getHeight(),
				Color.GRAY);
	}

}

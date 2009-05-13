package interfaz.componentes;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JComponent;

public class Brillo extends JComponent {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Color c1;
	private Color c2;

	public Brillo() {
		c1=new Color(255, 255, 255, 80);
		c2=new Color(255, 255, 255, 0);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Paint p=new GradientPaint(0, 0, c1, 0, 140, c2);
		g2.setPaint(p);

		GeneralPath gp=new GeneralPath();
		gp.moveTo(0, 0);
		gp.lineTo(getWidth(), 0);
		gp.lineTo(getWidth(), 100);
		gp.quadTo(getWidth()*3/4, 150, getWidth()/2, 100);
		gp.quadTo(getWidth()/4, 50, 0, 100);
		gp.lineTo(0, 0);

		g2.fill(gp);
	}

}

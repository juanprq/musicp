package interfaz.componentes.dialogos.descarga;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class BarraProgreso extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Shape trackBar;
	private Shape fillBar;
	private Paint gradiente1;
	private Paint gradiente2;
	private int progreso;

	private void construirGeometrias() {
		trackBar = new RoundRectangle2D.Float(2, this.getHeight() / 2 - 2, this
				.getWidth() - 4, 6, 20, 20);
		fillBar = new RoundRectangle2D.Float();

		gradiente1 = new GradientPaint(0, this.getHeight() / 2 - 2,
				Color.DARK_GRAY, 0, this.getHeight() + 2, Color.WHITE);
		gradiente2 = new GradientPaint(0, this.getHeight() / 2 - 2, Color.BLUE
				.brighter().brighter().brighter(), 0, this.getHeight() + 2, Color.LIGHT_GRAY);
	}

	public final int getProgreso() {
		int resultado=progreso/getWidth()*100;
		return resultado;
	}

	public final void setProgreso(int progreso) {
		progreso=this.getWidth()*progreso/100;
		this.progreso = progreso;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D) g;
		if(trackBar==null)
			construirGeometrias();

		g2.setPaint(gradiente1);
		g2.fill(trackBar);

		fillBar=new RoundRectangle2D.Float(2, this.getHeight()/2-2, progreso-2, 6, 20, 20);

		g2.setPaint(gradiente2);
		g2.fill(fillBar);

	}

}

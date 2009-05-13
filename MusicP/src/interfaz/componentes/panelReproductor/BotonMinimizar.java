/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz.componentes.panelReproductor;

import java.awt.AlphaComposite;
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
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;


/**
 *
 * @author Juan Pablo
 */
public class BotonMinimizar extends JButton {

	private static final long serialVersionUID = 1L;
	private int xRoundness = 0;
	private int yRoundness = 0;
	private Color background;
	private Color shineColor;
	private String label;

	public BotonMinimizar() {
		setOpaque(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		this.setToolTipText("Minimizar");
		setForeground(Color.WHITE);
		background = new Color(0x333333).darker().darker().darker();
		shineColor = Color.WHITE;
	}

	@Override
	public void setText(String label){
		this.label=label;
	}

	public int getXRoundness() {
		return xRoundness;
	}

	public void setXRoundness(int xRoundness) {
		this.xRoundness = xRoundness;
	}

	public int getYRoundness() {
		return yRoundness;
	}

	public void setYRoundness(int yRoundness) {
		this.yRoundness = yRoundness;
	}

	public void setColor(Color color) {
		this.background = color;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ButtonModel bModel = getModel();
		g2.setComposite(AlphaComposite.SrcOver.derive(0.3f));

		RoundRectangle2D boton = new RoundRectangle2D.Float(0, 0, getWidth(),
				getHeight(), xRoundness, yRoundness);

		if (bModel.isArmed()) {
			// cuando se oprimio
			g2.setColor(background.darker());
		} else {
			if (bModel.isRollover()) {
				// cuando hay rollover
				g2.setColor(background.brighter());
			} else {
				// al no estar haciendo nada
				g2.setColor(background);
			}
		}
		// pintar el boton
		g2.fill(boton);

		//texto
		Font f=new Font("Arial", Font.BOLD, 12);
		TextLayout tl=new TextLayout(label, f, g2.getFontRenderContext());
		g2.setColor(Color.WHITE);
		Rectangle2D r2=tl.getBounds();
		int x=(int)(getWidth()*0.5-r2.getCenterX());
		int y=(int)(getHeight()*0.5-r2.getCenterY());
		tl.draw(g2, x, y+5);
		// brillo
		int largo = getWidth() - 2;
		int alto = (int) (getHeight() * 0.5);

		// hacer el color degradado
		Color color1 = shineColor;
		Color color2 = new Color(0.255f, 0.255f, 0.255f, 0f);
		Paint fondo = new GradientPaint(0, -getHeight(), color1, 0, alto,
				color2);
		g2.setPaint(fondo);

		// dibujar la figura
		Shape shine = new RoundRectangle2D.Double(1, 1, largo, alto + 50,
				xRoundness, yRoundness);
		g2.fill(shine);

		// brillo inferior
		alto = (int) (getHeight() * 0.2);

		// posicion
		x = 1;
		y = getHeight() - alto - 1;

		// hacer el degradado
		fondo = new GradientPaint(0, y, color2, 0, getHeight() * 2, color1);
		g2.setPaint(fondo);

		// crear la figura
		shine = new RoundRectangle2D.Float(x, y - 10, largo, alto + 10,
				xRoundness, yRoundness);
		g2.fill(shine);
	}
}

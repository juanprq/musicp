package interfaz.componentes.menuPrincipal;

import interfaz.componentes.panelReproductor.BotonMinimizar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MenuPrincipal extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Shape fondo;
	private JButton botonMinimizar;
	private boolean visible = true;
	private Timer temp;
	private final int velocidad = 1;

	public MenuPrincipal() {
		this.setOpaque(false);
		this.setLayout(null);
		fondo = new RoundRectangle2D.Float(1, -10, 262, 53, 20, 20);
		botonMinimizar = new BotonMinimizar();
		botonMinimizar.setText("_");
		botonMinimizar.setBounds(239, 30, 12, 12);
		botonMinimizar.setToolTipText("Ocultar menú");
		this.add(botonMinimizar);

		temp = new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				animar();
			}
		});

		botonMinimizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				empezarAnimacion();
			}
		});
	}

	protected void empezarAnimacion() {
		temp.start();
	}

	protected void animar() {
		int x = (int) this.getLocation().getX();
		int y = (int) this.getLocation().getY();
		if (visible) {
			y -= velocidad;
		} else {
			y += velocidad;
		}
		this.setLocation(x, y);
		if (y <= 10 || y >= 44) {
			temp.stop();
			visible = !visible;
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(new Color(0.51f, 0.51f, 0.51f, 0.3f));
		g2.fill(fondo);

		g2.setColor(new Color(153, 153, 153));
		g2.setStroke(new BasicStroke(2));
		g2.draw(fondo);
	}

}

package interfaz.componentes.pantalla;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import logica.Cancion;

public class PanelCalificacion extends JPanel implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int calificacion = 0;
	private Estrella[] estrella = new Estrella[5];
	private Cancion cancion;

	public PanelCalificacion() {
		this.setOpaque(false);
		this.setLayout(null);
		int x = 0;
		for (int i = 0; i < estrella.length; i++) {
			estrella[i] = new Estrella();
			estrella[i].setBounds(x, 0, 18, 17);
			estrella[i].addActionListener(this);
			this.add(estrella[i]);
			x += 23;
		}
	}

	public void setCalificacion(Cancion cancion) {
		this.cancion = cancion;
		int c = cancion.getCalificacion();
		if (c < 1 || c > 5) {
			System.err.println("Error en calificacion");
		} else {
			this.calificacion = c;
			for (int i = 0; i < estrella.length; i++) {
				if (i < c) {
					estrella[i].setColor(Color.YELLOW);
				} else {
					estrella[i].setColor(Color.WHITE);
				}
			}
		}
	}

	public int getCalificacion() {
		return calificacion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int c = 1;
		for (int i = 0; i < estrella.length; i++) {
			if (e.getSource().equals(estrella[i])) {
				break;
			} else {
				c++;
			}
		}
		cancion.setCalificacion(c);
		setCalificacion(cancion);
	}
}

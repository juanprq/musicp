package interfaz.componentes.pantalla;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import logica.Cancion;

public class pantallaReproduccion extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private PanelCalificacion calificacion;
	private JTextArea areaComentarios;
	private JLabel labelComentarios;

	private Cancion cancion;
	private int minutos = 0;
	private int segundos = 0;
	private Timer temp;

	private Font fuenteTiempo;
	private Font fuenteCancion;

	public pantallaReproduccion() {
		this.setOpaque(false);
		this.setLayout(null);

		fuenteTiempo = new Font(Font.SERIF, Font.BOLD, 50);
		fuenteCancion = new Font(Font.SERIF, Font.BOLD, 12);

		labelComentarios = new JLabel("Comentarios:");
		labelComentarios.setFont(fuenteCancion);
		labelComentarios.setForeground(Color.WHITE);
		labelComentarios.setBounds(16, 146, 136, 15);
		this.add(labelComentarios);

		calificacion = new PanelCalificacion();
		calificacion.setBounds(23, 118, 112, 18);
		this.add(calificacion);

		JScrollPane js = new JScrollPane();
		js.setBounds(16, 162, 136, 73);
		areaComentarios = new JTextArea();
		js.setViewportView(areaComentarios);
		this.add(js);

		temp = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aumentarTiempo();
			}
		});
	}

	protected void aumentarTiempo() {
		segundos++;
		if (segundos >= 60) {
			segundos = 0;
			minutos++;
		}
		if (minutos == cancion.getMinutos()
				&& segundos == cancion.getSegundos()) {
			temp.stop();
		}
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0, 0, 334, 257);

		String tiempo = "";
		if (minutos < 10) {
			tiempo += "0" + minutos;
		} else {
			tiempo += minutos;
		}
		tiempo += ":";
		if (segundos < 10) {
			tiempo += "0" + segundos;
		} else {
			tiempo += segundos;
		}
		TextLayout tl = new TextLayout(tiempo, fuenteTiempo, g2
				.getFontRenderContext());
		g2.setColor(Color.WHITE);
		tl.draw(g2, 8, 50);

		if (cancion != null) {
			String cadena = cancion.getNombre() + " - " + cancion.getArtista();
			tl = new TextLayout(cadena, fuenteCancion, g2
					.getFontRenderContext());
			tl.draw(g2, 9, 90);

			Image album = new ImageIcon(cancion.getUrlAlbum()).getImage();
			g2.drawImage(album, 181, 118, 135, 118, this);
		}
	}

	public void setCancion(Cancion cancion) {
		this.cancion = cancion;
		areaComentarios.setText(cancion.getComentarios());
		calificacion.setCalificacion(cancion);

		this.repaint();
	}

	public void comenzarCuenta() {
		temp.start();
	}

	public void pararCuenta() {
		temp.stop();
	}

	public void reiniciarCuenta(){
		temp.restart();
		temp.stop();
		minutos=0;
		segundos=0;
	}

}

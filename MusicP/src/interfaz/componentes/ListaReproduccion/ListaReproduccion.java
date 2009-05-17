package interfaz.componentes.ListaReproduccion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import logica.Cancion;

public class ListaReproduccion extends JPanel implements MouseListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField entrada;
	private BotonHerramienta buscar;
	private BotonHerramienta agregar;
	private JPanel lista;
	private ArrayList<Cancion> listaCanciones;
	private ArrayList<JPanel> canciones;
	private JPanel barraHerramientas;
	private JPanel seleccionado;
	private Color colorAnterior;

	public ListaReproduccion() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		listaCanciones = new ArrayList<Cancion>();
		canciones = new ArrayList<JPanel>();
		inicializarComponentes();
		ponerAcciones();
	}

	private void ponerAcciones() {
		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		agregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agregarCancion();
			}
		});
	}

	private void inicializarComponentes() {
		barraHerramientas = new BarraHerramientas();
		FlowLayout l = new FlowLayout(FlowLayout.LEFT);
		l.setHgap(0);
		l.setVgap(0);
		barraHerramientas.setLayout(l);

		entrada = new JTextField(15);
		barraHerramientas.add(entrada);

		buscar = new BotonHerramienta();
		buscar.setText("Buscar");
		barraHerramientas.add(buscar);

		agregar = new BotonHerramienta();
		agregar.setText("Agregar");
		barraHerramientas.add(agregar);

		this.add(barraHerramientas, BorderLayout.NORTH);

		lista = new JPanel();
		lista.setOpaque(false);
		BoxLayout bl = new BoxLayout(lista, BoxLayout.Y_AXIS);
		lista.setLayout(bl);
		JScrollPane js = new JScrollPane(lista);
		this.add(js, BorderLayout.CENTER);
	}

	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
		canciones = new ArrayList<JPanel>();
		for (int j = 0; j < listaCanciones.size(); j++) {
			Cancion i = listaCanciones.get(j);
			JPanel c = new JPanel(new BorderLayout());
			if (j % 2 == 0) {
				c.setBackground(Color.BLACK);
			} else {
				c.setBackground(Color.DARK_GRAY);
			}

			JLabel l = new JLabel(i.getNombre() + " - " + i.getArtista());
			l.setForeground(Color.WHITE);
			c.add(l, BorderLayout.CENTER);
			c.setMaximumSize(new Dimension(800, 20));
			c.addMouseListener(this);

			canciones.add(c);
			lista.add(c);
		}
		canciones.get(0).setVisible(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/* eventos */

	protected void agregarCancion() {
		// posterior implementacion
	}

	protected void buscar() {
		for (int i = 0; i < listaCanciones.size(); i++) {
			Cancion c = listaCanciones.get(i);
			if (c.getArtista().contains(entrada.getText())
					|| c.getNombre().contains(entrada.getText())) {
				canciones.get(i).setVisible(true);
			} else {
				canciones.get(i).setVisible(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JOptionPane.showMessageDialog(this, "Reproducir canción", "Reproducir", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (seleccionado != null) {
				seleccionado.setBackground(colorAnterior);
			}
			seleccionado = (JPanel) e.getSource();
			colorAnterior = seleccionado.getBackground();
			seleccionado.setBackground(Color.LIGHT_GRAY);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}

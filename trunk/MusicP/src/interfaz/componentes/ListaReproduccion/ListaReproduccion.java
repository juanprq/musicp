package interfaz.componentes.ListaReproduccion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Cancion;

public class ListaReproduccion extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField entrada;
	private BotonHerramienta buscar;
	private JList lista;
	private ArrayList<Cancion> listaCanciones;
	private JPanel barraHerramientas;

	public ListaReproduccion() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		inicializarComponentes();
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

		this.add(barraHerramientas, BorderLayout.NORTH);

		lista = new JList();
		this.add(lista, BorderLayout.CENTER);
	}

	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}

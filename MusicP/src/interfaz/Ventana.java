package interfaz;

import interfaz.componentes.BotonApagar;
import interfaz.componentes.BotonMinimizar;
import interfaz.componentes.Brillo;
import interfaz.componentes.Fondo;
import interfaz.componentes.panelReproductor.BotonPlay;
import interfaz.componentes.panelReproductor.BotonSiguiente;
import interfaz.componentes.panelReproductor.BotonStop;
import interfaz.componentes.panelReproductor.BotonVolumen;
import interfaz.componentes.panelReproductor.FondoFalso;
import interfaz.componentes.panelReproductor.PanelReproduccion;
import interfaz.componentes.panelReproductor.slider.Slider;
import interfaz.componentes.panelReproductor.sliderVolumen.PanelVolumen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel fondo;
	private JButton apagar;
	private JButton minimizar;
	private JComponent brillo;
	private JPanel reproductor;
	private JPanel pantalla;
	// variables para el drag&drop
	private int xi;
	private int yi;
	private FondoFalso fondoFalso;
	private BotonPlay botonPlay;
	private BotonSiguiente botonSiguiente;
	private BotonSiguiente BotonAtras;
	private BotonStop botonStop;
	private Slider slider;
	private BotonVolumen botonVolumen;
	private PanelVolumen pv;

	public Ventana() {
		super("Hamilpod");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(600, 400);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		iniciarComponentes();
		agregarEventos();
		ponerArrastrable();
	}

	private void agregarEventos() {
		botonVolumen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pv.setLocation(botonVolumen.getX() - 8 + reproductor.getX(),
						botonVolumen.getY() - 78 + reproductor.getY());
				pv.ver();
			}
		});

		botonPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonPlay.reproducir();
			}
		});
		botonPlay.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botonPlay.mouseEntered();
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				botonPlay.mouseExited();
			}
		});
	}

	private void ponerArrastrable() {
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) {
				mouseArrastrandose(evt);
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				mousePresionado(evt);
			}
		});

		apagar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TerminarAplicacion();
			}
		});

		minimizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				minimizar();
			}
		});
	}

	private void iniciarComponentes() {
		JLayeredPane lp = this.getLayeredPane();

		fondo = new Fondo();
		fondo.setLayout(null);
		fondo.setBounds(7, 15, 526, 314);
		this.getContentPane().add(fondo);

		apagar = new BotonApagar();
		apagar.setBounds(445, 130, 53, 53);
		fondo.add(apagar);

		minimizar = new BotonMinimizar();
		minimizar.setText("_");
		minimizar.setBounds(466, 26, 15, 15);
		fondo.add(minimizar);

		brillo = new Brillo();
		brillo.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.setGlassPane(brillo);
		this.getGlassPane().setVisible(true);

		/* todo lo del panel de reproduccion */
		reproductor = new PanelReproduccion();
		reproductor.setBounds(139, 227, 291, 73);
		lp.add(reproductor, new Integer(2));

		botonPlay = new BotonPlay();
		botonPlay.setBounds(127, 6, 22, 22);
		reproductor.add(botonPlay);

		botonSiguiente = new BotonSiguiente(BotonSiguiente.ADELANTAR);
		botonSiguiente.setBounds(167, 6, 22, 22);
		reproductor.add(botonSiguiente);

		BotonAtras = new BotonSiguiente(BotonSiguiente.ATRASAR);
		BotonAtras.setBounds(85, 6, 22, 22);
		reproductor.add(BotonAtras);

		botonStop = new BotonStop();
		botonStop.setBounds(43, 6, 22, 22);
		reproductor.add(botonStop);

		botonVolumen = new BotonVolumen();
		botonVolumen.setBounds(209, 6, 22, 22);
		reproductor.add(botonVolumen);

		slider = new Slider();
		slider.setBounds(12, 46, 241, 10);
		reproductor.add(slider);

		pv = new PanelVolumen();
		pv.setBounds(botonVolumen.getX() - 8 + reproductor.getX(), botonVolumen
				.getY()
				- 78 + reproductor.getY(), 33, 73);
		lp.add(pv, new Integer(3));

		fondoFalso = new FondoFalso();
		fondoFalso.setBounds(112, 299, 319, 33);
		lp.add(fondoFalso, new Integer(3));
		/* Fin componentes del panel de reproduccion */
		pantalla = new JPanel();
		pantalla.setLayout(new BorderLayout());
		pantalla.setBounds(97, 28, 332, 255);
		fondo.add(pantalla);
	}

	// **************eventos********************
	protected void mouseArrastrandose(MouseEvent evt) {
		evt.getComponent().setLocation(evt.getXOnScreen() - xi,
				evt.getYOnScreen() - yi);
	}

	protected void mousePresionado(MouseEvent evt) {
		xi = evt.getXOnScreen() - evt.getComponent().getX();
		yi = evt.getYOnScreen() - evt.getComponent().getY();
	}

	protected void TerminarAplicacion() {
		int aux = JOptionPane.showConfirmDialog(this,
				"¿Desea salir de Hamilpod?", "¿Salir?",
				JOptionPane.YES_NO_OPTION);
		if (aux == 0) {
			System.exit(0);
		}
	}

	protected void minimizar() {
		this.setExtendedState(JFrame.ICONIFIED);
	}
}

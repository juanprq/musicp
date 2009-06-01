package interfaz;

import interfaz.componentes.BotonApagar;
import interfaz.componentes.BotonMinimizar;
import interfaz.componentes.Brillo;
import interfaz.componentes.Fondo;
import interfaz.componentes.ListaReproduccion.ListaReproduccion;
import interfaz.componentes.albumChooser.AlbumChooser;
import interfaz.componentes.dialogoDescarga.DialogoDescarga;
import interfaz.componentes.listaSugeridas.ListaSugeridas;
import interfaz.componentes.menuPrincipal.FondoFalso2;
import interfaz.componentes.menuPrincipal.ItemMenuPrincipal;
import interfaz.componentes.menuPrincipal.MenuPrincipal;
import interfaz.componentes.panelReproductor.BotonEsconder;
import interfaz.componentes.panelReproductor.BotonPlay;
import interfaz.componentes.panelReproductor.BotonSiguiente;
import interfaz.componentes.panelReproductor.BotonStop;
import interfaz.componentes.panelReproductor.BotonVolumen;
import interfaz.componentes.panelReproductor.FondoFalso;
import interfaz.componentes.panelReproductor.PanelReproduccion;
import interfaz.componentes.panelReproductor.slider.Slider;
import interfaz.componentes.panelReproductor.sliderVolumen.PanelVolumen;
import interfaz.componentes.pantalla.pantallaReproduccion;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica.Cancion;

public class Ventana extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel fondo;
	private JButton apagar;
	private JButton minimizar;
	private JComponent brillo;
	private PanelReproduccion reproductor;
	private JPanel pantalla;
	private FondoFalso fondoFalso;
	private BotonPlay botonPlay;
	private BotonSiguiente botonSiguiente;
	private BotonSiguiente BotonAtras;
	private BotonStop botonStop;
	private Slider slider;
	private BotonVolumen botonVolumen;
	private PanelVolumen pv;
	// variables para el drag&drop
	private int xi;
	private int yi;
	private FondoFalso2 fondoFalso2;
	private MenuPrincipal menuPrincipal;
	private ItemMenuPrincipal botonReproduccionActual;
	private ItemMenuPrincipal botonListaDeReproduccion;
	private ItemMenuPrincipal botonAlbum;
	private ItemMenuPrincipal botonSugerir;
	private ItemMenuPrincipal botonListaSugeridas;

	private pantallaReproduccion pantallaReproduccion;
	private ListaReproduccion listaReproduccion;
	private BotonEsconder botonMinimizarMenuPrincipal;
	private BotonEsconder botonMinimizarPanelReproductor;

	private AlbumChooser cas;

	private JPanel panelActual;
	private Cancion cancionActual;
	private ListaSugeridas miListaSugeridas;

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

		mostrarAlbum();

	}

	private void agregarEventos() {
		botonMinimizarMenuPrincipal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.empezarAnimacion();
				botonMinimizarMenuPrincipal.setTipo(!menuPrincipal.esVisible());
			}
		});

		botonMinimizarPanelReproductor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reproductor.empezarAnimacion();
				pv.ocultar();
				botonMinimizarPanelReproductor.setTipo(reproductor.esVisible());
			}
		});

		botonVolumen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(reproductor.esAnimado())
					return;
				pv.setLocation(botonVolumen.getX() - 8 + reproductor.getX(),
						botonVolumen.getY() - 78 + reproductor.getY());
				if(pv.isVisible())
					pv.ocultar();
				else
					pv.mostrar();
			}
		});
		botonVolumen.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botonVolumen.mouseEntered();
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				botonVolumen.mouseExited();
			}
		});

		botonPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pv.ocultar();
				if(!botonPlay.estaReproduciendo()){
					pantallaReproduccion.comenzarCuenta();
				}
				else{
					pantallaReproduccion.pararCuenta();
				}
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

		botonSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pv.ocultar();
			}
		});
		botonSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botonSiguiente.mouseEntered();
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				botonSiguiente.mouseExited();
			}
		});

		BotonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pv.ocultar();
			}
		});
		BotonAtras.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				BotonAtras.mouseEntered();
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				BotonAtras.mouseExited();
			}
		});

		botonStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pv.ocultar();
				if(botonPlay.estaReproduciendo()){
					botonPlay.reproducir();
				}
				pantallaReproduccion.reiniciarCuenta();
			}
		});
		botonStop.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botonStop.mouseEntered();
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				botonStop.mouseExited();
			}
		});
		botonReproduccionActual.addMouseListener(new java.awt.event.MouseAdapter() {

					@Override
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						pv.ocultar();
						mostrarPanelReproduccionActual();
					}
				});

		botonListaDeReproduccion.addMouseListener(new java.awt.event.MouseAdapter() {

					@Override
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						pv.ocultar();
						mostrarListaDeReproduccion();
					}
				});

		botonAlbum.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				pv.ocultar();
				mostrarAlbum();
			}
		});

		botonSugerir.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				pv.ocultar();
				sugerir();
			}
		});

		botonListaSugeridas.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				pv.ocultar();
				mostrarListaDeSugeridas();
			}
		});
	}

	public void descargar() {
		DialogoDescarga miDialogoDescarga=new DialogoDescarga();
		miDialogoDescarga.setVisible(true);
		miDialogoDescarga.animar();


	}
	private void sugerir() {
		String mensaje="";
		if(panelActual.equals(pantallaReproduccion)){
			mensaje="La canción "+cancionActual.getNombre()+" se ha sugerido con éxito";
		}
		if(panelActual.equals(listaReproduccion)){
			mensaje="La canción "+cancionActual.getNombre()+" se ha sugerido con éxito";
		}
		if(panelActual.equals(miListaSugeridas)){
			mensaje="La canción "+cancionActual.getNombre()+" se ha sugerido con éxito";
		}
		if(panelActual.equals(cas)){
			mensaje="El álbum Audioslave se ha sugerido con éxito";
		}
		if(panelActual.equals(null)){
			System.out.println("null");
		}
		JOptionPane.showMessageDialog(this, mensaje);
	}

	private void mostrarPanelReproduccionActual() {
		if (panelActual != null) {
			pantalla.remove(panelActual);
		}
		panelActual = pantallaReproduccion;
		pantalla.add(pantallaReproduccion, BorderLayout.CENTER);
		panelActual.setVisible(false);
		panelActual.setVisible(true);
	}

	private void mostrarListaDeReproduccion() {
		if (panelActual != null)
			pantalla.remove(panelActual);
		panelActual = listaReproduccion;
		pantalla.add(listaReproduccion, BorderLayout.CENTER);
		panelActual.setVisible(false);
		panelActual.setVisible(true);
	}

	private void mostrarListaDeSugeridas() {
		if (panelActual != null)
			pantalla.remove(panelActual);
		panelActual = miListaSugeridas;
		pantalla.add(miListaSugeridas, BorderLayout.CENTER);
		panelActual.setVisible(false);
		panelActual.setVisible(true);
	}

	private void mostrarAlbum() {
		if (panelActual != null)
			pantalla.remove(panelActual);
		panelActual = cas;
		pantalla.add(cas, BorderLayout.CENTER);
		panelActual.setVisible(false);
		panelActual.setVisible(true);
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
				pv.ocultar();
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
				pv.ocultar();
				minimizar();
			}
		});
	}

	private void iniciarComponentes() {
		JLayeredPane lp = this.getLayeredPane();

		fondo = new Fondo();
		fondo.setLayout(null);
		fondo.setBounds(7, 11, 526, 314);
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
		fondoFalso.setLayout(null);
		fondoFalso.setBounds(112, 294, 319, 34);

		botonMinimizarPanelReproductor = new BotonEsconder();
		botonMinimizarPanelReproductor.setTipo(BotonEsconder.BAJAR);
		botonMinimizarPanelReproductor.setBounds(fondoFalso.getWidth() / 2, 0,
				15, 15);
		fondoFalso.add(botonMinimizarPanelReproductor);

		lp.add(fondoFalso, new Integer(3));
		/* Fin componentes del panel de reproduccion */

		/* Componentes Menu */

		fondoFalso2 = new FondoFalso2();
		fondoFalso2.setLayout(null);
		fondoFalso2.setBounds(132, 11, 273, 29);

		botonMinimizarMenuPrincipal = new BotonEsconder();
		botonMinimizarMenuPrincipal.setTipo(BotonEsconder.SUBIR);
		botonMinimizarMenuPrincipal.setBounds(fondoFalso2.getWidth() / 2,
				fondoFalso2.getHeight() - 15, 15, 15);
		fondoFalso2.add(botonMinimizarMenuPrincipal);

		lp.add(fondoFalso2, new Integer(3));

		/* Fin Componentes Menu */

		/* Pantalla */
		pantalla = new JPanel();
		pantalla.setLayout(new BorderLayout());
		pantalla.setBounds(97, 29, 332, 254);
		fondo.add(pantalla);
		pantallaReproduccion = new pantallaReproduccion();
		/* prueba */
		Cancion c = new Cancion();
		c.setArtista("DragonForce");
		c.setAlbum("Album 1");
		c.setNombre("Through the Fire and Flames");
		c.setMinutos(4);
		c.setSegundos(30);
		c.setUrlAlbum("imagenes/album1.jpg");
		c.setCalificacion(4);
		c.setComentarios("Holasad");
		cancionActual=c;
		/* fin prueba */

		pantallaReproduccion.setCancion(c);
		/* prueba */
		listaReproduccion = new ListaReproduccion();
		/* prueba con lista */
		ArrayList<Cancion> z = new ArrayList<Cancion>();
		for (int i = 0; i < 40; i++) {
			z.add(c);
		}
		listaReproduccion.setListaCanciones(z);
		/* fin prueba */
		miListaSugeridas=new ListaSugeridas(this);
		miListaSugeridas.setListaCanciones(z);

		cas = new AlbumChooser();

		menuPrincipal = new MenuPrincipal();
		menuPrincipal.setBounds(139, 31, 266, 47);
		lp.add(menuPrincipal, new Integer(2));

		botonReproduccionActual = new ItemMenuPrincipal("imagenes/reproduccionIn.png","imagenes/reproduccionOut.png");
		menuPrincipal.add(botonReproduccionActual);
		botonReproduccionActual.setLocation(10, 10);
		botonReproduccionActual.setToolTipText("Reproducción Actual");

		botonListaDeReproduccion = new ItemMenuPrincipal("imagenes/listaIn.png","imagenes/listaOut.png");
		menuPrincipal.add(botonListaDeReproduccion);
		botonListaDeReproduccion.setLocation(50, 10);
		botonListaDeReproduccion.setToolTipText("Lista de reproducción");

		botonAlbum = new ItemMenuPrincipal("imagenes/reproduccionIn.png","imagenes/reproduccionOut.png");
		menuPrincipal.add(botonAlbum);
		botonAlbum.setLocation(90, 10);
		botonAlbum.setToolTipText("Seleccionar Album");

		botonSugerir = new ItemMenuPrincipal("imagenes/sugerenciasIn.png","imagenes/sugerenciasOut.png");
		menuPrincipal.add(botonSugerir);
		botonSugerir.setLocation(130, 10);
		botonSugerir.setToolTipText("Sugerencias");

		botonListaSugeridas = new ItemMenuPrincipal("imagenes/sugerenciasIn.png","imagenes/sugerenciasOut.png");
		menuPrincipal.add(botonListaSugeridas);
		botonListaSugeridas.setLocation(170, 10);
		botonListaSugeridas.setToolTipText("Sugeridas");

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

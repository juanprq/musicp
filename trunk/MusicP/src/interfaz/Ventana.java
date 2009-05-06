package interfaz;

import interfaz.componentes.BotonApagar;
import interfaz.componentes.BotonMinimizar;
import interfaz.componentes.Brillo;
import interfaz.componentes.Fondo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
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
	// variables para el drag&drop
	private int xi;
	private int yi;

	public Ventana() {
		super("Hamilpod");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(600, 400);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		iniciarComponentes();
		ponerArrastrable();
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

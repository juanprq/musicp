package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel fondo;

	public Ventana() {
		super("Hamilpod");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(600, 400);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		fondo=new Fondo();
		fondo.setBounds(7, 15, 526, 314);
		this.getContentPane().add(fondo);
	}

}

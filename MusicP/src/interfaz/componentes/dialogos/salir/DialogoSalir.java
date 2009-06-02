package interfaz.componentes.dialogos.salir;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaz.componentes.dialogos.BotonDialogo;
import interfaz.componentes.dialogos.descarga.PanelFondo;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class DialogoSalir extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private PanelFondo fondo;
	private JLabel etiqueta;
	private BotonDialogo aceptar;
	private BotonDialogo cancelar;

	public DialogoSalir() {
		this.setTitle("¿Salir?");
		this.setResizable(false);
		this.setSize(280, 130);
		this.setModal(true);
		this.setUndecorated(true);

		inicializarComponentes();
		ponerEventos();
		this.setLocationRelativeTo(null);
	}

	private void ponerEventos() {
		aceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}});

		cancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}});
	}

	private void inicializarComponentes() {
		fondo = new PanelFondo();
		fondo.setLayout(null);
		this.add(fondo, BorderLayout.CENTER);

		etiqueta = new JLabel("¿Desea salir del hamilpod?");
		etiqueta.setForeground(Color.WHITE);
		etiqueta.setBounds(78, 42, 160, 20);
		fondo.add(etiqueta);

		aceptar = new BotonDialogo();
		aceptar.setText("Aceptar");
		aceptar.setBounds(60, 80, 70, 20);
		fondo.add(aceptar);

		cancelar = new BotonDialogo();
		cancelar.setText("Cancelar");
		cancelar.setBounds(144, 80, 70, 20);
		fondo.add(cancelar);
	}
}

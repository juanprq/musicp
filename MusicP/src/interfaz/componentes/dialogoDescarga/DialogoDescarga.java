package interfaz.componentes.dialogoDescarga;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.Timer;

public class DialogoDescarga extends JDialog{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private PanelFondo fondo;
	private BarraProgreso progreso;
	private Timer temp;
	private int p=0;

	public DialogoDescarga() {
		this.setTitle("Descargando");
		this.setSize(300, 100);
		this.setResizable(false);

		temp=new Timer(20, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				p+=1;
				progreso.setProgreso(p);
				if(p>=100){
					temp.stop();
					setVisible(false);
				}
			}});

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		inicializarComponentes();
		this.setLocationRelativeTo(null);
	}

	private void inicializarComponentes() {
		fondo=new PanelFondo();
		fondo.setLayout(null);
		fondo.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(fondo, BorderLayout.CENTER);

		progreso=new BarraProgreso();
		progreso.setBounds(30, 10, this.getWidth()-60, 60);
		fondo.add(progreso);
	}

	public static void main(String[] args) {
		new DialogoDescarga().setVisible(true);
	}

	@Override
	public void setVisible(boolean v){
		if(v){
			super.setVisible(true);
			temp.start();
		} else{
			super.setVisible(false);
		}
	}

}

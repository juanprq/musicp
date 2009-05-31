package interfaz.componentes.menuPrincipal;


import javax.swing.JLabel;

public class ItemMenuPrincipal extends JLabel {

	private static final long serialVersionUID = 1L;
	
	public ItemMenuPrincipal() {
	}

	public void setRutaImagen(String rutaImagen) {
		setSize(32, 32);
		setIcon(new javax.swing.ImageIcon(rutaImagen));		
	}
	

}
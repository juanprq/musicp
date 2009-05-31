package interfaz.componentes.menuPrincipal;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ItemMenuPrincipal extends JLabel {

	private static final long serialVersionUID = 1L;
	private ImageIcon iconIn;
	private ImageIcon iconOut;
	
	public ItemMenuPrincipal(String rutaImagenIn,String rutaImagenOut) {
		setSize(32, 32);
		iconIn=new javax.swing.ImageIcon(rutaImagenIn);
		iconOut=new javax.swing.ImageIcon(rutaImagenOut);
		setIcon(iconOut);
		this.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				setIcon(iconIn);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				setIcon(iconOut);
			}
		});
	}

	
	

}
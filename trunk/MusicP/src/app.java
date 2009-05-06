import interfaz.Ventana;

import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import com.sun.jna.examples.WindowUtils;

public class app {

	public static void main(String[] args) {
		System.setProperty("sun.java2d.noddraw", "true");
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Shape mask = new RoundRectangle2D.Float(10, 40, 526, 314, 118, 118);
		Ventana miVentana = new Ventana();
		WindowUtils.setWindowMask(miVentana, mask);

		miVentana.setVisible(true);
	}

}

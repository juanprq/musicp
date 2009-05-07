package logica;

import java.io.File;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class Reproductor implements BasicPlayerListener {

	private BasicPlayer bp;
	private double bytesLength;

	public static void main(String[] args) {
		new Reproductor();
	}

	public Reproductor() {
		bp = new BasicPlayer();
		bp.addBasicPlayerListener(this);
	}

	public void reproducir() throws Exception {
		try {
			bp.play();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("No hay un archivo de musica para reproducir.");
		}
	}

	public void abrir(String archivo) throws Exception {
		try {
			bp.open(new File(archivo));
		} catch (BasicPlayerException e) {
			e.printStackTrace();
			throw new Exception("Imposible abrir el archivo especificado.");
		}
	}

	public void pausar() throws Exception {
		try {
			bp.pause();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ha ocurrido un error pausando la reproducción");
		}
	}

	public void detener() throws Exception {
		try {
			bp.stop();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					"Ha Ocurrido un error deteniendo la reproducción");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void opened(Object arg0, Map arg1) {
		if (arg1.containsKey("audio.length.bytes")) {
			bytesLength = Double.parseDouble(arg1.get("audio.length.bytes")
					.toString());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void progress(int bytesread, long arg1, byte[] arg2, Map arg3) {
		float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
		 int progressNow = (int) (bytesLength * progressUpdate);
		 System.out.println(progressNow);
	}

	@Override
	public void setController(BasicController arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateUpdated(BasicPlayerEvent arg0) {
		// TODO Auto-generated method stub

	}

}

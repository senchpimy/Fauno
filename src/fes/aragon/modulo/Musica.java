package fes.aragon.modulo;

import java.io.File;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Musica extends Thread{

	String cancion = "media/bug.mp3";
	public void run() {
	Media hit = new Media(new File(cancion).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.setCycleCount(Integer.MAX_VALUE);
	mediaPlayer.play();
	}
}

package fes.aragon.modulo;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Caminadores extends Creatura {
	private GraphicsContext gc;
	int[][] matriz;
	Image skin = new Image(new File("media/Caminante.png").toURI().toString()); // cambiar a fantasma
	private Random rand = new Random();
	Boolean mover = true;
	int segundo;

	@Override
	public void pintar() {
		gc.drawImage(skin, X * pixel, Y * pixel);
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {

	}

	@Override
	public void raton(KeyEvent evento) {

	}

	@Override
	public void logicaCalculos() {
		int val =1;
		int new_x=X;
		int new_y=Y;
		for (int i =0;i<20;i++) {
			int ca_y=Y;
			int ca_x=X;
			try {
				new_y=rand.nextInt(ca_y-1,ca_y+1);
				new_x=rand.nextInt(ca_x-1,ca_x+1);
				val=matriz[new_x][new_y];
			}catch (Exception e) {}
		}
		if (val!=1) {
			X=new_x;
			Y=new_y;
		}
	}

	public void setGc(GraphicsContext grc) {
		gc = grc;
	}

	public void setMatriz(int[][] matr) {
		gc.clearRect(X * pixel, Y * pixel, pixel, pixel);
		matriz = matr;
		int fila = rand.nextInt(29);
		int columna = rand.nextInt(29);

		while (matriz[fila][columna] != 1) {
			fila = rand.nextInt(29);
			columna = rand.nextInt(29);
		}
		X = columna;
		Y = columna;
	}

	public Boolean getMover() {
		return mover;
	}

	public void unlock(int seg) {
		if (seg != segundo) {
			segundo = seg;
			mover = true;
		}
	}

	public void Mover() {
		if (mover) {
			gc.clearRect(X * pixel, Y * pixel, pixel, pixel);
			logicaCalculos();
			pintar();
			mover = false;
		}
	}
}

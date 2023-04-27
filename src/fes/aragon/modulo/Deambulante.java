package fes.aragon.modulo;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Deambulante extends Creatura{
    private GraphicsContext gc ;
    private int pixel;
    int[][] matriz;
    Image skin=new Image(new File("media/atras.png").toURI().toString()); //cambiar a fantasma
	private Random rand = new Random();
	Boolean mover = true;
	int segundo;

	public void setPixel(int pix) {
		pixel=pix;
	}
	public void setGc(GraphicsContext grc) {
		gc=grc;
	}
	public void setMatriz(int[][] matr) {
		matriz=matr;
	}
	public  Boolean getMover() {
		return mover;
	}
	public void unlock(int seg) {
		if (seg!=segundo) {
			segundo=seg;
			mover=true;
		}
	}
	
	public void Mover() {
		if (mover) {
		gc.clearRect(X*pixel, Y*pixel, pixel, pixel);
		X=rand.nextInt(30);
		Y=rand.nextInt(30);
		while (matriz[X][Y]==1) {
		X=rand.nextInt(30);
		Y=rand.nextInt(30);
		}
        gc.drawImage(skin,X*pixel,Y*pixel);
        mover=false;
		}
	}
}

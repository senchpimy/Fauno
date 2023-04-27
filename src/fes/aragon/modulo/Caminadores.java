package fes.aragon.modulo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Caminadores extends Creatura{
    private GraphicsContext gc ;
    List<Tupla<Integer,Integer>> caminantes = new ArrayList<>();
    private int pixel;
    int[][] matriz;
    Image skin=new Image(new File("media/atras.png").toURI().toString()); //cambiar a fantasma
	private Random rand = new Random();
	Boolean mover = true;
	int segundo;
	Boolean mostrar;
	
	public void mostrar() {
		mostrar=true;
	}

	public void noMostrar() {
		mostrar=false;
	}

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
	
	public void crearCaminantes() {
		int val = rand.nextInt(7);
		for (int i =0;i<val;i++) {
			int x = rand.nextInt(29);
			int y = rand.nextInt(29);
			while (matriz[y][x]==1) {
				x = rand.nextInt(29);
				y = rand.nextInt(29);
			}
			caminantes.add(new Tupla<>(x,y));
		}
	}
	public void eliminarCaminantes() {
		gc.clearRect(0, 0, 600, 600);
	}
	public void Mover() {
		if (mover) {
			for(Tupla<Integer,Integer> caminante:caminantes) {
				int val =0;
				int new_x=caminante.primero;
				int new_y=caminante.segundo;
				for (int i =0;i<5;i++) {
					int ca_y=caminante.primero;
					int ca_x=caminante.segundo;
					try {
						new_y=rand.nextInt(ca_y-1,ca_y+1);
						new_x=rand.nextInt(ca_x-1,ca_x+1);
						val=matriz[new_y][new_x];
					}catch (Exception _) {
						
					}
					if (val!=1)
						break;
				}
				caminante.primero=new_y;
				caminante.segundo=new_x;
			}
			//Tupla<Integer,Integer> papu =caminantes.get(2);
			//7caminantes.set(2, papu);
		}
	}
	public void Pintar() {
		if (mostrar)
		for(Tupla<Integer,Integer> caminante:caminantes) {
			gc.drawImage(skin, caminante.primero, caminante.segundo);
		}
	}

    static class Tupla<T, T1> {
        T primero;
        T1 segundo;

        public Tupla(T pri, T1 se) {
            this.primero = pri;
            this.segundo = se;
            }
	}
	
}

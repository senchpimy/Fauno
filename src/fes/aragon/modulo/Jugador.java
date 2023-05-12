package fes.aragon.modulo;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.canvas.GraphicsContext;
import java.io.File;

public class Jugador extends Creatura{
	private int energia=2000;
	private Boolean vivo=true;
	private GraphicsContext gc;
	
	public void setgc(GraphicsContext grc) {
		gc=grc;
	}

    private final Image Frente=new Image(new File("media/frente.png").toURI().toString());

    private final Image Atras=new Image(new File("media/atras.png").toURI().toString());

    private final Image Izquierda=new Image(new File("media/izq.png").toURI().toString());

    private final Image Derecha=new Image(new File("media/der.png").toURI().toString());

	private Image img;
	
	public Jugador() {
		X=0;
		Y=345;
	}
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public Boolean Final() {return (X>660 && Y==345? true:false);}


	public void Reset(GraphicsContext gc) {
		X=0;
		Y=345;
	}
	public void Alimentarse() {
	energia+=100;	
	String cancion = "media/comer.mp3";
	MediaPlayer mediaPlayer;
	Media hit = new Media(new File(cancion).toURI().toString());
	mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.play();
	}

	public Boolean Salud() {return vivo;}

	public void Mover(Movimiento direccion) {
		if (!vivo) return;
        gc.clearRect(X, Y, pixel, pixel);
		if (energia<=0)vivo=false;
        energia-=20;
        if (direccion==Movimiento.ARRIBA) {
		Arriba();
		img=Atras;
        } else if (direccion==Movimiento.ABAJO) {
		Abajo();
		img=Frente;
        } else if (direccion==Movimiento.DERECHA) {
		Derecha();
		img=Derecha;
        } else {
		Izquierda();
		img=Izquierda;
        }
		pintar();
        if (energia<=0)vivo=false;
	}
	public void ConsumirEnergia(){energia-=50;}
	@Override
	public void pintar() {
		gc.drawImage(img,X, Y, pixel, pixel);
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {

	}

	@Override
	public void raton(KeyEvent evento) {

	}

	@Override
	public void logicaCalculos() {

	}
}

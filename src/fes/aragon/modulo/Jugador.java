package fes.aragon.modulo;
import java.io.FileInputStream;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.io.File;

public class Jugador extends Creatura{

    @FXML 
    private Image Frente=new Image(new File("media/frente.png").toURI().toString());

    @FXML 
    private Image Atras=new Image(new File("media/atras.png").toURI().toString());

    @FXML 
    private Image Izquierda=new Image(new File("media/izq.png").toURI().toString());

    @FXML 
    private Image Derecha=new Image(new File("media/der.png").toURI().toString());
	
	public Jugador() {
		super();
		X=0;
		Y=300;
	}
	public void Empezar(GraphicsContext gc,int pixel) {
        gc.setFill(Color.GREEN);
        gc.fillOval(X, Y, pixel, pixel);
	}
	
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public Boolean Final() {
		return (X>570 && Y==300? true:false);
	}
	
	public void Reset(GraphicsContext gc,int pixel) {
		X=0;
		Y=300;
        gc.setFill(Color.GREEN);
        gc.fillOval(X, Y, pixel, pixel);
	}
	
	public void Mover(GraphicsContext gc,Movimiento direccion,int pixel) {
        gc.clearRect(X, Y, pixel, pixel);
        Image img;
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
        gc.drawImage(img,X, Y, pixel, pixel);
	}
}

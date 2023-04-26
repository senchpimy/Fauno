package fes.aragon.controller;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import java.util.concurrent.TimeUnit;
import javafx.util.Duration;

import java.io.File;

import fes.aragon.modulo.*;
import fes.aragon.modulo.Jugador;

public class InicioController {
	Laberinto laberinto = new Laberinto();
	Jugador player = new Jugador();
	int pixel=20;
	Deambulante deambulante= new Deambulante();
	
	Boolean activo=true;

    @FXML 
    private Canvas img ;
    @FXML 
    private Canvas player_c ;
    @FXML 
    private Canvas fondo;
    @FXML 
    private Canvas sombra;
    @FXML 
    private Canvas deambulantes;

    //@FXML 
    //private ImageView TituloImage;

    private GraphicsContext gc ;
    

    @FXML 
    private void drawCanvas(int[][] matriz) {
    	gc = img.getGraphicsContext2D();
        
        for (int fila=0; fila<matriz.length;fila++)
        	for (int columna=0; columna<matriz[fila].length;columna++) {
        			int x=fila*pixel;
        			int y=columna*pixel;
        		if (matriz[fila][columna]==1) {
        			gc.setFill(Color.BLACK);
        			gc.fillRect(x, y, pixel, pixel);
        		}else if (matriz[fila][columna]==0) {
        			gc.clearRect(x, y, pixel, pixel);
        		}else if (matriz[fila][columna]==3) {
        			gc.setFill(Color.PINK);
        			gc.fillRect(x, y, pixel, pixel);
        		}else {
        			gc.setFill(Color.RED);
        			gc.fillRect(x, y, pixel, pixel);
        		}
        	}
    }
	@FXML
	public void initialize() {
		GraphicsContext grc = fondo.getGraphicsContext2D();
		Image Fondo=new Image(new File("media/piso.png").toURI().toString());
        grc.drawImage(Fondo,0, 0, 600, 600);
        deambulante.setPixel(pixel);
        deambulante.setGc(deambulantes.getGraphicsContext2D());
		//TranslateTransition translate = new TranslateTransition();
		//translate.setNode(TituloImage);
		//translate.setDuration(Duration.millis(1000));
		//translate.setByY(200);
		//translate.play();
		Empezar();
	}

	@FXML
	public void Empezar() {
		this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
		this.player.Empezar(player_c.getGraphicsContext2D(),pixel);
	}
	
	public void VerificarActividad() {
		activo = player.Salud();
		if (!activo) {
		gc = sombra.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, 600, 600);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font (gc.getFont().getName(),50));
        gc.fillText("Moriste de Hambre",100, 150);
		}
	}

	void NuevoLaberinto() {
		this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
	}
	public void Arriba() {
		int res =(laberinto.HabilitarMovimiento(player.getX(), player.getY()-pixel));
		if (res==1) return;
		gc = player_c.getGraphicsContext2D();
		player.Mover(gc,Movimiento.ARRIBA,pixel);
		if (res==3) {
			player.Alimentarse();
			gc = img.getGraphicsContext2D();
			gc.clearRect(player.getX(), player.getY(), pixel, pixel);
		}
	}
	public void Abajo() {
		int res=laberinto.HabilitarMovimiento(player.getX(), player.getY()+pixel);
		if (res==1) return;
		gc = player_c.getGraphicsContext2D();
		this.player.Mover(gc,Movimiento.ABAJO,pixel);
		if (res==3) {
			player.Alimentarse();
			gc = img.getGraphicsContext2D();
			gc.clearRect(player.getX(), player.getY(), pixel, pixel);
		}
	}
	public void Izquierda() {
		int res=  (laberinto.HabilitarMovimiento(player.getX()-pixel, player.getY()));
		if (res==1) return;
		gc = player_c.getGraphicsContext2D();
		this.player.Mover(gc,Movimiento.IZQUIERDA,pixel);
		if (res==3) {
			player.Alimentarse();
			gc = img.getGraphicsContext2D();
			gc.clearRect(player.getX(), player.getY(), pixel, pixel);
		}
	}
	public void Derecha() {
		int res= (laberinto.HabilitarMovimiento(player.getX()+pixel, player.getY()));
		if (res==1) return;
		gc = player_c.getGraphicsContext2D();
		this.player.Mover(gc,Movimiento.DERECHA,pixel);
		if (res==3) {
			player.Alimentarse();
			gc = img.getGraphicsContext2D();
			gc.clearRect(player.getX(), player.getY(), pixel, pixel);
		}
	}
	
	public void Reset() {
		if (this.player.Final()) {
		gc = player_c.getGraphicsContext2D();
		this.NuevoLaberinto();	
		this.player.Reset(gc,pixel);
		activo = player.Salud();
		}		
	}
}

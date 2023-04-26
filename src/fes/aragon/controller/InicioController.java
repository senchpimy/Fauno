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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
	int tipo_laberinto;
	
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
        PintPiso();
    }
	@FXML
	public void initialize() {
        deambulante.setPixel(pixel);
        deambulante.setGc(deambulantes.getGraphicsContext2D());
        player.setPasos(pixel);
        player.setgc(player_c.getGraphicsContext2D());
		//TranslateTransition translate = new TranslateTransition();
		//translate.setNode(TituloImage);
		//translate.setDuration(Duration.millis(1000));
		//translate.setByY(200);
		//translate.play();
		Empezar();
	}

	public void Empezar() {
		NuevoLaberinto();
		player.Empezar(player_c.getGraphicsContext2D(),pixel);
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
			String cancion = "media/die.mp3";
			Media hit = new Media(new File(cancion).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(hit);
			mediaPlayer.play();
		}
	}

	void NuevoLaberinto() {
		tipo_laberinto=this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
	}
	public void Arriba() {
		int res =(laberinto.HabilitarMovimiento(player.getX(), player.getY()-pixel));
		if (res==1) return;
		player.Mover(Movimiento.ARRIBA);
		if (res==3) {
			player.Alimentarse();
			gc = img.getGraphicsContext2D();
			gc.clearRect(player.getX(), player.getY(), pixel, pixel);
		}
	}
	public void Abajo() {
		int res=laberinto.HabilitarMovimiento(player.getX(), player.getY()+pixel);
		if (res==1) return;
		this.player.Mover(Movimiento.ABAJO);
		if (res==3) {
			player.Alimentarse();
			gc = img.getGraphicsContext2D();
			gc.clearRect(player.getX(), player.getY(), pixel, pixel);
		}
	}
	public void Izquierda() {
		int res=  (laberinto.HabilitarMovimiento(player.getX()-pixel, player.getY()));
		if (res==1) return;
		this.player.Mover(Movimiento.IZQUIERDA);
		if (res==3) {
			player.Alimentarse();
			gc = img.getGraphicsContext2D();
			gc.clearRect(player.getX(), player.getY(), pixel, pixel);
		}
	}
	public void Derecha() {
		int res= (laberinto.HabilitarMovimiento(player.getX()+pixel, player.getY()));
		if (res==1) return;
		this.player.Mover(Movimiento.DERECHA);
		if (res==3) {
			player.Alimentarse();
			gc = img.getGraphicsContext2D();
			gc.clearRect(player.getX(), player.getY(), pixel, pixel);
		}
	}
	
	public void PintPiso() {
		Image Fondo;
		if (tipo_laberinto ==1) {
			Fondo=new Image(new File("media/pisoLuz.png").toURI().toString());
		}else if (tipo_laberinto==2) {
			Fondo=new Image(new File("media/pisoOscuro.png").toURI().toString());
		}else {
			Fondo=new Image(new File("media/piso.png").toURI().toString());
		}
		GraphicsContext grc = fondo.getGraphicsContext2D();
        grc.drawImage(Fondo,0, 0, 600, 600);
	}
	
	public void Reset() {
		if (this.player.Final()) {
		gc = player_c.getGraphicsContext2D();
		NuevoLaberinto();	
		player.Reset(gc);
		activo = player.Salud();
		}		
	}
}

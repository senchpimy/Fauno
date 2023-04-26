package fes.aragon.controller;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	LabT tipo_laberinto;
	Musica musica = new Musica(); 
	
	Boolean activo=true;
	Boolean activo_musica=true;

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
		musica.start();
		//deambulante.run();

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
			musica.dete();
			gc = sombra.getGraphicsContext2D();
			Image Fondo=new Image(new File("media/MuerteFondo.png").toURI().toString());
			gc.drawImage(Fondo,0, 0, 600, 600);
			Image Letras=new Image(new File("media/MuerteLetras.png").toURI().toString());
			gc.drawImage(Letras,0, 0, 600, 600);
			Font Puntuacion=new Font("media/ROMANUS.otf",30);
			gc.setFont(Puntuacion);
			gc.fillText("Recorriste: "+laberinto.getNiveles()+" niveles",300, 300);

			if (activo_musica) {
				Media hit = new Media(new File("media/die.mp3").toURI().toString());
				MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();
				activo_musica=false;
			}
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
		if (tipo_laberinto ==LabT.RUINAS) {
			Fondo=new Image(new File("media/pisoLuz.png").toURI().toString());
		}else if (tipo_laberinto==LabT.TUNEL) {
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

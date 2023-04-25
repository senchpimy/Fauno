package fes.aragon.controller;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
//import java.util.concurrent.TimeUnit;
import javafx.util.Duration;

import java.io.File;

import fes.aragon.modulo.*;
import fes.aragon.modulo.Jugador;

public class InicioController {
	Laberinto laberinto = new Laberinto();
	Jugador player = new Jugador();
	int pixel=20;

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
		//grc.clearRect(1,0, 600, 600);
        grc.drawImage(Fondo,0, 0, 600, 600);
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

	void NuevoLaberinto() {
		this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
	}
	public void Arriba() {
		if (laberinto.HabilitarMovimiento(player.getX(), player.getY()-pixel)) {
		gc = player_c.getGraphicsContext2D();
		this.player.Mover(gc,Movimiento.ARRIBA,pixel);
		}
	}
	public void Abajo() {
		if (laberinto.HabilitarMovimiento(player.getX(), player.getY()+pixel)) {
		gc = player_c.getGraphicsContext2D();
		this.player.Mover(gc,Movimiento.ABAJO,pixel);
		}
	}
	public void Izquierda() {
		if (laberinto.HabilitarMovimiento(player.getX()-pixel, player.getY())) {
		gc = player_c.getGraphicsContext2D();
		this.player.Mover(gc,Movimiento.IZQUIERDA,pixel);
		}
	}
	public void Derecha() {
		if (laberinto.HabilitarMovimiento(player.getX()+pixel, player.getY())) {
		gc = player_c.getGraphicsContext2D();
		this.player.Mover(gc,Movimiento.DERECHA,pixel);
		}
	}
	
	public void Reset() {
		if (this.player.Final()) {
		gc = player_c.getGraphicsContext2D();
		this.NuevoLaberinto();	
		this.player.Reset(gc,pixel);
		}		
	}
}

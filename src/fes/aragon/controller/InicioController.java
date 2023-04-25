package fes.aragon.controller;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
//import java.util.concurrent.TimeUnit;

import fes.aragon.modulo.*;
import fes.aragon.modulo.Jugador;

public class InicioController {
	Laberinto laberinto = new Laberinto();
	Jugador player = new Jugador();
	int pixel=20;

    @FXML 
    private Canvas img ;
    @FXML 
    private Canvas sombra ;
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
        			gc.setFill(Color.WHITE);
        			gc.fillRect(x, y, pixel, pixel);
        		}else {
        			gc.setFill(Color.RED);
        			gc.fillRect(x, y, pixel, pixel);
        		}
        	}
    }
	@FXML
	public void initialize() {
		this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
		this.JugadorUbi();
		gc = sombra.getGraphicsContext2D();
        //gc.setFill(Color.BLACK);
        //gc.fillRect(0, 0, 600, 600);
        //gc.setFill(null);
        //gc.fillOval(0, 0, pixel0, pixel0);
	}
	void NuevoLaberinto() {
		this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
	}
	public void Arriba() {
		if (laberinto.HabilitarMovimiento(player.getX(), player.getY()-pixel)) {
		gc = img.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.clearRect(this.player.getX(), this.player.getY(), pixel, pixel);
		this.player.Arriba();
		}
	}
	public void Abajo() {
		if (laberinto.HabilitarMovimiento(player.getX(), player.getY()+pixel)) {
		gc = img.getGraphicsContext2D();
        gc.clearRect(this.player.getX(), this.player.getY(), pixel, pixel);
		this.player.Abajo();
		}
	}
	public void Izquierda() {
		if (laberinto.HabilitarMovimiento(player.getX()-pixel, player.getY())) {
		gc = img.getGraphicsContext2D();
        gc.clearRect(this.player.getX(), this.player.getY(), pixel, pixel);
		this.player.Izquierda();
		}
	}
	public void Derecha() {
		if (laberinto.HabilitarMovimiento(player.getX()+pixel, player.getY())) {
		gc = img.getGraphicsContext2D();
        gc.clearRect(this.player.getX(), this.player.getY(), pixel, pixel);
		this.player.Derecha();
		}
	}
	
	void JugadorUbi() {
		gc = img.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(this.player.getX(), this.player.getY(), pixel, pixel);
	}
	public void Reset() {
		if (this.player.Final()) {
		this.NuevoLaberinto();	
		this.player.Reset();
		}		
		int[][] matriz = this.laberinto.getMatriz();
		this.JugadorUbi();
	}
}

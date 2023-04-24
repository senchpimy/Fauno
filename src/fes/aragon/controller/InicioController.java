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

    @FXML private Canvas img ;
    private GraphicsContext gc ;
    

    @FXML private void drawCanvas(int[][] matriz) {
    	gc = img.getGraphicsContext2D();
        
        for (int fila=0; fila<matriz.length;fila++)
        	for (int columna=0; columna<matriz[fila].length;columna++) {
        			int x=fila*25;
        			int y=columna*25;
        		if (matriz[fila][columna]==1) {
        			gc.setFill(Color.BLACK);
        			gc.fillRect(x, y, 25, 25);
        		}else if (matriz[fila][columna]==0) {
        			gc.setFill(Color.WHITE);
        			gc.fillRect(x, y, 25, 25);
        		}else {
        			gc.setFill(Color.RED);
        			gc.fillRect(x, y, 25, 25);
        		}
        	}
    }
	@FXML
	public void initialize() {
		this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
		this.JugadorUbi();
	}
	void NuevoLaberinto() {
		this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
	}
	public void Arriba() {
		if (laberinto.HabilitarMovimiento(player.getX(), player.getY()-25)) {
		gc = img.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(this.player.getX(), this.player.getY(), 25, 25);
		this.player.Arriba();
		}
	}
	public void Abajo() {
		if (laberinto.HabilitarMovimiento(player.getX(), player.getY()+25)) {
		gc = img.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(this.player.getX(), this.player.getY(), 25, 25);
		this.player.Abajo();
		}
	}
	public void Izquierda() {
		if (laberinto.HabilitarMovimiento(player.getX()-25, player.getY())) {
		gc = img.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(this.player.getX(), this.player.getY(), 25, 25);
		this.player.Izquierda();
		}
	}
	public void Derecha() {
		if (laberinto.HabilitarMovimiento(player.getX()+25, player.getY())) {
		gc = img.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(this.player.getX(), this.player.getY(), 25, 25);
		this.player.Derecha();
		}
	}
	
	void JugadorUbi() {
		gc = img.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(this.player.getX(), this.player.getY(), 25, 25);
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

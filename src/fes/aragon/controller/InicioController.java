package fes.aragon.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;

import fes.aragon.modulo.*;

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
        		}else {
        			gc.setFill(Color.WHITE);
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
		this.player.Arriba();
	}
	public void Abajo() {
		this.player.Abajo();
	}
	public void Izquierda() {
		this.player.Izquierda();
	}
	public void Derecha() {
		this.player.Derecha();
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
		this.drawCanvas(matriz);
		this.JugadorUbi();
	}
}

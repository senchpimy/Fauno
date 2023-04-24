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
        gc.setFill(Color.BLACK);
        
        for (int fila=0; fila<matriz.length;fila++)
        	for (int columna=0; columna<matriz[fila].length;columna++) {
        		if (matriz[fila][columna]==1) {
        			int x=fila*25;
        			int y=columna*25;
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
	public void Arriba() {
		this.player.Arriba();
		this.JugadorUbi();
	}
	public void Abajo() {
		this.player.Abajo();
		this.JugadorUbi();
	}
	public void Izquierda() {
		this.player.Izquierda();
		this.JugadorUbi();
	}
	public void Derecha() {
		this.player.Derecha();
		this.JugadorUbi();
	}
	
	void JugadorUbi() {
		gc = img.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(this.player.getX(), this.player.getY(), 25, 25);
	}
}

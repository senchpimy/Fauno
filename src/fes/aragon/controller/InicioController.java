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

    @FXML private Canvas img ;
    private GraphicsContext gc ;
    

    @FXML private void drawCanvas(int[][] matriz) {
    	gc = img.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        
        System.out.println("color set to black");
        for (int fila=0; fila<matriz.length;fila++)
        	for (int columna=0; columna<matriz[fila].length;columna++) {
        		if (matriz[fila][columna]==1) {
        			int x=fila*15;
        			int y=columna*15;
        			gc.fillRect(x, y, 15, 15);
        			System.out.println("Cuadrado: "+x+y);
        		}
        	}
    }
	@FXML
	public void initialize() {
		this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
	}
	public void Arriba() {
		//this.drawCanvas();
		System.out.println("Arriba");
	}
	public void Abajo() {
		System.out.println("Abajo");
	}
	public void Izquierda() {
		System.out.println("Izquierda");
	}
	public void Derecha() {
		System.out.println("Derecha");
	}
	public void Otracosa() {
	}
}

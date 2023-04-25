package fes.aragon.controller;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
//import java.util.concurrent.TimeUnit;
import javafx.util.Duration;
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
    private ImageView TituloImage;

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
        			//gc.setFill(Color.//TRANSPARENT);
        			gc.clearRect(x, y, pixel, pixel);
        		}else {
        			gc.setFill(Color.RED);
        			gc.fillRect(x, y, pixel, pixel);
        		}
        	}
    }
	@FXML
	public void initialize() {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(TituloImage);
		translate.setDuration(Duration.millis(1000));
		translate.setByY(200);
		translate.play();
		Empezar();
	}
	@FXML
	public void Empezar() {
		this.laberinto.laberintoRandom();
		int[][] matriz = this.laberinto.getMatriz();
		this.drawCanvas(matriz);
		this.JugadorUbi();
		gc = sombra.getGraphicsContext2D();
		gc = fondo.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, 600, 600);
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
		gc = player_c.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.clearRect(this.player.getX(), this.player.getY(), pixel, pixel);
		this.player.Arriba();
		}
	}
	public void Abajo() {
		if (laberinto.HabilitarMovimiento(player.getX(), player.getY()+pixel)) {
		gc = player_c.getGraphicsContext2D();
        gc.clearRect(this.player.getX(), this.player.getY(), pixel, pixel);
		this.player.Abajo();
		}
	}
	public void Izquierda() {
		if (laberinto.HabilitarMovimiento(player.getX()-pixel, player.getY())) {
		gc = player_c.getGraphicsContext2D();
        gc.clearRect(this.player.getX(), this.player.getY(), pixel, pixel);
		this.player.Izquierda();
		}
	}
	public void Derecha() {
		if (laberinto.HabilitarMovimiento(player.getX()+pixel, player.getY())) {
		gc = player_c.getGraphicsContext2D();
        gc.clearRect(this.player.getX(), this.player.getY(), pixel, pixel);
		this.player.Derecha();
		}
	}
	
	void JugadorUbi() {
		gc = player_c.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(this.player.getX(), this.player.getY(), pixel, pixel);
	}
	public void Reset() {
		if (this.player.Final()) {
		this.NuevoLaberinto();	
		this.player.Reset();
		}		
		this.JugadorUbi();
	}
}

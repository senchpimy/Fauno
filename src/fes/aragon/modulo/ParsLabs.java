package fes.aragon.modulo;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ParsLabs {
    //@FXML 
    private Image bordeArriba=new Image(new File("media/paredes/bordeArriba.png").toURI().toString());
    private Image bordeAbajo=new Image(new File("media/paredes/bordeDebajo.png").toURI().toString());
    private Image bordeDerecha=new Image(new File("media/paredes/bordeDerecha.png").toURI().toString());
    private Image bordeIzquierda=new Image(new File("media/paredes/bordeIzquierda.png").toURI().toString());
    private Image simple=new Image(new File("media/paredes/soloSimpleUnico.png").toURI().toString());
    private Image simpleVertical=new Image(new File("media/paredes/soloSimpleVertical.png").toURI().toString());
    private Image simpleHorizontal=new Image(new File("media/paredes/soloSimpleHorizontal.png").toURI().toString());
    private Image topeSimpleArriba=new Image(new File("media/paredes/topeSimpleArriba.png").toURI().toString());
    private Image topeSimpleAbajo=new Image(new File("media/paredes/topeSimpleDebajo.png").toURI().toString());
    private Image topeSimpleDerecha=new Image(new File("media/paredes/topeSimpleDerecha.png").toURI().toString());
    private Image topeSimpleIzquierda=new Image(new File("media/paredes/topeSimpleIzquierda.png").toURI().toString());
    private Image esquinaSimpleSupIzq=new Image(new File("media/paredes/esquinaSimpleSupIzq.png").toURI().toString());
    private Image esquinaSimpleInfIzq=new Image(new File("media/paredes/esquinaSimpleInfIzq.png").toURI().toString());
    private Image esquinaSimpleInfDer=new Image(new File("media/paredes/esquinaSimpleInfDer.png").toURI().toString());
    private Image esquinaSimpleSupDer=new Image(new File("media/paredes/esquinaSimpleSupDer.png").toURI().toString());

    private Image RuinaBordeArriba=new Image(new File("media/paredes/bordeArriba.png").toURI().toString());
    private Image RuinaBordeAbajo=new Image(new File("media/paredes/bordeDebajo.png").toURI().toString());
    private Image RuinaBordeDerecha=new Image(new File("media/paredes/bordeDerecha.png").toURI().toString());
    private Image RuinaBordeIzquierda=new Image(new File("media/paredes/bordeIzquierda.png").toURI().toString());
    private Image RuinaSimple=new Image(new File("media/paredes/soloSimpleUnico.png").toURI().toString());
    private Image RuinaVertical=new Image(new File("media/paredes/soloSimpleVertical.png").toURI().toString());
    private Image RuinaHorizontal=new Image(new File("media/paredes/soloSimpleHorizontal.png").toURI().toString());
    private Image RuinaTopeArriba=new Image(new File("media/paredes/topeSimpleArriba.png").toURI().toString());
    private Image RuinaTopeAbajo=new Image(new File("media/paredes/topeSimpleDebajo.png").toURI().toString());
    private Image RuinaTopeDerecha=new Image(new File("media/paredes/topeSimpleDerecha.png").toURI().toString());
    private Image RuinaTopeIzquierda=new Image(new File("media/paredes/topeSimpleIzquierda.png").toURI().toString());
    private Image RuinaEsquinaSupIzq=new Image(new File("media/paredes/esquinaSimpleSupIzq.png").toURI().toString());
    private Image RuinaEsquinaInfIzq=new Image(new File("media/paredes/esquinaSimpleInfIzq.png").toURI().toString());
    private Image RuinaEsquinaInfDer=new Image(new File("media/paredes/esquinaSimpleInfDer.png").toURI().toString());
    private Image RuinaEsquinaSupDer=new Image(new File("media/paredes/esquinaSimpleSupDer.png").toURI().toString());
    int[][] matriz;
    int pixel;
    public void setMatriz(int[][]matr) {
    matriz=matr	;
    }
    public void setPixel(int matr) {
    pixel=matr	;
    }
    public void pintarSimple(int fila, int columna, GraphicsContext gc) {
    				int arriba=1;
    				int abajo=1;
    				int derecha=1;
    				int izquierda=1;
        			int x=fila*pixel;
        			int y=columna*pixel;

    				try {
    					izquierda = matriz[fila+1][columna];
    				}catch(Exception _){
    				}

    				try {
    					derecha = matriz[fila-1][columna];
    				}catch(Exception _){
    				}

    				try {
    					arriba = matriz[fila][columna-1];
    				}catch(Exception _){
    				}

    				try {
    					abajo = matriz[fila][columna+1];
    				}catch(Exception _){
    				}

    				if (arriba==1 && abajo==1 && derecha ==1 && izquierda==1) {
    				gc.setFill(Color.BLACK);
        			gc.fillRect(x, y, pixel, pixel);
    				}else if (arriba==1 && abajo!=1 && derecha ==1 && izquierda==1) {
    					gc.drawImage(bordeAbajo, x, y);
    				}else if (arriba!=1 && abajo==1 && derecha ==1 && izquierda==1) {
    					gc.drawImage(bordeArriba, x, y);
    				}else if (arriba!=1 && abajo!=1 && derecha !=1 && izquierda!=1) {
    					gc.drawImage(simple, x, y);
    				}else if (arriba==1 && abajo==1 && derecha ==1 && izquierda!=1) {
    					gc.drawImage(bordeDerecha, x, y);
    				}else if (arriba==1 && abajo==1 && derecha !=1 && izquierda==1) {
    					gc.drawImage(bordeIzquierda, x, y);
    				}else if (arriba==1 && abajo==1 && derecha!=1 && izquierda !=1) {
    					gc.drawImage(simpleVertical, x, y);
    				}else if (arriba!=1 && abajo!=1 && derecha==1 && izquierda ==1) {
    					gc.drawImage(simpleHorizontal, x, y);
    				}else if (arriba!=1 && abajo==1 && derecha!=1 && izquierda !=1) {
    					gc.drawImage(topeSimpleArriba, x, y);
    				}else if (arriba==1 && abajo!=1 && derecha!=1 && izquierda !=1) {
    					gc.drawImage(topeSimpleAbajo, x, y);
    				}else if (arriba!=1 && abajo!=1 && derecha==1 && izquierda !=1) {
    					gc.drawImage(topeSimpleDerecha, x, y);
    				}else if (arriba!=1 && abajo!=1 && derecha!=1 && izquierda ==1) {
    					gc.drawImage(topeSimpleIzquierda, x, y);
    				}else if (arriba!=1 && abajo==1 && derecha==1 && izquierda !=1) {
    					gc.drawImage(esquinaSimpleSupDer, x, y);
    				}else if (arriba==1 && abajo!=1 && derecha==1 && izquierda !=1) {
    					gc.drawImage(esquinaSimpleInfDer, x, y);
    				}else if (arriba==1 && abajo!=1 && derecha!=1 && izquierda ==1) {
    					gc.drawImage(esquinaSimpleInfIzq, x, y);
    				}else if (arriba!=1 && abajo==1 && derecha!=1 && izquierda ==1) {
    					gc.drawImage(esquinaSimpleSupIzq, x, y);
        			}else {
    				gc.setFill(Color.WHITE);
        			gc.fillRect(x, y, pixel, pixel);
        			}
    }
    public void pintarRuinas(int fila, int columna, GraphicsContext gc) {
    				int arriba=1;
    				int abajo=1;
    				int derecha=1;
    				int izquierda=1;
        			int x=fila*pixel;
        			int y=columna*pixel;

    				try {
    					izquierda = matriz[fila+1][columna];
    				}catch(Exception _){
    				}

    				try {
    					derecha = matriz[fila-1][columna];
    				}catch(Exception _){
    				}

    				try {
    					arriba = matriz[fila][columna-1];
    				}catch(Exception _){
    				}

    				try {
    					abajo = matriz[fila][columna+1];
    				}catch(Exception _){
    				}

    				if (arriba==1 && abajo==1 && derecha ==1 && izquierda==1) {
    				gc.setFill(Color.BLACK);
        			gc.fillRect(x, y, pixel, pixel);
    				}else if (arriba==1 && abajo!=1 && derecha ==1 && izquierda==1) {
    					gc.drawImage(RuinaBordeAbajo, x, y);
    				}else if (arriba!=1 && abajo==1 && derecha ==1 && izquierda==1) {
    					gc.drawImage(RuinaBordeArriba, x, y);
    				}else if (arriba!=1 && abajo!=1 && derecha !=1 && izquierda!=1) {
    					gc.drawImage(RuinaSimple, x, y);
    				}else if (arriba==1 && abajo==1 && derecha ==1 && izquierda!=1) {
    					gc.drawImage(RuinaBordeDerecha, x, y);
    				}else if (arriba==1 && abajo==1 && derecha !=1 && izquierda==1) {
    					gc.drawImage(RuinaBordeIzquierda, x, y);
    				}else if (arriba==1 && abajo==1 && derecha!=1 && izquierda !=1) {
    					gc.drawImage(RuinaVertical, x, y);
    				}else if (arriba!=1 && abajo!=1 && derecha==1 && izquierda ==1) {
    					gc.drawImage(RuinaHorizontal, x, y);
    				}else if (arriba!=1 && abajo==1 && derecha!=1 && izquierda !=1) {
    					gc.drawImage(RuinaTopeArriba, x, y);
    				}else if (arriba==1 && abajo!=1 && derecha!=1 && izquierda !=1) {
    					gc.drawImage(RuinaTopeAbajo, x, y);
    				}else if (arriba!=1 && abajo!=1 && derecha==1 && izquierda !=1) {
    					gc.drawImage(RuinaTopeDerecha, x, y);
    				}else if (arriba!=1 && abajo!=1 && derecha!=1 && izquierda ==1) {
    					gc.drawImage(RuinaTopeIzquierda, x, y);
    				}else if (arriba!=1 && abajo==1 && derecha==1 && izquierda !=1) {
    					gc.drawImage(RuinaEsquinaSupDer, x, y);
    				}else if (arriba==1 && abajo!=1 && derecha==1 && izquierda !=1) {
    					gc.drawImage(RuinaEsquinaInfDer, x, y);
    				}else if (arriba==1 && abajo!=1 && derecha!=1 && izquierda ==1) {
    					gc.drawImage(RuinaEsquinaInfIzq, x, y);
    				}else if (arriba!=1 && abajo==1 && derecha!=1 && izquierda ==1) {
    					gc.drawImage(RuinaEsquinaSupIzq, x, y);
        			}else {
    				gc.setFill(Color.WHITE);
        			gc.fillRect(x, y, pixel, pixel);
        			}
    }
}

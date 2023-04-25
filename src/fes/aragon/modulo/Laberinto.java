package fes.aragon.modulo;

import java.util.Random;
import java.util.Vector;

import javafx.util.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Laberinto {
	private int filas = 30;
	private int columnas = 30;
	private int niveles = 0;
	private int pixel=600/30;
	private int[][] matriz = new int[this.filas][this.columnas];
	private Random rand = new Random();

	public void laberintoRandom() {
		if (rand.nextInt(2) == 0) {
			this.nivelPasado();
			for (int i = 0; i < filas; i++)
				for (int j = 0; j < filas; j++)
					this.matriz[i][j] = (rand.nextFloat() > 0.8 ? 1 : 0);

			for (int i = 5; i < filas - 5; i++)
				for (int j = 5; j < columnas - 5; j++) {
					matriz[0][j] = 0;
					matriz[this.filas-1][j] = 0;
				}
			matriz[0][this.columnas/2] = 2;
			matriz[this.filas-1][this.columnas/2] = 2;
		} else {
			this.resetMatriz(1);
			generateTunels(3);
			//this.mazeDivision(0, 0, 19, 19);
			matriz[0][15] = 2;
			matriz[29][15] = 2;
		}
      }

	void mazeDivision(int x, int y, int numCols, int numRow) {
		if (numCols < 2 || numRow < 2)
			return;
		Boolean horizontal;
		if (numRow < numCols)
			horizontal = true;
		else if (numCols < numRow)
			horizontal =false;
		else
			horizontal=rand.nextBoolean();

		int wx= x+(horizontal? 0:rand.nextInt(numCols-2));
		int wy= y+(horizontal? rand.nextInt(numRow-2):0);

		int px= wx+(horizontal? 0:rand.nextInt(numCols));
		int py= wy+(horizontal? rand.nextInt(numRow):0);
		
		int dx=(horizontal?1:0);
		int dy=(horizontal?0:1);
		
		int distancia = (horizontal?numCols:numRow);
		
		int dir = (horizontal? 1:2);
		for (int i =0; i<numCols;i++) {
		        if (wx!=px || wy!=py)
		      	  matriz[wy][wx]=1;

		          wx += dx;
		          wy += dy;
		}
		int nx=x;
		int ny=y;
		int w = (horizontal? numCols:x+numCols-wx-1);
		int h = (horizontal? y+numRow-wy-1:numRow);
		this.mazeDivision(nx, ny, w, h);
	}
	
	public void tunel(int x1, int y1, int x2, int y2) {
	    int dx = Math.abs(x2 - x1);
	    int dy = Math.abs(y2 - y1);
	    int sx = (x1 < x2? 1:-1);
	    int sy = (y1 < y2? 1:-1);
	    int err = dx - dy;
	    while (x1!=x2 || y1!=y2) {
	    	matriz[x1][y1]=0;
	    	try {
	            matriz[x1][y1+1] = 0;
	    	}catch (Exception _){
	            matriz[x1][y1-1] = 0;
	    	}
	    	int e2=2*err;
	    	if (e2> -dy) {
	    		err-=dy;
	    		x1+=sx;
	    	}
	    	if (e2< dx) {
	    		err+=dx;
	    		y1+=sy;
	    	}
	    }
	    matriz[x1][y1]=0;
	} 

	public void generateTunels(int num) {
		int prev_fila=0;
		int prev_col=15;
		for (int i=0;i<num;i++) {
	        int fila=rand.nextInt(filas-1);
	        int columna=rand.nextInt(1,columnas-1);
	        tunel(prev_fila,prev_col,fila,columna);
	        prev_fila=fila;
	        prev_col=columna;
		}
	       tunel(prev_fila,prev_col,29,16);
	       tunel(prev_fila,prev_col,29,15);
	}

	public void matrizEstado() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < filas; j++) {
				System.out.print(this.matriz[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	void nivelPasado() {
		this.niveles++;
	}

	public int getNiveles() {
		return niveles;
	}

	public int[][] getMatriz() {
		return this.matriz;
	}

	public void setNiveles(int niveles) {
		this.niveles = niveles;
	}

	public Boolean HabilitarMovimiento(int x, int y) {
		int valor;
		try {
			valor =matriz[x / pixel][y / pixel] ;
		}catch(Exception e){
			valor=1;
		}
		if (valor != 1) 
			return true;
		return false;
	}

	void resetMatriz(int val) {
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < filas; j++)
				this.matriz[i][j] = val;
	}

	int random(int max) {
		max = max / 2;
		int val = rand.nextInt(max);
		while (val % 2 != 0) {
			val = rand.nextInt(max);
		}
		return val;
	}
}

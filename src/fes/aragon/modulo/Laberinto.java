package fes.aragon.modulo;

import java.util.Random;
import java.util.Vector;

import javafx.util.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Laberinto {
	private int filas = 20;
	private int columnas = 20;
	private int niveles = 0;
	private int[][] matriz = new int[this.filas][this.columnas];
	private Random rand = new Random();

	public void laberintoRandom() {
		//if (rand.nextInt(2) == 0) {
			this.nivelPasado();
			for (int i = 0; i < filas; i++)
				for (int j = 0; j < filas; j++)
					this.matriz[i][j] = (rand.nextFloat() > 0.8 ? 1 : 0);

			for (int i = 5; i < filas - 5; i++)
				for (int j = 5; j < columnas - 5; j++) {
					matriz[0][j] = 0;
					matriz[19][j] = 0;
				}
			matriz[0][10] = 2;
			matriz[19][10] = 2;
//		} else {
//			this.resetMatriz(0);
//			this.laberintoDivision(0, 0, 19, 19);
//			matriz[0][10] = 2;
//			matriz[19][10] = 2;
//		}
	}

	/**
	 * 
	 */
//	void laberintoDivision(int x, int y, int numCols, int numFil) {
//		if (numCols < 2 || numFil < 2)
//			return;
//		Boolean horizontal;
//		  if (numFil < numCols)
//		    horizontal = true;
//		  else if (numCols < numFil)
//		    horizontal =false;
//		  else
//		 horizontal=rand.nextBoolean();
//
//		  int wx= x+(horizontal? 0:rand.nextInt(numCols-2));
//		  int wy= y+(horizontal? rand.nextInt(numFil-2):0);
//
//		  int px= wx+(horizontal? 0:rand.nextInt(numCols));
//		  int py= wy+(horizontal? rand.nextInt(numFil):0);
//		  
//		  int dx=(horizontal?1:0);
//		  int dy=(horizontal?0:1);
//		  
//		  int distancia = (horizontal?numCols:numFil);
//		  
//		  int dir = (horizontal? 1:2);
//		  for (int i =0; i<numCols;i++) {
//			  if (wx!=px || wy!=py)
//				  matriz[wy][wx]=1;
//
//			    wx += dx;
//			    wy += dy;
//		  }
//		  int nx=x;
//		  int ny=y;
//		  int w = (horizontal? numCols:x+numCols-wx-1);
//		  int h = (horizontal? y+numFil-wy-1:numFil);
//		  this.laberintoDivision(nx, ny, w, h);
//		//if (horizontal){
////			int fila = y + 1 + rand.nextInt(numFil - 1);
////			int puerta = x + 1 + rand.nextInt(numCols - 1);
////			while (fila == 10) {
////				fila = y + 1 + rand.nextInt(numFil);
//			}
//			for (int i = 0; i < numCols - 1; i++)
//				if (i != puerta)
//					matriz[i][fila] = 1;
//
//			matriz[puerta][fila] = 0;
//			int newFil = numFil - (fila - y);
//			this.laberintoDivision(x, fila, numCols, fila - y);
//			//this.laberintoDivision(x, fila, numCols, numFil-fila);
//			this.laberintoDivision(x, y, numCols, newFil);
//		//} else { // vertical
//			int col = x + 1 + rand.nextInt(numCols - 1);
//			int puerta = y + 1 + rand.nextInt(numFil - 1);
//			while (col == 0 || col == 19) {
//				col = x + 1 + rand.nextInt(numCols);
//			}
//			for (int i = 0; i < numFil - 1; i++)
//				if (i != puerta)
//					matriz[col][i] = 1;
//
//			matriz[col][puerta] = 0;
//			int newCols = numCols - (col - x);
//			//this.laberintoDivision(col, y, col - x, numFil);
//			this.laberintoDivision(col, y, numCols-col, numFil);
//			this.laberintoDivision(x, y, newCols, numFil);
//
//		}
	//}

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
			valor =matriz[x / 25][y / 25] ;
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

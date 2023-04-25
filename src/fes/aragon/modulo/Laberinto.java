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
		//int escojerNivel=rand.nextInt(2);
		Boolean escojerNivel=rand.nextBoolean();
		float poblacion = rand.nextFloat();
		while (poblacion <0.65 || poblacion>0.8)
			poblacion = rand.nextFloat();
		if (escojerNivel) {
			this.nivelPasado();
			for (int i = 0; i < filas; i++)
				for (int j = 0; j < filas; j++)
					this.matriz[i][j] = (rand.nextFloat() > poblacion ? 1 : 0);

			for (int i = 5; i < filas - 5; i++)
				for (int j = 5; j < columnas - 5; j++) {
					matriz[0][j] = 0;
					matriz[this.filas-1][j] = 0;
				}
			matriz[0][this.columnas/2] = 2;
			matriz[this.filas-1][this.columnas/2] = 2;
		} else {
			this.resetMatriz(1);
			if (rand.nextBoolean()) {
				generateTunels(rand.nextInt(2,6));
			}else {
				generateTunelsDif(rand.nextInt(2,6));
			}
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
		
		//int distancia = (horizontal?numCols:numRow);
		
		//int dir = (horizontal? 1:2);
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

	public void generateTunelsDif(int num) {
		int prev_fila=0;
		int prev_col=15;
		int [][] cuadrantes=new int[][] {{0,0,15,15},{0,15,15,29}
										,{15,0,29,15},{15,15,30,30}};
		int index_cuadrantes=rand.nextInt(4);//Verficar out of range
		int menor_columna=cuadrantes[index_cuadrantes][0];
		int menor_fila=cuadrantes[index_cuadrantes][1];
		int mayor_fila=cuadrantes[index_cuadrantes][2];
		int mayor_columna=cuadrantes[index_cuadrantes][3];
		for (int i=0;i<num;i++) {
			int max = Math.max(mayor_fila,menor_fila);
			int men = Math.min(mayor_fila,menor_fila);
			if (max==men) {men=0;max=29;}
			int max2 = Math.max(mayor_columna,menor_columna);
			int men2 = Math.min(mayor_columna,menor_columna);
			if (max2==men2) {men2=0;max2=29;}

	        int fila=rand.nextInt(men,max);
	        int columna=rand.nextInt(men2,max2);
	        tunel(prev_fila,prev_col,fila,columna);
	        prev_fila=fila;
	        prev_col=columna;
	        index_cuadrantes++;
	        int index_cuadrantes_next=rand.nextInt(4);//Verficar out of range
	        while (index_cuadrantes==index_cuadrantes_next) 
	        	index_cuadrantes_next=rand.nextInt(4);//Verficar out of range
	        index_cuadrantes=index_cuadrantes_next;
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

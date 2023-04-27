package fes.aragon.modulo;

import java.util.Random;
import java.util.Stack;

public class Laberinto {
	private int filas = 30;
	private int columnas = 30;
	private int niveles = 0;
	private int pixel=600/30;
	private int[][] matriz = new int[this.filas][this.columnas];
	private Random rand = new Random();

	public LabT laberintoRandom() {
		LabT escojerNivel=LabT.randomTipo();
		if (escojerNivel==LabT.RUINAS) {
			float poblacion = rand.nextFloat();
			while (poblacion <0.65 || poblacion>0.8)
				poblacion = rand.nextFloat();

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
		} else if (escojerNivel==LabT.TUNEL) {
			resetMatriz(1);
			if (rand.nextBoolean()) {
				generateTunels(rand.nextInt(3,7));
			}else {
				generateTunelsDif(rand.nextInt(3,7));
			}
			matriz[0][15] = 2;
			matriz[29][15] = 2;
		}else if (escojerNivel==LabT.NORMAL){
			resetMatriz(0);
			mazeDivision();
			matriz[10][0] = 1;
			matriz[filas-10][0] = 1;
			matriz[filas-1][columnas/2] = 2;
			matriz[0][columnas/2] = 2;
			matriz[filas-1][columnas/2] = 2;
		}
		GenerarComida();
		return escojerNivel;
      }

	void mazeDivision() {
	    int VERTICAL = 0;
	    int HORIZONTAL = 1;            
		Stack<Tupla<Tupla<Integer, Integer>, Tupla<Integer, Integer>>> regiones = new Stack<>();
        regiones.push(new Tupla<>(new Tupla<>(1, 0), new Tupla<>(28, 29)));
        Random random = new Random();

        while (!regiones.isEmpty()) {
            Tupla<Tupla<Integer, Integer>, Tupla<Integer, Integer>> Region = regiones.pop();
            int minY = Region.primero.primero;
            int minX = Region.primero.segundo;
            int maxY = Region.segundo.primero;
            int maxX = Region.segundo.segundo;
            int alto = maxY - minY + 1;
            int ancho = maxX - minX + 1;

            if (alto <= 1 || ancho <= 1) {
                continue;
            }

            int dir;
            if (ancho < alto) {
                dir = HORIZONTAL; 
            } else if (ancho > alto) {
                dir = VERTICAL; 
            } else {
                if (ancho == 2) {
                    continue;
                }
                dir = random.nextInt(2);
            }
            int cortLon = (dir == HORIZONTAL) ? ancho : alto;
            if (cortLon < 3) {
                continue;
            }
            int cutPos = random.nextInt(cortLon / 2) * 2 + 1;
            int hoyo = random.nextInt((dir == VERTICAL) ? alto : ancho) / 2 * 2;
            if (dir == VERTICAL) {
                for (int row = minY; row <= maxY; row++) {
                    matriz[row][minX + cutPos] = 1;
                }
                matriz[minY + hoyo][minX + cutPos] = 0;
            } else {  
                for (int col = minX; col <= maxX; col++) {
                    matriz[minY + cutPos][col] = 1;
                }
                matriz[minY + cutPos][minX + hoyo] = 0;
            }

            if (dir == VERTICAL) {
                regiones.push(new Tupla<>(new Tupla<>(minY, minX), new Tupla<>(maxY, minX + cutPos - 1)));
                regiones.push(new Tupla<>(new Tupla<>(minY, minX + cutPos + 1), new Tupla<>(maxY, maxX)));
            } else {  // horizontal
                regiones.push(new Tupla<>(new Tupla<>(minY, minX), new Tupla<>(minY + cutPos - 1, maxX)));
                regiones.push(new Tupla<>(new Tupla<>(minY + cutPos + 1, minX), new Tupla<>(maxY, maxX)));
            }
        }
    }

    static class Tupla<T, T1> {
        T primero;
        T1 segundo;

        public Tupla(T pri, T1 se) {
            this.primero = pri;
            this.segundo = se;
            }
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

	public int HabilitarMovimiento(int x, int y) {
		int valor;
		try {
			valor =matriz[x / pixel][y / pixel] ;
			if (valor==1)return 1;
		}catch(Exception e){
			return 1;
		}
		if (valor == 0) 
			return 0;
		else if (valor==3)
			matriz[x / pixel][y / pixel]=0;
			return 3;
		//return 1;
	}

	void resetMatriz(int val) {
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
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
	private void GenerarComida() {
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				if (rand.nextFloat()<0.01 && matriz[i][j]==0)
					this.matriz[i][j] = 3;
		
	}
}

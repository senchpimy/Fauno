package fes.aragon.modulo;
import java.util.Random;

public class Laberinto {
	private int filas = 20;
	private int columnas = 20;
	private int niveles = 0;
	private int[][] matriz =new int[this.filas][this.columnas];
	private Random rand=new Random();
	
	public void laberintoRandom() {
		this.nivelPasado();
		for (int i = 0; i<filas;i++) {
			for (int j = 0; j<filas;j++) {
				this.matriz[i][j]=(rand.nextFloat() > 0.8? 1:0);
			}
		}
	}
	
	public void matrizEstado() {
		for (int i = 0; i<filas;i++) {
			for (int j = 0; j<filas;j++) {
				System.out.print(this.matriz[i][j]+" ");
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

	public int[][] getMatriz(){
		return this.matriz;
	}
	public void setNiveles(int niveles) {
		this.niveles = niveles;
	}
}

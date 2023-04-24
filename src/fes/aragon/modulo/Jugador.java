package fes.aragon.modulo;

public class Jugador {
	private int Columna;
	private int Fila;
	
	public void Izquierda() {
		this.Fila=this.Columna-1;
	}
	public void Derecha() {
		this.Fila=this.Columna+1;
	}
	public void Abajo() {
		this.Fila=this.Fila-1;
	}
	public void Arriba() {
		this.Fila=this.Fila+1;
	}
}

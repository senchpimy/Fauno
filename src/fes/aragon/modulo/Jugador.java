package fes.aragon.modulo;

public class Jugador {
	private int X;
	private int Y;
	
	public Jugador(){
		X=0;
		Y=250;
	}
	
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
	public void Izquierda() {
		if (X>0) {
			X-=10;
		}
	}
	public void Derecha() {
		if (X<500) {
			X+=10;
		}
	}
	public void Abajo() {
		if (Y<480) {
			Y+=10;
		}
	}
	public void Arriba() {
		if (Y>0) {
			Y-=10;
		}
	}
}

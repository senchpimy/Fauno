package fes.aragon.modulo;

public class Jugador {
	private int X;
	private int Y;
	
	public Jugador(){
		X=0;
		Y=300;
	}
	
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
	public void Izquierda() {
		if (X>0) {
			X-=20;
		}
	}
	public void Derecha() {
		if (X<570) {
			X+=20;
		}
	}
	public void Abajo() {
		if (Y<570) {
			Y+=20;
		}
	}
	public void Arriba() {
		if (Y>0) {
			Y-=20;
		}
	}
	public Boolean Final() {
		return (X>570 && Y==300? true:false);
	}
	
	public void Reset() {
		X=0;
		Y=300;
	}
}

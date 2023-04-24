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
			X-=25;
		}
	}
	public void Derecha() {
		if (X<455) {
			X+=25;
		}
	}
	public void Abajo() {
		if (Y<470) {
			Y+=25;
		}
	}
	public void Arriba() {
		if (Y>0) {
			Y-=25;
		}
	}
	public Boolean Final() {
		return (X>470 && Y==250? true:false);
	}
	
	public void Reset() {
		X=0;
		Y=250;
	}
}

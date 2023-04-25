package fes.aragon.modulo;

public abstract class Creatura {
	public int X;
	public int Y;
	public void Izquierda() {
		if (this.X>0) {
			this.X-=20;
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
}

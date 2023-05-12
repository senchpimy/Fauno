package fes.aragon.modulo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public abstract class Creatura {
	public int X;
	public int Y;

	public int pixel;
	public void setPixel(int pixe){
		pixel=pixe;
	}
	public void Izquierda() {
		if (this.X>0) {
			this.X-=pixel;
		}
	}
	public void Derecha() {
		if (X<690) {
			X+=pixel;
		}
	}
	public void Abajo() {
		if (Y<680) {
			Y+=pixel;
		}
	}
	public void Arriba() {
		if (Y>0) {
			Y-=pixel;
		}
	}

	public abstract void pintar();
	public abstract void teclado(KeyEvent evento,boolean presiona);
	public abstract void raton(KeyEvent evento);
	public abstract void logicaCalculos();
}

package fes.aragon.controller;
import com.modulo.modulo.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Stack;


public class InicioController {
    Laberinto laberinto = new Laberinto();
    Jugador player = new Jugador();
    int pixel=23;
    Deambulante deambulante= new Deambulante();
    LabT tipo_laberinto;
    Musica musica = new Musica();

    Boolean activo=true;
    ParsLabs paredes=new ParsLabs();
    Boolean activo_musica=true;
    Caminadores caminadores = new Caminadores();

    @FXML
    private Canvas img ;
    @FXML
    private Canvas player_c ;
    @FXML
    private Canvas fondo;
    @FXML
    private Canvas sombra;

    @FXML
    private Canvas deambulantes;

    @FXML
    private Canvas entradaLetras;

    @FXML
    private Canvas Caminadores;
    @FXML
    private Canvas entrada;

    Image Comida = new Image(new File("media/Comida.png").toURI().toString());

    private GraphicsContext gc ;
    Boolean borrar=true;

    private void drawCanvas(int[][] matriz) {
        gc = img.getGraphicsContext2D();
        Stack<Tupla<Integer, Integer>> comida = new Stack<>();
        paredes.setMatriz(matriz);
        paredes.setPixel(pixel);
        gc.clearRect(0, 0, 690, 690);
        if (tipo_laberinto!=LabT.RUINAS) {
            for (int fila=0; fila<matriz.length;fila++)
                for (int columna=0; columna<matriz[fila].length;columna++) {
                    int x=fila*pixel;
                    int y=columna*pixel;
                    if (matriz[fila][columna]==1) {
                        paredes.pintarSimple(fila, columna, gc);
                    }else if (matriz[fila][columna]==0) {
                    }else if (matriz[fila][columna]==3) {//Comida
                        comida.push(new Tupla<>(x,y));
                    }else {
                        gc.setFill(Color.RED);
                        gc.fillRect(x, y, pixel, pixel);
                    }
                }

        }else {
            for (int fila=0; fila<matriz.length;fila++)
                for (int columna=0; columna<matriz[fila].length;columna++) {
                    int elemento =matriz[fila][columna];
                    int x=fila*pixel;
                    int y=columna*pixel;
                    if (elemento==1) {
                        paredes.pintarRuinas(fila, columna, gc);
                    }else if (elemento==0) {
                    }else if (elemento==3) { //comida
                        comida.push(new Tupla<>(x,y));
                    }else {
                        gc.setFill(Color.RED);
                        gc.fillRect(x, y, pixel, pixel);
                    }
                }
        }
        while (!comida.isEmpty()) {
            Tupla<Integer,Integer> comi = comida.pop();
            gc.drawImage(Comida, comi.primero, comi.segundo);
        }
        PintPiso();
    }
    @FXML
    public void initialize() {
        deambulante.setPixel(pixel);
        caminadores.setPixel(pixel);
        deambulante.setGc(deambulantes.getGraphicsContext2D());
        caminadores.setGc(Caminadores.getGraphicsContext2D());
        player.setPixel(pixel);
        player.setgc(player_c.getGraphicsContext2D());
        Image Entrada = new Image(new File("media/Entrada.png").toURI().toString());
        Image Titulo = new Image(new File("media/Titulo.png").toURI().toString());
        long tiempoInicio = System.nanoTime();
        AnimationTimer tiempo = new AnimationTimer() {
            @Override
            public void handle(long tiempoActual) {
                double sec = (tiempoActual - tiempoInicio) / 1000000000.0;
                GraphicsContext grc=entrada.getGraphicsContext2D();
                GraphicsContext grc1=entradaLetras.getGraphicsContext2D();
                if (borrar) {
                    grc.clearRect(0, 0, 690, 690);
                    grc1.clearRect(0, 0, 690, 690);
                }
                if ((int)sec%2==0)
                {
                    deambulante.unlock((int)sec);
                    deambulante.Mover();
                    caminadores.unlock((int)sec);
                    caminadores.Mover();
                }

                double y;
                if (sec<24) {
                    y=-(110*sec);
                }else {
                    y=-2500;
                    borrar=false;
                }

                if (sec<10) {
                    grc1.drawImage(Titulo,10,100);
                }else {
                    grc1.drawImage(Titulo,0,y);
                }
                grc.drawImage(Entrada,0,y);
            }

        };
        tiempo.start();
        musica.start();
        NuevoLaberinto();
    }

    public void VerificarActividad() {
        activo = player.Salud();
        if (!activo) {
            musica.dete();
            gc = sombra.getGraphicsContext2D();
            Font Puntuacion=new Font("ROMANUS_regular",30);
            gc.fillText("Recorriste: "+laberinto.getNiveles()+" niveles",300, 300);
            Image muerteFondo=new Image(new File("media/MuerteFondo.png").toURI().toString());
            Image muerteLetras=new Image(new File("media/MuerteLetras.png").toURI().toString());
            gc.setFont(Puntuacion);
            if (activo_musica) {
                gc.drawImage(muerteFondo,0,0);
                gc.drawImage(muerteLetras,30,30);
                Media hit = new Media(new File("media/die.mp3").toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(hit);
                mediaPlayer.play();
                activo_musica=false;
                gc.fillText("Recorriste: "+laberinto.getNiveles()+" niveles",300, 300);
            }
        }
    }

    void NuevoLaberinto() {
        laberinto.nivelPasado();
        tipo_laberinto=this.laberinto.laberintoRandom();
        int[][] matriz = this.laberinto.getMatriz();
        deambulante.setMatriz(matriz);
        caminadores.setMatriz(matriz);
        drawCanvas(matriz);
    }

    public void Arriba() {
        int res =(laberinto.HabilitarMovimiento(player.getX(), player.getY()-pixel));
        if (res==1) return;
        player.Mover(Movimiento.ARRIBA);
        if (res==3) {
            player.Alimentarse();
            gc = img.getGraphicsContext2D();
            gc.clearRect(player.getX(), player.getY(), pixel, pixel);
        }
    }
    public void Abajo() {
        int res=laberinto.HabilitarMovimiento(player.getX(), player.getY()+pixel);
        if (res==1) return;
        this.player.Mover(Movimiento.ABAJO);
        if (res==3) {
            player.Alimentarse();
            gc = img.getGraphicsContext2D();
            gc.clearRect(player.getX(), player.getY(), pixel, pixel);
        }
    }
    public void Izquierda() {
        int res=  (laberinto.HabilitarMovimiento(player.getX()-pixel, player.getY()));
        if (res==1) return;
        this.player.Mover(Movimiento.IZQUIERDA);
        if (res==3) {
            player.Alimentarse();
            gc = img.getGraphicsContext2D();
            gc.clearRect(player.getX(), player.getY(), pixel, pixel);
        }
    }
    public void Derecha() {
        int res= (laberinto.HabilitarMovimiento(player.getX()+pixel, player.getY()));
        if (res==1) return;
        this.player.Mover(Movimiento.DERECHA);
        if (res==3) {
            player.Alimentarse();
            gc = img.getGraphicsContext2D();
            gc.clearRect(player.getX(), player.getY(), pixel, pixel);
        }
    }

    public void PintPiso() {
        Image Fondo;
        if (tipo_laberinto ==LabT.RUINAS) {
            Fondo=new Image(new File("media/pisoLuz.png").toURI().toString());
        }else if (tipo_laberinto==LabT.TUNEL) {
            Fondo=new Image(new File("media/pisoOscuridad.png").toURI().toString());
        }else {
            Fondo=new Image(new File("media/piso.png").toURI().toString());
        }
        GraphicsContext grc = fondo.getGraphicsContext2D();
        grc.drawImage(Fondo,0, 0);
    }

    public void Reset() {
        if (this.player.Final()) {
            gc = player_c.getGraphicsContext2D();
            NuevoLaberinto();
            player.Reset(gc);
            activo = player.Salud();
        }
    }
    public void  Interaccion() {
        int xx = caminadores.X;
        int yy = caminadores.Y;
        int xxP = deambulante.X;
        int yyP = deambulante.Y;
        int U_x= player.getX()/pixel;
        int U_y= player.getY()/pixel;
        if (xx==U_x && yy==U_y){
            player.Alimentarse();
            System.out.println("Se encontro con el caminador");
        }
        if (xxP==U_x && yyP==U_y){
            player.ConsumirEnergia();
            System.out.println("Se encontro con el Deambulante");
        }
    }

    static class Tupla<T, T1> {
        T primero;
        T1 segundo;

        public Tupla(T first, T1 second) {
            this.primero = first;
            this.segundo = second;
        }
    }
}

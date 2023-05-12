package fes.aragon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


import fes.aragon.controller.InicioController;
import fes.aragon.modulo.*;
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
        			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Inicio.fxml"));
            Pane root =loader.load();
            InicioController controller = loader.getController();

            Scene scene = new Scene(root);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    controller.VerificarActividad();
                    controller.Interaccion();
                    switch (event.getCode()) {
                        case UP :
                        case W :
                            controller.Arriba();
                            break;
                        case DOWN:
                        case S :
                            controller.Abajo();
                            break;
                        case LEFT:
                        case A:
                            controller.Izquierda();
                            break;
                        case RIGHT:
                        case D :
                            controller.Derecha();
                            break;
                        default:
                            break;
                    }
                    controller.Reset();
                }
            });
            primaryStage.setTitle("Fauno");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

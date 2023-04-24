package fes.aragon;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

import java.lang.ModuleLayer.Controller;
import java.util.HashSet;

import fes.aragon.controller.InicioController;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Text text = new Text("Hola");      
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Inicio.fxml"));
			Pane root = loader.load();
            root.getChildren().add(text);

            InicioController controller = loader.getController();

			Scene scene = new Scene(root);
			//InicioController controller = loader.getController();
			scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
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
					case D :
						controller.Izquierda();
						break;
					case RIGHT:
					case A :
						controller.Derecha();
						break;
					default:
						break;
					}
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
		launch(args);
	}
}

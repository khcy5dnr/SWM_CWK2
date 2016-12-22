package com.neet.DiamondHunter.MainApp;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class LauncherMain extends Application {
	public void start(Stage primaryStage) {
		//launch the main launcher for the game and map viewer
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LauncherMenu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("LauncherMenu.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Diamond Hunter");
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            public void handle(WindowEvent t) {
	                Platform.exit();
	                System.exit(0);
	            }
	        });
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

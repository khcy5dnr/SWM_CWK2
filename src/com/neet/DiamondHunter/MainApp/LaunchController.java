package com.neet.DiamondHunter.MainApp;

import javax.swing.JFrame;

import com.neet.DiamondHunter.Main.GamePanel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LaunchController {
	
	Stage primaryStage = new Stage();
	
	private static JFrame window;
	
	@FXML
	private Button btnPlay,btnExit;
	
	@FXML
	private Button btnMapViewer = new Button();
	
	//launch the Diamond Hunter game
	public void gameDH(){
		window = new JFrame("Diamond Hunter");
		
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.setUndecorated(true);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
	
	public static JFrame getWindow() {
		return window;
	}
	
	//launch the Map Viewer for Diamond Hunter game
	@FXML
	public void mapViewerLaunch() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("MapViewerWindow.fxml"));
		primaryStage.setTitle(" Diamond Hunter Map Viewer ");
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("MapViewerWindow.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		Image icon = new Image(getClass().getResourceAsStream("iconDiamond.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.show();
	}

	@FXML
	public void exit(){
		System.exit(0);
	}

}

package com.neet.DiamondHunter.MainApp;

import javax.swing.JFrame;

import com.neet.DiamondHunter.Main.GamePanel;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LaunchController {
	
	Stage primaryStage = new Stage();
	
	@FXML
	private Button btnPlay;
	
	public void gameDH(){
		JFrame window = new JFrame("Diamond Hunter");
		
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@FXML
	public void mapViewerLaunch() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("MapViewerWindow.fxml"));
		primaryStage.setTitle(" Diamond Hunter Map Viewer ");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

package com.neet.DiamondHunter.MainApp;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MapViewerController implements Initializable{
	
	Stage primaryStage = new Stage();
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Canvas mapCanvas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GraphicsContext gc = mapCanvas.getGraphicsContext2D();
        TileMap test = new TileMap(16);
        test.loadMap("/Maps/testmap.map");
        test.draw_Image(gc);
		
	}
	
    private void drawShapes(GraphicsContext gc) {
    	gc.beginPath();
        gc.moveTo(30.5, 30.5);
        gc.lineTo(150.5, 30.5);
        gc.lineTo(150.5, 150.5);
        gc.lineTo(30.5, 30.5);
        gc.stroke();
	}

	@FXML
    public void exit()throws Exception{
        Scene scene = btnBack.getScene();
        Stage currentScene = (Stage)scene.getWindow();
        currentScene.hide();
    }

	@FXML
	public void save() throws Exception{    
    	Stage stage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("SavedNotification.fxml"));
    	//stage.initStyle(StageStyle.UNDECORATED);
    	stage.setTitle(" Save Complete ");
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("SavedNotification.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
    }

}

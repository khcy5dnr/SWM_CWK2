package com.neet.DiamondHunter.MainApp;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	private Canvas mapCanvas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GraphicsContext gc = mapCanvas.getGraphicsContext2D();
        drawShapes(gc);
		
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


}

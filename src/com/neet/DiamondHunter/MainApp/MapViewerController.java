package com.neet.DiamondHunter.MainApp;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.neet.DiamondHunter.Manager.Content;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class MapViewerController implements Initializable{
	
	Stage primaryStage = new Stage();
	private Boolean boat =false,axe=false;
	
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
		test.draw_Item(gc);
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
    	final Stage stage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("SavedNotification.fxml"));
    	stage.initStyle(StageStyle.UNDECORATED);
    	stage.setTitle(" Save Complete ");
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("SavedNotification.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
		// saved notification disappears after 2 seconds
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
		    new EventHandler<ActionEvent>() {

			@Override
		    public void handle(ActionEvent event) {
		            stage.hide();
		        }
		    }));
		timeline.play();
    }

	@FXML
	public void mouseClicked(MouseEvent e)throws IOException {
		int x=(int)e.getX() /16;
		int y=(int)e.getY()/16;
		System.out.println(x+","+y);//these co-ords are relative to the component

		//TileMap mm = new TileMap(16);
		GraphicsContext gc = mapCanvas.getGraphicsContext2D();
		//draw boat
		BufferedImage sprite;

		if (boat && !axe){
			sprite = Content.ITEMS[1][0];
			WritableImage boat = SwingFXUtils.toFXImage(sprite,null);
			gc.drawImage(boat,16*x,16*y,16,16);
		}else if (axe && !boat){
			sprite = Content.ITEMS[1][1];
			WritableImage axe = SwingFXUtils.toFXImage(sprite,null);
			gc.drawImage(axe,16*x,16*y,16,16);
		}

	}

	@FXML
	public void setItemBoat(){
		if (!boat && !axe){
			boat=true;
			axe=false;
		}else if (!boat && axe){
			boat=true;
			axe=false;
		}else if (boat && axe){
			boat=false;
			axe=false;
		}
	}

	@FXML
	public void setItemAxe(){
		if (!boat && !axe){
			axe=true;
			boat=false;
		}else if (boat && !axe){
			boat=false;
			axe=true;
		}else if (boat && axe){
			boat=false;
			axe=false;
		}
	}

}

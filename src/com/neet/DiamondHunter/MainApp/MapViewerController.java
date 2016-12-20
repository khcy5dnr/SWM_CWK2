package com.neet.DiamondHunter.MainApp;


import java.awt.image.BufferedImage;
import java.io.*;
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
	public int arr[] = new int[4];
	//private int tempArr[] = new int[4];

	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Canvas mapCanvas;
	
	public TileMap test = new TileMap(16);
	
	private boolean flagAxe = true;
	private boolean flagBoat = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GraphicsContext gc = mapCanvas.getGraphicsContext2D();
		readDB();
		test.readDB();
        test.loadMap("/Maps/testmap.map");
        test.draw_Image(gc);
		test.draw_Item(gc, "boat");
		test.draw_Item(gc, "axe");

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
		
		//save coordinates of boat and axe position
		String filename = "Resources/Maps/itemDB.txt";
		BufferedWriter bw = null;
		
		try{
			bw = new BufferedWriter(new FileWriter(filename));
			for(int i=0; i < 4; i++){
				bw.write(Integer.toString(arr[i]));
				bw.newLine();	
			}
			bw.flush();
			bw.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
    }

	@FXML
	public void mouseClicked(MouseEvent e)throws IOException {
		int x=(int)e.getX() / 16;
		int y=(int)e.getY() / 16;
		System.out.println(x+","+y);//console print //testing

		GraphicsContext gc = mapCanvas.getGraphicsContext2D();

		BufferedImage sprite;
		//case boat chosen
		if (boat && !axe){
			test.draw_Image(gc);
			sprite = Content.ITEMS[1][0];
			WritableImage boat = SwingFXUtils.toFXImage(sprite,null);

			gc.drawImage(boat,16*x,16*y,16,16);
			//if (arr[3]!=x && arr[2]!=y){
			test.draw_Item(gc, "axe",arr[0],arr[1]);
			arr[3]=x;
			arr[2]=y;
			System.out.println(arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]);

			//}
		}
		//case axe chosen
		else if (axe && !boat){
			test.draw_Image(gc);
			sprite = Content.ITEMS[1][1];
			WritableImage axe = SwingFXUtils.toFXImage(sprite,null);

			gc.drawImage(axe,16*x,16*y,16,16);
			//if (arr[1]!=x && arr[0]!=y){
			test.draw_Item(gc, "boat",arr[2],arr[3]);
			arr[1]=x;
			arr[0]=y;
			System.out.println(arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]);

			//}
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

	public void readDB() {
		String line = null;

		//creating file as obj to be read
		File file = new File("Resources/Maps/itemDB.txt");

		//check file existence
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} 
		catch (FileNotFoundException e) {
			System.out.println("File doesn't exists");
			e.printStackTrace();
		}

		//new buffered reader; per lines
		BufferedReader br = new BufferedReader(fr);
		int i = 0;
		try {
			while ((line = br.readLine()) != null) {
				arr[i] = Integer.parseInt(line);
				i++;
			}
			br.close();
		}catch (IOException e){
			e.printStackTrace();
		}

	}
	
	

}

package com.neet.DiamondHunter.MainApp;

import java.awt.image.BufferedImage;
import java.io.*;

import com.neet.DiamondHunter.Manager.Content;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class TileMap {

	private int arr[] = new int[4];
	private BufferedImage sprite;
	
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private Image tile;
	
	public TileMap(int size){
		this.tileSize = size;
	}
	
	public void loadMap(String path){
		try {			
			InputStream in = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			
			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw_Image(GraphicsContext gc){
		tile = new Image("/Tilesets/testtileset.gif");
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				int originalPoint = map[row][col];
				int yOSH = 0;
				
				if(originalPoint >= (tile.getWidth()/tileSize)){
					yOSH++;
					originalPoint = (int) (originalPoint - tile.getWidth()/tileSize);
				}
			
				gc.drawImage(tile, originalPoint*tileSize, yOSH*tileSize, tileSize, tileSize, col*tileSize, row*tileSize, tileSize, tileSize);
			}
		}
	}

	public void draw_Item(GraphicsContext gc, String itemName){
		int width = 16, height = 16;
		readDB();
		int xB = arr[1], yB = arr[0];
		int xA = arr[3], yA = arr[2];

		//draw boat
		if(itemName == "boat"){
			sprite = Content.ITEMS[1][0];
			WritableImage boat = SwingFXUtils.toFXImage(sprite,null);
			gc.drawImage(boat,xB*width,yB*height,width,height);
		}
		//draw axe
		else if(itemName == "axe"){
			sprite = Content.ITEMS[1][1];
			WritableImage axe = SwingFXUtils.toFXImage(sprite,null);
			gc.drawImage(axe,xA*width,yA*height,width,height);
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
		} catch (FileNotFoundException e) {
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
		}catch (IOException e){
			e.printStackTrace();
		}

	}

}


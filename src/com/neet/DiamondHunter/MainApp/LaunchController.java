package com.neet.DiamondHunter.MainApp;

import javax.swing.JFrame;

import com.neet.DiamondHunter.Main.GamePanel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LaunchController {
	
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
}

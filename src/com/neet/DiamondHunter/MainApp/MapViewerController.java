package com.neet.DiamondHunter.MainApp;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MapViewerController {

@FXML
private Button btnBack;

    @FXML
    public void exit()throws Exception{
        Scene scene = btnBack.getScene();
        Stage currentScene = (Stage)scene.getWindow();
        currentScene.hide();
    }

}

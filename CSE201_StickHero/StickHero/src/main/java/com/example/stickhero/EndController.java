package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.stickhero.MainController.bestscore;

public class EndController implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Character character;
    @FXML
    private Label score;
    @FXML
    private Label bestScore;
    public static boolean resetCharacter=false;
    public void switchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //resetCharacter();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void setScores(){
        Image image=new Image("stick_hero_icon-removebg-preview.png");
        character=Character.getInstance(image);
        score.setText(Integer.toString(character.getScore()));
        bestScore.setText(Integer.toString(bestscore));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setScores();
    }
}

package com.example.stickhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

public class GameScreen extends Application {
    public static String cwd_music=String.valueOf(Paths.get("").toAbsolutePath())+"/StickHero/src/main/resources/music.mp3";
    public static MediaPlayer mediaPlayer;
    public static boolean isPlaying;
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Thread t=new Thread(this::musicPlayer);
            t.start();
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("start.fxml")));
            Parent root = fxmlLoader.load();
            StartController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);

            // Set the scene in the controller
            controller.setScene(scene);

            stage.setScene(scene);
            stage.setTitle("StickHero");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void musicPlayer(){
        isPlaying=true;
        Media media=new Media(new File(cwd_music).toURI().toString());
        mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }
}

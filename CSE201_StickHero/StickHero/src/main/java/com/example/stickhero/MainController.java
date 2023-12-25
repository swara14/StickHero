package com.example.stickhero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.media.Media;

import static java.lang.Math.abs;

public class MainController implements Initializable {
    @FXML
    private Label score_textfield;
    @FXML
    private Label cherries_textfield;
    @FXML
    private Label best_score_textfield;
    public static int bestscore;
    public static int cherries;
    @FXML
    private Character character;
    @FXML
    private Stick stick;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Platform platform1;
    @FXML
    private Platform platform2;
    private final double platformHeight=300;
    private final double platformY=500;
    private final double characterY=500;
    private double rectangle1Width;
    private double spacing;
    private double rectangle2Width;
    private boolean isMousePressed = false;
    private Timeline elongateTimeline;
    private boolean moveFlag=false;
    private double intialStickX;
    private AnimationTimer gameLoop;
    private double originalStickEndY;
    private boolean hasFallen=false;
    private boolean isSpace;
    private final double cherryY=495;
    private Cherry cherry;
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("intializing");
//        if(EndController.resetCharacter)
//            resetCharacter();
        stage=StartController.setStage;
        initializePlatforms();
        renderCharacter();
        renderStick();
        originalStickEndY = stick.getEndY();
        bestscore=SaveScoreAndCherries.retrieveBestScore();
        cherries=SaveScoreAndCherries.retrieveCherries();
    }
    private void initializePlatforms() {
        System.out.println("initializing rectangles");
        double screenWidth = rootPane.getWidth();
        Random random = new Random();

        rectangle1Width = random.nextDouble() * 100 + 50;
        spacing = random.nextDouble() *300 + 30;
        rectangle2Width = random.nextDouble() * 100 + 50;

        platform1 = new Platform();
        platform1.setHeight(platformHeight);
        platform1.setX(100);
        platform1.setFill(Color.BLACK);
        platform1.setY(platformY);
        platform1.setLayoutX(0);
        platform1.setLayoutY(0);
        platform1.setWidth(rectangle1Width);

        platform2= new Platform();
        platform2.setFill(Color.BLACK);
        platform2.setHeight(platformHeight);
        platform2.setY(platformY);
        platform2.setLayoutX(0);
        platform2.setLayoutX(0);
        platform2.setX(100+rectangle1Width+spacing);
        platform2.setWidth(rectangle2Width);

        rootPane.getChildren().add(platform1);
        rootPane.getChildren().add(platform2);
    }
    private void renderCharacter(){
        Image image=new Image("stick_hero_icon-removebg-preview.png");
        character=Character.getInstance(image);
        //resetCharacter();
        character.setX(100+rectangle1Width-60);
        character.setY(characterY-50);
        character.setFitWidth(60);
        character.setFitHeight(50);
        character.setLayoutX(0);
        character.setLayoutY(0);
        character.setScore(0);
        character.setCherries(0);
        rootPane.getChildren().add(character);
    }
    public void renderStick(){
        stick= Stick.getInstance();
        intialStickX=100+rectangle1Width-5;
        stick.setStartX(intialStickX);
        stick.setStartY(platformY);
        stick.setEndX(intialStickX);
        stick.setEndY(platformY);
        stick.setStrokeWidth(5);
        stick.setStroke(Color.BLACK);
        stick.setOpacity(1);
        rootPane.getChildren().add(stick);
    }
    @FXML
    public void handleMousePressed(MouseEvent event) {
        isMousePressed = true;
        startElongation();
    }
    @FXML
    public void handleMouseReleased(MouseEvent event) throws IOException {
        isMousePressed = false;
        stopElongation();
        rotateStick();
        moveCharacter();
        //update();
    }
    private void startElongation() {
            elongateTimeline = new Timeline(
                    new KeyFrame(Duration.millis(10), e -> {
                        if (isMousePressed) {
                            stick.setEndY(stick.getEndY() - 1);
                        }
                    })
            );
            elongateTimeline.setCycleCount(Timeline.INDEFINITE);
            elongateTimeline.play();

    }
    public void stopElongation(){
        if (elongateTimeline != null) {
            elongateTimeline.stop();
        }
    }
    private void rotateStick() {
            double pivotX = stick.getStartX();
            double pivotY = stick.getStartY();

            Rotate rotate = new Rotate(90, pivotX, pivotY);
            stick.getTransforms().add(rotate);
            System.out.println("rotated");
            moveFlag = true;

    }
    public void resetCharacter(){
        character.getTransforms().clear();
        character.setX(100+rectangle1Width-60);
        System.out.println(character.getX());
        character.setY(characterY-50);
        System.out.println(character.getY());
        character.setScore(0);
        character.setCherries(0);
        System.out.println(character.getCherries());
        System.out.println(character.getScore());
    }
    private void resetStick(){
        stick.getTransforms().clear();
        intialStickX=100+rectangle1Width-5;
        stick.setStartX(intialStickX);
        stick.setStartY(platformY);
        stick.setEndX(intialStickX);
        stick.setEndY(platformY);
    }
    private void moveCharacter() throws IOException {
        System.out.println(stick.getEndX());
        System.out.println(stick.getStartX());
        System.out.println(stick.getEndY());
        System.out.println(stick.getStartY());
        if(moveFlag) {
            double stickLength = stick.getLength();
            double lowerBound = spacing;
            double upperBound = spacing + rectangle2Width;
            if (stickLength >= lowerBound && stickLength <= upperBound) {
                TranslateTransition transition = new TranslateTransition(Duration.seconds(2),character);
                transition.setToX(spacing+platform2.getWidth()); // Set the destination X coordinate
                transition.setOnFinished(event->resetStick());
                transition.setOnFinished(event->newPlatform());
                transition.play();
                character.setScore(character.getScore()+1);
                score_textfield.setText(Integer.toString(character.getScore()));
                if (character.getScore() > bestscore)
                    bestscore = character.getScore();

            } else {
                TranslateTransition transition = new TranslateTransition(Duration.seconds(2),character);
                transition.setToX(stickLength);// Set the destination X coordinate
                TranslateTransition transition2 = new TranslateTransition(Duration.seconds(2),character);
                transition2.setToY(character.getY()+400);
                transition2.setOnFinished(event-> {
                    resetStick();
                    SaveScoreAndCherries.saveBestScore();
                    SaveScoreAndCherries.saveCherries();
                    try {
                        switchToEnd((Stage) transition2.getNode().getScene().getWindow());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
//                transition2.setOnFinished(event -> {
//                    try {
//                        hasFallen=true;
//                        if(hasFallen)
//                            switchToEnd(event);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
                transition.setOnFinished(event -> transition2.play());
                transition.play();
                //switchToEnd(hasFallen);
                //stick.getTransforms().clear();
                //switchToEnd;
            }
        }
    }
    public void newPlatform(){
        Platform newPlatform=new Platform();
        TranslateTransition transition=new TranslateTransition(Duration.seconds(2),platform1);
        transition.setToX(-(1000));
        TranslateTransition transition1=new TranslateTransition(Duration.seconds(2),character);
        transition1.setByX(-(rectangle1Width+spacing));
//        System.out.println("spacing "+spacing);
//        System.out.println("width "+rectangle2Width);
//        System.out.println("rect+width "+(rectangle2Width+spacing));
        TranslateTransition transition2=new TranslateTransition(Duration.seconds(2),platform2);
        transition2.setToX(-(rectangle1Width+spacing));
//        System.out.println("rect+width "+rectangle2Width+spacing);
        Random random = new Random();
        rectangle1Width = rectangle2Width;
        platform1 = platform2;
        spacing = random.nextDouble() * 300 + 30;
        rectangle2Width = random.nextDouble() * 100 + 50;
        resetStick();
        transition.play();
        transition1.play();
        transition2.play();
        transition2.setOnFinished(event-> {
            newPlatform.setX(100 + rectangle1Width + spacing);
            newPlatform.setY(platformY);
            newPlatform.setHeight(platformHeight);
            newPlatform.setWidth(rectangle2Width);
            newPlatform.setFill(Color.BLACK);
            rootPane.getChildren().add(newPlatform);
            platform2 = newPlatform;
        });
    }
//    public void update() throws IOException {
//        stick.getTransforms().clear();
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
//        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

//    private void setupGameLoop() {
//        gameLoop = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                try {
//                    update();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        };
//        gameLoop.start();
//    }
    public static boolean checkInBounds(double length,double lowerBound,double upperBound){
        return length<=upperBound &&length>=lowerBound;
    }
    public static boolean checkHeightsEquality(double platform1Height,double platform2Height){
        return (platform1Height == platform2Height);
    }
    public void switchToEnd(Stage stage) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("end.fxml")));
        //EndController endController (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        //resetCharacter();
        stage.show();
    }
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            isSpace=true;
        }
    }

    @FXML
    private void handleKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            isSpace=false;
        }
    }
    public void renderCherry(){
        Image image=new Image("cherry-game-removebg-preview.png");
        Cherry chery=new Cherry(image);
        cherry.setY(cherryY);
        Random random=new Random();
        chery.setLayoutX(0);
        cherry.setLayoutY(0);
        double pos=random.nextDouble()*(spacing)+10;
        cherry.setX(pos+10);
        rootPane.getChildren().add(cherry);
    }

//    public void switchToEnd() throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("night_end.fxml")));
//        stage = (Stage)((Node)rootPane).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//    public void switchToEnd(){
//        try {
//            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("end.fxml")));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
}

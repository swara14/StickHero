package com.example.stickhero;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character extends ImageView implements Collect {
    private int cherries;
    private int score;
    /* SINGLETON DESIGN PATTERN */
    private static Character character=null;
    private Character(Image image){
        super(image);
    }
    public static Character getInstance(Image image){
        if(character==null)
            character=new Character(image);
        return character;
    }
    public void setCherries(int cherries) {
        this.cherries = cherries;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getCherries() {
        return cherries;
    }
    public int getScore() {
        return score;
    }
    public void collectCherry(){
        this.cherries+=1;
    }
}
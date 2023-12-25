package com.example.stickhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cherry extends ImageView implements Collect{
    public Cherry(Image image){
        super(image);
    }
    public void collectCherry(){
        this.setOpacity(0);
    }
}
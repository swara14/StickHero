package com.example.stickhero;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Platform extends Rectangle{
//    private Pane root;
//    public static final double PLATFORM_HEIGHT = 250;
//    public static double platform_distance;
    /* COMPOSITE DESIGN PATTERN*/
    private ArrayList<Platform> platforms=new ArrayList<>();
    public Platform() {
        super();
        platforms.add(this);
    }
}
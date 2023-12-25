package com.example.stickhero;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import static java.lang.Math.abs;

public class Stick extends Line {
    private double length;
    private String colour="BLACK";
    private static final double SPEED = 2.0;
    private static final double MAX_LENGTH = 500.0;
    private static final double ROTATION_ANGLE = 90.0;
    private Rotate rotate;
    /* SINGLETON DESIGN PATTERN */
    private static Stick stick=null;
    public static Stick getInstance(){
        if(stick==null)
            stick=new Stick();
        return stick;
    }
    private Stick(){
        super();
    }

    public double getLength() {
        double length=abs(Math.pow(Math.pow(this.getEndX()-this.getStartX(),2)+Math.pow(this.getEndY()-this.getStartY(),2),0.5));
        return length;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setLength(double length) {
        this.length = this.getLength();
    }

}

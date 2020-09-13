package com.company.Collection;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private float x; //Значение поля должно быть больше -554
    private Integer y; //Поле не может быть null

    public float getX() {
        return x;
    }

    public void setX(float x) {
        if ( x > -554){
            this.x = x;
        } else System.out.println("Координата X <= -554");
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
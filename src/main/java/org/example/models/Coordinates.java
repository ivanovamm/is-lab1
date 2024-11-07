package org.example.models;

import jakarta.persistence.*;


@Embeddable
public class Coordinates {

    @Column(nullable = false)
    private Long x;

    @Column(nullable = false)
    private int y;

    public Coordinates() {
    }

    public Coordinates(Long x, int y) {
        this.x = x;
        this.y = y;
    }


    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

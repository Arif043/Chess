package com.github.arif043.chess.entity;

import jdk.internal.util.xml.impl.Pair;

/**
 * @author Arif Ertugrul
 * @date 21.06.24
 */
public abstract class Figure {

    private int xPosition, yPosition;

    public Figure(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public abstract void validateMoves();

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
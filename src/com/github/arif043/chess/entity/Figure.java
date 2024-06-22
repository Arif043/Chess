package com.github.arif043.chess.entity;


/**
 * @author Arif Ertugrul
 * @date 21.06.24
 */
public abstract class Figure {

    public static final Object IMG = null;
    private boolean isBlack;
    private int xPosition, yPosition;

    public Figure(int xPosition, int yPosition, boolean isBlack) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isBlack = isBlack;
    }

    public abstract void validateMoves(Figure[][] board);

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

    public boolean isBlack() {
        return isBlack;
    }
}
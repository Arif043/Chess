package com.github.arif043.chess.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class King extends Figure {

    public static final BufferedImage WHITE_IMG, BLACK_IMG;
    private boolean moved, castlingAble = true;

    static {
        WHITE_IMG = getScaledImage(0, 0, 320, 320, 80, 80);
        BLACK_IMG = getScaledImage(0, 320, 320, 320, 80, 80);
    }

    public King(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public ArrayList<Position> validateMoves(Figure[][] board) {
        var res = new ArrayList<Position>();
        for (int stepY = -1; stepY < 2; stepY++)
            for (int stepX = -1; stepX < 2; stepX++) {
                if (getxPosition() + stepX >= 0 && getxPosition() + stepX < 8 && getyPosition() + stepY >= 0 &&
                        getyPosition() + stepY < 8 && (stepX != 0 || stepY != 0) && (board[getyPosition() + stepY][getxPosition() + stepX] == null ||
                        board[getyPosition() + stepY][getxPosition() + stepX].isBlack() != isBlack())) {
                    res.add(new Position(getxPosition() + stepX, getyPosition() + stepY));
                }
            }

        return res;
    }

    @Override
    public void setxPosition(int xPosition) {
        super.setxPosition(xPosition);
        moved = true;
    }

    @Override
    public void setyPosition(int yPosition) {
        super.setyPosition(yPosition);
        moved = true;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean isCastlingAble() {
        return castlingAble;
    }

    public void setCastlingAble(boolean castlingAble) {
        this.castlingAble = castlingAble;
    }
}
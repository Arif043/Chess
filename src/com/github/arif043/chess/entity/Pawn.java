package com.github.arif043.chess.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Pawn extends Figure {

    public static final BufferedImage WHITE_IMG, BLACK_IMG;

    static {
        WHITE_IMG = getScaledImage(1600, 0, 320, 320, 80, 80);
        BLACK_IMG = getScaledImage(1600, 320, 320, 320, 80, 80);
    }

    private boolean moved;

    public Pawn(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public ArrayList<Position> validateMoves(Figure[][] figure) {
        var res = new ArrayList<Position>();
        if (!moved && normalizedYPosition() == 1 && figure[getxPosition()][isBlack() ? 4 : 3] == null)
            res.add(new Position(getxPosition(), isBlack() ? 3 : 4));

        if (figure[getxPosition()][getForwardYPos()] == null)
            res.add(new Position(getxPosition(), getForwardYPos()));

        return res;
    }

    private int normalizedYPosition() {
        return isBlack() ? getyPosition() : 7 - getyPosition();
    }

    private int getForwardYPos() {
        return getyPosition() + (isBlack() ? 1 : -1);
    }
}
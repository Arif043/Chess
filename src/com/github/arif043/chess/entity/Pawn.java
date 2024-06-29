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
        if (!moved && figure[getForwardYPos()][getxPosition()] == null && figure[isBlack() ? 3 : 4][getxPosition()] == null)
            res.add(new Position(getxPosition(), isBlack() ? 3 : 4));

        if (figure[getForwardYPos()][getxPosition()] == null)
            res.add(new Position(getxPosition(), getForwardYPos()));

        if (getxPosition() - 1 >= 0 && figure[getForwardYPos()][getxPosition() - 1] != null && figure[getForwardYPos()][getxPosition() - 1].isBlack() != isBlack())
            res.add(new Position(getxPosition() - 1, getForwardYPos()));
        if (getxPosition() + 1 < 8 && figure[getForwardYPos()][getxPosition() + 1] != null && figure[getForwardYPos()][getxPosition() + 1].isBlack() != isBlack())
            res.add(new Position(getxPosition() + 1, getForwardYPos()));

        return res;
    }

    private int getForwardYPos() {
        return getyPosition() + (isBlack() ? 1 : -1);
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
}
package com.github.arif043.chess.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Bishop extends Figure {

    public static final BufferedImage WHITE_IMG, BLACK_IMG;

    static {
        WHITE_IMG = getScaledImage(640, 0, 320, 320, 80, 80);
        BLACK_IMG = getScaledImage(640, 320, 320, 320, 80, 80);
    }
    public Bishop(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public ArrayList<Position> validateMoves(Figure[][] figure) {
        return null;
    }
}
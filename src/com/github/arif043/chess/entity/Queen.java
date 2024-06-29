package com.github.arif043.chess.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Queen extends Figure {

    public static final BufferedImage WHITE_IMG, BLACK_IMG;
    private Figure innerRook, innerBishop;

    static {
        WHITE_IMG = getScaledImage(320, 0, 320, 320, 80, 80);
        BLACK_IMG = getScaledImage(320, 320, 320, 320, 80, 80);
    }

    public Queen(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
        innerRook = new Rook(xPosition, yPosition, isBlack);
        innerBishop = new Bishop(xPosition, yPosition, isBlack);
    }

    @Override
    public ArrayList<Position> validateMoves(Figure[][] board) {
        var res = new ArrayList<Position>(innerRook.validateMoves(board));
        res.addAll(innerBishop.validateMoves(board));
        return res;
    }

    @Override
    public void setxPosition(int xPosition) {
        super.setxPosition(xPosition);
        innerBishop.setxPosition(xPosition);
        innerRook.setxPosition(xPosition);
    }

    @Override
    public void setyPosition(int yPosition) {
        super.setyPosition(yPosition);
        innerBishop.setyPosition(yPosition);
        innerRook.setyPosition(yPosition);
    }
}
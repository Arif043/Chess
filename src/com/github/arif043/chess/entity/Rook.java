package com.github.arif043.chess.entity;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Rook extends Figure {

    public Rook(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public void validateMoves(Figure[][] figure) {

    }
}
package com.github.arif043.chess.entity;

import java.util.ArrayList;

/**
 * @author Arif Ertugrul
 * @date 26.06.24
 */
public class NullFigure extends Figure {

    public static final NullFigure NULL = new NullFigure(0, 0);

    private NullFigure(int xPosition, int yPosition) {
        super(xPosition, yPosition, true);
    }

    @Override
    public ArrayList<Position> validateMoves(Figure[][] board) {
        return new ArrayList<>();
    }
}
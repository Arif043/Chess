package com.github.arif043.chess.entity;

import java.util.ArrayList;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Pawn extends Figure {

    private boolean moved;

    public Pawn(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public void validateMoves(Figure[][] figure) {
        var res = new ArrayList<Position>();
        if (!moved && normalizedYPostion() == 1 && figure[getxPosition()][isBlack() ? 4 : 3] == null)
            res.add(new Position(getxPosition(), isBlack() ? 4 : 3));

        if (figure[getxPosition()][getForwardYPos()] == null)
            res.add(new Position(getxPosition(), getForwardYPos()));

    }

    private int normalizedYPostion() {
        return isBlack() ? 7 - getyPosition() : getyPosition();
    }

    private int getForwardYPos() {
        return getyPosition() + (isBlack() ? -1 : 1);
    }
}
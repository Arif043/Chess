package com.github.arif043.chess.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Knight extends Figure {

    public static final BufferedImage WHITE_IMG, BLACK_IMG;

    static {
        WHITE_IMG = getScaledImage(960, 0, 320, 320, 80, 80);
        BLACK_IMG = getScaledImage(960, 320, 320, 320, 80, 80);
    }

    public Knight(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public ArrayList<Position> validateMoves(Figure[][] figure) {
        var res = new ArrayList<Position>();
        if (getxPosition() - 2 >= 0)
            res.addAll(getHorizontalValidatePlaces(figure,-2));
        if (getxPosition() + 2 <= 7)
            res.addAll(getHorizontalValidatePlaces(figure, 2));
        if (getyPosition() - 2 >= 0)
            res.addAll(getVerticalValidatePlaces(figure, -2));
        if (getyPosition() + 2 <= 7)
            res.addAll(getVerticalValidatePlaces(figure, 2));
        return res;

    }

    private ArrayList<Position> getVerticalValidatePlaces(Figure[][] figures, int stepY) {
        var res = new ArrayList<Position>();
        if (getxPosition() - 1 >= 0 && (figures[getyPosition() + stepY][getxPosition() - 1] == null ||
                figures[getyPosition() + stepY][getxPosition() - 1].isBlack() != isBlack()))
            res.add(new Position(getxPosition() - 1, getyPosition() + stepY));
        if (getxPosition() + 1 <= 7 && (figures[getyPosition() + stepY][getxPosition() + 1] == null ||
                figures[getyPosition() + stepY][getxPosition() + 1].isBlack() != isBlack()))
            res.add(new Position(getxPosition() + 1, getyPosition() + stepY));
        return res;
    }

    private ArrayList<Position> getHorizontalValidatePlaces(Figure[][] figures, int stepX) {
        var res = new ArrayList<Position>();
        if (getyPosition() - 1 >= 0 && (figures[getyPosition() - 1][getxPosition() + stepX] == null ||
                figures[getyPosition() - 1][getxPosition() + stepX].isBlack() != isBlack()))
            res.add(new Position(getxPosition() + stepX, getyPosition() - 1));
        if (getyPosition() + 1 <= 7 && (figures[getyPosition() + 1][getxPosition() + stepX] == null ||
                figures[getyPosition() + 1][getxPosition() + stepX].isBlack() != isBlack()))
            res.add(new Position(getxPosition() + stepX, getyPosition() + 1));
        return res;
    }
}
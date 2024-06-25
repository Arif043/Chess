package com.github.arif043.chess.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
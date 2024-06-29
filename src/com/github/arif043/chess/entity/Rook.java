package com.github.arif043.chess.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Rook extends Figure {

    public static final BufferedImage WHITE_IMG, BLACK_IMG;
    private boolean continueTop, continueRight, continueBottom, continueLeft;

    static {
        WHITE_IMG = getScaledImage(1280, 0, 320, 320, 80, 80);
        BLACK_IMG = getScaledImage(1280, 320, 320, 320, 80, 80);
    }
    public Rook(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public ArrayList<Position> validateMoves(Figure[][] board) {
        continueTop = continueRight = continueBottom = continueLeft = true;
        var res = new ArrayList<Position>();
        for (int step = 1; step < 8; step++) {
            if (continueBottom) {
                var pos = getVerticalValidatePlaces(board, cord -> cord < 8, step);
                if (pos != null) res.add(pos);
            }
            if (continueLeft) {
                var pos = getHorizontalValidatePlaces(board, cord -> cord >= 0, -step);
                if (pos != null) res.add(pos);
            }
            if (continueTop) {
                var pos = getVerticalValidatePlaces(board, cord -> cord >= 0, -step) ;
                if (pos != null) res.add(pos);
            }
            if (continueRight) {
                var pos = getHorizontalValidatePlaces(board, cord -> cord < 8, step);
                if (pos != null) res.add(pos);
            }
        }
        return res;
    }

    private Position getVerticalValidatePlaces(Figure[][] board, Predicate<Integer> inequality, int stepY) {
        Position res = null;
        if (inequality.test(getyPosition() + stepY)) {
            if (board[getyPosition() + stepY][getxPosition()] == null ||
                    board[getyPosition() + stepY][getxPosition()].isBlack() != isBlack()) {
                res = new Position(getxPosition(), getyPosition() + stepY);
            }
            if (board[getyPosition() + stepY][getxPosition()] != null) {
                if (stepY > 0)
                    continueBottom = false;
                else
                    continueTop = false;
            }
        }
        return res;
    }

    private Position getHorizontalValidatePlaces(Figure[][] board, Predicate<Integer> inequality, int stepX) {
        Position res = null;
        if (inequality.test(getxPosition() + stepX)) {
            if (board[getyPosition()][getxPosition() + stepX] == null ||
                    board[getyPosition()][getxPosition() + stepX].isBlack() != isBlack()) {
                res = new Position(getxPosition() + stepX, getyPosition());
            }
            if (board[getyPosition()][getxPosition() + stepX] != null) {
                if (stepX > 0)
                    continueRight = false;
                else
                    continueLeft = false;
            }
        }
        return res;
    }
}
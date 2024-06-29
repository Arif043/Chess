package com.github.arif043.chess.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Rook extends Figure {

    public static final BufferedImage WHITE_IMG, BLACK_IMG;
    private boolean continueTop, continueRight, continueBottom, continueLeft, moved;

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
        var tempRes = new LinkedList<Position>();
        for (int step = 1; step < 8; step++) {
            if (continueBottom)
                tempRes.add(checkVerticalValidPlace(board, cord -> cord < 8, step));
            if (continueLeft)
                tempRes.add(checkHorizontalValidPlace(board, cord -> cord >= 0, -step));
            if (continueTop)
                tempRes.add(checkVerticalValidPlace(board, cord -> cord >= 0, -step));
            if (continueRight)
                tempRes.add(checkHorizontalValidPlace(board, cord -> cord < 8, step));
        }
        for (Position pos : tempRes)
            if (pos != null) res.add(pos);
        return res;
    }

    private Position checkVerticalValidPlace(Figure[][] board, Predicate<Integer> inequality, int stepY) {
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

    private Position checkHorizontalValidPlace(Figure[][] board, Predicate<Integer> inequality, int stepX) {
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

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}
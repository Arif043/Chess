package com.github.arif043.chess.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Bishop extends Figure {

    public static final BufferedImage WHITE_IMG, BLACK_IMG;
    private boolean continueTopRight, continueBottomRight, continueBottomLeft, continueTopLeft;

    static {
        WHITE_IMG = getScaledImage(640, 0, 320, 320, 80, 80);
        BLACK_IMG = getScaledImage(640, 320, 320, 320, 80, 80);
    }
    public Bishop(int xPosition, int yPosition, boolean isBlack) {
        super(xPosition, yPosition, isBlack);
    }

    @Override
    public ArrayList<Position> validateMoves(Figure[][] board) {
        continueBottomLeft = continueTopLeft = continueTopRight = continueBottomRight = true;
        var res = new ArrayList<Position>();
        var tempRes = new LinkedList<Position>();
        for (int step = 1; step < 8; step++) {
            if (continueBottomRight)
                tempRes.add(checkValidPlace(board, cord -> cord < 8,cord -> cord < 8, step, step));
            if (continueBottomLeft)
                tempRes.add(checkValidPlace(board, cord -> cord >= 0,cord -> cord < 8,-step, step));
            if (continueTopLeft)
                tempRes.add(checkValidPlace(board, cord -> cord >= 0, cord -> cord >= 0, -step, -step));
            if (continueTopRight)
                tempRes.add(checkValidPlace(board, cord -> cord < 8, cord -> cord >= 0, step, -step));
        }
        for (Position pos : tempRes)
            if (pos != null) res.add(pos);
        return res;
    }

    private Position checkValidPlace(Figure[][] board, Predicate<Integer> inequalityX, Predicate<Integer> inequalityY, int stepX, int stepY) {
        Position res = null;
        if (inequalityX.test(getxPosition() + stepX) && inequalityY.test(getyPosition() + stepY)) {
            if (board[getyPosition() + stepY][getxPosition() + stepX] == null ||
                    board[getyPosition() + stepY][getxPosition() + stepX].isBlack() != isBlack()) {
                res = new Position(getxPosition() + stepX, getyPosition() + stepY);
        System.out.println(stepX * stepY / Math.abs(stepX * stepY));
            }
            if (board[getyPosition() + stepY][getxPosition() + stepX] != null) {
                if (stepX * stepY > 0) {
                    if (stepX > 0)
                        continueBottomRight = false;
                    else
                        continueTopLeft = false;
                }
                else {
                    if (stepX > 0)
                        continueTopRight = false;
                    else
                        continueBottomLeft = false;
                }
            }
        }
        return res;
    }
}
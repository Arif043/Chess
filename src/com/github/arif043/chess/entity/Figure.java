package com.github.arif043.chess.entity;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * @author Arif Ertugrul
 * @date 21.06.24
 */
public abstract class Figure {

    public static final BufferedImage WHOLE_IMG;

    static {
        try {
            WHOLE_IMG = ImageIO.read(new File(Figure.class.getResource("/ChessPieces.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private boolean isBlack;
    private int xPosition, yPosition;

    public Figure(int xPosition, int yPosition, boolean isBlack) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isBlack = isBlack;
    }

    public abstract ArrayList<Position> validateMoves(Figure[][] board);

     protected static BufferedImage getScaledImage(int x, int y, int w, int h, int newWidth, int newHeight) {
         Image toolkitImage = WHOLE_IMG.getSubimage(x, y, w, h).getScaledInstance(newWidth, newHeight,
                 Image.SCALE_SMOOTH);
         int width = toolkitImage.getWidth(null);
         int height = toolkitImage.getHeight(null);

         BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
         Graphics g = newImage.getGraphics();
         g.drawImage(toolkitImage, 0, 0, null);
         g.dispose();

         return newImage;
     }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public boolean isBlack() {
        return isBlack;
    }
}
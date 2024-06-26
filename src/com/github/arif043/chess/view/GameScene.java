package com.github.arif043.chess.view;

import com.github.arif043.chess.entity.*;
import com.github.arif043.chess.service.RootService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class GameScene {

    private final RootService rootService;
    private final JPanel mainPanel;
    private JPanel boardPanel;
    private final JPanel sidePanel;
    private final JButton[][] buttonBoard = new JButton[8][8];
    private static final Color BROWN = new Color(91, 58, 41);
    private int selectedX = -1, selectedY = -1;

    public GameScene(RootService rootService) {
        this.rootService = rootService;
        mainPanel = new JPanel(new BorderLayout());
        sidePanel = new JPanel();

        initBoardPanel();
    }

    private void initBoardPanel() {
        var boardLayout = new GridLayout(8, 8);
        var black = false;
        boardPanel = new JPanel();
        boardPanel.setLayout(boardLayout);

        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                var button = new JButton();
                buttonBoard[i][k] = button;
                button.setBackground(black ? BROWN : Color.WHITE);
                button.setPreferredSize(new Dimension(80, 80));
                button.addActionListener(e -> {
                    button.requestFocus();
                });
                boardPanel.add(button);
                black = !black;
            }
            black = !black;
        }

        mainPanel.add(boardPanel, BorderLayout.CENTER);
    }

    public void prepareGame() {
        preparePlayersFigure(true);
        preparePlayersFigure(false);
        registerGameEvents();
    }

    private void preparePlayersFigure(boolean isBlack) {
        var y = isBlack ? 0 : 7;
        for (int k = 0; k < 8; k++) {
            buttonBoard[isBlack ? 1 : 6][k].setIcon(new ImageIcon(isBlack ? Pawn.BLACK_IMG : Pawn.WHITE_IMG));
        }
        buttonBoard[y][0].setIcon(new ImageIcon(isBlack ? Rook.BLACK_IMG : Rook.WHITE_IMG));
        buttonBoard[y][1].setIcon(new ImageIcon(isBlack ? Knight.BLACK_IMG : Knight.WHITE_IMG));
        buttonBoard[y][2].setIcon(new ImageIcon(isBlack ? Bishop.BLACK_IMG : Bishop.WHITE_IMG));
        buttonBoard[y][3].setIcon(new ImageIcon(isBlack ? Queen.BLACK_IMG : Queen.WHITE_IMG));
        buttonBoard[y][4].setIcon(new ImageIcon(isBlack ? Knight.BLACK_IMG : King.WHITE_IMG));
        buttonBoard[y][5].setIcon(new ImageIcon(isBlack ? Bishop.BLACK_IMG : Bishop.WHITE_IMG));
        buttonBoard[y][6].setIcon(new ImageIcon(isBlack ? Knight.BLACK_IMG : Knight.WHITE_IMG));
        buttonBoard[y][7].setIcon(new ImageIcon(isBlack ? Rook.BLACK_IMG : Rook.WHITE_IMG));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void registerGameEvents() {
        ActionListener buttonListener = e -> {
            var currentPlayer = rootService.getCurrentGame().getCurrentPlayer();
            var board = rootService.getCurrentGame().getBoard();
            var clickedButton = (JButton) e.getSource();
            int x = 0, y = 0;
            OuterLoop:
            for (int j = 0; j < 8; j++)
                for (int k = 0; k < 8; k++) {
                    if (clickedButton.equals(buttonBoard[j][k])) {
                        y = j;
                        x = k;
                        break OuterLoop;
                    }
                }
            if (selectedX == -1 && selectedY == -1 && board[y][x] != null && (currentPlayer.isBlack() == board[y][x].isBlack())) {
                selectedX = x;
                selectedY = y;
                clickedButton.setBackground(Color.YELLOW.darker());
                var options = rootService.getPlayerActionService().showMoveOptions(selectedX, selectedY);
                System.out.println(options);
                for (Position pos : options)
                    buttonBoard[pos.yCord()][pos.xCord()].setBackground(new Color(92, 226, 127));
            }
        };

        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 8; k++) {
                buttonBoard[j][k].addActionListener(buttonListener);
            }
        }
    }

}
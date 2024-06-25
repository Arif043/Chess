package com.github.arif043.chess.view;

import com.github.arif043.chess.entity.Pawn;
import com.github.arif043.chess.service.RootService;

import javax.swing.*;
import java.awt.*;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class GameScene {

    private RootService rootService;
    private JPanel mainPanel;
    private JPanel boardPanel;
    private JPanel sidePanel;

    public GameScene(RootService rootService) {
        this.rootService = rootService;
        mainPanel = new JPanel(new BorderLayout());
        sidePanel = new JPanel();

        initBoardPanel();

    }

    private void initBoardPanel() {
        boardPanel = new JPanel();
        var boardLayout = new GridLayout(8, 8);
        boardPanel.setLayout(boardLayout);

        for (int i = 0; i < 8; i++)
            for (int k = 0; k < 8; k++) {
                var button = new JButton(new ImageIcon(Pawn.WHITE_IMG));
                button.setPreferredSize(new Dimension(80, 80));
                button.addActionListener(e -> {button.requestFocus();});
                button.setBackground(Color.WHITE);
                boardPanel.add(button);

            }

        mainPanel.add(boardPanel,BorderLayout.CENTER);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
package com.github.arif043.chess.view;

import com.github.arif043.chess.service.RootService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.Year;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Application {

    private RootService rootService;
    private JFrame frame;

    public Application(RootService rootService) {
        this.rootService = rootService;
        // init ui
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var layout = new FlowLayout();
        layout.setVgap(30);
        frame.setLayout(layout);

        final var background = Color.YELLOW;
        var contentPane = new JPanel();
        var buttonPanel = new JPanel();
        var firstPlayerNameField = new HintTextField("Player 1");
        var secondPlayerNameField = new HintTextField("Player 2");
        var startButton = new JButton("Start");
        var quitButton = new JButton("Quit");

        var buttonFront = new Font("Arial", Font.BOLD, 24);
        startButton.setFont(buttonFront);
        quitButton.setFont(buttonFront);
        startButton.setBackground(Color.CYAN.brighter());
        quitButton.setBackground(Color.PINK);
        startButton.addActionListener(e -> {
            var firstNamePlayer = firstPlayerNameField.getText().isEmpty() ? "Player 1" : firstPlayerNameField.getText();
            var secondNamePlayer = secondPlayerNameField.getText().isEmpty() ? "Player 2" : secondPlayerNameField.getText();
            var gameScene = new GameScene(rootService);
            frame.getContentPane().removeAll();
            frame.getContentPane().repaint();
            frame.add(gameScene.getMainPanel());
            frame.setSize(700, 740);
            frame.setLocationRelativeTo(null);

        });
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.setBackground(background);
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        contentPane.setBounds(0, 0, 500, 500);
        contentPane.setBackground(background);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(firstPlayerNameField);
        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(secondPlayerNameField);
        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(buttonPanel);

        frame.getContentPane().setBackground(background);
        frame.add(contentPane);
        frame.pack();
        frame.setSize((int) (frame.getWidth() * 1.2), frame.getHeight());
        frame.setLocationRelativeTo(null);
        contentPane.requestFocus();
        frame.setVisible(true);
    }

    private static class HintTextField extends JTextField implements FocusListener {

        final String hint;
        boolean showingHint;

        HintTextField(final String hint) {
            super(hint);
            setFont(new Font("Arial", Font.BOLD, 24));
            this.hint = hint;
            showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText("");
                showingHint = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText(hint);
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }
}
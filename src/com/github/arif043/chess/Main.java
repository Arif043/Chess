package com.github.arif043.chess;

import com.github.arif043.chess.service.RootService;
import com.github.arif043.chess.view.Application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Arif Ertugrul
 * @date 22.06.24
 */
public class Main {

    public static void main(String[] args) {
                new Application(new RootService());
    }
}
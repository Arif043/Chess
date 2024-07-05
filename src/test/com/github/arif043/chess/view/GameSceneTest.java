package test.com.github.arif043.chess.view;

import com.github.arif043.chess.view.GameScene;
import com.github.arif043.chess.service.RootService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class GameSceneTest {

    @Test
    void testInitBoardPanel() {
        RootService rootService = new RootService();
        GameScene gameScene = new GameScene(rootService);
        gameScene.initBoardPanel();

        assertNotNull(gameScene.getMainPanel());
    }
}
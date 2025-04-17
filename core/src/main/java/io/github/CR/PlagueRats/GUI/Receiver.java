package io.github.CR.PlagueRats.GUI;

import com.badlogic.gdx.Game;
import io.github.CR.PlagueRats.RatGame;
import io.github.CR.PlagueRats.SettingsMenu;

public class Receiver {
    private RatGame game;

    public Receiver(RatGame game) {
        this.game = game;
    }

    public void quitGame() {
        System.exit(0);
    }

    public void settings() {
        game.setScreen(new SettingsMenu(game));
    }
}

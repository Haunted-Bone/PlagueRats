package io.github.CR.PlagueRats.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonFactory implements GUIFactory {
    ElementTracker e = new ElementTracker();
    private int buttonID;

    @Override
    public TextButton createButton(String name, Skin skin) {
        int bCount = e.getButtonCount();
        TextButton b = new TextButton(name, skin);
        int buttonHeight = 50;
        b.setSize(100, buttonHeight);
        b.setPosition((float) Gdx.graphics.getWidth() / 2 - 50, (float) Gdx.graphics.getHeight() / 2 - (25 + (buttonHeight * bCount)));
        e.addButton();
        return b;
    }

}

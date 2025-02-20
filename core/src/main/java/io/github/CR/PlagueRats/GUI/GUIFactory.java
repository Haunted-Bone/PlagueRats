package io.github.CR.PlagueRats.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public interface GUIFactory {

    public TextButton createButton(String name, Skin skin);
}

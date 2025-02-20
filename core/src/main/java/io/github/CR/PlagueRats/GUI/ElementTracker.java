package io.github.CR.PlagueRats.GUI;

public class ElementTracker {
    private int buttonCount;
    private int buttonID;

    public void addButton() {
        buttonCount++;

    }

    public int getButtonCount() {
        return buttonCount;
    }
}

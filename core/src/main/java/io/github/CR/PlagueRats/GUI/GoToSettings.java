package io.github.CR.PlagueRats.GUI;

import io.github.CR.PlagueRats.RatGame;

public class GoToSettings implements Command {
    private Receiver receiver;

    public GoToSettings(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.settings();
    }
}

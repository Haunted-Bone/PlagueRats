package io.github.CR.PlagueRats.GUI;

public class QuitGame implements Command {
    private Receiver receiver;

    public QuitGame(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.quitGame();
    }
}

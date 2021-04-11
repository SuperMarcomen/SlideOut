package it.marcodemartino.icecave.handlers;

import it.marcodemartino.icecave.entities.Direction;
import it.marcodemartino.icecave.entities.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class InputHandler implements EventHandler<KeyEvent> {

    private final Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            switch (event.getCode()) {
                case UP, W -> player.move(Direction.UP);
                case DOWN, S -> player.move(Direction.DOWN);
                case LEFT, A -> player.move(Direction.LEFT);
                case RIGHT, D -> player.move(Direction.RIGHT);
            }
        }
    }
}

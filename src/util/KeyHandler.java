package util;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import entities.Snake;

public class KeyHandler implements EventHandler<KeyEvent> {
    private final Snake snake;

    public KeyHandler(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getEventType().getName()) {
            case "KEY_PRESSED" -> {
                switch (event.getCode()) {
                    case LEFT -> snake.setDX(-1);
                    case RIGHT -> snake.setDX(1);
                    case UP -> snake.setDY(-1);
                    case DOWN -> snake.setDY(1);
                    default -> {
                    }
                }
            }
            case "KEY_RELEASED" -> {
                switch (event.getCode()) {
                    case LEFT, RIGHT  -> snake.setDX(0);
                    case UP, DOWN -> snake.setDY(0);
                    default -> {
                    }
                }
            }
        }
    }
}


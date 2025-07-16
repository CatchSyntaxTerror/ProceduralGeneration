package util;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import entities.Snake;

public class MouseHandler implements EventHandler<MouseEvent> {
    private final Snake snake;

    public MouseHandler(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void handle(MouseEvent event) {
        snake.setTarget(event.getX(), event.getY());
    }
}

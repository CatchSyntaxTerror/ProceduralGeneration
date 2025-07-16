package main;

import entities.Snake;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This is the main class. I will test code here for the time being,
 * but this is meant to be the main game controller.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = screenBounds.getWidth() / 1.5;
        double height = screenBounds.getHeight() / 1.5;
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setX(screenBounds.getWidth() * 0.15);
        stage.setY(screenBounds.getHeight() * 0.15);

        Pane root = new Pane();
        Scene scene = new Scene(root, width, height);
        root.setStyle("-fx-background-color: #1a1a1a;");

//        for (int i = 1; i < 11 ; i++) {
//            makeSnake(root, width - i * 50, height - i * 50, 10, 20);
//        }

        Snake snake = makeSnake(root, width/2, height/2 , 10, 20);

        scene.setOnKeyPressed(new util.KeyHandler(snake));
        scene.setOnKeyReleased(new util.KeyHandler(snake));

        scene.setOnMouseMoved(new util.MouseHandler(snake));

        root.setFocusTraversable(true);
        root.requestFocus();

        stage.setScene(scene);
        stage.setTitle("Simulation");
        stage.show();
    }

    private Snake makeSnake(Pane root, double startX, double startY, double headSize, int length) {
        Snake snake = new Snake(startX, startY, headSize, length, root);
        snake.draw(root);
        Thread thread = new Thread(snake);
        thread.start();
        return snake;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


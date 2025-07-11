package entities;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import util.Segments;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Youssef Amin
 * This class creates snakes made up of segments
 * Todo: Draw lines on the sides of the segment chain to make a snake shape
 */
public class Snake implements Runnable {

    private final List<Segments> SEGMENTS = new ArrayList<>();
    private final List<Circle> SHAPES = new ArrayList<>();
    private final double MAX_ROTATION = Math.toRadians(90);
    boolean alive = true;

    public Snake(double startX, double startY, double initialRadius, int segemnts) {
        double radius = initialRadius;
        double x = startX;
        double y = startY;
        for (int i = 0; i < segemnts; i++) {
            SEGMENTS.add(new Segments(x, y, radius));
            x += Math.cos(0) * (radius * 2);
            y += Math.sin(0) * (radius + 1.5);
            radius *= 0.95;
        }

//        for (Segments seg : segments) {
//            System.out.println(seg.toString());
//        }
    }

    public void draw(Pane pane) {
        for (Segments seg : SEGMENTS) {
            Circle circle = new Circle(seg.x, seg.y, seg.radius);
            circle.setFill(Color.FORESTGREEN);
            circle.setStroke(Color.BLACK);
            pane.getChildren().add(circle);
            SHAPES.add(circle);
        }
    }

    /**
     * Currently, this method simply moves the head of the snake to the left
     */
    @Override
    public void run() {
        while (alive) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Segments head = SEGMENTS.getFirst();
            head.x -= 2;
            updateSegments();

            javafx.application.Platform.runLater(() -> {
                for (int i = 0; i < SHAPES.size(); i++) {
                    SHAPES.get(i).setCenterX(SEGMENTS.get(i).x);
                    SHAPES.get(i).setCenterY(SEGMENTS.get(i).y);
                }
            });
        }
    }

    /**
     * This method updates the body's x,y positions based on the heads position
     */
    private void updateSegments() {
        for(int i = 1; i < SEGMENTS.size(); i++) {
            SEGMENTS.get(i).x -= 2;
        }
    }
}



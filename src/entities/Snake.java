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
public class Snake extends Entity {

    private final List<Segments> SEGMENTS = new ArrayList<>();
    private final List<Circle> SHAPES = new ArrayList<>();

    private Pane pane;
    private double headSpeed = 2.0;

    public Snake(double startX, double startY, double initialRadius, int segemnts, Pane pane) {
        super(startX, startY);
        this.pane = pane;

        double radius = initialRadius;
        double x = startX;
        double y = startY;
        for (int i = 0; i < segemnts; i++) {
            SEGMENTS.add(new Segments(x, y, radius, i));
            x += Math.cos(0) * (radius * 2);
            y += Math.sin(0) * (radius + 1.5);
            radius *= 0.95;
        }

        for (Segments seg : SEGMENTS) {
            Circle circle = new Circle(seg.x, seg.y, seg.radius);
            if(seg.num % 2 == 0) {
                circle.setFill(Color.BLACK);
            } else {
                circle.setFill(Color.YELLOW);
            }

            circle.setStroke(Color.BLACK);
            SHAPES.add(circle);
            pane.getChildren().add(circle);
        }
    }

    @Override
    public void update() {
        Segments head = SEGMENTS.get(0);
        head.x -= headSpeed;
        updateSegments();
    }

    /**
     * This method updates the body's x,y positions based on the heads position
     */
    private void updateSegments() {
        for(int i = 1; i < SEGMENTS.size(); i++) {
            SEGMENTS.get(i).x -= 2;
        }
    }

    @Override
    public void draw(Pane pane) {
        for (int i = 0; i < SHAPES.size(); i++) {
            Circle c = SHAPES.get(i);
            Segments seg = SEGMENTS.get(i);
            c.setCenterX(seg.x);
            c.setCenterY(seg.y);
        }
    }

    /**
     * Currently, this method simply moves the head of the snake to the left
     */
    @Override
    public void run() {
        while (alive) {
            update();

            javafx.application.Platform.runLater(() -> {
                draw(pane);
            });

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Snake is having trouble");
                e.printStackTrace();
            }
        }
    }

}



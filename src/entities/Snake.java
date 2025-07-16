package entities;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import util.Segment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Youssef Amin
 * This class creates snakes made up of segments
 * Todo: Draw lines on the sides of the segment chain to make a snake shape
 *       Have the snake run in a clockwise and counterclockwise circle.
 */
public class Snake extends Entity {

    private final List<Segment> SEGMENTS = new ArrayList<>();
    private final List<Circle> SHAPES = new ArrayList<>();

    private final Pane pane;
    private final double headSpeed = 2.0;
    private double targetX;
    private double targetY;
    private boolean mouse = true;

    public Snake(double startX, double startY, double initialRadius, int segments, Pane pane) {
        super(startX, startY);
        this.pane = pane;

        double radius = initialRadius;
        double x = startX;
        double y = startY;
        SEGMENTS.addFirst(new Segment(x, y, radius, 0));
        SEGMENTS.add(new Segment(x, y, radius * 1.5, 1));

        for (int i = 2; i < segments; i++) {
            SEGMENTS.add(new Segment(SEGMENTS.get(i - 1).x + SEGMENTS.get(i - 1).radius, SEGMENTS.get(i - 1).y, SEGMENTS.get(i - 1).radius * 0.95, i));
            x += Math.cos(0) * (radius * 2);
            y += Math.sin(0) * (radius + 1.5);

        }

        makeSegments();
    }

    private void makeSegments() {
        for (Segment seg : SEGMENTS) {
            Circle circle = new Circle(seg.x, seg.y, seg.radius);
            circle.setFill(Color.RED);
            circle.setStroke(Color.RED);
            SHAPES.add(circle);
        }

        for (int i = SEGMENTS.size() - 1; i >= 0; i--) {
            pane.getChildren().add(SHAPES.get(i));
        }
    }

    /**
     * this method updates the snakes position
     */
    @Override
    public void update() {
        Segment head = SEGMENTS.getFirst();
        if (mouse) {
            double TdistX = targetX - head.x;
            double TdistY = targetY - head.y;
            double Tdist = Math.sqrt(TdistX * TdistX + TdistY * TdistY);
            if (Tdist > 1) {
                head.x += headSpeed * (TdistX / Tdist);
                head.y += headSpeed * (TdistY / Tdist);
            }
        } else {
            head.x += dx * headSpeed;
            head.y += dy * headSpeed;
        }
        updateBody();
    }

    /**
     * This method updates the body's x,y positions based on the heads position
     */
    private void updateBody() {
        for (int i = 1; i < SEGMENTS.size(); i++) {
            Segment prev = SEGMENTS.get(i - 1);
            Segment curr = SEGMENTS.get(i);

            double dx = prev.x - curr.x;
            double dy = prev.y - curr.y;

            double desiredAngle = Math.atan2(dy, dx);
            double angleDiff = desiredAngle - curr.angle;
            angleDiff = Math.atan2(Math.sin(angleDiff), Math.cos(angleDiff));

            double maxTurnAngle = Math.toRadians(3);

            if (angleDiff > maxTurnAngle) angleDiff = maxTurnAngle;
            else if (angleDiff < -maxTurnAngle) angleDiff = -maxTurnAngle;

            curr.angle += angleDiff;
            double distance = prev.radius;
            curr.x = prev.x - Math.cos(curr.angle) * distance;
            curr.y = prev.y - Math.sin(curr.angle) * distance;
        }
    }

    /**
     * sets the snakes target
     *
     * @param targetX the mouses x
     * @param targetY the mouses y
     */
    public void setTarget(double targetX, double targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
    }

    @Override
    public void draw(Pane pane) {
        for (int i = 0; i < SHAPES.size(); i++) {
            Circle c = SHAPES.get(i);
            Segment seg = SEGMENTS.get(i);
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



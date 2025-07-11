package entities;

import javafx.scene.layout.Pane;

/**
 * Parent class for all game entities.
 * Stores core state and defines update/draw methods to override.
 * Leaves body representation totally up to subclasses.
 */
public abstract class Entity implements Runnable {
    public double x, y;
    public boolean alive = true;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Update the entity’s state
     */
    public abstract void update();

    /**
     *  Draw the entity on the given Pane
     */
    public abstract void draw(Pane pane);

    /**
     *  Runnable’s run method: typical game loop
     */
    @Override
    public void run() {
        while (alive) {

            try {
                Thread.sleep(10); // or configurable tick rate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update();
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }
}

package util;

/**
 * Author: Youssef Amin
 * This class is responsible for making each segment and takes in an x, y position as well as the radius
 * of the point.
 */
public class Segment {

    public double x, y;
    public double radius;
    public int num;
    public double angle;

    public Segment(double x, double y, double radius, int num){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.num = num;
        this.angle = 0;
    }

    @Override
    public String toString() {
        return "{" + "x=" + x + ", y=" + y + ", radius=" + radius + '}';
    }
}

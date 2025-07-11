package util;

/**
 * Author: Youssef Amin
 * This class is responsible for making each segment and takes in an x, y position as well as the radius
 * of the point.
 */
public class Segments {

    public double x, y;
    public double radius;
    public int num;

    public Segments( double x, double y, double radius, int num){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.num = num;
    }

    @Override
    public String toString() {
        return "{" + "x=" + x + ", y=" + y + ", radius=" + radius + '}';
    }
}

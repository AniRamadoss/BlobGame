import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class Blob {
    int radius;
    Color theColor;
    int x, y;
    int deltaX, deltaY;

    public Blob(
        int aRadius,
        Color aColor,
        int aX,
        int aY,
        int aDeltaX,
        int aDeltaY) {
        // assign class variables
        this.radius = aRadius;
        this.theColor = aColor;
        this.x = aX;
        this.y = aY;
        this.deltaX = aDeltaX;
        this.deltaY = aDeltaY;
    }


    // returns the x coordinate of the blob
    public int getX() {
        return x;
    }


    // returns the y coordinate of the blob
    public int getY() {
        return y;
    }


    public int getDeltaX() {
        return deltaX;
    }


    public int getDeltaY() {
        return deltaY;
    }


    // returns the radius of the blob
    public int getRadius() {
        return radius;
    }


    // increases the size(radius) of the blob
    public void eat() {
        radius += 3;
    }


    // moves the blob on its current path
    public void move() {
        x += deltaX;
        y += deltaY;
    }


    // has the blob increase its direction to the right
    public void moveRight() {
        deltaX += 2;
        move();
    }


    // has the blob increase its direction to the left
    public void moveLeft() {
        deltaX -= 2;
        move();
    }


    // has the blob increase its direction up
    public void moveUp() {
        deltaY -= 2;
        move();
    }


    // has the blob increase its direction down
    public void moveDown() {
        deltaY += 2;
        move();
    }


    public void draw(Graphics g) {
        g.setColor(theColor);
        g.fillOval(x - radius, y - radius, 2 * radius + 1, 2 * radius + 1);
    }
}

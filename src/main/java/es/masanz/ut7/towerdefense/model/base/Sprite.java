package es.masanz.ut7.towerdefense.model.base;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public abstract class Sprite {

    private double x, y;
    private int width, height;
    private boolean alive;

    public Sprite(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alive = true;
    }

    // devolvera true si sigue vivo, false sino
    public abstract boolean takeDamage(int damage);

    public abstract void draw(GraphicsContext gc);

    public abstract void update(Sprite target);

    public abstract List<Bullet> tryToShoot(double elapsedTime, List<Sprite> targets);

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean checkCollisions(Sprite target){
        double dx = this.getX() - target.getX();
        double dy = this.getY() - target.getY();
        double distance = Math.hypot(dx, dy);
        double collisionDistance = (this.getWidth() / 2) + (target.getWidth() / 2);
        if (distance < collisionDistance) {
            target.takeDamage(1);
            this.takeDamage(1);
            return true;
        }
        return false;
    }
}

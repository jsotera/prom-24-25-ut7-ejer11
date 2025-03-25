package es.masanz.ut7.towerdefense.model.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Bullet extends Sprite {

    private double speed;
    private double angle;
    private Color color;
    private boolean isFromTower;

    public Bullet(Sprite from, Sprite target){
        this(from, target, 6, 6, Color.YELLOW, 4);
    }

    public Bullet(Sprite from, Sprite target, int width, int height, Color color, int speed){
        super(from.getX(), from.getY(), width, height);
        this.color = color;
        this.speed = speed;
        this.isFromTower = false;
        if(from instanceof Tower) {
            this.isFromTower = true;
        }
        this.angle = Math.atan2(target.getY() - from.getY(), target.getX() - from.getX());
    }

    @Override
    public void update(Sprite target) {
        // Esta bala va recta, por lo que no sigue a ningun objetivo
        setX(getX() + Math.cos(angle) * speed);
        setY(getY() + Math.sin(angle) * speed);
        // TODO: Hacer que si la bala se sale del mapa que muera (alive=false)
    }

    @Override
    public List<Bullet> tryToShoot(double elapsedTime, List<Sprite> targets) {
        // Si, una bala podria disparar, lo suyo seria replicar el objetivo original
        return new ArrayList<>();
    }

    @Override
    public boolean takeDamage(int damage) {
        // Si, una bala podria tener vida, pero esta muere al recibir cualquier golpe
        setAlive(false);
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
    }

    public boolean isFromTower() {
        return isFromTower;
    }
}

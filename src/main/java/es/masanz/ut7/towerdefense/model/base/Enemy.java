package es.masanz.ut7.towerdefense.model.base;

import es.masanz.ut7.towerdefense.model.impl.Rifle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Sprite {

    double speed = 1.5;
    Color color;
    int health = 2;
    List<Weapon> weapons = new ArrayList<>();
    private double shootCooldown = new Random().nextDouble() + 1;

    public Enemy(double x, double y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
        weapons.add(new Rifle());
    }

    @Override
    public void update(Sprite target) {
        double angle = Math.atan2(target.getY() - getY(), target.getX() - getX());
        setX(getX() + Math.cos(angle) * speed);
        setY(getY() + Math.sin(angle) * speed);
    }

    @Override
    public List<Bullet> tryToShoot(double elapsedTime, List<Sprite> targets) {
        List<Bullet> newBullets = new ArrayList<>();
        for (Weapon weapon : weapons) {
            newBullets.addAll(weapon.shoot(this, targets, elapsedTime));
        }
        return newBullets;
    }

    @Override
    public boolean takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            setAlive(false);
            return false;
        }
        return true;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
    }
}

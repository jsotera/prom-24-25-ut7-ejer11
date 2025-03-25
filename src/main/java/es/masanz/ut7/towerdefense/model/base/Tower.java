package es.masanz.ut7.towerdefense.model.base;

import es.masanz.ut7.towerdefense.model.impl.Metralleta;
import es.masanz.ut7.towerdefense.model.impl.Rifle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Tower extends Sprite {

    private int lives = 10;
    private List<Weapon> weapons = new ArrayList<>();
    private double lastShotTime = 0;

    public Tower(double x, double y, int width, int height) {
        super(x, y, width, height);
        weapons.add(new Metralleta());
        //weapons.add(new Rifle());
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
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillOval(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
    }

    @Override
    public void update(Sprite target) {
        // no actualizamos la posicion de la torre
    }

    @Override
    public boolean takeDamage(int damage) {
        lives = lives - damage;
        return lives > 0;
    }
}

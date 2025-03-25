package es.masanz.ut7.towerdefense.model.impl;

import es.masanz.ut7.towerdefense.model.base.Bullet;
import es.masanz.ut7.towerdefense.model.base.Sprite;
import es.masanz.ut7.towerdefense.model.base.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Rifle implements Weapon {

    private static final double COOLDOWN = 0.6;
    private double lastShotTime = 0;

    @Override
    public List<Bullet> shoot(Sprite from, List<Sprite> targets, double elapsedTime) {
        List<Bullet> bullets = new ArrayList<>();
        lastShotTime += elapsedTime;
        if(lastShotTime >= getShootCooldown() && targets!=null && targets.size()>0){
            // el rifle siempre atacara al enemigo mas cercano
            Sprite target = findClosestTarget(from, targets);
            bullets.add(new GigaBullet(from, target));
            lastShotTime = 0;
        }
        return bullets;
    }

    @Override
    public double getShootCooldown() {
        return COOLDOWN;
    }

    private Sprite findClosestTarget(Sprite from, List<Sprite> targets) {
        if (targets==null || targets.isEmpty()) {
            return null;
        }
        Sprite closest = null;
        double minDistance = Double.MAX_VALUE;
        double towerX = from.getX();
        double towerY = from.getY();
        for (Sprite target : targets) {
            double distance = Math.hypot(target.getX() - towerX, target.getY() - towerY);
            if (distance < minDistance) {
                minDistance = distance;
                closest = target;
            }
        }
        return closest;
    }
}

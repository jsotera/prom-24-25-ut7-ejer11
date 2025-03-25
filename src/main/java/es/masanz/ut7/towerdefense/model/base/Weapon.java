package es.masanz.ut7.towerdefense.model.base;

import java.util.List;

public interface Weapon {

    List<Bullet> shoot(Sprite from, List<Sprite> targets, double elapsedTime);

    double getShootCooldown();
}

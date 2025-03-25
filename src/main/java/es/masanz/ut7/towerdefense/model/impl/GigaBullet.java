package es.masanz.ut7.towerdefense.model.impl;

import es.masanz.ut7.towerdefense.model.base.Bullet;
import es.masanz.ut7.towerdefense.model.base.Sprite;
import javafx.scene.paint.Color;

public class GigaBullet extends Bullet {

    public GigaBullet(Sprite from, Sprite target) {
        super(from, target,20, 20, Color.GREEN, 4);
    }


}

package es.masanz.ut7.towerdefense.controller;

import es.masanz.ut7.towerdefense.model.base.Sprite;
import es.masanz.ut7.towerdefense.model.base.Bullet;
import es.masanz.ut7.towerdefense.model.base.Enemy;
import es.masanz.ut7.towerdefense.model.base.Tower;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameController {

    private static final int WIDTH = 600, HEIGHT = 600;
    private static final double VELOCIDAD_ENEMIES_SPAWN = 0.5;
    private static final double DISTANCIA_SPAWN = 300;

    private List<Sprite> enemies;
    private List<Sprite> bullets;
    private Sprite tower;

    private double lastEnemySpawn = 0;

    public GameController(){
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        this.tower = new Tower(300, 300, 20, 20);
    }

    public void launch(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Scene scene = new Scene(new javafx.scene.layout.StackPane(canvas), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Tower Defense");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }
                double elapsedTime = (now - lastUpdate) / 1e9;
                lastUpdate = now;
                checkCollisions();
                update(elapsedTime);
                render(gc);
            }
        };
        timer.start();
    }

    private void checkCollisions() {
        //Analizamos la colision de las balas con la torre y los enemigos
        Iterator<Sprite> bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            Bullet bullet = (Bullet) bulletIter.next();
            if (bullet.isFromTower()) {
                Iterator<Sprite> enemyIter = enemies.iterator();
                while (enemyIter.hasNext()) {
                    Sprite enemy = enemyIter.next();
                    if(bullet.checkCollisions(enemy)){
                        if(!enemy.isAlive()){
                            enemyIter.remove();
                        }
                        if(!bullet.isAlive()){
                            bulletIter.remove();
                            break;
                        }
                    }
                }
            } else {
                if(bullet.checkCollisions(tower)){
                    if(!tower.isAlive()){
                        System.out.println("Game Over");
                        System.exit(0);
                    }
                    bulletIter.remove();
                }
            }
        }

        //Analizamos la colision de las balas de la torre y las balas de los enemigos
        List<Sprite> bulletsToRemove = new ArrayList<>();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bulletFrom = (Bullet) bullets.get(i);
            if (bulletFrom.isFromTower()) {
                for (int j = 0; j < bullets.size(); j++) {
                    Bullet bulletTo = (Bullet) bullets.get(j);
                    if (!bulletTo.isFromTower() && bulletFrom.checkCollisions(bulletTo)) {
                        if (!bulletTo.isAlive()) {
                            bulletsToRemove.add(bulletTo);
                        }
                        if (!bulletFrom.isAlive()) {
                            bulletsToRemove.add(bulletFrom);
                        }
                    }
                }
            }
        }
        bullets.removeAll(bulletsToRemove);

        //Analizamos la colision entre torre y enemigos
        Iterator<Sprite> enemyIter = enemies.iterator();
        while (enemyIter.hasNext()) {
            Sprite enemy = enemyIter.next();
            if(enemy.checkCollisions(tower)){
                if(!tower.isAlive()){
                    System.out.println("Game Over");
                    System.exit(0);
                }
                enemyIter.remove();
            }
        }
    }

    private void update(double elapsedTime) {
        lastEnemySpawn += elapsedTime;
        if (lastEnemySpawn >= VELOCIDAD_ENEMIES_SPAWN) {
            spawnEnemy();
            lastEnemySpawn = 0;
        }
        bullets.addAll(tower.tryToShoot(elapsedTime, enemies));
        for (Sprite enemy : enemies) {
            enemy.update(tower);
            bullets.addAll(enemy.tryToShoot(elapsedTime, List.of(tower)));
        }
        List<Sprite> newBullets = new ArrayList<>();
        for (Sprite bullet : bullets) {
            bullet.update(tower);
            newBullets.addAll(bullet.tryToShoot(elapsedTime, null));
        }
        bullets.addAll(newBullets);

        //Analizamos si despues actualizar a los enemigos, alguno desaparece
        Iterator<Sprite> enemiesIterator = enemies.iterator();
        while(enemiesIterator.hasNext()) {
            Sprite enemy = enemiesIterator.next();
            if(!enemy.isAlive()){
                enemiesIterator.remove();
            }
        }
        //Analizamos si despues actualizar a las balas, alguna desaparece
        Iterator<Sprite> bulletsIterator = bullets.iterator();
        while(bulletsIterator.hasNext()) {
            Sprite bullet = bulletsIterator.next();
            if(!bullet.isAlive()){
                bulletsIterator.remove();
            }
        }

    }

    private void spawnEnemy() {
        Random rand = new Random();
        double angle = rand.nextDouble() * 2 * Math.PI;
        enemies.add(new Enemy(300 + DISTANCIA_SPAWN * Math.cos(angle), 300 + DISTANCIA_SPAWN * Math.sin(angle), 20, 20, Color.RED));
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        tower.draw(gc);
        enemies.forEach(e -> e.draw(gc));
        bullets.forEach(b -> b.draw(gc));
    }

}

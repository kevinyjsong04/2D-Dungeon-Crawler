package com.example.dungeoncrawler.model;

public abstract class Spawner {
    public Enemy spawnEnemy() {
        Enemy enemy;

        enemy = createEnemy();
        return enemy;
    }
    abstract Enemy createEnemy();
}

package com.example.dungeoncrawler.model;

public abstract class Weapon {

    public Weapon() {
    }

    public void attack(Enemy enemy) {
        enemy.kill();
    }

    public abstract boolean checkEnemy(Location location, Enemy enemy);
}

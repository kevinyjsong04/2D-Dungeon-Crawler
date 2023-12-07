package com.example.dungeoncrawler.viewmodel;


import com.example.dungeoncrawler.model.Enemy;
import com.example.dungeoncrawler.model.Location;


public class EnemyViewModel {

    private Enemy enemy;

    public EnemyViewModel(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void movement() {
        enemy.movement();
    }

    public Location getLocation() {
        return enemy.getLocation();
    }


    public void resetLocation() {
        enemy.setCoords(0, 0);
    }

    public void setDamageMultiplier(int diff) {
        enemy.setDamageMultiplier(diff);
    }

    public boolean alive() {
        return enemy.alive();
    }
}

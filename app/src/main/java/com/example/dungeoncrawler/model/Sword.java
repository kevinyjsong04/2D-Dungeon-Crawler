package com.example.dungeoncrawler.model;

public class Sword extends Weapon {
    public boolean checkEnemy(Location location, Enemy enemy) {
        if (checkX(location.getxCord(), enemy) && checkY(location.getyCord(), enemy)) {
            attack(enemy);
            return true;
        }
        return false;
    }

    private boolean checkX(int x, Enemy enemy) {
        int enemyX = enemy.getLocation().getxCord();
        if (x < enemyX && x + 150 > enemyX) {
            return true;
        }
        return false;
    }

    private boolean checkY(int y, Enemy enemy) {
        int enemyY = enemy.getLocation().getyCord();
        if (y - 75 < enemyY && y + 75 > enemyY) {
            return true;
        }
        return false;
    }
}

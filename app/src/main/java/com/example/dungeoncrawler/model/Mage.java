package com.example.dungeoncrawler.model;

public class Mage extends Enemy {
    Mage() {
        health = 100;
        defense = 10;
        damage = 10;
        location = new Location(0, 0);
        movementCycleX = new int[]{-5, -5, -5, -5, -5, -5, -5, 5, 5, 5, 5, 5, 5, 5};
        movementCycleY = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        tickCount = 0;
    }
}

package com.example.dungeoncrawler.model;

public class Spirit extends Enemy {
    Spirit() {
        health = 100;
        defense = 10;
        damage = 5;
        location = new Location(0, 0);
        movementCycleX = new int[] {10, 10, 10, 10, -10, -10, -10, -10};
        movementCycleY = new int[] {10, 10, 10, 10, -10, -10, -10, -10};
        tickCount = 0;
    }
}

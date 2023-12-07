package com.example.dungeoncrawler.model;

public class SwordSkeleton extends Enemy {
    SwordSkeleton() {
        health = 100;
        defense = 20;
        damage = 15;
        location = new Location(0, 0);
        movementCycleX = new int[] {-5, -5, -5, -5, 5, 5, 5, 5};
        movementCycleY = new int[] {-5, -5, -5, -5, 5, 5, 5, 5};
        tickCount = 0;
    }
}

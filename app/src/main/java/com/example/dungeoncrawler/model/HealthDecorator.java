package com.example.dungeoncrawler.model;

public class HealthDecorator extends PowerupDecorator {
    public HealthDecorator(Powerupable p) {
        powerup = p;
        location = p.getLocation();
    }
    public int getHealth() {
        return powerup.getHealth() + 20;
    }
}

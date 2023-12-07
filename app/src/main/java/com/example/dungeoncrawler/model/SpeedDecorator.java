package com.example.dungeoncrawler.model;

public class SpeedDecorator extends PowerupDecorator  {
    public SpeedDecorator(Powerupable p) {
        powerup = p;
        location = p.getLocation();
    }
    public int getSpeed() {
        return powerup.getSpeed() + 10;
    }
}

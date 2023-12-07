package com.example.dungeoncrawler.model;

public interface Powerupable {
    int getSpeed();
    int getHealth();
    boolean getInvulnerability();
    int getScore();
    Location getLocation();
    void use();
}

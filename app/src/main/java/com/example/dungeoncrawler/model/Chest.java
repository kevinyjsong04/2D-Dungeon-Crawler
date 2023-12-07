package com.example.dungeoncrawler.model;
import java.math.*;
public class Chest {
    private static Location location;

    private final long chestScore;

    public Chest(Location location) {
        this.location = new Location(location.getxCord(), location.getyCord());
        chestScore = (int) (Math.random() * 51 + 50);
    }

    public static Location getLocation() {
        return location;
    }
    public static void setLocation(int newX, int newY) {
        Chest.location.setxCord(newX);
        Chest.location.setyCord(newY);
    }

    public long getChestScore() {
        return chestScore;
    }
}

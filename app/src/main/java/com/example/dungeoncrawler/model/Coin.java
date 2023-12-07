package com.example.dungeoncrawler.model;

public class Coin {
    private static Location location;

    private static final long COINSCORE = 10;

    public Coin(Location location) {
        this.location = new Location(location.getxCord(), location.getyCord());
    }

    public static Location getLocation() {
        return location;
    }
    public static void setLocation(int newX, int newY) {
        Coin.location.setxCord(newX);
        Coin.location.setyCord(newY);
    }

    public static long getCoinScore() {
        return COINSCORE;
    }
}

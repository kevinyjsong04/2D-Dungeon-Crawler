package com.example.dungeoncrawler.model;

public interface Observer {
    public boolean checkCollision(Location entityLocation, int changeX, int changeY);

    public void update(Location location);
}

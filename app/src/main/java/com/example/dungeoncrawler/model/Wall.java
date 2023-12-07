package com.example.dungeoncrawler.model;

public class Wall implements Observer {
    private Location start;

    private Location end;

    private Location playerLocation;

    /*
       direction of a wall prevents an entity from moving past that wall in the saved direction
       0 = right, 1 = down, 2 = left, 3 = up
    */
    private int direction;

    public Wall(Location start, Location end, int direction) {
        this.start = new Location(start.getxCord(), start.getyCord());
        this.end = new Location(end.getxCord(), end.getyCord());
        this.direction = direction;
    }

    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }

    public void setStart(int newX, int newY) {
        start.setxCord(newX);
        start.setyCord(newY);
    }

    public void setEnd(int newX, int newY) {
        end.setxCord(newX);
        end.setyCord(newY);
    }

    public boolean checkCollision(Location entityLocation, int changeX, int changeY) {
        float newX = entityLocation.getxCord() + changeX;
        float newY = entityLocation.getyCord() + changeY;
        if (direction == 0 && newX > start.getxCord() && inY(entityLocation.getyCord())) {
            return true;
        } else if (direction == 1 && newY > start.getyCord() && inX(entityLocation.getxCord())) {
            return true;
        } else if (direction == 2 && newX < start.getxCord() && inY(entityLocation.getyCord())) {
            return true;
        } else if (direction == 3 && newY < start.getyCord() && inX(entityLocation.getxCord())) {
            return true;
        }
        return false;
    }

    private boolean inX(int x) {
        if ((x > start.getxCord() && x < end.getxCord())) {
            return true;
        }
        return false;
    }

    private boolean inY(int y) {
        if ((y > start.getyCord() && y < end.getyCord())) {
            return true;
        }
        return false;
    }

    public void update(Location location) {
        playerLocation = location;
    }

}

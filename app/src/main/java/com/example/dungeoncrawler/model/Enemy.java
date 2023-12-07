package com.example.dungeoncrawler.model;


public abstract class Enemy implements Observer {

    protected int health;
    protected int defense;
    protected boolean alive;
    protected int damage;
    protected int[] movementCycleX;
    protected int[] movementCycleY;

    protected int tickCount;
    protected int damageMultiplier = 1;

    protected Location location;
    protected Location playerLocation;

    public Enemy(int health, int defense, int damage, Location location) {
        this.health = health;
        this.defense = defense;
        this.damage = damage;
        this.location = new Location(location.getxCord(), location.getyCord());
        tickCount = 0;
        alive = true;
    }

    public Enemy() {
        alive = true;
    }

    public Location getLocation() {
        return location;
    }

    public void setDamageMultiplier(int multiplier) {
        damageMultiplier = multiplier;
    }

    public void setCoords(int newX, int newY) {
        location.setxCord(newX);
        location.setyCord(newY);
    }

    public void movement() {
        location.setxCord(location.getxCord() + movementCycleX[tickCount % movementCycleX.length]);
        location.setyCord(location.getyCord() + movementCycleY[tickCount % movementCycleY.length]);

        tickCount++;
    }

    public int getDamage() {
        return damage;
    }

    public boolean checkCollision(Location entityLocation, int changeX, int changeY) {
        int newX = entityLocation.getxCord() + changeX;
        int newY = entityLocation.getyCord() + changeY;
        if (Math.abs(newX - location.getxCord()) <= 25
                && Math.abs(newY - location.getyCord()) <= 25) {
            return true;
        }
        return false;
    }

    public void update(Location location) {
        playerLocation = location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setLocation(int newX, int newY) {
        location.setxCord(newX);
        location.setyCord(newY);
    }

    public void kill() {
        alive = false;
    }

    public boolean alive() {
        return alive;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }
}

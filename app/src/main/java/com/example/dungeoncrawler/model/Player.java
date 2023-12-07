package com.example.dungeoncrawler.model;

import java.util.*;

public class Player extends Entity implements Observable {


    private static volatile Player player = new Player();

    private int health;

    private int speed;

    private int direction;

    private long score;

    private String name;

    private int character;

    // location implementation different than others. Only created when new Player created.
    //private Location location;

    private int difficulty;

    private Location location;

    private List<Observer> wallList;

    private Powerupable powerup;

    private boolean isInvulnerable;

    private Weapon weapon;

    private Player(int health, int speed, int direction, long score, String name, int difficulty) {
        super(null);
        this.health = health;
        this.speed = speed;
        this.direction = direction;
        this.score = score;
        this.name = name;
        this.location = new Location(0, 0);
        this.difficulty = difficulty;
        wallList = new ArrayList<>();
        weapon = new Sword();
        isInvulnerable = false;
    }

    private Player() {
        this(100, 10,  10, 50, "", 0);
    }


    public static Player getPlayer() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            System.out.println("Input name cannot be null!");
        } else if (name.equals("")) {
            System.out.println("Input name cannot be empty!");
        } else if (name.trim().isEmpty()) {
            System.out.println("Input name cannot only contain whitespaces!");
        } else {
            this.name = name.trim();
        }
    }

    public void setCharacter(int id) {
        character = id;
    }

    public int getCharacter() {
        return character;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean getInvulnerability() {
        return isInvulnerable;
    }

    public void setInvulnerability(boolean state) {
        isInvulnerable = state;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        if (score >= 0) {
            this.score = score;
        }
    }

    public void increaseScore(long amount) {
        this.score += amount;
    }

    public void decreaseScore(long amount) {
        this.score = Math.max(this.score - amount, 0);
    }

    public int getDifficulty() {
        return difficulty;
    }


    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        if (difficulty == 3) {
            setHealth(50);
        } else if (difficulty == 2) {
            setHealth(75);
        } else {
            setHealth(100);
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(int newX, int newY) {
        location.setxCord(newX);
        location.setyCord(newY);
    }

    public void addObserver(Observer wall) {
        wallList.add(wall);
    }

    public void removeObserver(Observer wall) {
        wallList.remove(wall);
    }

    public void removeAllObservers() {
        wallList = new ArrayList<>();
    }

    public boolean validMove(int changeX, int changeY) {
        for (Observer observer : wallList) {
            if (observer instanceof Wall) {
                if (observer.checkCollision(location, changeX, changeY)) {
                    return false;
                }
            } else if (observer instanceof Enemy) {
                if (!isInvulnerable && observer.checkCollision(location, changeX, changeY)) {
                    player.decreaseScore(health - difficulty * ((Enemy) observer).getDamage());
                    player.setHealth(health - difficulty * ((Enemy) observer).getDamage());
                }
            } else /*instance of powerup*/ {
                if (observer.checkCollision(location, changeX, changeY)) {
                    setPowerup((Powerupable) observer);
                }
            }
        }
        return true;
    }

    public void notifyObservers() {
        for (Observer o: wallList) {
            o.update(location);
        }
    }

    public void setPowerup(Powerupable powerup) {
        powerup.use();
        this.powerup = powerup;
        speed += powerup.getSpeed();
        health += powerup.getHealth();
        isInvulnerable = powerup.getInvulnerability();
        score += powerup.getScore();
    }

    public void attack(Enemy enemy) {
        if (weapon.checkEnemy(location, enemy)) {
            score += 20;
        }
    }
}
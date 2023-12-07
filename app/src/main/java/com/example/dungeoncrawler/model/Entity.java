package com.example.dungeoncrawler.model;

public abstract class Entity {
    private MovementStrategy movementStrategy;

    public Entity(MovementStrategy ms) {
        movementStrategy = ms;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void moveUp() {
        movementStrategy.moveUp();
    }
    public void moveDown() {
        movementStrategy.moveDown();
    }
    public void moveLeft() {
        movementStrategy.moveLeft();
    }
    public void moveRight() {
        movementStrategy.moveRight();
    }


    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }
}

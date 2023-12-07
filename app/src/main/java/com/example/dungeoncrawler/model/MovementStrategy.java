package com.example.dungeoncrawler.model;

public interface MovementStrategy {
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();

    int getStep();

    void setStep(int newStep);
}

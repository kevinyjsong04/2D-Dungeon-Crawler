package com.example.dungeoncrawler.model;

public class RunStrategy implements MovementStrategy {
    private int step = 50;
    private Player player = Player.getPlayer();
    public void moveUp() {
        player.getLocation().setyCord(player.getLocation().getyCord() - step);
    }
    public void moveDown() {
        player.getLocation().setyCord(player.getLocation().getyCord() + step);
    }
    public void moveLeft() {
        player.getLocation().setxCord(player.getLocation().getxCord() - step);
    }
    public void moveRight() {
        player.getLocation().setxCord(player.getLocation().getxCord() + step);
    }
    public int getStep() {
        return step;
    }

    public void setStep(int newStep) {
        step = newStep;
    }
}

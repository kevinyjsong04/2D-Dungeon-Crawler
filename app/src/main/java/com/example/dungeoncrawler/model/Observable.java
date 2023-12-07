package com.example.dungeoncrawler.model;

public interface Observable {
    public boolean validMove(int changeX, int changeY);

    public void addObserver(Observer wall);

    public void removeObserver(Observer wall);

    public void notifyObservers();

}

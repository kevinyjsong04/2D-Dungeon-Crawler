package com.example.dungeoncrawler.model;

public class MageSpawner extends Spawner {
    @Override
    protected Enemy createEnemy() {
        return new Mage();
    }
}

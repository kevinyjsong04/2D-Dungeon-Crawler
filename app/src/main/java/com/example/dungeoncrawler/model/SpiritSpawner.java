package com.example.dungeoncrawler.model;

public class SpiritSpawner extends Spawner {
    @Override
    protected Enemy createEnemy() {
        return new Spirit();
    }
}

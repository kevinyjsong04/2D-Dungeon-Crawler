package com.example.dungeoncrawler.model;

public class ScytheSkeletonSpawner extends Spawner {
    @Override
    protected Enemy createEnemy() {
        return new ScytheSkeleton();
    }
}

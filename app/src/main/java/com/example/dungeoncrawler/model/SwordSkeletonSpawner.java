package com.example.dungeoncrawler.model;

public class SwordSkeletonSpawner extends Spawner {
    @Override
    protected Enemy createEnemy() {
        return new SwordSkeleton();
    }
}

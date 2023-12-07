package com.example.dungeoncrawler;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.dungeoncrawler.model.Enemy;
import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Spawner;
import com.example.dungeoncrawler.model.SpiritSpawner;

public class WeaponTest {
    Player player = Player.getPlayer();
    Spawner spiritSpawner = new SpiritSpawner();
    @Test
    public void InRangeAttackTestXY() {
        Enemy enemy = spiritSpawner.spawnEnemy();
        player.setLocation(0, 0);
        enemy.setLocation(149, 74);
        player.attack(enemy);
        assertEquals(false, enemy.alive());
    }

    @Test
    public void OutRangeAttackTestXY() {
        Enemy enemy = spiritSpawner.spawnEnemy();
        player.setLocation(0, 0);
        enemy.setLocation(150, 75);
        player.attack(enemy);
        assertEquals(true, enemy.alive());
    }

    @Test
    public void OutRangeAttackTestX() {
        Enemy enemy = spiritSpawner.spawnEnemy();
        player.setLocation(0, 0);
        enemy.setLocation(149, 75);
        player.attack(enemy);
        assertEquals(true, enemy.alive());
    }

    @Test
    public void OutRangeAttackTestY() {
        Enemy enemy = spiritSpawner.spawnEnemy();
        player.setLocation(0, 0);
        enemy.setLocation(150, 74);
        player.attack(enemy);
        assertEquals(true, enemy.alive());
    }
}

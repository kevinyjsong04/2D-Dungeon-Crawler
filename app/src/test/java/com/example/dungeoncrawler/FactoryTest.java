package com.example.dungeoncrawler;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.dungeoncrawler.model.Enemy;
import com.example.dungeoncrawler.model.Spawner;
import com.example.dungeoncrawler.model.Mage;
import com.example.dungeoncrawler.model.MageSpawner;
import com.example.dungeoncrawler.model.Spirit;
import com.example.dungeoncrawler.model.SpiritSpawner;
import com.example.dungeoncrawler.model.SwordSkeleton;
import com.example.dungeoncrawler.model.SwordSkeletonSpawner;
import com.example.dungeoncrawler.model.ScytheSkeleton;
import com.example.dungeoncrawler.model.ScytheSkeletonSpawner;
public class FactoryTest {
    @Test
    public void spiritTest() {
        Spawner spiritSpawner = new SpiritSpawner();

        Enemy enemy = spiritSpawner.spawnEnemy();
        assertEquals(100, enemy.getHealth());
        assertEquals(5, enemy.getDamage());
        assertEquals(10, enemy.getDefense());
    }
    @Test
    public void mageTest() {
        Spawner mageSpawner = new MageSpawner();

        Enemy enemy = mageSpawner.spawnEnemy();
        assertEquals(100, enemy.getHealth());
        assertEquals(10, enemy.getDamage());
        assertEquals(10, enemy.getDefense());
    }
    @Test
    public void swordSkeletonTest() {
        Spawner swordSkeletonSpawner = new SwordSkeletonSpawner();

        Enemy enemy = swordSkeletonSpawner.spawnEnemy();
        assertEquals(100, enemy.getHealth());
        assertEquals(15, enemy.getDamage());
        assertEquals(20, enemy.getDefense());
    }
    @Test
    public void scytheSkeletonTest() {
        Spawner scytheSkeletonSpawner = new ScytheSkeletonSpawner();

        Enemy enemy = scytheSkeletonSpawner.spawnEnemy();
        assertEquals(120, enemy.getHealth());
        assertEquals(25, enemy.getDamage());
        assertEquals(10, enemy.getDefense());
    }
    @Test
    public void spiritMageTest() {
        Spawner spiritSpawner = new SpiritSpawner();
        Spawner mageSpawner = new MageSpawner();

        Enemy enemy = spiritSpawner.spawnEnemy();
        assertEquals(100, enemy.getHealth());
        assertEquals(5, enemy.getDamage());
        assertEquals(10, enemy.getDefense());

        enemy = mageSpawner.spawnEnemy();
        assertEquals(100, enemy.getHealth());
        assertEquals(10, enemy.getDamage());
        assertEquals(10, enemy.getDefense());

        enemy = spiritSpawner.spawnEnemy();
        assertFalse(enemy.getHealth() != 100);
        assertFalse(enemy.getDamage() == 15);
        assertFalse(enemy.getDefense() != 10);
    }
    @Test
    public void swordScytheSkeletonTest() {
        Spawner swordSkeletonSpawner = new SwordSkeletonSpawner();
        Spawner scytheSkeletonSpawner = new ScytheSkeletonSpawner();

        Enemy enemy = swordSkeletonSpawner.spawnEnemy();
        assertEquals(100, enemy.getHealth());
        assertEquals(15, enemy.getDamage());
        assertEquals(20, enemy.getDefense());

        enemy = scytheSkeletonSpawner.spawnEnemy();
        assertEquals(120, enemy.getHealth());
        assertEquals(25, enemy.getDamage());
        assertEquals(10, enemy.getDefense());

        enemy = swordSkeletonSpawner.spawnEnemy();
        assertFalse(enemy.getHealth() == 120);
        assertFalse(enemy.getDamage() == 50);
        assertFalse(enemy.getDefense() == 10);
    }
    @Test
    public void spiritSwordSkeletonTest() {
        Spawner spiritSpawner = new SpiritSpawner();
        Spawner swordSkeletonSpawner = new SwordSkeletonSpawner();

        Enemy enemy = swordSkeletonSpawner.spawnEnemy();
        assertEquals(100, enemy.getHealth());
        assertEquals(15, enemy.getDamage());
        assertEquals(20, enemy.getDefense());

        enemy = spiritSpawner.spawnEnemy();
        assertEquals(100, enemy.getHealth());
        assertEquals(5, enemy.getDamage());
        assertEquals(10, enemy.getDefense());

        enemy = swordSkeletonSpawner.spawnEnemy();
        assertFalse(enemy.getHealth() != 100);
        assertFalse(enemy.getDamage() == 10);
        assertFalse(enemy.getDefense() == 10);
    }
    @Test
    public void spawnerNullTest() {
        Spawner spiritSpawner = new SpiritSpawner();
        Spawner mageSpawner = new MageSpawner();
        Spawner swordSkeletonSpawner = new SwordSkeletonSpawner();
        Spawner scytheSkeletonSpawner = new ScytheSkeletonSpawner();

        assertNotNull(spiritSpawner);
        assertNotNull(mageSpawner);
        assertNotNull(swordSkeletonSpawner);
        assertNotNull(scytheSkeletonSpawner);
    }
    @Test
    public void enemyNullTest() {
        Spawner spiritSpawner = new SpiritSpawner();
        Spawner mageSpawner = new MageSpawner();
        Spawner swordSkeletonSpawner = new SwordSkeletonSpawner();
        Spawner scytheSkeletonSpawner = new ScytheSkeletonSpawner();

        Enemy enemy = spiritSpawner.spawnEnemy();
        assertNotNull(enemy);
        enemy = mageSpawner.spawnEnemy();
        assertNotNull(enemy);
        enemy = swordSkeletonSpawner.spawnEnemy();
        assertNotNull(enemy);
        enemy = scytheSkeletonSpawner.spawnEnemy();
        assertNotNull(enemy);
    }
    @Test
    public void notSameTest1() {
        Spawner spiritSpawner = new SpiritSpawner();
        Spawner mageSpawner = new MageSpawner();
        Spawner swordSkeletonSpawner = new SwordSkeletonSpawner();
        Spawner scytheSkeletonSpawner = new ScytheSkeletonSpawner();

        Enemy enemy1 = spiritSpawner.spawnEnemy();
        Enemy enemy2 = mageSpawner.spawnEnemy();
        Enemy enemy3 = swordSkeletonSpawner.spawnEnemy();
        Enemy enemy4 = scytheSkeletonSpawner.spawnEnemy();
        assertNotSame(enemy1, enemy2);
        assertNotSame(enemy1, enemy3);
        assertNotSame(enemy1, enemy4);
        assertNotSame(enemy2, enemy3);
        assertNotSame(enemy2, enemy3);
        assertNotSame(enemy3, enemy4);
    }
    @Test
    public void notSameTest2() {
        Spawner spiritSpawner = new SpiritSpawner();
        Spawner mageSpawner = new MageSpawner();
        Spawner swordSkeletonSpawner = new SwordSkeletonSpawner();
        Spawner scytheSkeletonSpawner = new ScytheSkeletonSpawner();

        Enemy enemy1 = spiritSpawner.spawnEnemy();
        Enemy enemy2 = spiritSpawner.spawnEnemy();
        Enemy enemy3 = mageSpawner.spawnEnemy();
        Enemy enemy4 = mageSpawner.spawnEnemy();
        Enemy enemy5 = swordSkeletonSpawner.spawnEnemy();
        Enemy enemy6 = swordSkeletonSpawner.spawnEnemy();
        Enemy enemy7 = scytheSkeletonSpawner.spawnEnemy();
        Enemy enemy8 = scytheSkeletonSpawner.spawnEnemy();

        assertNotSame(enemy1, enemy2);
        assertNotSame(enemy3, enemy4);
        assertNotSame(enemy5, enemy6);
        assertNotSame(enemy7, enemy8);
    }
    @Test
    public void sameTest() {
        Spawner spiritSpawner = new SpiritSpawner();
        Spawner mageSpawner = new MageSpawner();
        Spawner swordSkeletonSpawner = new SwordSkeletonSpawner();
        Spawner scytheSkeletonSpawner = new ScytheSkeletonSpawner();

        Enemy enemy1 = spiritSpawner.spawnEnemy();
        Enemy enemy2 = mageSpawner.spawnEnemy();
        Enemy enemy3 = swordSkeletonSpawner.spawnEnemy();
        Enemy enemy4 = scytheSkeletonSpawner.spawnEnemy();

        enemy1 = enemy2;
        assertSame(enemy1, enemy2);
        enemy1 = enemy3;
        assertSame(enemy1, enemy3);
        enemy1 = enemy4;
        assertSame(enemy1, enemy4);
    }
}

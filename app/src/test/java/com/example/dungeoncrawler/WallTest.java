package com.example.dungeoncrawler;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.dungeoncrawler.model.Location;
import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Wall;

public class WallTest {

    Player player = Player.getPlayer();

    @Test
    public void testRightWall() {
        Wall right = new Wall(new Location(100,0), new Location (100, 1000),
                0);
        player.addObserver(right);
        player.setLocation(0, 100);
        boolean valid = player.validMove(99,0);
        assertTrue(valid);
        valid = player.validMove(101, 0);
        assertFalse(valid);
        player.removeObserver(right);
    }

    @Test
    public void testDownWall() {
        Wall down = new Wall(new Location(0,100), new Location (200, 100),
                1);
        player.addObserver(down);
        player.setLocation(100, 50);
        boolean valid = player.validMove(0, 40);
        assertTrue(valid);
        valid = player.validMove(0, 60);
        assertFalse(valid);
        player.removeObserver(down);
    }

    @Test
    public void testLeftWall() {
        Wall left = new Wall(new Location(100,0), new Location (100, 1000),
                2);
        player.addObserver(left);
        player.setLocation(200, 200);
        boolean valid = player.validMove(-50,0);
        assertTrue(valid);
        valid = player.validMove(-101, 0);
        assertFalse(valid);
        player.removeObserver(left);
    }

    @Test
    public void testUpWall() {
        Wall up = new Wall(new Location(0,100), new Location (200, 100),
                3);
        player.addObserver(up);
        player.setLocation(100, 150);
        boolean valid = player.validMove(0,-20);
        assertTrue(valid);
        valid = player.validMove(0, -100);
        assertFalse(valid);
        player.removeObserver(up);
    }
}

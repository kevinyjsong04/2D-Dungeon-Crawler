package com.example.dungeoncrawler;

import static org.junit.Assert.assertEquals;


import com.example.dungeoncrawler.model.Player;

import com.example.dungeoncrawler.model.RunStrategy;
import com.example.dungeoncrawler.model.WalkStrategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovementTest {
    
    Player player = Player.getPlayer();
    @Test
    public void testWalkUp() {
        int x = 500;
        int y = 500;
        player.setLocation(x,y);
        player.setMovementStrategy(new WalkStrategy());
        player.moveUp();
        y-=25;
        assertEquals(player.getLocation().getxCord(),x,0);
        assertEquals(player.getLocation().getyCord(),y,0);
    }
    @Test
    public void testWalkDown() {
        int x = 500;
        int y = 500;
        player.setLocation(x, y);
        player.setMovementStrategy(new WalkStrategy());
        player.moveDown();
        y += 25;
        assertEquals(player.getLocation().getxCord(), x, 0);
        assertEquals(player.getLocation().getyCord(), y, 0);
    }
    @Test
    public void testWalkLeft() {
        int x = 500;
        int y = 500;
        player.setLocation(x, y);
        player.setMovementStrategy(new WalkStrategy());
        player.moveLeft();
        x -= 25;
        assertEquals(player.getLocation().getxCord(), x, 0);
        assertEquals(player.getLocation().getyCord(), y, 0);
    }
    @Test
    public void testWalkRight() {
        int x = 500;
        int y = 500;
        player.setLocation(x, y);
        player.setMovementStrategy(new WalkStrategy());
        player.moveRight();
        x += 25;
        assertEquals(player.getLocation().getxCord(), x, 0);
        assertEquals(player.getLocation().getyCord(), y, 0);
    }
    @Test
    public void testRunUp() {
        int x = 500;
        int y = 500;
        player.setLocation(x,y);
        player.setMovementStrategy(new RunStrategy());
        player.moveUp();
        y-=50;
        assertEquals(player.getLocation().getxCord(),x,0);
        assertEquals(player.getLocation().getyCord(),y,0);
    }
    @Test
    public void testRunDown() {
        int x = 500;
        int y = 500;
        player.setLocation(x, y);
        player.setMovementStrategy(new RunStrategy());
        player.moveDown();
        y += 50;
        assertEquals(player.getLocation().getxCord(), x, 0);
        assertEquals(player.getLocation().getyCord(), y, 0);
    }
    @Test
    public void testRunLeft() {
        int x = 500;
        int y = 500;
        player.setLocation(x, y);
        player.setMovementStrategy(new RunStrategy());
        player.moveLeft();
        x -= 50;
        assertEquals(player.getLocation().getxCord(), x, 0);
        assertEquals(player.getLocation().getyCord(), y, 0);
    }
    @Test
    public void testRunRight() {
        int x = 500;
        int y = 500;
        player.setLocation(x, y);
        player.setMovementStrategy(new RunStrategy());
        player.moveRight();
        x += 50;
        assertEquals(player.getLocation().getxCord(), x, 0);
        assertEquals(player.getLocation().getyCord(), y, 0);
    }

}

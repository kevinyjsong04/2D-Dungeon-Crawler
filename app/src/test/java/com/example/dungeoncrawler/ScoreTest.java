package com.example.dungeoncrawler;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Coin;
import com.example.dungeoncrawler.model.Chest;
import com.example.dungeoncrawler.model.Powerup;
import com.example.dungeoncrawler.model.Location;

public class ScoreTest {

    Player player = Player.getPlayer();

    @Test
    public void testIncreaseDecreaseScore() {
        player.setScore(0);
        player.increaseScore(10);
        assertEquals(10, player.getScore());
        player.increaseScore(100);
        assertEquals(110, player.getScore());
        player.decreaseScore(100);
        assertEquals(10, player.getScore());
    }

    @Test
    public void testScoreSubzero() { // tests that score cannot dip below 0
        player.setScore(0);
        assertEquals(0, player.getScore());
        player.setScore(-1);
        assertEquals(0, player.getScore());
        player.decreaseScore(10);
        assertEquals(0, player.getScore());
        player.increaseScore(20);
        assertEquals(20, player.getScore());
        player.decreaseScore(30);
        assertEquals(0, player.getScore());
    }

    @Test
    public void testModelScore() {
        player.setScore(0);

        //location is dummy value in this test
        Location location = new Location(0, 0);
        Coin coin = new Coin(location);
        player.increaseScore(coin.getCoinScore());
        assertEquals(10, player.getScore());

        player.setScore(0);
        Powerup powerup = new Powerup(location);
        player.increaseScore(powerup.getScore());
        assertEquals(50, player.getScore());

        player.setScore(0);
        Chest chest = new Chest(location);
        player.increaseScore(chest.getChestScore());
        assertTrue(player.getScore() >= 50);
        assertTrue(player.getScore() <= 100);
    }

}

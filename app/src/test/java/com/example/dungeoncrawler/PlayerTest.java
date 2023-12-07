package com.example.dungeoncrawler;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.dungeoncrawler.model.Player;

public class PlayerTest {

    Player player = Player.getPlayer();

    @Test
    public void playerFieldsTest() {
        player.setName("A");
        player.setScore(100);
        player.setDifficulty(1);
        player.setCharacter(1000000);
        player.setLocation(1, 2);
        player.setDirection(1);
        player.setHealth(100);
        player.setSpeed(10);

        assertEquals("A", player.getName());
        assertEquals(100, player.getScore());
        assertEquals(1, player.getDifficulty());
        assertEquals(1000000, player.getCharacter());
        assertEquals(1f, player.getLocation().getxCord(), 0f);
        assertEquals(2f, player.getLocation().getyCord(), 0f);
        assertEquals(1, player.getDirection());
        assertEquals(100, player.getHealth());
        assertEquals(10, player.getSpeed());
    }

    @Test
    public void testName() {
        player.setName("helloWorld");
        assertEquals("helloWorld", player.getName());
        player.setName("     ");
        assertEquals("helloWorld", player.getName());
        player.setName("");
        assertEquals("helloWorld", player.getName());
        player.setName("  hi  ");
        assertEquals("hi", player.getName());
        player.setName(null);
        assertEquals("hi", player.getName());
    }

    @Test
    public void testIllegalName() { // ensures player naming is not illegal
        player.setName("Bob");
        assertEquals("Bob", player.getName());
        player.setName(null);
        assertEquals("Bob", player.getName());
        player.setName(" ");
        assertEquals("Bob", player.getName());
        player.setName("         ");
        assertEquals("Bob", player.getName());
        player.setName("         Candace");
        assertEquals("Candace", player.getName());
        player.setName("Tracy         ");
        assertEquals("Tracy", player.getName());
    }

    @Test
    public void testDifficulty1() {
        player.setDifficulty(3);
        assertEquals(50, player.getHealth());
    }
    @Test
    public void testDifficulty2() {
        player.setDifficulty(2);
        assertEquals(75, player.getHealth());
    }
    @Test
    public void testDifficulty3() {
        player.setDifficulty(1);
        assertEquals(100, player.getHealth());
    }
}

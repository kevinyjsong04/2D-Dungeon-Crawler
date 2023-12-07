package com.example.dungeoncrawler.model;

public class Attempt implements Comparable<Attempt> {
    private String name;
    private long score;
    private String date;

    public Attempt(String name, long score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }
    @Override
    public int compareTo(Attempt attempt) {
        if (this.score > attempt.score) {
            return -1;
        } else if (this.score < attempt.score) {
            return 1;
        } else {
            return 0;
        }
    }
    @Override
    public String toString() {
        return String.format("%s: %d   %s", name, score, date);
    }
    public long getScore() {
        return score;
    }
}
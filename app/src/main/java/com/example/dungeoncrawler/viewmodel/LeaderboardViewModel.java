package com.example.dungeoncrawler.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.dungeoncrawler.model.Attempt;
import com.example.dungeoncrawler.model.Leaderboard;
public class LeaderboardViewModel extends ViewModel {
    private Leaderboard leaderboard = Leaderboard.getLeaderboard();

    public void addAttempt() {
        leaderboard.addAttempt();
    }

    public Attempt[] getAttempts() {
        return leaderboard.getAttempts();
    }

    public Attempt getMostRecent() {
        return leaderboard.getMostRecent();
    }

    public void empty() {
        leaderboard.empty();
    }
}

package com.example.dungeoncrawler.view;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeoncrawler.model.Player;
import java.util.Timer;

public class GameActivity extends AppCompatActivity {
    private int screenWidth;
    private int screenHeight;
    protected PlayerView playerView;

    private Timer timer;

    private Player player = Player.getPlayer();      //temporary solution
    //    @Override
    //    public boolean onKeyDown(int keyCode, KeyEvent event) {
    //        switch (keyCode) {
    //            case KeyEvent.KEYCODE_SHIFT_LEFT:
    //                player.setMovementStrategy(new RunStrategy());
    //                break;
    //            case KeyEvent.KEYCODE_W:
    //                if (player.validMove(0, -25))
    //                    player.moveUp();
    //                break;
    //            case KeyEvent.KEYCODE_A:
    //                if (player.validMove(-25, 0))
    //                    player.moveLeft();
    //                break;
    //            case KeyEvent.KEYCODE_S:
    //                if (player.validMove(0, 25))
    //                    player.moveDown();
    //                break;
    //            case KeyEvent.KEYCODE_D:
    //                if (player.validMove(25, 0))
    //                    player.moveRight();
    //                break;
    //            default:
    //                break;
    //        }
    //        playerView.updatePosition();
    //        return false;
    //    }
    //    @Override
    //    public boolean onKeyUp(int keyCode, KeyEvent event) {
    //        if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT) {
    //            player.setMovementStrategy(new WalkStrategy());
    //        }
    //        return false;
    //    }
    public void setPlayerView(PlayerView playerView) {
        this.playerView = playerView;
    }

}
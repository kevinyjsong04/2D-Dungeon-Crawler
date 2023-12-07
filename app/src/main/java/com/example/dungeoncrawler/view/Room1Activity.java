package com.example.dungeoncrawler.view;
import com.example.dungeoncrawler.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
//import android.graphics.RectF;
import android.graphics.BitmapFactory;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
import android.view.KeyEvent;
//import android.widget.Button;
import android.view.View;
import android.widget.TextView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
import android.os.Handler;

import androidx.lifecycle.ViewModelProvider;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
import com.example.dungeoncrawler.model.Enemy;
import com.example.dungeoncrawler.model.HealthDecorator;
import com.example.dungeoncrawler.model.Location;
import com.example.dungeoncrawler.model.Powerup;
import com.example.dungeoncrawler.model.Powerupable;
import com.example.dungeoncrawler.model.RunStrategy;
import com.example.dungeoncrawler.model.ScytheSkeletonSpawner;
import com.example.dungeoncrawler.model.Spawner;
import com.example.dungeoncrawler.model.SpeedDecorator;
import com.example.dungeoncrawler.model.SpiritSpawner;
import com.example.dungeoncrawler.model.Wall;
import com.example.dungeoncrawler.viewmodel.EnemyViewModel;
import com.example.dungeoncrawler.viewmodel.PlayerViewModel;


import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.dungeoncrawler.model.WalkStrategy;


public class Room1Activity extends GameActivity {

    private float playerX;
    private float playerY;
    private int screenWidth;
    private int screenHeight;

    private TextView score;
    private TextView health;

    private PlayerViewModel playerVM;

    private EnemyViewModel enemyVM1;

    private EnemyViewModel enemyVM2;

    private PlayerView playerView;
    private EnemyView enemyView1;
    private EnemyView enemyView2;

    private WeaponView weaponView;
    private PowerUpView powerUpView;
    private TextView temp;

    private Spawner spawner;
    private HealthDecorator healthDecorator;

    private Handler handler = new Handler();
    private Runnable update = new Runnable() {
        @Override
        public void run() {
            if (!enemyVM1.alive()) {
                enemyView1.setVisibility(View.GONE);
                playerVM.removeObserver(enemyVM1.getEnemy());
            }
            if (!enemyVM2.alive()) {
                enemyView2.setVisibility(View.GONE);
                playerVM.removeObserver(enemyVM2.getEnemy());
            }
            if (healthDecorator.isUsed()) {
                powerUpView.setVisibility(View.GONE);
                playerVM.removeObserver(healthDecorator);
            }
            enemyVM1.movement();
            enemyView1.updatePosition();
            enemyVM2.movement();
            enemyView2.updatePosition();
            score.setText("Score: " + playerVM.getScore());
            health.setText("Health: " + playerVM.getHealth());
            handler.postDelayed(this, 50);
            checkGameOver();
            checkExit();
            //Log.d("Player Location", playerVM.getLocation().getxCord() + "," +
            // playerVM.getLocation().getyCord());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamescreen);

        playerVM = new ViewModelProvider(this).get(PlayerViewModel.class);

        TextView name = findViewById(R.id.nameText);
        TextView difficulty = findViewById(R.id.difficultyText);
        health = findViewById(R.id.healthText);
        score = findViewById(R.id.scoreText);


        name.setText(playerVM.getName());
        int difficultyNum = playerVM.getDifficulty();
        if (difficultyNum == 2) {
            difficulty.setText("Medium");
        } else if (difficultyNum == 3) {
            difficulty.setText("Hard");
        } else if (difficultyNum == 1) {
            difficulty.setText("Easy");
        }
        health.setText("Health: " + playerVM.getHealth());
        score.setText("Score: " + playerVM.getScore());


        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        int character = playerVM.getCharacter();
        playerVM.setLocation(screenWidth / 2 - 100, screenHeight / 2 - 100);
        playerView = new PlayerView(this, playerVM.getLocation(),
                BitmapFactory.decodeResource(getResources(), R.drawable.knight));
        if (character == 1) {
            playerView.setSprite(BitmapFactory.decodeResource(getResources(), R.drawable.rogue));
        } else if (character == 2) {
            playerView.setSprite(BitmapFactory.decodeResource(getResources(), R.drawable.mage));
        }
        Location temp = new Location(playerVM.getLocation().getxCord(), playerVM.getLocation().getyCord());
        temp.setxCord(temp.getxCord() + 190);
        temp.setyCord(temp.getyCord() - 130);
        weaponView = new WeaponView(this, temp, BitmapFactory.decodeResource(getResources(), R.drawable.sword));

        Powerup base = new Powerup(new Location(300,600));
        healthDecorator = new HealthDecorator(base);
        temp = new Location(base.getLocation().getxCord(), base.getLocation().getyCord());
        powerUpView = new PowerUpView(this, temp, base, healthDecorator);
        powerUpView.setSprite();

        //main upper wall
        Wall wallUp1 = new Wall(new Location(0, 380), new Location(900, 380),
                3);
        playerVM.addObserver(wallUp1);
        //right upper wall
        Wall wallRight1 = new Wall(new Location(775, 360), new Location(775, 580), 0);
        playerVM.addObserver(wallRight1);
        //smaller upper wall
        Wall wallUp2 = new Wall(new Location(775, 580), new Location(1000, 580), 3);
        playerVM.addObserver(wallUp2);
        //main down wall
        Wall wallDown1 = new Wall(new Location(100, 1150), new Location(1000, 1150),
                1);
        //right lower wall
        Wall wallRight2 = new Wall(new Location(775, 950), new Location(775, 1200), 0);
        playerVM.addObserver(wallRight2);
        //smaller lower wall
        Wall wallDown2 = new Wall(new Location(775, 950), new Location(1000, 950), 1);
        playerVM.addObserver(wallDown2);
        //main lower wall
        playerVM.addObserver(wallDown1);
        //main left wall
        Wall left = new Wall(new Location(125, 380), new Location(125, 1300),
                2);
        playerVM.addObserver(left);




        playerVM.startScore();

        playerVM.setMovementStrategy(new WalkStrategy());
        ConstraintLayout gameLayout = findViewById(R.id.room1);
        super.setPlayerView(playerView);
        gameLayout.addView(super.playerView);
        gameLayout.addView(weaponView);
        gameLayout.addView(powerUpView);

        playerVM.setLocation(getIntent().getIntExtra("startx", 500), 800);
        handler.post(update);

        createEnemy(gameLayout);

        playerVM.addObserver(enemyVM1.getEnemy());
        playerVM.addObserver(enemyVM2.getEnemy());
        playerVM.addObserver(healthDecorator);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_SHIFT_LEFT:
            playerVM.setMovementStrategy(new RunStrategy());
            break;
        case KeyEvent.KEYCODE_W:
            if (playerVM.callValidMove(0, -1 * playerVM.getMovementStrategy().getStep())) {
                playerVM.moveUp();
            }
            break;
        case KeyEvent.KEYCODE_A:
            if (playerVM.callValidMove(-1 * playerVM.getMovementStrategy().getStep(), 0)) {
                playerVM.moveLeft();
            }
            break;
        case KeyEvent.KEYCODE_S:
            if (playerVM.callValidMove(0, playerVM.getMovementStrategy().getStep())) {
                playerVM.moveDown();
            }
            break;
        case KeyEvent.KEYCODE_D:
            if (playerVM.callValidMove(playerVM.getMovementStrategy().getStep(), 0)) {
                playerVM.moveRight();
            }
            break;
        case KeyEvent.KEYCODE_F:
            playerVM.attack(enemyVM1.getEnemy());
            playerVM.attack(enemyVM2.getEnemy());
            rotate();
            break;
        default:
            break;
        }
        weaponView.playerOffset(playerVM.getLocation());
        weaponView.updatePosition();
        playerView.updatePosition();
        playerVM.notifyObservers();
        return false;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT) {
            playerVM.setMovementStrategy(new WalkStrategy());
        }
        return false;
    }

    public void checkExit() {
        if (playerVM.getLocation().getxCord() > 950) {
            launchNextActivity();
        }
    }
    public void launchNextActivity() {
        Intent intent = new Intent(this, Room2Activity.class);
        intent.putExtra("startx", 50);
        startActivity(intent);
        playerVM.endScore();
        playerVM.removeAllObservers();
        handler.removeCallbacks(update);
        finish();
    }
    public void checkGameOver() {
        if (playerVM.getHealth() <= 0) {
            launchGameOver();
        }
    }
    public void launchGameOver() {
        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
        playerVM.endScore();
        playerVM.removeAllObservers();
        handler.removeCallbacks(update);
        finish();
    }
    public void createEnemy(ConstraintLayout gameLayout) {
        spawner = new SpiritSpawner();
        Location temp = new Location(500, 500);
        Enemy enemy1 = spawner.spawnEnemy();
        enemyView1 = new EnemyView(this, temp, enemy1);
        enemyVM1 = new EnemyViewModel(enemy1);
        spawner = new ScytheSkeletonSpawner();
        Location temp2 = new Location(500, 1000);
        Enemy enemy2 = spawner.spawnEnemy();
        enemyView2 = new EnemyView(this, temp2, enemy2);
        enemyVM2 = new EnemyViewModel(enemy2);
        gameLayout.addView(enemyView1);
        gameLayout.addView(enemyView2);
    }


    public void rotate() {
        weaponView.setPivotX(weaponView.getLocation().getxCord()+25);
        weaponView.setPivotY(weaponView.getLocation().getyCord() +60);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(weaponView, "rotation", 0f, 110f);
        rotate.setDuration(500);
        rotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                weaponView.setRotation(0f); // Reset to original position
            }
        });
        rotate.start();
    }
}
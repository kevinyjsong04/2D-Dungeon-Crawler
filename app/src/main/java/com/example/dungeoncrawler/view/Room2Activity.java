package com.example.dungeoncrawler.view;
import com.example.dungeoncrawler.R;
import com.example.dungeoncrawler.model.Enemy;
import com.example.dungeoncrawler.model.HealthDecorator;
import com.example.dungeoncrawler.model.Location;
import com.example.dungeoncrawler.model.MageSpawner;
import com.example.dungeoncrawler.model.MovementStrategy;
import com.example.dungeoncrawler.model.Powerup;
import com.example.dungeoncrawler.model.RunStrategy;
import com.example.dungeoncrawler.model.Spawner;
import com.example.dungeoncrawler.model.SpeedDecorator;
import com.example.dungeoncrawler.model.SwordSkeletonSpawner;
import com.example.dungeoncrawler.model.WalkStrategy;
import com.example.dungeoncrawler.model.Wall;
import com.example.dungeoncrawler.viewmodel.EnemyViewModel;
import com.example.dungeoncrawler.viewmodel.PlayerViewModel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

public class Room2Activity extends GameActivity {

    private float playerX;
    private float playerY;
    private int screenWidth;
    private int screenHeight;

    private long leftTime;

    private TextView score;
    private TextView health;

    private PlayerViewModel playerVM;

    private PlayerView playerView;
    private EnemyView enemyView1;
    private EnemyView enemyView2;

    private WeaponView weaponView;

    private EnemyViewModel enemyVM1;

    private EnemyViewModel enemyVM2;
    private SpeedDecorator speedDecorator;
    private PowerUpView powerUpView;

    private WalkStrategy room2Walk;

    private RunStrategy room2Run;

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
            if (speedDecorator.isUsed()) {
                room2Walk.setStep(100);
                playerVM.setMovementStrategy(room2Walk);
                room2Run.setStep(200);
                powerUpView.setVisibility(View.GONE);
                playerVM.removeObserver(speedDecorator);
            }
            enemyVM1.movement();
            enemyView1.updatePosition();
            enemyVM2.movement();
            enemyView2.updatePosition();
            score.setText("Score: " + playerVM.getScore());
            handler.postDelayed(this, 50);
            health.setText("Health: " + playerVM.getHealth());
            checkGameOver();
            checkExit();
            //Log.d("Player Location", playerVM.getLocation().getxCord() +
            // "," + playerVM.getLocation().getyCord());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamescreen2);

        playerVM = new ViewModelProvider(this).get(PlayerViewModel.class);
        leftTime = playerVM.getScore() * 1000;

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

        Powerup base = new Powerup(new Location(300,600));
        speedDecorator = new SpeedDecorator(base);
        Location temp = new Location(base.getLocation().getxCord(), base.getLocation().getyCord());
        powerUpView = new PowerUpView(this, temp, base, speedDecorator);
        powerUpView.setSprite();

        //upper left up wall
        Wall up1 = new Wall(new Location(-20, 600), new Location(150, 600), 3);
        playerVM.addObserver(up1);
        //upper left left wall
        Wall left1 = new Wall(new Location(150, 380), new Location(150, 600), 2);
        playerVM.addObserver(left1);
        //upper middle wall
        Wall up2 = new Wall(new Location(0, 380), new Location(1000, 380),
                3);
        playerVM.addObserver(up2);
        //upper right right wall
        Wall right1 = new Wall(new Location(775, 380), new Location(775, 600), 0);
        playerVM.addObserver(right1);
        //upper right up wall
        Wall up3 = new Wall(new Location(775, 600), new Location(1000, 600), 3);
        playerVM.addObserver(up3);

        //lower left down wall
        Wall down1 = new Wall(new Location(-20, 950), new Location(150, 950), 1);
        playerVM.addObserver(down1);
        //lower left left wall
        Wall left2 = new Wall(new Location(150, 950), new Location(150, 1175), 2);
        playerVM.addObserver(left2);
        //middle down wall
        Wall down2 = new Wall(new Location(0, 1150), new Location(1000, 1150), 1);
        playerVM.addObserver(down2);
        //lower right right wall
        Wall right2 = new Wall(new Location(775, 950), new Location(775, 1175), 0);
        playerVM.addObserver(right2);
        //lower right down wall
        Wall down3 = new Wall(new Location(775, 950), new Location(1000, 950), 1);
        playerVM.addObserver(down3);

        playerVM.startScore();
        room2Walk = new WalkStrategy();
        room2Run = new RunStrategy();
        playerVM.setMovementStrategy(room2Walk);
        ConstraintLayout gameLayout = findViewById(R.id.room2);
        super.setPlayerView(playerView);
        gameLayout.addView(super.playerView);
        playerVM.setLocation(getIntent().getIntExtra("startx", 500), 800);
        handler.post(update);
        createEnemy(gameLayout);

        playerVM.addObserver(enemyVM1.getEnemy());
        playerVM.addObserver(enemyVM2.getEnemy());
        playerVM.addObserver(speedDecorator);

        temp = new Location(playerVM.getLocation().getxCord(), playerVM.getLocation().getyCord());
        temp.setxCord(temp.getxCord() + 190);
        temp.setyCord(temp.getyCord() - 130);
        weaponView = new WeaponView(this, temp, BitmapFactory.decodeResource(getResources(), R.drawable.sword));
        gameLayout.addView(weaponView);
        gameLayout.addView(powerUpView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_SHIFT_LEFT:
            playerVM.setMovementStrategy(room2Run);
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
        //for testing health and game over
        case KeyEvent.KEYCODE_P:
            playerVM.setHealth(playerVM.getHealth() - 5);
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
        if (playerVM.getLocation().getxCord() < 0) {
            launchPreviousActivity();
        }
        if (playerVM.getLocation().getxCord() > 950) {
            launchNextActivity();
        }
    }
    public void launchPreviousActivity() {
        Intent intent = new Intent(this, Room1Activity.class);
        intent.putExtra("startx", 900);
        startActivity(intent);
        playerVM.endScore();
        handler.removeCallbacks(update);
        finish();
    }
    public void launchNextActivity() {
        room2Walk.setStep(25);
        room2Run.setStep(50);
        Intent intent = new Intent(this, Room3Activity.class);
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
        room2Walk.setStep(25);
        room2Run.setStep(50);
        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
        playerVM.endScore();
        playerVM.removeAllObservers();
        handler.removeCallbacks(update);
        finish();
    }
    public void createEnemy(ConstraintLayout gameLayout) {
        Spawner spawner = new SwordSkeletonSpawner();
        Location temp = new Location(500, 500);
        Enemy enemy1 = spawner.spawnEnemy();
        enemyView1 = new EnemyView(this, temp, enemy1);
        enemyVM1 = new EnemyViewModel(enemy1);
        spawner = new MageSpawner();
        Enemy enemy2 = spawner.spawnEnemy();
        Location temp2 = new Location(500, 1000);
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
package com.example.dungeoncrawler.view;

import com.example.dungeoncrawler.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.example.dungeoncrawler.viewmodel.PlayerViewModel;
import com.google.android.material.textfield.TextInputLayout;


public class PreGameConfiguration extends AppCompatActivity {
    private PlayerViewModel playerVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsscreen);

        playerVM = new ViewModelProvider(this).get(PlayerViewModel.class);

        Button startBtn = findViewById(R.id.beginButton);

        TextInputLayout textInputLayout = findViewById(R.id.nameInputLayout);

        RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        RadioButton easy = findViewById(R.id.easyButton);
        RadioButton medium = findViewById(R.id.mediumButton);
        RadioButton hard = findViewById(R.id.hardButton);

        ImageButton knight = findViewById(R.id.knightButton);
        ImageButton rogue = findViewById(R.id.rogueButton);
        ImageButton mage = findViewById(R.id.mageButton);

        knight.setOnClickListener(view -> {
            playerVM.setCharacter(0);
            rogue.setSelected(false);
            mage.setSelected(false);
        });

        rogue.setOnClickListener(view -> {
            playerVM.setCharacter(1);
            knight.setSelected(false);
            mage.setSelected(false);
        });

        mage.setOnClickListener(view -> {
            playerVM.setCharacter(2);
            rogue.setSelected(false);
            knight.setSelected(false);
        });


        easy.setOnClickListener(v -> {
            playerVM.setDifficulty(1);
        });
        medium.setOnClickListener(v -> {
            playerVM.setDifficulty(2);
        });
        hard.setOnClickListener(v -> {
            playerVM.setDifficulty(3);
        });

        // Set difficulty based on difficulty checked
        startBtn.setOnClickListener(v -> {
            String name = textInputLayout.getEditText().getText().toString().trim();
            boolean invalid = TextUtils.isEmpty(name)
                    || difficultyRadioGroup.getCheckedRadioButtonId() == -1
                    || !playerVM.getCharSelected();

            if (!invalid) {
                playerVM.setName(name);
                playerVM.setScore(50);
                playerVM.setDifficulty(playerVM.getDifficulty());
                playerVM.setDefaultMovementStrategy();
                Intent game = new Intent(PreGameConfiguration.this, Room1Activity.class);
                startActivity(game);
                finish();
            }
        });
    }
}
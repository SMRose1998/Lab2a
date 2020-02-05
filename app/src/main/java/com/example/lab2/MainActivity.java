package com.example.lab2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.*;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private enum Weapon{
        ROCK("Rock", "Scissors"),
        PAPER("Paper", "Rock"),
        SCISSORS("Scissors","Paper");

        //Constructor
        private String id, winsAgainst;
        private Weapon(String msg, String win) { id = msg; winsAgainst = win;}

        @Override
        public String toString() { return id; }


        //Get Winner (Returns Null if Tied)
        public Boolean winsAgainst(Weapon weapon){
            if(weapon == this) return null;
            if(this.winsAgainst == weapon.id) return true;
            return false;
        }

        //Get Random Weapon
        public static Weapon getRandomWeapon(){
            Random r = new Random();
            Weapon[] weaponslist = Weapon.values();

            return weaponslist[r.nextInt(weaponslist.length)];
        }
    }

    private TextView text_scoreComp, text_scorePlayer, text_playerChoice, text_computerChoice, text_results;

    private int compScore, playerScore = 0;

    private Weapon playerWeapon, compWeapon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Set Texts
        text_scoreComp = findViewById(R.id.text_scoreComp);
        text_scorePlayer = findViewById(R.id.text_scorePlayer);
        text_playerChoice = findViewById(R.id.text_playerChoice);
        text_computerChoice = findViewById(R.id.text_computerChoice);
        text_results = findViewById(R.id.text_results);

        setScoreText();
    }

    private void setScoreText(){
        String StringCompScore = "Computer: ".concat(Integer.toString(compScore));
        String StringPlayerScore = "Player: ".concat(Integer.toString(playerScore));

        text_scoreComp.setText(StringCompScore);
        text_scorePlayer.setText(StringPlayerScore);
    }

    public void onClickRock(View view){
        execute(Weapon.ROCK);
    }

    public void onClickPaper(View view){
        execute(Weapon.PAPER);
    }

    public void onClickSissors(View view){
        execute(Weapon.SCISSORS);
    }

    private void execute(Weapon w){
        playerWeapon = w;
        compWeapon = Weapon.getRandomWeapon();
        Boolean playerWins = playerWeapon.winsAgainst(compWeapon);
        if(playerWins==null) {
            tie();
        }
        else{
            if(playerWins){
                win();
            }
            else{
                lose();
            }
        }

        text_playerChoice.setText("Player Chooses ".concat(playerWeapon.id));
        text_computerChoice.setText("Computer Chooses ".concat(compWeapon.id));
        setScoreText();


    }

    private void tie(){
        text_results.setText("It's A Tie!!!");
    }
    private void win(){
        text_results.setText("Player Wins!!!");
        playerScore++;
    }
    private void lose(){
        text_results.setText("Computer Wins!!!");
        compScore++;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

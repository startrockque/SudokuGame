package activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;

import com.roume.fabien.sudokugame.R;

import outils.BoiteAOutils;
import outils.GameEngine;

public class JouerAleatoireActivity extends AppCompatActivity implements View.OnClickListener{
    private Chronometer chronometre;
    private long temps;
    private int tempsChrono;
    private boolean tourne;

    // Créer un objet chronomètre qui se gère à part ?
    // Comment afficher le temps du chrono et est ce que le temps sera bien compté ?

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.niveau);

        chronometre = findViewById(R.id.chronometre);


        GameEngine.getInstance().creerPlateau(this);

        lancerChrono();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_joker:
                relancerChrono();
                break;
            case R.id.btn_pause:
                stopperChrono();
                break;
        }
    }



    private void lancerChrono() {
        temps = SystemClock.elapsedRealtime();
        tempsChrono = -1;
        tourne = true;
        chronometre.start();
        chronometre.setFormat(getResources().getString(R.string.chronometre));
        chronometre.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                                                     public void onChronometerTick(Chronometer arg0) {
            if(tourne) {
                tempsChrono++;
            }
                chronometre.setText(BoiteAOutils.tempsEnString(tempsChrono));
            }
            }
        );

    }

    private void stopperChrono() {
        temps = SystemClock.elapsedRealtime();
        tourne = false;
        chronometre.stop();
    }

    private void relancerChrono() {
        long intervalOnPause = (SystemClock.elapsedRealtime() - temps);
        chronometre.setBase( chronometre.getBase() + intervalOnPause );
        tourne = true;
        chronometre.start();
    }
}

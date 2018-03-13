package activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;

import com.roume.fabien.sudokugame.R;

import data.NiveauDAO;
import model.Niveau;
import outils.BoiteAOutils;

/**
 * Created by Fabien on 27/02/2018.
 */

public class JouerActivity extends AppCompatActivity implements View.OnClickListener{
    private Integer idNiveau;
    private Niveau niveau;
    private NiveauDAO dao;
    private Chronometer chronometre;
    private long temps;
    private int tempsChrono;
    private boolean tourne;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.niveau);

        idNiveau = getIntent().getExtras().getInt("id");
        recupererNiveau();

        ((TextView) findViewById(R.id.txt_niveau)).setText(getResources().getString(R.string.niveau, String.valueOf(niveau.getId())));
        chronometre = findViewById(R.id.chronometre);

        lancerChrono();
    }

    private void recupererNiveau() {
        dao = new NiveauDAO(this);
        dao.open();
        niveau = dao.getNiveau(idNiveau);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_joker:
                relancerChrono();
                break;
            case R.id.btn_pause:
                stopperChrono();
                dao.sauvegarderTemps(idNiveau, tempsChrono);
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

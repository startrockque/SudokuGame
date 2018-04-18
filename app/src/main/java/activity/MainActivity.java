package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.roume.fabien.sudokugame.R;

import java.io.IOException;

import data.NiveauDAO;
import outils.SudokuGenerateur;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private NiveauDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        dao = new NiveauDAO(this);
        dao.open();
        try {
            if (baseNonExistante()) {
                dao.peuplerLaBase(this);
            } else {
                //TODO retirer ce else et faitre proprement le test et initialisation de la base de données
                dao.razBase();
                dao.peuplerLaBase(this);
            }
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    private boolean baseNonExistante() {
        try {
            // si la base existe, on n'aura pas d'erreur
            dao.getNiveau(1);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_new_game:
                // Si on clique sur le bouton "Nouvelle partie"
                startActivity(new Intent(this, ChoixNiveauActivity.class));
                /*overridePendingTransition(R.anim.right_to_center, R.anim.center_to_left);*/
                finish();
                break;
            case R.id.btn_continuer:
                // Si on clique sur le bouton "Continuer"
                Toast.makeText(this, getResources().getString(R.string.toast),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_aleatoire:
                // Si on clique sur le bouton "Mode aléatoire"
                startActivity(new Intent(this, JouerAleatoireActivity.class));
                /*overridePendingTransition(R.anim.right_to_center, R.anim.center_to_left);*/
                break;
        }
    }
}

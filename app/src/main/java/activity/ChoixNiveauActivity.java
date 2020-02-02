package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.roume.fabien.sudokugame.R;

import java.util.ArrayList;
import java.util.List;

import adapters.NiveauDTOAdapter;
import data.NiveauDAO;
import model.NiveauDTO;
import services.GererBDDService;

/**
 * Created by Fabien on 25/02/2018.
 */

public class ChoixNiveauActivity extends AppCompatActivity implements View.OnClickListener {
    private GererBDDService gererBDDService;

    private SparseArray<String> niveauxDeDifficulte;
    private Integer niveauDeDifficulte;
    private List<NiveauDTO> listeNiveaux = new ArrayList<>();
    private ListView listView;
    private NiveauDTOAdapter adapter;
    private ProgressBar iconeChargement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.choix_niveau);

        iconeChargement = findViewById(R.id.icone_chargement);
        iconeChargement.setVisibility(View.VISIBLE);

        gererBDDService = new GererBDDService();
        initialiserDifficulte();
        getListeNiveauxDTO();
        initialiserListe();

        iconeChargement.setVisibility(View.GONE);
    }

    private void initialiserListe() {
        listView = findViewById(R.id.list_choix_niveau);
        adapter = new NiveauDTOAdapter(this, listeNiveaux);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), JouerActivity.class);
            intent.putExtra("id", listeNiveaux.get(i).getId());
            startActivity(intent);
        });
    }

    private void initialiserDifficulte() {
        niveauDeDifficulte = 1;
        niveauxDeDifficulte = new SparseArray<>();
        niveauxDeDifficulte.append(1, getResources().getString(R.string.niveau_debutant));
        niveauxDeDifficulte.append(2, getResources().getString(R.string.niveau_intermediaire));
        niveauxDeDifficulte.append(3, getResources().getString(R.string.niveau_expert));
        ((TextView) findViewById(R.id.txt_difficulté_actuelle)).setText(niveauxDeDifficulte.get(niveauDeDifficulte));
    }

    public void getListeNiveauxDTO() {
        listeNiveaux.clear();
        listeNiveaux.addAll(gererBDDService.getTousNiveaux(niveauDeDifficulte));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_difficulté_moins:
                iconeChargement.setVisibility(View.VISIBLE);
                baisserDifficulte();
                adapter.notifyDataSetChanged();
                iconeChargement.setVisibility(View.GONE);
                break;
            case R.id.btn_difficulté_plus:
                iconeChargement.setVisibility(View.VISIBLE);
                augmenterDifficulte();
                adapter.notifyDataSetChanged();
                iconeChargement.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void augmenterDifficulte() {
        if (niveauDeDifficulte < 3) {
            niveauDeDifficulte++;
            getListeNiveauxDTO();
            ((TextView) findViewById(R.id.txt_difficulté_actuelle)).setText(niveauxDeDifficulte.get(niveauDeDifficulte));
        }
    }

    private void baisserDifficulte() {
        if (niveauDeDifficulte > 1) {
            niveauDeDifficulte--;
            getListeNiveauxDTO();
            ((TextView) findViewById(R.id.txt_difficulté_actuelle)).setText(niveauxDeDifficulte.get(niveauDeDifficulte));
        }
    }
}

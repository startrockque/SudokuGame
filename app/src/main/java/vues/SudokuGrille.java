package vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.roume.fabien.sudokugame.R;

import outils.GameEngine;

public class SudokuGrille extends GridView {

    private Context context;

    public SudokuGrille(final Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        SudokuGrilleAdapter grilleAdapteur = new SudokuGrilleAdapter(context);
        setAdapter(grilleAdapteur);
        
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int x = position % 9;
                int y = position / 9;

                colorerCases(x, y);
            }
        });
    }

    private void colorerCases(int x, int y) {
        Cellule[][] plateau = GameEngine.getInstance().getPlateau().getPlateau();
        for (Cellule[] c : plateau){
            for (Cellule cellule : c){
                cellule.setBackgroundColor(getResources().getColor(R.color.light_grey));
            }
        }
        for (int i = 0; i < 9; i++){
            plateau[x][i].setBackgroundColor(getResources().getColor(R.color.bleu));
            plateau[i][y].setBackgroundColor(getResources().getColor(R.color.bleu));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    class SudokuGrilleAdapter extends BaseAdapter {
        private Context context;

        public SudokuGrilleAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return 81;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return GameEngine.getInstance().getPlateau().getItem(position);
        }
    }
}

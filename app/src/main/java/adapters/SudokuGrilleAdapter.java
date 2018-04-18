package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.roume.fabien.sudokugame.R;

import outils.GameEngine;
import vues.Cellule;

public class SudokuGrilleAdapter extends BaseAdapter {
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
        View v = convertView;

        if (v==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            v = inflater.inflate(R.layout.cellule, parent, false);
        }

        int x = position % 9;
        int y = position / 9;

        Cellule cell = (Cellule)v;
        cell.setNombre(GameEngine.getInstance().getSudoku()[x][y]);

        return v;
    }
}

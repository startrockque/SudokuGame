package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roume.fabien.sudokugame.R;

import java.util.List;

import model.NiveauDTO;

/**
 * Created by Fabien on 06/03/2018.
 */

public class NiveauDTOAdapter extends BaseAdapter {
    private List<NiveauDTO> listN;
    private LayoutInflater inflater;
    private Context c = null ;


    public NiveauDTOAdapter(Context c, List<NiveauDTO> listN) {
        this.listN = listN;
        inflater = LayoutInflater.from(c);
        this.c = c;
    }

    @Override
    public int getCount() {
        return listN.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView niveau;
        TextView temps;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_choix_niveau, null);
            holder.niveau = view.findViewById(R.id.item_choix_niveau_nom);
            holder.temps = view.findViewById(R.id.item_meilleur_temps);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.niveau.setText(c.getResources().getString(R.string.niveau, String.valueOf(listN.get(i).getId())));
        holder.temps.setText(String.valueOf(listN.get(i).getMeilleurTemps()));
        return view;
    }
}

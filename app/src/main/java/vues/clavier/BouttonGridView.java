package vues.clavier;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.roume.fabien.sudokugame.R;

public class BouttonGridView extends GridView{

    public BouttonGridView(Context context, AttributeSet attrs){
        super(context, attrs);

        BouttonGridViewAdapter gridViewAdapter = new BouttonGridViewAdapter(context);
        setAdapter(gridViewAdapter);
    }

    class BouttonGridViewAdapter extends BaseAdapter{
        private Context context;

        public BouttonGridViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;

            if (v == null){
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                v = inflater.inflate(R.layout.boutton, viewGroup, false);

                Boutton boutton;
                boutton = (Boutton) v;
                boutton.setId(i);

                if (i != 9) {
                    boutton.setText(String.valueOf(i + 1));
                    boutton.setNombre(i+1);
                } else {
                    boutton.setText(getResources().getString(R.string.btn_effacer));
                    boutton.setNombre(0);
                }
                return boutton;
            }
            return v;
        }
    }
}
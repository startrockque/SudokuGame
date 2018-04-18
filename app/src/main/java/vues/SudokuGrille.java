package vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import adapters.SudokuGrilleAdapter;

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

                Toast.makeText(context, "Case choisie : x=" + x + ", y=" + y, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}

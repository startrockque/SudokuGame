package vues;

import android.content.Context;
import android.view.View;

public class ICellule extends View {

    private int value;

    public ICellule(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setValue(int n){
        value = n;
        invalidate();
    }

    public int getValue() {
        return value;
    }
}

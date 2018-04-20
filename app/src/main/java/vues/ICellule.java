package vues;

import android.content.Context;
import android.view.View;

public class ICellule extends View {

    private int value;
    public boolean modifiable = true;

    public ICellule(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setNotModifiable(){
        modifiable = false;
    }

    public void setValue(int n){
        if(modifiable)
            value = n;
        invalidate();
    }

    public void setInitValue(int value){
        this.value = value;
        invalidate();
    }

    public int getValue() {
        return value;
    }
}

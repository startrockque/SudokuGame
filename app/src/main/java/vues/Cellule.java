package vues;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.roume.fabien.sudokugame.R;

public class Cellule extends ICellule{

    private Paint mPaint;
    private int cpt;

    public Cellule (Context context) {
        super(context);
        cpt = 0;
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        dessinerNombre(canvas);
        dessinerLignes(canvas);
        if (cpt == 0)
            setBackgroundColor(getResources().getColor(R.color.light_grey));
        cpt++;
    }

    private void dessinerLignes(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }

    private void dessinerNombre(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize( 60 );
        mPaint.setStyle(Paint.Style.FILL);

        Rect bounds = new Rect();
        mPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);

        if (getValue()!= 0)
            canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width())/2, (getHeight() + bounds.height())/2, mPaint);
    }



}

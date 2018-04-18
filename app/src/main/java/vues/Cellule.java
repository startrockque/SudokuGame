package vues;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Cellule extends View{

    private Paint mPaint;
    private int nombre;

    public Cellule(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setNombre(int n){
        nombre = n;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize( 60 );

        Rect bounds = new Rect();
        mPaint.getTextBounds(String.valueOf(nombre), 0, String.valueOf(nombre).length(), bounds);

        canvas.drawText(String.valueOf(nombre), (getWidth() - bounds.width())/2, (getHeight() + bounds.height())/2, mPaint);
    }
}

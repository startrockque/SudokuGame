package vues.clavier;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import outils.GameEngine;

public class Boutton extends Button implements View.OnClickListener {

    private int nombre;

    public Boutton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        GameEngine.getInstance().setNombre(nombre);
    }

    public void setNombre(int nombre){
        this.nombre = nombre;
    }

}

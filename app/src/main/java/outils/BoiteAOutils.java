package outils;

import com.google.gson.Gson;

import model.Plateau;

/**
 * Created by Fabien on 02/03/2018.
 */

public class BoiteAOutils {

    public static Plateau stringAPlateau(String string){
        Plateau plateau = new Gson().fromJson(string, Plateau.class);
        return plateau;
    }

    public static String tempsEnString(long temps){
        int h = 0;
        int m = 0;
        int s = 0;

        m = (int) (temps % 3600) / 60;
        s = (int) (temps % 60);
        if (temps > 3599){
            h = (int) (temps / 3600);
            return h + ":" + m +":" + s;
        }
        return m+":"+s;
    }
}

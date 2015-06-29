package com.morlune.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by AntonioJoaquín on 26/06/2015.
 */
public class Preferencias {

    private Preferences preferences; //variable donde guardaremos nuestras configuraciones

    public Preferencias(){
        preferences = Gdx.app.getPreferences("Alien_Attack_Pref"); //creamos o abrimos el fichero "Alien_Attack_Pref" donde almacenamos los datos
    }

    public Preferences getPreferences(){return preferences;} //devolvemos la variable preferences

    public void setPreferences(ActionPlay actionPlay){preferences.putInteger("puntuacionMaxima", actionPlay.getPuntuacionMaxima());} //realizamos el proceso de almacenamiento
}

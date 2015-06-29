package com.morlune.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by AntonioJoaquín on 28/06/2015.
 */
public class MusicAndSound {
    private Sound damageSound;

    public void musicPlay(){
        //Instancia la música.
        Music musica = Gdx.audio.newMusic(Gdx.files.internal("Music/birds.wav"));
        //La pone en bucle continuo
        musica.setLooping(true);
        //La pone en play.
        musica.play();
    }

    public void damageSound(){
        //Instancia el sonido de daño
        damageSound = Gdx.audio.newSound(Gdx.files.internal("Sound/damageSound.mp3"));
        damageSound.play();
    }
}

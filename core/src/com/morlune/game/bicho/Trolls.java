package com.morlune.game.bicho;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by AntonioJoaquín on 25/06/2015.
 */
public class Trolls extends ActorBicho{

    public Trolls(){
        //pasamos a la clase superior el sprite del troll, sus puntos de daño, puntos de muerte, velocidad de movimiento
        super(new Sprite(new Texture("personaje2.png")), 20, 20, 90);
    }
}

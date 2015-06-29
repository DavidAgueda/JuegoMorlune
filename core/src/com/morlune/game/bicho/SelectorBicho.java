package com.morlune.game.bicho;

import java.util.Random;

/**
 * Created by AntonioJoaquín on 25/06/2015.
 */
public class SelectorBicho implements ISelectorBicho{

    public ActorBicho selection(){
        ActorBicho bicho = null;
        Random rnd = new Random();

        switch (rnd.nextInt(2)){
            case 0:
                bicho = new Goblins();
                break;
            case 1:
                bicho = new Trolls();
                break;
            default:
                bicho = null;
        }
        return bicho;
    }
}

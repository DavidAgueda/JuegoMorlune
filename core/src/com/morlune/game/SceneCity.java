package com.morlune.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by AntonioJoaqu�n on 20/06/2015.
 */
public class SceneCity {

    //declaramos las variables:
    //textura que tendr� la ciudad
    private Texture cityTexture;
    //la ciudad como objeto f�sico
    private Sprite city;
    //�rea de colisi�n de la ciudad
    private Rectangle rectangleCity;
    //vida de la ciudad
    private int cityHealth;
    //enum de los estados de la ciudad
    Texture[] towns = {new Texture("casa.png"), new Texture("casa_med.png"), new Texture("casa_null.png") };

    public SceneCity(int height, int width){
        cityTexture = new Texture("casa.png");
        // instanciar sprint ciudad
        city = new Sprite(cityTexture);
        // Dar posicion a sprint ciudad
        city.setPosition((width/2)-(city.getWidth())/2, (height/2)-(city.getHeight())/2);
        // instanciar rectangulo colision ciudad
        rectangleCity = new Rectangle((width/2)-city.getWidth()/2,(height/2)-city.getHeight()/2, city.getWidth(), city.getHeight());
        //inicializamos la vida de la ciudad
        cityHealth = 100;
    }

    //m�todo para devolver la zona de colisi�n de la ciudad
    public Rectangle getRectangleCity(){return rectangleCity;}

    //m�todo para devolver el Sprite de la ciudad
    public Sprite getSpriteCity(){return city;}

    //obtener vida
    public int getCityHealth(){return cityHealth;}

    //cambiar la textura de la ciudad
    public void setCityTexture(int i){
        city.setTexture(towns[i]);
    }

    //m�todo de restar vida
    public int loseHealth(int damage){
        cityHealth -= damage;
        return cityHealth;
    }
    //
    public void show(SpriteBatch lienzo) {city.draw(lienzo);}
}
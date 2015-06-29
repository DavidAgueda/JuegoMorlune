package com.morlune.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by AntonioJoaquín on 20/06/2015.
 */
public class SceneCity {

    //constante para inicializar la vida
    private final int HEALTH = 200;

    //declaramos las variables:
    //textura que tendrá la ciudad, y barra de vida
    private Texture cityTexture, barHealth;
    //barra para la vida de la ciudad
    private NinePatch bar;
    //la ciudad como objeto físico
    private Sprite city;
    //área de colisión de la ciudad
    private Rectangle rectangleCity;
    //vida de la ciudad
    private int cityHealth;
    //array de los estados de la ciudad
    Texture[] towns = {new Texture("casa.png"), new Texture("casa_med.png"), new Texture("casa_null.png") };

    public SceneCity(int height, int width){
        barHealth = new Texture("path.png");
        bar = new NinePatch(new TextureRegion(barHealth, width-50, height-20, width, height), 20, 20, 10, 10);
        cityTexture = new Texture("casa.png");
        // instanciar sprint ciudad
        city = new Sprite(cityTexture);
        // Dar posicion a sprint ciudad
        city.setPosition((width/2)-(city.getWidth())/2, (height/2)-(city.getHeight())/2);
        // instanciar rectangulo colision ciudad
        rectangleCity = new Rectangle((width/2)-city.getWidth()/2,(height/2)-city.getHeight()/2, city.getWidth(), city.getHeight());
        //inicializamos la vida de la ciudad
        cityHealth = HEALTH;
    }

    //método para devolver la zona de colisión de la ciudad
    public Rectangle getRectangleCity(){return rectangleCity;}

   public NinePatch getBar(){return bar;}

    //obtener vida
    public int getCityHealth(){return cityHealth;}

    //cambiar la textura de la ciudad
    public void setCityTexture(int i){
        city.setTexture(towns[i]);
    }

    //método de restar vida
    public int loseHealth(int damage){
        cityHealth -= damage;
        return cityHealth;
    }
    //
    public void show(SpriteBatch lienzo) {city.draw(lienzo);}
}
package com.morlune.game;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.morlune.game.bicho.ActorBicho;

/*
 * - Que la pantalla no se deforme segun el tamaño de pantalla:
 * http://glovecatstudio.com/?p=900
 * - Que los bichos miren en la buena direccion
 * Obtener el angulo de rotacion: Usando el teorema de pitagoras y lo del seno y coseno 
 * - Que aparezcan donde deben aparecer los bichos
 * Hacer algoritmo a la mano antes
 * 
 * */

public class MyGdxGame implements ApplicationListener {
	private int height ;				// tamano de pantalla
	private int width;					//
	private int control;				// control para que no se repitan infinitas veces la pulsacion
	
	private Texture background; // fondo
	
	private long lastDropTime; // lapso de tiempo entre aparicion de bichos
	private long longVelocidadAparicion = 1000000000L ; // retraso entre aparicion de bichos

	private ActionPlay action;
	
	private SpriteBatch batch;		// lienzo
	private SceneCity city;	// ciudad

	private BitmapFont contadorPuntos, contadorVida, puntuacionMax;	// mostrar contador de puntos, mostrar contador de vida de la ciudad
	private int puntos, puntosCityHealth; // cantidad de puntos, puntos de vida de la ciudad

	private Rectangle RectangleRaton; 	// rectangulo de colision del Raton
	float ratonX, ratonY;				// x e y del raton

	private MusicAndSound music; //musica para pantalla de juego

	@Override
	public void create () {
		// dar valores a tamano de pantalla
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		background = new Texture("town.png"); // fondo
		
		batch = new SpriteBatch();		// lienzo
		control = 0; // poner control a cero

		action = new ActionPlay();
		
		city = new SceneCity(height, width);

		RectangleRaton = new Rectangle(ratonX, height - ratonY, 30, 30); //instanciar rectangulo colision raton
		
		// Puntos
		contadorPuntos = new BitmapFont();
		puntos = 0;

        //Vida de la ciudad
        contadorVida = new BitmapFont();
        puntosCityHealth = city.getCityHealth();

        puntuacionMax = new BitmapFont();

		music = new MusicAndSound();
		music.musicPlay();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		//Color del fondo y no se que las
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/

		//inicio del lienzo 
		batch.begin();
        // fondo
		batch.draw(background, 0, 0);

		// Puntos
		contadorPuntos.draw(batch, "Puntuacion: " + puntos, 50, height - 20);

        //Vida ciudad
        contadorVida.draw(batch, "Vida Ciudad: " + puntosCityHealth, width-300, height-20);

        //Puntuación máxima
        puntuacionMax.draw(batch, "Puntuacion Maxima: " + action.getPuntuacionMaxima(), 200, height-20);

        city.getBar().draw(batch, width-300, height-20, width-300, height-20);

		// dibujo la ciudad
		city.show(batch);

		// bucle para dibujar bichos
		Iterator<ActorBicho> iterx = action.getBichos().iterator();

		while (iterx.hasNext()) action.drawBicho(iterx, batch);


		batch.end();

		// Condiciones del juego 

		// anadir un bicho a la lista
		// si el intervalo de tiempor es mayor que long... crea un bicho 
		if (TimeUtils.nanoTime() - lastDropTime > longVelocidadAparicion) lastDropTime = action.spawnActorBicho();

		// bucle para mover y comprovar si el bicho a chocado
		Iterator<ActorBicho> iter = action.getBichos().iterator();

		while (iter.hasNext()) {
			puntos = action.movementBicho(city, iter, RectangleRaton, puntos); //puntos obtenidos al matar bichos
            try {
                puntosCityHealth = action.damage(city, puntosCityHealth); //puntos de salud de la ciudad perdidos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // se podra borra
			// si cocamos la pantalla o hacemos click con el raton
			// damos posicion aleatoria a bicho de prueba
			// ponemos control a uno
			if (Gdx.input.isTouched()) if (control == 0) control = 1;

			// si no hacemos click ponemos control a cero
			if (!Gdx.input.isTouched()) control = 0;

			// damos posicion a x e y de raton
			ratonX = Gdx.input.getX();
			ratonY = Gdx.input.getY();
			// damos posicion a x e y de rectangulo colision raton
			RectangleRaton.setX(ratonX);
			RectangleRaton.setY(ratonY);
		}
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
        puntuacionMax.dispose();
		batch.dispose();
		background.dispose();
	}

	public int getHeight(){return height;}

	public int getWidth(){return width;}

}



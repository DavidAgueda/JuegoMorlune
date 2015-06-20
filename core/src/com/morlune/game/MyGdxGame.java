package com.morlune.game;

import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/*
 * - Que la pantalla no de deforme segun el tamano de pantalla: 
 * http://glovecatstudio.com/?p=900
 * - Que los bichos miren en la buena direccion
 * Obtener el angulo de rotacion: Usando el teorema de pitagoras y lo del seno y coseno 
 * - Que aparezcan donde deben aparecer los bichos
 * Hacer algoritmo a la mano antes
 * 
 * */

public class MyGdxGame extends ApplicationAdapter {
	private int height ;				// tamano de pantalla
	private int width;					//
	private int control;				// control para que no se repitan infinitas veces la pulsacion
	
	private long lastDropTime; // lapso de tiempo entre aparicion de bichos
	private long longVelocidadAparicion = 1000000000L ; // retraso entre aparicion de bichos
	private Array<ActorBicho> Bichos; // lista que contiene bichos
	
	private SpriteBatch batch;		// lienzo
	private Texture textureCiudad;	// textura ciudad

	private Sprite miCiudad;		// Sprint ciudad;
	private Rectangle rectangleCiudad;// rectangulo de colision de Ciudad
	
	private BitmapFont ContadorPuntos;	// mostrar contador de puntos
	private int Puntos; // contidad de puntos

	
	// se podra suprimir
	private Texture textureBichoX; 	// textura bicho de prueba
	private Sprite BichoX;
	private int intAleatorioX = 0 ; // numeros aleatorios para bicho de pruba
	private int intAleatorioY = 0 ; //
	private BitmapFont stringAleatorio; // mostar texto resuldado de los aleatorios	
	private BitmapFont stringDiffPossitionCentre; // mostar texto diferencia de posicion bichoX
	
	
	private Rectangle RectangleRaton; 	// rectangulo de colision del Raton
	float ratonX, ratonY;				// x e y del raton
	
	// se podra suprimir
	private BitmapFont PosicionRaton;	// mostrar texto posicion raton	
	private String Raton;				// Estring con posicion del raton 
	
	
	@Override
	public void create () {
		// dar valores a tamano de pantalla
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		// se podra suprimir
		PosicionRaton = new BitmapFont(); // texto mostar posicion raton
		textureBichoX = new Texture("personaje1.png"); //instanciar textura Bicho de prueba
		BichoX = new Sprite(textureBichoX);

		
		stringAleatorio = new BitmapFont(); //instanciar texto aleatorio
		stringDiffPossitionCentre = new BitmapFont();
		
		batch = new SpriteBatch();		// lienzo
		Bichos = new Array<ActorBicho>(); // instanciar array
		control = 0; // poner control a cero
		
		textureCiudad= new Texture("casa.png"); // instanciar textura Ciudad
		miCiudad = new Sprite(textureCiudad); // instanciar sprint ciudad
		// Dar posicion a sprint ciudad
		miCiudad.setPosition((width/2)-(miCiudad.getWidth())/2, (height/2)-(miCiudad.getHeight())/2);
		// instanciar rectangulo colision ciudad
		rectangleCiudad = new Rectangle((width/2)-miCiudad.getWidth()/2,(height/2)-miCiudad.getHeight()/2, miCiudad.getWidth(), miCiudad.getHeight());

		RectangleRaton = new Rectangle(ratonX, height - ratonY, 30, 30); //instanciar rectangulo colision raton
		
		// Puntos
		ContadorPuntos = new BitmapFont();
		Puntos = 0;
	
	}
	
	// metodo aparecer actor bicho
	private void spawnActorBicho() {
		ActorBicho Bicho = new ActorBicho(); 	// creamos un bicho
		Bichos.add(Bicho);						// lo metemos en la arrau
		lastDropTime = TimeUtils.nanoTime();	// guardamos el momento en que lo guardamos
   	}

	@Override
	public void render () {
		//Color del fondo y no se que las
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// se podra suprimir
		Raton ="X " + ratonX + ", Y " + ratonY ; // composicion del texto de raton
		BichoX.setPosition(400, 50);

		//inicio del lienzo 
		batch.begin();
		
			// Puntos
			ContadorPuntos.draw(batch, ""+Puntos, 50,  height-20);	
		
			// se podra suprimir
			// texto de numero aleatori
			stringAleatorio.draw(batch, "Numero aleatorio X: " + intAleatorioX +" Numero aleatorio Y: " +intAleatorioY,  200, 200 );
			BichoX.draw(batch);
			PosicionRaton.draw(batch,Raton , ratonX + 10,
					height - ratonY + 10);
			stringDiffPossitionCentre.draw(batch, "Alto " +(intAleatorioY-(height/2))+" Ancho " + (intAleatorioX-(width/2))+ " Angulo "+BichoX.getRotation(), intAleatorioX, intAleatorioY );
			
						
			// dibujo la ciudad
			miCiudad.draw(batch);
			
			// bucle para dibujar bichos
			Iterator<ActorBicho> iterx = Bichos.iterator();

			while(iterx.hasNext()) {
				ActorBicho bicho = iterx.next();
				bicho.show(batch);
		
		      }
			
		batch.end();
		
		// Condiciones del juego 
		
		// anadir un bicho a la lista
		// si el intervalo de tiempor es mayor que long... crea un bicho 
		if(TimeUtils.nanoTime() - lastDropTime > longVelocidadAparicion) spawnActorBicho();
		
		// bucle para mover y comprovar si el bicho a chocado
		Iterator<ActorBicho> iter = Bichos.iterator();

		while(iter.hasNext()) {
			ActorBicho bicho = iter.next();
			bicho.movimiento(); // moviendo bicho
			
			// si colisiona borramos bicho de la lista
			if (bicho.colision(rectangleCiudad)){
				iter.remove();
         	}
			// si colisiona con el rectangulo y hacemos click lo borramos de la lista
			if (bicho.colision(RectangleRaton) && Gdx.input.isTouched()){
				iter.remove();
				Puntos = Puntos + 10;
			}
		}

		// se podra borra
		// si cocamos la pantalla o hacemos click con el raton 
		// damos posicion aleatoria a bicho de prueba
		// ponemos control a uno 
		if (Gdx.input.isTouched()) {
			if (control == 0) {
			
			intAleatorioX = Gdx.input.getX();
			intAleatorioY = height- Gdx.input.getY();

			control = 1;

			}
			
		}
		// si no hacemos click ponemos control a cero
		if (!Gdx.input.isTouched()){
			control = 0;
		}
		
		// modificar para hacer cosa util
		// rectangulo raton colisiona con rectangulo Ciudad 
		if (RectangleRaton.overlaps(rectangleCiudad)) {
			System.out.println("ciudad"); // mostramos en consola ciudad
		}
		
		// damos posicion a x e y de raton
		ratonX = Gdx.input.getX();
		ratonY = Gdx.input.getY();
		// damos posicion a x e y de rectangulo colision raton
		RectangleRaton.setX(ratonX);
		RectangleRaton.setY(ratonY);
	}

}



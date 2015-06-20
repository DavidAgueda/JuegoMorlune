package com.morlune.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/*
 * - Que los bichos miren en la buena direccion
 * Obtener el angulo de rotacion: Usando el teorema de pitagoras y lo del seno y coseno 
 * - Que aparezcan donde deben aparecer los bichos
 * Hacer algoritmo a la mano antes
 * - Que empiecen despacio y aceleren un poco
 * - Que cuando se dirijan al medio se mas prograsivo
 * En lugar de hacer 45 grados y luego recto que adecuen esto
 * */

public class ActorBicho {

	private Texture textureBicho;
	private Sprite miBicho;
	private int posicionX;
	private int posicionY;
	private int posicionInicialX ;
	private int posicionInicialY ;
	private int velocidad = 100;
//	private float velocidadX ,velocidadY;
	private Rectangle rectangleBicho;
	
	
	double angulo;
	double velocidadx ;	
	double velocidady ;
	
	int height = Gdx.graphics.getHeight(); // extraemos el alto y el ancho de la
	// ventana
	int width = Gdx.graphics.getWidth();

	public ActorBicho() {
		// instanci de textura del bicho
		textureBicho = new Texture("personaje1.png");
		// instancia de posicion x e y aleatoria
		posicionX = (int) (Math.random() * Gdx.graphics.getWidth() + 1);
		posicionY = (int) (Math.random() * Gdx.graphics.getHeight() + 1);
		
		posicionInicialX = posicionX;
		posicionInicialY = posicionY;

		// hacer que solo aparezcan en los bodes
		
		boolean condicion = true;
		do {
			posicionX = (int) (Math.random() * Gdx.graphics.getWidth() + 1);
			posicionY = (int) (Math.random() * Gdx.graphics.getHeight() + 1);
			
			if ( (posicionX > Gdx.graphics.getWidth() -5 || posicionX < 5) || (posicionY <50 || posicionY > (Gdx.graphics.getHeight() - 5)) ){
				condicion = false;
				
			}
			
		} while (condicion);
		

		// instancia de Sprite y posicion
		miBicho = new Sprite(textureBicho);
		miBicho.setPosition(posicionX, posicionY);
		// instancia de rectangulo de colision
		rectangleBicho = new Rectangle(posicionX, posicionY,
				miBicho.getWidth(), miBicho.getHeight());

		miBicho.rotate((float)orientar()+180);
	}

	
	public double orientar() {
		angulo = Math.atan2(posicionY-(height/2), posicionX-(width/2));
		angulo = Math.toDegrees(angulo);
		return angulo;
	}
	
	// dibujar el bicho
	public void show(SpriteBatch lienzo) {
		miBicho.draw(lienzo);
	}

	// movimiento del bicho y su rectangulo
	public void movimiento() {
		// hacer que se dirijan al centro

//		
//		if ((Gdx.graphics.getWidth()) *0.5 < posicionX) {
//			posicionX = posicionX
//					- (int) ((velocidad) * Gdx.graphics.getDeltaTime());
//		} else if ((Gdx.graphics.getWidth()) * 0.5 > posicionX) {
//			posicionX = posicionX
//					+ (int) (velocidad * Gdx.graphics.getDeltaTime());
//		}
//		if ((Gdx.graphics.getHeight()) *0.5 < posicionY) {
//			posicionY = posicionY
//					- (int) (velocidad * Gdx.graphics.getDeltaTime());
//		} else if ((Gdx.graphics.getHeight()) * 0.5 > posicionY) {
//			posicionY = posicionY
//					+ (int) (velocidad * Gdx.graphics.getDeltaTime());
//		}
		
		angulo = Math.atan2(posicionY-(height/2), posicionX-(width/2));
		angulo = Math.toDegrees(angulo);
		
		velocidadx = (double) Math.cos((angulo))*100 ;		
		velocidady = (double) Math.sin((angulo))*100 ;
		if(velocidadx<0){velocidadx=velocidadx/-1;}
		if(velocidady<0){velocidady=velocidady/-1;}
		if ((Gdx.graphics.getWidth()) *0.5 < posicionX) {
			posicionX = posicionX - (int) ((velocidadx) * Gdx.graphics.getDeltaTime());
			
		} else if ((Gdx.graphics.getWidth()) * 0.5 > posicionX) {
			posicionX = posicionX + (int) (velocidadx * Gdx.graphics.getDeltaTime());
		}
		if ((Gdx.graphics.getHeight()) *0.5 < posicionY) {
			posicionY = posicionY - (int) (velocidady * Gdx.graphics.getDeltaTime());
			
		} else if ((Gdx.graphics.getHeight()) * 0.5 > posicionY) {
			posicionY = posicionY + (int) (velocidady * Gdx.graphics.getDeltaTime());
		}
		
		
		
		// Creo que la solucion esta en eso de variar la velocidad
		
		
		
//	if ((Gdx.graphics.getWidth()) / 2 < posicionX) {
//		posicionX-= (velocidad* Math.sin(Math.toRadians(orientar()))) * Gdx.graphics.getDeltaTime();
//	} else if ((Gdx.graphics.getWidth()) / 2 > posicionX) {
//		posicionX+= (velocidad* Math.sin(Math.toRadians(orientar()))) * Gdx.graphics.getDeltaTime();
//	}
//	if ((Gdx.graphics.getHeight()) / 2 < posicionY) {
//		posicionY-= (velocidad* Math.cos(Math.toRadians(orientar()))) * Gdx.graphics.getDeltaTime();
//		
//	} else if ((Gdx.graphics.getHeight()) / 2 > posicionY) {
//		posicionY+= (velocidad* Math.cos(Math.toRadians(orientar()))) * Gdx.graphics.getDeltaTime();
//		
//	}
//		
//		posicionX+= (velocidad* Math.cos(Math.toRadians(orientar()))) * Gdx.graphics.getDeltaTime();
//		posicionY+= (velocidad* Math.sin(Math.toRadians(orientar()))) * Gdx.graphics.getDeltaTime();
//		
		

		miBicho.setPosition(posicionX, posicionY);
		rectangleBicho.setX(posicionX);
		rectangleBicho.setY(Gdx.graphics.getHeight() - posicionY);

	}

	// colision del bicho con otro rectangle
	public boolean colision(Rectangle X) {
		return rectangleBicho.overlaps(X);
	}

}

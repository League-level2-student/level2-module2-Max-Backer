package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
	static final int WIDTH = 800;
	static final int HEIGHT = 800;

	/*
	 * Game variables
	 * 
	 * Put all the game variables here.
	 */
	Segment segment;
	int foodX;
	int foodY;
	int direction = UP;
	int piecesEaten;

	ArrayList<Segment> tail = new ArrayList<>();

	/*
	 * Setup methods
	 * 
	 * These methods are called at the start of the game.
	 */
	@Override
	public void settings() {
		size(WIDTH, HEIGHT);
	}

	@Override
	public void setup() {
		segment = new Segment(400, 400);
		frameRate(20);
		dropFood();
	}

	void dropFood() {
		// Set the food in a new random location
		foodX = ((int) random(50) * 10);
		foodY = ((int) random(50) * 10);
	}

	/*
	 * Draw Methods
	 * 
	 * These methods are used to draw the snake and its food
	 */

	@Override
	public void draw() {
		background(100);

		drawFood();
		move();
		drawSnake();
		eat();
	}

	void drawFood() {
		// Draw the food
		fill(200);
		rect(foodX, foodY, 10, 10);

	}

	void drawSnake() {
		// Draw the head of the snake followed by its tail
		fill(50);
		rect(segment.x, segment.y, 10, 10);
		
		manageTail();
	}

	void drawTail() {
		// Draw each segment of the tail
		for (Segment s : tail) {
			rect(s.x, s.y, 10, 10);
		}
	}
	/*
	 * Tail Management methods
	 * 
	 * These methods make sure the tail is the correct length.
	 */

	void manageTail() {
		// After drawing the tail, add a new segment at the "start" of the tail and
		// remove the one at the "end"
		// This produces the illusion of the snake tail moving.
		checkTailCollision();
		drawTail();
		
		tail.add(new Segment(segment.x, segment.y));
		tail.remove(0);
		
	}

	void checkTailCollision() {
		// If the snake crosses its own tail, shrink the tail back to one segment
		for(Segment s: tail) {
			if(segment.x == s.x && segment.y == s.y) {
				piecesEaten = 0;
				tail = new ArrayList<Segment>();
				segment.x = 400;
				segment.y = 400;
				tail.add(new Segment(segment.x, segment.y));
			}
		}


	}

	/*
	 * Control methods
	 * 
	 * These methods are used to change what is happening to the snake
	 */

	@Override
	public void keyPressed() {
		// Set the direction of the snake according to the arrow keys pressed
		if (key == CODED) {
			if (direction == UP && keyCode != DOWN) {
				direction = keyCode;
			}

			if (direction == RIGHT && keyCode != LEFT) {
				direction = keyCode;
			}

			if (direction == LEFT && keyCode != RIGHT) {
				direction = keyCode;
			}

			if (direction == DOWN && keyCode != UP) {
				direction = keyCode;
			}
		}
	}

	void move() {
		// Change the location of the Snake head based on the direction it is moving.

		if (direction == UP) {
			segment.y -= 10;

		} else if (direction == DOWN) {
			segment.y += 10;

		} else if (direction == LEFT) {
			segment.x -= 10;
		} else if (direction == RIGHT) {
			segment.x += 10;
		}
		checkBoundaries();

	}

	void checkBoundaries() {
		// If the snake leaves the frame, make it reappear on the other side
		if (segment.x > 800) {
			segment.x = 0;
		}

		if (segment.x < 0) {
			segment.x = 800;
		}

		if (segment.y > 800) {
			segment.y = 0;
		}

		if (segment.y < 0) {
			segment.y = 800;
		}

	}

	void eat() {
		// When the snake eats the food, its tail should grow and more
		// food appear
		if (segment.x == foodX && segment.y == foodY) {
			piecesEaten++;

			foodX = ((int) random(50) * 10);
			foodY = ((int) random(50) * 10);

			tail.add(new Segment(segment.x, segment.y));
		}

	}

	static public void main(String[] passedArgs) {
		PApplet.main(LeagueSnake.class.getName());
	}
}

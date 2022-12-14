/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu;

/**
 * The driver class.
 */

import edu.ilstu.monopoly.Game;

public class Main {
	/**
	 * Application entry point
	 * 
	 * @param args CLI arguments
	 * @throws Exception Generic exception
	 */
	public static void main(String[] args) throws Exception {
		// Initialize the game (get out of the static trap)
		@SuppressWarnings("unused")
		Game game = new Game();
	}
}
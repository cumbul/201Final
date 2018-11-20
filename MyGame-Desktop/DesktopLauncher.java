//LAUNCHER FOR GIT REPO

package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Game";
                //config.width = 1024;
                //config.height = 768;
                config.fullscreen = true;
                new LwjglApplication(new MyGame(), config);
	}
	//desktop launcher
	public static boolean run() {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.fullscreen = false;
			new LwjglApplication(new MyGame(), config);
			return true;
		}
}

//try power ups
//hazards
//have score show in corner
//have coin count show in corner
//have some send game state and win state thing (for sending to the other player, like is curr running, when not running, what is state)
//have you win image
//have you lose underneath the game over
//////when you lose, have a high score image

//123 power ups
//xyz hazards

//add check for if y goes below 0

//power ups: double coins (have a text thing pop up)
//invulnerability (5 seconds each)

//projectile 


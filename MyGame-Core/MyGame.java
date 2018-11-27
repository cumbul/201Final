/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;
import java.util.StringTokenizer;

import registerlogin.GuestScoresScreen;
//ADDITION
import registerlogin.ScoresScreen;
import registerlogin.StartUp;

/**
 *
 * @author Stephanie and Jasper
 */
public class MyGame implements ApplicationListener {
    //for directions and start to be shown in the beginning
    private boolean isWaiting = true;
    private float time_passed = 0.0f;
    private boolean gamePaused = true;
    
    //so that we don't instantiate more than one player
    private boolean playerExists = false;
    private float playerStartx;
    private float playerStarty;
    
    //so that player can't keep jumping forever, and we only jump when we didn't jump previously
    private boolean spacePressed = false;
    
    //===== LEVEL LOADING ===
    private float x_start_level = 0.0f; //how far we traveled is player.x minus x_start_levels
    private int curr_width = 0; //how far we've gone (because the objects will be instantiated at the same places, just shifted over some amount)
    private int which_level = 0; //what level we're currently on

    
    //===== INFINITE BACKGROUND =====
    private float bg_start = 0.0f;  //the beginning of our set of three images each time
    private float shift_1 = 0.0f;   //the shift distance of image 1 each time
    private float shift_2 = 0.0f;   //the shift distance of image 2 each time
    private float shift_3 = 0.0f;   //the shift distance of image 3 each time
    private int one_that_stays = 3; //which image is the one that will be off by one shift each time (will be the image player is on when we shift the other two)
    private float shift_time = 0.0f;    //tells us when to shift 
    
    //implementation of gravity
    public float mYSpeed = 0.0f;
    
    //===== TEXTURES AND RENDERER =====
    private SpriteBatch batch;
    private Texture blocktexture;
    private Texture bg1_texture;
    private Texture bg2_texture;
    private Texture bg3_texture;
    private Texture playertexture;
    private Texture cointexture;
    private Array<Texture> playerTextures;
    private Array<Texture> coinTextures;
    private Texture hazardTexture;
    private Texture powerUpTexture;
    //for the texts that are stored as images
    private Texture readyTextTexture;
    private Texture setTextTexture;
    private Texture goTextTexture;
    private Texture pressShiftTextTexture;
    private Texture pressSpaceTextTexture;
    
    
    //the camera view
    private OrthographicCamera camera;
    
    //===== GAME OBJECTS =====
    private Rectangle player;
    private Array<Rectangle> blocks;    //keeps track of all our blocks
    private Array<Rectangle> coins;    //keeps track of all our coins
    private Array<Rectangle> hazards;
    private Array<Rectangle> lasers;
    private Texture laserTexture;
    private boolean didClickShift = false;
    private Array<Rectangle> pitfalls;
    private Texture pitfallTexture;
   
    
    //for collisions
    private CollisionDetector cd;
    
    //===== ANIMATION TIMERS =====
    private float playerAnimTimer = 0.0f;
    private int playerCurrFrame;
    private float coinAnimTimer = 0.0f;
    private int coinCurrFrame;
    
    //the coin counter and the score counter
    int coins_collected = 0;
    int score = 0;  //score is based on how long has passed
    
    //for printing out text
    private BitmapFont font;
    
    private boolean first_iteration = true;
    boolean gameRunning = true;
    
   
    //create essentially generates the output
    @Override
    public void create() {    
        cd = new CollisionDetector(this);
        blocks = new Array<Rectangle>();
        coins = new Array<Rectangle>();

        pitfalls = new Array<Rectangle>();
        pitfallTexture = new Texture(Gdx.files.internal("lava.jpg"));

        hazards = new Array<Rectangle>();
        lasers = new Array<Rectangle>();
        laserTexture = new Texture(Gdx.files.internal("Laser.png"));
        hazardTexture = new Texture(Gdx.files.internal("Sycamore.png"));

        //wavSound = Gdx.audio.newSound(Gdx.files.internal("data/wav.wav"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        
        //set each of the player textures for our player run animation
        playerTextures = new Array<Texture>();
        for (int i = 1; i <= 10; i++)
        {
            Texture tmp = new Texture(Gdx.files.internal("millerSprites/Run" + i + ".png"));
            playerTextures.add(tmp);
        }
        
        //set each of the coin textures for our coin animation
        coinTextures = new Array<Texture>();
        for (int i = 1; i <= 16; i++)
        {
            Texture tmp = new Texture(Gdx.files.internal("Coin/coin" + i + ".png"));
            coinTextures.add(tmp);
        }
        
        //set each of our backgrounds
        bg1_texture = new Texture(Gdx.files.internal("backGroundSprites/Mid_0.png"));
        bg2_texture = new Texture(Gdx.files.internal("backGroundSprites/Mid_1.png"));
        bg3_texture = new Texture(Gdx.files.internal("backGroundSprites/Mid_2.png"));
        
        //set our block texture
        blocktexture = new Texture(Gdx.files.internal("block.png"));
        
        
        //set our text textures
        readyTextTexture = new Texture(Gdx.files.internal("Text/ready.png"));
        setTextTexture = new Texture(Gdx.files.internal("Text/set.png"));
        goTextTexture = new Texture(Gdx.files.internal("Text/go.png"));
        pressShiftTextTexture = new Texture(Gdx.files.internal("Text/press_shift.png"));
        pressSpaceTextTexture = new Texture(Gdx.files.internal("Text/press_space.png"));
        
       
        //create our player rectangle and set its hit box size
        player = new Rectangle();
        player.width = 20;
        player.height = 64;
        
        
        batch = new SpriteBatch();  
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
        
        LoadNextLevel();
    }
    
    public void LoadNextLevel()
    {         
        if(which_level > 3)
            which_level = 0;
        String levelFileName = "Level" + which_level + ".txt";
        FileHandle file = Gdx.files.internal(levelFileName);
        StringTokenizer tokens = new StringTokenizer(file.readString());
        
        for (int i = 0; i < 24; i++)
	{
            String full_line = tokens.nextToken();
            int y_pos =  24*32 - (32 * (i+1));

            for (int j = 0; j < full_line.length(); j++)
            {
                String type = Character.toString(full_line.charAt(j));
                if ("P".equals(type) && !playerExists)
                {
                    playerExists = true;
                    player.x = 64*j + curr_width;
                    player.y = y_pos;
                }
                else
                {
                    if ("*".equals(type))
                    {
                        Rectangle tmp = new Rectangle();
                        tmp.x = 64*j + curr_width;
                        tmp.y = y_pos;
                        tmp.width = 32;
                        tmp.height = 32;
                        coins.add(tmp);
                    }
                    else if("H".equals(type))
                    {
                        Rectangle tmp = new Rectangle();
                        tmp.x = 64*j + curr_width;
                        tmp.y = y_pos;
                        tmp.width = 64;
                        tmp.height = 64;
                        hazards.add(tmp);
                    }
                    else if("O".equals(type))
                    {
                        Rectangle tmp = new Rectangle();
                        tmp.x = 64*j + curr_width;
                        tmp.y = y_pos;
                        tmp.width = 64;
                        tmp.height = 32;
                        pitfalls.add(tmp);
                    }

                    else if (!".".equals(type))
                    {
                        Rectangle tmp = new Rectangle();
                        tmp.x = 64*j+curr_width;
                        tmp.y = y_pos;
                        tmp.width = 64;
                        tmp.height = 32;
                        blocks.add(tmp);
                    }
                }			
            }
	}
        curr_width += 56*64;
        if(player.x > (56*64 - (1024))) //so if player is where the next level should be (subtract 1024 bc should load before we get there
        {
            x_start_level += 56*64; //increment how we're calculating traveled distance
        }
        which_level++;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    //PROCESSINPUT()AND UPDATEGAME()
    @Override
    public void render() {        
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        float bg_y = camera.position.y /15;

        Rectangle bg1 = new Rectangle();
        bg1.width = 1280;
        bg1.height = 768;
        bg1.x = 0 + shift_1;
        bg1.y = bg_y;

        Rectangle bg2 = new Rectangle();
        bg2.width = 1280;
        bg2.height = 768;
        bg2.x = 1280 + shift_2;
        bg2.y = bg_y;

        Rectangle bg3 = new Rectangle();
        bg3.width = 1280;
        bg3.height = 768;
        bg3.x = 2560 + shift_3;
        bg3.y = bg_y;

        batch.draw(bg1_texture, bg1.x, bg1.y);
        batch.draw(bg2_texture, bg2.x, bg2.y);
        batch.draw(bg3_texture, bg3.x, bg3.y);

        for (Rectangle currBlock : pitfalls) {
            batch.draw(pitfallTexture, currBlock.x, currBlock.y);
        }

        batch.setProjectionMatrix(camera.combined);
        for (Rectangle currBlock : blocks) {
            batch.draw(blocktexture, currBlock.x, currBlock.y);
        }

        coinAnimTimer += 15.0f * Gdx.graphics.getDeltaTime();
        coinCurrFrame = Math.round(coinAnimTimer);
        //Coin animation
            if (coinCurrFrame == coinTextures.size - 1)
                coinAnimTimer -= coinTextures.size - 1;
            cointexture = coinTextures.get(coinCurrFrame);

        for (Iterator<Rectangle> iter = coins.iterator(); iter.hasNext();)
        {
            Rectangle currCoin = iter.next();
            batch.draw(cointexture, currCoin.x, currCoin.y);
        }

        
        batch.end();

        //at the beginning, we show ready, set, go (w/ controls underneath)
        if(isWaiting)
        {
            batch.begin();
            if(Gdx.input.isKeyPressed(Input.Keys.S))
            {
                gamePaused = false;
            }
            if(!gamePaused)
                time_passed += Gdx.graphics.getDeltaTime();
   
            if(time_passed > 3)
            {
                isWaiting = false;
                font.getData().setScale(2);
                
            }
            else if(time_passed < 1)
            {
                batch.draw(readyTextTexture, 150, 500, 1316*2/3, 177*2/3);
                batch.draw(pressSpaceTextTexture, 200, 425, 1316/2, 78/2);
                batch.draw(pressShiftTextTexture, 200, 350, 1316/2, 63/2);
            }
            else if(time_passed < 2 && time_passed > 1)
            {
                batch.draw(setTextTexture, 200, 500, 577, 173);
                batch.draw(pressSpaceTextTexture, 200, 425, 1316/2, 78/2);
                batch.draw(pressShiftTextTexture, 200, 350, 1316/2, 63/2);
            }
            else if(time_passed < 3 && time_passed > 2)
            {
                batch.draw(goTextTexture, 200, 500, 542, 190);
                batch.draw(pressSpaceTextTexture, 200, 425, 1316/2, 78/2);
                batch.draw(pressShiftTextTexture, 200, 350, 1316/2, 63/2);
            }
                          
            playertexture = playerTextures.get(0);
            batch.draw(playertexture, player.x, player.y);
            batch.end();
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            	SetToReadyState();
            	if(StartUp.isGuest == false)
            	{
            		System.out.println("displaying logged in user scores");
            		ScoresScreen ss = new ScoresScreen();
            	}
            	else//user is guest, show guest scores screen
            	{
            		GuestScoresScreen gss = new GuestScoresScreen();
            	}
            	//dispose();
            } ;
        }
        else
        {
            if(!gameRunning)
            {
                //so player has lost
                Rectangle lose = new Rectangle();
                Texture loseTexture = new Texture(Gdx.files.internal("gameover.png"));
                lose.width = 852;
                lose.height = 501;
                lose.x = camera.position.x - 425;
                lose.y = camera.position.y - 200;
                
                batch.begin();
                batch.draw(loseTexture, lose.x, lose.y);
                font.draw(batch, "SCORE: " + score, camera.position.x + 250, camera.position.y + 300);
                font.draw(batch, "COINS: " + coins_collected, camera.position.x + 250, camera.position.y + 275);

                batch.draw(playertexture, player.x, player.y);

                for(Rectangle l: lasers)
                {
                    batch.draw(laserTexture, l.x, l.y);
                }
                for(Rectangle h: hazards)
                {
                    batch.draw(hazardTexture, h.x, h.y);
                }

                batch.end();

                cd.BlockCollision(player, blocks);
                if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                	SetToReadyState();
                	if(StartUp.isGuest == false)
                	{
                		System.out.println("displaying logged in user scores");
                		ScoresScreen ss = new ScoresScreen();
                	}
                	else//user is guest, show guest scores screen
                	{
                		GuestScoresScreen gss = new GuestScoresScreen();
                	}
                	//hide();
                } 
            }
            else
            {
                if(first_iteration)
                {
                    camera.position.x = player.x + (1024/2) - 190;
                    first_iteration = false;
                }
                else
                    camera.position.x += 500*Gdx.graphics.getDeltaTime();

                camera.position.y = 385;
                camera.update();

                //update score 
                score += (int) (100 * Gdx.graphics.getDeltaTime());

                batch.begin();
               
                
                float theTime = Gdx.graphics.getDeltaTime();

                shift_time += theTime;
                //if(player.x - bg_start >= (1280*2 + 190))
                if(shift_time >= 5.25)
                {
                   shift_time = 0.0f;
                   if(one_that_stays == 3)
                   {
                       shift_1 += (1280*3);
                       shift_2 += (1280*3);
                   }
                   else if(one_that_stays == 2)
                   {
                       shift_1 += (1280*3);
                       shift_3 += (1280*3);
                   }
                   else
                   {
                       shift_2 += (1280*3);
                       shift_3 += (1280*3);
                   }
                   one_that_stays--;

                   if(one_that_stays <= 0)
                       one_that_stays = 3;

                   bg_start += (player.x - bg_start - 40*10*Gdx.graphics.getDeltaTime());
                }
                
               
                //Player animation
                playerAnimTimer += 15.0f * Gdx.graphics.getDeltaTime();
                playerCurrFrame = Math.round(playerAnimTimer);
                if (playerCurrFrame == playerTextures.size - 1)
                    playerAnimTimer -= playerTextures.size - 1;
                playertexture = playerTextures.get(playerCurrFrame);
                batch.draw(playertexture, player.x, player.y);

                font.draw(batch, "SCORE: " + score, camera.position.x + 250, camera.position.y + 300);
                font.draw(batch, "COINS: " + coins_collected, camera.position.x + 250, camera.position.y + 275);
                
                for(Rectangle l: lasers)
                {
                    batch.draw(laserTexture, l.x, l.y);
                }
                for(Rectangle h: hazards)
                {
                    batch.draw(hazardTexture, h.x, h.y);
                }

                batch.end();

                mYSpeed -= 2000*Gdx.graphics.getDeltaTime();
                player.y += (mYSpeed*Gdx.graphics.getDeltaTime());

                if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                	SetToReadyState();
                	if(StartUp.isGuest == false)
                	{
                		System.out.println("displaying logged in user scores");
                		ScoresScreen ss = new ScoresScreen();
                	}
                	else//user is guest, show guest scores screen
                	{
                		GuestScoresScreen gss = new GuestScoresScreen();
                	}
                	
                	//dispose();
                } 

               if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && !didClickShift)
                {
                    Rectangle tmpLaser = new Rectangle();
                    tmpLaser.x = player.x + 10;
                    tmpLaser.y = player.y + 3;
                    tmpLaser.width = 32;
                    tmpLaser.height = 32;
                    lasers.add(tmpLaser);  
                }
                didClickShift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);

                if(Gdx.input.isKeyPressed(Input.Keys.SPACE))mYSpeed = 600.0f;
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= 300 * Gdx.graphics.getDeltaTime();
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x += 100 * Gdx.graphics.getDeltaTime();
                spacePressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);
                player.x += 500 * Gdx.graphics.getDeltaTime();
                if(camera.position.x - x_start_level  >= (56*64 - (1024))) //subtract by 1024 bc should load before we get there
                    LoadNextLevel();    

                //Collision detection for each block 
                cd.BlockCollision(player, blocks);
                for (Rectangle c: coins)
                {
                    if (cd.Intersect(c, player))
                    {
                        coins.removeValue(c, true);
                        coins_collected++;
                    }
                }

                for(Rectangle h: hazards)
                {
                    if(cd.Intersect(player, h))
                    {
                        gameRunning = false;
                    }
                    if(h.x - player.x < - 1024)
                    {
                        hazards.removeValue(h, true);
                    }
                }
                for(Rectangle l: lasers)
                {
                    l.x += 1500*Gdx.graphics.getDeltaTime();
                    if(l.x > player.x + 1000)
                    {
                        lasers.removeValue(l, true);
                        System.out.println("laser went out of screen range");
                    }
                    else
                    {
                        for(Rectangle h: hazards)
                        {
                            if(cd.Intersect(l, h))
                            {
                                hazards.removeValue(h, true);
                                lasers.removeValue(l, true);
                                System.out.println("destroyed hazard");
                            }
                        } 
                    }    
                }

                //send lose message if player falls behind camera pace
                if((player.x + 600) < camera.position.x)
                {
                    System.out.println("PLAYER IS BEHIND CAMERA");
                    gameRunning = false;
                    //System.exit(0);
                }
                if(player.y < 0)
                {
                    System.out.println("Player is below screen");
                    gameRunning = false;
                }  
            }
        } 
    }

      public void SetToReadyState()
    {
    	gamePaused = true;
        coins_collected = 0;
        score = 0;
        which_level = 0;
        isWaiting = true;
        time_passed = 0.0f;
        
        x_start_level = 0.0f;
        curr_width = 0;
        which_level = 0;
        
        bg_start = 0.0f;
        shift_1 = 0.0f;
        shift_2 = 0.0f;
        shift_3 = 0.0f;
        one_that_stays = 3;
        shift_time = 0.0f;
        
        mYSpeed = 0.0f;
        
        playerAnimTimer = 0.0f;
        coinAnimTimer = 0.0f;

        didClickShift = false;
        lasers.clear();
        hazards.clear();
        
        first_iteration = true;
        gameRunning = true;
        
        player.x = playerStartx;
        player.y = playerStarty;
        camera.position.x = playerStartx + (1024/2) - 190;
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.end();
        LoadNextLevel();
       
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
    
    
}

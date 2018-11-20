/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import static java.lang.Math.abs;


/**
 *
 * @author jasper.huang
 */
public class CollisionDetector 
{
    private MyGame game;
    public CollisionDetector(MyGame game)
    {
        this.game = game;
    }
    
    public boolean Intersect(Rectangle a, Rectangle b)
    {
        Vector2 aMin = GetMin(a);
	Vector2 aMax = GetMax(a);
	Vector2 bMin = GetMin(b);
	Vector2 bMax = GetMax(b);
        
        boolean no = 
                aMax.x < bMin.x ||
		aMax.y < bMin.y ||
		bMax.x < aMin.x ||
		bMax.y < aMin.y;

	return !no;
    }
    
    public Vector2 GetMin(Rectangle a)
    {
        Vector2 min = new Vector2(a.getX() - a.width/2, a.getY());
        return min;
    }
    
    public Vector2 GetMax(Rectangle a)
    {
        Vector2 max = new Vector2(a.getX() + a.width/2, a.getY() + a.height);
        return max;
    }
    
    public void BlockCollision(Rectangle a, Array<Rectangle> blocks)
    {
	for (Rectangle b : blocks)
	{
            // Does the player collide with this block?
            if (Intersect(a, b))
            {
                //System.out.println("entered intersect");
                // Get player min/max and block min/max
                Vector2 playerMin = GetMin(a);
                Vector2 playerMax = GetMax(a);
                Vector2 blockMin = GetMin(b);
                Vector2 blockMax = GetMax(b);

                // Figure out which side we are closest to
                float dx1 = blockMin.x - playerMax.x;
                float dx2 = blockMax.x - playerMin.x;
                float dy1 = blockMin.y - playerMax.y;
                float dy2 = blockMax.y - playerMin.y;

                float dx = 0.0f;

                if (abs(dx1) < abs(dx2))
                {
                    //System.out.println("top");
                    dx = dx1;
                    game.mYSpeed = 0.0f;
                }
                else
                {
                    //System.out.println("bottom");
                    dx = dx2;
                }

                float dy = 0.0f;

                if (abs(dy1) < abs(dy2))
                {
                    //System.out.println("left");
                    dy = dy1;
                }
                else
                {
                    //System.out.println("right");
                    dy = dy2;
                }

                // Are we closer to top or side?
                if (abs(dy) < abs(dx))
                {
                    a.y += dy;
                }
                else
                {
                    a.x += dx;
                }
            }
	}
    }
}

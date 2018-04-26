
package com.CtrlAltPlay.objects;

import com.CtrlAltPlay.levels.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
public class Ground {
    
    private Vector position;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;
    
    public Ground(int x, int y)
    {
        //constructer method
        position = new Vector(x, y);
        isVisible = true;
    }
    
    //getters/setters
    public int getSpriteWidth()
    {
        return spriteWidth;
    }
    
    public int getSpriteHeight()
    {
        return spriteHeight;
    }
    
    public Vector getPosition()
    {
        return position;
    }
    
    public boolean getIsVisible(){
        return isVisible;
    }
    
    public void setIsVisible(boolean newIsVisible){
        isVisible = newIsVisible;
    }
    
    public Rectangle getBounds()
    {
        Rectangle orbRect = new Rectangle(position.getX(), position.getY(), 17280, 190);
        return orbRect;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {//used to draw the ground on screen
            g2d.fillRect((position.getX() - (playerX - screenPosition)), position.getY(), 17280, 190);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CtrlAltPlay.objects;

import com.CtrlAltPlay.levels.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Spear {
    
    private Vector position;
    private Vector displacement;
    private BufferedImage spear;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isRight;
    private boolean isCollected;
    private boolean isVisible;
    
    public Spear(int x, int y, boolean newIsRight)
    {
        //constructer method
        position = new Vector(x, y);
        displacement = new Vector(0, 0);
        isRight = newIsRight;
        try{
            spear = ImageIO.read(getClass().getResource("/Images/Spear.png"));
        }catch(Exception ex){
            System.out.println("Error loading spear sprite");
        }
        
        if(isRight == false){
            sprite = spear.getSubimage(0, 0, 145, 29);
        }else
        {
            sprite = spear.getSubimage(145, 0, 145, 29);
        }
        spriteWidth = 145;
        spriteHeight = 29;
        
        
        isCollected = false;
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
    
    public boolean getIsCollected(){
        return isCollected;
    }
    
    public void setIsCollected(boolean newIsCollected){
        isCollected = newIsCollected;
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
    
    public void doMove()
    {
        //used to update the position of the spear
        move();
        position.add(displacement);
    }
    
    private void move()
    {
        //used to move the spear depending on whether it is shot right or left
        if(isRight == true)
        {
            displacement.setX(20);
        }else
        {
            displacement.setX(-20);
        }
    }
    
    public Rectangle getBounds()
    {
        Rectangle orbRect = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
        return orbRect;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {
        //used to draw on screen
        if(isVisible == true && isCollected == false)
        {
            g2d.drawImage(sprite, (position.getX() - (playerX - screenPosition)), position.getY(), null);
        }
        
    }
    
    public void drawForMenu(Graphics2D g2d)
    {
        //used to draw for menus
        if(isVisible == true && isCollected == false)
        {
            g2d.drawImage(sprite, position.getX(), position.getY(), null);
        }
        
    }
}

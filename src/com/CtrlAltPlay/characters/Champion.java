
package com.CtrlAltPlay.characters;
import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.animation.Animation;
import com.CtrlAltPlay.characters.Orbs;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Champion {
    
    private int health;
    private Vector position;
    private Vector displacement;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    private int staticX;
    private int orbs;
    private boolean isFalling;
    private boolean isJumping;
    
    public Champion(int newLevelWidth, int newLevelHeight)
    {
        position = new Vector(newLevelWidth/2, newLevelHeight/2);
        staticX = newLevelWidth/2;
        displacement = new Vector(0, 0);
        health = 5;
        orbs = 0;
        
        isFalling = false;
        isJumping = false;
        
        try{
            sprite = ImageIO.read(getClass().getResource("/Images/Sprite-0002.png"));
        }catch(Exception ex){
            System.out.println("Error loading player sprite");
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();

    }
    
    public void setIsFalling(boolean newIsFalling)
    {
        isFalling = newIsFalling;
    }
    
    public boolean getIsFalling()
    {
        return isFalling;
    }
    
    public int getSpriteWidth()
    {
        return spriteWidth;
    }
    
    public int getSpriteHeight()
    {
        return spriteHeight;
    }
    
    public void setX(int newX)
    {
        position.setX(newX);
    }
    
    public void setY(int newY)
    {
        position.setY(newY);
    }
    
    public void setPosition(Vector v)
    {
        position.setToVector(v);
    }
    
    public int getX()
    {
        return position.getX();
    }
    
    public int getY()
    {
        return position.getY();
    }
    
    public Vector getPosition()
    {
        return position;
    }
    
    public void stopY(){
        displacement.setY(0);
    }
    
    public void stopX(){
        displacement.setX(0);
    }
    
    public void doMove(){
        if(isFalling == true){
            fall();

        }else if(isJumping == false && isFalling == false){
           this.stopY();
        }
        
        position.add(displacement);
    }
    
    public void move(int direction)
    {
        switch(direction)
        {
            case 1: // jumping
                isJumping = true;
                jump();
                break;
                
            case 2: // move left
                displacement.setX(-5);
                break;
                
            case 3: // move right
                displacement.setX(5);
                break;
                
            default:
                break;
        }
    }
    
    public void draw(Graphics2D g2d)
    {
        // used to draw the character on screen
        g2d.drawImage(sprite, staticX, position.getY(), null);
    }
    
    public void attack()
    {
        
    }
    
    public void jump()
    {
        displacement.setY(-25);
    }
    
    public void fall(){
        isJumping = false;
        displacement.addY(1);
        
        if(displacement.getY() > 7)
        {
            displacement.setY(7);
        }
    }
    
    public Rectangle getBounds()
    {
        Rectangle playerRect = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
        return playerRect;
    }
    
    public void checkCollision(Orbs[] o)
    {
        for(int i = 0; i < o.length; i++){
            if(o[i].getBounds().intersects(getBounds()) && o[i].getIsVisible() == true)
            {
                o[i].setIsVisible(false);
                if(orbs != 5)
                {
                    orbs += 1;
                }
                
            }
        }
    }
    
    public void checkCollsision(Caveman[] c)
    {
        for(int i = 0; i < c.length; i++){
            if(c[i].getBounds().intersects(getBounds()) && c[i].getIsVisible() == true)
            {
                c[i].setIsVisible(false);
                
                
            }
        }
    }
}

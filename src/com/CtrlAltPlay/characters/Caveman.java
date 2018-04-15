
package com.CtrlAltPlay.characters;

import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.objects.Ground;
import com.CtrlAltPlay.objects.Level1LargePlatform;
import com.CtrlAltPlay.objects.Level1SmallPlatform;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.CtrlAltPlay.animation.Animation;

public class Caveman {
    
    Vector position;
    Vector displacement;
    private BufferedImage spriteSheet;
    private BufferedImage sprite;
    private Animation walkR;
    private Animation walkL;
    private int spriteWidth;
    private int spriteHeight;
    private int currentMoveTime;
    private int moveTime; // the ammount of time the enemy moves in a certain direction
    private boolean moveRight; // determines whether the enemy is moving right or left
    private boolean isVisible;
    private boolean isAlive;
    private boolean isFalling;
    
    public Caveman(int newX, int newY){
        
        position = new Vector(newX, newY);
        displacement = new Vector(0, 0);
        moveTime = 0;
        currentMoveTime = 0;
        
        try{
            spriteSheet = ImageIO.read(getClass().getResource("/Images/Caveman Walking.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Chieftain image");
        }
        
        sprite = spriteSheet.getSubimage(0, 0, 128, 128);
        
        spriteWidth = 128;
        spriteHeight = 128;
        
        isAlive = true;
        isVisible = true;
        moveRight = false;
        isFalling = false;
        
        initAnimation();
    }
    
    public Caveman(int newX, int newY, int newMoveTime, boolean newMoveRight){
        
        position = new Vector(newX, newY);
        displacement = new Vector(0, 0);
        moveTime = newMoveTime;
        
        try{
            spriteSheet = ImageIO.read(getClass().getResource("/Images/Caveman Walking.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Chieftain image");
        }
        
        sprite = spriteSheet.getSubimage(0, 0, 128, 128);
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
        
        moveRight = newMoveRight;
        isAlive = true;
        isVisible = true;
        isFalling = false;
        
        initAnimation();
    }
    
    private void initAnimation()
    {
        walkR = new Animation(6, 6, spriteSheet, 1, 1, spriteWidth, spriteHeight, false);
        walkL = new Animation(6, 6, spriteSheet, 7, 1, spriteWidth, spriteHeight, true);
    }
    
    public boolean getIsVisible()
    {
        return isVisible;
    }
    
    public void setIsVisible(boolean newIsVisible)
    {
        isVisible = newIsVisible;
    }
    
    public boolean getMoveRight()
    {
        return moveRight;
    }
    
    public void setMoveRight(boolean newMoveRight)
    {
        moveRight = newMoveRight;
    }
    
    public void toggleMoveRight()
    {
        if(moveRight == true)
        {
            moveRight = false;
        }else
        {
            moveRight = true;
        }
    }
    
    public boolean getIsAlive()
    {
        return isAlive;
    }
    
    public void setIsAlive(boolean newIsAlive)
    {
        isAlive = newIsAlive;
    }
    
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
    
    public Rectangle getBounds()
    {
        Rectangle bossRect = new Rectangle(position.getX() + 25, position.getY() + 20, spriteWidth - 60, spriteHeight - 35);
        return bossRect;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {
        if(isVisible == true && isAlive == true)
        {
            g2d.drawImage(sprite, (position.getX() - (playerX - screenPosition)), position.getY(), null);
        }
        
    }
    
    public void drawForMenus(Graphics2D g2d)
    {
        if(isVisible == true && isAlive == true)
        {
            g2d.drawImage(sprite, position.getX(), position.getY(), null);
        }
        
    }
    
    private void fall(){
        displacement.addY(1);
        
        if(displacement.getY() > 7)
        {
            displacement.setY(7);
        }
    }
    
    public void doMove()
    {
        if(isVisible == true && isAlive == true)
        {
            if(isFalling == true){
            fall();

        }else if(isFalling == false){
           displacement.setY(0);
        }
            if(moveTime != 0)
            {
            move();
            }
            position.add(displacement);
        }
    }
    
    private void move()
    {
        if(currentMoveTime == moveTime){
            currentMoveTime = 0;
            toggleMoveRight();
        }
        
        if(moveRight == true)
        {
            displacement.setX(2);
            sprite = walkR.getCurrentSprite();
            walkR.run();
        }else
        {
            displacement.setX(-2);
            sprite = walkL.getCurrentSprite();
            walkL.runBackwards();
        }
        currentMoveTime += 1;
    }

    public boolean checkCollision(Level1LargePlatform[] l)
    {
        for(int i = 0; i < l.length; i++){
            if(getBounds().intersects(l[i].getBounds()))
            {
                isFalling = false;
                return true;
            }else
            {
                isFalling = true;
            }
        }
        return false;
    }
    
    public boolean checkCollision(Level1SmallPlatform[] s)
    {
        for(int i = 0; i < s.length; i++){
            if(getBounds().intersects(s[i].getBounds()))
            {
                isFalling = false;
                return true;
            }else
            {
                isFalling = true;
            }
        }
        return false;
    }
    
    public boolean checkCollision(Ground g)
    {
            if(getBounds().intersects(g.getBounds()))
            {
                isFalling = false;
                return true;
            }else
            {
                isFalling = true;
                return false;
            }
    }
    
    public boolean checkAttackCollision(Champion c)
    {
        if(c.getLeftAttackBounds().intersects(getBounds()) && c.getIsAttackingL() == true || c.getRightAttackBounds().intersects(getBounds()) && c.getIsAttackingR() == true)
        {
            isAlive = false;
        }
        return false;
    }
    
}


package com.CtrlAltPlay.characters;
import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.animation.Animation;
import com.CtrlAltPlay.objects.Ground;
import com.CtrlAltPlay.objects.Level1LargePlatform;
import com.CtrlAltPlay.objects.Orbs;
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
    private int invulnerableTimer;
    private boolean isFacingR;
    private boolean isFacingL;
    private boolean isMovingR;
    private boolean isMovingL;
    private boolean hasTakenDamage; // Used to stop the player from moving in a certain direction after they take damage
    private boolean isInvulnerable;
    private boolean isFalling;
    private boolean isJumping;
    
    public Champion(int newLevelWidth, int newLevelHeight)
    {
        position = new Vector(newLevelWidth/2, newLevelHeight/2);
        staticX = newLevelWidth/2;
        displacement = new Vector(0, 0);
        health = 5;
        invulnerableTimer = 0;
        orbs = 0;
        
        isFalling = false;
        isJumping = false;
        isInvulnerable = false;
        isMovingL = false;
        isMovingR = false;
        isFacingL = false;
        isFacingR = true;
        hasTakenDamage = false;
        try{
            sprite = ImageIO.read(getClass().getResource("/Images/Champion sprite.png"));
        }catch(Exception ex){
            System.out.println("Error loading player sprite");
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();

    }
    
    public boolean getIsMovingR()
    {
        return isMovingR;
    }
    
    public boolean getIsMovingL()
    {
        return isMovingL;
    }
    
    public void setHasTakenDamge(boolean newHasTakenDamage){
        hasTakenDamage = newHasTakenDamage;
    }
    
    public boolean getHasTakenDamge(){
        return hasTakenDamage;
    }
    
    public void setIsFalling(boolean newIsFalling)
    {
        isFalling = newIsFalling;
    }
    
    public boolean getIsFalling()
    {
        return isFalling;
    }
    
    public boolean getIsInvulnerable()
    {
        return isInvulnerable;
    }
    
    public void setIsInvulnerable(boolean newIsInvulnerable)
    {
        isInvulnerable = newIsInvulnerable;
    }
    
    public int getHealth()
    {
        return health;
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
        if(hasTakenDamage == false)
        {
            displacement.setX(0);
            isMovingL = false;
            isMovingR = false;
        }
    }
    
    public void doMove(){
        if(isFalling == true){
            fall();

        }else if(isJumping == false && isFalling == false){
           this.stopY();
        }
        
        if(isInvulnerable == true)
        {
            invulnerableTimer += 1;
            if(invulnerableTimer == 100)
            {
                isInvulnerable = false;
                invulnerableTimer = 0;
            }
        }
        
        position.add(displacement);
    }
    
    public void move(int direction)
    {
        switch(direction)
        {
            case 1: // jumping
                jump();
                break;
                
            case 2: // move left
                if(hasTakenDamage == false)
                {
                    isMovingL = true;
                    isMovingR = false;
                    isFacingL = true;
                    isFacingR = false;
                
                    displacement.setX(-5);
                }
                break;
                
            case 3: // move right
                if(hasTakenDamage == false)
                {
                    isMovingL = false;
                    isMovingR = true;
                    isFacingL = false;
                    isFacingR = true;
                
                    displacement.setX(5);
                }
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
        isJumping = true;
        displacement.setY(-25);
    }
    
    public void takeDamage(int damage)
    {
        if(isInvulnerable == false)
        {
            isJumping = true;
            displacement.setY(-15);
            if(isFacingR == true)
            {
                displacement.setX(-5);
            }else if(isFacingL == true)
            {
                displacement.setX(5);
            }
            health -= damage;
            isInvulnerable = true;
            hasTakenDamage = true;
        }
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
        Rectangle playerRect = new Rectangle(position.getX() + 25, position.getY() + 20, spriteWidth - 60, spriteHeight - 30);
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
                takeDamage(1);
            }
        }
    }
    
    public boolean checkCollision(Level1LargePlatform[] l)
    {
        for(int i = 0; i < l.length; i++){
            if(getBounds().intersects(l[i].getBounds()))
            {
                isFalling = false;
                
                if(getHasTakenDamge() == true)
                {
                    setHasTakenDamge(false);
                    stopX();
                }
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
                
                if(getHasTakenDamge() == true)
                {
                setHasTakenDamge(false);
                stopX();
                }
                return true;
            }else
            {
                setIsFalling(true);
                return false;
            }
    }
}

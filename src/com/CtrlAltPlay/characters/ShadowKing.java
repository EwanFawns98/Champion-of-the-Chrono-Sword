
package com.CtrlAltPlay.characters;

import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.objects.Ground;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.CtrlAltPlay.animation.Animation;
import com.CtrlAltPlay.objects.Level3Wall;
import com.CtrlAltPlay.sounds.Sounds;
import java.util.Random;

public class ShadowKing {
    
    Vector position;
    Vector displacement;
    private BufferedImage walkingAnim;
    private BufferedImage attackingAnim;
    private BufferedImage chargingAnim;
    private BufferedImage sprite;
    private Animation walkR;
    private Animation walkL;
    private Animation attackL;
    private Animation attackR;
    private Animation chargeR;
    private Animation chargeL;
    private int spriteWidth;
    private int spriteHeight;
    private int health;
    private int attackTimer;
    private int waitTimer;
    private int invulnerableTimer;
    private boolean isVisible;
    private boolean isAlive;
    private boolean isFalling;
    private boolean isAttackingR;
    private boolean isAttackingL;
    private boolean showAttackBoxL;
    private boolean showAttackBoxR;
    private boolean isThrowing;
    private boolean throwing;
    private final int THROW_CHANCE = 100;
    
    public ShadowKing(int newX, int newY){
        //constructer method
        position = new Vector(newX, newY);
        displacement = new Vector(0, 0);
        health = 9;
        invulnerableTimer = 0;
        waitTimer = 0;
        try{
            walkingAnim = ImageIO.read(getClass().getResource("/Images/ShadowKing_walking.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Pharaoh walking animation");
        }
        
        try{
            chargingAnim = ImageIO.read(getClass().getResource("/Images/ShadowKing_charging.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Pharaoh walking animation");
        }
        
        try{
            attackingAnim = ImageIO.read(getClass().getResource("/Images/ShadowKing_shockwave.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Pharaoh attacking animation");
        }
        
        sprite = walkingAnim.getSubimage(0, 0, 190, 252);
        
        spriteWidth = 190;
        spriteHeight = 252;
        attackTimer = 4;
        
        isAlive = true;
        isVisible = true;
        isFalling = false;
        isThrowing = false;
        throwing = false;
        isAttackingR = false;
        isAttackingL = false;
        showAttackBoxL = false;
        showAttackBoxR = false;
        initAnimation();
    }
    
    
    private void initAnimation()
    {
        //initialises animation
        walkR = new Animation(4, 6, walkingAnim, 7, 1, spriteWidth, spriteHeight, false);
        walkL = new Animation(4, 6, walkingAnim, 1, 1, spriteWidth, spriteHeight, true);
        attackR = new Animation(20, 4, attackingAnim, 5, 1, spriteWidth, spriteHeight, false);
        attackL = new Animation(20, 4, attackingAnim, 1, 1, spriteWidth, spriteHeight, true);
        chargeR = new Animation(2, 6, chargingAnim, 7, 1, spriteWidth, spriteHeight, false);
        chargeL = new Animation(2, 6, chargingAnim, 1, 1, spriteWidth, spriteHeight, true);
        
    }
    
    //getters/setters
    public boolean getIsVisible()
    {
        return isVisible;
    }
    
    public void setIsVisible(boolean newIsVisible)
    {
        isVisible = newIsVisible;
    }
    
    
    public boolean getIsAlive()
    {
        return isAlive;
    }
    
    public void setIsAlive(boolean newIsAlive)
    {
        isAlive = newIsAlive;
    }
    
    public boolean getIsThrowing()
    {
        return isThrowing;
    }
    
    public void setIsThrowing(boolean newIsThrowing)
    {
        isThrowing = newIsThrowing;
    }
    
    public boolean getThrowing()
    {
        return throwing;
    }
    
    public void setThrowing(boolean newThrowing)
    {
        throwing = newThrowing;
    }
    
    public boolean getIsAttackingR()
    {
        return isAttackingR;
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
        Rectangle bossRect = new Rectangle(position.getX() + 30, position.getY() + 110, 90, 130);
        return bossRect;
    }
    
    public Rectangle getLeftBounds()
    {
        Rectangle leftBounds = new Rectangle(position.getX() , position.getY() + 110, 50, 150);
        return leftBounds;
    }
    
    public Rectangle getRightBounds()
    {
        Rectangle rightBounds = new Rectangle(position.getX() + 140, position.getY() + 110, 50, 150);
        return rightBounds;
    }
    
    public boolean getShowAttackBoxL()
    {
        return showAttackBoxL;
    }
    
    public boolean getShowAttackBoxR()
    {
        return showAttackBoxR;
    }
    
    public void setShowAttackBoxL(boolean newShowAttackBoxL)
    {
        showAttackBoxL = newShowAttackBoxL;
    }
    
    public void setShowAttackBoxR(boolean newShowAttackBoxR)
    {
        showAttackBoxR = newShowAttackBoxR;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {// used to draw the character based on the position of the boss
        if(isVisible == true && isAlive == true)
        {
            g2d.drawImage(sprite, (position.getX() - (playerX - screenPosition)), position.getY(), null);
        }
        
    }
    
    public void fall(){
        //used to make the character fall
        displacement.addY(1);
        
        if(displacement.getY() > 7)
        {
            displacement.setY(7);
        }
    }
    
    public void doMove(int playerX)
    {
        //used to update the position of the character
        
        //used to determine if the character is falling
        if(isVisible == true && isAlive == true)
        {
            if(isFalling == true)
            {
                fall();
            }else if(isFalling == false)
            {
               displacement.setY(0);
            }
            
            move(playerX);
            if(invulnerableTimer > 0)
            {
                invulnerableTimer++;
            }
            
            if(invulnerableTimer == 50)
            {
                invulnerableTimer = 0;
            }
            //updates the position of the character
            position.add(displacement);
        }
    }
    
    private void move(int playerX)
    {
        //used to move the character
        int throwDecision;
        Random rand = new Random();
        //used to determine if the boss is using shockwave attack
        if(isThrowing == false && isAttackingL == false && isAttackingR == false)
        {
            throwDecision = rand.nextInt(THROW_CHANCE)+1;
            if(throwDecision == 1)
            {
                isThrowing = true;
            }
        }
        
        //used to move the character towards the player and if they get in range or they are using shockwave attack they will stop
        if(position.getX() > playerX + 70 && isAttackingL == false && isAttackingR == false && isThrowing == false)
        {
            displacement.setX(-8);
            sprite = walkL.getCurrentSprite();
            walkL.runBackwards();
        }else if(position.getX() + 115 < playerX && isAttackingL == false && isAttackingR == false && isThrowing == false)
        {
            displacement.setX(8);
            sprite = walkR.getCurrentSprite();
            walkR.run();
        }else if(isThrowing == true)
        {
            if(isAttackingL == false && isAttackingR == false)
            {
                checkPosition(playerX);
            }
            throwSpear();
        }else
        {
            if(isAttackingL == false && isAttackingR == false)
            {
                checkPosition(playerX);
            }
            attack();
        }
    }
    
    private void checkPosition(int playerX)
    {// used to check if the player is in front or behind the boss
        if(position.getX() + 95 > playerX + 64)
        {
            isAttackingL = true;
            
        }else
        {
            isAttackingR = true;
            
        }
    }
    
    private void attack()
    {//charge attack, moves the boss faster in the direction of the player
        
        //used to set the ammount of times the player charges off the walls
        Random rand = new Random();
        if(isAttackingL == true && attackTimer > 0)
        {
                sprite = chargeL.getCurrentSprite();
                chargeL.run();
                displacement.setX(-15);
                showAttackBoxL = true;
                
                
            
            
        }else if(isAttackingR == true && attackTimer > 0)
        {
            
                sprite = chargeR.getCurrentSprite();
                chargeR.runBackwards();
                displacement.setX(15);
                showAttackBoxR = true;
                
            
        }
        
        
        //used to create a delay to allow the player to attack the boss
        if(attackTimer == 0)
        {
            waitTimer++;
            displacement.setX(0);
            showAttackBoxL = false;
            showAttackBoxR = false;
            if(waitTimer == 200)
            {
                attackTimer = rand.nextInt(4) + 2;
                isAttackingL = false;
                isAttackingR = false;
                waitTimer = 0;
            }
        }
    }
    
    private void throwSpear()
    {
        //shockwave attack
        if(isAttackingL == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 60 && attackTimer <= 103)
            {
                sprite = attackL.getCurrentSprite();
                attackL.run();
                
                if(attackTimer == 60)
                {
                    throwing = true;
                }
                
                if(attackTimer == 60)
                {
                    Sounds.play(getClass().getResourceAsStream("/Sounds/spear-throw.wav"), false);
                }
            }
            
        }else if(isAttackingR == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 60 && attackTimer <= 103)
            {
                sprite = attackR.getCurrentSprite();
                attackR.runBackwards();
                
                if(attackTimer == 60)
                {
                    throwing = true;
                }
                
                if(attackTimer == 60)
                {
                    Sounds.play(getClass().getResourceAsStream("/Sounds/spear-throw.wav"), false);
                }
            }
        }
        
        if(attackTimer > 60)
        {
            throwing = false;
        }
        
        attackTimer++;
        displacement.setX(0);
        
        
        
        if(attackTimer == 103)
        {
            attackTimer = 0;
            isAttackingL = false;
            isAttackingR = false;
            isThrowing = false;
        }
    }
    
    //collisions between objects and characters
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
    
    public void checkCollision(Level3Wall[] w)
    {
        //used to change the direction of the charge
        for(int i = 0; i < w.length; i++)
        {
            if(getBounds().intersects(w[i].getBounds()) && attackTimer != 0)
            {
                if(isAttackingR == true)
                {
                    isAttackingR = false;
                    isAttackingL = true;
                    Sounds.play(getClass().getResourceAsStream("/Sounds/Charge attack sound.wav"), false);
                }else if(isAttackingL == true)
                {
                    isAttackingR = true;
                    isAttackingL = false;
                    Sounds.play(getClass().getResourceAsStream("/Sounds/Charge attack sound.wav"), false);
                }
                attackTimer -= 1;
                
            }
        }
    }
    
    public boolean checkAttackCollision(Champion c)
    {
        if(c.getLeftAttackBounds().intersects(getBounds()) && c.getIsAttackingL() == true && invulnerableTimer == 0 || c.getRightAttackBounds().intersects(getBounds()) && c.getIsAttackingR() == true && invulnerableTimer == 0)
        {
            invulnerableTimer++;
            health -= 1;
            if(health == 0)
            {
                isAlive = false;
                return true;
            }
        }
        return false;
    }
    
}
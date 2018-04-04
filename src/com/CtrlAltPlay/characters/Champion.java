
package com.CtrlAltPlay.characters;
import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.animation.Animation;
import com.CtrlAltPlay.objects.Ground;
import com.CtrlAltPlay.objects.HealthPickup;
import com.CtrlAltPlay.objects.Level1LargePlatform;
import com.CtrlAltPlay.objects.Level1SmallPlatform;
import com.CtrlAltPlay.objects.Level1Wall;
import com.CtrlAltPlay.objects.Level2LargePlatform;
import com.CtrlAltPlay.objects.Level2SmallPlatform;
import com.CtrlAltPlay.objects.Level2Wall;
import com.CtrlAltPlay.objects.Level3Platform;
import com.CtrlAltPlay.objects.Level3Wall;
import com.CtrlAltPlay.objects.Orbs;
import com.CtrlAltPlay.objects.Portal;
import com.CtrlAltPlay.sounds.Sounds;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Champion {
    
    private int health;
    private Vector position;
    private Vector displacement;
    private BufferedImage spriteSheet;
    private BufferedImage sprite;
    private Animation idleR;
    private Animation idleL;
    private int spriteWidth;
    private int spriteHeight;
    private int staticX;
    private int orbs;
    private int invulnerableTimer;
    private int attackTimer;
    private boolean isFacingR;
    private boolean isFacingL;
    private boolean isMovingR;
    private boolean isMovingL;
    private boolean hasTakenDamage; // Used to stop the player from moving in a certain direction after they take damage
    private boolean isInvulnerable;
    private boolean isFalling;
    private boolean isJumping;
    private boolean isAttackingL;
    private boolean isAttackingR;
    
    public Champion(int newLevelWidth, int newLevelHeight)
    {
        position = new Vector(newLevelWidth/2, newLevelHeight/2);
        staticX = newLevelWidth/2;
        displacement = new Vector(0, 0);
        health = 5;
        invulnerableTimer = 0;
        attackTimer = 0;
        orbs = 0;
        
        isFalling = false;
        isJumping = false;
        isInvulnerable = false;
        isMovingL = false;
        isMovingR = false;
        isFacingL = false;
        isFacingR = true;
        hasTakenDamage = false;
        isAttackingL = false;
        isAttackingR = false;
        
        try{
            spriteSheet = ImageIO.read(getClass().getResource("/Images/Champion Idle.png"));
        }catch(Exception ex){
            System.out.println("Error loading player sprite");
        }
        
        sprite = spriteSheet.getSubimage(0, 0, 128, 128);
        
        spriteWidth = 128;
        spriteHeight = 128;
        
        initAnimations();

    }
    
    private void initAnimations()
    {
        idleR = new Animation(30, 4, spriteSheet, 1, 1, spriteWidth, spriteHeight);
        idleL = new Animation(30, 4, spriteSheet, 5, 1, spriteWidth, spriteHeight);
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
    
    public void setIsAttackingL(boolean newIsAttacking)
    {
        isAttackingL = newIsAttacking;
    }
    
    public boolean getIsAttackingL()
    {
        return isAttackingL;
    }
    
    public void setIsAttackingR(boolean newIsAttacking)
    {
        isAttackingR = newIsAttacking;
    }
    
    public boolean getIsAttackingR()
    {
        return isAttackingR;
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
    
    public void setOrbs(int newOrbs)
    {
        orbs = newOrbs;
    }
    
    public int getOrbs()
    {
        return orbs;
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
        
        if(isAttackingL == true || isAttackingR == true)
        {
            attackTimer += 1;
            if(attackTimer == 30)
            {
                isAttackingL = false;
                isAttackingR = false;
                attackTimer = 0;
            }
        }
        
        
        if(isMovingR == false && isMovingL == false && isFacingR == true)
        {
            idleR.run();
            sprite = idleR.getCurrentSprite();
        }else if(isMovingR == false && isMovingL == false && isFacingL == true)
        {
            idleL.runBackwards();
            sprite = idleL.getCurrentSprite();
        }else{
            sprite = spriteSheet.getSubimage(0, 0, 128, 128);
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
        if(isAttackingL == false && isAttackingR == false)
        {
            if(isFacingL == true)
            {
                isAttackingL = true;
            }
            
            if(isFacingR == true)
            {
                isAttackingR = true;
            }
            
            Sounds.play(getClass().getResourceAsStream("/Sounds/sword strike.wav"), false);
        }
    }
    
    public void jump()
    {
        isJumping = true;
        displacement.setY(-25);
    }
    
    public void takeDamage(int damage)
    {
        if(isInvulnerable == false && health != 0)
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
            Sounds.play(getClass().getResourceAsStream("/Sounds/Taking damage.wav"), false);
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
    
    public Rectangle getLeftBounds()
    {
        Rectangle playerRect = new Rectangle(position.getX() + 15, position.getY() + 20, 10, spriteHeight - 40);
        return playerRect;
    }
    
    public Rectangle getRightBounds()
    {
        Rectangle playerRect = new Rectangle(position.getX() + 25 + spriteWidth - 60, position.getY() + 20, 10, spriteHeight - 40);
        return playerRect;
    }
    
    public Rectangle getLeftAttackBounds()
    {
        Rectangle playerRect = new Rectangle(position.getX() - 25, position.getY() + 20, 50, spriteHeight - 40);
        return playerRect;
    }
    
    public Rectangle getRightAttackBounds()
    {
        Rectangle playerRect = new Rectangle(position.getX() + 25 + spriteWidth - 60, position.getY() + 20, 50, spriteHeight - 40);
        return playerRect;
    }
    
    public Rectangle getHeadBounds()
    {
        Rectangle playerRect = new Rectangle(position.getX() + 25, position.getY() - 5, spriteWidth - 60, 20);
        return playerRect;
    }
    
    public void checkCollision(Orbs[] o)
    {
        for(int i = 0; i < o.length; i++){
            if(o[i].getBounds().intersects(getBounds()) && o[i].getIsCollected() == false)
            {
                o[i].setIsCollected(true);
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
            if(c[i].getBounds().intersects(getBounds()) && c[i].getIsAlive() == true)
            {
                takeDamage(1);
            }
        }
    }
    
    public void checkCollsision(Mummy[] m)
    {
        for(int i = 0; i < m.length; i++){
            if(m[i].getBounds().intersects(getBounds()) && m[i].getIsAlive() == true)
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
    
    public boolean checkCollision(Level1SmallPlatform[] s)
    {
        for(int i = 0; i < s.length; i++){
            if(getBounds().intersects(s[i].getBounds()))
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
    
    public boolean checkCollision(Level2LargePlatform[] l)
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
    
    public boolean checkCollision(Level2SmallPlatform[] s)
    {
        for(int i = 0; i < s.length; i++){
            if(getBounds().intersects(s[i].getBounds()))
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
    
    public boolean checkCollision(Level3Platform[] s)
    {
        for(int i = 0; i < s.length; i++){
            if(getBounds().intersects(s[i].getBounds()))
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
    
    public void checkCollision(HealthPickup[] h)
    {
        for(int i = 0; i < h.length; i++){
            if(h[i].getBounds().intersects(getBounds()) && h[i].getIsCollected() == false)
            {
                h[i].setIsCollected(true);
                if(health != 5)
                {
                    health += 1;
                }
                
            }
        }
    }
    
    public boolean checkCollision(Portal p)
    {
        if(p.getBounds().intersects(getBounds()))
        {
            return true;
        }
        return false;
    }
    
    public boolean checkRightCollision(Level1SmallPlatform[] s)
    {
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getBounds().intersects(getRightBounds()) && displacement.getX() > 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkRightCollision(Level1LargePlatform[] l)
    {
        for(int i = 0; i < l.length; i++)
        {
            if(l[i].getBounds().intersects(getRightBounds()) && displacement.getX() > 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkRightCollision(Level2SmallPlatform[] s)
    {
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getBounds().intersects(getRightBounds()) && displacement.getX() > 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkRightCollision(Level2LargePlatform[] l)
    {
        for(int i = 0; i < l.length; i++)
        {
            if(l[i].getBounds().intersects(getRightBounds()) && displacement.getX() > 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkRightCollision(Level3Platform[] s)
    {
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getBounds().intersects(getRightBounds()) && displacement.getX() > 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkRightCollision(Level1Wall[] w)
    {
        for(int i = 0; i < w.length; i++)
        {
            if(w[i].getBounds().intersects(getRightBounds()) && displacement.getX() > 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkRightCollision(Level2Wall[] w)
    {
        for(int i = 0; i < w.length; i++)
        {
            if(w[i].getBounds().intersects(getRightBounds()) && displacement.getX() > 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkRightCollision(Level3Wall[] w)
    {
        for(int i = 0; i < w.length; i++)
        {
            if(w[i].getBounds().intersects(getRightBounds()) && displacement.getX() > 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkLeftCollision(Level1SmallPlatform[] s)
    {
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getBounds().intersects(getLeftBounds()) && displacement.getX() < 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    
    
    public boolean checkLeftCollision(Level1LargePlatform[] l)
    {
        for(int i = 0; i < l.length; i++)
        {
            if(l[i].getBounds().intersects(getLeftBounds()) && displacement.getX() < 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkLeftCollision(Level2SmallPlatform[] s)
    {
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getBounds().intersects(getLeftBounds()) && displacement.getX() < 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    
    
    public boolean checkLeftCollision(Level2LargePlatform[] l)
    {
        for(int i = 0; i < l.length; i++)
        {
            if(l[i].getBounds().intersects(getLeftBounds()) && displacement.getX() < 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkLeftCollision(Level3Platform[] s)
    {
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getBounds().intersects(getLeftBounds()) && displacement.getX() < 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkLeftCollision(Level1Wall[] w)
    {
        for(int i = 0; i < w.length; i++)
        {
            if(w[i].getBounds().intersects(getLeftBounds()) && displacement.getX() < 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkLeftCollision(Level2Wall[] w)
    {
        for(int i = 0; i < w.length; i++)
        {
            if(w[i].getBounds().intersects(getLeftBounds()) && displacement.getX() < 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkLeftCollision(Level3Wall[] w)
    {
        for(int i = 0; i < w.length; i++)
        {
            if(w[i].getBounds().intersects(getLeftBounds()) && displacement.getX() < 0)
            {
                if(hasTakenDamage == false)
                {
                    stopX();
                }else
                {
                    displacement.setX(displacement.getX() * -1);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkHeadCollision(Level1SmallPlatform[] s)
    {
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getBounds().intersects(getHeadBounds()))
            {
                displacement.setY(1);
                return true;
            }
        }
        return false;
    }
    
    public boolean checkHeadCollision(Level1LargePlatform[] l)
    {
        for(int i = 0; i < l.length; i++)
        {
            if(l[i].getBounds().intersects(getHeadBounds()))
            {
                displacement.setY(1);
                return true;
            }
        }
        return false;
    }
    
    public boolean checkHeadCollision(Level2SmallPlatform[] s)
    {
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getBounds().intersects(getHeadBounds()))
            {
                displacement.setY(1);
                return true;
            }
        }
        return false;
    }
    
    public boolean checkHeadCollision(Level2LargePlatform[] l)
    {
        for(int i = 0; i < l.length; i++)
        {
            if(l[i].getBounds().intersects(getHeadBounds()))
            {
                displacement.setY(1);
                return true;
            }
        }
        return false;
    }
    
    public boolean checkHeadCollision(Level3Platform[] s)
    {
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getBounds().intersects(getHeadBounds()))
            {
                displacement.setY(1);
                return true;
            }
        }
        return false;
    }
    
    public void cavemenSwordRift(Caveman[] c)
    {
        for(int i = 0; i < c.length; i++)
        {
            if(c[i].getPosition().getX() <= position.getX() + 960 && c[i].getPosition().getX() + c[i].getSpriteWidth() >= position.getX() - 960)
            {
                if(orbs == 5)
                {
                    c[i].setIsAlive(false);
                }
            }
        }
        
    }
    
    public void mummySwordRift(Mummy[] m)
    {
        for(int i = 0; i < m.length; i++)
        {
            if(m[i].getPosition().getX() <= position.getX() + 960 && m[i].getPosition().getX() + m[i].getSpriteWidth() >= position.getX() - 960)
            {
                if(orbs == 5)
                {
                    m[i].setIsAlive(false);
                }
            }
        }
        
    }
    
    public void resetOrbs()
    {
        if(orbs == 5)
        {
            orbs = 0;
        }
    }
}

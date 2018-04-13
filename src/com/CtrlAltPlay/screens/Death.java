
package com.CtrlAltPlay.screens;

import com.CtrlAltPlay.characters.Champion;
import com.CtrlAltPlay.game.Game;
import com.CtrlAltPlay.sounds.Sounds;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Death extends JPanel{

    private Game game;
    private BufferedImage background;
    private int level;
    private int lives;
    
    public Death(Game theGame, int newLevel, int newLives){
        game = theGame;
        level = newLevel;
        lives = newLives;
        init();
    }
    
    private void init()
    {
        
        try{
            background = ImageIO.read(getClass().getResource("/Images/blackBackground.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Font font = new Font("Arial", Font.PLAIN, 40);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(Game.xScaleFactor, Game.yScaleFactor);
        g2d.setFont(font);
        g2d.drawImage(background, 0, 0, null);
        g2d.setColor(Color.WHITE);
        g2d.drawString("you have been vanquished", 750, 265);
        g2d.drawString("Press space to restart the level", 750, 465);
        g2d.drawString("Press esc to return to main menu", 750, 665);
        g.dispose();
    }
    

    private class TAdapter extends KeyAdapter{
        
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_SPACE:
                    switch(level)
                    {
                        case 1:
                            game.startGame(5, lives-1, 0);
                            break;
                            
                        case 2:
                            game.startLevel2(5, lives-1, 0);
                            break;
                            
                        case 3:
                            game.startLevel3(5, lives-1, 0);
                            break;
                            
                        default:
                            break;
                    }
                    break;
                    
                case KeyEvent.VK_ESCAPE:
                    game.startMainMenu();
                    break;
                    
            }
        }
        
    }
}

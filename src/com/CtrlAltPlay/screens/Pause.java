
package com.CtrlAltPlay.screens;

import com.CtrlAltPlay.game.Game;
import com.CtrlAltPlay.sounds.Sounds;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Pause extends JPanel{

    private Game game;
    private BufferedImage background;
    private int level;
    
    public Pause(Game theGame, int newLevel){
        game = theGame;
        level = newLevel;
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
        addMouseListener(new MAdapter());
        //Sounds.play(getClass().getResourceAsStream("/Sounds/music.wav"), true);
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
        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(700, 200, 500, 100);
        g2d.fillRect(700, 800, 500, 100);
        
        g2d.setColor(Color.WHITE);
        g2d.drawString("Resume", 850, 265);
        g2d.drawString("Go to main menu", 830, 865);
        g.dispose();
    }
    
    
    
    private class MAdapter extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            switch(e.getButton()){
                case MouseEvent.BUTTON1:
                    if(e.getX() <= (1200 * Game.xScaleFactor) && e.getX() >= (700 * Game.xScaleFactor) && e.getY() >= (200 * Game.yScaleFactor) && e.getY() <= (300 * Game.yScaleFactor))
                    {
                        if(level == 1)
                        {
                            game.resumeLevel1();
                        }else if(level == 2)
                        {
                            game.resumeLevel2();
                        }else if(level == 3)
                        {
                            game.resumeLevel3();
                        }
                    }
                    
                    if(e.getX() <= (1200 * Game.xScaleFactor) && e.getX() >= (700 * Game.xScaleFactor) && e.getY() >= (800 * Game.yScaleFactor) && e.getY() <= (900 * Game.yScaleFactor))
                    {
                        game.startMainMenu();
                    }
                    break;
                    
            }
        }
        
    }
}
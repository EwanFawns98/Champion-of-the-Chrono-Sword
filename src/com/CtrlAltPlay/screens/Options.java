
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
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Options extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private Timer timer;
    private boolean onResolution;
    private int sound;
    
    public Options(Game theGame){
        game = theGame;
        
        init();
    }
    
    private void init()
    {
        sound = 5;
        onResolution = false;
        
        try{
            background = ImageIO.read(getClass().getResource("/Images/Placeholder background.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        setFocusable(true);
        setDoubleBuffered(true);
        addMouseListener(new MAdapter());
        timer = new Timer(10, this);
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
        g2d.fillRect(50, 200, 500, 400);
        g2d.fillRect(1300, 200, 500, 800);
        g2d.fillRect(50, 800, 500, 100);
        
        g2d.setColor(Color.BLUE);
        g2d.fillRect(100, 230, 400, 50);
        
        if(onResolution == true)
        {
        g2d.fillRect(100, 290, 400, 50);
        g2d.fillRect(100, 350, 400, 50);
        g2d.fillRect(100, 410, 400, 50);
        g2d.fillRect(100, 470, 400, 50);
        }
        
        g2d.setColor(Color.WHITE);
        g2d.drawString("Resolution", 200, 265);
        g2d.drawString("Sound Options", 180, 865);
        
        if(onResolution == true)
        {
            g2d.drawString("1920x1080", 200, 325);
            g2d.drawString("1360x768", 200, 385);
            g2d.drawString("1280x1024", 200, 445);
            g2d.drawString("1280x960", 200, 505);
        }
        
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        repaint();
    }
    
    
    public void startTimer()
    {
        timer.start();
    }
    
    public void stopTimer()
    {
        timer.stop();
    }
    
    
    

    
    private class MAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            switch(e.getButton()){
                case MouseEvent.BUTTON1:
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (100 * Game.xScaleFactor) && e.getY() >= (230 * Game.yScaleFactor) && e.getY() <= (270 * Game.yScaleFactor))
                    {
                        if(onResolution == false){
                            onResolution = true;
                        }else{
                            onResolution = false;
                        }
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (100 * Game.xScaleFactor) && e.getY() >= (290 * Game.yScaleFactor) && e.getY() <= (340 * Game.yScaleFactor) && onResolution == true)
                    {
                        onResolution = false;
                        Game.xScaleFactor = 1;
                        Game.yScaleFactor = 1;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (100 * Game.xScaleFactor) && e.getY() >= (350 * Game.yScaleFactor) && e.getY() <= (400 * Game.yScaleFactor) && onResolution == true)
                    {
                        onResolution = false;
                        Game.xScaleFactor = 0.70833333;
                        Game.yScaleFactor = 0.711111;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (100 * Game.xScaleFactor) && e.getY() >= (410 * Game.yScaleFactor) && e.getY() <= (460 * Game.yScaleFactor) && onResolution == true)
                    {
                        onResolution = false;
                        Game.xScaleFactor = 0.666666666;
                        Game.yScaleFactor = 0.9481481481;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (100 * Game.xScaleFactor) && e.getY() >= (470 * Game.yScaleFactor) && e.getY() <= (520 * Game.yScaleFactor) && onResolution == true)
                    {
                        onResolution = false;
                        Game.xScaleFactor = 0.666666666;
                        Game.yScaleFactor = 0.8888888;
                    }
                    break;
                    
            }
        }
        
    }
}
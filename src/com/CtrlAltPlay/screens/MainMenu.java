
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


public class MainMenu extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private Timer timer;
    private Champion player;
    
    public MainMenu(Game theGame){
        game = theGame;
        player = new Champion(260, 130);
        init();
    }
    
    private void init()
    {
        
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
        
        g2d.setFont(font);
        
        g2d.drawImage(background, 0, 0, null);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 200, 500, 100);
        g2d.fillRect(0, 400, 500, 100);
        g2d.fillRect(0, 600, 500, 100);
        g2d.fillRect(0, 800, 500, 100);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Start Game", 150, 265);
        g2d.drawString("Controls", 180, 465);
        g2d.drawString("Tutorial", 180, 665);
        g2d.drawString("Options", 180, 865);
        g2d.scale(5, 5);
        player.draw(g2d);
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        updateMove();
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
    
    
    private void updateMove()
    {
        player.doMove();
    }
    

    
    private class MAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            switch(e.getButton()){
                case MouseEvent.BUTTON1:
                    if(e.getX() <= 500 && e.getX() >= 0 && e.getY() >= 200 && e.getY() <= 300)
                    {
                        game.startGame();
                    }
                    break;
                    
            }
        }
        
    }
}

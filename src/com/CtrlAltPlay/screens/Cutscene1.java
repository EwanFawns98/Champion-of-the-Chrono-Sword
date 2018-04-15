
package com.CtrlAltPlay.screens;
import com.CtrlAltPlay.animation.TextTyper;
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
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Cutscene1 extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private String[] text;
    private TextTyper textTyper;
    Timer timer;
    
    public Cutscene1(Game theGame){
        game = theGame;
        text = new String[14];
        init();
    }
    
    private void init()
    {
        
        try{
            background = ImageIO.read(getClass().getResource("/Images/blackBackground.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        text[0] = "From the heavens came the Chrono Sword and the Sword of Light. 100 years ago the Shadow King";
        text[1] = "invaded the Titan Kingdom and stole the Sword of Time from the Heavens Temple in an attempt";
        text[2] = "to corrupt the Sword which would have caused all timelines to occur at once, thereby";
        text[3] = "destroying the flow of time itself. After a great battle he was defeated and the Shadow";
        text[4] = "King vanished, the Chrono Sword was returned to the Heavens Temple alongside the Sword of";
        text[5] = "Light. The King of Titan decided to choose two champions to protect both swords;";
        text[6] = "the Champion of the Chrono Sword and Champion of the Sword of Light.";
        
        text[7] = "The Shadow king has now re-emerged to exact his vengeance upon the kingdom of Titan.";
        text[8] = "He has slain the champion of the Sword of Light and has stolen the blade, corrupting it";
        text[9] = "and plunging the world into darkness. The champion of the Chrono sword has been tasked by the";
        text[10] = "king of Titan to use the sword's power to go back in time and vanquish the shadow king once and for all.";
        text[11] = "As the champion opened a gateway into the past, he underestimated the sword's power and travelled back";
        text[12] = "too far into the past, bringing the darkness with him. He must now vanquish the darkness and travel";
        text[13] = "through time once more.";
        
        textTyper = new TextTyper(1, text);
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
        timer = new Timer(10, this);
        if(Game.musicIsPlaying == false)
        {
            Sounds.play(getClass().getResourceAsStream("/Sounds/music.wav"), true);
            Game.musicIsPlaying = true;
        }
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
        g2d.drawString(text[0].substring(0, textTyper.getSubStringIndex()[0]), 50, 65);
        g2d.drawString(text[1].substring(0, textTyper.getSubStringIndex()[1]), 50, 115);
        g2d.drawString(text[2].substring(0, textTyper.getSubStringIndex()[2]), 50, 165);
        g2d.drawString(text[3].substring(0, textTyper.getSubStringIndex()[3]), 50, 215);
        g2d.drawString(text[4].substring(0, textTyper.getSubStringIndex()[4]), 50, 265);
        g2d.drawString(text[5].substring(0, textTyper.getSubStringIndex()[5]), 50, 315);
        g2d.drawString(text[6].substring(0, textTyper.getSubStringIndex()[6]), 50, 365);
        g2d.drawString(text[7].substring(0, textTyper.getSubStringIndex()[7]), 50, 465);
        g2d.drawString(text[8].substring(0, textTyper.getSubStringIndex()[8]), 50, 515);
        g2d.drawString(text[9].substring(0, textTyper.getSubStringIndex()[9]), 50, 565);
        g2d.drawString(text[10].substring(0, textTyper.getSubStringIndex()[10]), 50, 615);
        g2d.drawString(text[11].substring(0, textTyper.getSubStringIndex()[11]), 50, 715);
        g2d.drawString(text[12].substring(0, textTyper.getSubStringIndex()[12]), 50, 765);
        g2d.drawString(text[13].substring(0, textTyper.getSubStringIndex()[13]), 50, 815);
        g2d.drawString("Press space to continue", 750, 1000);
        
        
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textTyper.run();
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

    private class TAdapter extends KeyAdapter{
        
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_SPACE:
                    timer.stop();
                    game.startGame(5, 3, 0);
                    break;
                    
            }
        }
        
    }
}

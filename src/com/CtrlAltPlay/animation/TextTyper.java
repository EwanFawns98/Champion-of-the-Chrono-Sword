
package com.CtrlAltPlay.animation;


public class TextTyper {
    
    private int speed; 
    private int index;
    private String[] text;
    private int[] totalChars;
    private int[] subString;
    private int currentStringIndex;
    
    public TextTyper(int newSpeed, String[] newText)
    {
        // constructer method
        speed = newSpeed;
        text = newText;
        totalChars = new int[text.length];
        subString = new int[text.length];
        currentStringIndex = 0;
        for(int i = 0; i < text.length; i++)
        {
            subString[i] = 0;
            totalChars[i] = text[i].length();
        }
        
        index = 0;
    }
    
    
    public void run(){
        // used to run the textTyper
        if(currentStringIndex < text.length)
        {
        index++;
        if(index > speed)
        {
            index = 0;
            nextLetter();
        }
        }
    }
    
    private void nextLetter()
    {
        // used to move to the next letter in the text. It also goes to the next text if it runs out of characters
        subString[currentStringIndex]++;
        
        if(subString[currentStringIndex] == totalChars[currentStringIndex])
        {
            currentStringIndex++;
        }
        
        
            
        
    }
    
    public int[] getSubStringIndex()
    {
        // used to get the substring index for drawing
        return subString;
    }
}

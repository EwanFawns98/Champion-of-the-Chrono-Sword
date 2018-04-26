
package com.CtrlAltPlay.levels;


public class Vector {
    private int x;
    private int y;
    
    public Vector(){
        //constructer method
        x = 0;
        y = 0;
    }
    
    public Vector(int newX, int newY){
        //overloaded constructer
        x = newX;
        y = newY;
    }
    
    public Vector(Vector v){
        //overloaded constructer
        x = v.getX();
        y = v.getY();
    }
    
    public void add(Vector v){
        // adds one vector to this
        x += v.getX();
        y += v.getY();
    }
    
    public void setToVector(Vector v){
        // sets one vector to this
        x = v.getX();
        y = v.getY();
    }
    
    public void addX(int newX)
    {
        //adds a value to x
        x += newX;
    }
    
    public void addY(int newY)
    {
        //adds a value to y
        y += newY;
    }
    
    public void setX(int x){
        //sets a value to x
        this.x = x;
    }
    
    public void setY(int y){
        //set a value to y
        this.y = y;
    }
    
    public int getX(){
        //gets the x value
        return x;
    }
    
    public int getY(){
        //gets the y value
        return y;
    }
}

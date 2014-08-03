/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.applet.AudioClip;
import java.util.*;

/**
 *
 * @author tsaitl
 */
public class Snake extends JFrame {

    
    final int WIDTH = 400, HEIGHT = 400;
    final int SIZE = 20;
    double speed = 1; 
    final int UP = 0, RIGHT = 3, DOWN = 1, LEFT = 2, NONE = 4;
    int DIR = RIGHT;
    int STEP = 5;
    
    int LENGH = 3; 
    int HEAD = 1;
    int TAIL = LENGH;
    
    int SPEED = 1; 
    
    int startX = SIZE+SIZE/2;
    int startY = HEIGHT-SIZE-SIZE/2;
    
    int baitX = (int)(Math.random()*(WIDTH-2*SIZE))+SIZE/2;
    int baitY = (int)(Math.random()*(HEIGHT-3*SIZE))+SIZE+SIZE/2;
        
    Rectangle bait = new Rectangle(baitX,baitY,SIZE,SIZE);
    Rectangle b  = new Rectangle();
    Rectangle[] sqr = new Rectangle[101];
    
    public Snake(){
        
        super("Snake");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
        Move m = new Move();
        m.start();
        
        // filling up sqr array with square blocks for snake
        for(int i = 1; i <= 100; i++)
            {
             Rectangle r = new Rectangle(startX-SIZE*i,startY,SIZE,SIZE);
             sqr[i] = r;
            }
        
        System.out.println("test output");
        
        repaint();
    }
    
    private void Dinner(){

                System.out.println("head: "+HEAD);
                System.out.println("tail: "+TAIL);

                int new_baitX = (int)(Math.random()*(WIDTH-2*SIZE))+SIZE/2;
                int new_baitY = (int)(Math.random()*(HEIGHT-3*SIZE))+SIZE+SIZE/2;
                bait.x = new_baitX;
                bait.y = new_baitY;

                SPEED++;

                
                if(DIR == RIGHT){
                    for(int i=HEAD; i<=LENGH; i++){
                         sqr[HEAD+i].x = sqr[HEAD+i].x+SIZE;
                    }
                }
                /*
                if(DIR == LEFT){
                    for(int i=HEAD; i<=LENGH; i++){
                         sqr[HEAD+i].x = sqr[HEAD+i].x-SIZE;
                    }
                }
                
                if(DIR == UP){
                    for(int i=HEAD; i<=LENGH; i++){
                         sqr[HEAD+i].y = sqr[HEAD+i].y+SIZE;
                    }
                }
                
                if(DIR == DOWN){
                    for(int i=HEAD; i<=LENGH; i++){
                         sqr[HEAD+i].y = sqr[HEAD+i].y-SIZE;
                    }
                }
                */
                
                /*
                 if(DIR == RIGHT){
                        // adding new sqaure
                    sqr[LENGH+1].x = sqr[HEAD].x+SIZE;
                    sqr[LENGH+1].y = sqr[HEAD].y;
                    }*/
                    
                LENGH++;
                //HEAD++;
    }
    
    public void paint(Graphics g){
        
        // background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        // painting the Snake
        g.setColor(Color.RED);
        for(int i = 1; i <= LENGH; i++){
            g.fill3DRect(sqr[i].x,sqr[i].y,sqr[i].width,sqr[i].height,true);
            }
        
        /// painting the bait 
        g.setColor(Color.YELLOW);
        g.fill3DRect(bait.x,bait.y,bait.width,bait.height,true);
        
        }
    
    
    
    private class Move extends Thread implements KeyListener{
       
        public void run(){
            addKeyListener(this);
            DIR = RIGHT;
            while(true){
                //System.out.println("test"+i);
                
                try{
                    
                    
                        if(DIR==RIGHT)  {
                            sqr[TAIL].x = sqr[HEAD].x+SIZE;   // rigth
                            sqr[TAIL].y = sqr[HEAD].y;        // same vert
                        }
                         
                        if(DIR==UP)  {
                            sqr[TAIL].x = sqr[HEAD].x;        // same hor
                            sqr[TAIL].y = sqr[HEAD].y-SIZE;   // up
                        }

                        if(DIR==DOWN)  {
                            sqr[TAIL].x = sqr[HEAD].x;        // same hor
                            sqr[TAIL].y = sqr[HEAD].y+SIZE;   // up
                        }
                        if(DIR==LEFT)  {
                            sqr[TAIL].x = sqr[HEAD].x-SIZE;   // left
                            sqr[TAIL].y = sqr[HEAD].y;        // same vert
                        }
                           
                        
                        /// update HEAD & TAIL
                        
                        HEAD -=1;
                        TAIL -=1;
                        if(HEAD==0) HEAD = LENGH;
                        if(TAIL==0) TAIL = LENGH; 
                        //System.out.println("head: "+HEAD);
                        //System.out.println("tail: "+TAIL);
                        
                        
                        
                    if(sqr[HEAD].intersects(bait)) Dinner();
                        
                        
                        
                    repaint();
                    Thread.sleep(200/SPEED);
           
                    }catch(Exception e){
                    break;
                }
            }
        }
        
        public void keyPressed(KeyEvent event){ }
        public void keyReleased(KeyEvent event){ }
        public void keyTyped(KeyEvent event){

            
            if(event.getKeyChar()=='k') DIR = DOWN;
            if(event.getKeyChar()=='l') DIR = RIGHT;
            if(event.getKeyChar()=='i') DIR = UP;
            if(event.getKeyChar()=='j') DIR = LEFT;

        } // -key typed
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        new Snake();
        
    }
    
}

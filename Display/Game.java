package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Character.*;
import Event.Event;

public class Game extends JPanel implements KeyListener{

    int gameSpeed = 20;
    long lastPress = 0;
    Dog dog = new Dog(50,300,50,100);
    Wave wave = new Wave(800,300,30,40, 30, this);

    Wave[] waveSet = makeWaveSet(3);

    public Game(){
        this.setBounds(0,0,1000,600);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);
    }

    private Wave[] makeWaveSet(int waveNumber){
        Wave[] waveSet = new Wave[waveNumber];
        for(int i=0; i<waveNumber;i++){
            double waveLocation = 1000+Math.floor(Math.random()*1000);
            waveSet[i] = new Wave((int)waveLocation, 300,30,40, 30, this);
        }
        return waveSet;
    }

    @Override
    public void paint(Graphics g) {
        /*Dog*/
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.drawRect(dog.x,dog.y, dog.dogSize,dog.dogSize);
        g2.drawString(dog.health+" %" , 40,40);
        /*Wave*/
        g2.setColor(Color.black);
        for(Wave wave : waveSet)
        {
            g2.drawRect(wave.x,wave.y, wave.weight,wave.height);
            if(Event.checkHit(dog, wave)){
                g2.setStroke(new BasicStroke(10.0f));
                g2.setColor(Color.red);
                g2.drawRect(0,0, 1000 ,600);
                dog.health -= 10;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(System.currentTimeMillis()-lastPress>600){
            if(e.getKeyCode() == 38 || e.getKeyCode() == 32)
            {
                dog.jump(this);
                this.repaint();
            }
            lastPress = System.currentTimeMillis();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

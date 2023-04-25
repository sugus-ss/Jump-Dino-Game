package Display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.File;
import javax.swing.*;
import javax.imageio.ImageIO;

import Character.*;
import Event.Event;
import Element.*;

public class Game extends JPanel implements KeyListener{

    int gameSpeed = 20;
    private long point = 0;
    private long lastPress=0; // last of click
    Dog dog = new Dog(50,300,50);
    Wave wave = new Wave(800,300,30,40, 30, this);
    Wave[] waveSet = makeWaveSet(3);
    private static int base=400,xStart = 1000;
    static Display display;


    //--------------------Cloud--------------------------------
    private Environment[] envSet = makeEnv(2,Environment.CLOUD);
    private Environment building = new Environment(xStart-100,base-150,this,Environment.BUILDING,4);


    public Game(){
        this.setBounds(0,0,1000,600);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        try {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            this.drawBackground(g2);
            System.out.println("Reading complete.");
            //---POINT----
            g2.setFont(Element.getFont(30));
            g2.setColor(Color.white);
            g2.drawString("Point : "+point,750,40);
            //--- dog --
            g2.setColor(Color.RED);
            drawDogHealth(g2);
            g2.drawImage(dog.getImage(),dog.x,dog.y,dog.dogSize,dog.dogSize, null);
            //----Wave----
            for(Wave item : waveSet) {
                drawWave(item,g2);
            }
            this.point+=1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
    }


    // sky, dir, cloud, building
    private void drawBackground(Graphics2D g2) throws IOException {
        g2.drawImage(ImageIO.read(new File("/Users/gus/Documents/GitHub/Dog_game_java/image/sky.png")),0,0,2000,1000, null);
        g2.drawImage(building.getImage(),building.x,building.y,500,200,null);
        g2.drawImage(ImageIO.read(new File("/Users/gus/Documents/GitHub/Dog_game_java/image/dir.png")),0,base+10,2000,220, null);
        for(Environment item:envSet) {
            g2.drawImage(item.getImage(),item.x,item.y,250,160, null);
        }
    }

    // dogHealth
    private void drawDogHealth(Graphics2D g2) {
        try {
            g2.drawImage(ImageIO.read(new File("/Users/gus/Documents/GitHub/Dog_game_java/image/heart.png")),10,20, 20,20,null);
            g2.setStroke(new BasicStroke(18.0f));
            g2.setColor(new Color(241, 98, 69));
            g2.drawLine(60, 30,60+dog.health,30);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(6.0f));
            g2.drawRect(50,20, 200,20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawWave(Wave wave,Graphics2D g2) {
        g2.drawImage(wave.getImage(),wave.x ,(wave.y-wave.height),40,wave.height+10,null);
        if(Event.checkHit(dog,wave)){
            g2.setColor(new Color(241, 98, 69));
            g2.fillRect(0, 0,1000,1000);
            dog.health-=10;
            if(dog.health<=0) {
                //display.endGame(this.point);
                dog.health = new Dog().health;
                this.point = 0;
            }
        }
    }

    private Wave[] makeWaveSet(int waveNumber){
        Wave[] waveSet = new Wave[waveNumber];
        for(int i=0; i<waveNumber;i++){
            double waveLocation = 1000+Math.floor(Math.random()*1000);
            waveSet[i] = new Wave((int)waveLocation, 300,30,40, 30, this);
        }
        return waveSet;
    }

    private Environment[] makeEnv(int size,int eType){
        Environment[] envSet = new Environment[size];
        int far = 0;
        for(int i=0;i<size;i++) {
            envSet[i] = new Environment(xStart+far,20,this,eType,10);
            far+=600;
        }
        return envSet;
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

package Character;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Dog {
    public int x,y,dogSize;

    public int health =180;
    private int jumpHeight = 100;

    public Dog(){

    }
    public Dog(int x, int y, int dogSize)
    {
        this.x=x;
        this.y=y;
        this.dogSize=dogSize;
    }
    public void jump(JPanel game) {
        this.y -= jumpHeight;
        game.repaint();
        Timer timer = new Timer(450, new ActionListener() {             //java swing
            @Override
            public void actionPerformed(ActionEvent e) {
                y += jumpHeight;
                game.repaint();
            }
        });//loop
        timer.setRepeats(false);//stop loop
        timer.start();
    }

    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("/Users/gus/Documents/GitHub/Dog_game_java/image/dog.png"));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}

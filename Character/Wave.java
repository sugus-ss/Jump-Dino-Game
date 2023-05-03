package Character;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Wave {
    public int x, y, weight, height, speed;
    private int xStart;

    public Wave(int x, int y, int weight, int height, int speed, JPanel game) {
        this.x = x;
        this.xStart = x;
        this.y = y;
        this.weight = weight;
        this.height = height;
        this.speed = speed;
        move(game);
    }

    public void move(JPanel game)//default repeat
    {
        Timer timer = new Timer(50, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                x -= speed;
                game.repaint();
                if(x<0){
                    x=xStart;
                }
            }
        });
        timer.start();
    }

    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("/Users/gus/Documents/GitHub/Dog_game_java/image/dino.png"));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}

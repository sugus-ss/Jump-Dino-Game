package Character;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Environment {
    public int x;
    public int y;
    public int startX;
    public int speed;
    public int envType;
    public static int CLOUD = 0,BUILDING=1;
    private Timer timeMove;
    public Environment(int x,int y,JPanel page,int envType,int speed) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.speed = speed;
        this.envType = envType;
        this.move(page);
    }

    public void move(JPanel page) {
        this.timeMove = new Timer(10,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(x+400<0) {
                    x = startX; // loop of environment
                }
                x -= speed;
                page.repaint();
            }
        });
        this.timeMove.start();
    }

    public String getEnvType(int envType){
        String[] name = new String[] {"cloud.png","building.png"};
        return name[envType];
    }

    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("/Users/gus/Documents/GitHub/Dog_game_java/image/"+ getEnvType(this.envType)));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}

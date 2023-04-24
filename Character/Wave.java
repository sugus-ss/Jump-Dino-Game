package Character;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}

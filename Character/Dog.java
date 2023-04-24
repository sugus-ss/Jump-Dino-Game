package Character;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dog {
    public int x,y,dogSize,health;
    private int jumpHeight = 100;
    public Dog(int x, int y, int dogSize,int health)
    {
        this.x=x;
        this.y=y;
        this.dogSize=dogSize;
        this.health=health;
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
}
